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

public class dac_status extends AppCompatActivity {

    TextView UHQA,HPH,AMP,IMPEDANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dac_status_layout);

        DACClear();
        DACStatus();
        UHQAState();
        HPHState();
        AMPState();
        IMPEDANCEState();
    }

    private void UHQAState(){
        UHQA = (TextView) findViewById(R.id.uhqa_status);
        FileInputStream fstream;
        try {
            fstream = openFileInput("uhqa");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("0")){
                UHQA.setText("Inactive");
            } else if (details[0].equals("1")){
                UHQA.setText("Active");
            } else {
                UHQA.setVisibility(View.GONE);
                Toast.makeText(dac_status.this,"Logic Failed", Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            UHQA.setVisibility(View.GONE);
            Toast.makeText(dac_status.this,"UHQA Not Found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            UHQA.setVisibility(View.GONE);
            Toast.makeText(dac_status.this,"Logic Failed", Toast.LENGTH_LONG).show();
        }
    }

    private void HPHState(){
        HPH = (TextView) findViewById(R.id.hph_status);
        FileInputStream fstream;
        try {
            fstream = openFileInput("hph");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("0")){
                HPH.setText("Inactive");
            } else if (details[0].equals("1")){
                HPH.setText("Active");
            } else {
                HPH.setVisibility(View.GONE);
                Toast.makeText(dac_status.this,"Logic Failed", Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(dac_status.this,"HPH Not Found", Toast.LENGTH_LONG).show();
            HPH.setVisibility(View.GONE);
        } catch (IOException e) {
            e.printStackTrace();
            HPH.setVisibility(View.GONE);
        }
    }

    private void AMPState(){
        AMP = (TextView) findViewById(R.id.amp_status);
        FileInputStream fstream;
        try {
            fstream = openFileInput("amp");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("0")){
                AMP.setText("Inactive");
            } else if (details[0].equals("1")){
                AMP.setText("Active");
            } else {
                AMP.setVisibility(View.GONE);
                Toast.makeText(dac_status.this,"Logic Failed", Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            AMP.setVisibility(View.GONE);
            Toast.makeText(dac_status.this,"AMP Not Found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            AMP.setVisibility(View.GONE);
        }
    }

    private void IMPEDANCEState(){
        IMPEDANCE = (TextView) findViewById(R.id.impedance_status);
        FileInputStream fstream;
        try {
            fstream = openFileInput("impedance");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            if (details[0].equals("0")){
                IMPEDANCE.setText("Inactive");
            } else if (details[0].equals("1")){
                IMPEDANCE.setText("Active");
            } else {
                IMPEDANCE.setVisibility(View.GONE);
                Toast.makeText(dac_status.this,"Logic Failed", Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            IMPEDANCE.setVisibility(View.GONE);
            Toast.makeText(dac_status.this,"Impedance Not Found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            IMPEDANCE.setVisibility(View.GONE);
        }
    }

    private void DACStatus() {
        File uhqa_file = new File("/sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
        File hph_file = new File("/sys/module/snd_soc_wcd9330/parameters/high_perf_mode");
        File amp_file = new File("/sys/module/snd_soc_wcd9335/parameters/low_distort_amp");
        File impedance_file = new File("/sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en");
        if (uhqa_file.exists()) {
            try {
                CommandResult result = Shell.SU.run("cp /sys/module/snd_soc_wcd9335/parameters/huwifi_mode /data/data/com.hana.mao/files/uhqa");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(dac_status.this, "Could not find UHQA Path", Toast.LENGTH_SHORT).show();
            }
        }
        if (hph_file.exists()) {
            try {
                CommandResult result = Shell.SU.run("cp sys/module/snd_soc_wcd9330/parameters/high_perf_mode /data/data/com.hana.mao/files/hph");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(dac_status.this, "Could not find HPH Path", Toast.LENGTH_SHORT).show();
            }
        }
        if (amp_file.exists()) {
            try {
                CommandResult result = Shell.SU.run("cp /sys/module/snd_soc_wcd9335/parameters/low_distort_amp /data/data/com.hana.mao/files/amp");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(dac_status.this, "Could not find AMP Path", Toast.LENGTH_SHORT).show();
            }
        }
        if (impedance_file.exists()) {
                        try {
                            CommandResult result = Shell.SU.run("cp /sys/module/snd_soc_wcd9xxx/parameters/impedance_detect_en /data/data/com.hana.mao/files/impedance");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(dac_status.this, "Could not find IMPEDANCE Path", Toast.LENGTH_SHORT).show();
                        }
        } else {
                Toast.makeText(dac_status.this, "NULL", Toast.LENGTH_LONG).show();
            }
    }

    private void DACClear() {
            CommandResult uhqa = Shell.SU.run("rm /data/data/com.hana.mao/files/uhqa");
            CommandResult hph = Shell.SU.run("rm /data/data/com.hana.mao/files/hph");
            CommandResult amp = Shell.SU.run("rm /data/data/com.hana.mao/files/amp");
            CommandResult impedance = Shell.SU.run("rm /data/data/com.hana.mao/files/impedance");
    }
}