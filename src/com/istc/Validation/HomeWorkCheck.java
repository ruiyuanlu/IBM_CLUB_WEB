package com.istc.Validation;

/**
 * Created by lurui on 2017/2/27 0027.
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 检查文件上传合法性
 * 单个文件最大不能超过 500M
 */
public class HomeWorkCheck {

    private Boolean fileLengthOK;
    private Boolean fileExsits;
    private Boolean fileTypeOK;
    private String fileExtion;

    private static final Integer buffSize = 14;

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
     * 构造时进行文件相关检查
     */
    public HomeWorkCheck(File file) {
        //检查文件大小
        this.isFileLengthOK(file);
        try {
            //检查文件类型
            this.isFileTypeOK(file);
        } catch (Exception e) {
            fileLengthOK = fileExsits = fileTypeOK = false;
            fileExtion = null;
        }
    }

    /**
     * 传入文件并返回文件类型检查结果
     * @param file
     * @return 文件类型是否符合要求, 即pdf, doc, docx 三种
     * @throws Exception
     */
    private Boolean isFileTypeOK(File file)throws Exception{
        if(file == null)return fileTypeOK = fileExsits = false;
        fileExsits = true;

        FileInputStream fis = new FileInputStream(file);
        byte[] buff = new byte[buffSize];
        fis.read(buff);
        //获取文件头中的特征码
        String code = getFileCharactorCode(buff);
        for(String head: characterCode2Type.keySet()){
            if(head.startsWith(code.toLowerCase())){
                fileExtion = characterCode2Type.get(head);
                fis.close();
                return fileTypeOK = true;
            }
        }
        fis.close();
        return fileTypeOK = false;
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

    private Boolean isFileLengthOK(File file){
        return this.fileLengthOK = file == null ? false : file.length() <= maxLen;
    }

    public Boolean isFileLengthOK() {
        return fileLengthOK;
    }

    public Boolean isFileExsits() {
        return fileExsits;
    }

    public Boolean isFileTypeOK() {
        return fileTypeOK;
    }

    public String getFileExtion() {
        return fileExtion;
    }
}
