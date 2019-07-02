package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.Film;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select " +
            "UUID, film_name, film_type, img_address, film_score, film_preSaleNum, film_box_office, " +
            "    film_source, film_cats, film_area, film_date, film_time, film_status " +
            " from tb_film " +
            " where film_status = #{filmStatus}")
    List<Film> selectByFilmStatus(@Param("filmStatus") int filmStatus);
}