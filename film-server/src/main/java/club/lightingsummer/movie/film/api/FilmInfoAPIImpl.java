package club.lightingsummer.movie.film.api;

import club.lightingsummer.movie.film.enums.ResponseStatus;
import club.lightingsummer.movie.film.mapper.*;
import club.lightingsummer.movie.film.po.*;
import club.lightingsummer.movie.film.utils.VOUtil;
import club.lightingsummer.movie.film.vo.*;
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
    @Autowired
    private YearDictMapper yearDictMapper;
    @Autowired
    private CatDictMapper catDictMapper;
    @Autowired
    private SourceMapper sourceMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private ActorMapper actorMapper;

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
     * @author lightingSummer
     * @date 2019/7/25 0025
     * @description 获取经典影片
     * @param nums
     * @param nowPage
     * @param sortId
     * @param sourceId
     * @param yearId
     * @param catId
     * @return club.lightingsummer.movie.film.api.vo.CommonResponse<club.lightingsummer.movie.film.api.vo.FilmVO>
     */
    @Override
    public CommonResponse<FilmVO> getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        CommonResponse<FilmVO> commonResponse = CommonResponse.success(FilmVO.class);
        FilmVO filmVO = new FilmVO();
        try {
            PageHelper.startPage(nowPage, nums);
            // 1-按热门排序，2-按时间排序，3-按评价排序
            switch (sortId) {
                case 1:
                    PageHelper.orderBy("film_box_office");
                    break;
                case 2:
                    PageHelper.orderBy("film_time");
                    break;
                case 3:
                    PageHelper.orderBy("film_score");
                    break;
                default:
                    PageHelper.orderBy("film_box_office");
                    break;
            }
            String catLimit = null;
            if (catId != 99) {
                catLimit = "#" + catId + "#";
            }
            // 查询经典影片
            List<Film> films = filmMapper.selectByFilmStatus(3, sourceId, yearId, catLimit);
            List<FilmInfoVO> filmInfoVOs = VOUtil.getFilmInfoVOs(films);
            filmVO.setFilmInfoVO(filmInfoVOs);
            filmVO.setFilmNum(filmInfoVOs.size());
            filmVO.setNowPage(nowPage);
            filmVO.setTotalPage((int) Math.ceil(films.size() / (1.0 * nums)));
        } catch (Exception e) {
            logger.error("查询经典影片出错" + e);
            return CommonResponse.fail(FilmVO.class, ResponseStatus.SYSTEM_ERROR);
        }
        commonResponse.setData(filmVO);
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
    public CommonResponse<FilmVO> getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        CommonResponse<FilmVO> commonResponse = CommonResponse.success(FilmVO.class);
        FilmVO filmVO = new FilmVO();
        // 热映影片的限制条件 film_status 1
        try {
            //用于首页的查询
            if (isLimit) {
                PageHelper.startPage(nowPage, nums);
                List<Film> films = filmMapper.selectByFilmStatus(1, 99, 99, null);
                List<FilmInfoVO> filmInfoVOs = VOUtil.getFilmInfoVOs(films);
                filmVO.setFilmInfoVO(filmInfoVOs);
                filmVO.setFilmNum(filmInfoVOs.size());
            } else {
                //用于列表的查询
                PageHelper.startPage(nowPage, nums);
                // 1-按热门排序，2-按时间排序，3-按评价排序
                switch (sortId) {
                    case 1:
                        PageHelper.orderBy("film_box_office");
                        break;
                    case 2:
                        PageHelper.orderBy("film_time");
                        break;
                    case 3:
                        PageHelper.orderBy("film_score");
                        break;
                    default:
                        PageHelper.orderBy("film_box_office");
                        break;
                }
                String catLimit = null;
                if (catId != 99) {
                    catLimit = "#" + catId + "#";
                }
                List<Film> films = filmMapper.selectByFilmStatus(1, sourceId, yearId, catLimit);
                List<FilmInfoVO> filmInfoVOs = VOUtil.getFilmInfoVOs(films);
                filmVO.setFilmInfoVO(filmInfoVOs);
                filmVO.setFilmNum(filmInfoVOs.size());
                filmVO.setNowPage(nowPage);
                filmVO.setTotalPage((int) Math.ceil(films.size() / (1.0 * nums)));
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
    public CommonResponse<FilmVO> getSoonFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        CommonResponse<FilmVO> commonResponse = CommonResponse.success(FilmVO.class);
        FilmVO filmVO = new FilmVO();
        // 即将上映影片的限制条件 film_status = 2
        try {
            //用于首页的查询
            if (isLimit) {
                PageHelper.startPage(1, nums);
                List<Film> films = filmMapper.selectByFilmStatus(2, 99, 99, null);
                List<FilmInfoVO> filmInfoVOs = VOUtil.getFilmInfoVOs(films);
                filmVO.setFilmInfoVO(filmInfoVOs);
                filmVO.setFilmNum(filmInfoVOs.size());
            } else {
                //用于列表的查询
                PageHelper.startPage(nowPage, nums);
                // 1-按热门排序，2-按时间排序，3-按评价排序
                switch (sortId) {
                    case 1:
                        PageHelper.orderBy("film_preSaleNum");
                        break;
                    case 2:
                        PageHelper.orderBy("film_time");
                        break;
                    case 3:
                        PageHelper.orderBy("film_preSaleNum");
                        break;
                    default:
                        PageHelper.orderBy("film_preSaleNum");
                        break;
                }
                String catLimit = null;
                if (catId != 99) {
                    catLimit = "#" + catId + "#";
                }
                List<Film> films = filmMapper.selectByFilmStatus(2, sourceId, yearId, catLimit);
                List<FilmInfoVO> filmInfoVOs = VOUtil.getFilmInfoVOs(films);
                filmVO.setFilmInfoVO(filmInfoVOs);
                filmVO.setFilmNum(filmInfoVOs.size());
                filmVO.setNowPage(nowPage);
                filmVO.setTotalPage((int) Math.ceil(films.size() / (1.0 * nums)));

            }
        } catch (Exception e) {
            logger.error("查询即将上映影片出错" + e);
            return CommonResponse.fail(FilmVO.class, ResponseStatus.SYSTEM_ERROR);
        }
        commonResponse.setData(filmVO);
        return commonResponse;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/23 0023
     * @description: 获取影片条件接口--片源条件
     * @return java.util.List<club.lightingsummer.movie.film.api.vo.SourceVO>
     */
    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> response = new ArrayList<>();
        try {
            List<Source> sourceList = sourceMapper.selectSourceList();
            for (Source source : sourceList) {
                SourceVO sourceVO = new SourceVO();
                sourceVO.setSourceId(source.getUuid() + "");
                sourceVO.setSourceName(source.getShowName());
                response.add(sourceVO);
            }
        } catch (Exception e) {
            logger.error("获取影片片源条件失败" + e.getMessage());
        }
        return response;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/23 0023
     * @description: 获取影片条件接口--获取年代条件
     * @return java.util.List<club.lightingsummer.movie.film.api.vo.YearVO>
     */
    @Override
    public List<YearVO> getYears() {
        List<YearVO> response = new ArrayList<>();
        try {
            List<YearDict> yearDictList = yearDictMapper.selectYearList();
            for (YearDict yearInfo : yearDictList) {
                YearVO yearVO = new YearVO();
                yearVO.setYearId(yearInfo.getUuid() + "");
                yearVO.setYearName(yearInfo.getShowName());
                response.add(yearVO);
            }
        } catch (Exception e) {
            logger.error("获取年代条件失败" + e.getMessage());
        }
        return response;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/25 0025
     * @description: 查询影片详细信息
     * @param searchType
     * @param searchParam
     * @return club.lightingsummer.movie.film.api.vo.FilmDetailVO
     */
    @Override
    public FilmDetailVO getFilmDetail(int searchType, String searchParam) {
        FilmDetailVO filmDetailVO = null;
        // searchType 1-按名称  2-按ID的查找
        if (searchType == 1) {
            filmDetailVO = filmMapper.getFilmDetailByName("%" + searchParam + "%");
        } else {
            filmDetailVO = filmMapper.getFilmDetailById(searchParam);
        }
        return filmDetailVO;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/23 0023
     * @description: 获取影片条件接口--分类条件
     * @return java.util.List<club.lightingsummer.movie.film.api.vo.CatVO>
     */
    @Override
    public List<CatVO> getCats() {
        List<CatVO> response = new ArrayList<>();
        try {
            List<CatDict> catDictList = catDictMapper.selectCatList();
            for (CatDict catDict : catDictList) {
                CatVO catVO = new CatVO();
                catVO.setCatId(catDict.getUuid() + "");
                catVO.setCatName(catDict.getShowName());
                response.add(catVO);
            }
        } catch (Exception e) {
            logger.error("获取影片分类条件失败" + e.getMessage());
        }
        return response;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/25 0025
     * @description: 电影简介
     * @param filmId
     * @return club.lightingsummer.movie.film.api.vo.FilmDescVO
     */
    @Override
    public FilmDescVO getFilmDesc(String filmId) {
        FilmDescVO filmDescVO = new FilmDescVO();
        try {
            FilmInfoWithBLOBs filmInfoWithBLOBs = filmInfoMapper.selectByPrimaryKey(Integer.valueOf(filmId));
            filmDescVO.setBiography(filmInfoWithBLOBs.getBiography());
            filmDescVO.setFilmId(filmId);
        } catch (NumberFormatException e) {
            logger.error("查询影片简介失败" + e.getMessage());
        }
        return filmDescVO;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/25 0025
     * @description: 电影海报
     * @param filmId
     * @return club.lightingsummer.movie.film.api.vo.FilmDescVO
     */
    @Override
    public ImgVO getImgs(String filmId) {
        ImgVO imgVO = new ImgVO();
        try {
            FilmInfoWithBLOBs filmInfoWithBLOBs = filmInfoMapper.selectByPrimaryKey(Integer.valueOf(filmId));
            // 图片地址是五个以逗号为分隔的链接URL
            String filmImgStr = filmInfoWithBLOBs.getFilmImgs();
            String[] filmImgs = filmImgStr.split(",");
            // 顺序固定
            imgVO.setMainImg(filmImgs[0]);
            imgVO.setImg01(filmImgs[1]);
            imgVO.setImg02(filmImgs[2]);
            imgVO.setImg03(filmImgs[3]);
            imgVO.setImg04(filmImgs[4]);
        } catch (NumberFormatException e) {
            logger.error("获取电影海报失败" + e.getMessage());
        }
        return imgVO;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/25 0025
     * @description: 电影导演
     * @param filmId
     * @return club.lightingsummer.movie.film.api.vo.FilmDescVO
     */
    @Override
    public ActorVO getDectInfo(String filmId) {
        ActorVO actorVO = new ActorVO();
        try {
            FilmInfoWithBLOBs filmInfoWithBLOBs = filmInfoMapper.selectByPrimaryKey(Integer.valueOf(filmId));
            // 获取导演编号
            Integer directId = filmInfoWithBLOBs.getDirectorId();
            Actor actor = actorMapper.selectByPrimaryKey(directId);
            actorVO.setImgAddress(actor.getActorImg());
            actorVO.setDirectorName(actor.getActorName());
        } catch (NumberFormatException e) {
            logger.error("获取导演信息失败" + e.getMessage());
        }
        return actorVO;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/25 0025
     * @description: 电影演员表
     * @param filmId
     * @return club.lightingsummer.movie.film.api.vo.FilmDescVO
     */
    @Override
    public List<ActorVO> getActors(String filmId) {
        List<ActorVO> actors = new ArrayList<>();
        try {
            actors = actorMapper.getActors(filmId);
        } catch (Exception e) {
            logger.error("获取演员失败" + e.getMessage());
        }
        return actors;
    }
}

