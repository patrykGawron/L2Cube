package patryk.gawron.l2cube2;

import android.graphics.Color;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;

public class Text {
    private static int start = 0;
    private static int end = 0;
    static Spannable word;

    public static void setWord(Spannable w){
        word = w;
    }

    public static void parseString(){
        for(int i = end+1; i < word.length(); i++){
            char temp =  word.toString().charAt(i);
            if(temp == ' '){
                start = end;
                end = i;
                break;
            }
        }
        word.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    public static void parseStringBack(){
        word.setSpan(new ForegroundColorSpan(Color.BLACK), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        for(int i = start-1; i >= 0; i--){
            char temp =  word.toString().charAt(i);
            if(temp == ' ' || i == 0){
                end = start;
                start = i;
                break;
            }
        }
    }



    public static void resetStart(){
        start = 0;
    }

    public static void resetEnd(){
        end = 0;
    }
}
