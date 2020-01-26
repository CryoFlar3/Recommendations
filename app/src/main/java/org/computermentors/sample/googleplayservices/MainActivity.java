package org.computermentors.sample.googleplayservices;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.computermentors.sample.googleplayservices.api.Etsy;
import org.computermentors.sample.googleplayservices.model.ActiveListings;

public class MainActivity extends AppCompatActivity {

    public static final String STATE_ACTIVE_LISTINGS = "StateActiveListings";
    private ListingAdapter adapter;

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

        if (savedInstanceState == null){
            showLoading();
            Etsy.getActiveListings(adapter);
        } else {
            if (savedInstanceState.containsKey(STATE_ACTIVE_LISTINGS)) {
                adapter.success((ActiveListings) savedInstanceState.getParcelable(STATE_ACTIVE_LISTINGS), null);
                showList();
            }else {
                showLoading();
                Etsy.getActiveListings(adapter);
            }
        }


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
