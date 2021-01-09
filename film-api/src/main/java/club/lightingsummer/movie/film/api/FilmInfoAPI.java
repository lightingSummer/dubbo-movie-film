package club.lightingsummer.movie.film.api;

import club.lightingsummer.movie.film.vo.*;

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
    CommonResponse<FilmVO> getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    // 获取即将上映影片[受欢迎程度做排序]
    CommonResponse<FilmVO> getSoonFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    // 获取经典影片
    CommonResponse<FilmVO> getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    // 获取影片条件接口--分类条件
    List<CatVO> getCats();

    // 获取影片条件接口--片源条件
    List<SourceVO> getSources();

    // 获取影片条件接口--获取年代条件
    List<YearVO> getYears();

    // 获取影片详细信息
    FilmDetailVO getFilmDetail(int searchType, String searchParam);

    // 获取影片描述信息
    FilmDescVO getFilmDesc(String filmId);

    // 获取图片信息
    ImgVO getImgs(String filmId);

    // 获取导演信息
    ActorVO getDectInfo(String filmId);

    // 获取演员信息
    List<ActorVO> getActors(String filmId);
}
