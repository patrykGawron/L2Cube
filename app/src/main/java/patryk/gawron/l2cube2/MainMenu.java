package patryk.gawron.l2cube2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button1Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        startActivity(intent);
    }
}
