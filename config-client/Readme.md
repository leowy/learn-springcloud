# config client

+ 需要使用bootstrap.yml,使用application.yml不行（因为bootstrap.yml会在程序启动时加载）
+ 手动刷新配置需要actuator，通过post调用refresh刷新配置