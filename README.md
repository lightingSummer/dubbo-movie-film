# dubbo-movie-film<br>
based dubbo project film模块<br>
影片模块<br>

## 用到的技术及实现主要功能<br>
* 使用dubbo提供film信息各种查询服务，使用zookeeper做注册中心，用于服务注册及调用<br>
* 使用springboot作为后端主要框架<br>
* 使用mysql5.7作为数据库存储，mybatis做查询，mybatis-generator生成xml映射，pagehelper做分页<br>

## api列表<br>

* 电影信息API，用于获取电影信息接口<br>
```java
public interface FilmInfoAPI {
    // 获取banners
    CommonResponse<List<BannerVO>> getBanners();

    // 获取热映影片
    CommonResponse<FilmVO> getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    // 获取即将上映影片[受欢迎程度做排序]
    CommonResponse<FilmVO> getSoonFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    // 获取经典影片
    CommonResponse<FilmVO> getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    // 获取影片条件接口--分类条件
    List<CatVO> getCats();

    // 获取影片条件接口--片源条件
    List<SourceVO> getSources();

    // 获取影片条件接口--获取年代条件
    List<YearVO> getYears();

    // 获取影片详细信息
    FilmDetailVO getFilmDetail(int searchType, String searchParam);

    // 获取影片描述信息
    FilmDescVO getFilmDesc(String filmId);

    // 获取图片信息
    ImgVO getImgs(String filmId);

    // 获取导演信息
    ActorVO getDectInfo(String filmId);

    // 获取演员信息
    List<ActorVO> getActors(String filmId);
}
```

* 排行榜API，用户获取各种人气排行榜<br>
```java
public interface FilmRankAPI {
    // 获取票房排行榜
    CommonResponse<List<FilmInfoVO>> getBoxRanking();

    // 获取新片预售排行榜
    CommonResponse<List<FilmInfoVO>> getExpectRanking();

    // 获取评分排行榜
    CommonResponse<List<FilmInfoVO>> getTop();
}
```

* 电影信息异步API，dubbo异步调用接口，因为dubbo异步设置只能设置一整个api，而不能注释接口，所以单独写一个api<br>
```java
public interface FilmAsyncServiceApi {
    // 获取影片描述信息
    FilmDescVO getFilmDescAsync(String filmId);

    // 获取图片信息
    ImgVO getImgsAsync(String filmId);

    // 获取导演信息
    ActorVO getDectInfoAsync(String filmId);

    // 获取演员信息
    List<ActorVO> getActorsAsync(String filmId);
}
```


## 数据表ddl<br>

```sql
CREATE TABLE `tb_actor` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `actor_name` varchar(50) DEFAULT NULL COMMENT '演员名称',
  `actor_img` varchar(200) DEFAULT NULL COMMENT '演员图片位置',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='演员表';
```
```sql
CREATE TABLE `tb_banner` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `banner_address` varchar(50) DEFAULT NULL COMMENT 'banner图存放路径',
  `banner_url` varchar(200) DEFAULT NULL COMMENT 'banner点击跳转url',
  `is_valid` int(11) DEFAULT '0' COMMENT '是否弃用 0-失效,1-有效',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='banner信息表';
```
```sql
CREATE TABLE `tb_cat_dict` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='类型信息表';
```
```sql
CREATE TABLE `tb_film` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_name` varchar(100) DEFAULT NULL COMMENT '影片名称',
  `film_type` int(11) DEFAULT NULL COMMENT '片源类型: 0-2D,1-3D,2-3DIMAX,4-无',
  `img_address` varchar(200) DEFAULT NULL COMMENT '影片主图地址',
  `film_score` varchar(20) DEFAULT NULL COMMENT '影片评分',
  `film_preSaleNum` int(11) DEFAULT NULL COMMENT '影片预售数量',
  `film_box_office` int(11) DEFAULT NULL COMMENT '影片票房：每日更新，以万为单位',
  `film_source` int(11) DEFAULT NULL COMMENT '影片片源，参照片源字典表',
  `film_cats` varchar(50) DEFAULT NULL COMMENT '影片分类，参照分类表,多个分类以#分割',
  `film_area` int(11) DEFAULT NULL COMMENT '影片区域，参照区域表',
  `film_date` int(11) DEFAULT NULL COMMENT '影片上映年代，参照年代表',
  `film_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '影片上映时间',
  `film_status` int(11) DEFAULT NULL COMMENT '影片状态,1-正在热映，2-即将上映，3-经典影片',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片主表';
```
```sql
CREATE TABLE `tb_film_actor` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` int(11) DEFAULT NULL COMMENT '影片编号,对应mooc_film_t',
  `actor_id` int(11) DEFAULT NULL COMMENT '演员编号,对应mooc_actor_t',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片与演员映射表';
```
```sql
CREATE TABLE `tb_film_info` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` varchar(100) DEFAULT NULL COMMENT '影片编号',
  `film_en_name` varchar(50) DEFAULT NULL COMMENT '影片英文名称',
  `film_score` varchar(20) DEFAULT NULL COMMENT '影片评分',
  `film_score_num` int(11) DEFAULT NULL COMMENT '评分人数,以万为单位',
  `film_length` int(11) DEFAULT NULL COMMENT '播放时长，以分钟为单位，不足取整',
  `biography` text COMMENT '影片介绍',
  `director_id` int(11) DEFAULT NULL COMMENT '导演编号',
  `film_imgs` text COMMENT '影片图片集地址,多个图片以逗号分隔',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片主表';
```
```sql
CREATE TABLE `tb_source` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='区域信息表';
```
```sql
CREATE TABLE `tb_year_dict` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='年代信息表';
```
