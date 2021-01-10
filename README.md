<!--
 * @Description: 
 * @Author: He Yuhang
 * @Github: https://github.com/hyhhhhhhhh
 * @Date: 2021-01-10 13:49:37
 * @LastEditors: Box
 * @LastEditTime: 2021-01-10 13:51:42
-->
# Springboot Web Project
 SCUT homework
# eplatform
A Web Applicatioin_SCUT_Practice 
 
登录后台： 主页面/admin 企图越过admin直接访问admin下的其他页面会被拦截 
 
后台登录密码：账号：admin密码：admin
 
github地址：https://github.com/hyhhhhhhhh/eplatform
 
IP地址访问线上网站首页：http://39.97.185.42:2
 
IP地址访问线上网站后台：http://39.97.185.42:2/admin
 
项目报告书：https://www.wolai.com/xvaK7wGNGxHbfCZQ5EP4tT
 
# 采用框架

    springboot+mysql+thymeleaf+js+css+html+bootstrap

    ### springboot

    springboot基于Spring4.0设计，不仅继承了Spring框架原有的优秀特性，而且还通过简化配置来进一步简化了Spring应用的整个搭建和开发过程。

    SpringBoot所具备的特征有：

    （1）可以创建独立的[Spring](https://baike.baidu.com/item/Spring/85061)应用程序，并且基于其Maven或Gradle插件，可以创建可执行的JARs和WARs；

    （2）内嵌Tomcat或Jetty等Servlet容器；

    （3）提供自动配置的“starter”项目对象模型（POMS）以简化[Maven](https://baike.baidu.com/item/Maven/6094909)配置；

    （4）尽可能自动配置Spring容器；

    （5）提供准备好的特性，如指标、健康检查和外部化配置；

    （6）绝对没有代码生成，不需要XML配置。

    本次实验采用springboot搭建电商平台。

    ### mysql

    mysql 用于搭建后台的数据库，实现要求中和数据库有关的功能。使用jdbc API可以实现数据的交互。

    ### thymeleaf

    thymeleaf属于一款模板引擎，使用thymeleaf的主要目的是，处理后端传到前端的数据，动态地处理html页面的数据，也有助于将网页可复用的部分分成fragment。

    ### bootstrap

    bootstrap提供了优秀的css排版样式、js插件

    ### js+css+html

    js+css+html用于现实前端的静态页面，处理动态效果，处理图表等
    
    # 项目结构

```纯文本
├─com
│  └─eplatform
│      ├─config
│      ├─controller
│      ├─mapper
│      └─pojo
└─META-INF
```
