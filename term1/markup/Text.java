package markup;
import java.util.List;
public class Text extends AbstractList{
    String str;
    public Text(String str){
        super(List.of());
        this.str = str;
    }
    @Override
    public void toMarkdown(StringBuilder line){
        line.append(str);
    }
    public void toBBCode(StringBuilder line){
        line.append(str);
    }
}
