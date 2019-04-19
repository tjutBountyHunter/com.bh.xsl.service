package util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @version V1.0
 * @ClassName:HttpClientUtil
 * @Description:悬赏令httpclient工具
 * @author:何林鸿
 * @date: 2018年7月30日
 */
public class HttpClientUtil {


    /**
     * get请求
     * <p>Title: doGet</p>
     * <p>Description: 发送一个get请求</p>
     *
     * @param url
     * @param param(Map)
     * @return
     */
    public static String doGet(String url, Map<String, String> param) {

        //创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //返回串
        String resultString = "";
        //响应
        CloseableHttpResponse response = null;

        try {
            // 创建一个url构造器
            URIBuilder builder = new URIBuilder(url);

            if (param != null) {
                for (String key : param.keySet()) {
                    //添加参数
                    builder.addParameter(key, param.get(key));
                }
            }

            //创建一个url
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);

            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * get请求
     * <p>Title: doGet</p>
     * <p>Description: 发送一个get请求</p>
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }


    /**
     * post请求
     * <p>Title: doPost</p>
     * <p>Description: 发送一个post请求</p>
     *
     * @param url
     * @param param(Map)
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {
        //创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //返回串
        String resultString = "";
        //响应
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                //封装数据（BasicNameValuePair）
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }

                // 利用参数列表提交一个数据表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);

                //讲表单添加进入post
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpclient.execute(httpPost);

            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    /**
     * post请求
     * <p>Title: doPost</p>
     * <p>Description: 发送一个post请求</p>
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * post请求
     * <p>Title: doPost</p>
     * <p>Description: 发送一个get请求</p>
     *
     * @param url
     * @param param(Json)
     * @return
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }
}

