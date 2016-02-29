package com.halzhang.android.example.rxexample;


import android.util.Log;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * 接口
 * Created by Hal on 15/4/26.
 */
public class YuexiangApiManager {

    private static final String ENDPOINT = "http://115.29.246.87:56512/cms_if";

    /**
     * 服务接口
     */
    private interface YuexiangApiManagerService {
        @GET("/getArticles")
        ArticlesData getArticles(@Query("columnId") int columnId, @Query("lastFileId") int lastFileId, @Query("count") int count, @Query("rowNumber") int rowNumber, @Query("version") int version);

        /**
         * retrofit 支持 rxjava 整合
         * 这种方法适用于新接口
         */
        @GET("/getArticles")
        Observable<ArticlesData> getArticlesObservable(@Query("columnId")  int columnId, @Query("lastFileId") int lastFileId, @Query("count") int count, @Query("rowNumber") int rowNumber, @Query("version") int version);

    }

    private static final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).setLogLevel(RestAdapter.LogLevel.FULL).build();

    private static final YuexiangApiManagerService apiManager = restAdapter.create(YuexiangApiManagerService.class);

    /**
     * 将服务接口返回的数据，封装成{@link Observable}
     * 这种写法适用于将旧代码封装
     * @param city
     * @return
     */
    public static Observable<ArticlesData> getArticlesObservable(final int columnId,final int lastFileId,final int count,final int rowNumber,final int version) {
        return Observable.create(new Observable.OnSubscribe<ArticlesData>() {
            @Override
            public void call(Subscriber<? super ArticlesData> subscriber) {
                //订阅者回调 onNext 和 onCompleted
                subscriber.onNext(apiManager.getArticles(columnId,lastFileId,count,rowNumber,0));
                subscriber.onCompleted();
                Log.i("YuexiangApiManager", "Thread id: " + Thread.currentThread().getId());
            }
        }).subscribeOn(Schedulers.io());
    }
}
