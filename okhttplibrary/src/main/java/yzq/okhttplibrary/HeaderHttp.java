package yzq.okhttplibrary;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.Header;

/**
 * Created by yzq on 2018/2/11.
 *
 * 请求头
 */

public class HeaderHttp {

    public static void main(String[] args) {

        OkHttpClient client=new OkHttpClient();

        final Request request=new Request.Builder()
                .url("https://baidu.com")
                .addHeader("coustomHeaderName","coustomValue")//添加自定义的请求头
                .addHeader("Content-Type","application/json")
                .build();


        System.out.println("请求头信息：");

        Headers requestHeaders = request.headers();
        for (int i = 0; i < requestHeaders.size(); i++) {
            System.out.println(requestHeaders.name(i)+":"+requestHeaders.value(i));
        }


        System.out.println("响应头信息：");

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Headers headers=response.headers();

                /*打印响应头*/
                for (int i = 0; i < headers.size(); i++) {
                    System.out.println(headers.name(i)+" : "+headers.value(i));
                }
            }
        });
    }
}
