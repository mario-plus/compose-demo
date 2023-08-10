#!/bin/sh
mkdir -p /home/data/volumes/mysql/conf
chmod 777 /home/data/volumes/mysql/conf
mkdir -p /home/data/volumes/mysql/data
chmod 777 /home/data/volumes/mysql/data
echo '创建mysql数据卷完成'
mkdir -p /home/data/volumes/redis/data
chmod 777 /home/data/volumes/redis/data
mkdir -p /home/data/volumes/redis/redis.conf
chmod 777 /home/data/volumes/redis/redis.conf
echo '创建redis数据卷完成'
echo '开始创建compose服务镜像'
docker build -f /home/compose/Dockerfile -t /home/compose-demo/
echo '构建compose镜像成功'
echo '开始创建容器'
docker compose up -d
echo '创建容器成功'

