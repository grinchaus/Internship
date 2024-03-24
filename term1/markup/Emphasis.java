package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup {
    public Emphasis(List<AbstractList> list){
        super(list,"*","[i]","[/i]");
    }
}
