package com.kbeanie.runner;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by vidushi on 24/6/17.
 */

public class LoginActivity extends RunnerActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
