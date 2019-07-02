package club.lightingsummer.movie.film.biz.api;

import club.lightingsummer.movie.film.api.api.FilmInfoAPI;
import club.lightingsummer.movie.film.api.enums.ResponseStatus;
import club.lightingsummer.movie.film.api.po.Banner;
import club.lightingsummer.movie.film.api.po.Film;
import club.lightingsummer.movie.film.api.vo.BannerVO;
import club.lightingsummer.movie.film.api.vo.CommonResponse;
import club.lightingsummer.movie.film.api.vo.FilmInfoVO;
import club.lightingsummer.movie.film.api.vo.FilmVO;
import club.lightingsummer.movie.film.biz.utils.VOUtil;
import club.lightingsummer.movie.film.dal.dao.BannerMapper;
import club.lightingsummer.movie.film.dal.dao.FilmMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/2 0002
 * @description：
 */
@Component
@Service(interfaceClass = FilmInfoAPI.class, loadbalance = "roundrobin")
public class FilmInfoAPIImpl implements FilmInfoAPI {
    private static final Logger logger = LoggerFactory.getLogger(FilmInfoAPIImpl.class);

    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private BannerMapper bannerMapper;

    /**
     * @author: lightingSummer
     * @date: 2019/7/2 0002
     * @description: 获取banners
     * @return club.lightingsummer.movie.film.api.vo.CommonResponse<java.util.List < club.lightingsummer.movie.film.api.vo.BannerVO>>
     */
    @Override
    public CommonResponse<List<BannerVO>> getBanners() {
        CommonResponse<List<BannerVO>> commonResponse = CommonResponse.success(List.class);
        List<BannerVO> res = new ArrayList<>();
        try {
            List<Banner> banners = bannerMapper.selectBannerList();
            for (Banner banner : banners) {
                BannerVO bannerVO = new BannerVO();
                bannerVO.setBannerId(banner.getUuid() + "");
                bannerVO.setBannerUrl(banner.getBannerUrl());
                bannerVO.setBannerAddress(banner.getBannerAddress());
                res.add(bannerVO);
            }
        } catch (Exception e) {
            logger.error("获取banner失败" + e.getMessage());
            return CommonResponse.fail(List.class, ResponseStatus.SYSTEM_ERROR);
        }
        commonResponse.setData(res);
        return commonResponse;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/2 0002
     * @description: 获取正在热映的电影
     * @param isLimit
     * @param nums
     * @return club.lightingsummer.movie.film.api.vo.CommonResponse<club.lightingsummer.movie.film.api.vo.FilmVO>
     */
    @Override
    public CommonResponse<FilmVO> getHotFilms(boolean isLimit, int nums) {
        CommonResponse<FilmVO> commonResponse = CommonResponse.success(FilmVO.class);
        FilmVO filmVO = new FilmVO();
        // 热映影片的限制条件 film_status 1
        try {
            //用于首页的查询
            if (isLimit) {
                PageHelper.startPage(1, nums);
                List<Film> films = filmMapper.selectByFilmStatus(1);
                List<FilmInfoVO> filmInfoVOs = VOUtil.getFilmInfoVOs(films);
                filmVO.setFilmInfoVO(filmInfoVOs);
                filmVO.setFilmNum(filmInfoVOs.size());
            } else {
                //用于列表的查询

            }
        } catch (Exception e) {
            logger.error("查询热映影片出错" + e);
            return CommonResponse.fail(FilmVO.class, ResponseStatus.SYSTEM_ERROR);
        }
        commonResponse.setData(filmVO);
        return commonResponse;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/2 0002
     * @description: 获取即将上映影片[受欢迎程度做排序]
     * @param isLimit
     * @param nums
     * @return club.lightingsummer.movie.film.api.vo.CommonResponse<club.lightingsummer.movie.film.api.vo.FilmVO>
     */
    @Override
    public CommonResponse<FilmVO> getSoonFilms(boolean isLimit, int nums) {
        CommonResponse<FilmVO> commonResponse = CommonResponse.success(FilmVO.class);
        FilmVO filmVO = new FilmVO();
        // 即将上映影片的限制条件 film_status = 2
        try {
            //用于首页的查询
            if (isLimit) {
                PageHelper.startPage(1, nums);
                List<Film> films = filmMapper.selectByFilmStatus(2);
                List<FilmInfoVO> filmInfoVOs = VOUtil.getFilmInfoVOs(films);
                filmVO.setFilmInfoVO(filmInfoVOs);
                filmVO.setFilmNum(filmInfoVOs.size());
            } else {
                //用于列表的查询

            }
        } catch (Exception e) {
            logger.error("查询即将上映影片出错" + e);
            return CommonResponse.fail(FilmVO.class, ResponseStatus.SYSTEM_ERROR);
        }
        commonResponse.setData(filmVO);
        return commonResponse;
    }

}

