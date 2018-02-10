package zxing.demo.yzq.okhttplib;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by yzq on 2018/2/10.
 */

public class HelloOkhttp {

    public static void main(String[] args) {


        System.out.println("sdasads");

        OkHttpClient okHttpClient=new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .build();


        try {
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()){
                System.out.println(response.body().string());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
