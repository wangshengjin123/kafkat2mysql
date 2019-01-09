# flink-nginx-kafkat2mysql
使用flink代码消费kafka写到mysql，日志是规范好的nginx日志

/n

nginx日志格式配置好
    log_format json '{ "@timestamp": "$time_iso8601", '
                         '"time": "$time_iso8601", '
                         '"remote_addr": "$remote_addr", '
                         '"remote_user": "$remote_user", '
                         '"body_bytes_sent": "$body_bytes_sent", '
                         '"request_time": "$request_time", '
                         '"status": "$status", '
                         '"host": "$host", '
                         '"request": "$request", '
                         '"request_method": "$request_method", '
                         '"uri": "$uri", '
                         '"http_referrer": "$http_referer", '
                         '"body_bytes_sent":"$body_bytes_sent", '
                         '"http_x_forwarded_for": "$http_x_forwarded_for", '
                         '"http_user_agent": "$http_user_agent" '
                    '}';
   access_log  /var/log/nginx/access.log  json;
  
  
 /n

mysql创建表语句
CREATE TABLE `nginx` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `request_method` varchar(8) NOT NULL DEFAULT '-',
  `status` int(11) NOT NULL DEFAULT '0',
  `request_time` decimal(10,3) NOT NULL DEFAULT '0.000',
  `remote_user` varchar(16) NOT NULL DEFAULT '-',
  `http_referrer` varchar(1028) NOT NULL DEFAULT '-',
  `request` varchar(1028) NOT NULL DEFAULT '-',
  `uri` varchar(128) NOT NULL DEFAULT '-',
  `remote_addr` varchar(16) NOT NULL DEFAULT '-',
  `body_bytes_sent` int(11) NOT NULL DEFAULT '0',
  `http_x_forwarded_for` varchar(128) NOT NULL DEFAULT '-',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `http_user_agent` varchar(1028) NOT NULL DEFAULT '-',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=7318 DEFAULT CHARSET=utf8；


代码打包好之后，传到flink的页面执行，日志会插入到mysql
