package com.example.admlab105.recorridocampus;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoFragment extends Fragment {

    //private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new InfoTextsFragment(), "Textos");
        adapter.addFragment(new InfoPicturesFragment(), "Fotos");
        adapter.addFragment(new InfoLinksFragment(), "Enlaces");
        viewPager.setAdapter(adapter);
    }
}
