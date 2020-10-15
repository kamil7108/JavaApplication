package io.github.mat3e;

public class Language {
    private Long   id;
    private String welcomeMSG;
    private String code;

    public Language(Long id, String welcomeMSG, String code) {
        this.id = id;
        this.welcomeMSG = welcomeMSG;
        this.code = code;
    }

    public Long getId() {
        return id;
    }


    public String getWelcomeMSG() {
        return welcomeMSG;
    }

    public void setWelcomeMSG(String welcomeMSG) {
        this.welcomeMSG = welcomeMSG;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
