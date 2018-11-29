package com.example.bfrol.it_samsung;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView view;
    TextView textLog;
    Disposable request;
    boolean click = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        view = (TextView) findViewById(R.id.textView);
        textLog = (TextView) findViewById(R.id.textLog);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Observable<Feed> obs = Observable.create(it -> {
                    it.onNext(Utils.getRequest("https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.bbci.co.uk%2Fnews%2Frss.xml"));
                }).map(it -> new Gson().fromJson(it.toString(), Feed.class)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                request = obs.subscribe(l -> {
                    for (FeedItem i : l.getItems()) {
                        textLog.append("Title: " + i.getTitle()+"\n");
                        textLog.append("Description: " + i.getDescription()+"\n");
                    }
                }, e -> {
                    textLog.append("ERROR" + e.toString());

                });
            }


        });
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
            view.setText(d);
            //Log.e("tag",Integer.toString(resultCode));
        }
    }
}
