//package com.example.Controller;
//
//import com.aliyun.oss.model.OSSObjectSummary;
//import com.example.Result.FileUploadResult;
//import com.example.service.Impl.FileUploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@Controller
//public class FileUploadController {
//    @Autowired
//    private FileUploadService fileUploadService;
//
//    /**
//     * @desc 文件上传到oss
//     * @return FileUploadResult
//     * @Param uploadFile
//     */
//    @RequestMapping("file/upload")
//    @ResponseBody
//    public FileUploadResult upload(@RequestParam("file") MultipartFile uploadFile)
//            throws Exception {
//        return this.fileUploadService.upload(uploadFile);
//    }
//
//    /**
//     * @return FileUploadResult
//     * @desc 根据文件名删除oss上的文件
//     * @Param objectName
//     */
//    @RequestMapping("file/delete")
//    @ResponseBody
//    public FileUploadResult delete(@RequestParam("fileName") String objectName)
//            throws Exception {
//        return this.fileUploadService.delete(objectName);
//    }
//
//    /**
//     *
//     * @desc 查询oss上的所有文件
//     * @return List<OSSObjectSummary>
//     * @Param
//     */
//    @RequestMapping("file/list")
//    @ResponseBody
//    public List<OSSObjectSummary> list()
//            throws Exception {
//        return this.fileUploadService.list();
//    }
//
//    /**
//     *
//     * @desc 根据文件名下载oss上的文件
//     * @return
//     * @Param objectName
//     */
//    @RequestMapping("file/download")
//    @ResponseBody
//    public void download(@RequestParam("fileName") String objectName, HttpServletResponse response) throws IOException {
//        //通知浏览器以附件形式下载
//        response.setHeader("Content-Disposition",
//                "attachment;filename=" + new String(objectName.getBytes(), "ISO-8859-1"));
//        this.fileUploadService.exportOssFile(response.getOutputStream(),objectName);
//    }
//}
package com.example.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileUploadController{
    //访问路径为：http://localhost:8080/file

    @RequestMapping("/file")

    public String file(){
        System.out.print("================请求路径===跳转file页面====="+"\n");
        return "/file";

    }

    @RequestMapping("/upload")

    public String upload(){
        System.out.print("================请求路径===跳转index页面====="+"\n");
        return "/index";

    }

}
