# sentinel
####一：说明

sentinel修改版。<br>

#####1：完美支持nacos双向同步持久化<br>

#####2：通过shell脚本动态连接你的nacos环境以及其他参数配置，无需改动源码配置


####二：下载文件
1：下载shell脚本：sentinel.sh<br>

2：下载 sentinel-dashboard.jar 包<br>

脚本下载:
[sentinel.sh](https://github.com/hlcen66/sentinel/releases/download/1.0/sentinel.sh)

jar包下载:
[sentinel-dashboard.jar](https://github.com/hlcen66/sentinel/releases/download/1.0/sentinel-dashboard.jar)

####三：如何使用
1：打开脚本文件修改一下配置：
```powershell
#!/bin/sh
export LANG="en_US.UTF-8"

#sentinel控制台访问地址,ip:port或域名
export WEBADDR=

#nacos-server地址
export NACOSSERVER=

#nacos-命名空间
export NAMESPACE=

#nacos-group
export NACOSGROUP=

#sentinel控制台-用户名
export USERNAME=

#sentinel控制台-密码
export PASSWORD=

#sentinel-dashboard.jar所存放的路径,
export JARPATH=/data

#sentinel日志输出路径
export LOGDIR=/data/sentinel-logs
```
2：脚本使用方法：
```powershell
./sentinel.sh  start | stop | restart
```
启动即可。