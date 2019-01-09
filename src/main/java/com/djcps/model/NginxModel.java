package com.djcps.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class NginxModel {
	@JSONField(name = "request_method")
	public String requestMethod;
	public Integer status;
	@JSONField(name="request_time")
	public BigDecimal requestTime;
	@JSONField(name="remote_user")
	public String remoteUser;
	@JSONField(name = "http_referrer")
	public String httpReferrer;
	public String request;
	public String uri;
	@JSONField(name = "remote_addr")
	public String remoteAddr;
	@JSONField(name = "body_bytes_sent")
	public Integer bodyBytesSent;
	@JSONField(name = "http_x_forwarded_for")
	public String httpXForwardedFor;
	@JSONField(format="yyyy-MM-ddTHH:mm:ssZ")
	public Timestamp time;
	@JSONField(name="http_user_agent")
	public String httpUserAgent;
	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(BigDecimal requestTime) {
		this.requestTime = requestTime;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public String getHttpReferrer() {
		return httpReferrer;
	}

	public void setHttpReferrer(String httpReferrer) {
		this.httpReferrer = httpReferrer;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public Integer getBodyBytesSent() {
		return bodyBytesSent;
	}

	public void setBodyBytesSent(Integer bodyBytesSent) {
		this.bodyBytesSent = bodyBytesSent;
	}

	public String getHttpXForwardedFor() {
		return httpXForwardedFor;
	}

	public void setHttpXForwardedFor(String httpXForwardedFor) {
		this.httpXForwardedFor = httpXForwardedFor;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getHttpUserAgent() {
		return httpUserAgent;
	}

	public void setHttpUserAgent(String httpUserAgent) {
		this.httpUserAgent = httpUserAgent;
	}

	@Override
	public String toString() {
		return "NginxModel [requestMethod=" + requestMethod + ", status=" + status + ", requestTime=" + requestTime
				+ ", remoteUser=" + remoteUser + ", httpReferrer=" + httpReferrer + ", request=" + request + ", uri="
				+ uri + ", remoteAddr=" + remoteAddr + ", bodyBytesSent=" + bodyBytesSent + ", httpXForwardedFor="
				+ httpXForwardedFor + ", time=" + time + ", httpUserAgent=" + httpUserAgent + "]";
	}
	
	
}
