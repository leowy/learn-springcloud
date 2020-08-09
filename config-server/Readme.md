# config server

### 获取配置访问格式
        
        #获取配置信息
        /{label}/{application}-{profile}
        #获取配置文件
        /{label}/{application}-{profile}.yml
        
+ application 表示应用名称
+ label 表示分支名称
+ profile 表示环境名称


### 自动刷新配置
+ SpringCloud Bus 
+ 设置github webhooks
+ 依赖actuator
+ 依赖RabbitMQ