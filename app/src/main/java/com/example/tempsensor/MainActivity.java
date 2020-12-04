                          package com.example.tempsensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textview;
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private Boolean isProximitySensorAvailable;
    private Vibrator vibrator;
    private String importantInfo;
    private double intInfo;





    String solution = "a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mValueView;
        TextView Action;

        mValueView = (TextView) findViewById(R.id.valueView);

        FirebaseDatabase database2 = FirebaseDatabase.getInstance("https://bait2123-202010-05.firebaseio.com/");
        DatabaseReference myRef = database2.getReference("PI_001/buzzer");
        //cahnge buzzer to temp for temp sensor

/*        FirebaseDatabase database2 = FirebaseDatabase.getInstance("https://bait2123-202010-03.firebaseio.com");
        DatabaseReference myRef = database2.getReference("PI_03_Control/buzzer");*/


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                importantInfo = value;
                mValueView.setText(value);
                intInfo =Double.parseDouble(importantInfo);


                if(intInfo <= 0){
                    solution = "a";
                }else if(intInfo <= 15 ){
                    solution = "b";
                }else if(intInfo <= 25 ){
                    solution = "b";
                }else if(intInfo <= 35 ){
                    solution = "b";
                }else{
                    solution = "a";
                }


                String lock = "";
                String buzz = "";
                String buzzer = "";
                String lcdscr = "";
                String lcdtxt = "";
                String lcdbkR = "";
                String lcdbkG = "";
                String lcdbkB = "";


                //common resource
                FirebaseDatabase database1 = FirebaseDatabase.getInstance("https://bait2123-202010-03.firebaseio.com");
                DatabaseReference comRef = database1.getReference("PI_03_CONTROL");


                FirebaseDatabase database2 = FirebaseDatabase.getInstance("https://bait2123-202010-05.firebaseio.com/");
                DatabaseReference tempRef = database2.getReference("PI_001");


/*                FirebaseDatabase database2 = FirebaseDatabase.getInstance("https://bait2123-202010-03.firebaseio.com");
                DatabaseReference tempRef = database2.getReference("PI_03_CONTROL");*/


                //personal resource
                if(solution == "a"){
                    lock = "1";
                    buzz = "1";
                    buzzer = "1";
                    lcdscr = "1";
                    lcdtxt = "exit*pronto*****";
                    lcdbkR = "20";
                    lcdbkG = "0";
                    lcdbkB = "0";

                    //myRef.child("lock").setValue("lock");
                    tempRef.child("lock").setValue(lock);
                    tempRef.child("buzz").setValue(buzz);
                    comRef.child("buzzer").setValue(buzzer);
                    comRef.child("lcdscr").setValue(lcdscr);
                    comRef.child("lcdtxt").setValue(lcdtxt);
                    comRef.child("lcdbkR").setValue(lcdbkR);
                    comRef.child("lcdbkG").setValue(lcdbkG);
                    comRef.child("lcdbkB").setValue(lcdbkB);
                }else if(solution == "b"){
                    lock = "0";
                    buzz = "0";
                    buzzer = "0";
                    lcdscr = "1";
                    lcdtxt = "safe*stay*******";
                    lcdbkR = "0";
                    lcdbkG = "0";
                    lcdbkB = "20";

                    tempRef.child("lock").setValue(lock);
                    tempRef.child("buzz").setValue(buzz);
                    comRef.child("buzzer").setValue(buzzer);
                    comRef.child("lcdscr").setValue(lcdscr);
                    comRef.child("lcdtxt").setValue(lcdtxt);
                    comRef.child("lcdbkR").setValue(lcdbkR);
                    comRef.child("lcdbkG").setValue(lcdbkG);
                    comRef.child("lcdbkB").setValue(lcdbkB);
                }

/*                if(intInfo <= 0){
                    lock[0] = "1";
                    buzzer[0] = "1";
                    lcdscr[0] = "1";
                    lcdtxt[0] = "exit*pronto*****";
                    lcdbkR[0] = "20";
                    lcdbkG[0] = "0";
                    lcdbkB[0] = "0";

                    comRef.child("buzzer").setValue(buzzer);
                    comRef.child("lcdscr").setValue(lcdscr);
                    comRef.child("lcdtxt").setValue(lcdtxt);
                    comRef.child("lcdbkR").setValue(lcdbkR);
                    comRef.child("lcdbkG").setValue(lcdbkG);
                    comRef.child("lcdbkB").setValue(lcdbkB);

                }else if(intInfo <= 15){
                    lock[0] = "0";
                    buzzer[0] = "0";
                    lcdscr[0] = "1";
                    lcdtxt[0] = "safe*stay*******";
                    lcdbkR[0] = "0";
                    lcdbkG[0] = "0";
                    lcdbkB[0] = "20";

                    comRef.child("buzzer").setValue(buzzer);
                    comRef.child("lcdscr").setValue(lcdscr);
                    comRef.child("lcdtxt").setValue(lcdtxt);
                    comRef.child("lcdbkR").setValue(lcdbkR);
                    comRef.child("lcdbkG").setValue(lcdbkG);
                    comRef.child("lcdbkB").setValue(lcdbkB);
                }else if(intInfo <=25 ){
                    lock[0] = "0";
                    buzzer[0] = "0";
                    lcdscr[0] = "1";
                    lcdtxt[0] = "safe*stay*******";
                    lcdbkR[0] = "0";
                    lcdbkG[0] = "0";
                    lcdbkB[0] = "20";

                    comRef.child("buzzer").setValue(buzzer);
                    comRef.child("lcdscr").setValue(lcdscr);
                    comRef.child("lcdtxt").setValue(lcdtxt);
                    comRef.child("lcdbkR").setValue(lcdbkR);
                    comRef.child("lcdbkG").setValue(lcdbkG);
                    comRef.child("lcdbkB").setValue(lcdbkB);
                }else if(intInfo <= 35 ){
                    lock[0] = "0";
                    buzzer[0] = "0";
                    lcdscr[0] = "1";
                    lcdtxt[0] = "safe*stay*******";
                    lcdbkR[0] = "0";
                    lcdbkG[0] = "0";
                    lcdbkB[0] = "20";

                    comRef.child("buzzer").setValue(buzzer);
                    comRef.child("lcdscr").setValue(lcdscr);
                    comRef.child("lcdtxt").setValue(lcdtxt);
                    comRef.child("lcdbkR").setValue(lcdbkR);
                    comRef.child("lcdbkG").setValue(lcdbkG);
                    comRef.child("lcdbkB").setValue(lcdbkB);
                }else{
                    lock[0] = "1";
                    buzzer[0] = "1";
                    lcdscr[0] = "1";
                    lcdtxt[0] = "exit*pronto*****";
                    lcdbkR[0] = "20";
                    lcdbkG[0] = "0";
                    lcdbkB[0] = "0";

                    comRef.child("buzzer").setValue(buzzer);
                    comRef.child("lcdscr").setValue(lcdscr);
                    comRef.child("lcdtxt").setValue(lcdtxt);
                    comRef.child("lcdbkR").setValue(lcdbkR);
                    comRef.child("lcdbkG").setValue(lcdbkG);
                    comRef.child("lcdbkB").setValue(lcdbkB);
                }
                */
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }}


            );



    }


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}