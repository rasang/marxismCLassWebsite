package cn.jmu.marxism.common.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/18 9:00
 * 通用工具类
 */

public class Utils {
    /**
     * 判断是否为中文
     * @param str
     * @return
     */
    public static boolean isChinese(String str){
        String pattern = "^[\\u4E00-\\u9FA5]+$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(str);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    /**
     * 判断是否为大小写字母
     * @param str
     * @return
     */
    public static boolean isUpperOrLowerCharacter(String str){
        String pattern = "^[a-zA-Z]+$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(str);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        String pattern = "^[0-9]+$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(str);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    /**
     * 判断是否为合法的用户名，即中文 大小写字母 或 数字
     * @param str
     * @return
     */
    public static boolean isLegalUsername(String str){
        String pattern = "^[0-9a-zA-Z\\u4E00-\\u9FA5]+$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(str);
        if(matcher.find()){
            return true;
        }
        return false;
    }
}
