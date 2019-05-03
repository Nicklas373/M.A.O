package com.hana.mao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Switch uhqa,hph,impedance,amp;

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

        uhqa = findViewById(R.id.uhqa);
        hph = findViewById(R.id.hph);
        amp = findViewById(R.id.amp);
        impedance = findViewById(R.id.impedance);

        File uhqa_file = new File("/sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
        if(uhqa_file.exists()){
            uhqa.setVisibility(View.VISIBLE);
            uhqa.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(uhqa.isChecked())
                    {
                            try {
                                CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                Toast.makeText(MainActivity.this, "UHQA is Active", Toast.LENGTH_SHORT).show();
                            } catch(Exception e){
                                e.printStackTrace();
                            }
                    }
                    else {

                            try {
                                CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                Toast.makeText(MainActivity.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                    }
                }
            });
        } else {
            uhqa.setVisibility(View.GONE);
        }

        File hph_file = new File("/sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
        if(hph_file.exists()){
            hph.setVisibility(View.VISIBLE);
            hph.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(hph.isChecked())
                    {
                        try {
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                            Toast.makeText(MainActivity.this, "HPH is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                            Toast.makeText(MainActivity.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            hph.setVisibility(View.GONE);
        }

        File amp_file = new File("/sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
        if(amp_file.exists()){
            amp.setVisibility(View.VISIBLE);
            amp.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(amp.isChecked())
                    {
                        try {
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                            Toast.makeText(MainActivity.this, "AMP is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                            Toast.makeText(MainActivity.this, "AMP is Disabled", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            amp.setVisibility(View.GONE);
        }

        File impedance_file = new File("/sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
        if(impedance_file.exists()){
            impedance.setVisibility(View.VISIBLE);
            impedance.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(impedance.isChecked())
                    {
                        try {
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                            Toast.makeText(MainActivity.this, "Impedance is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                            Toast.makeText(MainActivity.this, "Impedance is Disabled", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            impedance.setVisibility(View.GONE);
        }
    }
}
