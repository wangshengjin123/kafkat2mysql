package com.djcps.main;

import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import com.alibaba.fastjson.JSON;
import com.djcps.model.NginxModel;
import com.djcps.sink.MySQLSink;

public class ReadingFromKafkaToMysql {
	 private static String ZOOKEEPER_HOST = "192.168.23.170:33471";
	    private static String KAFKA_BROKER = "192.168.23.170:38260";
//	    private static String TRANSACTION_GROUP = UUID.randomUUID().toString();
	    private static String TRANSACTION_GROUP = "ReadingFromKafkaToMysql";

	    public static void main(String[] args) throws Exception {
	        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
	        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
	        env.enableCheckpointing(1000);
	        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
	        Properties kafkaProps = new Properties();
	        kafkaProps.setProperty("zookeeper.connect", ZOOKEEPER_HOST);
	        kafkaProps.setProperty("bootstrap.servers", KAFKA_BROKER);
	        kafkaProps.setProperty("group.id", TRANSACTION_GROUP);
	        kafkaProps.put("enable.auto.commit", "true");
	        kafkaProps.put("auto.commit.interval.ms", "1000");
	        kafkaProps.put("session.timeout.ms", "30000");
	        kafkaProps.put("auto.offset.reset", "earliest");
	        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        DataStreamSource<String> dataStreamSource = env.addSource(new FlinkKafkaConsumer011<>("nginxlog1", new SimpleStringSchema(), kafkaProps));
	        dataStreamSource.filter(new FilterFunction<String>() {
				private static final long serialVersionUID = 1L;

				public boolean filter(String value) throws Exception{
					return StringUtils.isNotBlank(value);
	        	};
	        } ).map(new MapFunction<String, NginxModel>(){
				private static final long serialVersionUID = 1L;

				@Override
				public NginxModel map(
						String value) throws Exception {
					NginxModel nginxModel = JSON.parseObject(value, NginxModel.class);
					return nginxModel;
				}
	        	
	        })
	        .addSink(new MySQLSink());
	        env.execute();

	    }
}
