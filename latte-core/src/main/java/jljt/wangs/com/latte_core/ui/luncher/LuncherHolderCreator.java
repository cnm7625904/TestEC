package jljt.wangs.com.latte_core.ui.luncher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Administrator on 2017/12/14.
 */

public class LuncherHolderCreator implements CBViewHolderCreator<LuncherHolder>{

    @Override
    public LuncherHolder createHolder() {
        return new LuncherHolder();
    }
}
