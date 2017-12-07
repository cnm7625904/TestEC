package jljt.wangs.com.testec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import jljt.wangs.com.latte_core.delegates.LatteDelegate;

/**
 * Created by Administrator。 on 2017/12/7.
 */

public class ExampleDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
