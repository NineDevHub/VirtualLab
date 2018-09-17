package com.nyongconnect.android.virtuallab.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.nyongconnect.android.virtuallab.Physics;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.adapters.ExperimentListingAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AvailableExperimentActivity extends AppCompatActivity implements ExperimentListingAdapter.ExperimentListingClickListener {
    RecyclerView recyclerView;
    ExperimentListingAdapter experimentListingAdapter;


    List<Physics> physicsList;
    ProgressBar progressBar;

    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_experiment);

        firebaseFirestore = FirebaseFirestore.getInstance();


        progressBar = findViewById(R.id.pb_home_exp);

        physicsList = new ArrayList<>();


        recyclerView = findViewById(R.id.rv_experiment_listing);
        experimentListingAdapter = new ExperimentListingAdapter(this, physicsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(experimentListingAdapter);


//        firebaseFirestore.collection("physics")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//
//
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                        progressBar.setVisibility(View.INVISIBLE);
//                        if (task.isSuccessful()) {
//
//                            for (DocumentSnapshot snapshot : task.getResult()) {
//
//                                String code = snapshot.getString("code");
//                                String title = snapshot.getString("title");
//                                Double duration = snapshot.getDouble("duration");
//                                boolean isInitialized = snapshot.getBoolean("izInitialized");
//
//
//                                physicsList.add(new Physics(code, title, duration, isInitialized));
//
//                                Log.i("exp ",isInitialized+"");
//
//
//
//
//                            }
//
////                            Collections.reverse(physicsList);
//                            experimentListingAdapter.notifyDataSetChanged();
//                        }
//                    }
//                });
    }

    @Override
    public void onExperimentListingClickListener(int clickedIndex) {

        Log.i("pos ",clickedIndex+"");

        Log.i("pos ",experimentListingAdapter.getItemCount()+"");


        Physics physics = physicsList.get(clickedIndex);

        Intent intent = new Intent(AvailableExperimentActivity.this, LabWorkActivity.class);
        intent.putExtra("physics", physics);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    protected void onStart() {
        super.onStart();

        firebaseFirestore.collection("physics")
                .addSnapshotListener(this, new EventListener<QuerySnapshot>() {


                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {


                        progressBar.setVisibility(View.INVISIBLE);


                        physicsList.clear();


                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {


                            String code = snapshot.getString("code");
                            String title = snapshot.getString("title");
                            Double duration = snapshot.getDouble("duration");
                            boolean isInitialized = snapshot.getBoolean("izInitialized");


                            physicsList.add(new Physics(code, title, duration, isInitialized));

                            Log.i("exp ", isInitialized + "");


                        }

//                            Collections.reverse(physicsList);
                        experimentListingAdapter.notifyDataSetChanged();


                    }
                });


    }
}
