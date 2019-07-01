package club.lightingsummer.movie.film.api.po;

import lombok.Data;

@Data
public class FilmInfo {
    private Integer uuid;

    private String filmId;

    private String filmEnName;

    private String filmScore;

    private Integer filmScoreNum;

    private Integer filmLength;

    private Integer directorId;
}