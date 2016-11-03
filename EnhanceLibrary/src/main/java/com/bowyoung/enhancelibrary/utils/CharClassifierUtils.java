package com.bowyoung.enhancelibrary.utils;

/**
 * Created by S0S on 2016/10/20.
 */

public class CharClassifierUtils {

    //中文
    public final static int TYPE_CHINESE = 0x001;
    //英文
    public final static int TYPE_ENGLISH = 0x002;
    //中文左标点
    public final static int TYPE_CHINESE_PUNCTUATION_LEFT = 0x003;
    //中文右标点
    public final static int TYPE_CHINESE_PUNCTUATION_RIGHT = 0x004;
    //中文单标点(除成对的那种标点)
    public final static int TYPE_CHINESE_PUNCTUATION = 0x005;
    //英文左标点
    public final static int TYPE_ENGLISH_PUNCTUATION_LEFT = 0x006;
    //英文右标点
    public final static int TYPE_ENGLISH_PUNCTUATION_RIGHT = 0x007;
    //英文单标点(除成对的那种标点)
    public final static int TYPE_ENGLISH_PUNCTUATION = 0x008;
    //英文标点(成对标点,即不分左右)
    public final static int TYPE_ENGLISH_PUNCTUATION_LEFT_RIGHT = 0x009;

    private final static String NEWLINE = "\n";
    private final static String PUNCTUATION = "、`~!@#$^&*)=|:;,./?~！@#￥……&*）——|；：'。，、？";
    private final static String ENGLISH_PUNCTUATION_LEFT = "<[{";
    private final static String ENGLISH_PUNCTUATION_RIGHT = ">]}";
    private final static String ENGLISH_PUNCTUATION_LEFT_RIGHT = "'\"";
    private final static String CHINESE_PUNCTUATION_LEFT = "《“‘（【";
    private final static String CHINESE_PUNCTUATION_RIGHT = "》”’）】";

    /**
     * 将原始字符串以 lineLength 分隔为若干行并返回
     *
     * @param text       需要分隔的字符串
     * @param lineLength 一行最大字符串（一个汉字占1个，一个英文占0.5）
     * @return 格式化好的字符串
     */
    public static String separatedLine(String text, int lineLength) {
        if (StringUtils.isEmpty(text) || lineLength < 1) {
            new IllegalArgumentException("Text is null or lineLength is illegal.");
        }
        StringBuilder sb = new StringBuilder();
        int lineNumber = lineLength * 2;
        char[] chars = text.toCharArray();
        int lineIndex = 0;
        int step = 0;
        int length = chars.length;
        for (int index = 0; index < length; index++) {
            lineIndex += step = isChinese(chars[index]) ? 2 : 1;
            int type = getType(chars[index]);
            switch (type) {
                case TYPE_CHINESE:
                case TYPE_ENGLISH:
                    if (lineIndex > lineNumber) {
                        sb.append(NEWLINE);
                        sb.append(chars[index]);
                        lineIndex = step;
                    } else if (lineIndex == lineNumber) {
                        sb.append(chars[index]);
                        int next = index + 1;
                        if (next < chars.length && isPunctuation(chars[next])) {
                            sb.append(chars[next]);
                            next += 1;
                            if (next < chars.length && isPunctuation(chars[next])) {
                                sb.append(chars[next]);
                            }
                            index = next;
                        }
                        sb.append(NEWLINE);
                        lineIndex = 0;
                    } else {
                        sb.append(chars[index]);
                    }
                    break;
                case TYPE_CHINESE_PUNCTUATION:
                case TYPE_ENGLISH_PUNCTUATION:
                    if (lineIndex >= lineNumber) {
                        sb.append(chars[index]);
                        sb.append(NEWLINE);
                        lineIndex = 0;
                    } else {
                        sb.append(chars[index]);
                    }
                    break;
                case TYPE_CHINESE_PUNCTUATION_RIGHT:
                case TYPE_ENGLISH_PUNCTUATION_RIGHT:
                    if (lineIndex >= lineNumber) {
                        sb.append(chars[index]);
                        int next = index + 1;
                        if (next < chars.length && isPunctuation(chars[next])) {
                            sb.append(chars[next]);
                            index = next;
                        }
                        sb.append(NEWLINE);
                        lineIndex = 0;
                    } else {
                        sb.append(chars[index]);
                    }
                    break;
                case TYPE_CHINESE_PUNCTUATION_LEFT:
                case TYPE_ENGLISH_PUNCTUATION_LEFT:
                    if (lineIndex >= lineNumber) {
                        sb.append(NEWLINE);
                        sb.append(chars[index]);
                        lineIndex = 0;
                    } else {
                        sb.append(chars[index]);
                    }
                    break;
                case TYPE_ENGLISH_PUNCTUATION_LEFT_RIGHT:
                    if (lineIndex >= lineNumber) {
                        int charCount = subCount(sb.toString(), chars[index]);
                        if (isEven(charCount)) {
                            sb.append(NEWLINE);
                            sb.append(chars[index]);
                            lineIndex = 0;
                        } else {
                            sb.append(chars[index]);
                            int next = index + 1;
                            if (next < length && isPunctuation(chars[next])) {
                                sb.append(chars[next]);
                                index = next;
                            }
                            sb.append(NEWLINE);
                            lineIndex = 0;
                        }
                    } else {
                        sb.append(chars[index]);
                    }
                    break;
                default:
                    sb.append(chars[index]);
                    break;
            }
        }
        return sb.toString();
    }

