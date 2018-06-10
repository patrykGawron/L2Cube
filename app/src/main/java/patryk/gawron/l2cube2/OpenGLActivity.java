package patryk.gawron.l2cube2;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class OpenGLActivity extends Activity {

    private CustomGLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLView = new CustomGLSurfaceView(this);
        mGLView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(mGLView);
        mGLView.runCube();
        Button next = new Button(this);
        Button reset = new Button(this);
        FrameLayout.LayoutParams nextParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams resetParms = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        next.setText(getResources().getString(R.string.next_move));
        reset.setText(getResources().getString(R.string.reset_cube));
        int width = size.x;
        int height = size.y;
        nextParams.leftMargin = width - (width/2);
        nextParams.topMargin = height - 150;
        resetParms.leftMargin = width - (width/2) - 250;
        resetParms.topMargin = height - 150;
        System.out.println("reset width:\t" + reset.getWidth());
        addContentView(next, nextParams);
        addContentView(reset, resetParms);
        System.out.println("reset width:\t" + reset.getWidth());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomGLSurfaceView.setRotateTrue();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGLView.clear();
            }
        });
    }
}