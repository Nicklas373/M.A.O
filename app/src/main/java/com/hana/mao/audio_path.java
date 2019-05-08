package com.hana.mao;
;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.File;
import java.io.FileInputStream;

public class audio_path extends AppCompatActivity {

    private Switch uhqa, hph, impedance, amp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_path_layout);

        uhqa = findViewById(R.id.uhqa);
        hph = findViewById(R.id.hph);
        amp = findViewById(R.id.amp);
        impedance = findViewById(R.id.impedance);

        File uhqa_file = new File("/sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
        File hph_file = new File("/sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
        File amp_file = new File("/sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
        File impedance_file = new File("/sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");

        FileInputStream fstream;
        Clean();
        uhqa_dump();
        hph_dump();
        amp_dump();
        impedance_dump();

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
                    uhqa.setVisibility(View.VISIBLE);
                    uhqa.setChecked(true);
                    uhqa.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(uhqa.isChecked())
                            {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_path.this, "UHQA is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_path.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    uhqa.setVisibility(View.VISIBLE);
                    uhqa.setChecked(false);
                    uhqa.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(uhqa.isChecked())
                            {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_path.this, "UHQA is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult uhqa = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                                    Toast.makeText(audio_path.this, "UHQA is Disabled", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(audio_path.this, "UHQA Not Found", Toast.LENGTH_SHORT).show();
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
                    hph.setVisibility(View.VISIBLE);
                    hph.setChecked(true);
                    hph.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(hph.isChecked())
                            {
                                try {
                                    CommandResult hph = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                                    Toast.makeText(audio_path.this, "Headset High Performance Mode is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult hph = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                                    Toast.makeText(audio_path.this, "Headset High Performance Mode is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    hph.setVisibility(View.VISIBLE);
                    hph.setChecked(false);
                    hph.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(hph.isChecked())
                            {
                                try {
                                    CommandResult hph = Shell.SU.run("echo \"1\" > //sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                                    Toast.makeText(audio_path.this, "Headset High Performance Mode is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult hph = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
                                    Toast.makeText(audio_path.this, "Headset High Performance Mode is Disabled", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(audio_path.this, "Headset High Performance Mode Not Found", Toast.LENGTH_SHORT).show();
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
                    amp.setChecked(true);
                    amp.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(amp.isChecked())
                            {
                                try {
                                    CommandResult amp = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                                    Toast.makeText(audio_path.this, "Low Distortion AMP is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult amp = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                                    Toast.makeText(audio_path.this, "Low Distortion AMP is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    amp.setVisibility(View.VISIBLE);
                    amp.setChecked(false);
                    amp.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(amp.isChecked())
                            {
                                try {
                                    CommandResult amp = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                                    Toast.makeText(audio_path.this, "Low Distortion AMP is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult amp = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
                                    Toast.makeText(audio_path.this, "Low Distortion AMP is Disabled", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(audio_path.this, "Low Distortion AMP Not Found", Toast.LENGTH_SHORT).show();
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
                    impedance.setChecked(true);
                    impedance.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(impedance.isChecked())
                            {
                                try {
                                    CommandResult impedance = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                                    Toast.makeText(audio_path.this, "Headphone Impedance Detection is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult impedance = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                                    Toast.makeText(audio_path.this, "Headphone Impedance Detection is Disabled", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } if (details[0].equals("0")){
                    impedance.setVisibility(View.VISIBLE);
                    impedance.setChecked(false);
                    impedance.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if(impedance.isChecked())
                            {
                                try {
                                    CommandResult impedance = Shell.SU.run("echo \"1\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                                    Toast.makeText(audio_path.this, "Headphone Impedance Detection is Active", Toast.LENGTH_SHORT).show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }  else {
                                try {
                                    CommandResult impedance = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
                                    Toast.makeText(audio_path.this, "Headphone Impedance Detection is Disabled", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(audio_path.this, "Headphone Impedance Detection Not Found", Toast.LENGTH_SHORT).show();
        }
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

    private void Clean() {
        CommandResult IMPEDANCE = Shell.SU.run("rm /data/data/com.hana.mao/files/impedance.txt");
        CommandResult AMP = Shell.SU.run("rm /data/data/com.hana.mao/files/amp.txt");
        CommandResult HPH = Shell.SU.run("rm /data/data/com.hana.mao/files/hph.txt");
        CommandResult UHQA = Shell.SU.run("rm /data/data/com.hana.mao/files/uhqa.txt");
    }
}