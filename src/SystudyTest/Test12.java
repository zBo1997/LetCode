package SystudyTest;


import com.alibaba.fastjson2.JSONObject;

import java.lang.reflect.Array;

/**
 * @Classname Test11
 * @Description
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test12 {


    public static void main(String[] args) {
        Integer[] o  = (Integer[])Array.newInstance(Integer.class, 2);
        for (int i = 1; i <= o.length; i++) {
            o[i-1] = i;
        }
        System.out.println(JSONObject.toJSONString(o));
    }




}