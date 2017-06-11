package com.xxh.web.vo;

import java.io.Serializable;

/**
 * Created by raid on 2017/6/11.
 */
public class DemoVo implements Serializable {
    private int id;
    private String name;

    public DemoVo() {
    }

    public DemoVo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
