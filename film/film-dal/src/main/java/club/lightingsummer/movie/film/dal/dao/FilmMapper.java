package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.Film;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FilmMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);
}