package com.istc.validation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Morn Wu on 2017/3/1.
 */
public class DateCheck {
    String date;
    boolean result;

    public DateCheck(String date) {
        // TODO Auto-generated constructor stub
        this.date=date.trim();
        result=checkDate();
    }

    /**
     * 检查QQ号是否合法
     */
    private boolean checkDate() {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Calendar curtime = Calendar.getInstance();
        curtime.setTime(new Date());
        int maxyear = curtime.get(Calendar.YEAR)-14;
        Calendar curbirthday = Calendar.getInstance();
        try {
            curbirthday.setTime(sdf.parse(date));
            if (curbirthday.get(Calendar.YEAR) < maxyear && curbirthday.get(Calendar.YEAR) >= 1970){
                result = true;
            }
        } catch (Exception e) {
            result=false;
        }
        return result;
    }

    public boolean getResult(){
        return result;
    }

}