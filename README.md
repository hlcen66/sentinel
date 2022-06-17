# sentinel
sentinel修改版，完美支持nacos双向同步，并且支持nacos配置参数传入。

启动时必须指定nacos的服务地址，名称空间，group3个参数，不然无法启动。

参数指定事例：<br>
-Dcustomer.nacos.server=yourNacosServerAddr <br>
-Dcustomer.nacos.namespace=yourNamespaceId<br>
-Dcustomer.nacos.group=yourGroup

参数较多，建议使用脚本启动.

脚本下载：
<a href="sentinel.sh" target="_blank">sentinel.sh</a>

脚本用法：
./sentinel.sh start | stop | restart

jar包下载：
<a href="sentinel-dashboard-chl6.jar" target="_blank">sentinel-dashboard.jar</a>