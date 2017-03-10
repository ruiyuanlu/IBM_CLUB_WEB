package com.istc.action;

import com.istc.Entities.Entity.HomeWork;
import com.istc.Entities.Entity.HomeWorkIssue;
import com.istc.Entities.Entity.Member;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.*;

/**
 * 部长参与评分
 */
@ParentPackage("needajax")
@AllowedMethods({"giveScore","fetchHomeworklist"})
public class ScoreAction extends ActionSupport{

    private int dept;
    private int homeworkid;
    private Map<String,Integer> scores = new HashMap<String,Integer>();
    private static HomeWorkIssue curhomework;
    private Map<String,Object> jsonresult=new HashMap<String,Object>();

    static {
        addtemp();
    }

    @Action(
            value="giveScore",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String giveScore(){
        try {
            for(Iterator<String> scoreIter = this.scores.keySet().iterator();scoreIter.hasNext();){
                String curid = scoreIter.next();
                int curscore = scores.get(curid);
                System.out.println(curid+" "+curscore);
                //这里仅由前端进行限制，若非法数据绕过前端提交，则自动剔除而不返回错误
                if (curscore > 100 || curscore < 0){
                    curscore = 0;
                }
                HashSet<HomeWork> homeWorks = (HashSet<HomeWork>) curhomework.getHomeWorks();
                for (Iterator<HomeWork> homeWorkIterator = homeWorks.iterator();homeWorkIterator.hasNext();){
                    HomeWork h = homeWorkIterator.next();
                    if (h.getHomeWorkSubmitter().getID().equals(curid)){
                        h.setScore(curscore);
                        break;
                    }
                }
            }
        }
        catch (Exception e){
            addFieldError("giveScore","评分失败！");
        }
        return INPUT;
    }

    @Action(
            value="fetchHomeworklist",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String fetchHomeworklist(){
        try {
            jsonresult.put("curhomework",curhomework);
        }
        catch (Exception e){
            addFieldError("fetchHomeworklist","获取作业列表失败！");
        }
        return INPUT;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public int getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(int homeworkid) {
        this.homeworkid = homeworkid;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }

    public Map<String, Object> getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(Map<String, Object> jsonresult) {
        this.jsonresult = jsonresult;
    }

    private static void addtemp(){
        curhomework=new HomeWorkIssue();
        HashSet<HomeWork> homeWorks = new HashSet<HomeWork>();
        HomeWork h=new HomeWork();
        Calendar starttime = Calendar.getInstance();
        starttime.set(2017,2,3,14,5);
        h.setBeginTime(starttime);
        Calendar endtime = Calendar.getInstance();
        endtime.set(2017,2,10,14,5);
        h.setEndTime(endtime);
        Calendar birthday = Calendar.getInstance();
        birthday.set(1996,4,7,00,00);
        h.setFileID(1);
        Member m = new Member();
        m.setID("2141601001");
        m.setName("张三");
        m.setBirthday(birthday);
        h.setHomeWorkSubmitter(m);
        homeWorks.add(h);
        h=new HomeWork();
        h.setBeginTime(starttime);
        h.setEndTime(endtime);
        h.setFileID(2);
        m=new Member();
        m.setID("2141601002");
        m.setName("李四");
        m.setBirthday(birthday);
        h.setHomeWorkSubmitter(m);
        homeWorks.add(h);
        curhomework.setHomeWorks(homeWorks);
        curhomework.setStartTime(starttime);
        curhomework.setEndTime(endtime);
    }

}
