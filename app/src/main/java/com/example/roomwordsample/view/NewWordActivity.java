package com.example.roomwordsample.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomwordsample.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditWordView;
    private EditText mEditTagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);
        mEditTagView = findViewById(R.id.edit_tag);

        //Test App ID
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdView adView = findViewById(R.id.nw_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        final Button saveWordButton = findViewById(R.id.button_saveWord);
        saveWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())){
                    setResult(RESULT_CANCELED,replyIntent);
                }else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,word);
                    replyIntent.putExtra("RESULT_TYPE", "WORD");
                    setResult(RESULT_OK,replyIntent);
                }
                finish();
            }
        });
        final Button saveTagButton = findViewById(R.id.button_saveTag);
        saveTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTagView.getText())){
                    setResult(RESULT_CANCELED,replyIntent);
                }else {
                    String tag = mEditTagView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,tag);
                    replyIntent.putExtra("RESULT_TYPE","TAG");
                    setResult(RESULT_OK,replyIntent);
                }
                finish();
            }
        });
    }
}