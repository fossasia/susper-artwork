package io.neurolab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class ProgramModeActivity extends AppCompatActivity {

    public static final int FOCUS_PROGRAM_MODE = 1;
    public static final int RELAX_PROGRAM_MODE = 2;
    public static final int VJ_PROGRAM_MODE = 3;
    public static final int SERIAL_PROGRAM_MODE = 4;

    public static final String INTENT_KEY_PROGRAM_MODE = "MODE";
    public static final String INTENT_KEY_SETTINGS = "SETTINGS";

    private boolean SETTING_SIMULATION;
    private boolean SETTING_LOAD_RESOURCES_FROM_PHN;
    private boolean SETTING_AUDIO_FEEDBACK;
    private boolean SETTING_24BIT;
    private boolean SETTING_ADVANCED;

    private ImageView rocketimage;
    private int lastPos = 0;
    private int newPos = -300;
    private boolean moving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_mode);

        rocketimage = findViewById(R.id.rocketimage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int mode = bundle.getInt(INTENT_KEY_PROGRAM_MODE);

        boolean[] settings = bundle.getBooleanArray(INTENT_KEY_SETTINGS);

        SETTING_SIMULATION = settings[0];
        SETTING_LOAD_RESOURCES_FROM_PHN = settings[1];
        SETTING_AUDIO_FEEDBACK = settings[2];
        SETTING_24BIT = settings[3];
        SETTING_ADVANCED = settings[4];

        switch (mode) {
            case FOCUS_PROGRAM_MODE:
                setTitle(R.string.focus);
                break;
            case RELAX_PROGRAM_MODE:
                setTitle(R.string.relax);
                break;
            case VJ_PROGRAM_MODE:
                setTitle(R.string.vj);
                break;
            case SERIAL_PROGRAM_MODE:
                setTitle(R.string.serial);
                break;
        }
    }

    public void moveRocket(View view) {
        if (!moving) {
            float PivotX = rocketimage.getPivotX();
            float PivotY = rocketimage.getPivotY();

            Animation launch = new TranslateAnimation(0, 0, lastPos, newPos);
            launch.setDuration(1000);
            launch.setFillAfter(true);
            launch.setRepeatCount(10);
            launch.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    moving = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    moving = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            int c = lastPos;
            lastPos = newPos;
            newPos = c;

            rocketimage.startAnimation(launch);
        }
    }

}
