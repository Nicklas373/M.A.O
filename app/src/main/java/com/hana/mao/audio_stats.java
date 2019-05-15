package com.hana.mao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class audio_stats extends AppCompatActivity {

    TextView sr,bd,fl,dr,o,bc;

    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_audio_stats);

        Clean();
        MediaFlinger();
        HiRes_Detect();

        Button back = (Button) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(audio_stats.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button refresh = (Button) findViewById(R.id.btn_refresh);

        refresh.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Clean();
                MediaFlinger();
                HiRes_Detect();
            }
        });
    }

    private void HiRes_Detect(){
        Audio_Dump();
        FileInputStream fstream;
        try {
            fstream = openFileInput("fshr.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("(DIRECT)")){
                dr = (TextView) findViewById(R.id.textView4);
                dr.setText("Hi-Res Audio Driver");
                HiRes_SR();
                HiRes_BD();
                HiRes_FL();
                HiRes_O();
                Buffer_HiRes();
            } else if (details[0].equals("(RECORD)")){
                dr = (TextView) findViewById(R.id.textView4);
                dr.setText("RECORD Audio State");
                Alsa_SR();
                Alsa_BD();
                Alsa_FL();
                Alsa_O();
                Buffer_Alsa();
            } else if (details[0].equals(" (DIRECT)")){
                dr = (TextView) findViewById(R.id.textView4);
                dr.setText("Hi-Res Audio Driver");
                HiRes_SR();
                HiRes_BD();
                HiRes_FL();
                HiRes_O();
                Buffer_HiRes();
            } else if (details[0].equals("1 (DIRECT)")){
                dr = (TextView) findViewById(R.id.textView4);
                dr.setText("Hi-Res Audio Driver");
                HiRes_SR();
                HiRes_BD();
                HiRes_FL();
                HiRes_O();
                Buffer_HiRes();
            }  else {
                dr = (TextView) findViewById(R.id.textView4);
                dr.setText("ALSA Audio Driver");
                Alsa_SR();
                Alsa_BD();
                Alsa_FL();
                Alsa_O();
                Buffer_Alsa();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            dr = (TextView) findViewById(R.id.textView4);
            sr = (TextView) findViewById(R.id.sr_status);
            bd = (TextView) findViewById(R.id.bd_status);
            fl = (TextView) findViewById(R.id.flags_status);
            dr.setText("Invalid");
            sr.setText("Invalid");
            bd.setText("Invalid");
            fl.setText("Invalid");
            Toast.makeText(audio_stats.this, "File not found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Audio_Dump(){
        try {
            CommandResult Detect = Shell.SU.run("grep -w type /data/data/com.hana.mao/files/audio.txt | sed -n '4p' | tail -c +62 | sed 's/.$//' > /data/data/com.hana.mao/files/fshr.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void MediaFlinger(){
        try {
            CommandResult Dump_Media_Flinger = Shell.SU.run("dumpsys media.audio_flinger > /data/data/com.hana.mao/files/audio.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Alsa_Out_Dump(){
        try {
            CommandResult Alsa_Out_Dump = Shell.SU.run("grep -w Output /data/data/com.hana.mao/files/audio.txt | sed -n '4p' | tail -c +23 | sed 's/.$//' > /data/data/com.hana.mao/files/ao.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void HiRes_Out_Dump(){
        try {
            CommandResult HiRes_Out_Dump = Shell.SU.run("grep -w Output /data/data/com.hana.mao/files/audio.txt | sed -n '8p' | tail -c +23 | sed 's/.$//' > /data/data/com.hana.mao/files/ho.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void HiRes_SR(){
        try {
            CommandResult SR = Shell.SU.run("grep -w Sample /data/data/com.hana.mao/files/audio.txt | sed -n '4p' | tail -c +16 > /data/data/com.hana.mao/files/hsr.txt");
        } catch (Exception e){
            e.printStackTrace();
        }

        sr = (TextView) findViewById(R.id.sr_status);

        FileInputStream fstream;
        try {
            fstream = openFileInput("hsr.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            sr.setText(details[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            sr.setVisibility(View.GONE);
            Toast.makeText(audio_stats.this,"Invalid Sample Rate", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            sr.setVisibility(View.GONE);
        }
    }

    private void HiRes_BD(){
        try {
            CommandResult SR = Shell.SU.run("grep -w format /data/data/com.hana.mao/files/audio.txt | sed -n '7p' | tail -c +20 | sed 's/.$//' > /data/data/com.hana.mao/files/hbd.txt");
        } catch (Exception e){
            e.printStackTrace();
        }

        bd = (TextView) findViewById(R.id.bd_status);

        FileInputStream fstream;
        try {
            fstream = openFileInput("hbd.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("AUDIO_FORMAT_PCM_FLOAT")){
                bd.setText("Floating Point Bit Depth");
            } else if (details[0].equals("AUDIO_FORMAT_PCM_16_BIT")){
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
            Toast.makeText(audio_stats.this,"Invalid Bit Depth", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            bd.setVisibility(View.GONE);
        }
    }

    private void HiRes_FL(){
        try {
            CommandResult SR = Shell.SU.run("grep -w AudioStreamOut /data/data/com.hana.mao/files/audio.txt | sed -n '4p' | tail -c +41 | sed 's/.$//' > /data/data/com.hana.mao/files/hfl.txt");
        } catch (Exception e){
            e.printStackTrace();
        }

        fl = (TextView) findViewById(R.id.flags_status);
        FileInputStream fstream;
        try {
            fstream = openFileInput("hfl.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("AUDIO_OUTPUT_FLAG_DIRECT")){
                fl.setText("DIRECT");
            } else if (details[0].equals("AUDIO_OUTPUT_FLAG_DEEP_BUFFER")){
                fl.setText("DEEP BUFFER");
            } else if (details[0].equals("AUDIO_OUTPUT_FLAG_FAST|AUDIO_OUTPUT_FLAG_PRIMARY")){
                fl.setText("FAST | PRIMARY");
            } else if (details[0].equals("AUDIO_OUTPUT_FLAG_RAW")){
                fl.setText("RAW");
            } else {
                fl.setText("Invalid Flags");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fl.setVisibility(View.GONE);
            Toast.makeText(audio_stats.this,"Invalid Flags", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            fl.setVisibility(View.GONE);
        }
    }

    private void HiRes_O(){
        o = (TextView) findViewById(R.id.out_status);

        HiRes_Out_Dump();

        FileInputStream fstream;
        try {
            fstream = openFileInput("ho.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("AUDIO_DEVICE_OUT_WIRED_HEADSET")){
                o.setText("Wired Headset");
            } else if (details[0].equals("AUDIO_DEVICE_OUT_SPEAKER")){
                o.setText("Speaker");
            } else if (details[0].equals("AUDIO_DEVICE_OUT_LINE")){
                o.setText("Line Out");
            } else if (details[0].equals("AUDIO_DEVICE_OUT_WIRED_HEADPHONE")){
                o.setText("Wired Headphone");
            } else if (details[0].equals("AUDIO_DEVICE_OUT_AUX_DIGITAL")){
                o.setText("AUX");
            } else {
                o.setText("Standby");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void Alsa_SR(){
        try {
            CommandResult SR = Shell.SU.run("grep -w Sample /data/data/com.hana.mao/files/audio.txt | sed -n '2p' | tail -c +16 > /data/data/com.hana.mao/files/asr.txt");
        } catch (Exception e){
            e.printStackTrace();
        }

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
            sr.setText(details[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            sr.setVisibility(View.GONE);
            Toast.makeText(audio_stats.this,"Invalid Sample Rate", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            sr.setVisibility(View.GONE);
        }
    }

    private void Alsa_BD(){
        try {
            CommandResult SR = Shell.SU.run("grep -w format /data/data/com.hana.mao/files/audio.txt | sed -n '3p' | tail -c +20 | sed 's/.$//' > /data/data/com.hana.mao/files/abd.txt");
        } catch (Exception e){
            e.printStackTrace();
        }

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
            if (details[0].equals("AUDIO_FORMAT_PCM_24_BIT_PACKED")){
                bd.setText("24 Bit (Digital)");
            } else if (details[0].equals("AUDIO_FORMAT_PCM_16_BIT")){
                bd.setText("16 Bit");
            } else if (details[0].equals("AUDIO_FORMAT_PCM_FLOAT")){
                bd.setText("Floating Point Bit Depth");
            } else {
                bd.setText("Invalid Bit Depth");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bd.setVisibility(View.GONE);
            Toast.makeText(audio_stats.this,"Invalid Bit Depth", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            bd.setVisibility(View.GONE);
        }
    }

    private void Alsa_FL(){
        try {
            CommandResult SR = Shell.SU.run("grep -w AudioStreamOut /data/data/com.hana.mao/files/audio.txt | sed -n '2p' | tail -c +41 | sed 's/.$//' > /data/data/com.hana.mao/files/afl.txt");
        } catch (Exception e){
            e.printStackTrace();
        }

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
            if (details[0].equals("AUDIO_OUTPUT_FLAG_DEEP_BUFFER")){
                fl.setText("DEEP BUFFER");
            } else if (details[0].equals("AUDIO_OUTPUT_FLAG_FAST|AUDIO_OUTPUT_FLAG_PRIMARY")){
                fl.setText("FAST | PRIMARY");
            } else if (details[0].equals("AUDIO_OUTPUT_FLAG_RAW")){
                fl.setText("RAW");
            } else {
                fl.setText("Invalid Flags");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fl.setVisibility(View.GONE);
            Toast.makeText(audio_stats.this,"Invalid Flags", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            fl.setVisibility(View.GONE);
        }
    }

    private void Alsa_O(){
        o = (TextView) findViewById(R.id.out_status);

        Alsa_Out_Dump();

        FileInputStream fstream;
        try {
            fstream = openFileInput("ao.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("AUDIO_DEVICE_OUT_WIRED_HEADSET")){
                o.setText("Wired Headset");
            } else if (details[0].equals("AUDIO_DEVICE_OUT_SPEAKER")){
                o.setText("Speaker");
            } else if (details[0].equals("AUDIO_DEVICE_OUT_LINE")){
                o.setText("Line Out");
            } else if (details[0].equals("AUDIO_DEVICE_OUT_WIRED_HEADPHONE")){
                o.setText("Wired Headphone");
            } else if (details[0].equals("AUDIO_DEVICE_OUT_AUX_DIGITAL")){
                o.setText("AUX");
            } else {
                o.setText("Standby");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Buffer_HiRes(){
        try {
            CommandResult BHR = Shell.SU.run("grep -w size /data/data/com.hana.mao/files/audio.txt | sed -n '7p' | tail -c +20  > /data/data/com.hana.mao/files/bhr.txt");
        } catch (Exception e){
            e.printStackTrace();
        }

        bc = (TextView) findViewById(R.id.buffer_status);

        FileInputStream fstream;
        try {
            fstream = openFileInput("bhr.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read()) != -1) {
                sbuffer.append((char) i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            bc.setText(details[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bc.setVisibility(View.GONE);
            Toast.makeText(audio_stats.this,"Invalid Buffer Size", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            bc.setVisibility(View.GONE);
        }
    }

    private void Buffer_Alsa(){
        try {
            CommandResult BA = Shell.SU.run("grep -w size /data/data/com.hana.mao/files/audio.txt | sed -n '3p' | tail -c +20  > /data/data/com.hana.mao/files/ba.txt");
        } catch (Exception e){
            e.printStackTrace();
        }

        bc = (TextView) findViewById(R.id.buffer_status);

        FileInputStream fstream;
        try {
            fstream = openFileInput("ba.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read()) != -1) {
                sbuffer.append((char) i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            bc.setText(details[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bc.setVisibility(View.GONE);
            Toast.makeText(audio_stats.this,"Invalid Buffer Size", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            bc.setVisibility(View.GONE);
        }
    }

    private void Clean() {
        CommandResult audioflinger = Shell.SU.run("rm /data/data/com.hana.mao/files/audio.txt");
        CommandResult HiRes_SR = Shell.SU.run("rm /data/data/com.hana.mao/files/hsr.txt");
        CommandResult Audio_Dump = Shell.SU.run("rm /data/data/com.hana.mao/files/hr.txt");
        CommandResult Fail_Safe_Audio_Dump = Shell.SU.run("rm /data/data/com.hana.mao/files/fshr.txt");
        CommandResult HiRes_BD = Shell.SU.run("rm /data/data/com.hana.mao/files/hbd.txt");
        CommandResult HiRes_FL = Shell.SU.run("rm /data/data/com.hana.mao/files/hfl.txt");
        CommandResult HiRes_O = Shell.SU.run("rm /data/data/com.hana.mao/files/ho.txt");
        CommandResult Alsa_SR = Shell.SU.run("rm /data/data/com.hana.mao/files/asr.txt");
        CommandResult Alsa_BD = Shell.SU.run("rm /data/data/com.hana.mao/files/abd.txt");
        CommandResult Alsa_FL = Shell.SU.run("rm /data/data/com.hana.mao/files/afl.txt");
        CommandResult Alsa_O = Shell.SU.run("rm /data/data/com.hana.mao/files/ao.txt");
        CommandResult Buffer_HiRes = Shell.SU.run("rm /data/data/com.hana.mao/files/bhr.txt");
        CommandResult Buffer_Alsa = Shell.SU.run("rm /data/data/com.hana.mao/files/ba.txt");
    }
}