    public static boolean isEven(int count) {
        if (count % 2 == 0) {
            return true;
        }
        return false;
    }

    public static int subCount(String text, char c) {
        int count = 0;
        for (char aChar : text.toCharArray()) {
            if (aChar == c) {
                count++;
            }
        }
        return count;
    }

    public static int getType(char c) {
        int type;
        if (isChineseByBlock(c)) {  //是否为中文
            type = TYPE_CHINESE;
        } else if (isChinesePunctuation(c)) {   //是否为中文标点
            if (CHINESE_PUNCTUATION_LEFT.contains(String.valueOf(c))) { //中文左标点
                type = TYPE_CHINESE_PUNCTUATION_LEFT;
            } else if (CHINESE_PUNCTUATION_RIGHT.contains(String.valueOf(c))) { //中文右标点
                type = TYPE_CHINESE_PUNCTUATION_RIGHT;
            } else {    //中文单标点
                type = TYPE_CHINESE_PUNCTUATION;
            }
        } else if (isEnglish(c)) {  //是否为英文
            type = TYPE_ENGLISH;
        } else {
            if (ENGLISH_PUNCTUATION_LEFT.contains(String.valueOf(c))) { //英文左标点
                type = TYPE_ENGLISH_PUNCTUATION_LEFT;
            } else if (ENGLISH_PUNCTUATION_RIGHT.contains(String.valueOf(c))) { //英文右标点
                type = TYPE_ENGLISH_PUNCTUATION_RIGHT;
            } else if (ENGLISH_PUNCTUATION_LEFT_RIGHT.contains(String.valueOf(c))) {    //英文不分左右的成对标点
                type = TYPE_ENGLISH_PUNCTUATION_LEFT_RIGHT;
            } else {    //英文单标点
                type = TYPE_ENGLISH_PUNCTUATION;
            }
        }
        return type;
    }

    /**
     * 判断一个 char 是否为中文，包括是否为中文标点符号
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        if (isChineseByBlock(c) || isChinesePunctuation(c)) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个 char 是否为中文
     *
     * @param c
     * @return
     */
    public static boolean isChineseByBlock(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个 char 是否为中文标点，竖着写的标点符号不能判断，例如：   
     *
     * @param c
     * @return
     */
    public static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个 char 是否为标点符号,包括中英文字符,除成对出现的字符
     *
     * @param c
     * @return
     */
    public static boolean isPunctuation(char c) {
        if (isChinesePunctuation(c) || PUNCTUATION.contains(String.valueOf(c))) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个 char 是否为英文
     *
     * @param c
     * @return
     */
    public static boolean isEnglish(char c) {
        return String.valueOf(c).matches("^[a-zA-Z0-9]*");
    }

}
