package com.yxh.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author YXH
 * @date 2020/7/27 - 20:14
 */
public class Tools {
    /**发送 http请求**/
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            //String urlNameString = url + "" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // resp.setContentType("text/html;charset=utf-8");
            connection.setRequestProperty("charset","utf-8");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {

                result += line;
            }
            System.out.println("发送请求后返回的数据："+result);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            return null;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**发送至后后台，用户id+商品id+5分，一次发一条**/
    public static void sendinitfoods2JW(String phone, String initfoods) {
        String[] food_ids = initfoods.split("-");
        for(int i=0;i<food_ids.length;i++) {
            String param = phone+"/"+food_ids[i]+"/"+5;
            String res = sendGet(Data.URL_WirteGrade+param,"");
            System.out.println("已发送。。。"+param+"..\n"+res);
        }
    }

    /**发送至后后台，用户id+商品id+5分，一次发一条**/
    public static void sendinitfoods2JW(String phone, String food_id, int grade) {

        String param = phone+"/"+food_id+"/"+grade;
        String res = sendGet(Data.URL_WirteGrade+param,"");
        System.out.println("已发送。。。"+param+"..\n"+res);
    }

    public static String getLikeFoodsId(String phone) {

        String getIds = sendGet(Data.URL_GetLikeFoods+phone,"");
        return getIds;
    }
}

