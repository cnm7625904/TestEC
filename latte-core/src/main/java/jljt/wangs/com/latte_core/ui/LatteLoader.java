package jljt.wangs.com.latte_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import jljt.wangs.com.latte_core.R;
import jljt.wangs.com.latte_core.util.dimen.DimenUtil;

/**
 * Created by Administrator。 on 2017/12/9.
 */

public class LatteLoader {

    private static final int LOADER_SIZE_SCALE=8;//缩放倍数
    private static final int LOADER_OFFSET_SCALE=10;//偏移量
    /**
     * 创建集合存放loader,统一管理
     * @param context
     * @param type
     */
    private static final ArrayList<AppCompatDialog> LOADERS=new ArrayList<>();
    //默认加载样式
    private static final String DEAFAULT_LOADER=LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> loaderStyleEnum){
      showLoading(context,loaderStyleEnum.name());
    }

    public static void showLoading(Context context,String type){

        final AppCompatDialog dialog=new AppCompatDialog(context,R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView=LoaderCreator.creat(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth= DimenUtil.getScreenWidth();
        int deviceHeight=DimenUtil.getScreenHeigth();

        final Window dialogWindow=dialog.getWindow();

        if(dialogWindow!=null){
            WindowManager.LayoutParams lp=dialogWindow.getAttributes();
            lp.width=deviceWidth/LOADER_SIZE_SCALE;
            lp.height=deviceHeight/LOADER_SIZE_SCALE;
            lp.height=lp.height+deviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    //重载加载默认样式
    public static void showLoading(Context context){
        showLoading(context,DEAFAULT_LOADER);
    }
    public static void  stopLoading(){
        for (AppCompatDialog dialog:LOADERS) {
            if(dialog!=null) {
                if (dialog.isShowing()) {
                    //dialog.cancle有回调,dialog.dismiss没有回调
                    dialog.cancel();
                }
            }
        }
    }

}
