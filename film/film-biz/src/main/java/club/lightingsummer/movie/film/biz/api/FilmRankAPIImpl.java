package club.lightingsummer.movie.film.biz.api;

import club.lightingsummer.movie.film.api.api.FilmRankAPI;
import club.lightingsummer.movie.film.api.enums.ResponseStatus;
import club.lightingsummer.movie.film.api.po.Film;
import club.lightingsummer.movie.film.api.vo.CommonResponse;
import club.lightingsummer.movie.film.api.vo.FilmInfoVO;
import club.lightingsummer.movie.film.biz.utils.VOUtil;
import club.lightingsummer.movie.film.dal.dao.FilmMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/2 0002
 * @description：
 */
@Component
@Service(interfaceClass = FilmRankAPI.class, loadbalance = "roundrobin")
public class FilmRankAPIImpl implements FilmRankAPI {
    private static final Logger logger = LoggerFactory.getLogger(FilmRankAPIImpl.class);

    @Autowired
    private FilmMapper filmMapper;

    /**
     * @author: lightingSummer
     * @date: 2019/7/2 0002
     * @description: 获取票房排行榜 10名
     *  条件 -> 正在上映的，票房前10名
     * @return club.lightingsummer.movie.film.api.vo.CommonResponse<java.util.List < club.lightingsummer.movie.film.api.vo.FilmInfoVO>>
     */
    @Override
    public CommonResponse<List<FilmInfoVO>> getBoxRanking() {
        CommonResponse<List<FilmInfoVO>> commonResponse = CommonResponse.success(List.class);
        List<FilmInfoVO> filmInfoVOs;
        try {
            PageHelper.startPage(1, 10, "film_box_office");
            //正在上映 status = 1
            List<Film> films = filmMapper.selectByFilmStatus(1);
            filmInfoVOs = VOUtil.getFilmInfoVOs(films);
        } catch (Exception e) {
            logger.error("获取票房排行榜失败" + e.getMessage());
            return CommonResponse.fail(List.class, ResponseStatus.SYSTEM_ERROR);
        }
        commonResponse.setData(filmInfoVOs);
        return commonResponse;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/2 0002
     * @description: 获取新片预售排行榜
     * 条件 -> 即将上映的，预售前10名
     * @return club.lightingsummer.movie.film.api.vo.CommonResponse<java.util.List < club.lightingsummer.movie.film.api.vo.FilmInfoVO>>
     */
    @Override
    public CommonResponse<List<FilmInfoVO>> getExpectRanking() {
        CommonResponse<List<FilmInfoVO>> commonResponse = CommonResponse.success(List.class);
        List<FilmInfoVO> filmInfoVOs;
        try {
            PageHelper.startPage(1, 10, "film_preSaleNum");
            //即将上映 status = 2
            List<Film> films = filmMapper.selectByFilmStatus(2);
            filmInfoVOs = VOUtil.getFilmInfoVOs(films);
        } catch (Exception e) {
            logger.error("获取新片预售排行榜" + e.getMessage());
            return CommonResponse.fail(List.class, ResponseStatus.SYSTEM_ERROR);
        }
        commonResponse.setData(filmInfoVOs);
        return commonResponse;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/2 0002
     * @description: 条件 -> 正在上映的，评分前10
     * @return club.lightingsummer.movie.film.api.vo.CommonResponse<java.util.List < club.lightingsummer.movie.film.api.vo.FilmInfoVO>>
     */
    @Override
    public CommonResponse<List<FilmInfoVO>> getTop() {
        CommonResponse<List<FilmInfoVO>> commonResponse = CommonResponse.success(List.class);
        List<FilmInfoVO> filmInfoVOs;
        try {
            PageHelper.startPage(1, 10, "film_score");
            //正在上映 status = 1
            List<Film> films = filmMapper.selectByFilmStatus(1);
            filmInfoVOs = VOUtil.getFilmInfoVOs(films);
        } catch (Exception e) {
            logger.error("获取新片预售排行榜" + e.getMessage());
            return CommonResponse.fail(List.class, ResponseStatus.SYSTEM_ERROR);
        }
        commonResponse.setData(filmInfoVOs);
        return commonResponse;
    }
}
