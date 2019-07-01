package club.lightingsummer.movie.film.biz;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/1 0001
 * @description：
 */
@SpringBootApplication(scanBasePackages = "club.lightingsummer.movie.film")
@MapperScan("club.lightingsummer.movie.film.dal.dao")
@EnableDubboConfiguration
public class FilmBziMain {

    public static void main(String[] args) {
        SpringApplication.run(FilmBziMain.class, args);
    }

}
