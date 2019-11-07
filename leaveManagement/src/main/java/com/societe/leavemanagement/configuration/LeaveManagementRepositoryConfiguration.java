package com.societe.leavemanagement.configuration;

import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.societe.leavemanagement.repository.CollaborateurRepository;
import com.societe.leavemanagement.repository.CongeRepository;
import com.societe.leavemanagement.repository.LeaveManagementDatasourceProperties;

/**
 * Classe de configuration de la partie transactionnel des repositries
 * {@link CollaborateurRepository} et {@link CongeRepository}.
 * 
 * @author salah
 * 
 */
@Configuration
@PropertySource({ "classpath:leavemanagement.properties" })
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.societe.leavemanagement.repository", entityManagerFactoryRef = "leavemanagementEntityManager", transactionManagerRef = "transactionManager")
public class LeaveManagementRepositoryConfiguration {

	private static Logger logger = LoggerFactory.getLogger(LeaveManagementRepositoryConfiguration.class);

	/*
	 * 
	 */
	private JpaVendorAdapter jpaVendorAdapter;
	/*
	 * 
	 */
	private LeaveManagementDatasourceProperties leavemanagementDatasourceProperties;

	/**
	 * 
	 * @param jpaVendorAdapter
	 * @param leavemanagementDatasourceProperties
	 */
	public LeaveManagementRepositoryConfiguration(JpaVendorAdapter jpaVendorAdapter,
			LeaveManagementDatasourceProperties leavemanagementDatasourceProperties) {
		super();
		this.jpaVendorAdapter = jpaVendorAdapter;
		this.leavemanagementDatasourceProperties = leavemanagementDatasourceProperties;
	}

	@Bean
	public TomcatServletWebServerFactory tomcatFactory() {
		logger.info("initializing tomcat factory... ");
		return new TomcatServletWebServerFactory() {
			@Override
			protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
				tomcat.enableNaming();
				return new TomcatWebServer(tomcat, getPort() >= 0);
			}

			@Override
			protected void postProcessContext(Context context) {
				logger.info("initializing tomcat factory JDNI ... ");
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/leavemanagement");
				resource.setType("com.mysql.cj.jdbc.MysqlXADataSource");
				resource.setProperty("factory", "com.mysql.cj.jdbc.MysqlDataSourceFactory");
				resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
				resource.setProperty("url", leavemanagementDatasourceProperties.getUrl()+"&user=root&password=Salah2802178");
				resource.setProperty("username", leavemanagementDatasourceProperties.getUsername());
				resource.setProperty("password", leavemanagementDatasourceProperties.getPassword());
				resource.setProperty("explicitUrl", "true");
				resource.setProperty("pinGlobalTxToPhysicalConnection", "true");
				context.getNamingResources().addResource(resource);
			}
		};
	}

	@Bean(name = "mysqlXaDataSource", destroyMethod = "")
	public XADataSource jndiDataSource() throws NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:comp/env/jdbc/leavemanagement");
		bean.setProxyInterface(XADataSource.class);
		bean.setLookupOnStartup(true);
		bean.afterPropertiesSet();
		return (XADataSource) bean.getObject();
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	@DependsOn(value = "mysqlXaDataSource")
	@Bean(name = "leavemanagementDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource orderDataSource(@Qualifier("mysqlXaDataSource") XADataSource mysqlXaDataSource) {
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName("xads2");
		return xaDataSource;
	}

	/**
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "leavemanagementEntityManager")
	public LocalContainerEntityManagerFactoryBean orderEntityManager(@Qualifier("leavemanagementDataSource") DataSource dataSource) {

		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.societe.leavemanagement.entities");
		entityManager.setPersistenceUnitName("leavemanagementPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
