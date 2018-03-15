package yzq.okhttplibrary.base;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yzq on 2018/3/14.
 * 缓存
 */

public class CacheHttp {
    public static void main(String[] args) throws IOException {
        long maxCacheSize = 10 * 1024 * 1024;//缓存的大小
        File cacheFile = new File("/E:/Cache");//设置缓存的目录

        Cache cache = new Cache(cacheFile, maxCacheSize);

        OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();

        /*默认使用缓存*/
        Request request = new Request.Builder().url("http://www.qq.com/").build();
        /*配置不使用缓存*/
        Request request1 = new Request.Builder().url("http://www.qq.com/").cacheControl(new CacheControl.Builder().noCache().build()).build();


        Response response1 = client.newCall(request).execute();

        String body1 = response1.body().string();//这个地方要读取一下body，否则触发缓存
        Response networkResponse1 = response1.networkResponse();
        Response cacheResponse1 = response1.cacheResponse();
        System.out.println("networkResponse1: " + networkResponse1);
        System.out.println("cacheResponse1: " + cacheResponse1);

        System.out.println("====================================");

        Response response2 = client.newCall(request).execute();

        String body2 = response2.body().string();//这个地方要读取一下body，否则触发缓存
        Response networkResponse2 = response2.networkResponse();
        Response cacheResponse2 = response2.cacheResponse();
        System.out.println("networkResponse2: " + networkResponse2);
        System.out.println("cacheResponse2: " + cacheResponse2);


    }

}


