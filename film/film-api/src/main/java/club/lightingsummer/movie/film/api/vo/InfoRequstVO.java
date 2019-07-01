package club.lightingsummer.movie.film.api.vo;

import lombok.Data;

@Data
public class InfoRequstVO {

    private String biography;
    private ActorRequestVO actors;
    private ImgVO imgVO;
    private String filmId;

}
