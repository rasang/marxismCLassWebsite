package cn.jmu.marxism.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

//上传配置类
//图片放到/E:/fileUpload/后，从磁盘读取的图片数据scr将会变成images/picturename.jpg的格式
@Configuration
public class FileConfig implements WebMvcConfigurer {
    /**
     * 配置资源映射
     * 意思是：如果访问的资源路径是以“/images/”开头的，
     * 就给我映射到本机的“F:/uploadFile/”这个文件夹内，去找你要的资源
     * 注意：F:/uploadFile/ 后面的 “/”一定要带上
     */

    //获取jar包路径 ,目录地址中\转化为/
    private String jarPath=System.getProperty("user.dir");
    private String transJarPath=jarPath.replace("\\","/");

    private String learnFilePath=transJarPath+"/learnFile/";
    private String teachFilePath=transJarPath+"/teachFile/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //教案文件资源映射
        registry.addResourceHandler("/teachFile/**").addResourceLocations("file:/"+teachFilePath);
        //课件文件资源映射
        registry.addResourceHandler("/learnFile/**").addResourceLocations("file:/"+learnFilePath);
    }
}


