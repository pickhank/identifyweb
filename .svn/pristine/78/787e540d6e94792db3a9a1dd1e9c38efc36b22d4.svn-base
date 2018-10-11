#!/bin/sh

CURPATH=$(dirname $0)
echo "$(date +'%Y-%m-%d %H:%M') run $0" >> ${CURPATH}/deploy.log

echo "running svn up "
svn up

echo "Begin to deploy to ${TOMCAT_HOME}"
mvn_build_cmd="mvn clean package -P${MVN_MODE}"

echo "mvn_build_cmd: [$mvn_build_cmd]"
result=$(${mvn_build_cmd})
echo "result: [$result]"

TOMCAT_PIDS=$(ps -ef|grep -E "tomcat|cronolog|catalina" |grep "${TOMCAT_HOME}" | grep -v grep| awk '{print $2}'| xargs echo)
echo "TOMCAT_PIDS: [kill -9 $TOMCAT_PIDS]"
kill -9 $TOMCAT_PIDS

echo "running: rm -rf ${TOMCAT_HOME}/webapps/dgateway*"
rm -rf ${TOMCAT_HOME}/webapps/dgateway*

echo "running: cp ${CURPATH}/target/dgateway.war ${TOMCAT_HOME}/webapps/"
cp ${CURPATH}/target/dgateway.war ${TOMCAT_HOME}/webapps/

sh ${TOMCAT_HOME}/bin/startup.sh
echo "Done to deploy ${TOMCAT_HOME}"

exit 0
