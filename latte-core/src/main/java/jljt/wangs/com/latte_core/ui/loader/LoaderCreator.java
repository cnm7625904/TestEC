package jljt.wangs.com.latte_core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by Administrator。 on 2017/12/9.
 */

public final class LoaderCreator {

    private static final WeakHashMap<String,Indicator> LOADING_MAP=new WeakHashMap<>();

    static AVLoadingIndicatorView creat(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type)==null){
            final Indicator indicator=getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }
    private static Indicator getIndicator(String name){
        if (name==null||name.isEmpty()){
            return null;
        }
        /**
         * String字符串常量
         * StringBuffer与StringBuilder都是字符串变量
         * 操作速度StringBuilder>StringBuffer
         * StringBuilder线程非安全,StringBuffer线程安全
         */
        final StringBuilder drawableClassName=new StringBuilder();
        if(!name.contains(".")){
            final String defaultPackageName=AVLoadingIndicatorView.class.getPackage().getName();//反射获取包名
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass=Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
