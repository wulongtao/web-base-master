package com.xxh.web.controller.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 小小黑
 */
public class Cat implements Serializable {
    @NotNull(message = "NotBlank.cat.name")
    @NotBlank(message = "NotBlank.cat.name")
    @Length(min = 6, max = 8, message = "Length.cat.name.limit")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
