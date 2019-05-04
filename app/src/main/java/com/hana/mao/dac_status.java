package com.hana.mao;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class dac_status extends AppCompatActivity {

    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dac_status_layout);

        textView4 = (TextView) findViewById(R.id.textView4);
        FileInputStream fstream;

        UHQAStatus();
        
        try {
            fstream = openFileInput("uhqa");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            textView4.setText("UHQA Status is: "+ details[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void UHQAStatus() {
        File uhqa_file = new File("/sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
        if(uhqa_file.exists()){
            try {
                CommandResult result = Shell.SU.run("cp /sys/module/snd_soc_wcd9330/parameters/high_perf_mode /data/data/com.hana.mao/files/uhqa");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}