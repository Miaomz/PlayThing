package org.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author miaomuzhi
 * @since 2018/7/18
 */

@EnableScheduling
@SpringBootApplication
@RestController
@EntityScan(basePackages = "org.application.po")
public class PlaythingApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(PlaythingApplication.class, args);
    }
}
