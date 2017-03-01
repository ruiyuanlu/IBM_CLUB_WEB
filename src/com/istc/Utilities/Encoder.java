package com.istc.Utilities;

/**
 * Created by lurui on 2017/3/1 0001.
 */

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA 加密算法族
 */
public class Encoder {
    private static Encoder self;
    private Encoder(){}

    /**
     * 双重校验锁
     * @return Encoder
     */
    public static Encoder getInstance(){
        if(self == null)
            synchronized (Encoder.class){
                if(self == null)
                    self = new Encoder();
            }
        return self;
    }

    /**
     * SHA1 算法
     * @param data 摘要的字节数组
     * @return 加密后的字符串
     */
    private String encodeSHA1(byte[] data){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
        }
        byte[] digest = messageDigest.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA256 算法
     * @param data 摘要的字节数组
     * @return 加密后的字符串
     */
    private String encodeSHA256(byte[] data){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
        }
        byte[] digest = messageDigest.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA384 算法
     * @param data 摘要的字节数组
     * @return 加密后的字符串
     */
    private String encodeSHA384(byte[] data){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-384");
        } catch (NoSuchAlgorithmException e) {
        }
        byte[] digest = messageDigest.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA512 算法
     * @param data 摘要的字节数组
     * @return 加密后的字符串
     */
    private String encodeSHA512(byte[] data){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
        }
        byte[] digest = messageDigest.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }
}
