package com.example.shiro;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

//上传配置类
//图片放到/F:/fileUpload/后，从磁盘读取的图片数据scr将会变成images/picturename.jpg的格式
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {
//public class WebAppConfig extends WebMvcConfigurationSupport {
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/success.html").setViewName("success");
        registry.addViewController("/login2.html").setViewName("login2");
        registry.addViewController("/register2.html").setViewName("register2");
        registry.addViewController("/article_article.html").setViewName("article_article");
        registry.addViewController("/cart.html").setViewName("cart");
        registry.addViewController("/403.html").setViewName("403");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/list_product.html").setViewName("list_product");
    }
//    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
//        factory.setMaxFileSize(DataSize.of(1024, DataUnit.MEGABYTES));
//        /// 设置总上传数据总大小10M
//        factory.setMaxRequestSize(DataSize.of(1024, DataUnit.MEGABYTES));
//        return factory.createMultipartConfig();
//    }

        @Override
        public void addResourceHandlers (ResourceHandlerRegistry registry){
            if (mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")) {
                String imagesPath = WebAppConfig.class.getClassLoader().getResource("").getPath();
                System.out.print("1.上传配置类imagesPath==" + imagesPath + "\n");
                if (imagesPath.indexOf(".jar") > 0) {
                    imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
                } else if (imagesPath.indexOf("classes") > 0) {
                    imagesPath = "file:"+ imagesPath.substring(0, imagesPath.indexOf("classes"));
                }
                imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"));
                mImagesPath = imagesPath;
            }
            System.out.print("imagesPath=============" + mImagesPath + "\n");
            //LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath============="+mImagesPath+"\n");
            registry.addResourceHandler("/**").addResourceLocations(mImagesPath);
            // TODO Auto-generated method stub
            System.out.print("2.上传配置类mImagesPath==" + mImagesPath + "\n");
            super.addResourceHandlers(registry);
        }


}