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
        SurahNames(1,"Al-Fatiha سورة الفاتحة",7,1,"ic_1"),
        SurahNames(2,"Al-Baqara اﻟﺒﻘﺮﺓ",286,2,"ic_2"),
        SurahNames(3,"Aal-i-Imraan اۤل عمران",200,45,"ic_3"),
        SurahNames(4,"An-Nisa اﻟﻨﺴﺄ",176,69,"ic_4"),
        SurahNames(5,"Al-Ma’idah المائدة",120,96,"ic_5"),
        SurahNames(6,"Al-An’am الأنعام",165,115,"ic_6"),
        SurahNames(7,"Al-A’raf الأعراف",206,136,"ic_7"),
        SurahNames(8,"Al-Anfal الأنفال",75,159,"ic_8"),
        SurahNames(9,"At-Taubah التوبة",129,168,"ic_9"),
        SurahNames(10,"Yunus يونس",109,187,"ic_10"),
        SurahNames(11,"Hud هود",123,199,"ic_11"),
        SurahNames(12,"Yusuf يوسف",111,212,"ic_12"),
        SurahNames(13,"Ar-Ra’d الرعد",43,224,"ic_13"),
        SurahNames(14,"Ibrahim ابراهيم",52,230,"ic_14"),
        SurahNames(15,"Al-Hijr الحجر",99,235,"ic_15"),
        SurahNames(16,"An-Nahl النحل",128,240,"ic_16"),
        SurahNames(17,"Al-Isra الإسرﺃ",111,254,"ic_17"),
        SurahNames(18,"Al-Kahf الكهف",110,264,"ic_18"),
        SurahNames(19,"Maryam مريم",98,275,"ic_19"),
        SurahNames(20,"Ta-Ha طه",135,281,"ic_20"),
        SurahNames(21,"Al-Anbiya اﻷﻧﺒﻴﺄ",112,290,"ic_21"),
        SurahNames(22,"Al-Haj الحج",78,299,"ic_22"),
        SurahNames(23,"Al-Mu’minun المؤمنون",118,308,"ic_23"),
        SurahNames(24,"An-Nur النور",64,315,"ic_24"),
        SurahNames(25,"Al-Furqan الفرقان",77,324,"ic_25"),
        SurahNames(26,"Ash-Shu’ara’ الشعرﺃ",227,330,"ic_26"),
        SurahNames(27,"An-Naml النمل",93,339,"ic_27"),
        SurahNames(28,"Al-Qasas القصص",88,347,"ic_28"),
        SurahNames(29,"Al-Ankabut العنكبوت",69,357,"ic_29"),
        SurahNames(30,"Ar-Rum الروم",60,364,"ic_30"),
        SurahNames(31,"Luqman لقمان",34,370,"ic_31"),
        SurahNames(32,"As-Sajdah السجدة",30,373,"ic_32"),
        SurahNames(33,"Al-Ahzab الأحزاب",73,376,"ic_33"),
        SurahNames(34,"Saba’ ﺳﺒﺄ ",54,385,"ic_34"),
        SurahNames(35,"Al-Fatir الفاطر ",45,391,"ic_35"),
        SurahNames(36,"Ya-Sin يٰسن",83,396,"ic_36"),
        SurahNames(37,"As-Saffah الصافات",182,401,"ic_37"),
        SurahNames(38,"Sad صۤ",88,408,"ic_38"),
        SurahNames(39,"Az-Zumar الزمر",75,412,"ic_39"),
        SurahNames(40,"Ghafir غافر",85,420,"ic_40"),
        SurahNames(41,"Fusilat فصلت",54,429,"ic_41"),
        SurahNames(42,"Ash-Shura الشورى",53,434,"ic_42"),
        SurahNames(43,"Az-Zukhruf الزخرف",89,440,"ic_43"),
        SurahNames(44,"Ad-Dukhan الدخان",59,446,"ic_44"),
        SurahNames(45,"Al-Jathiyah الجاثية",37,448,"ic_45"),
        SurahNames(46,"Al-Ahqaf الأحقاف",35,452,"ic_46"),
        SurahNames(47,"Muhammad محمد",38,456,"ic_47"),
        SurahNames(48,"Al-Fat’h الفتح",29,460,"ic_48"),
        SurahNames(49,"Al-Hujurat الحجرات",18,463,"ic_49"),
        SurahNames(50,"Qaf ق",45,466,"ic_50"),
        SurahNames(51,"Adz-Dhaariyah الذاريات",60,468,"ic_51"),
        SurahNames(52,"At-Tur الطور",49,471,"ic_52"),
        SurahNames(53,"An-Najm النجم",62,473,"ic_53"),
        SurahNames(54,"Al-Qamar القمر",55,475,"ic_54"),
        SurahNames(55,"Ar-Rahman الرحمن",78,478,"ic_55"),
        SurahNames(56,"Al-Waqi’ah الواقعة",96,481,"ic_56"),
        SurahNames(57,"Al-Hadid الحديد",29,484,"ic_57"),
        SurahNames(58,"Al-Mujadilah المجادلة",22,488,"ic_58"),
        SurahNames(59,"Al-Hashr الحشر",24,491,"ic_59"),
        SurahNames(60,"Al-Mumtahanah الممتحنة",13,495,"ic_60"),
        SurahNames(61,"As-Saf الصف",14,497,"ic_61"),
        SurahNames(62,"Al-Jum’ah الجمعة",11,499,"ic_62"),
        SurahNames(63,"Al-Munafiqun المنافقون",11,500,"ic_63"),
        SurahNames(64,"At-Taghabun التغابن",18,502,"ic_64"),
        SurahNames(65,"At-Talaq الطلاق",12,504,"ic_65"),
        SurahNames(66,"At-Tahrim التحريم",12,506,"ic_66"),
        SurahNames(67,"Al-Mulk الملك",30,508,"ic_67"),
        SurahNames(68,"Al-Qalam القلم",52,510,"ic_68"),
        SurahNames(69,"Al-Haqqah الحاقة",52,512,"ic_69"),
        SurahNames(70,"Al-Ma’arij المعارج",44,514,"ic_70"),
        SurahNames(71,"Nuh نوح",28,516,"ic_71"),
        SurahNames(72,"Al-Jinn الجن",28,518,"ic_72"),
        SurahNames(73,"Al-Muzammil المزمل",20,520,"ic_73"),
        SurahNames(74,"Al-Mudaththir المدثر",56,521,"ic_74"),
        SurahNames(75,"Al-Qiyamah القيامة",40,523,"ic_75"),
        SurahNames(76,"Al-Insan الإنسان",31,524,"ic_76"),
        SurahNames(77,"Al-Mursalaat المرسلات",50,526,"ic_77"),
        SurahNames(78,"An-Naba’ النبأ",40,528,"ic_78"),
        SurahNames(79,"An-Nazi’at النازعات",46,529,"ic_79"),
        SurahNames(80,"‘Abasa عبس",42,530,"ic_80"),
        SurahNames(81,"At-Takwir التكوير",29,532,"ic_81"),
        SurahNames(82,"Al-Infitar الإنفتار",19,532,"ic_82"),
        SurahNames(83,"Al-Mutaffifin المطففين",36,533,"ic_83"),
        SurahNames(84,"Al-Inshiqaq اﻹنشقاق",25,534,"ic_84"),
        SurahNames(85,"Al-Buruj البروج",22,535,"ic_85"),
        SurahNames(86,"At-Tariq الطارق",17,536,"ic_86"),
        SurahNames(87,"Al-A’la الأعلى",19,537,"ic_87"),
        SurahNames(88,"Al-Ghashiyah الغاشية",26,537,"ic_88"),
        SurahNames(89,"Al-Fajr الفجر",30,538,"ic_89"),
        SurahNames(90,"Al-Balad البلد",20,539,"ic_90"),
        SurahNames(91,"Ash-Shams الشمس",15,540,"ic_91"),
        SurahNames(92,"Al-Layl الليل",21,540,"ic_92"),
        SurahNames(93,"Adh-Dhuha الضحى",11,541,"ic_93"),
        SurahNames(94,"Al-Inshirah اﻹﻧﺸﺮﺡ",8,541,"ic_94"),
        SurahNames(95,"At-Tin التين",8,542,"ic_95"),
        SurahNames(96,"Al-‘Alaq العلق",19,542,"ic_96"),
        SurahNames(97,"Al-Qadar القدر",5,543,"ic_97"),
        SurahNames(98,"Al-Bayinah البينة",8,543,"ic_98"),
        SurahNames(99,"Az-Zalzalah الزلزلة",8,544,"ic_99"),
        SurahNames(100,"Al-‘Adiyah العاديات",11,544,"ic_100"),
        SurahNames(101,"Al-Qari’ah القارعة",11,544,"ic_101"),
        SurahNames(102,"At-Takathur التكاثر",8,545,"ic_102"),
        SurahNames(103,"Al-‘Asr العصر",3,545,"ic_103"),
        SurahNames(104,"Al-Humazah الهمزة",9,545,"ic_104"),
        SurahNames(105,"Al-Fil الفيل",5,546,"ic_105"),
        SurahNames(106,"Quraish قريش",4,546,"ic_106"),
        SurahNames(107,"Al-Ma’un الماعون",7,546,"ic_107"),
        SurahNames(108,"Al-Kauthar الكوثر",3,546,"ic_108"),
        SurahNames(109,"Al-Kafirun الكافرون",6,547,"ic_109"),
        SurahNames(110,"An-Nasr النصر",3,547,"ic_110"),
        SurahNames(111,"Al-Masad المسد",5,547,"ic_111"),
        SurahNames(112,"Al-Ikhlaas الإخلاص",4,547,"ic_112"),
        SurahNames(113,"Al-Falaq الفلق",5,548,"ic_113"),
        SurahNames(114,"An-Nas`الناس",6,548,"ic_114"),
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