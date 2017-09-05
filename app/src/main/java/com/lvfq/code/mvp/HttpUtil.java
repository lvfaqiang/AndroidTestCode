package com.lvfq.code.mvp;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HttpUtil
 *
 * @author lvfq
 * @date 2017/7/13 下午12:17
 * @mainFunction :
 */

public class HttpUtil {

    static final String HTTP = "http://192.168.2.1:8080/";

    private static Handler handler = new Handler();

    public static void post(final String url, final Map<String, Object> params, final IHttpResultListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                HttpURLConnection conn = null;
                OutputStream out = null;
                BufferedReader reader = null;
                try {
                    StringBuilder paramsStr = new StringBuilder();
                    if (params != null && params.size() > 0) {
                        for (Map.Entry<String, Object> entry : params.entrySet()) {
                            paramsStr.append(entry.getKey()).append("=").append(entry.getValue().toString()).append("&");
                        }
                        paramsStr.deleteCharAt(paramsStr.length() - 1);
                    }
                    URL u = new URL(HTTP + url);
                    conn = (HttpURLConnection) u.openConnection();

                    // 设置连接参数
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");// 设置以POST方式
                    conn.setUseCaches(false); // Post请求不使用缓存
                    conn.setInstanceFollowRedirects(true);
                    conn.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Charset", "UTF-8");

                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(10000);

                    // 上传的参数
                    Log.i("lfq", paramsStr.toString());
                    out = conn.getOutputStream();
                    out.write(paramsStr.toString().getBytes());
                    out.flush();
                    out.close();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer strBuf = new StringBuffer();
                    String str = null;
                    while ((str = reader.readLine()) != null) {
                        strBuf.append(str);
                    }
                    result = strBuf.toString();
                    Log.i("lfq", strBuf.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    result = e.toString();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (reader != null) {
                            reader.close();
                        }
                        if (conn != null) {
                            conn.disconnect();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (listener != null) {
                    final String res = result;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onResult(res);
                        }
                    });
                }
            }
        }).start();

    }
}
