package org.computermentors.sample.googleplayservices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.computermentors.sample.googleplayservices.google.GoogleServicesHelper;
import org.computermentors.sample.googleplayservices.model.ActiveListings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    public static final String STATE_ACTIVE_LISTINGS = "StateActiveListings";
    private ListingAdapter adapter;
    private GoogleServicesHelper googleServicesHelper;

    private RecyclerView recyclerView;
    private View progressBar;
    private TextView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.recyclerview);
        progressBar =  findViewById(R.id.progressbar);
        errorView =  findViewById(R.id.errorview);

        // setup recyclerView
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        adapter = new ListingAdapter(this);

        recyclerView.setAdapter(adapter);
        googleServicesHelper = new GoogleServicesHelper(this, adapter);

        showLoading();

        if (savedInstanceState == null){
            if (savedInstanceState.containsKey(STATE_ACTIVE_LISTINGS)) {
                adapter.success((ActiveListings) savedInstanceState.getParcelable(STATE_ACTIVE_LISTINGS), null);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleServicesHelper.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleServicesHelper.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        googleServicesHelper.handleActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ActiveListings activeListings = adapter.getActiveListings();
        if(activeListings != null){
            outState.putParcelable(STATE_ACTIVE_LISTINGS, activeListings);
        }
    }

    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    public void showList(){
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void showError(){
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
