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

        CleanSR();
        SampleRate();
        Actual_SR();
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

    private void SampleRate() {
            try {
                CommandResult audio_flinger = Shell.SU.run("dumpsys media.audio_flinger > /data/data/com.hana.mao/files/audio.txt");
                CommandResult Dump_Sample_Rate = Shell.SU.run("grep -i Sample /data/data/com.hana.mao/files/audio.txt > /data/data/com.hana.mao/files/sr.txt");
                CommandResult Dump_Actual_SR = Shell.SU.run("cat /data/data/com.hana.mao/files/sr.txt | tail -n1 | tail -c +16 > /data/data/com.hana.mao/files/asr.txt");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(audio_details.this, "Could not find UHQA Path", Toast.LENGTH_SHORT).show();
            }

    }

    private void CleanSR() {
        CommandResult audioflinger = Shell.SU.run("rm /data/data/com.hana.mao/files/audio.txt");
        CommandResult SampleRate = Shell.SU.run("rm /data/data/com.hana.mao/files/sr.txt");
        CommandResult asr = Shell.SU.run("rm /data/data/com.hana.mao/files/asr.txt");
    }

}
