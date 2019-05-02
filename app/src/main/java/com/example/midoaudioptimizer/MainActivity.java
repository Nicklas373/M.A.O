package com.example.midoaudioptimizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Switch uhqa,hph;

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

        uhqa = (Switch)findViewById(R.id.switch1);
        hph = (Switch)findViewById(R.id.switch2);

        uhqa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(uhqa.isChecked())
                {
                    File file = new File("/sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                    if(file.exists()){
                        try {
                            Toast.makeText(MainActivity.this, "UHQA is Available", Toast.LENGTH_SHORT).show();
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                            Toast.makeText(MainActivity.this, "UHQA is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "UHQA Is Not Available", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    File file = new File("/sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                    if(file.exists()) {
                        try {
                            Toast.makeText(MainActivity.this, "UHQA is Available", Toast.LENGTH_SHORT).show();
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                            Toast.makeText(MainActivity.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "UHQA Is Not Available", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        hph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(hph.isChecked())
                {
                    File file = new File("/sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                    if(file.exists()){
                        try {
                            Toast.makeText(MainActivity.this, "HPH is Available", Toast.LENGTH_SHORT).show();
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                            Toast.makeText(MainActivity.this, "HPH is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "HPH Is Not Available", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    File file = new File("/sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                    if(file.exists()) {
                        try {
                            Toast.makeText(MainActivity.this, "HPH is Available", Toast.LENGTH_SHORT).show();
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                            Toast.makeText(MainActivity.this, "HPH is Disabled", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "HPH Is Not Available", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}
