package org.security;

import org.util.LoggerUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author miaomuzhi
 * @since 2018/7/19
 * 用于MD5算法加密
 */
public class MD5Encrypt {

    private MD5Encrypt(){}

    public static String md5(String old){
        String newS = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(old.getBytes("utf-8"));
            byte[] md = messageDigest.digest();
            //将密文转为16进制字符串
            StringBuilder md5Buffer = new StringBuilder();
            for (byte aMd : md) {
                if (Integer.toHexString(0xFF & aMd).length() == 1)
                    md5Buffer.append("0").append(Integer.toHexString(0xFF & aMd));
                else md5Buffer.append(Integer.toHexString(0xFF & aMd));
            }
            newS = md5Buffer.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LoggerUtil.getLogger().warning(e);
        }
        return newS;
    }
}
