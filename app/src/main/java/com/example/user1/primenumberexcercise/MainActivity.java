package com.example.user1.primenumberexcercise;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText limNum=(EditText)findViewById(R.id.LimitNumber);
        Button startBtn=(Button)findViewById(R.id.StartBtn);
        Button restartBtn=(Button)findViewById(R.id.RestartBtn);
        final TextView primeNums=(TextView)findViewById(R.id.primeNums);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(limNum.getText().toString()!=""){
                    new PrimeFinderTask().execute();}
                else
                    Toast.makeText(getApplicationContext(),"Enter a number first",Toast.LENGTH_SHORT).show();
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primeNums.setText("");
                limNum.setText("");
            }
        });

    }
    public class PrimeFinderTask extends AsyncTask<Void,Integer,Void> {
        EditText limNum=(EditText)findViewById(R.id.LimitNumber);
        Integer x=Integer.parseInt(limNum.getText().toString());

        @Override
        protected Void doInBackground(Void... params) {
            int prime=3;
            boolean f;
            while(prime<x) {
                f=true;
                int checkPrime = 2;

                while(checkPrime<prime){
                    if(prime%checkPrime==0){
                        f=false;
                        break;
                    }
                    checkPrime++;
                }
                if(f){

                    publishProgress(prime);
                    try{Thread.sleep(20);}
                    catch(InterruptedException ex){}
                }
                prime+=2;
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TextView primeNums=(TextView)findViewById(R.id.primeNums);
            primeNums.setText(primeNums.getText() + Arrays.toString(values) + "\n");
        }
    }


}
