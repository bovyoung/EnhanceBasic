package com.bowyoung.enhancelibrary.utils;

import com.bowyoung.enhancelibrary.config.Constants;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by S0S on 2016/11/23.
 * <p>
 * Desc:
 */

public class StringUtils {

    private static final String TAG = StringUtils.class.getSimpleName();

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static int getLength(CharSequence str) {
        return isEmpty(str) ? 0 : str.length();
    }

    /**
     * 判断字符串是否为空
     *
     * @param strs
     * @return true - 全为空， false - 有一个不为空
     */
    public static boolean isEmpty(String... strs) {
        if (strs == null) {
            return true;
        }
        for (String str : strs) {
            if ((str != null) && !str.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     *
     * @param strs
     * @return true - 有一个不为空， false - 全部不为空
     */
    public static boolean hasEmpty(String... strs) {
        if (strs == null) {
            return true;
        }
        for (String str : strs) {
            if ((str == null) || str.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 把字符串转化为int
     *
     * @return
     */
    public static int parseInt(String str) {
        try {
            if (str != null) {
                return Integer.parseInt(str.trim());
            }
        } catch (Exception e) {
            LogUtils.d(TAG, e.toString());
        }
        return 0;
    }

    /**
     * 把字符串转化为int
     *
     * @return
     */
    public static long parseLong(String str) {
        try {
            if (str != null) {
                return Long.parseLong(str.trim());
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 获取md5加密
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes(Constants.Encoding.UTF_8));
            byte[] bytes = messageDigest.digest();
            StringBuffer sbuffer = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                if (Integer.toHexString(0xFF & bytes[i]).length() == 1) {
                    sbuffer.append("0").append(Integer.toHexString(0xFF & bytes[i]));
                } else {
                    sbuffer.append(Integer.toHexString(0xFF & bytes[i]));
                }
            }
            return sbuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String replaceBlank(String src) {
        Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
        Matcher matcher = pattern.matcher(src);
        String result = matcher.replaceAll("");

        return result;
    }

    /**
     * 获取md5加密
     *
     * @param str
     * @return
     */
    public static String md5MultScreen(String str) {
        String md5str = md5(str);
        if (md5str == null || md5str.length() == 0) {
            return "";
        }
        String result = "";
        if (md5str.length() == 32) {
            StringBuffer sbuffer = new StringBuffer();
            String str1 = md5str.substring(0, 6);
            String str2 = md5str.substring(6, 16);
            String str3 = md5str.substring(16, 26);
            String str4 = md5str.substring(26, md5str.length());
            sbuffer.append(str1);
            sbuffer.append(str4);
            sbuffer.append(str3);
            sbuffer.append(str2);
            sbuffer = sbuffer.reverse();
            result = md5(sbuffer.substring(4, 15));
        }

        return result;
    }

    private static StringBuilder mFormatBuilder;
    private static Formatter mFormatter;

    static {
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    }

    public static boolean isMailAddress(String mail) {
        if (!StringUtils.isEmpty(mail)) {
            Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
            Matcher m = p.matcher(mail);
            return m.matches();
        }
        return false;
    }

    public static boolean checkPassword(String input_password) {
        int length = input_password.length();
        if (length >= 4 && length <= 20) {
            Pattern pattern = Pattern.compile("[a-z0-9A-Z]+");
            Matcher matcher = pattern.matcher(input_password);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isPhoneNumber(String mobiles) {
        if (!StringUtils.isEmpty(mobiles)) {
            Pattern p = Pattern.compile("^\\d{11}$");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        }
        return false;
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

}
