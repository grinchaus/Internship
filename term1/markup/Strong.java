package markup;

import java.util.List;

public class Strong extends AbstractMarkup{
    public Strong(List<AbstractList> list){
        super(list,"__","[b]","[/b]");
    }
}
