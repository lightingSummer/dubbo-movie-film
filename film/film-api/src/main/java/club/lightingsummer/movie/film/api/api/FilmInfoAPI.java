package club.lightingsummer.movie.film.api.api;

import club.lightingsummer.movie.film.api.vo.*;

import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/1 0001
 * @description：
 */
public interface FilmInfoAPI {

    // 获取banners
    CommonResponse<List<BannerVO>> getBanners();

    // 获取热映影片
    CommonResponse<FilmVO> getHotFilms(boolean isLimit, int nums);

    // 获取即将上映影片[受欢迎程度做排序]
    CommonResponse<FilmVO> getSoonFilms(boolean isLimit, int nums);

    // 获取影片条件接口--分类条件
    List<CatVO> getCats();

    // 获取影片条件接口--片源条件
    List<SourceVO> getSources();

    // 获取影片条件接口--获取年代条件
    List<YearVO> getYears();
}
