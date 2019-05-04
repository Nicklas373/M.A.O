package com.hana.mao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button dac_conf,dac_status,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Process p = Runtime.getRuntime().exec("su");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Button dac_conf = (Button) findViewById(R.id.dac_conf);
        Button dac_status = (Button) findViewById(R.id.dac_status);
        Button about = (Button) findViewById(R.id.about);

        dac_conf.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, audio_path.class);
                startActivity(i);
            }
        });

        dac_status.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, dac_status.class);
                startActivity(i);
            }
        });

        about.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Not yet finished", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
