package com.example.admlab105.recorridocampus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class InfoLinksFragment extends Fragment {
    private static final String TAG = "InfoLinksFragment";

    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_links_fragment,container,false);
        TextView txtView = view.findViewById(R.id.linksView);
        loadTextView(txtView);
        return view;
    }

    public void loadTextView (TextView txtView) {
        InputStream inputStream = getResources().openRawResource(R.raw.links);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //String source = "<a href='https://www.android-examples.com//'>Android-Examples.com</a>";
        Spanned Text;
        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                if (i == 0xA) {
                    Text = Html.fromHtml(byteArrayOutputStream.toString());
                    txtView.setMovementMethod(LinkMovementMethod.getInstance());
                    txtView.append(Text);
                    txtView.append("\n\n");
                    byteArrayOutputStream.reset();
                }
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}