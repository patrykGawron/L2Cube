package patryk.gawron.l2cube2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
    private final float TOUCH_SCALE_FACTOR = 80.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

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

                        mRenderer.setXAngle(mRenderer.getxAngle()+ (dx*TOUCH_SCALE_FACTOR));
                        mRenderer.setYAngle(mRenderer.getyAngle()+(dy*TOUCH_SCALE_FACTOR));
                        requestRender();
                }
            case 2:
                mScaleDetector.onTouchEvent(e);
        }

        mPreviousX = x;
        mPreviousY = y;
        return  true;
    }

    public MyGLSurfaceView(Context context){
        super(context);


        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        mRenderer = new MyGLRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }

    public static float getmScaleFactor(){
        return  mScaleFactor;
    }
}
