package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.Source;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SourceMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Source record);

    int insertSelective(Source record);

    Source selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);

    List<Source> selectSourceList();
}