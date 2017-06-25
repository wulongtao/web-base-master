package com.xxh.web.vo.demo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by raid on 2017/6/12.
 * 实例XML对象，如果元素是多个单词，那么加上name属性用下划线连接
 */
@XmlRootElement(name = "xml-demo-vo")
public class XmlDemoVo {
    private Integer id;
    private String name;
    private String author;

    public XmlDemoVo() {
    }

    public XmlDemoVo(Integer id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "XmlDemoVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
