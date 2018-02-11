package yzq.okhttplibrary;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yzq on 2018/2/11.
 * <p>
 * get请求
 * <p>
 * 请求的是聚合新闻接口
 * 请求地址：http://v.juhe.cn/toutiao/index
 * 请求参数：type=&key=4c52313fc9247e5b4176aed5ddd56ad7
 * 请求方式：GET
 */

public class GetRequest {


    private static final String URL = "http://v.juhe.cn/toutiao/index";
    private static final String TYPE = "type";
    private static final String TYPE_VALUE = "top";
    private static final String KEY = "key";
    private static final String key_value = "4c52313fc9247e5b4176aed5ddd56ad7";

    public static void main(String[] args) {


        httpGet(URL);

    }

    public static void httpGet(String url) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .addQueryParameter(TYPE, TYPE_VALUE)
                .addQueryParameter(KEY, key_value)
                .build();

        Request request = new Request.Builder().url(httpUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                }

            }
        });

    }
}
