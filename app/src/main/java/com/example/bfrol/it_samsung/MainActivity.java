package com.example.bfrol.it_samsung;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Disposable request;
    ListView act1_listView;
    RecyclerView act1_recView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        act1_recView = findViewById(R.id.act1_recView);
        Observable<Feed> obs = Observable.create(it -> {
            it.onNext(Utils.getRequest("https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.bbci.co.uk%2Fnews%2Frss.xml"));
        }).map(it -> new Gson().fromJson(it.toString(), Feed.class)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        request = obs.subscribe(it -> {showRecView(it.getItems());}, e -> { });
    }

    @Override
    protected void onDestroy() {
        request.dispose();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String d = data.getStringExtra("return_text_tag");
           // view.setText(d);
            //Log.e("tag",Integer.toString(resultCode));
        }
    }
    public void showListView(ArrayList<FeedItem>items)
    {
        act1_listView.setAdapter(new Adapter(items));
    }
    public void showRecView(ArrayList<FeedItem>items)
    {
        act1_recView.setAdapter(new RecAdapter(items));
        act1_recView.setLayoutManager(new LinearLayoutManager(this));
    }
}
