package jljt.wangs.com.latte_core.app;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/12/6.
 */

public final class Configurator {
    private static final HashMap<String,Object> LATTE_CONFIGS=new HashMap<>();

    private Configurator() {

              LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);//配置已开始，但尚未完成
    }
    /**
     * 返回实例
     */
    public Configurator getInstance(){
        return Holder.INSTANCE;
    }
    /**
     * 静态内部类实现单例
     */
    private static class Holder{
        private static final Configurator INSTANCE=new Configurator();
    }

    /**
     * 配置完成
     */
    private final void configure(){
      LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }
}
