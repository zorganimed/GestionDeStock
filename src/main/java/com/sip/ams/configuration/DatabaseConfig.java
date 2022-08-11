package com.sip.ams.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import com.zaxxer.hikari.*;


@Configuration
public class DatabaseConfig {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	public DataSource datSource() {

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dbUrl);
		return new HikariDataSource(config);
	}

}
