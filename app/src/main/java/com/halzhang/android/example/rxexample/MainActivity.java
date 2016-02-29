package com.halzhang.android.example.rxexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MainActivity extends ActionBarActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String[] CITIES = {"Budapest,hu"};

    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private List<ArticlesData.Aritcle> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycleView();
        /**
         * 单个请求
         */
        Subscription subscribe = YuexiangApiManager.getArticlesObservable(19828, 0, 20, 0, 0).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArticlesData>() {
                    @Override
                    public void call(ArticlesData articlesData) {
                        Log.d(LOG_TAG, articlesData.toString());
                        mData = articlesData.articles;
                        mAdapter.setArticles(mData);
                        mAdapter.notifyDataSetChanged();
                    }
                });
//                        //解析列表
//
//                        //将列表拆成对象
//                .flatMap(new Func1<ArticlesData, Observable<ArticlesData.Aritcle>>() {
//                    @Override
//                    public Observable<ArticlesData.Aritcle> call(ArticlesData articlesData) {
//
//                        return Observable.from(articlesData.articles);
//                    }
//                })
//                        //打印所有标题
//                .subscribe(new Action1<ArticlesData.Aritcle>() {
//                    @Override
//                    public void call(ArticlesData.Aritcle article) {
//                        //是不是实际上这个就是订阅者的onNext。
//                        //这边需要在主线程显示，设置adapter，刷新列表
//                        Log.d(LOG_TAG, article.title);
//                        ((TextView) findViewById(R.id.text)).setText(article.title);
//                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        Log.e(LOG_TAG, throwable.getMessage(), throwable);
//                    }
//                });


        /**
         * Android View 事件处理
         */
//        RxView.clicks(findViewById(R.id.text))
//                .throttleFirst(500, TimeUnit.MILLISECONDS)
//                .subscribe(new Action1<Void>() {
//
//                    @Override
//                    public void call(Void aVoid) {
//                        Toast.makeText(getApplicationContext(), "Progress", Toast.LENGTH_SHORT).show();//主线程
//                    }
//                });
    }

    private void initRecycleView() {
        mData = new ArrayList<ArticlesData.Aritcle>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new NewsAdapter(mData,this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
