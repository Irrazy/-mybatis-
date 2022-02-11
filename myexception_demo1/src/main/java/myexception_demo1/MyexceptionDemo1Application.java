package myexception_demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "myexception_demo1.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MyexceptionDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(MyexceptionDemo1Application.class, args);
    }

}
