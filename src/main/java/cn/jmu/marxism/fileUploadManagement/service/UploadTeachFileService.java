package cn.jmu.marxism.fileUploadManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author qbz
 * @version 1.0
 * @date 2020/6/18 10:52
 * 和upLoadFile有关的业务方法
 */
@Service
public class UploadTeachFileService {

    /**
     *
     * @param file
     * @return ArrayList数组
     */
    public static ArrayList<String> UploadTeachFile(MultipartFile file){

        //获取jar包路径 ,目录地址中\转化为/
        String jarPath=System.getProperty("user.dir");
        String transJarPath=jarPath.replace("\\","/");

        //返回结果数组
        ArrayList<String> end =new ArrayList<>();
        end.clear();
        //存放Url
        String  url;
        //允许文件类型
        String AllowFile = ".ppt,.pptx,.doc,.docx";
        System.out.print("开始上传教案文件"+"\n");

        //判断文件是否为空
        if (file.isEmpty()) {
            end.add("404");
            return end;
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        //判断文件是否符合要求的类型，去掉空格转化为小写
        if(AllowFile.contains(prefix.trim().toLowerCase())){

            //生成UUID
            String fileNameUuid = UUID.randomUUID() + prefix;
           // String path = "D:/teachFile/" +fileNameUuid;
            String path=transJarPath+"/teachFile/"+fileNameUuid;

            //创建文件路径
            File destTeachFile = new File(path);

            //判断文件父目录是否存在
            if (!destTeachFile.getParentFile().exists()) {
                destTeachFile.getParentFile().mkdir();
            }

            try {
                //上传文件到本地
                file.transferTo(destTeachFile);
                System.out.print("教案文件本地路径："+path+"\n");
                //生成url浏览地址
                url="http://localhost:8080/teachFile/"+fileNameUuid;
                System.out.println(url);
                System.out.println(fileName);

                end.add("200");
                end.add(fileName);
                end.add(fileNameUuid);

                return end;

            } catch (IOException e) {
                end.add("-6");
                return end;
            }
        }else{
            System.out.println("教案上传失败,文件格式不符合要求(.doc,.docx,.ppt,.pptx)");
            end.add("403");
            return end;
        }
    }

}
