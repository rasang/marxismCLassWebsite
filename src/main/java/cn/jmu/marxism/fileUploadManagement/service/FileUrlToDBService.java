package cn.jmu.marxism.fileUploadManagement.service;


import cn.jmu.marxism.fileUploadManagement.model.FileNameUrl;
import cn.jmu.marxism.mapper.FileUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author qbz
 * @version 1.0
 * @date 2020/6/19 11:31
 * 文件和地址存入数据库和提取数据库数据业务方法
 */
@Service
@Component
public class FileUrlToDBService {

    @Autowired
    private FileUrlMapper fileUrlMapper;

    /**
     * 教案文件生成的url存入数据库教案表
     * @param filename 课件名
     * @param url 地址
     */
    public void insertTeachFileUrl(String filename,String url){
        fileUrlMapper.insertTeachFileUrl(filename,url);
    }

    /**
     * 课件文件生成的url存入数据库教案表
     * @param filename 课件名
     * @param url 地址
     */
    public void insertLearnFileUrl(String filename,String url){
       fileUrlMapper.insertLearnFileUrl(filename,url);
    }

    /**
     * 教案文件生成的文件名和url地址数据表的提取
     *@return 提取出的数据存入List数组end返回
     */
    public List<FileNameUrl> selectTeachFileUrl(){
        List<FileNameUrl> end=fileUrlMapper.selectTeachFileUrl();
         return end;
    }

    /**
     * 课件文件生成的文件名和url地址数据表的提取
     *@return 提取出的数据存入List数组end返回
     */
    public List<FileNameUrl> selectLearnFileUrl(){
        List<FileNameUrl> end=fileUrlMapper.selectLearnFileUrl();
        return end;
    }

}
