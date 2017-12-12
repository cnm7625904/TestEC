package jljt.wangs.com.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2017/12/6.
 */

public final class Configurator {
    private static final HashMap<Object,Object> LATTE_CONFIGS=new HashMap<>();//存放配置信息
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();//存放字体
    private static final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();//拦截器
    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY,false);//配置已开始，但尚未完成
    }
    /**
     * 返回实例
     */
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }
    final HashMap<Object,Object> getLatteConfigs(){
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
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY,true);
    }
    /**
     * 配置API
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }
    /**
     * 配置拦截器
     */
    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    /**
     * 配置拦截器
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    /**
     * 检查配置是否完成
     */
    private void checkConfiguration(){
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
    /**
     * 获取Configuration
     */
    final <T> T getConfigration(Object key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
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
