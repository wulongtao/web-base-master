package com.xxh.web.util.format;

import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by wulongtao on 2017/5/19.
 */
@SuppressWarnings("unchecked")
public class XmlResponseParser implements ResponseParser {
    Logger logger = LoggerFactory.getLogger(XmlResponseParser.class);
    private final String ATTR_PREFIX = "attr_";

    @Override
    public Map<String, Object> parse(String resp) {
        Map<String, Object> map = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(resp);
            if (document == null) return map;
            map.put(document.getRootElement().getQualifiedName(), dom2Map(document.getRootElement(), new HashMap<>()));
            return map;
        } catch (DocumentException e) {
            logger.trace(e.getMessage());
            return map;
        }

    }

    private Map<String, Object> dom2Map(Element element, Map<String, Object> map) {

        List<Element> lstElement = element.elements();


        for (Element item : lstElement) {
            String name = item.getQualifiedName();
            int nameSize = element.elements(name).size();
            if (nameSize>1  && !map.containsKey(name)) {
                map.put(name, dom2List(element.elements(name)));
            }

            if (map.containsKey(item.getQualifiedName())) {
                continue;
            } else if (item.elements().size() == 0) {
                Map<String, Object> mTmp = new HashMap<>();
                mTmp.put("value", item.getTextTrim());
                addAttribute2Map(item, mTmp);
                map.put(item.getQualifiedName(), mTmp);
            } else {
                map.put(item.getQualifiedName(), dom2Map(item, new HashMap<>()));
            }

        }

        addAttribute2Map(element, map);


        return map;
    }

    private List<Object> dom2List(List<Element> lstElement) {
        List<Object> lstRet = new ArrayList<>();
        Map<String, Object> map;
        for (Element item : lstElement) {

            //
            if (item.elements().size() == 0) {
                map = new HashMap<>();
                map.put("value", item.getTextTrim());
            } else {
                map = dom2Map(item, new HashMap<>());

            }

            addAttribute2Map(item, map);

            lstRet.add(map);
        }
        return lstRet;
    }

    private void addAttribute2Map(Element item, Map<String, Object> map) {
        //加入属性，前缀设置成attr_
        for (Iterator<Attribute> it = item.attributeIterator(); it.hasNext(); ) {
            Attribute attribute = it.next();
            map.put(ATTR_PREFIX+attribute.getQualifiedName(), attribute.getValue());
        }
    }

}
