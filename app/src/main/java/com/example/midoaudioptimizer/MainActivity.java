package com.example.midoaudioptimizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

public class MainActivity extends AppCompatActivity {

    private Switch sw1;

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

        sw1 = (Switch)findViewById(R.id.switch1);

        sw1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(sw1.isChecked())
                {
                    try {
                        CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                        Toast.makeText(MainActivity.this, "UHQA is Active", Toast.LENGTH_SHORT).show();
                    } catch(Exception e){
                        Toast.makeText(MainActivity.this, "UHQA Is Disabled", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    try {
                        CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                        Toast.makeText(MainActivity.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
                    } catch(Exception e){
                        Toast.makeText(MainActivity.this, "UHQA Is Active", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}
