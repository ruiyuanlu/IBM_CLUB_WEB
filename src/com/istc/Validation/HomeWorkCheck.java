package com.istc.Validation;

/**
 * Created by lurui on 2017/2/27 0027.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 检查文件上传合法性
 * 单个文件最大不能超过 500M
 */
public class HomeWorkCheck {

    private static final Integer buffSize = 14;
    private static HomeWorkCheck self;

    /**
     * 文件类型特征码到文件类型的转换
     */
    private static Map<String, String> characterCode2Type;
    /**
     * 单个文件最大不能超过 500M
     */
    private static final Integer maxLen = 500 << 20;

    /**
     * 初始化文件特征码集合
     */
    static {
        characterCode2Type = new HashMap<String, String>();
        characterCode2Type.put("255044462d312e", "pdf");
        characterCode2Type.put("d0cf11e0a1b11ae1", "doc");
        characterCode2Type.put("504b0304140006000800", "docx");
    }

    /**
     * 构造函数
     */
    private HomeWorkCheck() {
    }

    /**
     * 单例模式
     */
    public static HomeWorkCheck getInstance(){
        if(self == null)
            synchronized (HomeWorkCheck.class){
                if(self == null) self = new HomeWorkCheck();
            }
        return self;
    }

    /**
     * 传入文件并返回文件类型检查结果
     * @param file
     * @return 文件类型是否符合要求, 即pdf, doc, docx 三种
     * @throws Exception
     */
    public Boolean isFileTypeOK(File file){
        return this.getFileExtend(file) != null;
    }

    public Boolean isFileLengthOK(File file){
        return file == null ? false : file.length() <= maxLen;
    }

    public Boolean isFileExsits(File file){
        return file != null && file.exists();
    }

    /**
     * 检查文件格式，并匹配扩展名
     * @param file
     * @return 如果文件存在并且是允许的格式，则返回对应扩展名
     * @return 如果文件不存在，或是其它格式，则返回 null
     */
    public String getFileExtend(File file){
        if(!this.isFileExsits(file))return null;
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buff = new byte[buffSize];
            fis.read(buff);
            //获取文件头中的特征码
            String code = getFileCharactorCode(buff);
            for(String head: characterCode2Type.keySet()){
                if(head.startsWith(code.toLowerCase())){
                    fis.close();
                    return characterCode2Type.get(head);
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提取文件特征码
     * @param fileHead 文件头部字节
     * @return 文件特征码字符串
     */
    private String getFileCharactorCode(byte[] fileHead){
        if(fileHead == null || fileHead.length <= 0)return null;
        StringBuilder strb = new StringBuilder();
        for(byte b: fileHead)
            strb.append(b & 0xf0).append(b & 0x0f);
        return strb.toString();
    }

}
