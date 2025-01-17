package com.ogms.scenario;

/**
 * @name: DBTestApplication
 * @description: SpringBoot启动类
 * @author: Lingkai Shi
 * @date: 4/10/2024 5:27 PM
 * @version: 1.0
 */

import com.ogms.scenario.domain.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j // Lombok注解，简化日志记录器的创建，自动生成getter、setter、构造函数、toString等方法
@MapperScan("com.ogms.*.mapper") // MyBatis提供的，MapperScan操作数据库对象映射
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 入口启动类
@EnableTransactionManagement // 事务管理器配置；例如操作数据库的方法失败实现回滚操作保障数据的一致性
public class ScenarioApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(ScenarioApplication.class, args);
        log.info(ScenarioApplication.class.getName() + " Started Successfully.");
    }
}