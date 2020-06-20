package cn.jmu.marxism.mapper;

import cn.jmu.marxism.fileUploadManagement.model.FileNameUrl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FileUrlMapper {

        //插入教案表teachfileurl
        @Insert({"insert into teachfileurl values (#{filename},#{url})"})
        int insertTeachFileUrl(@Param("filename") String filename, @Param("url") String url);

        //插入课件表 learnfileurl
        @Insert({"insert into learnfileurl values (#{filename},#{url})"})
        int insertLearnFileUrl(@Param("filename") String filename, @Param("url") String url);


        //查询取数据教案表
        @Select("select * from teachfileurl")
        List<FileNameUrl> selectTeachFileUrl();

        //查询取数据课件表
        @Select("select * from learnfileurl")
        List<FileNameUrl> selectLearnFileUrl();
}
