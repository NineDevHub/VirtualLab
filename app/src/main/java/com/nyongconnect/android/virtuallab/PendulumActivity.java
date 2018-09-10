package com.nyongconnect.android.virtuallab;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nyongconnect.android.virtuallab.fragment.PracticalView;

import java.util.List;

public class PendulumActivity extends AppCompatActivity {

    PracticalView practicalView;
    TextView textView ;

    int numberOfOscillation;
    int threadLength;

    Button start, pause, reset, lap ;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    Handler handler;

    int Seconds, Minutes, MilliSeconds ;

    ListView listView ;

   EditText mOscillation, mLength;



    public final Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            textView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendulum);

//
        textView = (TextView) findViewById(R.id.textView);
        start = (Button)findViewById(R.id.button);
//        pause = (Button)findViewById(R.id.button2);
//        reset = (Button)findViewById(R.id.button3);
//        lap = (Button)findViewById(R.id.button4) ;
//        listView = (ListView)findViewById(R.id.listview1);

        handler = new Handler() ;

//        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
//
//        adapter = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_list_item_1,
//                ListElementsArrayList
//        );

//        listView.setAdapter(adapter);



//        pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                TimeBuff += MillisecondTime;
//
//                handler.removeCallbacks(runnable);
//
//                reset.setEnabled(true);
//
//            }
//        });

//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                MillisecondTime = 0L ;
//                StartTime = 0L ;
//                TimeBuff = 0L ;
//                UpdateTime = 0L ;
//                Seconds = 0 ;
//                Minutes = 0 ;
//                MilliSeconds = 0 ;
//
//                textView.setText("00:00:00");
//
//                ListElementsArrayList.clear();
//
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//        lap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ListElementsArrayList.add(textView.getText().toString());
//
//                adapter.notifyDataSetChanged();
//
//            }
//        });


//
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                textView.setText("00:00:00");

//                reset.setEnabled(false);

            }
        });

    }



    public void start(View view) {
        practicalView = findViewById(R.id.p_view);
        textView.setText("00:00:00");
        mLength = findViewById(R.id.thread_length);
        mOscillation = findViewById(R.id.number_of_oscillation);
        try {
            threadLength = Integer.parseInt(mLength.getText().toString());
            numberOfOscillation = Integer.parseInt(mOscillation.getText().toString());
            handler = new Handler() ;
            StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            practicalView = findViewById(R.id.p_view);
            practicalView.startPractical(numberOfOscillation, threadLength, runnable, handler);
        }catch (Exception ex){
            Toast.makeText(this, "Some fields missing", Toast.LENGTH_LONG).show();
        }
    }

    public void stop(View view) {
        TimeBuff += MillisecondTime;


//                reset.setEnabled(true);
        practicalView = findViewById(R.id.p_view);
        practicalView.stopPractical();
    }
}
