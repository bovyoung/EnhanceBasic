package com.bowyoung.enhancelibrary.utils;

import java.util.List;

/**
 * Created by S0S on 16/5/6.
 */
public class ListUtils {

    public static int getCount(List<?> list) {
        return (list == null ? 0 : list.size());
    }

    public static boolean isEmpty(List<?> list) {
        return (list == null || list.isEmpty());
    }
}
