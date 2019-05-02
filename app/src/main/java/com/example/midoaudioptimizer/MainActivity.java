package com.example.midoaudioptimizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Process p = Runtime.getRuntime().exec("su");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        final Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                CommandResult result = Shell.SU.run("echo \"0\" > /sys/module/snd_soc_wcd9335/parameters/huwifi_mode");
                if (result.isSuccessful()) {
                    System.out.println(result.getStdout());
                    // Example output on a rooted device:
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
