package patryk.gawron.l2cube2;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private  final float[] mMVP = new float[16];
    private  final float[] mProjection = new float[16];
    private  final float[] mView = new float[16];
    private  final float[] xRotation = new float[16];
    private  final float[] yRotation = new float[16];
    private  final float[] Rotations = new float[16];
    private volatile float xAngle;
    private volatile float yAngle;
    private volatile float zAngle;


    private Square mSquare;

    static float translationVectors[] [] = {
            {0,0,0}, //mid

            {1.05f,0,0},// right mid
            {-1.05f,0,0}, //left mid
            {0,1.05f,0},// top mid
            {0,-1.05f,0},//bottom mid
            {0,0,1.05f},//front mid
            {0,0,-1.05f}, // back mid

            {1.05f, 1.05f, 0}, //right top mid
            {1.05f, -1.05f, 0}, //right bottom mid
            {-1.05f, 1.05f, 0}, //left top mid
            {-1.05f, -1.05f, 0}, //left bottom mid

            {0, 1.05f, 1.05f}, //top front mid
            {0, -1.05f, 1.05f},// bottom front mid
            {0, 1.05f, -1.05f}, // top back mid
            {0, -1.05f, -1.05f}, //bottom back mid

            {1.05f,0,1.05f}, //right front mid
            {1.05f,0,-1.05f},//right back mid
            {-1.05f,0,1.05f}, //left front mid
            {-1.05f,0,-1.05f}, //left back mid

            {1.05f, 1.05f, 1.05f},//right top front
            {1.05f, 1.05f, -1.05f},//right top back
            {1.05f, -1.05f, 1.05f},// right bottom front
            {1.05f, -1.05f, -1.05f},// right bottom back

            {-1.05f, 1.05f, 1.05f},//left top front
            {-1.05f, 1.05f, -1.05f},//left top back
            {-1.05f, -1.05f, 1.05f},//left bottom front
            {-1.05f, -1.05f, -1.05f}//left bottom back
    };

    private  float[][][] colors ={
            {
                    {1.0f, 1.0f, 1.0f, 1.0f}, //front
                    {1.0f, 1.0f, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {1.0f, 1.0f, 1.0f, 1.0f}, //right
                    {1.0f, 1.0f, 1.0f, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },

            //------------------------------

            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {0, 1.0f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {0, 0, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 0,0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 0.647f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },

            //--------------------------------

            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 0, 1.0f},
                    {0, 1.0f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {0, 1.0f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {0, 0, 1.0f, 1.0f},
                    {1.0f, 1.0f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {0, 0, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },

            //--------------------------------

            {
                    {1.0f, 0, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 0, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 1.0f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
                    {1.0f, 0.647f, 0, 1.0f},
                    {1.0f, 1.0f, 1.0f, 1.0f},
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f}, //front
                    {1.0f, 1.0f, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {1.0f, 1.0f, 1.0f, 1.0f}, //right
                    {1.0f, 0.647f, 0, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },

            //---------------------------------------

            {
                    {1.0f, 0, 0, 1.0f}, //front
                    {1.0f, 1.0f, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {0, 1.0f, 0, 1.0f}, //right
                    {1.0f, 1.0f, 1.0f, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f}, //front
                    {1.0f, 1.0f, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {0, 1.0f, 0, 1.0f}, //right
                    {1.0f, 0.647f, 0, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f, 0, 0, 1.0f}, //front
                    {0, 0, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {1.0f, 1.0f, 1.0f, 1.0f}, //right
                    {1.0f, 1.0f, 1.0f, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f}, //front
                    {0, 0, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {1.0f, 1.0f, 1.0f, 1.0f}, //right
                    {1.0f, 0.647f, 0, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },

            //----------------------------------------

            {
                    {1.0f, 0,0, 1.0f}, //front
                    {1.0f, 1.0f, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 0, 1.0f}, //top
                    {0, 1.0f, 0, 1.0f}, //right
                    {1.0f, 1.0f, 1.0f, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f}, //front
                    {1.0f, 1.0f, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 0, 1.0f}, //top
                    {0, 1.0f, 0, 1.0f}, //right
                    {1.0f, 0.647f, 0, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f, 0,0, 1.0f}, //front
                    {1.0f, 1.0f, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {0, 1.0f, 0, 1.0f}, //right
                    {1.0f, 1.0f, 1.0f, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f}, //front
                    {1.0f, 1.0f, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {0, 1.0f, 0, 1.0f}, //right
                    {1.0f, 0.647f, 0, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },

            //--------------------------------------

            {
                    {1.0f,0,0, 1.0f}, //front
                    {0,0, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 0, 1.0f}, //top
                    {1.0f, 1.0f, 1.0f, 1.0f}, //right
                    {1.0f, 1.0f, 1.0f, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f}, //front
                    {0,0, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 0, 1.0f}, //top
                    {1.0f, 1.0f, 1.0f, 1.0f}, //right
                    {1.0f, 0.647f, 0, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f,0,0, 1.0f}, //front
                    {0,0, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {1.0f, 1.0f, 1.0f, 1.0f}, //right
                    {1.0f, 1.0f, 1.0f, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },
            {
                    {1.0f, 1.0f, 1.0f, 1.0f}, //front
                    {0,0, 1.0f, 1.0f}, //left
                    {1.0f, 1.0f, 1.0f, 1.0f}, //top
                    {1.0f, 1.0f, 1.0f, 1.0f}, //right
                    {1.0f, 0.647f, 0, 1.0f}, //back
                    {1.0f, 1.0f, 1.0f, 1.0f}, //far
            },

    };
    private  final int size = translationVectors.length;
    private float[][] translations = new float[size][16];

    static float squareCoords[] = {
            -0.5f,  0.5f,  0.5f,    //top near left     0
            -0.5f, -0.5f,  0.5f,    //bottom near left  1
            0.5f, -0.5f,  0.5f,    //bottom near right 2
            0.5f,  0.5f,  0.5f,    //top near right    3
            -0.5f,  0.5f, -0.5f,    //top far left      4
            -0.5f, -0.5f, -0.5f,    //bottom far left   5
            0.5f, -0.5f, -0.5f,    //bottom far right  6
            0.5f,  0.5f, -0.5f,    //top far right     7
    };

    private short drawOrder[] = {
            0, 1, 2,
            0, 3, 2,    //Front

            0, 4, 5,
            0, 1, 5,    //Left

            0, 4, 7,
            0, 3, 7,    //Top

            6, 7, 3,
            6, 2, 3,    //Right

            6, 7, 4,
            6, 5, 4,    //Back

            6, 5, 1,
            6, 2, 1     //Bottom
    };


    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES30.glClearColor(0.878f, 1.0f, 1.0f, 1.0f);
        mSquare = new Square(squareCoords, drawOrder, 1.0f);
    }

    public void onDrawFrame(GL10 unused) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        float[] scratch = new float[16];
        if(xAngle >360 || xAngle < -360){
            xAngle %=360;
        }
        if(yAngle > 360 || yAngle < -360){
            yAngle %=360;
        }
        Matrix.setRotateM(yRotation, 0, xAngle, 0,1.0f, 0);
        Matrix.setRotateM(xRotation, 0, yAngle, 1.0f,0, 0);
        Matrix.multiplyMM(Rotations, 0, xRotation,0,yRotation,0);
        System.out.println("X:\t" + xAngle);
        System.out.println("Y:\t" + yAngle);
        Matrix.setLookAtM(mView, 0,0,0,6/MyGLSurfaceView.getmScaleFactor(),0f, 0f,0f,0f,1.0f,0.0f);

        Matrix.multiplyMM(mMVP, 0, mProjection, 0, mView, 0);
        Matrix.multiplyMM(scratch , 0, mMVP, 0, Rotations, 0);
//        Matrix.multiplyMM(scratch , 0, scratch, 0, yRotation, 0);

        for(int i =0; i<size; i++){
            translations[i] = Arrays.copyOf(scratch, scratch.length);
        }
        for(int i =0; i <size; i++){
            Matrix.translateM(translations[i], 0, translationVectors[i][0],translationVectors[i][1],translationVectors[i][2]);
            mSquare.draw(translations[i], colors[i]);
        }



    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        Matrix.frustumM(mProjection, 0, -ratio, ratio, -1, 1, 1, 10);
    }

    public float getxAngle() {
        return xAngle;
    }

    public float getyAngle() {
        return yAngle;
    }

    public float getzAngle() {
        return zAngle;
    }

    public void setXAngle(float angle){
        xAngle = angle;
    }

    public void setYAngle(float angle){
        yAngle = angle;
    }

    public void setZAngle(float angle){
        zAngle = angle;
    }
}
