package com.kbeanie.runner;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vidushi on 20/6/17.
 */

public class RunnerActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (!(this instanceof MainActivity) && !(this instanceof OrderDetailsActivity) && !(this instanceof MessageActivity)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showNotification(String message){
        Snackbar sbar = Snackbar.make(getWindow().getDecorView(), message, Snackbar.LENGTH_LONG);
        sbar.getView().setBackgroundResource(R.drawable.snackbar_bg);
        sbar.setActionTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        sbar.setAction("SENT", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sbar.show();
    }
}
