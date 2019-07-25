package club.lightingsummer.movie.film.biz.api;

import club.lightingsummer.movie.film.api.api.FilmAsyncServiceApi;
import club.lightingsummer.movie.film.api.po.Actor;
import club.lightingsummer.movie.film.api.po.FilmInfoWithBLOBs;
import club.lightingsummer.movie.film.api.vo.ActorVO;
import club.lightingsummer.movie.film.api.vo.FilmDescVO;
import club.lightingsummer.movie.film.api.vo.ImgVO;
import club.lightingsummer.movie.film.dal.dao.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/25 0025
 * @description：
 */
@Component
@Service(interfaceClass = FilmAsyncServiceApi.class)
public class FilmAsyncServiceApiImpl implements FilmAsyncServiceApi {
    private static final Logger logger = LoggerFactory.getLogger(FilmAsyncServiceApiImpl.class);

    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private ActorMapper actorMapper;

    /**
     * @author: lightingSummer
     * @date: 2019/7/25 0025
     * @description: 电影简介
     * @param filmId
     * @return club.lightingsummer.movie.film.api.vo.FilmDescVO
     */
    @Override
    public FilmDescVO getFilmDescAsync(String filmId) {
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
    public ImgVO getImgsAsync(String filmId) {
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
    public ActorVO getDectInfoAsync(String filmId) {
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
    public List<ActorVO> getActorsAsync(String filmId) {
        List<ActorVO> actors = new ArrayList<>();
        try {
            actors = actorMapper.getActors(filmId);
        } catch (Exception e) {
            logger.error("获取演员失败" + e.getMessage());
        }
        return actors;
    }
}
