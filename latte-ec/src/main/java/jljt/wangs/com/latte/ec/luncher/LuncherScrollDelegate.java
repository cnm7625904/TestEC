package jljt.wangs.com.latte.ec.luncher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import jljt.wangs.com.latte.ec.R;
import jljt.wangs.com.latte_core.delegates.LatteDelegate;
import jljt.wangs.com.latte_core.ui.luncher.LuncherHolderCreator;
import jljt.wangs.com.latte_core.ui.luncher.ScrollLuncherTag;
import jljt.wangs.com.latte_core.util.storage.LattePreference;

/**
 * Created by Administrator on 2017/12/14.
 * 传入资源文件id
 */

public class LuncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner=null;
    private static final ArrayList<Integer> INTEGERS=new ArrayList<>();
    @Override
    public Object setLayout() {
        mConvenientBanner=new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    /**
     * 初始化Banner
     */
    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LuncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})//指示器
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//水平居中
                .setOnItemClickListener(this)
                .setCanLoop(false);//可循环
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
       initBanner();
    }

    @Override
    public void onItemClick(int position) {
       if(position==INTEGERS.size()-1){//点击的是最后一个
           LattePreference.setAppFlag(ScrollLuncherTag.HAS_FIRST_LANCHER_APP.name(),true);
           //检查用户是否登录

       }
    }
}
