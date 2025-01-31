package markup;
import java.util.List;
public class Paragraph extends AbstractList{
    public Paragraph(List<AbstractList> list){
        super(list);
    }
    public void toMarkdown(StringBuilder line){
        super.toMarkdown(line);
    }
    public void toBBCode(StringBuilder line){
        super.toBBCode(line);
    }
}