package com.example.benjamin.findvelib;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benjamin.findvelib.dbo.Station;
import com.example.benjamin.findvelib.dbo.Velib;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toast toast = null;

    public Velib velib = new Velib(new ArrayList<Station>());
    private final VelibAdapter recyclerAdapter = new VelibAdapter(velib, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleList();
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        RequestManager.getInstance().getData(velib, recyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query){
        recyclerAdapter.filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerAdapter.filter(newText);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.about:
                if (toast != null)
                    toast.cancel();
                toast = Toast.makeText(MainActivity.this, getResources().getString(R.string.contributors), Toast.LENGTH_SHORT);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if( v != null)
                    v.setGravity(Gravity.CENTER);
                toast.show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.velib_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
