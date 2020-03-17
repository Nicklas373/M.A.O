package com.hana.mao;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class stats_app extends AppCompatActivity {

    public Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_app);
        getSupportActionBar().hide();

        // Lock rotation to potrait by default
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setTitle("Contributor");

        ImageView telegram = (ImageView) findViewById(R.id.telegram);
        telegram.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://t.me/Nicklas373");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageView git = (ImageView) findViewById(R.id.github);
        git.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://github.com/Nicklas373");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        ImageView xda = (ImageView) findViewById(R.id.xda);
        xda.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://forum.xda-developers.com/member.php?u=5608002");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Button back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(stats_app.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
