package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BannerMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}