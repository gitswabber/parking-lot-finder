package com.parking.lot.api.configuration;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@EnableScheduling
@EnableBatchProcessing
@Configuration
public class BatchConfiguration implements BatchConfigurer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public JobRepository getJobRepository() throws Exception {
        final JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(getTransactionManager());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Override
    public JobLauncher getJobLauncher() throws Exception {
        final SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(getJobRepository());
        simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        simpleJobLauncher.afterPropertiesSet();
        return simpleJobLauncher;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
        final JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
        jobExplorerFactoryBean.setDataSource(dataSource);
        jobExplorerFactoryBean.setTablePrefix("JOB-EXECUTOR");
        jobExplorerFactoryBean.afterPropertiesSet();
        return jobExplorerFactoryBean.getObject();
    }
}
