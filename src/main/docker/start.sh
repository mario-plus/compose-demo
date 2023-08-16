#!/bin/sh
if [ ! -d "/home/data/volumes/mysql" ]; then
mkdir -p /home/data/volumes/mysql/conf
chmod 777 /home/data/volumes/mysql/conf
mkdir -p /home/data/volumes/mysql/data
chmod 777 /home/data/volumes/mysql/data
echo '创建mysql数据卷完成'
else
echo 'mysql数据卷存在，使用原有数据卷创建容器'
fi

if [ ! -d "/home/data/volumes/redis" ]; then
mkdir -p /home/data/volumes/redis/data
chmod 777 /home/data/volumes/redis/data
mkdir -p /home/data/volumes/redis/redis.conf
chmod 777 /home/data/volumes/redis/redis.conf
echo '创建redis数据卷完成'
else
echo 'Redis数据卷存在，使用原有数据卷创建容器'
fi

echo '开始创建compose服务镜像'
docker build -f /home/compose/Dockerfile -t compose-demo /home/compose/
echo '构建compose镜像成功'
echo '开始创建容器'
docker compose up -d
echo '创建容器成功'

