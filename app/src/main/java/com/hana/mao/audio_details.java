package com.hana.mao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class audio_details extends AppCompatActivity {

    TextView sr,bd,fl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_details_layout);

        Clean();
        SampleRate();
        Actual_SR();
        BitDepth();
        Actual_BD();
        Flags();
        Actual_FL();
    }

    private void Actual_SR() {
        sr = (TextView) findViewById(R.id.sr_status);
        FileInputStream fstream;
        try {
            fstream = openFileInput("asr.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("44100 Hz")){
                sr.setText("44.1 Khz");
            } else if (details[0].equals("48000 Hz")){
                sr.setText("48.0 Khz");
            } else if (details[0].equals("64000 Hz")){
                sr.setText("64.0 Khz");
            } else if (details[0].equals("88200 Hz")){
                sr.setText("88.2 Khz");
            } else if (details[0].equals("96000 Hz")){
                sr.setText("96.0 Khz");
            } else if (details[0].equals("176400 Hz")){
                sr.setText("176.4 Khz");
            } else if (details[0].equals("192000 Hz")){
                sr.setText("192.0 Khz");
            } else {
                sr.setText("Invalid Sample Rate");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            sr.setVisibility(View.GONE);
            Toast.makeText(audio_details.this,"Invalid Sample Rate", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            sr.setVisibility(View.GONE);
        }
    }

    private void Actual_BD() {
        bd = (TextView) findViewById(R.id.bd_status);
        FileInputStream fstream;
        try {
            fstream = openFileInput("abd.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("AUDIO_FORMAT_PCM_16_BIT")){
                bd.setText("16 Bit");
            } else if (details[0].equals("AUDIO_FORMAT_PCM_24_BIT_PACKED")){
                bd.setText("24 Bit (Digital)");
            } else if (details[0].equals("AUDIO_FORMAT_PCM_8_24_BIT")){
                bd.setText("24 Bit (Analog)");
            } else if (details[0].equals("AUDIO_FORMAT_PCM_32_BIT")){
                bd.setText("32 Bit");
            } else {
                bd.setText("Invalid Bit Depth");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bd.setVisibility(View.GONE);
            Toast.makeText(audio_details.this,"Invalid Bit Depth", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            bd.setVisibility(View.GONE);
        }
    }

    private void Actual_FL() {
        fl = (TextView) findViewById(R.id.flags_status);
        FileInputStream fstream;
        try {
            fstream = openFileInput("afl.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("AUDIO_OUTPUT_FLAG_DIRECT")){
                fl.setText("DIRECT Flags");
            } else if (details[0].equals("AUDIO_OUTPUT_FLAG_DEEP_BUFFER")){
                fl.setText("DEEP BUFFER Flags");
            } else {
                fl.setText("Invalid Flags");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fl.setVisibility(View.GONE);
            Toast.makeText(audio_details.this,"Invalid Flags", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            fl.setVisibility(View.GONE);
        }
    }

    private void SampleRate() {
            try {
                CommandResult audio_flinger = Shell.SU.run("dumpsys media.audio_flinger > /data/data/com.hana.mao/files/audio.txt");
                CommandResult Dump_Sample_Rate = Shell.SU.run("grep -i Sample /data/data/com.hana.mao/files/audio.txt > /data/data/com.hana.mao/files/sr.txt");
                CommandResult Dump_Actual_SR = Shell.SU.run("cat /data/data/com.hana.mao/files/sr.txt | tail -n1 | tail -c +16 > /data/data/com.hana.mao/files/asr.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void BitDepth() {
        try {
            CommandResult Dump_Bit_Depth = Shell.SU.run("grep -w format /data/data/com.hana.mao/files/audio.txt > /data/data/com.hana.mao/files/bd.txt");
            CommandResult Dump_Actual_BD = Shell.SU.run("grep -w format /data/data/com.hana.mao/files/bd.txt | tail -n1 | tail -c +27 | sed 's/.$//' > /data/data/com.hana.mao/files/abd.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Flags(){
        try {
            CommandResult Dump_Actual_FL = Shell.SU.run("grep -w AudioStreamOut /data/data/com.hana.mao/files/audio.txt | tail -n1 | tail -c +41 | sed 's/.$//' > /data/data/com.hana.mao/files/afl.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Clean() {
        CommandResult audioflinger = Shell.SU.run("rm /data/data/com.hana.mao/files/audio.txt");
        CommandResult SampleRate = Shell.SU.run("rm /data/data/com.hana.mao/files/sr.txt");
        CommandResult asr = Shell.SU.run("rm /data/data/com.hana.mao/files/asr.txt");
        CommandResult BitDepth = Shell.SU.run("rm /data/data/com.hana.mao/files/bd.txt");
        CommandResult abd = Shell.SU.run("rm /data/data/com.hana.mao/files/abd.txt");
        CommandResult afl = Shell.SU.run("rm /data/data/com.hana.mao/files/afl.txt");
    }
}