package e.hp.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView text;
    Button b;
    CountDownTimer cdt;

    boolean iscountdownactive=false;
    public void resetTimer(){
        b.setText("Go!");
        iscountdownactive=false;
        seekBar.setEnabled(true);
        seekBar.setProgress(30);
        text.setText("0:30");
        cdt.cancel();
    }
    public  void setTimer(int i)
    {
        int min=(int)(i/60),sec=(int)(i%60);

        String second;
        if(sec<10){
            second=String.valueOf(sec);
            second="0"+sec;
        }
        else
            second=String.valueOf(sec);

        text.setText(min+" : "+second);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button);
       text=(TextView)findViewById(R.id.text);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(30);
        seekBar.setMax(600);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              setTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void go(View view) {

        if(iscountdownactive==false) {

            iscountdownactive=true;
            seekBar.setEnabled(false);
            b.setText("Stop");

            cdt=new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    setTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mpl = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);

                    mpl.start();
                    resetTimer();

                }
            }.start();

        }
        else {

            resetTimer();

        }
    }
}
