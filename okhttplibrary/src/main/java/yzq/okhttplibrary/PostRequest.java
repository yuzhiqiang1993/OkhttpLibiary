package yzq.okhttplibrary;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yzq on 2018/2/11.
 * 请求地址：http://v.juhe.cn/toutiao/index
 * 请求参数：type=&key=4c52313fc9247e5b4176aed5ddd56ad7
 * 请求方式：POST
 */

public class PostRequest {

    private static final String URL = "http://v.juhe.cn/toutiao/index";
    private static final String TYPE = "type";
    private static final String TYPE_VALUE = "科技";
    private static final String KEY = "key";
    private static final String KEY_VALUE = "4c52313fc9247e5b4176aed5ddd56ad7";


    public static void main(String[] args) {

        httpPost();
    }


    public static void httpPost() {

        OkHttpClient client = new OkHttpClient();

        /*表单*/
        FormBody formBody = new FormBody.Builder()
                .addEncoded(TYPE, TYPE_VALUE)
                .addEncoded(KEY, KEY_VALUE)
                .build();


        /*请求对象*/
        Request request = new Request.Builder().url(URL).post(formBody)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        System.out.println(e.getMessage());

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.isSuccessful()) {
                            System.out.println(response.body().string());
                        } else {
                            System.out.println(response);
                        }
                    }
                });

    }
}
