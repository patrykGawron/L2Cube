package patryk.gawron.l2cube2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class CustomGLSurfaceView extends GLSurfaceView {

    private final float TOUCH_SCALE_FACTOR = 80.0f / 320;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final GLRenderer mRenderer;

    private float[][] translations = new float[27][16];
    private final float[] mView = new float[16];
    private final float[] mMVP = new float[16];

    private Spot[] spots = new Spot[27];
    private Cube[] mCube = new Cube[27];

    public static boolean rotate = false;

    private float mPreviousX;
    private float mPreviousY;
    private static float xAngle;
    private static float yAngle;

    private float angle = 0f;
    private float dAngle = 2f;

    private int rotationIndex = 0;

    private  void rotate(final Name... order){
        rotate = false;
        try{
            scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                        switch (order[rotationIndex]) {
                            case R:
                                draw(spotsToChange[0], axiesData[0], Name.R, true);
                                break;
                            case RP:
                                draw(spotsToChange[1], axiesData[1], Name.RP, true);
                                break;
                            case L:
                                draw(spotsToChange[2], axiesData[2], Name.L, true);
                                break;
                            case LP:
                                draw(spotsToChange[3], axiesData[3], Name.LP,true);
                                break;
                            case F:
                                draw(spotsToChange[4], axiesData[4], Name.F,true);
                                break;
                            case FP:
                                draw(spotsToChange[5], axiesData[5], Name.FP,true);
                                break;
                            case B:
                                draw(spotsToChange[6], axiesData[6], Name.B,true);
                                break;
                            case BP:
                                draw(spotsToChange[7], axiesData[7], Name.BP,true);
                                break;
                            case U:
                                draw(spotsToChange[8], axiesData[8], Name.U,true);
                                break;
                            case UP:
                                draw(spotsToChange[9], axiesData[9], Name.UP,true);
                                break;
                            case D:
                                draw(spotsToChange[10], axiesData[10], Name.D,true);
                                break;
                            case DP:
                                draw(spotsToChange[11], axiesData[11], Name.DP,true);
                                break;
                            case M:
                                draw(spotsToChange[12], axiesData[12], Name.M,true);
                                break;
                            case MP:
                                draw(spotsToChange[13], axiesData[13], Name.MP,true);
                                break;
                            case E:
                                draw(spotsToChange[14], axiesData[14], Name.E,true);
                                break;
                            case EP:
                                draw(spotsToChange[15], axiesData[15], Name.EP,true);
                                break;
                            case S:
                                draw(spotsToChange[16], axiesData[16], Name.S,true);
                                break;
                            case SP:
                                draw(spotsToChange[17], axiesData[17], Name.SP,true);
                                break;
                            case NULL:
                                draw(spotsToChange[0], axiesData[0], Name.R, false);
                                break;
                        }
                    }
            }, 0, 16, TimeUnit.MILLISECONDS);
        }catch(RejectedExecutionException | NullPointerException | IllegalArgumentException e){
            Log.e("FIXED RATE SCHEDULER", "Error", e);
        }
    }

    public enum Name{
        R, L, F, B, U, D, RP, LP, FP, BP, DP, UP, M, MP, E, EP, S, SP, NULL
    }

    private int[][] spotsToChange = {
            {20, 26, 8, 2, 14, 11, 23, 17, 5},      //R     0
            {20, 2, 8, 26, 14, 11, 5, 17, 23},      //R'    1
            {18, 0, 6, 24, 12, 9, 3, 15, 21},       //L     2
            {18, 24, 6, 0, 12, 9, 21, 15, 3},       //L'    3
            {0, 2, 8, 6, 4, 1, 5, 7, 3},            //F     4
            {0, 6, 8, 2, 4, 1, 3, 7, 5},            //F'    5
            {18, 24, 26, 20, 22, 19, 21, 25, 23},   //B     6
            {18, 20, 26, 24, 22, 19, 23, 25, 21},   //B'    7
            {20, 2, 0, 18, 10, 19, 11, 1, 9},       //U     8
            {20, 18, 0, 2, 10, 19, 9, 1, 11},       //U'    9
            {24, 6, 8, 26, 16, 25, 15, 7, 17},      //D     10
            {24, 26, 8, 6, 16, 25, 17, 7, 15},      //D'    11
            {10, 4, 16, 22, 13, 1, 7, 25, 19},      //M     12
            {10, 22, 16, 4, 13, 1, 19, 25, 7},      //M'    13
            {21, 3, 5, 23, 13, 22, 12, 4, 14},      //E     14
            {21, 23, 5, 3, 13, 22, 14, 4, 12},      //E'    15
            {9, 15, 17, 11, 13, 10, 12, 16, 14},    //S     16
            {9, 11, 17, 15, 13, 10, 14, 16, 12},    //S'    17
    };
    private float[][] axiesData = {
            {-1.0f, 0, 0},      //R     0
            {1.0f, 0, 0},       //R'    1
            {1.0f, 0, 0},       //L     2
            {-1.0f, 0, 0},      //L'    3
            {0, 0, -1.0f},      //F     4
            {0, 0, 1.0f},       //F'    5
            {0, 0, 1.0f},       //B     6
            {0, 0, -1.0f},      //B'    7
            {0, -1.0f, 0},      //U     8
            {0, 1.0f, 0},       //U'    9
            {0, 1.0f, 0},       //D     10
            {0, -1.0f, 0},      //D'    11
            {1.0f, 0, 0},       //M     12
            {-1.0f, 0, 0},      //M'    13
            {0, 1.0f, 0},       //E     14
            {0, -1.0f, 0},      //E'    15
            {0, 0, 1.0f},       //S     16
            {0, 0, -1.0f},      //S'    17
    };

    public void clear(){
        for(Cube x : mCube){
            x.clear();
        }
        rebindCubes();
        rotationIndex = 0;
    }
    public CustomGLSurfaceView(Context context){
        super(context);
        setEGLContextClientVersion(2);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        mRenderer = new GLRenderer();
        setRenderer(mRenderer);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    public void draw(final int[] index, final float[] axis, Name rotation, boolean increaseIndex) {
        if(rotate){
            angle += dAngle;
        }
        if (xAngle > 360 || xAngle < -360) {
            xAngle %= 360;
        }
        if (yAngle > 360 || yAngle < -360) {
            yAngle %= 360;
        }

        Matrix.setLookAtM(mView, 0, 0, 0, 6 / mScaleFactor, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVP, 0, mRenderer.getmProjection(), 0, mView, 0);
        Matrix.rotateM(mMVP, 0, yAngle, 1.0f, 0, 0);
        Matrix.rotateM(mMVP, 0, xAngle, 0, 1.0f, 0);

        for (int i = 0; i < mCube.length; i++) {
            translations[i] = Arrays.copyOf(mMVP, mMVP.length);
        }

        for (int i = 0; i < mCube.length; i++) {
            if(increaseIndex){
                if (i == index[0] ||
                        i == index[1] ||
                        i == index[2] ||
                        i == index[3] ||
                        i == index[4] ||
                        i == index[5] ||
                        i == index[6] ||
                        i == index[7] ||
                        i == index[8]
                        ) {
                    for (int j = 0; j < spots.length; j++) {
                        if (spots[i].getBoundCube() == mCube[j].getID()) {
                            Matrix.rotateM(translations[i], 0, angle, axis[0], axis[1], axis[2]);
                            Matrix.translateM(translations[i], 0, spots[i].getX(), spots[i].getY(), spots[i].getZ());
                            for(int n = mCube[spots[i].getBoundCube()].getRotationsPointer() - 1; n >= 0; n--){
                                Float[] tempRotations = mCube[spots[i].getBoundCube()].getRotations(n);
                                System.out.println("0:\t" + tempRotations[0] + "1:\t" + tempRotations[1]);
                                Matrix.rotateM(translations[i], 0 , tempRotations[0], tempRotations[1],
                                        tempRotations[2], tempRotations[3]);
                            }
                            mRenderer.setmMVPMatrix(translations[i], i);
                            mRenderer.setToDraw(mCube[spots[i].getBoundCube()], i);
                            break;
                        }
                    }
                    continue;
                }
            }
            for (int j = 0; j < spots.length; j++) {
                if (spots[i].getBoundCube() == mCube[j].getID()) {
                    Matrix.translateM(translations[i], 0, spots[i].getX(), spots[i].getY(), spots[i].getZ());
                    for(int n = mCube[spots[i].getBoundCube()].getRotationsPointer() - 1; n >= 0; n--){
                        Float[] tempRotations = mCube[spots[i].getBoundCube()].getRotations(n);
                        Matrix.rotateM(translations[i], 0 , tempRotations[0], tempRotations[1],
                                tempRotations[2], tempRotations[3]);
                    }
                    mRenderer.setmMVPMatrix(translations[i], i);
                    mRenderer.setToDraw(mCube[spots[i].getBoundCube()], i);
                    break;
                }
            }
        }
        mRenderer.setReadyToSetUpTrue();
        mRenderer.setReadyToDrawTrue();

        requestRender();

        if(angle == 90 && increaseIndex){

            rotate = false;

            angle = 0;

            int temp = spots[index[3]].getBoundCube();
            int temp1 = spots[index[8]].getBoundCube();
            spots[index[3]].bindCube(spots[index[2]].getBoundCube());
            spots[index[2]].bindCube(spots[index[1]].getBoundCube());
            spots[index[1]].bindCube(spots[index[0]].getBoundCube());
            spots[index[0]].bindCube(temp);
            spots[index[8]].bindCube(spots[index[7]].getBoundCube());
            spots[index[7]].bindCube(spots[index[6]].getBoundCube());
            spots[index[6]].bindCube(spots[index[5]].getBoundCube());
            spots[index[5]].bindCube(temp1);

            switch(rotation){
                case R:
                    for(int i = 0; i < 9; i++){
                        Float[] tempx = {-90f, 1.0f, 0f, 0f};
                        mCube[spots[index[i]].getBoundCube()].pushRotation(tempx);
                    }
                    break;
                case RP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{0f, 1.0f, 0f, 0f});
                    }
                case L:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, 1.0f, 0f, 0f});
                    }
                    break;
                case LP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{-90f, 1.0f, 0f, 0f});
                    }
                    break;
                case F:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{-90f, 0f, 0f, 1.0f});
                    }
                    break;
                case FP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, 0f, 0f, 1.0f});
                    }
                    break;
                case B:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, 0f, 0f, 1.0f});
                    }
                    break;
                case BP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{-90f, 0f, 0f, 1.0f});
                    }
                    break;
                case D:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, 0f, 1.0f, 0f});
                    }
                    break;
                case DP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{-90f, 0f, 1.0f, 0f});
                    }
                    break;
                case U:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{-90f, 0f, 1.0f, 0f});
                    }
                    break;
                case UP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, 0f, 1.0f, 0f});
                    }
                    break;
                case M:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, 1.0f, 0f, 0f});
                    }
                    break;
                case MP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{-90f, 1.0f, 0f, 0f});
                    }
                    break;
                case E:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, 0f, 1.0f, 0f});
                    }
                    break;
                case EP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{-90f, 0f, 1.0f, 0f});
                    }
                    break;
                case S:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, 0f, 0f, 1.0f});
                    }
                    break;
                case SP:
                    for(int i = 0; i < 9; i++){
                        mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{-90f, 0f, 0f, 1.0f});
                    }
                    break;
            }
            rotationIndex++;
        }
    }

    public static void setRotateTrue(){
        rotate = true;
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }

    private ScaleGestureDetector mScaleDetector;
    private static float mScaleFactor = 1.f;
    @Override
    public boolean onTouchEvent(MotionEvent e){
        float x = e.getX();
        float y = e.getY();
        switch (e.getPointerCount()){
            case 1:
                switch (e.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        float dx = x - mPreviousX;
                        float dy = y - mPreviousY;

                        xAngle += dx * TOUCH_SCALE_FACTOR;
                        yAngle += dy * TOUCH_SCALE_FACTOR ;
                }
            case 2:
                mScaleDetector.onTouchEvent(e);
        }

        mPreviousX = x;
        mPreviousY = y;
        return  true;
    }


    public void runCube(){
        createCubes();
        bindCubes();
        rotate(Name.R,
                Name.B,
                Name.D,
                Name.E,
                Name.S,
                Name.R,
                Name.D,
                Name.UP,
                Name.L,
                Name.BP,
                Name.NULL);
    }


    private void createCubes(){
        for(int i =0; i < mCube.length; i++){
            mCube[i] = new Cube(Data.colors[i]);
        }
    }

    private void bindCubes(){
        for(int i = 0; i<27; i++){
            spots[i] = new Spot(Data.translationVectors[Data.spots[i]][0],Data.translationVectors[Data.spots[i]][1],Data.translationVectors[Data.spots[i]][2]);
            spots[i].bindCube(Data.spots[i]);
        }
    }

    private void rebindCubes(){
        for(int i = 0; i < 27; i++){
            spots[i].bindCube(Data.spots[i]);
        }
    }

}
