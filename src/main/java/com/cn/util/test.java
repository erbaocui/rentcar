package com.cn.util;
import com.cn.constant.SMS;
import com.cn.constant.Status;

import java.util.UUID;

/**
 * Created by home on 2017/7/14.
 */
public class test
{


    public static void main(String[] args) throws Exception{
        /*for(int i=0;i<10;i++){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
        }*/
      /* String name=java.net.URLEncoder.encode("阿里云短信测试专用", "UTF-8");
        System.out.println(name);
        name=java.net.URLEncoder.encode(name,"UTF-8");
        System.out.println(name);
        name=java.net.URLDecoder.decode(name, "UTF-8");
        System.out.println(name);
        System.out.println(java.net.URLDecoder.decode(name, "UTF-8"));*/
        System.out.println(SMS.SIGN);
    }
}
