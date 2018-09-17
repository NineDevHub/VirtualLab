package com.nyongconnect.android.virtuallab.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nyongconnect.android.virtuallab.CommunicatorSimplePendulum;
import com.nyongconnect.android.virtuallab.Pendulum;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.activities.LabWorkActivity;

import java.util.List;

/**
 * Created by enyason on 9/10/18.
 */

public class FragmentSimplePendulumPractical extends Fragment {


    SimplePendulumPracticalView practicalView;
    TextView textView;

    int numberOfOscillation;
    int threadLength;

    Button start, stop, reset, lap,btnAdd;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;

    Handler handler;

    int Seconds, Minutes, MilliSeconds;

    List<Pendulum> pendulumList;

    EditText mOscillation, mLength;
    private LinearLayout layout;

    CommunicatorSimplePendulum communicatorSimplePendulum;



    public FragmentSimplePendulumPractical() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        communicatorSimplePendulum = (CommunicatorSimplePendulum)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_pendulum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        layout = view.findViewById(R.id.layout_pendulum);

        if (!LabWorkActivity.isInit) {

            layout.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(),"Experiment not Initialized...",Toast.LENGTH_LONG).show();
            return;

        }

        practicalView = view.findViewById(R.id.pendulum_view);
        mLength = view.findViewById(R.id.thread_length);
        mOscillation = view.findViewById(R.id.number_of_oscillation);



        textView =view.findViewById(R.id.textView);
        start = view.findViewById(R.id.btn_start);
        reset = view.findViewById(R.id.btn_reset);
        stop = view.findViewById(R.id.btn_stop);

        btnAdd = view.findViewById(R.id.btn_add);
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





                textView.setText("00:00:00");
                try {
                    threadLength = Integer.parseInt(mLength.getText().toString());
                    numberOfOscillation = Integer.parseInt(mOscillation.getText().toString());
                    handler = new Handler() ;
                    StartTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    practicalView.startPractical(numberOfOscillation, threadLength, runnable, handler);
                }catch (Exception ex){
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

//                reset.setEnabled(false);

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                textView.setText("00:00:00");



            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeBuff += MillisecondTime;


//                reset.setEnabled(true);
                practicalView.stopPractical();




            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                double length, time, period,tSquare;

                length = threadLength;
                time = (Minutes * 60) +Seconds;
                period = time/numberOfOscillation;
                tSquare = Math.pow(period,2);

                new Pendulum(length,time,period,tSquare);

                communicatorSimplePendulum.result((new Pendulum(length,time,period,tSquare)));

            }
        });


    }



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


}
