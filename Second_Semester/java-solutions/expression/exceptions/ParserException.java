package expression.exceptions;
import java.text.ParseException;

public class ParserException extends ParseException {
    public ParserException(String message){
        super(message,0);
    }
}