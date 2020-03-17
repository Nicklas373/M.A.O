package com.hana.mao;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.os.Build;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class audio_stats extends AppCompatActivity {

    private TextView sr,bd,fl,dr,o,bc;

    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_audio_stats);

        // Lock rotation to potrait by default
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Declare item component here
        final TextView dr = (TextView) findViewById(R.id.textView4);
        final Button refresh = (Button) findViewById(R.id.btn_refresh);

        // Run init component
        Codename();
        MediaFlinger();
        HiRes_Audio_Dump();
        HiRes_Detect();

        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Clean();
                MediaFlinger();
                HiRes_Detect();
            }
        });
    }

    // Detect Media Flinger
    private void MediaFlinger(){
        try {
            CommandResult Dump_Media_Flinger = Shell.SU.run("dumpsys media.audio_flinger > /data/data/com.hana.mao/files/audio.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check audio states (Mido)
    private void HiRes_Audio_Dump(){
        try {
            CommandResult H_Detect = Shell.SU.run("grep -w type /data/data/com.hana.mao/files/audio.txt | sed -n '5p' | tail -c +66 | sed 's/.$//' > /data/data/com.hana.mao/files/hrad.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio states (Lavender)
    private void HiRes_Audio_Dump_Lavender(){
        try {
            CommandResult H_Detect_L = Shell.SU.run("grep -w type /data/data/com.hana.mao/files/audio.txt | sed -n '4p' | tail -c +64 | sed 's/.$//' > /data/data/com.hana.mao/files/hrad.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio output states (Hi-Res) (Mido)
    private void HiRes_Out_Dump(){
        try {
            CommandResult HiRes_Out_Dump = Shell.SU.run("grep -w Output /data/data/com.hana.mao/files/audio.txt | sed -n '11p' | tail -c +22 > /data/data/com.hana.mao/files/hod.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check audio output states (Hi-Res) (Lavender)
    private void HiRes_Out_Dump_Lavender(){
        try {
            CommandResult HiRes_Out_Dump_L = Shell.SU.run("grep -w Output /data/data/com.hana.mao/files/audio.txt | sed -n '9p' | tail -c +22 > /data/data/com.hana.mao/files/hod.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check audio output states (ALSA) (Mido)
    private void Alsa_Out_Dump(){
        try {
            CommandResult Alsa_Out_Dump = Shell.SU.run("grep -w Output /data/data/com.hana.mao/files/audio.txt | sed -n '6p' | tail -c +22 > /data/data/com.hana.mao/files/aod.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check audio output states (ALSA) (Lavender)
    private void Alsa_Out_Dump_Lavender(){
        try {
            CommandResult Alsa_Out_Dump_L = Shell.SU.run("grep -w Output /data/data/com.hana.mao/files/audio.txt | sed -n '4p' | tail -c +22 > /data/data/com.hana.mao/files/aod.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check audio sample rates (Hi-Res) (Mido)
    private void HiRes_SR_Dump(){
        try {
            CommandResult HiRes_SR_Dump = Shell.SU.run("grep -w Sample /data/data/com.hana.mao/files/audio.txt | sed -n '5p' | tail -c +16 > /data/data/com.hana.mao/files/hsr.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check audio sample rates (Hi-Res) (Lavender)
    private void HiRes_SR_Dump_Lavender(){
        try {
            CommandResult HiRes_SR_Dump_L = Shell.SU.run("grep -w Sample /data/data/com.hana.mao/files/audio.txt | sed -n '4p' | tail -c +16 > /data/data/com.hana.mao/files/hsr.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check audio bit depth (Hi-Res) (Mido)
    private void HiRes_BD_Dump(){
        try {
            CommandResult HiRes_BD_Dump = Shell.SU.run("grep -w format /data/data/com.hana.mao/files/audio.txt | sed -n '10p' | tail -c +26 > /data/data/com.hana.mao/files/hbd.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio bit depth (Hi-Res) (Lavender)
    private void HiRes_BD_Dump_Lavender(){
        try {
            CommandResult HiRes_BD_Dump_L = Shell.SU.run("grep -w format /data/data/com.hana.mao/files/audio.txt | sed -n '7p' | tail -c +19 > /data/data/com.hana.mao/files/hbd.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio buffer size (Hi-Res)
    private void HiRes_BF_Dump(){
        try {
            CommandResult HiRes_BF_Dump = Shell.SU.run("grep -w size /data/data/com.hana.mao/files/audio.txt | sed -n '9p' | tail -c +20  > /data/data/com.hana.mao/files/bhr.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio buffer size (Hi-Res) (Lavender)
    private void HiRes_BF_Dump_Lavender(){
        try {
            CommandResult HiRes_BF_Dump_L = Shell.SU.run("grep -w size /data/data/com.hana.mao/files/audio.txt | sed -n '7p' | tail -c +20  > /data/data/com.hana.mao/files/bhr.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio flags (Hi-Res) (Mido)
    private void HiRes_FL_Dump(){
        try {
            CommandResult HiRes_FL_Dump = Shell.SU.run("grep -w AudioStreamOut /data/data/com.hana.mao/files/audio.txt | sed -n '5p' | tail -c +42 > /data/data/com.hana.mao/files/hfl.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio flags (Hi-Res) (Lavender)
    private void HiRes_FL_Dump_Lavender(){
        try {
            CommandResult HiRes_FL_Dump_L = Shell.SU.run("grep -w AudioStreamOut /data/data/com.hana.mao/files/audio.txt | sed -n '4p' | tail -c +42 > /data/data/com.hana.mao/files/hfl.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio sample rates (ALSA) (Mido)
    private void Alsa_SR_Dump(){
        try {
            CommandResult Alsa_SR_Dump = Shell.SU.run("grep -w Sample /data/data/com.hana.mao/files/audio.txt | sed -n '3p' | tail -c +16 > /data/data/com.hana.mao/files/asr.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio sample rates (ALSA) (Lavender)
    private void Alsa_SR_Dump_Lavender(){
        try {
            CommandResult Alsa_SR_Dump_L = Shell.SU.run("grep -w Sample /data/data/com.hana.mao/files/audio.txt | sed -n '2p' | tail -c +16 > /data/data/com.hana.mao/files/asr.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio bit depth (ALSA) (Mido)
    private void Alsa_BD_Dump(){
        try {
            CommandResult Alsa_BD = Shell.SU.run("grep -w format /data/data/com.hana.mao/files/audio.txt | sed -n '6p' | tail -c +26 > /data/data/com.hana.mao/files/abd.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio bit depth (ALSA) (Lavender)
    private void Alsa_BD_Dump_Lavender(){
        try {
            CommandResult Alsa_BD_L = Shell.SU.run("grep -w format /data/data/com.hana.mao/files/audio.txt | sed -n '3p' | tail -c +19 > /data/data/com.hana.mao/files/abd.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio flags (ALSA) (Mido)
    private void Alsa_FL_Dump(){
        try {
            CommandResult Alsa_FL_Dump = Shell.SU.run("grep -w AudioStreamOut /data/data/com.hana.mao/files/audio.txt | sed -n '3p' | tail -c +42 > /data/data/com.hana.mao/files/afl.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio flags (ALSA) (Lavender)
    private void Alsa_FL_Dump_Lavender(){
        try {
            CommandResult Alsa_FL_Dump_L = Shell.SU.run("grep -w AudioStreamOut /data/data/com.hana.mao/files/audio.txt | sed -n '2p' | tail -c +42 > /data/data/com.hana.mao/files/afl.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio buffer size (ALSA)
    private void Alsa_BF_Dump(){
        try {
            CommandResult Alsa_BF_Dump = Shell.SU.run("grep -w size /data/data/com.hana.mao/files/audio.txt | sed -n '5p' | tail -c +20  > /data/data/com.hana.mao/files/ba.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Check audio buffer size (ALSA)
    private void Alsa_BF_Dump_Lavender(){
        try {
            CommandResult Alsa_BF_Dump_L = Shell.SU.run("grep -w size /data/data/com.hana.mao/files/audio.txt | sed -n '3p' | tail -c +20  > /data/data/com.hana.mao/files/ba.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Declare device codename
    private void Codename() {
        TextView codename = (TextView) findViewById(R.id.codename);

        codename.setText(Build.MODEL);
    }

    /* Delay begin
      Do delay for :
      - HiRes / ALSA Bit Depth
      - HiRes / ALSA Sample Rate
      - HiRes / ALSA Flags
      - HiRes / AlSA Output
    */
    private void HiRes_BD_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HiRes_BD();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void HiRes_FL_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HiRes_FL();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void HiRes_SR_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HiRes_SR();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void HiRes_Out_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HiRes_O();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void HiRes_Buffer_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Buffer_HiRes();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void Alsa_BD_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Alsa_BD();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void Alsa_FL_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Alsa_FL();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void Alsa_SR_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Alsa_SR();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void Alsa_Out_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Alsa_O();
            }
        }, 2000L); //3000 L = 3 detik
    }

    private void Alsa_Buffer_Delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Buffer_Alsa();
            }
        }, 2000L); //3000 L = 3 detik
    }

    // Detect Hi-Res / ALSA State
    private void HiRes_Detect(){
        final TextView codename = (TextView) findViewById(R.id.codename);

        // Check device codename (Try to fix compatibility for lavender
        if (codename.getText().toString().equalsIgnoreCase("mido")) {
            HiRes_Audio_Dump();
        } else if (codename.getText().toString().equalsIgnoreCase("Redmi Note 7")) {
            HiRes_Audio_Dump_Lavender();
        }

        TextView dr = (TextView) findViewById(R.id.textView4);
        FileInputStream fstream;
        try {
            fstream = openFileInput("hrad.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            // Check device codename (Try to fix compatibility for lavender
            if (codename.getText().toString().equalsIgnoreCase("mido")) {
                if (details[0].equals("(DIRECT)")){
                    dr.setText("Hi-Res Audio Driver");
                    // Dump Audio state for specific driver only after drivers detected
                    HiRes_SR_Dump();
                    HiRes_BD_Dump();
                    HiRes_BF_Dump();
                    HiRes_FL_Dump();
                    HiRes_Out_Dump();

                    // Do delay
                    HiRes_BD_Delay();
                    HiRes_SR_Delay();
                    HiRes_Out_Delay();
                    HiRes_FL_Delay();
                    HiRes_Buffer_Delay();
                } else {
                    dr.setText("ALSA Audio Driver");
                    // Dump Audio state for specific driver only after drivers detected
                    Alsa_BD_Dump();
                    Alsa_BF_Dump();
                    Alsa_FL_Dump();
                    Alsa_Out_Dump();
                    Alsa_SR_Dump();

                    // Do delay
                    Alsa_BD_Delay();
                    Alsa_SR_Delay();
                    Alsa_Out_Delay();
                    Alsa_FL_Delay();
                    Alsa_Buffer_Delay();
                }
            } else if (codename.getText().toString().equalsIgnoreCase("Redmi Note 7")) {
                if (details[0].equals(" (DIRECT)")) {
                    dr.setText("Hi-Res Audio Driver");

                    // Dump Audio state for specific driver only after drivers detected
                    HiRes_SR_Dump_Lavender();
                    HiRes_BD_Dump_Lavender();
                    HiRes_BF_Dump_Lavender();
                    HiRes_FL_Dump_Lavender();
                    HiRes_Out_Dump_Lavender();

                    // Do delay
                    HiRes_BD_Delay();
                    HiRes_SR_Delay();
                    HiRes_Out_Delay();
                    HiRes_FL_Delay();
                    HiRes_Buffer_Delay();
                } else {
                    dr.setText("ALSA Audio Driver");
                    // Dump Audio state for specific driver only after drivers detected
                    Alsa_BD_Dump_Lavender();
                    Alsa_BF_Dump_Lavender();
                    Alsa_FL_Dump_Lavender();
                    Alsa_Out_Dump_Lavender();
                    Alsa_SR_Dump_Lavender();

                    // Do delay
                    Alsa_BD_Delay();
                    Alsa_SR_Delay();
                    Alsa_Out_Delay();
                    Alsa_FL_Delay();
                    Alsa_Buffer_Delay();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            dr.setText("Not Detected");
            sr.setText("Not Detected");
            bd.setText("Not Detected");
            fl.setText("Not Detected");
        } catch (IOException e) {
            e.printStackTrace();
            dr.setText("Not Detected");
        }
    }

    private  void HiRes_SR(){
        TextView sr = (TextView) findViewById(R.id.sr_status);

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
            sr.setText("Not Detected");
        } catch (IOException e) {
            e.printStackTrace();
            sr.setText("Not Detected");
        }
    }

    private void HiRes_BD(){
        TextView bd = (TextView) findViewById(R.id.bd_status);

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
            if (details[0].equals("(AUDIO_FORMAT_PCM_FLOAT)")){
                bd.setText("Floating Point Bit Depth");
            } else if (details[0].equals("(AUDIO_FORMAT_PCM_16_BIT)")){
                bd.setText("16 Bit");
            } else if (details[0].equals("(AUDIO_FORMAT_PCM_24_BIT_PACKED)")){
                bd.setText("24 Bit (Digital)");
            } else if (details[0].equals("(AUDIO_FORMAT_PCM_8_24_BIT)")){
                bd.setText("24 Bit (Analog)");
            } else if (details[0].equals("(AUDIO_FORMAT_PCM_32_BIT)")){
                bd.setText("32 Bit");
            } else {
                bd.setText("Not Detected");
            }
        } catch (FileNotFoundException e) {
            bd.setText("Not Detected");
        } catch (IOException e) {
            e.printStackTrace();
            bd.setText("Not Detected");
        }
    }

    private void HiRes_FL(){
        TextView fl = (TextView) findViewById(R.id.flags_status);

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
            if (details[0].equals("(AUDIO_OUTPUT_FLAG_DIRECT)")){
                fl.setText("DIRECT");
            } else if (details[0].equals("AUDIO_OUTPUT_FLAG_DEEP_BUFFER")){
                fl.setText("DEEP BUFFER");
            } else {
                fl.setText("Not Detected");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fl.setText("Not Detected");
        } catch (IOException e) {
            fl.setText("Not Detected");
        }
    }

    private void HiRes_O(){
        final TextView o = (TextView) findViewById(R.id.out_status);

        FileInputStream fstream;
        try {
            fstream = openFileInput("hod.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("(AUDIO_DEVICE_OUT_WIRED_HEADSET)")){
                o.setText("Wired Headset");
            } else if (details[0].equals("(AUDIO_DEVICE_OUT_SPEAKER)")){
                o.setText("Speaker");
            } else {
                o.setText("Not Detected");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            o.setText("Not Detected");
        } catch (IOException e) {
            o.setText("Not Detected");
        }
    }

    private void Buffer_HiRes(){
        FileInputStream fstream;

        TextView bc = (TextView) findViewById(R.id.buffer_status);

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
            bc.setText("Not Detected");
        } catch (IOException e) {
            bc.setText("Not Detected");
        }
    }

    private void Alsa_SR(){
        FileInputStream fstream;

        TextView sr = (TextView) findViewById(R.id.sr_status);

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
            sr.setText("Not Detected");
        } catch (IOException e) {
            sr.setText("Not Detected");
        }
    }

    private void Alsa_BD(){
        FileInputStream fstream;

        TextView bd = (TextView) findViewById(R.id.bd_status);

        try {
            fstream = openFileInput("abd.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("(AUDIO_FORMAT_PCM_24_BIT_PACKED)")){
                bd.setText("24 Bit (Digital)");
            } else if (details[0].equals("(AUDIO_FORMAT_PCM_16_BIT)")){
                bd.setText("16 Bit");
            } else if (details[0].equals("(AUDIO_FORMAT_PCM_FLOAT)")){
                bd.setText("Floating Point Bit Depth");
            } else {
                bd.setText("Not Detected");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bd.setText("Not Detected");
        } catch (IOException e) {
            bd.setText("Not Detected");
        }
    }

    private void Alsa_FL(){
        FileInputStream fstream;

        TextView fl = (TextView) findViewById(R.id.flags_status);

        try {
            fstream = openFileInput("afl.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("(AUDIO_OUTPUT_FLAG_DEEP_BUFFER)")){
                fl.setText("DEEP BUFFER");
            } else if (details[0].equals("(AUDIO_OUTPUT_FLAG_PRIMARY|AUDIO_OUTPUT_FLAG_FAST)")){
                fl.setText("PRIMARY | FAST");
            } else {
                fl.setText("Not Detected");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fl.setText("Not Detected");
        } catch (IOException e) {
            fl.setText("Not Detected");
        }
    }

    private void Alsa_O(){
        FileInputStream fstream;

        TextView o = (TextView) findViewById(R.id.out_status);

        try {
            fstream = openFileInput("aod.txt");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("(AUDIO_DEVICE_OUT_WIRED_HEADSET)")){
                o.setText("Wired Headset");
            } else if (details[0].equals("(AUDIO_DEVICE_OUT_SPEAKER)")){
                o.setText("Speaker");
            } else {
                o.setText("Not Detected");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            o.setText("Not Detected");
        } catch (Exception e) {
            o.setText("Not Detected");
        }
    }

    private void Buffer_Alsa(){
        FileInputStream fstream;

        TextView bc = (TextView) findViewById(R.id.buffer_status);

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
            bc.setText("Not Detected");
        } catch (IOException e) {
            bc.setText("Not Detected");
        }
    }

    private void Clean() {
        CommandResult clean = Shell.SU.run("rm -rvf /data/data/com.hana.mao/files/*.txt");
    }
}