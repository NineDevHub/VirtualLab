package com.nyongconnect.android.virtuallab.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nyongconnect.android.virtuallab.CommunicatorOhmsLaw;
import com.nyongconnect.android.virtuallab.OhmsLawResult;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.activities.LabWorkActivity;
import com.nyongconnect.android.virtuallab.canvas.CanvasOhmsLaw;

import java.util.ArrayList;

public class FragmentOhmsLawLabSimulation extends Fragment {

    CanvasOhmsLaw canvasOhmsLaw;

    SeekBar seekBarVoltage, seekBarResistance;

    TextView tvResistance, tvVoltage,tvCurrent,tvResult;

    float valCurrent = 0, valVoltage = 0, valResistance = 1000;

    private float xPointGraph=100, yPointGraph=1100;


    ImageView imageView;



    ArrayList<Float> listVoltage = new ArrayList<Float>();
    ArrayList<Float> listCurrent = new ArrayList<Float>();

    RelativeLayout layout;

    CommunicatorOhmsLaw communicatorOhmsLaw;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        communicatorOhmsLaw = (CommunicatorOhmsLaw)context;
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ohms_law_practical,container,false);

//        View view1 = inflater.inflate(R.layout.not_init,container,false);
//
//        if () {
//        }
        return view;
    }



    public void setUpAlertDialog(){


        View editTextView = LayoutInflater.from(getContext()).inflate(R.layout.alert_dialog_view,null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Change Voltage Value");

        builder.setView(editTextView);

        final EditText textVoltage = editTextView.findViewById(R.id.editText_voltage);
        textVoltage.setText("2");



        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                valVoltage = Float.parseFloat(textVoltage.getText().toString());
                float current = valVoltage/valResistance;

                if (valVoltage > 10) {
                    Toast.makeText(getContext(), "Voltage Entry Exceeded!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }




                if (listCurrent.isEmpty() && listVoltage.isEmpty()) {

                    listVoltage.add(valVoltage);
                    listCurrent.add(current);


                    communicatorOhmsLaw.result(new OhmsLawResult(valVoltage+"",""+current));


                } else {

                    if (listVoltage.get(listVoltage.size() - 1) > valVoltage || (listVoltage.get(listVoltage.size()-1) == valVoltage)) {
                        Toast.makeText(getContext(), "Voltage Entry failed!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        return;
                    } else {

                        listVoltage.add(valVoltage);
                        listCurrent.add(current);
                        communicatorOhmsLaw.result(new OhmsLawResult(valVoltage+"",""+current));

                    }



                }


                tvVoltage.setText("V = " + valVoltage + "v");
                tvCurrent.setText("I = "+current+"A");

                yPointGraph = 1100 - (50 * valVoltage);
                xPointGraph = 100 + (50000*current);

                int drawable;
                if (valVoltage > 0) {
                    drawable = R.drawable.ic_ammeter_current;

                } else {
                    drawable = R.drawable.ic_ammeter;

                }


                canvasOhmsLaw.invalidateCanvas(getActivity(), drawable,xPointGraph,yPointGraph);

                Toast.makeText(getContext(),"Added to table",Toast.LENGTH_SHORT).show();

                dialog.dismiss();


            }
        }).show();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        myCanvas = new MyCanvas(this);

//        setContentView(myCanvas);

        layout = view.findViewById(R.id.layout_ohms_law);

        if (!LabWorkActivity.isInit) {

            layout.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(),"Experiment not Initialized...",Toast.LENGTH_LONG).show();
            return;

        }




        seekBarVoltage = view.findViewById(R.id.seek_bar_voltage);
        seekBarResistance = view.findViewById(R.id.seek_bar_resistance);
        canvasOhmsLaw = view.findViewById(R.id.canvas_ohms_law);

        tvResistance = view.findViewById(R.id.tv_resistance);
        tvVoltage = view.findViewById(R.id.tv_voltage);
        tvCurrent = view.findViewById(R.id.tv_current);

//        tvResult = view.findViewById(R.id.tv_ohms_result);

        imageView = view.findViewById(R.id.image_view_voltmeter);

        imageView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                setUpAlertDialog();
            }
        });



        canvasOhmsLaw.invalidateCanvas(getActivity(),R.drawable.ic_ammeter,xPointGraph,yPointGraph);

        seekBarVoltage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valVoltage = progress;


                float current = valVoltage/valResistance;

                tvVoltage.setText("V = " + progress + "v");
                tvCurrent.setText("I = "+current+"A");

                String result = tvResult.getText().toString();
                tvResult.setText(result+"\n"+valVoltage+"\t\t\t\t"+current);



                yPointGraph = 1100 - (50 * progress);
                xPointGraph = 100 + (50000*current);

                int drawable;
                if (progress > 0) {
                    drawable = R.drawable.ic_ammeter_current;

                } else {
                    drawable = R.drawable.ic_ammeter;

                }


                canvasOhmsLaw.invalidateCanvas(getActivity(), drawable,xPointGraph,yPointGraph);

            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        seekBarResistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//                valResistance = progress;
//                float current = valVoltage / valResistance;
//
//                tvResistance.setText("Resistance = " + progress + getString(R.string.omega));
//                tvCurrent.setText("I = "+current+"A");
//
//
//                yPointGraph = 1100 - (50 * valVoltage);
//                xPointGraph = 100 + (50 * current);
//
////                myCanvas.invalidateCanvas(FragmentOhmsLawLabSimulation.this, 0,xPointGraph,yPointGraph);
//
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


    }


    public static Bitmap getBitmapFromDrawable(Context context, @DrawableRes int drawableId) {
        Drawable drawable = AppCompatResources.getDrawable(context, drawableId);

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawableCompat || drawable instanceof VectorDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            return bitmap;
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }
}
