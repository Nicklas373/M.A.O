package com.hana.mao;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.File;

public class audio_path extends AppCompatActivity {

    private Switch uhqa,hph,impedance,amp;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_path_layout);

        uhqa = findViewById(R.id.uhqa);
        hph = findViewById(R.id.hph);
        amp = findViewById(R.id.amp);
        impedance = findViewById(R.id.impedance);

        preferences = getSharedPreferences("DAC_Preferences", Context.MODE_PRIVATE);

        File uhqa_file = new File("/sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
        if(uhqa_file.exists()){
            uhqa.setVisibility(View.VISIBLE);
            uhqa_check();
            uhqa.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(uhqa.isChecked())
                    {
                        try {
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                            String uhqa_pref = "1";
                            editor = preferences.edit();
                            editor.putString("UHQA", uhqa_pref);
                            editor.apply();
                            Toast.makeText(audio_path.this, "UHQA is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                            String uhqa_pref = "0";
                            editor = preferences.edit();
                            editor.putString("UHQA", uhqa_pref);
                            editor.apply();
                            Toast.makeText(audio_path.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
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
            hph_check();
            hph.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(hph.isChecked())
                    {
                        try {
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                            String hph_pref = "1";
                            editor = preferences.edit();
                            editor.putString("HPH", hph_pref);
                            editor.apply();
                            Toast.makeText(audio_path.this, "HPH is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                            String hph_pref = "0";
                            editor = preferences.edit();
                            editor.putString("HPH", hph_pref);
                            editor.apply();
                            Toast.makeText(audio_path.this, "HPH is Disabled", Toast.LENGTH_SHORT).show();
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
            amp_check();
            amp.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(amp.isChecked())
                    {
                        try {
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                            String amp_pref = "1";
                            editor = preferences.edit();
                            editor.putString("AMP", amp_pref);
                            editor.apply();
                            Toast.makeText(audio_path.this, "AMP is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                            String amp_pref = "0";
                            editor = preferences.edit();
                            editor.putString("AMP", amp_pref);
                            editor.apply();
                            Toast.makeText(audio_path.this, "AMP is Disabled", Toast.LENGTH_SHORT).show();
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
            impedance_check();
            impedance.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(impedance.isChecked())
                    {
                        try {
                            CommandResult result = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                            String impedance_pref = "1";
                            editor = preferences.edit();
                            editor.putString("IMPEDANCE", impedance_pref);
                            editor.apply();
                            Toast.makeText(audio_path.this, "Impedance is Active", Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                            String impedance_pref = "0";
                            editor = preferences.edit();
                            editor.putString("IMPEDANCE", impedance_pref);
                            editor.apply();
                            Toast.makeText(audio_path.this, "Impedance is Disabled", Toast.LENGTH_SHORT).show();
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

    private void uhqa_check() {
        String uhqa_data = preferences.getString("UHQA", "0");
        if(uhqa_data.equals("1")){
            uhqa.setChecked(true);
        } else if (uhqa_data.equals("0")){
            uhqa.setChecked(false);
        } else {
            Toast.makeText(audio_path.this,"Upss", Toast.LENGTH_SHORT).show();
        }
    }

    private void hph_check() {
        String hph_data = preferences.getString("HPH", "0");
        if(hph_data.equals("1")){
            hph.setChecked(true);
        } else if (hph_data.equals("0")){
            hph.setChecked(false);
        } else {
            Toast.makeText(audio_path.this,"Upss", Toast.LENGTH_SHORT).show();
        }
    }

    private void amp_check() {
        String amp_data = preferences.getString("AMP", "0");
        if(amp_data.equals("1")){
            amp.setChecked(true);
        } else if(amp_data.equals("0")){
            amp.setChecked(false);
        }
        else {
            Toast.makeText(audio_path.this,"Upss", Toast.LENGTH_SHORT).show();
        }
    }

    private void impedance_check() {
        String impedance_data = preferences.getString("IMPEDANCE", "0");
        if(impedance_data.equals("1")){
            impedance.setChecked(true);
        } else if (impedance_data.equals("0")){
            impedance.setChecked(false);
        } else {
            Toast.makeText(audio_path.this,"Upss", Toast.LENGTH_SHORT).show();
        }
    }

}