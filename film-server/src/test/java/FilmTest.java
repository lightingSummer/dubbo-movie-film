import club.lightingsummer.movie.film.FilmBizMain;
import club.lightingsummer.movie.film.api.FilmInfoAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/23 0023
 * @description：
 */
@SpringBootTest(classes = FilmBizMain.class)
@RunWith(SpringRunner.class)
public class FilmTest {
    @Autowired
    private FilmInfoAPI filmInfoAPI;

    @Test
    public void Test() {
        System.out.println(filmInfoAPI.getCats());
        System.out.println(filmInfoAPI.getSources());
        System.out.println(filmInfoAPI.getYears());
    }

}
