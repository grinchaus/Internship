(defn constant [const] (fn [trash] const))
(defn variable [symbol] (fn [variables] (variables symbol)))
(defn MultiOperation [func] (fn [& elements] (fn [variables] (apply func (map #(% variables) elements)))))
(def add (MultiOperation +))
(def subtract (MultiOperation -))
(def negate (MultiOperation -))
(def multiply (MultiOperation *))
(defn miniDivide
  ([x] (/ 1.0 x))
  ([x y] (/ x (double y)))
  ([x y & more] (apply miniDivide (/ x (double y)) more)))
(def divide (MultiOperation miniDivide))
(def helpMean (fn [& num] (/ (apply + num) (count num))))
(def mean (MultiOperation helpMean))
(def varn (MultiOperation (fn [& num] (/ (apply + (map #(* % %) (map #(- % (apply helpMean num)) num))) (count num)))))
(def sin (MultiOperation #(Math/sin %)))
(def cos (MultiOperation #(Math/cos %)))
(def MultiUnary {'+ add, '- subtract, 'negate negate, '* multiply, '/ divide, 'mean mean, 'varn varn, 'sin sin, 'cos cos})
(defn parser [expression const vars multiMap] (cond
    (string? expression) (parser (read-string expression) const vars multiMap)
    (number? expression) (const expression)
    (symbol? expression) (vars (str expression))
    ::else (apply (get multiMap (first expression)) (map #(parser % const vars multiMap) (rest expression)))))

;============================================================================================== Object

(defn evaluate [exp args]
  ((:evaluate exp) args))
(defn toString [exp]
  (:toString exp))

(defn toStringPostfix [exp]
  (:toStringPostfix exp))
(defn Constant [num]
  {
   :evaluate (constantly num)
   :toString (str num)
   :toStringPostfix (str num)
   })
(defn Variable [symbol]
  {
   :evaluate (fn [variable] (variable (clojure.string/lower-case (get symbol 0))))
   :toString symbol
   :toStringPostfix symbol
   })
(defn multiOperation [func, operation] (fn [& args]
    {
     :evaluate (fn [variables] (apply func (map #((:evaluate %) variables) args)))
     :toString (str "(" operation " " (clojure.string/join " " (map :toString args)) ")")
     :toStringPostfix (str "(" (clojure.string/join " " (map :toStringPostfix args)) " " operation ")")
     }))
(def Add (multiOperation + '+))
(def Subtract (multiOperation - '-))
(def Multiply (multiOperation * '*))
(def Divide (multiOperation miniDivide '/))
(def Negate (multiOperation - 'negate))
(def Exp (multiOperation #(Math/exp %) 'exp))
(def Ln (multiOperation #(Math/log %) 'ln))
(def objMultiUnary {'+ Add, '- Subtract, 'negate Negate, '* Multiply, '/ Divide, 'exp Exp, 'ln Ln})
(defn parseFunction [expression] (parser expression constant variable MultiUnary))
(defn parseObject [expression] (parser expression Constant Variable objMultiUnary))

;============================================================================================== Parser

(load-file "parser.clj")
(def *space (+char " \t\n\r"))
(def *ws (+ignore (+star *space)))
(def *digit (+char ".0123456789"))
(defn sign [s tail]
  (if (#{\- \+} s)
  (cons s tail)
  tail))
(def *constant (+map #(Constant (read-string %)) (+str (+seqf sign (+opt (+char "+-")) (+plus *digit)))))
(def *letter (+char "xyzXYZ"))
(def *variable (+map Variable (+str (+plus *letter))))
(def *operation (+char (apply str (keys objMultiUnary))))
(defn *getObj [el] (get objMultiUnary (read-string el)))
(def *operator (+map *getObj (+str (+plus *operation)))) ; переписать всё что снизу включая концы
(def *value
  (+seqf
    (fn [oper operand] (apply operand oper))
    (+ignore (+char "(")) *ws
    (+plus (+seqn 0 (+or *constant *variable (delay *value)) *ws))
    *operator *ws
    (+ignore (+char ")"))))
(def *expression (+seqn 0 *ws (+or *constant *variable *value) *ws))
(def parseObjectPostfix (+parser *expression))