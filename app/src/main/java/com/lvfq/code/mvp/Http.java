package com.lvfq.code.mvp;

import java.util.HashMap;
import java.util.Map;

/**
 * Http
 *
 * @author lvfq
 * @date 2017/7/13 下午2:22
 * @mainFunction :
 */

public class Http {

    public static void http_login(String userName, String password, IHttpResultListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("passWord", password);
        HttpUtil.post("login", map, listener);
    }

}
