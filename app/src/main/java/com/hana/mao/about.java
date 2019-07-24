package com.hana.mao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class about extends Fragment {

    private Button About;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("About");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = (ImageView) view.findViewById(R.id.git);
        imageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://github.com/Nicklas373/M.A.O");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageView git_dl = (ImageView) view.findViewById(R.id.git_dl);
        git_dl.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://github.com/Nicklas373/M.A.O/releases");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        About = (Button) view.findViewById(R.id.app_details);

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), stats_app.class);
                startActivity(intent);
            }
        });
    }
}