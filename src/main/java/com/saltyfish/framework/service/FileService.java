package com.saltyfish.framework.service;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by weck on 16/9/7.
 */
@Service
public class FileService {

    private static String importFilePath = "/opt/ws/file/";
    @Autowired
    private MongoTemplate mongoTemplate;

    public File saveFile(MultipartFile file, Long timestamp) {
        try {
            byte[] bytes = file.getBytes();
            File f = new File(importFilePath + timestamp + file.getOriginalFilename());
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(f));
            stream.write(bytes);
            stream.close();
            return f;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String saveFileToMongoDB(MultipartFile file, Long timeStamp) throws IOException {
        InputStream inputStream = file.getInputStream();
        String aliases = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        saveFileStream("fs", inputStream, file.getContentType(), aliases, file.getOriginalFilename(), timeStamp);
        if (!aliases.equals("")) {
            return "/file/" + aliases;
        }
        return "";
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


}
