package club.lightingsummer.movie.film.mapper;

import club.lightingsummer.movie.film.po.Film;
import club.lightingsummer.movie.film.vo.FilmDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FilmMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);

    /*@Select("select " +
            "UUID, film_name, film_type, img_address, film_score, film_preSaleNum, film_box_office, " +
            "    film_source, film_cats, film_area, film_date, film_time, film_status " +
            " from tb_film " +
            " where film_status = #{filmStatus}")*/
    List<Film> selectByFilmStatus(@Param("filmStatus") int filmStatus,
                                  @Param("sourceId") int sourceId,
                                  @Param("yearId") int yearId,
                                  @Param("catId") String catId);

    FilmDetailVO getFilmDetailByName(@Param("filmName") String filmName);

    FilmDetailVO getFilmDetailById(@Param("uuid") String uuid);
}