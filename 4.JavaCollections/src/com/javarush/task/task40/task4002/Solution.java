package com.javarush.task.task40.task4002;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("http://httpbin.org/post", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();

        // пост-запрос
        HttpPost post = new HttpPost(url);
        // добавляем заголовок
        post.addHeader("User-Agent", "Mozilla/5.0");

        // параметры запроса
        //post.setEntity(new StringEntity(urlParameters ,"UTF-8")); альтернатива
        post.setEntity(new UrlEncodedFormEntity(URLEncodedUtils.parse(urlParameters, Charset.forName("UTF-8"))));

        // выполняем запрос и получаем ответ
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();

        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader;
        StringBuffer result = new StringBuffer();
        if (entity != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                result.append(responseLine);
            }
        }

        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}