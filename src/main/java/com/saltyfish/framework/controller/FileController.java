package com.saltyfish.framework.controller;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by weck on 16/9/15.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @RequestMapping("/{fileName}")
    public void downloadFile(HttpServletResponse response,
                             @PathVariable("fileName") String filename) {
        Query query = new Query();
        Criteria criteria = Criteria.where("aliases").regex(filename + ".*");
        query.addCriteria(criteria);

        GridFSDBFile dbfile = gridFsTemplate.findOne(query);
        String md5 = dbfile.getMD5();

        response.setContentLength((int) dbfile.getLength());
        response.setContentType(dbfile.getContentType());
        response.setHeader("Cache-Control", "public");//HTTP 1.1
        response.setDateHeader("Expires", System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000000);   //在代理服务器端防止缓冲, nanoseconds
        response.setDateHeader("max-age", System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000000);
        response.addHeader("md5", md5);
        OutputStream out;
        try {
            out = response.getOutputStream();
            dbfile.writeTo(out);
        } catch (IOException e) {
            System.out.println("download file failed:" + e);
        }
    }
}
