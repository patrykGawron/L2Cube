package patryk.gawron.l2cube2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
public class AlgorithmsMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algortihms_menu);
    }
    @Override
    public void onResume(){
        super.onResume();;

    }

    public void button3Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.R, Name.U, Name.RP, Name.NULL});
        intent.putExtra("text", "R U R\' ");
        intent.putExtra("setup", new Name[]{Name.R, Name.UP, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button4Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.UP, Name.R, Name.U, Name.U, Name.RP,
                Name.U, Name.U, Name.R, Name.UP, Name.RP, Name.NULL});
        intent.putExtra("text", "U\' R U U R\' U U R U\' R\' ");
        intent.putExtra("setup", new Name[]{Name.R, Name.U, Name.RP, Name.U, Name.U, Name.R, Name.U, Name.U, Name.RP, Name.U});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button5Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.y, Name.LP, Name.U, Name.L, Name.U, Name.U,
                Name.yp, Name.R, Name.U, Name.RP, Name.NULL});
        intent.putExtra("text", "y L\' U L U U y\' R U R\' ");
        intent.putExtra("setup", new Name[]{Name.R, Name.UP, Name.RP, Name.U, Name.U, Name.y, Name.LP, Name.UP, Name.L, Name.yp});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button6Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.M, Name.U, Name.RP, Name.F, Name.F,
                Name.R, Name.U, Name.LP, Name.U, Name.L, Name.L, Name.RP, Name.NULL});
        intent.putExtra("text", "M U R' F F R U L' U L L L' M' ");
        intent.putExtra("setup", new Name[]{Name.M, Name.UP, Name.L, Name.F, Name.F,
                Name.LP, Name.UP, Name.R, Name.UP, Name.R, Name.R, Name.MP, Name.R, });
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }
}
