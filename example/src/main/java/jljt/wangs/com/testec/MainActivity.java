package jljt.wangs.com.testec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /**
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
