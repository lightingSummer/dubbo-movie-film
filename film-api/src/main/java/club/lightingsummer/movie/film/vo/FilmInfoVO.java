package club.lightingsummer.movie.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmInfoVO implements Serializable {

    private String filmId;
    private int filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;
    private int expectNum;
    private String showTime;
    private int boxNum;
    private String score;

}
