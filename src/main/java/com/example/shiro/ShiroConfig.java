package com.example.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;


@Configuration
public class ShiroConfig {

/**配置shiro的web过滤器，拦截浏览器请求并交给securityManager处理**/
    @Bean
    public ShiroFilterFactoryBean webFilter(){
    ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
    //配置拦截链 使用linkedHashMap,因为linkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        Map<String,String> filterChainMap=new LinkedHashMap<String, String>(16);
        //authc:所有url必须认证通过才可以访问，anon：所有url都可以匿名访问，先配置anon在配置authc
        filterChainMap.put("/login","anon");
        filterChainMap.put("/index","anon");
        filterChainMap.put("/register","anon");
        filterChainMap.put("/**","authc");
        filterChainMap.put("/logout","logout");
        //设置拦截请求后跳转的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/success");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        return shiroFilterFactoryBean;
}
        @Bean
        public SecurityManager securityManager(){
            DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
            //将自定义的realm交给安全管理器
            securityManager.setRealm(customRealm());
            return securityManager;
        }
        @Bean
        public CustomRealm customRealm(){
        return new CustomRealm();
        }

        //开启AOP注解支持，@RequiresPermissions
        @Bean
         public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        //设置安全管理器
            authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
            return authorizationAttributeSourceAdvisor;
        }
        @Bean
        @ConditionalOnMissingBean
        public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
            DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
            defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
            return defaultAdvisorAutoProxyCreator;
        }
        //处理未授权的异常，返回403页面
        @Bean
        public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
            SimpleMappingExceptionResolver simpleMappingExceptionResolver=new SimpleMappingExceptionResolver();
            Properties properties=new Properties();
            properties.setProperty("UnauthorizedException","403.html");
            simpleMappingExceptionResolver.setExceptionMappings(properties);
            return simpleMappingExceptionResolver;
        }
}
