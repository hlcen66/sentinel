# sentinel
sentinel修改版，完美支持nacos双向同步，并且支持nacos配置参数传入。

启动时必须指定nacos的服务地址，名称空间，group3个参数，不然无法启动。

参数指定事例：<br>
-Dcustomer.nacos.server=yourNacosServerAddr <br>
-Dcustomer.nacos.namespace=yourNamespaceId<br>
-Dcustomer.nacos.group=yourGroup

参数较多，建议使用脚本启动.

脚本下载:
[sentinel.sh](https://github.com/hlcen66/sentinel/releases/download/1.0/sentinel.sh)

jar包下载:
[sentinel-dashboard.jar](https://github.com/hlcen66/sentinel/releases/download/1.0/sentinel-dashboard-chl.jar)
