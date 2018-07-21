package patryk.gawron.l2cube2;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements GLSurfaceView.Renderer {

    private final float[] mProjection = new float[16];
    private float[][] mMVPMatrix = new float[27][16];
    private boolean readyToSetUp = false;
    private boolean setUp = false;
    private boolean readyToDraw = false;
    private Cube[] toDraw = new Cube[27];

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES30.glClearColor(0.878f, 1.0f, 1.0f, 1.0f);
    }

    public void onDrawFrame(GL10 unused) {
        GLES30.glFlush();
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        if(readyToSetUp && !setUp){
            for(Cube cube : toDraw){
                cube.setup();
            }
            setUp = true;
        }
        if(readyToDraw){
            for(int i = 0; i < toDraw.length; i++){
                toDraw[i].draw(mMVPMatrix[i]);
            }
        }
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjection, 0, -ratio, ratio, -1, 1, 1, 20);
    }
    public float[] getmProjection(){
        return mProjection;
    }

    public void setmMVPMatrix(float[] matrix, int index) {
        mMVPMatrix[index] = matrix;
    }

    public void setToDraw(Cube cube, int index){
        toDraw[index] = cube;
    }
    public void setReadyToSetUpTrue(){
        readyToSetUp = true;
    }
    public void setReadyToDrawTrue(){
        readyToDraw = true;
    }
}
