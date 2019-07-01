package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.FilmInfo;
import club.lightingsummer.movie.film.api.po.FilmInfoWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FilmInfoMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(FilmInfoWithBLOBs record);

    int insertSelective(FilmInfoWithBLOBs record);

    FilmInfoWithBLOBs selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(FilmInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FilmInfoWithBLOBs record);

    int updateByPrimaryKey(FilmInfo record);
}