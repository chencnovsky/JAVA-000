package io.github.kimmking.gateway.outbound.httpclient;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpClientOutboundHandler {

    private static final int DEFAULT_CONN_TIMEOUT = 10000;

    private static final int DEFAULT_READ_TIMEOUT = 30000;

    private String backendUrl;

    public HttpClientOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(0, backendUrl.length() - 1) : backendUrl;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullRequest.uri();
        String resp = postReq(url, fullRequest.headers().get("nio"), DEFAULT_CONN_TIMEOUT, DEFAULT_CONN_TIMEOUT);
        FullHttpResponse response = null;
        try {
            byte[] body = resp.getBytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", resp.length());

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    /**
     * http post
     *
     * @param requrl
     * @param req
     * @param connTimeOut
     * @param readTimeOut
     * @return String
     */
    private String postReq(String requrl, String req, int connTimeOut, int readTimeOut) {
        String rtStr = null;

        HttpURLConnection conn = null;
        try {
            URL url = new URL(requrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setConnectTimeout(connTimeOut);
            conn.setReadTimeout(readTimeOut);
            conn.setRequestProperty("nio", req);
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")) {
//            out.write(req);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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

    private void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
