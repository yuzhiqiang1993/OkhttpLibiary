package yzq.okhttplibrary.download;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yzq.okhttplibrary.common.HeadersLogger;

/**
 * Created by yzq on 2018/3/15.
 * <p>
 * Range字段  用于标注当前获取资源的某段字节  前提是能够获取content-length
 * <p>
 * 不是所有的请求都包含content-length字段  一般静态资源  比如图片，文件，电影这些都是包含content-length的
 * 像直接请求网页一般是不包含content-leng字段的，这个时候获取content-length时，值为-1
 * <p>
 * 我们可以通过Range字段来获取指定资源的某一段字节
 * .addHeader("Range", "bytes=0-300")// 表示获取0-300这一段字节
 */

public class RangeHttp {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url("http://yuzhiqiang.name/file/resume(yuZhiQiang).doc")
                .addHeader("Range", "bytes=0-300")//指定Range字段  表示只获取0-300字节
                .build();
        HeadersLogger.printHeaders(request.headers(), false);


        try {
            Response response = client.newCall(request).execute();

            System.out.println("content-length:" + response.body().contentLength());
            if (response.isSuccessful()) {


                HeadersLogger.printHeaders(response.headers(), false);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
