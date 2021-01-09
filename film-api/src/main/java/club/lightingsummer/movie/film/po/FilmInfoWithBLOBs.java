package club.lightingsummer.movie.film.po;

import lombok.Data;

@Data
public class FilmInfoWithBLOBs extends FilmInfo {
    private String biography;

    private String filmImgs;
}