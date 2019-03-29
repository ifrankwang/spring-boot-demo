package com.github.ifrankwang.spring.api.converter;


import com.github.ifrankwang.utils.list.ListUtils;

import java.util.List;

/**
 * @author Frank Wang
 */
public class ListStringConverter {

    public String listToString(List<String> list) {
        return ListUtils.with(list).toString();
    }

    public List<String> stringToList(String string) {
        return ListUtils.toList(string);
    }
}
