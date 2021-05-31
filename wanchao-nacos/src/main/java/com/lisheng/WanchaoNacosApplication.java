package com.lisheng;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@NacosConfigurationProperties(dataId = "example",autoRefreshed = true)
@EnableDiscoveryClient
public class WanchaoNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(WanchaoNacosApplication.class, args);
    }

}
