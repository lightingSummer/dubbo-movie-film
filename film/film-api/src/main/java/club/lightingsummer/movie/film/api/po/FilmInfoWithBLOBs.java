package club.lightingsummer.movie.film.api.po;

import lombok.Data;

@Data
public class FilmInfoWithBLOBs extends FilmInfo {
    private String biography;

    private String filmImgs;
}