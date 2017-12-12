package jljt.wangs.com.latte_core.app;

import android.content.Context;

/**
 * Created by Administrator on 2017/12/6.
 * final不能被继承
 */

public final class Latte {
    public static Configurator init(Context context){
       Configurator.getInstance().
               getLatteConfigs().
               put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
       return Configurator.getInstance();
    }
    public static Configurator getConfigrator(){
        return Configurator.getInstance();
    }
    public static <T> T getConfigurations(Object key){
        return getConfigrator().getConfigration(key);
    }
    /**
     * 获取context的名字
     */
    public static Context getApplicationContext(){
        return getConfigurations(ConfigKeys.APPLICATION_CONTEXT);
    }
}
