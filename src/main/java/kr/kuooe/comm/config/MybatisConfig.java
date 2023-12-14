package kr.kuooe.comm.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

	@Value("${spring.datasource.emobile.jdbcType}")
	String jdbcType;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	@Qualifier(value = "emobileDataSource")
	private DataSource emobileDataSource;
	
	
	@Bean
	public SqlSessionFactory emobileSqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(emobileDataSource);
		
		/* 맵퍼 xml 파일 경로 설정 */
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:sqlmap/Main/*Mapper.xml"));
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:sqlmap/mybatis-config.xml"));
		
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		
		/* 실제DB컬럼명 스네이크 표기법 = 카멜케이스 표기법 맵핑 */
		sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		
		return sqlSessionFactory;
	}
	
	@Bean
    public SqlSession emobileSqlSession() throws Exception {
        return new SqlSessionTemplate(emobileSqlSessionFactoryBean());
    }
}
