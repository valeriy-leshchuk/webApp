package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class AppConfig
{
    @Configuration
    @ComponentScan(basePackages = {"components"})
    public class ApplicationConfig
    {
    }
}
