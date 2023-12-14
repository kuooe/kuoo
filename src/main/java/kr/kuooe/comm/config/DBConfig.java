package kr.kuooe.comm.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.emobile")
	public DataSource emobileDataSource() {
		return DataSourceBuilder.create().build();
	}
}
