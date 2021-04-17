package com.example.roomwordsample.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.roomwordsample.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends FragmentActivity {

//    private static final int NUM_PAGES = 5;
    private int NUM_PAGES;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private FragmentStateAdapter pagerAdapter;

    private ProgressBar progressBar;

    private BottomNavigationView bottomNavigationView;

    private RelationViewModel mRelationViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int ITEM_EDIT_ACTIVITY_REQUEST_CODE = 2;
    public static final int ALL_ITEM_ACTIVITY_REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Test Add ID
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //BottomNavigationViewの設定
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.items_icon:
                        Intent intent = new Intent(MainActivity.this, AllItemActivity.class);
                        startActivity(intent);
                }
                return false;
            }
        });

        //Viewモデルを作成
        progressBar = findViewById(R.id.progressBar);
        mRelationViewModel = new ViewModelProvider(this).get(RelationViewModel.class);
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabs);
        pagerAdapter = new ScreenSlidePagerAdapter(this);

        //DBへのアクセスはメインスレッドでは行えないため、別スレッドで実行
        new Thread(new Runnable() {
            @Override
            public void run() {
                NUM_PAGES = mRelationViewModel.getNumRows();
                viewPager.setAdapter(pagerAdapter);

                new TabLayoutMediator(tabLayout, viewPager,
                        (tab, position) -> tab.setText("My set" + (position + 1))
                ).attach();
                progressBar.setVisibility(View.GONE);
            }
        }).start();
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new GroupedItemFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    //region NewWord画面からの処理
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//        //requestCodeはstartActivityForResultの2つ目の引数の数値
//        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
//            String tagFlag = data.getStringExtra("RESULT_TYPE");
//            if(tagFlag.equals("TAG")) {
//                Tag tag = new Tag(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
//                mTagViewModel.insert(tag);
//            }else{
//                Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
//                mWordViewModel.insert(word);
//            }
//        }else if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_CANCELED){
//            String tagFlag = data.getStringExtra("RESULT_TYPE");
//            if(tagFlag.equals("TAG")){
//                toastNoItem(R.string.empty_not_saved_tag);
//            }else if (tagFlag.equals("WORD")){
//                toastNoItem(R.string.empty_not_saved_word);
//            }else{
//
//            }
//        }else{
//
//        }
//    }
//endregion

    //region private method
    //NewWord画面からNoItemが帰ってきた際のトースト
    private void toastNoItem(int notice){
        Toast.makeText(
            getApplicationContext(),
            notice,
            Toast.LENGTH_LONG
        ).show();
    }

    //endregion
}