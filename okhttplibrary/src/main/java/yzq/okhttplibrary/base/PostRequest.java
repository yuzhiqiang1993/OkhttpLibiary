package yzq.okhttplibrary.base;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yzq.okhttplibrary.common.HeadersLogger;
import yzq.okhttplibrary.progressmanager.ProgressListener;
import yzq.okhttplibrary.progressmanager.ProgressManager;
import yzq.okhttplibrary.progressmanager.body.ProgressInfo;

/**
 * Created by yzq on 2018/2/11.
 * 请求地址：http://v.juhe.cn/toutiao/index
 * 请求参数：type=&key=4c52313fc9247e5b4176aed5ddd56ad7
 * 请求方式：POST
 */

public class PostRequest  {

    private static final String URL = "http://v.juhe.cn/toutiao/index";
    private static final String TYPE = "type";
    private static final String TYPE_VALUE = "科技";
    private static final String KEY = "key";
    private static final String KEY_VALUE = "4c52313fc9247e5b4176aed5ddd56ad7";


    public static void main(String[] args) {

        httpPost();
    }


    public static void httpPost() {

        OkHttpClient client = ProgressManager.getInstance().with(new OkHttpClient.Builder()).build();

        /*表单*/
        FormBody formBody = new FormBody.Builder()
                .addEncoded(TYPE, TYPE_VALUE)
                .addEncoded(KEY, KEY_VALUE)
                .build();


        String downloadUrl = "https://github.com/yuzhiqiang1993/zxing/blob/master/app/release/zxing.apk";
        /*请求对象*/
        Request request = new Request.Builder().url(downloadUrl).post(formBody)
                .build();


        HeadersLogger.printHeaders(request.headers(), true);


        ProgressManager.getInstance().addRequestListener(downloadUrl, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {

                System.out.println("getContentLength:"+progressInfo.getContentLength());
                System.out.println("getCurrentbytes:"+progressInfo.getCurrentbytes());
                System.out.println("getEachBytes:"+progressInfo.getEachBytes());
                System.out.println("getId"+progressInfo.getId());
                System.out.println("getIntervalTime"+progressInfo.getIntervalTime());
                System.out.println("getPercent"+progressInfo.getPercent());
                System.out.println("getSpeed"+progressInfo.getSpeed());
                System.out.println("--------------------------------");

            }


            @Override
            public void onError(long id, Exception e) {

            }
        });

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        System.out.println(e.getMessage());

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.isSuccessful()) {

                            HeadersLogger.printHeaders(response.headers(), false);


                            System.out.println(response.body().string());


                        } else {
                            System.out.println(response);
                        }
                    }
                });

    }



}
