package cn.jmu.marxism.fileUploadManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qbz
 * @date 2020/6/18 11:23
 * FileNameUrl实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileNameUrl {
    //文件原名
    String fileName;
    //文件生成的Url
    String url;
}
