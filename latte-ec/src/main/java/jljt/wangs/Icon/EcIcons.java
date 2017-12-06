package jljt.wangs.Icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator。 on 2017/12/6.
 */

public enum  EcIcons implements Icon {
    icon_scan('\ue606'),
    icon_ali_pay('\ue606');
    private char character;//储存变量

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace("_","-");
    }

    @Override
    public char character() {
        return character;
    }
}
