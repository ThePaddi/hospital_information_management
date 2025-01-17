package com.lcy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class HospitalInformationManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalInformationManagementApplication.class);
        log.info("项目启动成功！");
    }
}
