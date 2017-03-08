package com.istc.validation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Morn Wu on 2017/3/8.
 */
public class ValidationUtils {
    private static class ValidationUtilsHolder{
        private static ValidationUtils instance=new ValidationUtils();
    }

    private ValidationUtils(){}

    public static ValidationUtils getInstance(){
        return ValidationUtils.ValidationUtilsHolder.instance;
    }

    public boolean checkIfAfterThanNow(String date) {
        date=date.trim();
        boolean result;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Calendar dateset = Calendar.getInstance();
        try {
            dateset.setTime(sdf.parse(date));
            if (dateset.after(Calendar.getInstance()) || dateset.YEAR < 1970){
                result = false;
            }
            else {
                result=true;
            }
        } catch (Exception e) {
            result=false;
        }
        return result;
    }

    public boolean checkCHNandENGname(String name,int minlength,int maxlength){
        boolean result=false;
        String regex = "[\\u4e00-\\u9fa5a-zA-Z]{"+minlength+","+maxlength+"}";
        try {
            result = name.matches(regex);
        }
        catch (Exception e){
            return false;
        }
        return result;
    }

    public String replaceString(String needcheck){
        char[] charArray = needcheck.toCharArray();
        for (int i = 0; i < needcheck.length(); i++) {
            int charIntValue = (int)charArray[i];
            if (charIntValue >= 33 && charIntValue <= 47) {
                charArray[i] = (char) (charIntValue + 65248);
            }
        }
        return new String(charArray);
    }
}
