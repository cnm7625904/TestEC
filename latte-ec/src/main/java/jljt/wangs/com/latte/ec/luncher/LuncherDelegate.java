package jljt.wangs.com.latte.ec.luncher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import jljt.wangs.com.latte.ec.R;
import jljt.wangs.com.latte.ec.R2;
import jljt.wangs.com.latte_core.app.Latte;
import jljt.wangs.com.latte_core.delegates.LatteDelegate;
import jljt.wangs.com.latte_core.ui.luncher.ScrollLuncherTag;
import jljt.wangs.com.latte_core.util.storage.LattePreference;
import jljt.wangs.com.latte_core.util.timer.BaseTimerTask;
import jljt.wangs.com.latte_core.util.timer.ITimerListener;

/**
 * Created by Administrator on 2017/12/13.
 */

public class LuncherDelegate extends LatteDelegate implements ITimerListener{
    @BindView(R2.id.tv_luncher_timer)
    AppCompatTextView mTvTimer=null;
    @OnClick(R2.id.tv_luncher_timer)
    void onClickTimerView(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer=null;
            checkIsShowScroll();
        }
    }
    private Timer mTimer=null;
    private int mCount=5;//倒计时数字
    @Override
    public Object setLayout() {
        return R.layout.delegate_luncher;
    }
    private void initTimer(){
        mTimer=new Timer();
        final BaseTimerTask task=new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);//延迟0ms,每隔1000ms执行一次
    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }
    //判断是否显示活动启动页
    private void checkIsShowScroll(){
        if(!LattePreference.getAppFlag(ScrollLuncherTag.HAS_FIRST_LANCHER_APP.name())){
            start(new LuncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登录了APP
        }
    }
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                mCount--;
                if(mCount<0){
//                    Toast.makeText(Latte.getApplicationContext(),""+mCount,Toast.LENGTH_LONG).show();
                    if(mTimer!=null){
                       mTimer.cancel();
                       mTimer=null;
                       checkIsShowScroll();
                    }
                }
            }
        });
    }
}
