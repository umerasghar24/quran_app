package com.example.quran_app.services

import com.example.quran_app.models.ParahNames
import com.example.quran_app.models.SurahNames

object DataServices {




    val parahs= listOf<ParahNames>(
        ParahNames(1,"Alif laam meem آلم",148,1,"ic_juz1"),
        ParahNames(2,"Sayaqulu سيقول السفهاء",111,20,"ic_juz2"),
        ParahNames(3,"Tilka ’r-Rusulu تلك الرسل",126,38,"ic_juz3"),
        ParahNames(4,"Lan Tana Loo لن تنالوا  ",131,56,"ic_juz4"),
        ParahNames(5," Wal Mohsanat والمحصنات",124,74,"ic_juz5"),
        ParahNames(6,"Yuhibbullah لا يحب الله",110,92,"ic_juz6"),
        ParahNames(7,"Wa Iza Samiu وإذا سمعوا",149,110,"ic_juz7"),
        ParahNames(8,"Wa Lau Annana ولو أننا",142,128,"ic_juz8"),
        ParahNames(9,"Qalal Malao قال الملأ",159,146,"ic_juz9"),
        ParahNames(10,"Wa A'lamu واعلموا",127,164,"ic_juz10"),
        ParahNames(11,"Yatazeroon يعتذرون",151,182,"ic_juz11"),
        ParahNames(12,"Wa Mamin Da'abat ومامن دابة",170,200,"ic_juz12"),
        ParahNames(13," Wa Ma Ubrioo وما أبرئ",154,218,"ic_juz13"),
        ParahNames(14,"Rubama ربما",227,237,"ic_juz14"),
        ParahNames(15," Subhanallazi سبحٰن الذیٓ",185,254,"ic_juz15"),
        ParahNames(16," Qal Alam قال ألم",269,272,"ic_juz16"),
        ParahNames(17," Aqtarabo اقترب للناس",190,290,"ic_juz17"),
        ParahNames(18," Qadd Aflaha قد أفلح",202,308,"ic_juz18"),
        ParahNames(19,"Wa Qalallazina وقال الذين",339,327,"ic_juz19"),
        ParahNames(20,"A'man Khalaq امن خلق",171,344,"ic_juz20"),
        ParahNames(21,"Utlu Ma Oohi اتل مآ اوحی",178,363,"ic_juz21"),
        ParahNames(22,"Wa Manyaqnut ومن يقنت",169,381,"ic_juz22"),
        ParahNames(23,"Wa Mali ومالی",357,398,"ic_juz23"),
        ParahNames(24,"Faman Azlam فمن أظلم",175,416,"ic_juz24"),
        ParahNames(25," Elahe Yuruddo إليه يرد",246,434,"ic_juz25"),
        ParahNames(26,"Ha'a Meem حـم",195,452,"ic_juz26"),
        ParahNames(27,"Qala Fama Khatbukum قال فما خطبكم",399,470,"ic_juz27"),
        ParahNames(28,"Qadd Sami Allah قد سمع اللہ",137,488,"ic_juz28"),
        ParahNames(29,"Tabarakallazi تبٰرک الذی",431,508,"ic_juz29"),
        ParahNames(30,"Amma Yatasa'aloon عم",564,528,"ic_juz30")


    )
    val surah= listOf<SurahNames>(
        SurahNames(1,"Surah Fatiha سورة الفاتحة",7,1,"ic_1"),
        SurahNames(2,"Surah Al-Baqara اﻟﺒﻘﺮﺓ",286,2,"ic_2"),
        SurahNames(3,"Surah Aal-i-Imraan اۤل عمران",200,45,"ic_3"),
        SurahNames(4,"Surah An-Nisa اﻟﻨﺴﺄ",176,69,"ic_4"),
        SurahNames(5,"Al-Ma’idah المائدة",286,96,"ic_5"),
        SurahNames(6," Al-An’am الأنعام",200,115,"ic_6"),
        SurahNames(7,"Al-A’raf الأعراف",7,136,"ic_7"),
        SurahNames(8,"Al-Anfal الأنفال",286,159,"ic_8"),
        SurahNames(9,"At-Taubah التوبة",200,168,"ic_9"),
        SurahNames(10,"Yunus يونس",7,187,"ic_10"),
        SurahNames(11,"Hud هود",286,199,"ic_11"),
        SurahNames(12,"Yusuf يوسف",200,212,"ic_12"),
        SurahNames(13,"Ar-Ra’d الرعد",7,224,"ic_13"),
        SurahNames(14,"Ibrahim ابراهيم",286,230,"ic_14"),
        SurahNames(15,"Al-Hijr الحجر",200,235,"ic_15"),
        SurahNames(16,"An-Nahl النحل",7,240,"ic_16"),
        SurahNames(17,"Al-Isra الإسرﺃ",286,254,"ic_17"),
        SurahNames(18,"Surah Al-Kahf الكهف",200,264,"ic_18"),
        SurahNames(19,"Surah Maryam مريم",7,275,"ic_19"),
        SurahNames(20,"Surah Ta-Ha طه",286,281,"ic_20"),
        SurahNames(21,"Surah Al-Anbiya اﻷﻧﺒﻴﺄ",200,290,"ic_21"),
        SurahNames(22,"Surah  Al-Haj الحج",7,299,"ic_22"),
        SurahNames(23,"Surah Al-Mu’minun المؤمنون",286,308,"ic_23"),
        SurahNames(24,"Surah  An-Nur النور",200,315,"ic_24"),
        SurahNames(25,"Surah Al-Furqan الفرقان",7,324,"ic_25"),
        SurahNames(26,"Surah Ash-Shu’ara’ الشعرﺃ",286,330,"ic_26"),
        SurahNames(27,"Surah An-Naml النمل",200,339,"ic_27"),
        SurahNames(28,"Surah  Al-Qasas القصص",7,347,"ic_28"),
        SurahNames(29,"Surah Al-Ankabut العنكبوت",286,357,"ic_29"),
        SurahNames(30,"Surah Ar-Rum الروم",200,364,"ic_30"),
        SurahNames(31,"Surah Luqman لقمان",7,370,"ic_31"),
        SurahNames(32,"Surah As-Sajdah",286,373,"ic_32"),
        SurahNames(33,"Surah Al-Ahzab",200,376,"ic_33"),
        SurahNames(34,"Surah Saba’ ",7,385,"ic_34"),
        SurahNames(35,"Surah Al-Fatir ",286,391,"ic_35"),
        SurahNames(36,"Surah Ya-Sin",200,396,"ic_36"),
        SurahNames(37,"Surah As-Saffah",7,401,"ic_37"),
        SurahNames(38,"Surah Sad ",286,408,"ic_38"),
        SurahNames(39,"Surah  Az-Zumar ",200,412,"ic_39"),
        SurahNames(40,"Surah  Ghafir ",7,420,"ic_40"),
        SurahNames(41,"Surah Fusilat",286,429,"ic_41"),
        SurahNames(42,"Surah  Ash-Shura ",200,434,"ic_42"),
        SurahNames(43,"Surah  Az-Zukhruf ",7,440,"ic_43"),
        SurahNames(44,"Surah  Ad-Dukhan",286,446,"ic_44"),
        SurahNames(45,"Surah Al-Jathiyah ",200,448,"ic_45"),
        SurahNames(46,"Surah Al-Ahqaf ",7,452,"ic_46"),
        SurahNames(47,"Surah  Muhammad",286,456,"ic_47"),
        SurahNames(48,"Surah Al-Fat’h ",200,460,"ic_48"),
        SurahNames(49,"Surah Al-Hujurat",7,463,"ic_49"),
        SurahNames(50,"Surah Qaf",286,466,"ic_50"),
        SurahNames(51,"Surah Adz-Dhaariyah",200,468,"ic_51"),
        SurahNames(52,"Surah  At-Tur",7,471,"ic_52"),
        SurahNames(53,"Surah  An-Najm",286,473,"ic_53"),
        SurahNames(54,"Surah Al-Qamar",200,475,"ic_54"),
        SurahNames(55,"Surah  Ar-Rahman",7,478,"ic_55"),
        SurahNames(56,"Surah  Al-Waqi’ah ",286,481,"ic_56"),
        SurahNames(57,"Surah  Al-Hadid",200,484,"ic_57"),
        SurahNames(58,"Surah Al-Mujadilah",7,488,"ic_58"),
        SurahNames(59,"Surah Al-Hashr",286,491,"ic_59"),
        SurahNames(60,"Surah  Al-Mumtahanah",200,495,"ic_60"),
        SurahNames(61,"Surah As-Saf ",7,497,"ic_61"),
        SurahNames(62,"Surah Al-Jum’ah",286,499,"ic_62"),
        SurahNames(63,"Surah Al-Munafiqun ",200,500,"ic_63"),
        SurahNames(64,"Surah At-Taghabun ",7,502,"ic_64"),
        SurahNames(65,"SurahAt-Talaq",286,504,"ic_65"),
        SurahNames(66,"Surah  At-Tahrim ",200,506,"ic_66"),
        SurahNames(67,"Surah Al-Mulk",7,508,"ic_67"),
        SurahNames(68,"Surah Al-Qalam",286,510,"ic_68"),
        SurahNames(69,"Surah  Al-Haqqah",200,512,"ic_69"),
        SurahNames(70,"Surah . Al-Ma’arij ",7,514,"ic_70"),
        SurahNames(71,"Surah  Nuh",286,516,"ic_71"),
        SurahNames(72,"Surah Al-Jinn ",200,518,"ic_72"),
        SurahNames(73,"Surah  Al-Muzammil",7,520,"ic_73"),
        SurahNames(74,"Surah  Al-Mudaththir",286,521,"ic_74"),
        SurahNames(75,"Surah  Al-Qiyamah ",200,523,"ic_75"),
        SurahNames(76,"Surah  Al-Insan",7,524,"ic_76"),
        SurahNames(77,"Surah Al-Mursalaat",286,526,"ic_77"),
        SurahNames(78,"Surah An-Naba’ ",286,528,"ic_78"),
        SurahNames(79,"Surah An-Nazi’at",200,529,"ic_79"),
        SurahNames(80,"Surah ‘Abasa",7,530,"ic_80"),
        SurahNames(81,"SurahAt-Takwir",286,532,"ic_81"),
        SurahNames(82,"Surah Al-Infitar",200,532,"ic_82"),
        SurahNames(83,"Surah Al-Mutaffifin",7,533,"ic_83"),
        SurahNames(84,"Surah Al-Inshiqaq",286,534,"ic_84"),
        SurahNames(85,"Surah  Al-Buruj",200,535,"ic_85"),
        SurahNames(86,"Surah  At-Tariq",7,536,"ic_86"),
        SurahNames(87,"Surah  Al-A’la",286,537,"ic_87"),
        SurahNames(88,"Surah  Al-Ghashiyah",200,537,"ic_88"),
        SurahNames(89,"Surah Al-Fajr",7,538,"ic_89"),
        SurahNames(90,"Surah Al-Balad",286,539,"ic_90"),
        SurahNames(91,"Surah  Ash-Shams",200,540,"ic_91"),
        SurahNames(92,"Surah Al-Layl",7,540,"ic_92"),
        SurahNames(93,"Surah Adh-Dhuha",286,541,"ic_93"),
        SurahNames(94,"Surah Al-Inshirah",200,541,"ic_94"),
        SurahNames(95,"Surah At-Tin ",7,542,"ic_95"),
        SurahNames(96,"Surah  Al-‘Alaq",286,542,"ic_96"),
        SurahNames(97,"Surah Al-Qadar",200,543,"ic_97"),
        SurahNames(98,"Surah  Al-Bayinah",7,543,"ic_98"),
        SurahNames(99,"Surah  Az-Zalzalah",286,544,"ic_99"),
        SurahNames(100,"Surah Al-‘Adiyah",200,544,"ic_100"),
        SurahNames(101,"Surah Al-Qari’ah",7,544,"ic_101"),
        SurahNames(102,"Surah At-Takathur",286,545,"ic_102"),
        SurahNames(103,"Surah  Al-‘Asr",200,545,"ic_103"),
        SurahNames(104,"Surah Al-Humazah",7,545,"ic_104"),
        SurahNames(105,"Surah Al-Fil ",286,546,"ic_105"),
        SurahNames(106,"Surah Quraish",200,546,"ic_106"),
        SurahNames(107,"Surah Al-Ma’un ",7,546,"ic_107"),
        SurahNames(108,"Surah Al-Kauthar",286,546,"ic_108"),
        SurahNames(109,"Surah Al-Kafirun",200,547,"ic_109"),
        SurahNames(110,"Surah  An-Nasr",7,547,"ic_110"),
        SurahNames(111,"Surah Al-Masad",286,547,"ic_111"),
        SurahNames(112,"Surah Al-Ikhlaas",286,547,"ic_112"),
        SurahNames(113,"Surah  Al-Falaq",200,548,"ic_113"),
        SurahNames(114,"Surah An-Nas",200,548,"ic_114"),
    )


    fun getparahFromPage(page:Int):ParahNames {
        for (i in 0..parahs.size - 1) {
            if (parahs[i].page > page) {
                if (i==0) return parahs[0]
                return parahs[i - 1]
            }

        }
        return parahs[parahs.size - 1]
    }
    fun getsurahFromPage(page:Int):SurahNames {
        for (i in 0..surah.size - 1) {
            if (surah[i].page > page) {
                if(i==0)return surah[0]
                return surah[i -1]

            }

        }
        return surah[surah.size - 1]
    }


}