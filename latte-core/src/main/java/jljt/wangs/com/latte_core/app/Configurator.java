package jljt.wangs.com.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/12/6.
 */

public final class Configurator {
    private static final HashMap<String,Object> LATTE_CONFIGS=new HashMap<>();//存放配置信息
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();//存放字体
    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);//配置已开始，但尚未完成
    }
    /**
     * 返回实例
     */
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }
    final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
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
    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }
    /**
     * 配置API
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }
    /**
     * 检查配置是否完成
     */
    private void checkConfiguration(){
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
    /**
     * 获取Configuration
     */
    final <T> T getConfigration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
    /**
     * 初始化字体
     */
    private void initIcons(){
        if(ICONS.size()>0){
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));
            for (int i=1;i<ICONS.size();i++){
                Iconify.with(ICONS.get(i));
            }
        }
    }

    /**
     * 添加自定义字体
     * @param descriptor
     * @return
     */
    public Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
}
