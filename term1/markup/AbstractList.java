package markup;
import java.util.List;
public abstract class AbstractList {
    protected  List<AbstractList> list;
    public AbstractList(List<AbstractList> list) {
        this.list = list;
    }
    public  void toMarkdown(StringBuilder builder) {
        for (AbstractList element : list) {
            element.toMarkdown(builder);
        }
    }
    public  void toBBCode(StringBuilder builder) {
        for (AbstractList element : list) {
            element.toBBCode(builder);
        }
    }
}
