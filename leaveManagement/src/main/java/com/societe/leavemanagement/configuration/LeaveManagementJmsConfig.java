package com.societe.leavemanagement.configuration;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.atomikos.jms.AtomikosConnectionFactoryBean;

/**
 * Classe de configuration de la partie transactionnel de la partie JMS.
 * 
 * @author salah
 * 
 */
@Configuration
@DependsOn("transactionManager")
@EnableJms
public class LeaveManagementJmsConfig {

	@Value("${jms.broker-url}")
	private String jmsBrokerUrl;

	@Value("${jms.user}")
	private String jmsUser;

	@Value("${jms.password}")
	private String jmsPassword;

	@Bean
	public ActiveMQXAConnectionFactory xaFactory() {
		ActiveMQXAConnectionFactory factory = new ActiveMQXAConnectionFactory();
		factory.setBrokerURL(jmsBrokerUrl);
		factory.setUserName(jmsUser);
		factory.setPassword(jmsPassword);

		return factory;
	}

	public ConnectionFactory connectionFactory() {
		AtomikosConnectionFactoryBean bean = new AtomikosConnectionFactoryBean();
		bean.setUniqueResourceName("xa");
		bean.setXaConnectionFactory(xaFactory());
		return bean;
	}

	@Bean("jmsQueueTemplate")
	public JmsTemplate jmsQueueTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setSessionTransacted(true);
		return jmsTemplate;
	}

	@Bean("jmsTopicTemplate")
	public JmsTemplate jmsTopicTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
}
