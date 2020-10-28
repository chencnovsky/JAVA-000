package org.geekbang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    private static final int DEFAULT_CONN_TIMEOUT = 10000;

    private static final int DEFAULT_READ_TIMEOUT = 30000;

    private static final String TEST_URL = "http://localhost:8801/test";

    /**
     * http post
     *
     * @param requrl
     * @param req
     * @param connTimeOut
     * @param readTimeOut
     * @return String
     */
    public static String postReq(String requrl, String req, int connTimeOut, int readTimeOut) {
        String rtStr = null;

        HttpURLConnection conn = null;
        try {
            URL url = new URL(requrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setConnectTimeout(connTimeOut);
            conn.setReadTimeout(readTimeOut);
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")) {
            out.write(req);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            char[] buff = new char[2048];
            int cnt = 0;
            while ((cnt = in.read(buff)) != -1) {
                sb.append(buff, 0, cnt);
            }
            rtStr = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rtStr;
    }

    public static void main(String[] args) {
        System.out.println(postReq(TEST_URL, "test", DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT));
    }

}
