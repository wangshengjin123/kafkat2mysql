package com.djcps.sink;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import com.djcps.model.NginxModel;


public class MySQLSink extends
RichSinkFunction<NginxModel> {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private PreparedStatement preparedStatement;
	String username = "xxxxxx";
	String password = "xxxxxx";
	String drivername = "com.mysql.jdbc.Driver";
	String dburl = "jdbc:mysql://192.168.23.172:3306/flink";
 
	@Override
	public void invoke(NginxModel nginxModel) throws Exception {
		Class.forName(drivername);
		connection = DriverManager.getConnection(dburl, username, password);
		String sql = "insert into nginx ( request_method, status, request_time, remote_user, http_referrer, request, uri, remote_addr, body_bytes_sent, http_x_forwarded_for, time, http_user_agent) values(?,?,?,?,?,?,?,?,?,?,?,?);" ;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nginxModel.getRequestMethod());
		preparedStatement.setInt(2, nginxModel.getStatus());
		preparedStatement.setBigDecimal(3, nginxModel.getRequestTime());
		preparedStatement.setString(4, nginxModel.getRemoteUser());
		preparedStatement.setString(5, nginxModel.getHttpReferrer());
		preparedStatement.setString(6, nginxModel.getRequest());
		preparedStatement.setString(7, nginxModel.getUri());
		preparedStatement.setString(8, nginxModel.getRemoteAddr());
		preparedStatement.setInt(9, nginxModel.getBodyBytesSent());
		preparedStatement.setString(10, nginxModel.getHttpXForwardedFor());
		preparedStatement.setTimestamp(11, nginxModel.getTime());
		preparedStatement.setString(12, nginxModel.getHttpUserAgent());
		preparedStatement.executeUpdate();
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
 
	}
}
