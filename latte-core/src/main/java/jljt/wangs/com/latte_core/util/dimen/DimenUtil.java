package jljt.wangs.com.latte_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import jljt.wangs.com.latte_core.app.Latte;

/**
 * Created by Administrator。 on 2017/12/9.
 */

public class DimenUtil {
    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return  dm.widthPixels;
    }
    /**
     * 获取屏幕高度
     */
    public static int getScreenHeigth(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return  dm.heightPixels;
    }
}
