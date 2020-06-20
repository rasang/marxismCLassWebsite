package cn.jmu.marxism.fileUploadManagement.service;


import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class UploadLearnFileService {


    /**
     * @param file
     * @return 状态码
     */
    public static ArrayList<String> UploadLearnFile(MultipartFile file){

        //返回结果数组
        ArrayList<String> end =new ArrayList<>();
        end.clear();
        //存放 url
        String  url;
        //设置允许上传文件类型,课件只有.pdf
        String AllowFile = ".png,.pdf";
        System.out.print("开始上传课件文件"+"\n");

        //判断文件是否为空
        if (file.isEmpty()) {
            end.add("404");
           return end;
        }

        //获取jar包路径 ,目录地址中\转化为/
        String jarPath=System.getProperty("user.dir");
        String transJarPath=jarPath.replace("\\","/");

        // 获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        //判断文件是否符合要求的类型,转化为小写
        if(AllowFile.contains(prefix.trim().toLowerCase())){

            //生成UUID
            String fileNameUuid = UUID.randomUUID() + prefix;
            //String path = "D:/learnFile/" +fileNameUuid;
             String path=transJarPath+"/learnFile/"+fileNameUuid;
            //创建文件路径
            File destLearnFile = new File(path);

            //判断文件父目录是否存在
            if (!destLearnFile.getParentFile().exists()) {
                destLearnFile.getParentFile().mkdir();
            }

            try {
                //上传文件到本地
                file.transferTo(destLearnFile);
                System.out.print("课件文件本地路径："+path+"\n");
                //生成url浏览地址
                url="http://localhost:8080/learnFile/"+fileNameUuid;

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
            System.out.println("上传课件失败,文件格式不符合要求(.png,.pdf)");
            end.add("403");
            return end;
        }

    }
}
