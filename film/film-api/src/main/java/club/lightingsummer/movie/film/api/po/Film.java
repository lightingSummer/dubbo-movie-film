package club.lightingsummer.movie.film.api.po;

import lombok.Data;

import java.util.Date;

@Data
public class Film {
    private Integer uuid;

    private String filmName;

    private Integer filmType;

    private String imgAddress;

    private String filmScore;

    private Integer filmPresalenum;

    private Integer filmBoxOffice;

    private Integer filmSource;

    private String filmCats;

    private Integer filmArea;

    private Integer filmDate;

    private Date filmTime;

    private Integer filmStatus;
}