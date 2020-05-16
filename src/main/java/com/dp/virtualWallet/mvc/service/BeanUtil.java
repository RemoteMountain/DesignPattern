package com.dp.virtualWallet.mvc.service;


import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-19 20:45  //时间
 */
public final class BeanUtil<T extends Serializable> {

    private BeanUtil() {
    }


    public static <T> T copyProperties(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    public static <T> List<T> copyProperties(List<?> source, Class<T> clazz) {
        if (source == null || source.size() == 0) {
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<>(source.size());
        for (Object o : source) {
            T t = null;
            try {
                t = clazz.newInstance();
                BeanUtils.copyProperties(o, t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.add(t);
        }
        return res;
    }

}
