package com.xxh.web.util.format;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Created by wulongtao on 2017/5/19.
 */
public class XmlResponseParserTest {

    @Test
    public void test() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \n" +
                "<!-- Edited by XMLSpy -->  \n" +
                "<bookstore key=\"11111\">  \n" +
                "    <book category=\"cooking\">  \n" +
                "        <title lang=\"en\">Everyday Italian</title>  \n" +
                "        <author>Giada De Laurentiis</author>  \n" +
                "    </book>  \n" +
                "    <book category=\"children\">  \n" +
                "        <title lang=\"en\">Harry Potter</title>  \n" +
                "        <author>J K. Rowling</author>  \n" +
                "    </book>  \n" +
                "    <book category=\"web\">  \n" +
                "        <title lang=\"en\">XQuery Kick Start</title>  \n" +
                "        <author id=\"1\">James McGovern</author>  \n" +
                "        <author id=\"1\">Per Bothner</author>  \n" +
                "        <author id=\"1\">Kurt Cagle</author>  \n" +
                "        <author id=\"1\">James Linn</author>  \n" +
                "        <author id=\"1\">Vaidyanathan Nagarajan</author>  \n" +
                "    </book>  \n" +
                "    <book category=\"web\" cover=\"paperback\">  \n" +
                "        <title lang=\"en\">Learning XML</title>  \n" +
                "        <author>Erik T. Ray</author>  \n" +
                "    </book>  \n" +
                "    <a category=\"web\" cover=\"paperback\"> abc \n" +
                "    </a>  \n" +
                "    <v category=\"web\" cover=\"paperback\"> abc \n" +
                "        <title lang=\"en\">Learning XML</title>  \n" +
                "        <author>Erik T. Ray</author>  \n" +
                "    </v>  \n" +
                "</bookstore>";

        System.out.println(str);
        XmlResponseParser parser = new XmlResponseParser();
        System.out.println(JSONObject.toJSONString(parser.parse(str)));
    }
}
