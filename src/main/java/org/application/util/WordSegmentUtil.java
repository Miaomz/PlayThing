package org.application.util;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Word;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/29
 */
public class WordSegmentUtil {

    private WordSegmentUtil() {}

    /**
     * 切词函数
     * @param text 原文本
     * @return 原文本切分的词
     */
    public static synchronized List<String> splitText(String text) {
        List<String> words = new ArrayList<>(text.length()/2);
        Dictionary dic = Dictionary.getInstance();
        MMSeg mmSeg = new MMSeg(new StringReader(text), new ComplexSeg(dic));
        Word word;
        try {
            while ((word = mmSeg.next()) != null) {
                String w = word.getString();
                words.add(w);
            }
        } catch (IOException e) {
            LoggerUtil.getLogger().info(e);
        }
        return words;
    }
}
