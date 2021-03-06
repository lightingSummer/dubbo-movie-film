package club.lightingsummer.movie.film.enums;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/28 0028
 * @description：
 */
public enum ResponseStatus {

    SUCCESS(0, "成功"),
    FAIL(1,"失败"),
    PARAM_LACK(300,"参数缺少"),
    SYSTEM_ERROR(301,"系统异常");

    private int code;
    private String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
