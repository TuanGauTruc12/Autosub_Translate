package com.example.autosub_translate.controller;

import com.example.autosub_translate.models.Languege;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
/**
 *
 * @author ANH_TUAN
 */
public class LanguageController {
    private JComboBox<Languege> cbLanguegeList;

    public LanguageController(JComboBox<Languege> cbLanguegeList) {
        this.cbLanguegeList = cbLanguegeList;
    }

    public JComboBox<Languege> getCbLanguegeList() {
        return cbLanguegeList;
    }

    public void setCbLanguegeList(JComboBox<Languege> cbLanguegeList) {
        this.cbLanguegeList = cbLanguegeList;
    }
    
    public void getListLanguage(){
        List<Languege> list = new ArrayList<>();
        list.add(new Languege("af", "Afrikaans"));
        list.add(new Languege("ar", "Arabic"));
        list.add(new Languege("az", "Azerbaijani"));
        list.add(new Languege("be", "Belarusian"));
        list.add(new Languege("bg", "Bulgarian"));
        list.add(new Languege("bn", "Bengali"));
        list.add(new Languege("bs", "Bosnian"));
        list.add(new Languege("ca", "Catalan"));
        list.add(new Languege("ceb", "Cebuano"));
        list.add(new Languege("cs", "Czech"));
        list.add(new Languege("cy", "Welsh"));
        list.add(new Languege("da", "Danish"));
        list.add(new Languege("de", "German"));
        list.add(new Languege("el", "Greek"));
        list.add(new Languege("en", "English"));
        list.add(new Languege("eo", "Esperanto"));
        list.add(new Languege("es", "Spanish"));
        list.add(new Languege("et", "Estonian"));
        list.add(new Languege("eu", "Basque"));
        list.add(new Languege("fa", "Persian"));
        list.add(new Languege("fi", "Finnish"));
        list.add(new Languege("fr", "French"));
        list.add(new Languege("ga", "Irish"));
        list.add(new Languege("gl", "Galician"));
        list.add(new Languege("gu", "Gujarati"));
        list.add(new Languege("ha", "Hausa"));
        list.add(new Languege("hi", "Hindi"));
        list.add(new Languege("hmn", "Hmong"));
        list.add(new Languege("hr", "Croatian"));
        list.add(new Languege("ht", "Haitian Creole"));
        list.add(new Languege("hu", "Hungarian"));
        list.add(new Languege("hy", "Armenian"));
        list.add(new Languege("id", "Indonesian"));
        list.add(new Languege("ig", "Igbo"));
        list.add(new Languege("is", "Icelandic"));
        list.add(new Languege("it", "Italian"));
        list.add(new Languege("iw", "Hebrew"));
        list.add(new Languege("ja", "Japanese"));
        list.add(new Languege("jw", "Javanese"));
        list.add(new Languege("ka", "Georgian"));
        list.add(new Languege("kk", "Kazakh"));
        list.add(new Languege("km", "Khmer"));
        list.add(new Languege("kn", "Kannada"));
        list.add(new Languege("ko", "Korean"));
        list.add(new Languege("la", "Latin"));
        list.add(new Languege("lo", "Lao"));
        list.add(new Languege("lt", "Lithuanian"));
        list.add(new Languege("lv", "Latvian"));
        list.add(new Languege("mg", "Malagasy"));
        list.add(new Languege("mi", "Maori"));
        list.add(new Languege("mk", "Macedonian"));
        list.add(new Languege("ml", "Malayalam"));
        list.add(new Languege("mn", "Mongolian"));
        list.add(new Languege("mr", "Marathi"));
        list.add(new Languege("ms", "Malay"));
        list.add(new Languege("mt", "Maltese"));
        list.add(new Languege("my", "Myanmar (Burmese)"));
        list.add(new Languege("ne", "Nepali"));
        list.add(new Languege("nl", "Dutch"));
        list.add(new Languege("no", "Norwegian"));
        list.add(new Languege("ny", "Chichewa"));
        list.add(new Languege("pa", "Punjabi"));
        list.add(new Languege("pl", "Polish"));
        list.add(new Languege("pt", "Portuguese"));
        list.add(new Languege("ro", "Romanian"));
        list.add(new Languege("ru", "Russian"));
        list.add(new Languege("si", "Sinhala"));
        list.add(new Languege("sk", "Slovak"));
        list.add(new Languege("sl", "Slovenian"));
        list.add(new Languege("so", "Somali"));
        list.add(new Languege("sq", "Albanian"));
        list.add(new Languege("sr", "Serbian"));
        list.add(new Languege("st", "Sesotho"));
        list.add(new Languege("su", "Sudanese"));
        list.add(new Languege("sv", "Swedish"));
        list.add(new Languege("sw", "Swahili"));
        list.add(new Languege("ta", "Tamil"));
        list.add(new Languege("te", "Telugu"));
        list.add(new Languege("tg", "Tajik"));
        list.add(new Languege("th", "Thai"));
        list.add(new Languege("tl", "Filipino"));
        list.add(new Languege("tr", "Turkish"));
        list.add(new Languege("uk", "Ukrainian"));
        list.add(new Languege("ur", "Urdu"));
        list.add(new Languege("uz", "Uzbek"));
        list.add(new Languege("vi", "Vietnamese"));
        list.add(new Languege("yi", "Yiddish"));
        list.add(new Languege("yo", "Yoruba"));
        list.add(new Languege("zh-CN", "Chinese (Simplified)"));
        list.add(new Languege("zh-TW", "Chinese (Traditional)"));
        list.add(new Languege("zu", "Zulu"));
        for (Languege languege : list) {
            cbLanguegeList.addItem(languege);
        }
    }
}
