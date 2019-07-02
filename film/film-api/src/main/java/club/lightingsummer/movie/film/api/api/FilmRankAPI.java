package club.lightingsummer.movie.film.api.api;

import club.lightingsummer.movie.film.api.vo.CommonResponse;
import club.lightingsummer.movie.film.api.vo.FilmInfoVO;

import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/2 0002
 * @description：
 */
public interface FilmRankAPI {

    // 获取票房排行榜
    CommonResponse<List<FilmInfoVO>> getBoxRanking();

    // 获取新片预售排行榜
    CommonResponse<List<FilmInfoVO>> getExpectRanking();

    // 获取评分排行榜
    CommonResponse<List<FilmInfoVO>> getTop();

}
