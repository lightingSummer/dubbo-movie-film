package club.lightingsummer.movie.film.mapper;

import club.lightingsummer.movie.film.po.FilmInfo;
import club.lightingsummer.movie.film.po.FilmInfoWithBLOBs;
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