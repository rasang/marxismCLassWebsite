package cn.jmu.marxism.fileUploadManagement.service;


import cn.jmu.marxism.fileUploadManagement.model.FileNameUrl;
import cn.jmu.marxism.mapper.FileUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class FileUrlToDBService {

    @Autowired
    private FileUrlMapper fileUrlMapper;

    //插入教案表
    public void insertTeachFileUrl(String filename,String url){
        System.out.print("开始插入=filename=="+filename+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        int end=fileUrlMapper.insertTeachFileUrl(filename,url);
        System.out.print("插入结果==="+end+"\n");
        //return end;
    }
    //插入课件表
    public void insertLearnFileUrl(String filename,String url){
        System.out.print("开始插入=filename=="+filename+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        int end=fileUrlMapper.insertLearnFileUrl(filename,url);
        System.out.print("插入结果==="+end+"\n");
        //return end;
    }

    //查询教案表
    public List<FileNameUrl> selectTeachFileUrl(){
        System.out.print("开始查询教案表");
        List<FileNameUrl> end=fileUrlMapper.selectTeachFileUrl();
         return end;
    }

    //查询取数据课件表
    public List<FileNameUrl> selectLearnFileUrl(){
        System.out.print("开始查询课件表");
        List<FileNameUrl> end=fileUrlMapper.selectLearnFileUrl();
        return end;
    }

}
