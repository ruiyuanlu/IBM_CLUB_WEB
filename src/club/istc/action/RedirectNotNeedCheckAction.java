package club.istc.action;

/**
 * Created by Morn Wu on 2017/2/23.
 */

public class RedirectNotNeedCheckAction {

    public String mainpage() {
        return "mainpage";
    }

    public String login() {
        return "login";
    }

    public String register() { return "register"; }

    public String error() {return "error";}

    public String exit() {return "exit";}

}
