package zxing.demo.yzq.okhttplibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yzq.okhttplibrary.progressmanager.ProgressListener;
import yzq.okhttplibrary.progressmanager.ProgressManager;
import yzq.okhttplibrary.progressmanager.body.ProgressInfo;

public class MainActivity extends AppCompatActivity {

    private String downloadUrl;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("asdas", "oncreate");
        client = ProgressManager.getInstance().with(new OkHttpClient.Builder()).build();

        downloadUrl = "http://oss.ucdl.pp.uc.cn/fs01/union_pack/Wandoujia_249423_web_inner_referral_binded.apk?x-oss-process=udf%2Fpp-udf%2CJjc3LiMnJ3R1dXFw";


        ProgressManager.getInstance().addResponseListener(downloadUrl, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {

//                Log.i("getContentLength:", "" + progressInfo.getContentLength());
//                Log.i("getCurrentbytes:", "" + progressInfo.getCurrentbytes());
//                Log.i("getEachBytes:", "" + progressInfo.getEachBytes());
//                Log.i("getId", "" + progressInfo.getId());
//                Log.i("getIntervalTime", "" + progressInfo.getIntervalTime());
//                Log.i("getPercent", "" + progressInfo.getPercent());
//                Log.i("getSpeed", "" + progressInfo.getSpeed());
//                Log.i("分割线", "--------------------------------");

                Log.i("下载信息：",progressInfo.toString());

            }


            @Override
            public void onError(long id, Exception e) {

            }
        });


        downloadStart();
    }


    /**
     * 点击开始下载资源,为了演示,就不做重复点击的处理,即允许用户在还有进度没完成的情况下,使用同一个 url 开始新的下载
     */
    public void downloadStart() {
        Log.i("sadsadsa", "开始下载");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url(downloadUrl)
                            .build();

                    Response response = client.newCall(request).execute();

                    InputStream is = response.body().byteStream();
                    //为了方便就不动态申请权限了,直接将文件放到CacheDir()中
                    File file = new File(getCacheDir(), "download");
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                    fos.flush();
                    fos.close();
                    bis.close();
                    is.close();


                } catch (IOException e) {
                    e.printStackTrace();

                    Log.e("错误", e.getMessage());
                    //当外部发生错误时,使用此方法可以通知所有监听器的 onError 方法
                    ProgressManager.getInstance().notifyOnErorr(downloadUrl, e);
                }
            }
        }).start();
    }
}
