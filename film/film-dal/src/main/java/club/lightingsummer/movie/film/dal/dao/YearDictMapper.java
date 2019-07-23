package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.YearDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface YearDictMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(YearDict record);

    int insertSelective(YearDict record);

    YearDict selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(YearDict record);

    int updateByPrimaryKey(YearDict record);

    List<YearDict> selectYearList();
}