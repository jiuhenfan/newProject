package com.fengping.demo.controller;

import com.fengping.demo.service.FdfsService;
import com.fengping.demo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/fdfs", method = RequestMethod.POST)
public class FdfsController {
    @Autowired
    private FdfsService fdfsService;

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, MultipartFile file) {
        try {
            return fdfsService.uploadFile(file.getBytes(), StringUtil.getExtName(file.getOriginalFilename()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest request, String pathName) {
        try {
            byte[] bytes = fdfsService.downFile(pathName);
            System.out.println(bytes.length);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping("/delete")
    public boolean delete(String filePath) {
        try {
            return fdfsService.deleteFile(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
