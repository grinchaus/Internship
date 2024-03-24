package markup;

import java.util.List;

public abstract class AbstractMarkup extends AbstractList{
    String tag = "";
    String tagBBCfirst = "";
    String tagBBCend = "";
    protected AbstractMarkup(List<AbstractList> list,String tag,String tagBBCfirst,String tagBBCend){
        super(list);
        this.tag = tag;
        this.tagBBCfirst = tagBBCfirst;
        this.tagBBCend = tagBBCend;
    }
    @Override
    public void toMarkdown(StringBuilder line){
        line.append(tag);
        super.toMarkdown(line);
        line.append(tag);
    }
    public void toBBCode(StringBuilder line){
        line.append(tagBBCfirst);
        super.toBBCode(line);
        line.append(tagBBCend);
    }
}
