package jljt.wangs.com.latte_core.util.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/18.
 */

public class ToastUtils {
    private static  Toast mToast=null;
    public static final void toastShort(Context context,String text){
            if(mToast==null){
                mToast=Toast.makeText(context,text,Toast.LENGTH_SHORT);
            }else {
                mToast.setText(text);
            }
            mToast.show();
    }
    public static final void toastLong(Context context,String text){
        if(mToast==null){
            mToast=Toast.makeText(context,text,Toast.LENGTH_LONG);
        }else {
            mToast.setText(text);
        }
        mToast.show();
    }
}
