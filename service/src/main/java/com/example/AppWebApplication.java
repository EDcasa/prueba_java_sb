package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;
import java.util.Objects;
@SpringBootApplication
@EnableAsync
@Slf4j
public class AppWebApplication {

    /**
     * Main to run app.
     *
     * @param args args to pass app
     */
    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(AppWebApplication.class);
            app.run(args);
        } catch (Exception throwable) {
            if (!Objects.equals(throwable.getClass().getName(), "org.springframework.boot.devtools.restart.SilentExitExceptionHandler$SilentExitException") && log.isErrorEnabled()) {
                log.error(
                        "********************************Ha ocurrido una exception****************************");
            }
        }
    }

    /**
     * commandLineRunner.
     *
     * @param ctx the ctx
     * @return CommandLineRunner instance
     */
    @Bean
    public CommandLineRunner commandLineRunner(ListableBeanFactory ctx) {
        if (log.isDebugEnabled()) {
            log.debug("Beans Loaded by Spring Boot:{}", ctx.getBeanDefinitionCount());
        }
        return args -> {
            if (log.isDebugEnabled()) {
                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    log.debug("Bean:{}", beanName);
                }
            }
        };
    }

}
