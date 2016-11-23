package cn.wycode.notifyme.pc;


import cn.wycode.notifyme.pc.bean.WyResult;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * JSON解析
 * Created by wangyu on 2015/8/17.
 */
public class JsonUtil {

    public static WyResult toJavaBeanResult(String resultStr) throws Exception {
        WyResult result = JSON.parseObject(resultStr, WyResult.class);
        return result;
    }


    public static WyResult toJavaBean(String resultStr, Class clazz) throws Exception {
        WyResult result = JSON.parseObject(resultStr, WyResult.class);
        Object o = JSON.parseObject(JSON.toJSONString(result.data), clazz);
        result.data = o;
        return result;
    }

    public static WyResult toJavaBeanList(String resultStr, Class clazz) throws Exception {
        WyResult result = JSON.parseObject(resultStr, WyResult.class);
        List tList = JSON.parseArray(JSON.toJSONString(result.data), clazz);
        result.data = tList;
        return result;
    }

    public static WyResult toJavaBeanListResult(String resultStr) throws Exception {
        WyResult result = JSON.parseObject(resultStr, WyResult.class);
        String s = JSON.toJSONString(result.data);
        List tList;
        if (s.length() > 2) {
            tList = JSON.parseArray(s);
        } else {
            tList = null;
        }
        result.data = tList;
        return result;
    }

}
