package com.example.admlab105.recorridocampus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class InfoTextsFragment extends Fragment {
    private static final String TAG = "InfoTextsFragment";

    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_texts_fragment,container,false);
        btnTEST = (Button) view.findViewById(R.id.btnTEST1);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Información general",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}