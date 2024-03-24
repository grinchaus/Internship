public class Sum {
    public static void main(String[] args) {
        int sum = 0; int start = 0;
        for (int i = 0; i < args.length; i++){
            boolean flag = false;
            for (int j = 0; j < args[i].length(); j++){
                if (!Character.isWhitespace(args[i].charAt(j)) && flag == false){
                    start=j;
                    flag=true;
                }
                if (j + 1 == args[i].length() && !Character.isWhitespace(args[i].charAt(j))){
                    sum += Integer.parseInt(args[i].substring(start,j+1));
                }
                else {
                    if (Character.isWhitespace(args[i].charAt(j)) && flag == true) {
                        flag = false;
                        sum += Integer.parseInt(args[i].substring(start,j));
                    }
                }
            }
        }
        System.out.println(sum);
    }
}