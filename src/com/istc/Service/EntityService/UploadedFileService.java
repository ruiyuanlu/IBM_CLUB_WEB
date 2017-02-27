package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Member;
import com.istc.Entities.Entity.UploadedFile;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.UploadedFileDAO;
import com.istc.Utilities.DAOFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("uploadedFileService")
@Transactional(rollbackFor = Exception.class)
public class UploadedFileService {
    @Resource
    private transient UploadedFileDAO uploadedFileDAO;

    public void addFile(File file, Member owner){
        Integer count = getFileCount() + 1;
        UploadedFile uploadedFile = null;
        try {
            uploadedFile = new UploadedFile(file, count, owner, 1);
        } catch (IOException e) {
            System.out.println("获取路径失败!");
            e.printStackTrace();
        }
        uploadedFileDAO.save(uploadedFile);
    }

    /**
     * 每个文件都要对应一个owner
     * @param files 上传的文件列表
     * @param owners 每个文件的上传者
     */
    public void addFiles(File[] files, Member[] owners){
        Integer count = getFileCount() + 1;
        UploadedFile[] uploadedFiles = new UploadedFile[files.length];
        try {
            for(int i = 0; i < files.length; i++,count++ )
                uploadedFiles[i] = new UploadedFile(files[i], count, owners[i],1);
        } catch (IOException e) {
            System.out.println("获取路径失败!");
            e.printStackTrace();
        }
        uploadedFileDAO.save(uploadedFiles);
    }

    public File getFile(Integer fileID){
        UploadedFile file = uploadedFileDAO.get(fileID);
        return file == null ? null : new File(file.getFileCanonicalPath());
    }

    public File[] getFiles(Integer[] fileIDs){
        List<UploadedFile> list = uploadedFileDAO.get(fileIDs);
        if(list == null || list.size() == 0)return null;
        File[] files = new File[fileIDs.length];
        for(int i=0; i < fileIDs.length; i++)
            files[i] = list.get(i).createFile();
        return files;
    }

    public Integer getFileCount(){
        return uploadedFileDAO.totalCount();
    }
}
