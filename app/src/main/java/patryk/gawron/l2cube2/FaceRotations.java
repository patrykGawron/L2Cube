package patryk.gawron.l2cube2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FaceRotations extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_rotations);
    }

    public void button7Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.R, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" R \" is a 90 degree clockwise rotation of the right face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button8Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.RP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" R\' \" is a 90 degree counter-clockwise rotation of the right face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button9Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.L, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" L \" is a 90 degree clockwise rotation of the left face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button10Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.LP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" L\' \" is a 90 degree counter-clockwise rotation of the left face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button11Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.F, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" F \" is a 90 degree clockwise rotation of the front face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button12Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.FP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" F\' \" is a 90 degree counter-clockwise rotation of the front face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button13Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.B, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" B \" is a 90 degree clockwise rotation of the back face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button14Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.BP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" B\' \" is a 90 degree counter-clockwise rotation of the back face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button15Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.U, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" U \" is a 90 degree clockwise rotation of the upper face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button16Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.UP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" U\' \" is a 90 degree counter-clockwise rotation of the upper face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button17Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.D, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" D \" is a 90 degree counter-clockwise rotation of the down face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button18Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.DP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" D\' \" is a 90 degree clockwise rotation of the down face");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }
    public void button19Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.M, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" M \" is a 90 degree clockwise rotation of the middle slice");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button20Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.MP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" M\' \" is a 90 degree counter-clockwise rotation of the middle slice");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button21Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.E, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" E \" is a 90 degree clockwise rotation of the middle equatorial slice");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button22Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.EP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" E\' \" is a 90 degree counter-clockwise rotation of the upper face middle equatorial slice");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button23Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.S, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" S \" is a 90 degree clockwise rotation of the standing slice");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }

    public void button24Pressed(View view) {
        Intent intent = new Intent(this, OpenGLActivity.class);
        intent.putExtra("algorithm", new Name[]{Name.SP, Name.NULL});
        intent.putExtra("text", "");
        intent.putExtra("guide", "\" S\' \" is a 90 degree counter-clockwise rotation of the standing slice");
        intent.putExtra("setup", new Name[]{Name.R, Name.RP});
        Data.setSpotToCubeIDs(Data.clearCube);
        startActivity(intent);
    }


}
