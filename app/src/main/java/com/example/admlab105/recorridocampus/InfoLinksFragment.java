package com.example.admlab105.recorridocampus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class InfoLinksFragment extends Fragment {
    private static final String TAG = "InfoLinksFragment";

    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_links_fragment,container,false);
        btnTEST = (Button) view.findViewById(R.id.btnTEST3);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Enlaces externos",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}