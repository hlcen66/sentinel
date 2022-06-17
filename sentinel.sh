#!/bin/sh
export LANG="en_US.UTF-8"
PID=$(ps -ef | grep sentinel-dashboard-chl6.jar | grep -v grep | awk '{ print $2 }')
case "$1" in
   start)
   echo "sentinel ready start..."
   nohup java -Dserver.port=8090 -Dcsp.sentinel.dashboard.server=ip:port -Dsentinel.dashboard.auth.username=zhangsan -Dsentinel.dashboard.auth.password=123 -Dproject.name=sentinel-dashboard -Dcustomer.nacos.server=yournacosserver -Dcustomer.nacos.namespace=yournamespace -Dcustomer.nacos.group=yourgroup -jar /data/sentinel-dashboard-chl.jar > /data/sentinel-logs/sentinel-dashboard.txt 2>&1 & echo $! > pid.txt
   sleep 1
   PIDNEW=$(cat pid.txt)
   echo "sentinel is started now,start pid is ${PIDNEW}."
   ;;
  stop)
    echo "ready stop sentinel ,pid is ${PID}..."
    kill -9 ${PID}
    echo "sentinel is stopped."
    ;;
  restart)
    echo "sentinel ready restart..."
    PID=$(cat pid.txt)
    kill -9 $PID
    sleep 1;
    echo "${PID} is stop,ready start a new process..."
    nohup java -Dserver.port=8090 -Dcsp.sentinel.dashboard.server=ip:port -Dsentinel.dashboard.auth.username=zhangsan -Dsentinel.dashboard.auth.password=123 -Dproject.name=sentinel-dashboard -Dcustomer.nacos.server=yournacosserver -Dcustomer.nacos.namespace=yournamespace -Dcustomer.nacos.group=yourgroup -jar /data/sentinel-dashboard-chl.jar > /data/sentinel-logs/sentinel-dashboard.txt 2>&1 & echo $! > pid.txt
    sleep 1
    PIDNEW=$(cat pid.txt)
    echo "sentinel is started now,restart pid is ${PIDNEW}"
    ;;

  *)
    echo "Usage $0 {start|stop|restart}"
    ;;
esac
exit 0
