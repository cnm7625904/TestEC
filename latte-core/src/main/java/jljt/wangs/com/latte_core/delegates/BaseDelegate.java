package jljt.wangs.com.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Administrator on 2017/12/7.
 * 基础代表Activity
 * 传入布局可以是View也可以是layoutid,所以将类型申明为Object
 */

public abstract class BaseDelegate extends SwipeBackFragment{
    private Unbinder mUnbinder=null;
    public abstract Object setLayout();//强制子类实现
    public abstract void onBindView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=null;
        if(setLayout() instanceof Integer){//布局为layout
            rootView=inflater.inflate((Integer) setLayout(),container,false);
        }else  if(setLayout() instanceof View)//布局为layout
        {
            rootView= (View) setLayout();
        }
        if(rootView!=null){
            mUnbinder= ButterKnife.bind(this, rootView);
        }
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
