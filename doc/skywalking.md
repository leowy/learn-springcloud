# skywalking 

[![document](https://img.shields.io/badge/document-中文-red.svg)](https://github.com/apache/skywalking/blob/5.x/docs/README_ZH.md)
### skywalking介绍
基于OpenTracking的开源项目

### skywalking架构
[![dilGNR.png](https://s1.ax1x.com/2020/08/14/dilGNR.png)](https://imgchr.com/i/dilGNR)

### skywalking 目录结构
+ agent // 探针
    + activations
    + bootstrap-plugins  // 引导插件
    + config  //探针配置
    + logs
    + optional-plugins  // 可选插件
    + optional-reporter-plugins //可选报告插件
    + plugins // 插件
    + skywalking-agent.jar //探针程序
+ bin
    + oapService //后台收集器启动脚本
    + webappService // UI启动脚本
    + startup // oapService和webappService启动脚本
+ config  // 配置
+ license
+ logs
+ oap-libs //oap程序以及依赖
+ tools
+ webapp  //UI
    + skywalking-webapp.jar  //UI程序
    + webapp.yml  //UI配置
    

### skywalking 服务端
+ 下载
    [skywalking下载](http://skywalking.apache.org/downloads/)
+ 单机版
    启动/bin目录下，startup.sh/startup.bat即可  
    注意：端口占用情况   
    - 11800 grpc端口   // /config/application.yml配置中
    - 12800 rest端口   // /config/application.yml配置中
    - 8080 UI端口    // /webapp/webapp.yml配置中

### skywalking 客户端
skywalking使用的是非侵入性的链路追踪（java探针）  
+ 部署探针  
    将下载包中的agent的目录取出（获取探针）
+ 配置探针
    /agent/config/agent.config  
    `# The service name in UI 应用名`  
    `agent.service_name=${SW_AGENT_NAME:Your_ApplicationName}`  
    `# Backend service addresses.后台收集器地址`  
    `collector.backend_service=${SW_AGENT_COLLECTOR_BACKEND_SERVICES:127.0.0.1:11800}`
+ 安装探针
    java -jar `-javaagent:/your_agent-path/skywalking-agent.jar` your_application.jar
    其他类型请参照官方文档：tomcat、docker、k8s

+ agent插件
    + bootstrap-plugins 引导插件
    + optional-plugins 可选插件
    + plugins 内置插件
    
    前2种插件无法直接使用，需要将jar移至plugins目录下
    

