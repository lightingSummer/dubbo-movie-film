package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BannerMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    @Select(" select" +
            " UUID, banner_address, banner_url, is_valid" +
            " from tb_banner " +
            " where is_valid = 1")
    List<Banner> selectBannerList();
}