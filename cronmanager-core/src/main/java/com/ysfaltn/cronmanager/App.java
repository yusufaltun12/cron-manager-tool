package com.ysfaltn.cronmanager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ysfaltn.cronmanager.entity.JobEntity;
import com.ysfaltn.cronmanager.repository.JobRepository;

 
@Configuration
@ComponentScan
public class App
{
   public static void main( String[] args ) 
   {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");
        JobRepository j = context.getBean(JobRepository.class);

        Iterable<JobEntity> entities = j.findAll();

        

        for (JobEntity entity: entities)
        {


        }

    }
}
