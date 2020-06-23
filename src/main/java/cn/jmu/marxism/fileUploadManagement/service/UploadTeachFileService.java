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
 * 上传教案文件有关的业务方法
 */
@Service
public class UploadTeachFileService {

    /**
     * @param file 要上传的教案文件
     * @return 结果数组，数组里面有状态码，文件名，文件url
     */
    public static ArrayList<String> UploadTeachFile(MultipartFile file){
        ArrayList<String> end =new ArrayList<>();
          end.clear();
        String AllowFile = ".ppt,.pptx,.doc,.docx";
        if (file.isEmpty()) {
            end.add("404");
            return end;
        }
        String fileName = file.getOriginalFilename();
        String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if(AllowFile.contains(prefix.trim().toLowerCase())){
            String fileNameUuid = UUID.randomUUID() + prefix;
            String path="/home/www/website"+"/teachFile/"+fileNameUuid;
            File destTeachFile = new File(path);
            if (!destTeachFile.getParentFile().exists()) {
                destTeachFile.getParentFile().mkdir();
            }
            try {
                file.transferTo(destTeachFile);
                end.add("200");end.add(fileName);end.add(fileNameUuid);
                return end;
            } catch (IOException e) {
                end.add("-6");
                return end;
            }
        }else{
            end.add("403");
            return end;
        }
    }

}
