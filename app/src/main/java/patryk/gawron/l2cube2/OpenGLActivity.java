package patryk.gawron.l2cube2;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.R.drawable;
import android.widget.TextView;


public class OpenGLActivity extends Activity {

    private final int SCALE = 2;
    private final int Y_OFFSET = 200;
    private CustomGLSurfaceView mGLView;
    TextView guideText;
    TextView algorithm;
    Spannable word;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Text.resetStart();
        Text.resetEnd();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Name names[] = (Name[]) bundle.get("algorithm");
        String temp = bundle.getString("text");
        String guide = bundle.getString("guide");
        mGLView = new CustomGLSurfaceView(this, names);
        mGLView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(mGLView);
        mGLView.setSetupAlgorithm((Name[]) bundle.get("setup"));
        mGLView.runCube();

        guideText = new TextView(this);
        algorithm = new TextView(this);
        Button previousButton = new Button(this);
        Button nextButton = new Button(this);
        Button resetButton = new Button(this);
        Button playButton = new Button(this);
        Button pauseButton = new Button(this);

        FrameLayout.LayoutParams guideTextParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams algorithmParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams nextParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams resetParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams playParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams pauseParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams previousParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Drawable previous = getDrawable(drawable.ic_media_previous);
        Drawable pause = getDrawable(drawable.ic_media_pause);
        Drawable play = getDrawable(android.R.drawable.ic_media_play);
        Drawable next = getDrawable(drawable.ic_media_next);
        Drawable res = getDrawable(drawable.ic_menu_revert);
        previousButton.setBackground(previous);
        pauseButton.setBackground(pause);
        playButton.setBackground(play);
        nextButton.setBackground(next);
        resetButton.setBackground(res);

        if(temp != null){
            word = new SpannableString(temp);
        }
        Text.setWord(word);

        algorithm.setText(word);
        algorithm.setTextSize(24f);
        algorithm.setTextColor(Color.BLACK);

        int width = size.x;
        int height = size.y;
        algorithmParams.topMargin = 50;
        algorithmParams.gravity = Gravity.CENTER_HORIZONTAL;

        guideTextParams.topMargin = 50;
        guideTextParams.leftMargin = 50;
        guideTextParams.rightMargin = 50;
        guideTextParams.gravity = Gravity.CENTER_HORIZONTAL;
        guideText.setGravity(Gravity.CENTER_HORIZONTAL);

        if(guide != null){
            guideText.setText(guide);
        }

        pauseParams.width = pause.getIntrinsicWidth() * SCALE;
        pauseParams.height = pause.getIntrinsicHeight() * SCALE;
        pauseParams.leftMargin = 50;
        pauseParams.topMargin = height - Y_OFFSET;

        playParams.width = play.getIntrinsicWidth() * SCALE;
        playParams.height = play.getIntrinsicHeight() * SCALE;
        playParams.leftMargin = width - playParams.width - 50;
        playParams.topMargin = height - Y_OFFSET;


        resetParams.width = res.getIntrinsicWidth() * SCALE;
        resetParams.height = res.getIntrinsicHeight() * SCALE;
        resetParams.leftMargin = width - (width/2) - (resetParams.width/2);
        resetParams.topMargin = height - Y_OFFSET;

        nextParams.width = next.getIntrinsicWidth() * SCALE;
        nextParams.height = next.getIntrinsicHeight() * SCALE;
        nextParams.leftMargin = width - (width/2) + (resetParams.width/2);
        nextParams.topMargin = height - Y_OFFSET;

        previousParams.width = previous.getIntrinsicWidth() * SCALE;
        previousParams.height = previous.getIntrinsicHeight() * SCALE;
        previousParams.leftMargin = width - (width/2) - previousParams.width - (resetParams.width/2);
        previousParams.topMargin = height - Y_OFFSET;

        addContentView(guideText, guideTextParams);
        addContentView(algorithm, algorithmParams);
        addContentView(previousButton, previousParams);
        addContentView(pauseButton, pauseParams);
        addContentView(playButton, playParams);
        addContentView(nextButton, nextParams);
        addContentView(resetButton, resetParams);

        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                algorithm.setText(word);
                mHandler.postDelayed(this, 50);
            }
        };
        mHandler.postDelayed(runnable, 0);

        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mGLView.setContinuousRotateFalse();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mGLView.setFullyBackedFalse();
                mGLView.setRotateTrue();
                mGLView.setContinuousRotateTrue();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGLView.setFullyBackedFalse();
                mGLView.setContinuousRotateFalse();
                mGLView.setOrder();
                mGLView.setRotateTrue();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGLView.clear();
                word.setSpan(new ForegroundColorSpan(Color.BLACK), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                algorithm.setText(word);
                Text.resetEnd();
                Text.resetStart();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                mGLView.setReverseOrder();
                mGLView.setRotateTrue();
            }
        });



    }

    @Override
    public void onPause(){
        super.onPause();
    }
}