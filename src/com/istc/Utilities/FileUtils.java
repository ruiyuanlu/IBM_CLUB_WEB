package com.istc.Utilities;

import java.io.*;

/**
 * Created by lurui on 2017/3/12 0012.
 */
public class FileUtils {
    private static FileUtils self;
    /**
     * 缓存大小为 2M
     */
    private static final int buffSize = 2 << 20;
    private FileUtils(){}

    public static FileUtils getInstance(){
        if(self == null) synchronized (FileUtils.class){
            if(self == null) self = new FileUtils();
        }
        return self;
    }

    /**
     * 删除文件或文件夹
     * @param file
     * @return true 如果文件存在并且删除成功
     * @return false 如果文件不存在或删除异常或其它异常
     */
    public boolean delete(File file){
        System.out.println(file.getAbsolutePath());
        if(file == null || !file.exists())return false;
        if(file.isFile())return deleteFile(file);
        if(file.isDirectory())return deleteDirectory(file);
        else return false;
    }

    /**
     * 删除文件的方法
     * @param file
     * @return true 如果文件存在且成功删除
     * @return false 如果文件不存在或删除异常
     */
    private boolean deleteFile(File file){
        if(file.delete() == false){
            System.gc();
            if(file.delete() == false){
                file.deleteOnExit();
                return false;
            }
        }
        return true;
    }

    /**
     * 递归地删除文件夹以及文件夹下的子文件夹和文件
     * @param directory
     * @return true 文件夹存在且所有文件删除成功
     * @return false 存在文件或者文件夹删除异常
     */
    private boolean deleteDirectory(File directory){
        File[] files = directory.listFiles();
        boolean flag = true;
        for(File file: files)flag &= delete(file);
        return flag;
    }

    public void copy (File src, File dst) {
        InputStream in = null;
        OutputStream out = null;
        int len;
        try {
            in = new BufferedInputStream(new FileInputStream(src), buffSize);
            out = new BufferedOutputStream(new FileOutputStream(dst), buffSize);
            byte[] buffer = new byte[buffSize];
            while ((len = in.read(buffer)) > 0) out.write(buffer, 0, len);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
