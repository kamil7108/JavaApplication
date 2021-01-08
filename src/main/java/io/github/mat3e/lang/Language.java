package io.github.mat3e.lang;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="LANGUAGES")
public class Language {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private  Integer   id;
    @Column(name="MSG")
    private String MSG;
    @Column(name="CODE")
    private String code;

    /**
     * Hibernate needs it
     */
    @SuppressWarnings("unused")
    public Language(){}

    public Language(Integer id, String MSG, String code) {
        this.id = id;
        this.MSG = MSG;
        this.code = code;
    }


    public Integer getId() {
        return id;
    }


    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
