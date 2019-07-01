package club.lightingsummer.movie.film.api.po;

import lombok.Data;

@Data
public class Banner {
    private Integer uuid;

    private String bannerAddress;

    private String bannerUrl;

    private Integer isValid;
}