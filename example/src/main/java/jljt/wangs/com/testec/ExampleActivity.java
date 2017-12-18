package jljt.wangs.com.testec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import jljt.wangs.com.latte.ec.luncher.LuncherDelegate;
import jljt.wangs.com.latte.ec.luncher.LuncherScrollDelegate;
import jljt.wangs.com.latte_core.activities.ProxyActivity;
import jljt.wangs.com.latte_core.delegates.LatteDelegate;
import jljt.wangs.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        super.onCreate(savedInstanceState);
    }
    /**
     * SHIFT+f6改名
     * 注解模块是latte-annotations JAVA_LIBRARY
     * 编译器Moudle latte-compiler JAVA_LIBRARY
     * 核心Moudle latte-core ANDROID_LIBRARY
     * 业务moudle latte-ec ANDROID_LIBRARY
     * core-moudle依赖annotation-moudle
     * ec-moudle依赖core-moudle
     * project依赖ec-moudle
     * project依赖compiler-moudle
     * @param savedInstanceState
     */
}
