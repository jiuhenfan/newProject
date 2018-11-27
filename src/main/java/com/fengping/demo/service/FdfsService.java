package com.fengping.demo.service;

import com.fengping.demo.component.MyFastFileStorageClientImpl;
import com.fengping.demo.dao.FastDfsDao;
import com.fengping.demo.dataSource.DS;
import com.fengping.demo.utils.StorePathUtil;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author fengping
 */
@Service
public class FdfsService {
    @Value("${fdfs.groups}")
    private String groupName;
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private MyFastFileStorageClientImpl myFastFileStorageClient;
    @Autowired
    private FastDfsDao fastDfsDao;

    /**
     * 通过fastdfs里的FastFileStorageClient类进行文件上传，其支持的文件格式有"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"，并将
     * 其路径存入数据库中
     * @param buff 文件数据
     * @param extName 文件扩展名
     * @return 返回文件存储路径
     * @throws Exception
     */
    @DS
    public String uploadFile(byte[]  buff,String extName) throws Exception {
        if(buff != null){
            InputStream sbs = new ByteArrayInputStream(buff);
            StorePath storePath = storageClient.uploadFile(sbs,buff.length,extName,null);
            fastDfsDao.insertFileData(storePath.getPath());
            return storePath.getPath();
        }
        return null;
    }

    /**
     * 过fastdfs里的FastFileStorageClient类进行文件下载，通过文件路径将改文件的byte[]数据返回
     * @param filePath 文件路径
     * @return 返回文件数据
     * @throws Exception
     */
    @DS
    public byte[] downFile(String filePath) throws Exception {
        DownloadByteArray callback = new DownloadByteArray();
        byte[] content = storageClient.downloadFile(groupName,filePath,callback);
        return content;
    }

    /**
     * 本方法不用fastdfs里的FastFileStorageClient类（该类中的删除方法尚无法正常使用）
     * 通过自身封装的MyFastFileStorageClientImpl类中的deleteFile方法进行实现
     * @param filePath 文件路径
     * @return 返回true：删除成功；false：删除失败
     */
    @DS
    public boolean deleteFile(String filePath){
        if(StringUtils.isNotBlank(filePath)){
            myFastFileStorageClient.deleteFile(filePath);
            String path = StorePathUtil.getFilePath(filePath);
            fastDfsDao.deleteFileData(path);
            return true;
        }
        return false;
    }
}