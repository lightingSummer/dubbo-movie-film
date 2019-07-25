package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.FilmActor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FilmActorMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(FilmActor record);

    int insertSelective(FilmActor record);

    FilmActor selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(FilmActor record);

    int updateByPrimaryKey(FilmActor record);
}