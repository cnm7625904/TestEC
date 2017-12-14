package jljt.wangs.com.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import jljt.wangs.com.latte_core.activities.ProxyActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Administrator on 2017/12/7.
 * 基础代表Activity
 * 传入布局可以是View也可以是layoutid,所以将类型申明为Object
 */

public abstract class BaseDelegate extends SwipeBackFragment{
    @SuppressWarnings("SpellCheckingInspection")//忽略拼写
    private Unbinder mUnbinder=null;
    public abstract Object setLayout();//强制子类实现绑定视图
    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);//可以获取id绑定
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if(setLayout() instanceof Integer){//布局为layout
            rootView=inflater.inflate((Integer) setLayout(),container,false);
        }else  if(setLayout() instanceof View)//布局为layout
        {
            rootView= (View) setLayout();
        }
        else {
            throw new ClassCastException("setLayout type must be int or View!");
        }
        mUnbinder= ButterKnife.bind(this, rootView);//已经绑定完成
        onBindView(savedInstanceState,rootView);
        return rootView;
    }
    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=null){
            mUnbinder.unbind();//解除绑定
        }
    }
}
