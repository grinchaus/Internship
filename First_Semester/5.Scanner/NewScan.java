import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.nio.*;
public class NewScan {
    private Reader input;
    private char buff[] = new char[50];
    private int read = -1;
    private int metka = read;
    private char globus;
    NewScan(InputStream input) {
        try {
            this.input = new InputStreamReader(input,"UTF8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    NewScan(String input) {
        this.input = new StringReader(input);
    }
    private char readInput() {
        try {
            if (this.metka >= this.read) {
                this.read = this.input.read(this.buff);
                if(this.read < 0){
                    return '\0';
                }
                this.metka = 0;
            }
//            if(this.buff[this.metka] == System.lineSeparator().charAt(0)) {
//                this.metka += System.lineSeparator().length();
//                //System.err.println("ujdyjfdfsdfdsf");
//                return '\1';
//            }
            if (1 == System.lineSeparator().length() && this.buff[this.metka] == System.lineSeparator().charAt(0)) {
                this.metka++;
                return '\1';
            }
            if (2 == System.lineSeparator().length() && globus == System.lineSeparator().charAt(0) && this.buff[this.metka] == System.lineSeparator().charAt(1)){
                this.metka++;
                return '\1';
            }
            globus = this.buff[this.metka];
            if(globus == System.lineSeparator().charAt(0)){
                this.metka++;
                return '\2';
            }
            return this.buff[this.metka++];
        } catch (IOException e) {
            e.printStackTrace();
            return '0';
        }
    }
    boolean hasNext(){
        char simbol = readInput();
        if (simbol == '\2'){
            simbol = readInput();
        }
        while(simbol != '\0') {
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
        if (simbol == '\2'){
            simbol = readInput();
        }
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
        if (simbol == '\2'){
            simbol = readInput();
        }
        while(simbol != '\0') {
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
        if (simbol == '\2'){
            simbol = readInput();
        }
        char minus = '0';
        while(simbol != '\0') {
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
        char simbol;
      //  if(this.metka != this.read) {
            simbol = readInput();
      //  }
        if(this.metka != this.read) {
            if (simbol == '\2'){
                simbol = readInput();
            }
        }
        char copy;
        if(simbol == '\1') {
            this.metka -= System.lineSeparator().length();
            return true;
        }
        if(simbol != '\0'){
            this.metka--;
            return true;
        }
        else{
            return false;
        }
    }
    String nextLine() {
        StringBuilder line = new StringBuilder("");
        char simbol = readInput();
        if (simbol == '\2'){
            simbol = readInput();
        }
        while(simbol != '\1') {
            line.append(simbol);
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