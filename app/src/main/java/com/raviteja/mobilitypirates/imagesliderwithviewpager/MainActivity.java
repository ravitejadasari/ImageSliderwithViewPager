package com.raviteja.mobilitypirates.imagesliderwithviewpager;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.raviteja.mobilitypirates.imagesliderwithviewpager.adapter.ViewPagerAdapter;
import com.raviteja.mobilitypirates.imagesliderwithviewpager.model.ImageModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.oneimg, R.drawable.twoimg, R.drawable.threeimg};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.viewPager);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        init();
    }

    // getting data from the list
    private ArrayList<ImageModel> populateList() {
        ArrayList<ImageModel> list = new ArrayList<>();
        for (int i = 0; i < myImageList.length; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }

    private void init() {

        mPager.setAdapter(new ViewPagerAdapter(MainActivity.this, imageModelArrayList));

        NUM_PAGES = imageModelArrayList.size();
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 2000);
    }
}
