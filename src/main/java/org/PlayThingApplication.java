package org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class PlayThingApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(PlayThingApplication.class, args);
    }
}
