package yzq.okhttplibrary.common;

import okhttp3.Headers;

/**
 * Created by yzq on 2018/3/15.
 */

public class HeadersLogger {

    public static void printHeaders(Headers headers, boolean isRequestHeaders) {
        if (isRequestHeaders) {
            System.out.println("===========Request Headers========");
        } else {
            System.out.println("===========Response Headers========");
        }


        for (int i = 0; i < headers.size(); i++) {
            System.out.println(headers.name(i) + ":" + headers.value(i));
        }
    }
}
