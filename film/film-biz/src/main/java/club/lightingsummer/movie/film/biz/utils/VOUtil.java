package club.lightingsummer.movie.film.biz.utils;

import club.lightingsummer.movie.film.api.po.Film;
import club.lightingsummer.movie.film.api.vo.FilmInfoVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/2 0002
 * @description：
 */
public class VOUtil {

    public static List<FilmInfoVO> getFilmInfoVOs(List<Film> films) {
        List<FilmInfoVO> filmInfos = new ArrayList<>();
        for (Film film : films) {
            FilmInfoVO filmInfo = new FilmInfoVO();
            filmInfo.setScore(film.getFilmScore());
            filmInfo.setImgAddress(film.getImgAddress());
            filmInfo.setFilmType(film.getFilmType());
            filmInfo.setFilmScore(film.getFilmScore());
            filmInfo.setFilmName(film.getFilmName());
            filmInfo.setFilmId(film.getUuid() + "");
            filmInfo.setExpectNum(film.getFilmPresalenum());
            filmInfo.setBoxNum(film.getFilmBoxOffice());
            filmInfo.setShowTime(DateUtil.getDay(film.getFilmTime()));

            // 将转换的对象放入结果集
            filmInfos.add(filmInfo);
        }

        return filmInfos;
    }
}
