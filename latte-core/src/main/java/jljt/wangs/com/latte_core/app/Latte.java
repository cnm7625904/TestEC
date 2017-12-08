package jljt.wangs.com.latte_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/12/6.
 * final不能被继承
 */

public final class Latte {
    public static Configurator init(Context context){
       getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
       return Configurator.getInstance();
    }
    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }
    /**
     * 获取context的名字
     */
    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
