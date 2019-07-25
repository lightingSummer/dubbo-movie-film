package club.lightingsummer.movie.film.dal.dao;

import club.lightingsummer.movie.film.api.po.Actor;
import club.lightingsummer.movie.film.api.vo.ActorVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActorMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Actor record);

    int insertSelective(Actor record);

    Actor selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Actor record);

    int updateByPrimaryKey(Actor record);

    // 两个表关联，拼的sql，添加演员的时候也要注意
    List<ActorVO> getActors(@Param("filmId") String filmId);
}