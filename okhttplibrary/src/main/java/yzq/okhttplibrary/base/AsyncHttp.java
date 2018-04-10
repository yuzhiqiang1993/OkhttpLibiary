package yzq.okhttplibrary.base;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yzq on 2018/2/11.
 */

public class AsyncHttp {

    /*同步请求*/
    private static void sendRequest(String url) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("sdasda", "dsasa")
                .build();


        try {
            System.out.println(Thread.currentThread().getId());
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {

                System.out.println(response.body().string());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("同步请求");

    }


    /*异步请求*/

    private static void sendAsyncRequest(String url) {
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder().url(url).build();
        System.out.println(Thread.currentThread().getId());
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

        System.out.println("异步请求");

    }

    public static void main(String[] args) {
        sendRequest("http://yuzhiqiang.name");

    }
}
