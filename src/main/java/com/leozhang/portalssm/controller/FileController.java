package com.leozhang.portalssm.controller;


import com.alibaba.fastjson.JSONObject;
import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/file")
@Controller
public class FileController {
    /**
     2. 然后我们将上传逻辑实现如下
     3. 重启服务测试⼀下上传按钮并且观察web控制台的打印信息，如果出现如下信息代表上传成功
     * 这⾥填写⾃⼰的public⽂件夹路径
     */
    public static String fileFolder = "D:/public/";
    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        //获取原始⽂件名
        String baseFileName = multipartFile.getOriginalFilename();
        //通过字符串截取最后⼀个.来获取⽂件后缀
        String ext = baseFileName.substring(baseFileName.lastIndexOf("."));
        //⽣成随机⽂件名防⽌同名⽂件覆盖
        String newFileName = UUID.randomUUID().toString() + ext;
        //创建⽂件对象
        File f = new File(fileFolder + newFileName);
        //将⽂件转存到我们创建的静态资源⽂件夹中
        multipartFile.transferTo(f);
        //创建返回数据对象
        JSONObject data = new JSONObject();
        //保存⽂件的访问路径和⽂件名
        data.put("url", "public/" + newFileName);
        data.put("name", newFileName);
        //通过Result对象将⽂件结果返回
        return Result.end(200, data, "上传成功", 0);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("url")String url){
        String fileName = url.substring(url.lastIndexOf("/")+1);
        String path = fileFolder+fileName;
        File f = new File(path);
        if(f.exists()){
            f.delete();
        }
        return Result.end(200,null,"删除成功",0);
    }


}
