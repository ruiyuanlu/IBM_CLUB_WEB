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

    public boolean checkIfDateAfterThanNow(String date) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        return checkBeforeAfterThanNow(date,sdf,false);
    }

    public boolean checkIfDatetimeAfterThanNow(String date){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return checkBeforeAfterThanNow(date,sdf,false);
    }

    public boolean checkIfDatetimeBeforeThanNow(String date){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return checkBeforeAfterThanNow(date,sdf,true);
    }

    private boolean checkBeforeAfterThanNow(String date,SimpleDateFormat sdf,boolean checkbefore){
        date=date.trim();
        boolean result;
        Calendar dateset = Calendar.getInstance();
        try {
            dateset.setTime(sdf.parse(date));
            if (checkbefore){
                if (!dateset.after(Calendar.getInstance())){
                    result = true;
                }
                else {
                    result=false;
                }
            }
            else {
                if (dateset.after(Calendar.getInstance())){
                    result = true;
                }
                else {
                    result=false;
                }
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

    public boolean checkIfDaysDelayAtLeast(String starttime,String endtime,int daysdelay){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        endtime=endtime.trim();
        starttime=starttime.trim();
        Calendar endtimeset = Calendar.getInstance();
        Calendar starttimeset = Calendar.getInstance();
        try {
            endtimeset.setTime(sdf.parse(endtime));
            starttimeset.setTime(sdf.parse(starttime));
            return dateTimeDaysDelayCheck(starttimeset,endtimeset,daysdelay);

        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkIfDaysDelayThanNowAtLeast(String endtime,int daysdelay){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Calendar endtimeset = Calendar.getInstance();
        try {
            endtimeset.setTime(sdf.parse(endtime));
            return dateTimeDaysDelayCheck(Calendar.getInstance(),endtimeset,daysdelay);
        }
        catch (Exception e){
            return false;
        }
    }

    private boolean dateTimeDaysDelayCheck(Calendar start,Calendar end, int daysdelay){
        start.add(Calendar.DAY_OF_YEAR,daysdelay);
        return !start.after(end);
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
