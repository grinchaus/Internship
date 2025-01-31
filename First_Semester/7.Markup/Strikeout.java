package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup{
    public Strikeout(List<AbstractList> list){
        super(list,"~","[s]","[/s]");
    }
}