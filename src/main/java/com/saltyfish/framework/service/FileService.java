package com.saltyfish.framework.service;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.sun.deploy.net.URLEncoder;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by weck on 16/9/7.
 */
@Service
public class FileService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String saveFile(MultipartFile file, Long timeStamp) throws IOException {
        InputStream inputStream = file.getInputStream();
        String aliases = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        saveFileStream("fs", inputStream, file.getContentType(), aliases, file.getOriginalFilename(), timeStamp);
        return "/file/" + aliases;
    }

    public void saveFileStream(String collectionName, InputStream inputStream, String contentType, String aliases, String originalName, Long timeStamp) {
        DB db = mongoTemplate.getDb();
        GridFS gridFS = new GridFS(db, collectionName);
        GridFSInputFile gridFSInputFile = gridFS.createFile(inputStream);
        gridFSInputFile.put("originalName", originalName);
        gridFSInputFile.put("aliases", aliases);
        gridFSInputFile.put("contentType", contentType);
        gridFSInputFile.put("timeStamp", timeStamp);
        gridFSInputFile.save();
    }

    /**
     * 设置下载文件中文件的名称
     *
     * @param filename
     * @param request
     * @return
     */
    public String encodeFilename(String filename, HttpServletRequest request) {
        /**
         * 获取客户端浏览器和操作系统信息
         * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
         * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
         */
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (agent.contains("MSIE"))) {
                String newFileName = URLEncoder.encode(filename, "UTF-8");
                newFileName = StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
                    newFileName = StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }
            if ((agent != null) && (agent.contains("Mozilla")))
                return MimeUtility.encodeText(filename, "UTF-8", "B");

            return filename;
        } catch (Exception ex) {
            return filename;
        }
    }

}
