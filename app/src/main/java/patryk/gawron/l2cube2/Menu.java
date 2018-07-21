package patryk.gawron.l2cube2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void button1Pressed(View view) {
        Intent intent = new Intent(this, FaceRotations.class);
        startActivity(intent);
    }

    public void button2Pressed(View view) {
        Intent intent = new Intent(this, AlgorithmsMenu.class);
        startActivity(intent);
    }
}
