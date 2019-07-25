package club.lightingsummer.movie.film.api.api;

import club.lightingsummer.movie.film.api.vo.ActorVO;
import club.lightingsummer.movie.film.api.vo.FilmDescVO;
import club.lightingsummer.movie.film.api.vo.ImgVO;

import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/25 0001
 * @description：
 */
public interface FilmAsyncServiceApi {

    // 获取影片描述信息
    FilmDescVO getFilmDescAsync(String filmId);

    // 获取图片信息
    ImgVO getImgsAsync(String filmId);

    // 获取导演信息
    ActorVO getDectInfoAsync(String filmId);

    // 获取演员信息
    List<ActorVO> getActorsAsync(String filmId);

}