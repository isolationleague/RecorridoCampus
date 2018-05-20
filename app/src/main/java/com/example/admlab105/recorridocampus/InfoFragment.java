package com.example.admlab105.recorridocampus;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    //private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private String etiqueta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etiqueta = this.getArguments().getString("etiq");
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        //mSectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.info_container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.info_tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        TextView tv = (TextView)view.findViewById(R.id.etqSitio);
        tv.setText(etiqueta);
        return view;
    }

    public void onDetach(){
        super.onDetach();
        getActivity().getSupportFragmentManager().beginTransaction().remove(getParentFragment()).commit();
    }
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new InfoTextsFragment(), "Textos");
        adapter.addFragment(new InfoPicturesFragment(), "Fotos");
        adapter.addFragment(new InfoLinksFragment(), "Enlaces");
        viewPager.setAdapter(adapter);
    }
}
