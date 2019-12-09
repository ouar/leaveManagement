package com.societe.leavemanagement.configuration;

import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
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
@PropertySource({ "classpath:leaveManagement.properties" })
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.societe.leavemanagement.repository", entityManagerFactoryRef = "leavemanagementEntityManager", transactionManagerRef = "transactionManager")
public class LeaveManagementRepositoryConfiguration {
	
	/**
	 * 
	 * @param leavemanagementDatasourceProperties
	 * @return
	 * @throws SQLException
	 */
	@Bean(name = "leavemanagementDataSource", initMethod = "init", destroyMethod = "close")
	@Profile("prod")
	public DataSource configDataSource(
			@Autowired LeaveManagementDatasourceProperties leavemanagementDatasourceProperties) throws SQLException {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(leavemanagementDatasourceProperties.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(leavemanagementDatasourceProperties.getPassword());
		mysqlXaDataSource.setUser(leavemanagementDatasourceProperties.getUsername());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

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
	public LocalContainerEntityManagerFactoryBean configEntityManager(
			@Qualifier("leavemanagementDataSource") DataSource dataSource,
			@Autowired JpaVendorAdapter jpaVendorAdapter) {

		HashMap<String, String> properties = new HashMap<>();
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

	/**
	 * 
	 * @param leavemanagementDatasourceProperties
	 * @return
	 */
	@Bean(name = "leavemanagementDataSource")
	@Profile("test")
	public DataSource configMockDataSource(
			@Autowired LeaveManagementDatasourceProperties leavemanagementDatasourceProperties) {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url(leavemanagementDatasourceProperties.getUrl());
		dataSourceBuilder.username(leavemanagementDatasourceProperties.getUsername());
		dataSourceBuilder.password(leavemanagementDatasourceProperties.getPassword());
		return dataSourceBuilder.build();
	}

}
