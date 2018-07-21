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

    private final int FPS = 60;

    private final float TOUCH_SCALE_FACTOR = 80.0f / 320;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final GLRenderer mRenderer;

    private float[][] translations = new float[27][16];
    private final float[] mView = new float[16];
    private final float[] mMVP = new float[16];

    private int[][] spotsToChange;
    private float[][] axisData;

    private Spot[] spots = new Spot[27];
    private Cube[] mCube = new Cube[27];

    private boolean rotate = false;
    private boolean rotateContinuously = false;
    private boolean reverseOrderActive = false;
    private boolean fullyBacked = true;

    private float mPreviousX;
    private float mPreviousY;
    private float xAngle;
    private float yAngle;
    private float zAngle;

    private float angle = 0f;
    private float dAngle = 2f;
    private float angleDiff = 0f;

    private int rotationIndex = 0;

    Name[] setupAlgorithm;
    Name[] order;
    Name[] reverseOrder;
    Name[] temp;

    private  void rotate(){
        rotate = false;
        try{
            scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                        switch (temp[rotationIndex]) {
                            case R:
                                draw(spotsToChange[0], axisData[0], Name.R, true);
                                break;
                            case RP:
                                draw(spotsToChange[1], axisData[1], Name.RP, true);
                                break;
                            case L:
                                draw(spotsToChange[2], axisData[2], Name.L, true);
                                break;
                            case LP:
                                draw(spotsToChange[3], axisData[3], Name.LP,true);
                                break;
                            case F:
                                draw(spotsToChange[4], axisData[4], Name.F,true);
                                break;
                            case FP:
                                draw(spotsToChange[5], axisData[5], Name.FP,true);
                                break;
                            case B:
                                draw(spotsToChange[6], axisData[6], Name.B,true);
                                break;
                            case BP:
                                draw(spotsToChange[7], axisData[7], Name.BP,true);
                                break;
                            case U:
                                draw(spotsToChange[8], axisData[8], Name.U,true);
                                break;
                            case UP:
                                draw(spotsToChange[9], axisData[9], Name.UP,true);
                                break;
                            case D:
                                draw(spotsToChange[10], axisData[10], Name.D,true);
                                break;
                            case DP:
                                draw(spotsToChange[11], axisData[11], Name.DP,true);
                                break;
                            case M:
                                draw(spotsToChange[12], axisData[12], Name.M,true);
                                break;
                            case MP:
                                draw(spotsToChange[13], axisData[13], Name.MP,true);
                                break;
                            case E:
                                draw(spotsToChange[14], axisData[14], Name.E,true);
                                break;
                            case EP:
                                draw(spotsToChange[15], axisData[15], Name.EP,true);
                                break;
                            case S:
                                draw(spotsToChange[16], axisData[16], Name.S,true);
                                break;
                            case SP:
                                draw(spotsToChange[17], axisData[17], Name.SP,true);
                                break;
                            case y:
                                draw(spotsToChange[0], axisData[0], Name.y, false);
                                break;
                            case yp:
                                draw(spotsToChange[0], axisData[0], Name.yp, false);
                                break;
                            case x:
                                draw(spotsToChange[0], axisData[0], Name.x, false);
                                break;
                            case xp:
                                draw(spotsToChange[0], axisData[0], Name.xp, false);
                                break;
                            case z:
                                draw(spotsToChange[0], axisData[0], Name.z, false);
                                break;
                            case zp:
                                draw(spotsToChange[0], axisData[0], Name.zp, false);
                                break;
                            case NULL:
                                draw(spotsToChange[0], axisData[0], Name.NULL, false);
                                break;
                        }
                    }
            }, 0, 1000/FPS, TimeUnit.MILLISECONDS);
        }catch(RejectedExecutionException | NullPointerException | IllegalArgumentException e){
            Log.e("FIXED RATE SCHEDULER", "Error", e);
        }
    }


    public CustomGLSurfaceView(Context context, Name[] array){
        super(context);
        setEGLContextClientVersion(2);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        mRenderer = new GLRenderer();
        setRenderer(mRenderer);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
        order = array;
        temp = order;
        serReverseOrder();
    }
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus){
        super.onWindowFocusChanged(hasWindowFocus);
    }
    @Override
    public void onPause(){
        super.onPause();
    }
    public void draw(int[] index,float[] axis, Name rotation, boolean allowFaceRotation) {
        boolean indexChanged = false;
        if(rotate && !fullyBacked && allowFaceRotation){
            angle += dAngle;
        }
        if (xAngle > 360 || xAngle < -360) {
            xAngle %= 360;
        }
        if (yAngle > 360 || yAngle < -360) {
            yAngle %= 360;
        }

        if( angleDiff < 90 && rotate && !fullyBacked &&
                (rotation == Name.y || rotation == Name.yp || rotation == Name.x ||
                        rotation == Name.xp || rotation == Name.z || rotation == Name.zp)){
            if(rotation == Name.y){
                angleDiff += 2;
                yAngle -= 2;
            } else if(rotation == Name.yp){
                angleDiff += 2;
                yAngle += 2;
            } else if(rotation == Name.x){
                angleDiff += 2;
                xAngle -= 2;
            } else if(rotation == Name.xp){
                angleDiff += 2;
                xAngle += 2;
            } else if(rotation == Name.z){
                angleDiff += 2;
                zAngle -= 2;
            } else if(rotation == Name.zp){
                angleDiff += 2;
                zAngle += 2;
            }
            if(angleDiff == 90){
                if(rotation == Name.y){
                    if(reverseOrderActive){
                        Text.parseStringBack();
                        Data.y();
                        decreaseIndex();
                        indexChanged = true;
                        reverseOrderActive = false;
                    } else{
                        Data.y();
                        Text.parseString();
                        rotationIndex++;
                        indexChanged = true;
                    }
                } else if(rotation == Name.yp){
                    if(reverseOrderActive){
                        Data.yp();
                        Text.parseStringBack();
                        decreaseIndex();
                        indexChanged = true;
                        reverseOrderActive = false;
                    } else{
                        Data.yp();
                        Text.parseString();
                        rotationIndex++;
                        indexChanged = true;
                    }
                } else if(rotation == Name.x){
                    if(reverseOrderActive){
                        Data.xp();
                        Text.parseStringBack();
                        decreaseIndex();
                        indexChanged = true;
                        reverseOrderActive = false;
                    } else{
                        Data.x();
                        Text.parseString();
                        rotationIndex++;
                        indexChanged = true;
                    }
                } else if(rotation == Name.xp){
                    if(reverseOrderActive){
                        Data.x();
                        Text.parseStringBack();
                        decreaseIndex();
                        indexChanged = true;
                        reverseOrderActive = false;
                    } else{
                        Data.xp();
                        Text.parseString();
                        rotationIndex++;
                        indexChanged = true;
                    }
                } else if(rotation == Name.z){
                    if(reverseOrderActive){
                        Data.zp();
                        Text.parseStringBack();
                        decreaseIndex();
                        indexChanged = true;
                        reverseOrderActive = false;
                    } else{
                        Data.z();
                        Text.parseString();
                        rotationIndex++;
                        indexChanged = true;
                    }
                } else if(rotation == Name.zp){
                    if(reverseOrderActive){
                        Data.z();
                        Text.parseStringBack();
                        decreaseIndex();
                        indexChanged = true;
                        reverseOrderActive = false;
                    } else{
                        Data.zp();
                        Text.parseString();
                        rotationIndex++;
                        indexChanged = true;
                    }
                }
                angleDiff = 0;
            }
        }

        System.out.println("Scale:\t" + mScaleFactor);
        Matrix.setLookAtM(mView, 0, 0, 0, 6 / mScaleFactor, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVP, 0, mRenderer.getmProjection(), 0, mView, 0);
        Matrix.rotateM(mMVP, 0, xAngle, 1.0f, 0, 0);
        Matrix.rotateM(mMVP, 0, yAngle, 0, 1.0f, 0);
        Matrix.rotateM(mMVP, 0, zAngle, 0, 0, 1.0f);

        for (int i = 0; i < mCube.length; i++) {
            translations[i] = Arrays.copyOf(mMVP, mMVP.length);
        }

        for (int i = 0; i < mCube.length; i++) {
            if(allowFaceRotation){
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

        if(angle == 90){
            if(!rotateContinuously){
                rotate = false;
            }

            angle = 0;

            if(allowFaceRotation){
                cubeUpdate(index, axis, rotation);
            }

            if(reverseOrderActive){
                Text.parseStringBack();
                reverseOrderActive = false;
            }
            else{
                if(!indexChanged && rotation != Name.NULL){
                    rotationIndex ++;
                }
                Text.parseString();
            }
        }
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            mScaleFactor = Math.max(0.4f, Math.min(mScaleFactor, 1.5f));

            invalidate();
            return true;
        }
    }

    private ScaleGestureDetector mScaleDetector;
    private  float mScaleFactor = 1.f;
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

                        xAngle += dy * TOUCH_SCALE_FACTOR;
                        yAngle += dx * TOUCH_SCALE_FACTOR ;
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
        setSpotsToChange();
        setAxisData();
        runSetupAlgorithm();
        rotate();
    }

    private void createCubes(){
        for(int i =0; i < mCube.length; i++){
            mCube[i] = new Cube(Data.colors[i]);
        }
    }

    private void bindCubes(){
        for(int i = 0; i<27; i++){
            spots[i] = new Spot(Data.translationVectors[Data.clearCube[i]][0],
                    Data.translationVectors[Data.clearCube[i]][1],Data.translationVectors[Data.clearCube[i]][2]);
            spots[i].bindCube(Data.spotToCubeIDs[i]);
        }
    }

    private void rebindCubes(){
        for(int i = 0; i < 27; i++){
            spots[i].bindCube(Data.spotToCubeIDs[i]);
        }
    }

    private void serReverseOrder(){
        reverseOrder = new Name[order.length];
        for(int i = 0; i < order.length; i ++ ){
            switch (order[i]) {
                case R:
                    reverseOrder[i] = Name.RP;
                    break;
                case RP:
                    reverseOrder[i] = Name.R;
                    break;
                case L:
                    reverseOrder[i] = Name.LP;
                    break;
                case LP:
                    reverseOrder[i] = Name.L;
                    break;
                case F:
                    reverseOrder[i] = Name.FP;
                    break;
                case FP:
                    reverseOrder[i] = Name.F;
                    break;
                case B:
                    reverseOrder[i] = Name.BP;
                    break;
                case BP:
                    reverseOrder[i] = Name.B;
                    break;
                case U:
                    reverseOrder[i] = Name.UP;
                    break;
                case UP:
                    reverseOrder[i] = Name.U;
                    break;
                case D:
                    reverseOrder[i] = Name.DP;
                    break;
                case DP:
                    reverseOrder[i] = Name.D;
                    break;
                case M:
                    reverseOrder[i] = Name.MP;
                    break;
                case MP:
                    reverseOrder[i] = Name.M;
                    break;
                case E:
                    reverseOrder[i] = Name.EP;
                    break;
                case EP:
                    reverseOrder[i] = Name.E;
                    break;
                case S:
                    reverseOrder[i] = Name.SP;
                    break;
                case SP:
                    reverseOrder[i] = Name.S;
                    break;
                case y:
                    reverseOrder[i] = Name.yp;
                    break;
                case yp:
                    reverseOrder[i] = Name.y;
                    break;
                case x:
                    reverseOrder[i] = Name.xp;
                    break;
                case xp:
                    reverseOrder[i] = Name.x;
                    break;
                case z:
                    reverseOrder[i] = Name.zp;
                    break;
                case zp:
                    reverseOrder[i] = Name.z;
                    break;
                case NULL:
                    reverseOrder[i] = Name.NULL;
                    break;
            }
        }
    }

    public void clear(){
        for(Cube x : mCube){
            x.clear();
        }
        Data.reset();
        rebindCubes();
        runSetupAlgorithm();
        angle = 0;
        rotationIndex = 0;
        rotate = false;
    }

    public  void setRotateTrue(){
        if(fullyBacked){
            rotationIndex = 0;
        }
        rotate = true;
    }

    public  void setContinuousRotateTrue(){
        temp  = order;
        rotateContinuously = true;
    }

    public  void setContinuousRotateFalse(){
        rotateContinuously = false;
    }

    public void setOrder(){
        temp = order;
        reverseOrderActive = false;
    }

    public void setFullyBackedFalse(){
        fullyBacked = false;
    }

    public void setReverseOrder(){
        angle = 0;
        setContinuousRotateFalse();
        decreaseIndex();
        temp = reverseOrder;
        reverseOrderActive = true;
    }

    public void setSpotsToChange(){
        spotsToChange = Data.spotsToChange;
    }

    public void setAxisData(){
        axisData = Data.axisData;
    }

    public void setSetupAlgorithm(Name[] s){
        setupAlgorithm = s;
    }

    public void decreaseIndex(){
        rotationIndex--;
        if(rotationIndex < 0){
            Text.resetStart();
            Text.resetEnd();
            fullyBacked = true;
            rotationIndex = 0;
        }
    }

    public void cubeUpdate(int[] index, float[]axis, Name rotation){
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

        if(rotation == Name.NULL){}
        else{
            for(int i = 0; i < 9; i++){
                mCube[spots[index[i]].getBoundCube()].pushRotation(new Float[]{90f, axis[0], axis[1], axis[2]});
            }
        }
    }

    public void runSetupAlgorithm(){
        for(int i = 0; i < setupAlgorithm.length; i++){
            switch (setupAlgorithm[i]) {
                case R:
                    cubeUpdate(spotsToChange[0], axisData[0], Name.R);
                    break;
                case RP:
                    cubeUpdate(spotsToChange[1], axisData[1], Name.RP);
                    break;
                case L:
                    cubeUpdate(spotsToChange[2], axisData[2], Name.L);
                    break;
                case LP:
                    cubeUpdate(spotsToChange[3], axisData[3], Name.LP);
                    break;
                case F:
                    cubeUpdate(spotsToChange[4], axisData[4], Name.F);
                    break;
                case FP:
                    cubeUpdate(spotsToChange[5], axisData[5], Name.FP);
                    break;
                case B:
                    cubeUpdate(spotsToChange[6], axisData[6], Name.B);
                    break;
                case BP:
                    cubeUpdate(spotsToChange[7], axisData[7], Name.BP);
                    break;
                case U:
                    cubeUpdate(spotsToChange[8], axisData[8], Name.U);
                    break;
                case UP:
                    cubeUpdate(spotsToChange[9], axisData[9], Name.UP);
                    break;
                case D:
                    cubeUpdate(spotsToChange[10], axisData[10], Name.D);
                    break;
                case DP:
                    cubeUpdate(spotsToChange[11], axisData[11], Name.DP);
                    break;
                case M:
                    cubeUpdate(spotsToChange[12], axisData[12], Name.M);
                    break;
                case MP:
                    cubeUpdate(spotsToChange[13], axisData[13], Name.MP);
                    break;
                case E:
                    cubeUpdate(spotsToChange[14], axisData[14], Name.E);
                    break;
                case EP:
                    cubeUpdate(spotsToChange[15], axisData[15], Name.EP);
                    break;
                case S:
                    cubeUpdate(spotsToChange[16], axisData[16], Name.S);
                    break;
                case SP:
                    cubeUpdate(spotsToChange[17], axisData[17], Name.SP);
                    break;
                case y:
                    Data.y();
                    break;
                case yp:
                    Data.yp();
                    break;
                case x:
                    Data.x();
                    break;
                case xp:
                    Data.xp();
                    break;
                case z:
                    Data.z();
                    break;
                case zp:
                    Data.zp();
                    break;
                case NULL:
                    cubeUpdate(spotsToChange[0], axisData[0], Name.NULL);
                    break;
            }
        }
    }
}
