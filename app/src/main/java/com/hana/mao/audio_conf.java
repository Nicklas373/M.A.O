package com.hana.mao;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.File;
import java.io.FileInputStream;

import android.content.SharedPreferences;
import android.content.Context;

public class audio_conf extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_audio_conf);

        //Define cardview layout
        final CardView uhqa = (CardView) findViewById(R.id.cv_uhqa);
        final CardView hph = (CardView) findViewById(R.id.cv_hph);
        final CardView amp = (CardView) findViewById(R.id.cv_amp);
        final CardView impedance = (CardView) findViewById(R.id.cv_impedance);
        final CardView exp = (CardView) findViewById(R.id.cv_experimental);
        final CardView qcom_gating =  (CardView) findViewById(R.id.cv_qcom_gating);

        //Define switch layout
        final Switch s_uhqa = (Switch) findViewById(R.id.uhqa);
        final Switch s_hph = (Switch) findViewById(R.id.hph);
        final Switch s_amp = (Switch) findViewById(R.id.amp);
        final Switch s_impedance = (Switch) findViewById(R.id.impedance);
        final Switch s_exp = (Switch) findViewById(R.id.exp);
        final Switch s_gating = (Switch) findViewById(R.id.qcom_gating);

        //Define textview component
        TextView t_uhqa = (TextView) findViewById(R.id.t_uhqa);
        TextView t_hph = (TextView) findViewById(R.id.t_hph);
        TextView t_amp = (TextView) findViewById(R.id.t_amp);
        TextView t_impedance = (TextView) findViewById(R.id.t_impedance);
        TextView d_uhqa = (TextView) findViewById(R.id.d_uhqa);
        TextView d_hph = (TextView) findViewById(R.id.d_hph);
        TextView d_amp = (TextView) findViewById(R.id.d_amp);
        TextView d_impedance = (TextView) findViewById(R.id.d_impedance);
        TextView t_v_uhqa = (TextView) findViewById(R.id.t_v_uhqa);
        TextView t_v_hph = (TextView) findViewById(R.id.t_v_hph);
        TextView t_v_amp = (TextView) findViewById(R.id.t_v_amp);
        TextView t_v_impedance = (TextView) findViewById(R.id.t_v_impedance);

        //Define kernel audio path
        File uhqa_file = new File("/sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
        File hph_file = new File("/sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
        File amp_file = new File("/sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
        File impedance_file = new File("/sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
        File gating_file = new File("/sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable");

        preferences = getSharedPreferences("exp_pref", Context.MODE_PRIVATE);

        FileInputStream fstream;
        Clean();
        exp_check();
        uhqa_dump();
        hph_dump();
        amp_dump();
        impedance_dump();
        gating_dump();

        if(uhqa_file.exists()){
            s_uhqa.setVisibility(View.VISIBLE);
            t_v_uhqa.setVisibility(View.VISIBLE);
            t_uhqa.setVisibility(View.INVISIBLE);
            d_uhqa.setVisibility(View.INVISIBLE);
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
                    s_uhqa.setChecked(true);
                    s_uhqa.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_uhqa.isChecked())
                            {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_conf.this, "Ultra High Quality Audio is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_conf.this, "Ultra High Quality Audio is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    s_uhqa.setChecked(false);
                    s_uhqa.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_uhqa.isChecked())
                            {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_conf.this, "Ultra High Quality Audio is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_conf.this, "Ultra High Quality Audio is Disabled", Toast.LENGTH_SHORT).show();
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
            s_uhqa.setVisibility(View.GONE);
            t_v_uhqa.setVisibility(View.INVISIBLE);
            t_uhqa.setVisibility(View.VISIBLE);
            d_uhqa.setVisibility(View.VISIBLE);
            Toast.makeText(audio_conf.this, "Ultra High Quality Audio Not Found", Toast.LENGTH_LONG).show();
        }

        if(hph_file.exists()){
            s_hph.setVisibility(View.VISIBLE);
            t_v_hph.setVisibility(View.VISIBLE);
            t_hph.setVisibility(View.INVISIBLE);
            d_hph.setVisibility(View.INVISIBLE);
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
            s_hph.setVisibility(View.GONE);
            t_v_hph.setVisibility(View.INVISIBLE);
            t_hph.setVisibility(View.VISIBLE);
            d_hph.setVisibility(View.VISIBLE);
            Toast.makeText(audio_conf.this, "Headset High Performance Mode Not Found", Toast.LENGTH_LONG).show();
        }

        if(amp_file.exists()){
            s_amp.setVisibility(View.VISIBLE);
            t_v_amp.setVisibility(View.VISIBLE);
            t_amp.setVisibility(View.INVISIBLE);
            d_amp.setVisibility(View.INVISIBLE);
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
            s_amp.setVisibility(View.GONE);
            t_v_amp.setVisibility(View.INVISIBLE);
            t_amp.setVisibility(View.VISIBLE);
            d_amp.setVisibility(View.VISIBLE);
            Toast.makeText(audio_conf.this, "Low Distortion AMP Not Found", Toast.LENGTH_LONG).show();
        }

        if(impedance_file.exists()){
            s_impedance.setVisibility(View.VISIBLE);
            t_v_impedance.setVisibility(View.VISIBLE);
            t_impedance.setVisibility(View.INVISIBLE);
            d_impedance.setVisibility(View.INVISIBLE);
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
            s_impedance.setVisibility(View.GONE);
            t_v_impedance.setVisibility(View.INVISIBLE);
            t_impedance.setVisibility(View.VISIBLE);
            d_impedance.setVisibility(View.VISIBLE);
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

        if(gating_file.exists()){
            try {
                fstream = openFileInput("gating.txt");
                StringBuffer sbuffer = new StringBuffer();
                int i;
                while ((i = fstream.read())!= -1){
                    sbuffer.append((char)i);
                }
                fstream.close();
                String details[] = sbuffer.toString().split("\n");
                if (details[0].equals("1")){
                    s_gating.setChecked(true);
                    s_gating.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_gating.isChecked())
                            {
                                try {
                                    CommandResult qcom_gating = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable");
                                    Toast.makeText(audio_conf.this, "Power Gating is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult qcom_gating = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable");
                                    Toast.makeText(audio_conf.this, "Power Gating is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    s_gating.setChecked(false);
                    s_gating.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(s_gating.isChecked())
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
            CommandResult uhqa_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9335/parameters/huwifi_mode /data/user/0/com.hana.mao/files/uhqa.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hph_dump() {
        try {
            CommandResult hph_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9330/parameters/high_perf_mode /data/user/0/com.hana.mao/files/hph.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void amp_dump() {
        try {
            CommandResult amp_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9335/parameters/low_distort_amp /data/user/0/com.hana.mao/files/amp.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void impedance_dump() {
        try {
            CommandResult impedance_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en /data/user/0/com.hana.mao/files/impedance.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gating_dump() {
        try {
            CommandResult qcom_gating_check = Shell.SU.run("cp /sys/module/snd_soc_wcd9335/parameters/dig_core_collapse_enable /data/user/0/com.hana.mao/files/qcom_gating.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exp_check() {
        CardView gating =  (CardView) findViewById(R.id.cv_qcom_gating);
        Switch s_exp = (Switch) findViewById(R.id.exp);

        String exp_data = preferences.getString("EXP", "0");
        if (exp_data.equals("1")) {
            s_exp.setChecked(true);
            gating.setVisibility(View.VISIBLE);
        } else if (exp_data.equals("0")) {
            s_exp.setChecked(false);
            gating.setVisibility(View.GONE);
        } else {
            Toast.makeText(audio_conf.this, "Preference not found", Toast.LENGTH_LONG).show();
        }
    }

    private void Clean() {
        CommandResult IMPEDANCE = Shell.SU.run("rm /data/user/0/com.hana.mao/files/impedance.txt");
        CommandResult AMP = Shell.SU.run("rm /data/user/0/com.hana.mao/files/amp.txt");
        CommandResult HPH = Shell.SU.run("rm /data/user/0/com.hana.mao/files/hph.txt");
        CommandResult UHQA = Shell.SU.run("rm /data/user/0/com.hana.mao/files/uhqa.txt");
        CommandResult GATING = Shell.SU.run("rm /data/user/0/com.hana.mao/files/gating.txt");
    }
}