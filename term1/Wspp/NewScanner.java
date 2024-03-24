import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.nio.*;
public class NewScanner {
    private Reader input;
    private char buff[] = new char[1];
    private int read = -1;
    private int metka = read;
    NewScanner(InputStream input) {
        try {
            this.input = new InputStreamReader(input,"UTF8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    NewScanner(String input) {
        this.input = new StringReader(input);
    }
    char readInput() {
        try {
            if (this.metka >= this.read) {
                this.read = this.input.read(this.buff);
                if(this.read < 0){
                    return '\0';
                }
                this.metka = 0;
            }
            return this.buff[this.metka++];
        } catch (IOException e) {
            e.printStackTrace();
            return '0';
        }
    }
    boolean end(){
        char simbol = readInput();
        this.metka--;
        if(read < 0){
            return true;
        }
        return false;
    }
    boolean hasNext(){
        char simbol = readInput();
        while(read > 0) {
            if(!Character.isWhitespace(simbol)){
                this.metka--;
                return true;
            }
            simbol = readInput();
        }
        return false;
    }
    String next(){
        StringBuilder line = new StringBuilder("");
        char simbol = readInput();
        while(read > 0) {
            if (!Character.isWhitespace(simbol)) {
                line.append(simbol);
            }
            else {
                if (line.length() > 0){
                    break;
                }
            }
            simbol = readInput();
        }
        return line.substring(0, line.length());
    }
    boolean hasNextInt(){
        char simbol = readInput();
        while(read > 0) {
            if(Character.isDigit(simbol) || simbol == '-'){
                this.metka--;
                return true;
            }
            simbol = readInput();
        }
        return false;
    }
    int nextInt(){
        StringBuilder line = new StringBuilder("");
        char simbol = readInput();
        char minus = '0';
        while(read > 0) {
            if (Character.isDigit(simbol)) {
                if(minus == '-') {
                    line.append(minus);
                }
                line.append(simbol);
            }
            else {
                if (line.length() > 0){
                    break;
                }
            }
            minus = simbol;
            simbol = readInput();
        }
        return Integer.parseInt(line.substring(0, line.length()));
    }
    boolean hasNextLine(){
        if(this.metka != this.read) {
            char simbol = readInput();
        }
        char simbol = readInput();
        char copy;
        if(this.buff[this.metka] == System.lineSeparator().charAt(0)) {
            return true;
        }
        if(read > 0){
            this.metka--;
            return true;
        }
        else{
            return false;
        }
    }
    String nextLine() {
        StringBuilder line = new StringBuilder("");
        while(this.buff[this.metka] != System.lineSeparator().charAt(0)) {
            this.metka += System.lineSeparator().length();
            char simbol = readInput();
            if(simbol != '\n'){
                line.append(simbol);
            }
            simbol = readInput();
        }
        return line.substring(0, line.length());
    }
    void close(){
        try {
            this.input.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}