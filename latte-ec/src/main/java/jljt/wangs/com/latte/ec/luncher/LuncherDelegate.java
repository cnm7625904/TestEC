package jljt.wangs.com.latte.ec.luncher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import jljt.wangs.com.latte.ec.R;
import jljt.wangs.com.latte.ec.R2;
import jljt.wangs.com.latte_core.delegates.LatteDelegate;

/**
 * Created by Administrator on 2017/12/13.
 */

public class LuncherDelegate extends LatteDelegate{
    @BindView(R2.id.tv_luncher_timer)
    AppCompatTextView mTvTimer=null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_luncher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
