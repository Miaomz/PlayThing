package org.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author miaomuzhi
 * @since 2018/8/28
 */
public class JsonSendUtil {

    private JsonSendUtil() {}

    /**
     * 工具方法
     * @param json 转化后的json字符串
     * @param response 响应
     */
    public static void sendJson2Browser(String json, HttpServletResponse response){
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try{
            writer = response.getWriter();
            writer.print(json);
            writer.flush();
        }catch (IOException ioe){
            LoggerUtil.getLogger().warning(ioe);
        }finally {
            if (writer != null){
                writer.close();
            }
        }
    }
}
