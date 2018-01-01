package io.egen;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan(basePackages = { "io.egen", "io.egen.controller", "io.egen.exception", "io.egen.entity" })
@EnableWebMvc
public class App 
{

}
