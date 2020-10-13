package com.example.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import static redis.clients.jedis.HostAndPort.localhost;


@Configuration
public class ShiroConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
/**配置shiro的web过滤器，拦截浏览器请求并交给securityManager处理**/
    @Bean
    public ShiroFilterFactoryBean webFilter(){
    ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
    //配置拦截链 使用linkedHashMap,因为linkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        Map<String,String> filterChainMap=new LinkedHashMap<String, String>(16);
        //authc:所有url必须认证通过才可以访问，anon：所有url都可以匿名访问，先配置anon在配置authc
        filterChainMap.put("/login","anon");
        filterChainMap.put("/getNumber","anon");
        filterChainMap.put("/getCode","anon");
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
            //自定义缓存实现，使用redis
            securityManager.setCacheManager(redisCacheManager());
            //自定义session管理，使用redis
            securityManager.setSessionManager(defaultWebSessionManager());
            return securityManager;
        }
        @Bean
        public CustomRealm customRealm(){
        CustomRealm customRealm=new CustomRealm();
        //注入密码加密//
         customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return new CustomRealm();
        }
        //密码加密算法设置
        @Bean
        public HashedCredentialsMatcher hashedCredentialsMatcher(){
            HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
            hashedCredentialsMatcher.setHashAlgorithmName("md5");
            //散列的次数
            hashedCredentialsMatcher.setHashIterations(2);
            return hashedCredentialsMatcher;
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
        /**用于thymeleaf模板使用shiro标签**/
        @Bean
        public ShiroDialect shiroDialect(){
        return new ShiroDialect();
        }
        /**redisManager**/
        public RedisManager redisManager(){
            RedisManager redisManager=new RedisManager();
            redisManager.setHost(host);
            redisManager.setPort(port);
            return redisManager;
        }
        /**cachemanager**/
        @Bean
        public RedisCacheManager redisCacheManager(){
            RedisCacheManager redisCacheManager=new RedisCacheManager();
            //配置过期时间
            redisCacheManager.setExpire(1800);
            redisCacheManager.setRedisManager(redisManager());
            return redisCacheManager;
        }
        /**redissessionDAO**/
        @Bean
        public RedisSessionDAO redisSessionDAO(){
            RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
            redisSessionDAO.setRedisManager(redisManager());
            return redisSessionDAO;
        }
        /**sessionManager**/
        @Bean
        public DefaultWebSessionManager defaultWebSessionManager(){
            DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
            sessionManager.setSessionDAO(redisSessionDAO());
            return sessionManager;
        }
}
