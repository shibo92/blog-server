package test;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.text.ParseException;
import java.util.List;
import java.util.StringTokenizer;

public class Test3 {
    String dict = "{'ui': '7', 'uan': '3', 'ian': '3', 'iu': '13', 'en': '8', 'ue': '16', 'ing': '9', 'a': '1', 'ei': '7',\n" +
            "            'eng': '9', 'uo': '6', 'ye': '12', 'in': '8', 'ou': '13', 'ao': '5', 'uang': '4', 'ong': '9', 'ang': '4',\n" +
            "            'ai': '2', 'ua': '1', 'uai': '2', 'an': '3', 'iao': '5', 'ia': '1', 'ie': '12', 'iong': '9', 'i': '11',\n" +
            "            'er': '10', 'e': '6', 'u': '14', 'un': '8', 'iang': '4', 'o': '6', 'qu': '15', 'xu': '15', 'yu': '15'}";

    public static void main(String[] args) throws ParseException {
        try {
            // String pinyinResult = PinyinHelper.convertToPinyinString("胸弟","-",PinyinFormat.WITHOUT_TONE);
            // System.out.println(pinyinResult);
            String str = "我爱北京天安门";
            JiebaSegmenter segmenter = new JiebaSegmenter();
            List<String> list = segmenter.sentenceProcess(str);
            for (String s : list){
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
