package com.whalecloud.util.httptool;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;



/**
 *
 *
 * @author zhaoyanac
 * @date 2019/10/27
 */
public class HttpClient {
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String JSON_CONTENT_FORM = "application/json;charset=UTF-8";
    public static final String CONTENT_FORM = "application/x-www-form-urlencoded;charset=UTF-8";

    public static String doService(String url, HttpParamers paramers, HttpHeader header, int connectTimeout, int readTimeout) throws Exception {
        HttpMethod httpMethod = paramers.getHttpMethod();
        switch (httpMethod) {
            case GET:
                return doGet(url, paramers, header, connectTimeout, readTimeout);
            case POST:
                return doPost(url, paramers, header, connectTimeout, readTimeout);
        }
        return null;
    }

    /**
     * post方法
     * @param url
     * @param paramers
     * @param header
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     */
    public static String doPost(String url, HttpParamers paramers, HttpHeader header, int connectTimeout, int readTimeout) throws IOException  {
        String responseData = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try{
            String query = null;
            HttpPost httpPost = new HttpPost(url);
            setHeader(httpPost, header);
            if (paramers.isJson()) {
                //json数据
                httpPost.setHeader(HTTP.CONTENT_TYPE, JSON_CONTENT_FORM);
                query = paramers.getJsonParamer();
            } else {
                //表单数据
                httpPost.setHeader(HTTP.CONTENT_TYPE, CONTENT_FORM);
                query = paramers.getQueryString(DEFAULT_CHARSET);
            }
            if(query != null){
                HttpEntity reqEntity = new StringEntity(query);
                httpPost.setEntity(reqEntity);
            }
            httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            HttpEntity resEntity = httpResponse.getEntity();
            responseData = EntityUtils.toString(resEntity);
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            httpResponse.close();
            httpClient.close();
        }
        return responseData;
    }


    /**
     * get方法
     *
     * @param url
     * @param params
     * @param header
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     */
    public static String doGet(String url, HttpParamers params, HttpHeader header, int connectTimeout, int readTimeout) throws IOException {
        String responseData = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try{
            String query = params.getQueryString(DEFAULT_CHARSET);
            url = buildGetUrl(url, query);
            HttpGet httpGet = new HttpGet(url);
            setHeader(httpGet, header);
            httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpGet);
            HttpEntity resEntity = httpResponse.getEntity();
            responseData = EntityUtils.toString(resEntity);
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            httpResponse.close();
            httpClient.close();
        }
        return responseData;
    }

    private static void setHeader(HttpRequestBase httpRequestBase, HttpHeader header){
        if(header != null){
            Map<String,String> headerMap = header.getParams();
            if (headerMap != null && !headerMap.isEmpty()) {
                Set<Map.Entry<String, String>> entries = headerMap.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    String name = entry.getKey();
                    String value = entry.getValue();
                    httpRequestBase.setHeader(name, value);
                }
            }
        }
    }

    private static String buildGetUrl(String url, String query) throws IOException {
        if (query == null || query.equals("")) {
            return url;
        }
        StringBuilder newUrl = new StringBuilder(url);
        boolean hasQuery = url.contains("?");
        boolean hasPrepend = (url.endsWith("?")) || (url.endsWith("&"));
        if (!hasPrepend) {
            if (hasQuery) {
                newUrl.append("&");
            } else {
                newUrl.append("?");
                hasQuery = true;
            }
        }
        newUrl.append(query);
        hasPrepend = false;
        return newUrl.toString();
    }
}


