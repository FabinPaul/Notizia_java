package com.fabinpaul.notizia;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    private Fragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomeFragment = getSupportFragmentManager().findFragmentById(R.id.contentMain);
        if (mHomeFragment == null) {

        }
    }
}
