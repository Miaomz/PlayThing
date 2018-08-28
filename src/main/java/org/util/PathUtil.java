package org.util;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * @author 王川源
 * 管理资源路径
 * 迭代三改用数据库，无需初始化文件夹
 */
public class PathUtil {

    private PathUtil() {}

    public static String imageContextPath;

    public static String imageUploadPath;


    static {
        Properties pathProperty = new Properties();
        try {
            pathProperty.load(new InputStreamReader(PathUtil.class.getResourceAsStream("/path.properties"),"UTF-8"));
            imageContextPath = pathProperty.getProperty("image-context-path");
            imageUploadPath = pathProperty.getProperty("image-upload-path");

            //初始化数据文件夹
            FileUtil.createDirectory(imageUploadPath);

        } catch (IOException e) {
            LoggerUtil.getLogger().info(e);
        }
    }

}
