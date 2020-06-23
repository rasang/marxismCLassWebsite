package cn.jmu.marxism.mapper;

import cn.jmu.marxism.fileUploadManagement.model.FileNameUrl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author qbz
 * @version 1.0
 * @date 2020/6/18 19:43
 */
@Repository
public interface FileUrlMapper {


        /**
         * 教案文件的文件名-Url插入数据库
         * @param filename 课件名
         * @param url 地址
         */
        @Insert({"insert into teachfileurl values (#{filename},#{url})"})
        void insertTeachFileUrl(@Param("filename") String filename, @Param("url") String url);

        /**
         * 课件文件的文件名-Url插入数据库
         * @param filename 课件名
         * @param url 地址
         */
        @Insert({"insert into learnfileurl values (#{filename},#{url})"})
        void insertLearnFileUrl(@Param("filename") String filename, @Param("url") String url);

        /**
         * 教案文件的文件名-Url从数据库中查询提取
         */
        @Select("select * from teachfileurl")
        List<FileNameUrl> selectTeachFileUrl();

        /**
         * 课件文件的文件名-Url从数据库中查询提取
         */
        @Select("select * from learnfileurl")
        List<FileNameUrl> selectLearnFileUrl();

        /**
         * 教案文件从数据库中删除
         */
        @Delete("delete from learnfileurl where url=#{url}")
        int deleteLearn(@Param("url") String url);

        /**
         * 课件文件从数据库中删除
         */
        @Delete("delete from teachfileurl where url=#{url}")
        int deleteTeach(@Param("url") String url);
}
