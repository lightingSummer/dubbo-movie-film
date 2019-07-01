package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.CatDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CatDictMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(CatDict record);

    int insertSelective(CatDict record);

    CatDict selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(CatDict record);

    int updateByPrimaryKey(CatDict record);
}