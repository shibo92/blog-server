package spider;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class Util {
    public static String handleWords() throws PinyinException {
        String pinyinResult = PinyinHelper.convertToPinyinString("胸弟", "-", PinyinFormat.WITHOUT_TONE);
        return pinyinResult;
    }
}
