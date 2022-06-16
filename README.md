# sentinel
sentinel修改版，完美支持nacos双向同步，并且支持nacos配置参数传入。

启动时请务必指定nacos的服务地址，名称空间，groupId这些参数，不然无法启动。

参数指定事例：

-Dcustomer.nacos.server=yournacos:8849 <br>-Dcustomer.nacos.namespace=9396c1aa-4b4a-4f37-9a26-d26c788ed618<br> -Dcustomer.nacos.group=your-group
