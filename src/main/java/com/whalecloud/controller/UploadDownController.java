package com.whalecloud.controller;

import com.google.gson.Gson;
import com.whalecloud.dto.UploadPathInfo;
import com.whalecloud.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * 封装了文件上传和下载的控制层
 *
 * @author zhaoyanac
 * @date 2019/10/21
 */
@Controller
@RequestMapping("/upload/")
public class UploadDownController {

    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */
    @RequestMapping("getFile")
    @ResponseBody
    public String upload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {
        ArrayList<UploadPathInfo> uploadPathInfos = new ArrayList<>();

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");

        try {
            for (MultipartFile file : files) {

                //单个文件的路径
                String fileUrl = FileUploadUtil.getFileUrl(request, file);
                UploadPathInfo uploadPathInfo = new UploadPathInfo();
                uploadPathInfo.setPath(fileUrl);

                uploadPathInfos.add(uploadPathInfo);
            }

            Gson gson = new Gson();
            String jsonString = gson.toJson(uploadPathInfos);

            return jsonString;
        } catch (Exception e) {
            return "文件上传失败";

        }
    }
}


