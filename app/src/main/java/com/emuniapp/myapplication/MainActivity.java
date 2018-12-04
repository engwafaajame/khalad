package com.emuniapp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText id=findViewById(R.id.id);
        final EditText name=findViewById(R.id.name);
        Button save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "please enter your student name", Toast.LENGTH_SHORT).show();
                } else if (name.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "please enterthe student id ", Toast.LENGTH_SHORT).show();
                }


                new VolleyRequests<>().setIReceiveData(new VolleyRequests.IReceiveData() {
                    @Override
                    public void onDataReceived(Object posts) {

                      // if ((((State) posts).getState())) {
                            Toast.makeText(MainActivity.this, "" + ((State) posts).getDbId()
                                    , Toast.LENGTH_LONG).show();

                       // }

                    }
                }).addstudent(id.getText().toString(),name.getText().toString());
               // Toast.makeText(MainActivity.this, ""+((State) posts).getState(), Toast.LENGTH_SHORT).show();
                id.setText("");
                name.setText("");



            }
        });
    }
}
