package club.lightingsummer.movie.film.mapper;

import club.lightingsummer.movie.film.po.CatDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CatDictMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(CatDict record);

    int insertSelective(CatDict record);

    CatDict selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(CatDict record);

    int updateByPrimaryKey(CatDict record);

    List<CatDict> selectCatList();
}