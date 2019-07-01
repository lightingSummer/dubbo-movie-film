package club.lightingsummer.movie.film.api.api;

import club.lightingsummer.movie.film.api.vo.BannerVO;
import club.lightingsummer.movie.film.api.vo.FilmInfoVO;
import club.lightingsummer.movie.film.api.vo.FilmVO;

import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/1 0001
 * @description：
 */
public interface FilmInfoAPI {

    // 获取banners
    List<BannerVO> getBanners();

    // 获取热映影片
    FilmVO getHotFilms(boolean isLimit, int nums);

    // 获取即将上映影片[受欢迎程度做排序]
    FilmVO getSoonFilms(boolean isLimit, int nums);

    // 获取票房排行榜
    List<FilmInfoVO> getBoxRanking();

    // 获取人气排行榜
    List<FilmInfoVO> getExpectRanking();

    // 获取Top100
    List<FilmInfoVO> getTop();
}
