package jljt.wangs.com.testec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import jljt.wangs.Icon.FontEcModule;
import jljt.wangs.com.latte_core.app.Latte;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ExampleApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .configure();

    }
}
