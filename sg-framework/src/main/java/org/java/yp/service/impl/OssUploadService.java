package org.java.yp.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.java.yp.domain.ResponseResult;
import org.java.yp.enmu.AppHttpCodeEnum;
import org.java.yp.exception.SystemException;
import org.java.yp.service.UploadService;
import org.java.yp.utils.PathUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/31 20:49
 */

@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class OssUploadService implements UploadService {

    private String accessKey;
    private String secretKey;
    private String bucket;

    @Override
    public ResponseResult uploadImg(MultipartFile img) {

        //判断文件类型或者大小
        String originalFilename = img.getOriginalFilename();
        if (!originalFilename.endsWith(".png")&&!originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //如果判断通过上传文件到oss
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url=uploadOss(img,filePath);
        return ResponseResult.okResult(url);
    }

    private String uploadOss(MultipartFile imgFile, String filePath){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            InputStream inputStream= imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://rhh8nmf31.hd-bkt.clouddn.com/"+key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";
    }
}
