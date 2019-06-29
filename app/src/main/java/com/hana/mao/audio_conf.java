package com.hana.mao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.File;
import java.io.FileInputStream;

public class audio_conf extends AppCompatActivity {

    private CardView uhqa, hph, impedance, amp, exp, qcom_gating;
    private Switch s_uhqa, s_hph, s_impedance, s_amp, s_exp, s_qcom_gating;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_audio_conf);

        //Define cardview layout
        uhqa = findViewById(R.id.cv_uhqa);
        hph = findViewById(R.id.cv_hph);
        amp = findViewById(R.id.cv_amp);
        impedance = findViewById(R.id.cv_impedance);
        exp = findViewById(R.id.cv_experimental);
        qcom_gating = findViewById(R.id.cv_qcom_gating);

        //Define switch layout
        s_uhqa = findViewById(R.id.uhqa);
        s_hph = findViewById(R.id.hph);
        s_amp = findViewById(R.id.amp);
        s_impedance = findViewById(R.id.impedance);
        s_exp = findViewById(R.id.exp);
        s_qcom_gating = findViewById(R.id.qcom_gating);

        preferences = getSharedPreferences("exp_pref", Context.MODE_PRIVATE);

        File uhqa_file = new File("/sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
        File hph_file = new File("/sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
        File amp_file = new File("/sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
        File impedance_file = new File("/sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
        File qcom_gating_file = new File("/sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable");
        File exp_file = new File("/data/data/com.hana.mao/shared_prefs/exp_pref.xml");

        FileInputStream fstream;
        Clean();
        exp_check();
        uhqa_dump();
        hph_dump();
        amp_dump();
        impedance_dump();
        qcom_gating_dump();

        if(uhqa_file.exists()){
            uhqa.setVisibility(View.VISIBLE);
            try {
                fstream = openFileInput("uhqa.txt");
                StringBuffer sbuffer = new StringBuffer();
                int i;
                while ((i = fstream.read())!= -1){
                    sbuffer.append((char)i);
                }
                fstream.close();
                String details[] = sbuffer.toString().split("\n");
                if (details[0].equals("1")){
                    s_uhqa.setVisibility(View.VISIBLE);
                    s_uhqa.setChecked(true);
                    s_uhqa.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_uhqa.isChecked())
                            {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_conf.this, "UHQA is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_conf.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    s_uhqa.setVisibility(View.VISIBLE);
                    s_uhqa.setChecked(false);
                    s_uhqa.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_uhqa.isChecked())
                            {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_conf.this, "UHQA is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_conf.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            uhqa.setVisibility(View.GONE);
            Toast.makeText(audio_conf.this, "Ultra High Quality Audio Not Found", Toast.LENGTH_LONG).show();
        }

        if(hph_file.exists()){
            hph.setVisibility(View.VISIBLE);
            try {
                fstream = openFileInput("hph.txt");
                StringBuffer sbuffer = new StringBuffer();
                int i;
                while ((i = fstream.read())!= -1){
                    sbuffer.append((char)i);
                }
                fstream.close();
                String details[] = sbuffer.toString().split("\n");
                if (details[0].equals("1")){
                    s_hph.setVisibility(View.VISIBLE);
                    s_hph.setChecked(true);
                    s_hph.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_hph.isChecked())
                            {
                                try {
                                    CommandResult hph = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                                    Toast.makeText(audio_conf.this, "Headset High Performance Mode is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult hph = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                                    Toast.makeText(audio_conf.this, "Headset High Performance Mode is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    hph.setVisibility(View.VISIBLE);
                    s_hph.setChecked(false);
                    s_hph.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_hph.isChecked())
                            {
                                try {
                                    CommandResult hph = Shell.SU.run("echo \"1\" > //sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                                    Toast.makeText(audio_conf.this, "Headset High Performance Mode is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult hph = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                                    Toast.makeText(audio_conf.this, "Headset High Performance Mode is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            hph.setVisibility(View.GONE);
            Toast.makeText(audio_conf.this, "Headset High Performance Mode Not Found", Toast.LENGTH_LONG).show();
        }

        if(amp_file.exists()){
            amp.setVisibility(View.VISIBLE);
            try {
                fstream = openFileInput("amp.txt");
                StringBuffer sbuffer = new StringBuffer();
                int i;
                while ((i = fstream.read())!= -1){
                    sbuffer.append((char)i);
                }
                fstream.close();
                String details[] = sbuffer.toString().split("\n");
                if (details[0].equals("1")){
                    amp.setVisibility(View.VISIBLE);
                    s_amp.setChecked(true);
                    s_amp.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_amp.isChecked())
                            {
                                try {
                                    CommandResult amp = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                                    Toast.makeText(audio_conf.this, "Low Distortion AMP is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult amp = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                                    Toast.makeText(audio_conf.this, "Low Distortion AMP is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    amp.setVisibility(View.VISIBLE);
                    s_amp.setChecked(false);
                    s_amp.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_amp.isChecked())
                            {
                                try {
                                    CommandResult amp = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                                    Toast.makeText(audio_conf.this, "Low Distortion AMP is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult amp = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                                    Toast.makeText(audio_conf.this, "Low Distortion AMP is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            amp.setVisibility(View.GONE);
            Toast.makeText(audio_conf.this, "Low Distortion AMP Not Found", Toast.LENGTH_LONG).show();
        }

        if(impedance_file.exists()){
            impedance.setVisibility(View.VISIBLE);
            try {
                fstream = openFileInput("impedance.txt");
                StringBuffer sbuffer = new StringBuffer();
                int i;
                while ((i = fstream.read())!= -1){
                    sbuffer.append((char)i);
                }
                fstream.close();
                String details[] = sbuffer.toString().split("\n");
                if (details[0].equals("1")){
                    impedance.setVisibility(View.VISIBLE);
                    s_impedance.setChecked(true);
                    s_impedance.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_impedance.isChecked())
                            {
                                try {
                                    CommandResult impedance = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                                    Toast.makeText(audio_conf.this, "Headphone Impedance Detection is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult impedance = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                                    Toast.makeText(audio_conf.this, "Headphone Impedance Detection is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    impedance.setVisibility(View.VISIBLE);
                    s_impedance.setChecked(false);
                    s_impedance.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_impedance.isChecked())
                            {
                                try {
                                    CommandResult impedance = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                                    Toast.makeText(audio_conf.this, "Headphone Impedance Detection is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult impedance = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                                    Toast.makeText(audio_conf.this, "Headphone Impedance Detection is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            impedance.setVisibility(View.GONE);
            Toast.makeText(audio_conf.this, "Headphone Impedance Detection Not Found", Toast.LENGTH_LONG).show();
        }

        s_exp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            if(s_exp.isChecked())
            {
                try {
                    qcom_gating.setVisibility(View.VISIBLE);
                    String exp_pref = "1";
                    editor = preferences.edit();
                    editor.putString("EXP", exp_pref);
                    editor.apply();
                    Toast.makeText(audio_conf.this, "Experimental features is active", Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }  else {
                try {
                    qcom_gating.setVisibility(View.GONE);
                    String exp_pref = "0";
                    editor = preferences.edit();
                    editor.putString("EXP", exp_pref);
                    editor.apply();
                    Toast.makeText(audio_conf.this, "Experimental features is not active", Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    e.printStackTrace();
                }
        }}
    });

        if(qcom_gating_file.exists()){
            try {
                fstream = openFileInput("qcom_gating.txt");
                StringBuffer sbuffer = new StringBuffer();
                int i;
                while ((i = fstream.read())!= -1){
                    sbuffer.append((char)i);
                }
                fstream.close();
                String details[] = sbuffer.toString().split("\n");
                if (details[0].equals("1")){
                    s_qcom_gating.setChecked(true);
                    s_qcom_gating.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_qcom_gating.isChecked())
                            {
                                try {
                                    CommandResult qcom_gating = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable");
                                    Toast.makeText(audio_conf.this, "QCOM Gating is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult qcom_gating = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable");
                                    Toast.makeText(audio_conf.this, "QCOM Gating is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    s_qcom_gating.setChecked(false);
                    s_qcom_gating.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_qcom_gating.isChecked())
                            {
                                try {
                                    CommandResult qcom_gating = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable");
                                    Toast.makeText(audio_conf.this, "QCOM Gating is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult qcom_gating = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable");
                                    Toast.makeText(audio_conf.this, "QCOM Gating is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            qcom_gating.setVisibility(View.GONE);
            Toast.makeText(audio_conf.this, "QCOM Gating Not Found", Toast.LENGTH_LONG).show();
        }

        if(exp_file.exists()){

        } else {
            qcom_gating.setVisibility(View.GONE);
        }

        Button back = (Button) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(audio_conf.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void uhqa_dump() {
        try {
            CommandResult uhqa_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9335/parameters/huwifi_mode /data/data/com.hana.mao/files/uhqa.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hph_dump() {
        try {
            CommandResult hph_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9330/parameters/high_perf_mode /data/data/com.hana.mao/files/hph.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void amp_dump() {
        try {
            CommandResult amp_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9335/parameters/low_distort_amp /data/data/com.hana.mao/files/amp.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void impedance_dump() {
        try {
            CommandResult impedance_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en /data/data/com.hana.mao/files/impedance.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exp_check() {
        String exp_data = preferences.getString("EXP", "0");
        if (exp_data.equals("1")) {
            s_exp.setChecked(true);
            qcom_gating.setVisibility(View.VISIBLE);
        } else if (exp_data.equals("0")) {
            s_exp.setChecked(false);
            qcom_gating.setVisibility(View.GONE);
        } else {
            Toast.makeText(audio_conf.this, "Preference not found", Toast.LENGTH_LONG).show();
        }
    }
    private void qcom_gating_dump() {
        try {
            CommandResult qcom_gating_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable /data/data/com.hana.mao/files/qcom_gating.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Clean() {
        CommandResult IMPEDANCE = Shell.SU.run("rm /data/data/com.hana.mao/files/impedance.txt");
        CommandResult AMP = Shell.SU.run("rm /data/data/com.hana.mao/files/amp.txt");
        CommandResult HPH = Shell.SU.run("rm /data/data/com.hana.mao/files/hph.txt");
        CommandResult UHQA = Shell.SU.run("rm /data/data/com.hana.mao/files/uhqa.txt");
        CommandResult QCOM_GATING = Shell.SU.run("rm /data/data/com.hana.mao/files/qcom_gating.txt");
    }
}