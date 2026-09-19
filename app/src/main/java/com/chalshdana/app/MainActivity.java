package com.chalshdana.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    LinearLayout root, content;
    TextView progress, coins, questionText, picture;
    int index = 0, score = 0, coin = 50;
    boolean answered = false;
    ArrayList<Question> questions = new ArrayList<>();

    static class Question {
        String q, icon; String[] a;
        int correct;
        Question(String q, String icon, String[] a, int correct){this.q=q;this.icon=icon;this.a=a;this.correct=correct;}
    }

    final String[][] facts = {
        {"پایتخت فرانسه کدام شهر است؟","🗼","پاریس","رم","برلین","مادرید"},
        {"بزرگ‌ترین سیاره منظومه شمسی کدام است؟","🪐","مشتری","زمین","مریخ","زهره"},
        {"بلندترین کوه جهان چه نام دارد؟","🏔️","اورست","دماوند","کی۲","آلپ"},
        {"آب از ترکیب کدام دو عنصر ساخته شده است؟","💧","هیدروژن و اکسیژن","کربن و اکسیژن","نیتروژن و هیدروژن","آهن و اکسیژن"},
        {"سراینده شاهنامه چه کسی است؟","📜","فردوسی","حافظ","سعدی","مولوی"},
        {"نزدیک‌ترین سیاره به خورشید کدام است؟","☀️","عطارد","زهره","زمین","مریخ"},
        {"بزرگ‌ترین اقیانوس زمین کدام است؟","🌊","اقیانوس آرام","اطلس","هند","منجمد شمالی"},
        {"کدام حیوان پستاندار است؟","🦁","دلفین","کوسه","مار","لاک‌پشت"},
        {"سیاره سرخ به چه نامی شناخته می‌شود؟","🔴","مریخ","زحل","نپتون","اورانوس"},
        {"کدام کشور به شکل چکمه شناخته می‌شود؟","🇮🇹","ایتالیا","اسپانیا","یونان","پرتغال"},
        {"واحد اندازه‌گیری شدت جریان برق چیست؟","⚡","آمپر","ولت","وات","اهم"},
        {"کدام گاز بیشترین سهم جو زمین را دارد؟","🌍","نیتروژن","اکسیژن","هیدروژن","دی‌اکسیدکربن"},
        {"کدام اندام خون را در بدن پمپاژ می‌کند؟","❤️","قلب","ریه","کبد","کلیه"},
        {"پایتخت ایران کدام شهر است؟","🇮🇷","تهران","شیراز","اصفهان","تبریز"},
        {"کدام بنا در اصفهان قرار دارد؟","🏛️","سی‌وسه‌پل","تخت جمشید","برج میلاد","ارگ بم"},
        {"تخت جمشید در کدام استان ایران قرار دارد؟","🏺","فارس","یزد","کرمان","خوزستان"},
        {"کدام دریاچه در شمال‌غرب ایران قرار دارد؟","🧂","ارومیه","هامون","بختگان","زریوار"},
        {"واحد پول ژاپن چیست؟","💴","ین","یورو","وون","دلار"},
        {"کدام سیاره حلقه‌های مشهور دارد؟","💫","زحل","مریخ","عطارد","زمین"},
        {"اولین ماهواره مصنوعی زمین چه نام داشت؟","🛰️","اسپوتنیک ۱","آپولو ۱۱","وویجر ۱","میر"},
        {"کدام ساز ایرانی زهی است؟","🎵","تار","نی","سنتور","دف"},
        {"نقاشی مونالیزا اثر چه کسی است؟","🎨","لئوناردو داوینچی","ون‌گوگ","پیکاسو","مونه"},
        {"کدام جانور سریع‌ترین حیوان خشکی است؟","🐆","یوزپلنگ","شیر","اسب","گرگ"},
        {"کدام پرنده نمی‌تواند پرواز کند؟","🐧","پنگوئن","عقاب","شاهین","کبوتر"},
        {"کدام ماده در دمای اتاق مایع است؟","🧪","جیوه","آهن","مس","آلومینیوم"},
        {"عدد اتمی هیدروژن چند است؟","⚛️","۱","۲","۶","۸"},
        {"کدام رنگ از ترکیب آبی و زرد به دست می‌آید؟","🎨","سبز","نارنجی","بنفش","صورتی"},
        {"چند قاره در جهان وجود دارد؟","🌎","۷","۵","۶","۸"},
        {"کدام کشور بیشترین مساحت را دارد؟","🗺️","روسیه","کانادا","چین","آمریکا"},
        {"کدام شهر به شهر عشق و هنر در فرانسه مشهور است؟","🇫🇷","پاریس","لیون","نیس","مارسی"},
        {"کدام اندام وظیفه تصفیه خون را بر عهده دارد؟","🫘","کلیه","معده","مغز","پوست"},
        {"کدام بخش گیاه بیشتر فتوسنتز می‌کند؟","🌿","برگ","ریشه","گل","دانه"},
        {"نور خورشید تقریباً چه مدت طول می‌کشد تا به زمین برسد؟","☀️","۸ دقیقه","۱ دقیقه","۳۰ دقیقه","۱ ساعت"},
        {"کدام فلز نماد شیمیایی Au دارد؟","🥇","طلا","نقره","مس","آهن"},
        {"کدام فلز نماد شیمیایی Ag دارد؟","🥈","نقره","طلا","آهن","روی"},
        {"کدام سیاره بزرگ‌ترین قمر شناخته‌شده منظومه شمسی را دارد؟","🌙","مشتری","زمین","مریخ","زهره"},
        {"کدام ورزش با راکت و توپ پر و بال انجام می‌شود؟","🏸","بدمینتون","فوتبال","شنا","بوکس"},
        {"در فوتبال هر تیم چند بازیکن داخل زمین دارد؟","⚽","۱۱","۹","۱۰","۱۲"},
        {"بازی شطرنج روی صفحه چند خانه دارد؟","♟️","۶۴","۵۶","۷۲","۸۱"},
        {"کدام اقیانوس بین آفریقا و استرالیا قرار دارد؟","🌊","هند","آرام","اطلس","منجمد جنوبی"},
        {"کدام کشور اهرام جیزه را دارد؟","🔺","مصر","مکزیک","هند","عراق"},
        {"زبان رسمی برزیل چیست؟","🇧🇷","پرتغالی","اسپانیایی","انگلیسی","فرانسوی"},
        {"کدام شهر در ایران به نصف جهان معروف است؟","🕌","اصفهان","شیراز","رشت","قم"},
        {"کدام شهر ایران به شهر شعر و باغ‌ها مشهور است؟","🌹","شیراز","تبریز","کرمان","قم"},
        {"کدام کویر در ایران بسیار شناخته‌شده است؟","🏜️","کویر لوت","کویر آتاکاما","صحرا","گبی"},
        {"کدام حیوان نماد مشهور استرالیاست؟","🦘","کانگورو","پاندا","لاما","ببر"},
        {"کدام حیوان بیشترین عمر را در میان لاک‌پشت‌ها دارد؟","🐢","لاک‌پشت غول‌پیکر","خرگوش","روباه","گوزن"},
        {"کدام سیاره به زمین نزدیک‌تر است در میان همسایه‌های داخلی؟","🌌","زهره","مشتری","زحل","نپتون"},
        {"کدام ستاره مرکز منظومه شمسی است؟","⭐","خورشید","شعرای یمانی","قطبی","شباهنگ"},
        {"کدام دستگاه برای اندازه‌گیری دماست؟","🌡️","دماسنج","فشارسنج","قطب‌نما","تراز"},
        {"کدام دستگاه فشار هوا را اندازه می‌گیرد؟","🌤️","فشارسنج","دماسنج","ترازو","قطب‌نما"},
        {"کدام بخش مغز بیشتر با تعادل و هماهنگی حرکتی مرتبط است؟","🧠","مخچه","مخ","نخاع","هیپوفیز"},
        {"کدام ویتامین با نور خورشید در بدن ساخته می‌شود؟","🌞","ویتامین د","ویتامین ث","ویتامین ب۱۲","ویتامین آ"},
        {"کدام ماده برای تنفس انسان ضروری است؟","🫁","اکسیژن","هلیوم","نیتروژن خالص","متان"},
        {"کدام سیاره دورترین سیاره اصلی منظومه شمسی است؟","🔵","نپتون","زحل","اورانوس","مریخ"},
        {"کدام شهر با برج ایفل شناخته می‌شود؟","🗼","پاریس","لندن","رم","وین"},
        {"پایتخت ایتالیا چیست؟","🏛️","رم","میلان","ونیز","فلورانس"},
        {"پایتخت ژاپن چیست؟","🗾","توکیو","کیوتو","اوساکا","هیروشیما"},
        {"پایتخت استرالیا چیست؟","🦘","کانبرا","سیدنی","ملبورن","پرت"},
        {"کدام کشور به سرزمین آفتاب تابان مشهور است؟","🌅","ژاپن","چین","کره جنوبی","تایلند"},
        {"کدام اقیانوس کوچک‌ترین اقیانوس جهان است؟","🧊","منجمد شمالی","هند","اطلس","آرام"},
        {"کدام قاره سردترین قاره جهان است؟","❄️","جنوبگان","اروپا","آسیا","آفریقا"},
        {"کدام عنصر برای ساخت مداد در مغز مداد به کار می‌رود؟","✏️","گرافیت","طلا","آهن","نمک"},
        {"کدام عدد اول است؟","🔢","۱۷","۲۱","۲۵","۲۷"},
        {"حاصل ۱۲ ضربدر ۸ چند است؟","➗","۹۶","۸۶","۱۰۶","۱۱۲"},
        {"حاصل ۱۵ جمع با ۲۷ چند است؟","➕","۴۲","۳۲","۴۴","۵۲"},
        {"مجموع زوایای یک مثلث چند درجه است؟","📐","۱۸۰","۹۰","۲۷۰","۳۶۰"},
        {"کدام شکل سه ضلع دارد؟","🔺","مثلث","مربع","پنج‌ضلعی","دایره"},
        {"کدام ماه ۲۸ روز دارد و گاهی ۲۹ روز؟","📅","فوریه","آوریل","ژوئن","نوامبر"},
        {"کدام ابزار جهت شمال را نشان می‌دهد؟","🧭","قطب‌نما","دماسنج","تلسکوپ","میکروسکوپ"},
        {"تلسکوپ بیشتر برای مشاهده چه چیزی استفاده می‌شود؟","🔭","جرم‌های آسمانی","سلول‌ها","صدا","دما"},
        {"میکروسکوپ برای دیدن چه چیزهایی مناسب است؟","🔬","اجسام بسیار ریز","ستاره‌ها","کوه‌ها","ابرها"},
        {"کدام اختراع امکان تماس بی‌سیم با اینترنت را فراهم می‌کند؟","📱","وای‌فای","چراغ قوه","بلندگو","چاپگر"}
    };

    @Override public void onCreate(Bundle b){super.onCreate(b); getWindow().setStatusBarColor(Color.rgb(5,12,38)); buildQuestions(); showHome();}

    String fa(int n){String s=""+n; String en="0123456789"; String fa="۰۱۲۳۴۵۶۷۸۹"; StringBuilder r=new StringBuilder(); for(char c:s.toCharArray()){int i=en.indexOf(c); r.append(i>=0?fa.charAt(i):c);} return r.toString();}
    int dp(int x){return (int)(x*getResources().getDisplayMetrics().density+0.5f);}
    TextView text(String s,float size){TextView v=new TextView(this);v.setText(s);v.setTextSize(size);v.setTextColor(Color.WHITE);v.setGravity(Gravity.CENTER);v.setTypeface(Typeface.DEFAULT,Typeface.BOLD);v.setPadding(dp(12),dp(8),dp(12),dp(8));v.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);return v;}
    GradientDrawable bg(int c1,int c2,float r){GradientDrawable g=new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{c1,c2});g.setCornerRadius(dp((int)r));g.setStroke(dp(1),Color.argb(110,255,255,255));return g;}
    Button button(String s){Button b=new Button(this);b.setText(s);b.setTextSize(17);b.setTextColor(Color.WHITE);b.setAllCaps(false);b.setGravity(Gravity.CENTER);b.setTypeface(Typeface.DEFAULT,Typeface.BOLD);b.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);b.setPadding(dp(10),0,dp(10),0);b.setBackground(bg(Color.rgb(18,91,190),Color.rgb(10,45,112),26));LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,dp(58));p.setMargins(dp(8),dp(7),dp(8),dp(7));b.setLayoutParams(p);return b;}
    void base(int c1,int c2){root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setGravity(Gravity.CENTER_HORIZONTAL);root.setPadding(dp(14),dp(18),dp(14),dp(14));root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);root.setBackground(bg(c1,c2,0));ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);scroll.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);content=new LinearLayout(this);content.setOrientation(LinearLayout.VERTICAL);content.setGravity(Gravity.CENTER_HORIZONTAL);content.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);scroll.addView(content);setContentView(scroll);root=content;}

    void showHome(){base(Color.rgb(5,18,58),Color.rgb(20,4,65));
        TextView icon=text("🧠\nچالش دانا",34); icon.setTextColor(Color.rgb(255,202,62)); root.addView(icon,new LinearLayout.LayoutParams(-1,dp(170)));
        root.addView(text("با دانش، دنیا را بهتر ببین!",19),new LinearLayout.LayoutParams(-1,dp(48)));
        Button start=button("▶   شروع بازی");start.setTextColor(Color.rgb(45,25,0));start.setBackground(bg(Color.rgb(255,218,92),Color.rgb(245,151,22),28));start.setOnClickListener(v->startGame());root.addView(start);
        Button daily=button("✨   چالش ویژه امروز");daily.setOnClickListener(v->startGame());root.addView(daily);
        Button cats=button("▦   دسته‌بندی‌ها");cats.setOnClickListener(v->Toast.makeText(this,"ایران و جهان • تاریخ • علم • فضا • طبیعت • حیوانات • ورزش • هنر • فناوری • منطق",Toast.LENGTH_LONG).show());root.addView(cats);
        Button record=button("🏆   آمار و رکورد من");record.setOnClickListener(v->showResult());root.addView(record);
        root.addView(text("بیش از ۱۰۰۰ سؤال متنوع • تصاویر موضوعی • مراحل جذاب",15),new LinearLayout.LayoutParams(-1,dp(65)));
    }

    void buildQuestions(){questions.clear(); String[] templates={"این دانستنی را می‌دانستی؟ %s","یک سؤال جذاب برای ذهن کنجکاو: %s","اگر خوب دقت کنی، پاسخ %s چیست؟","چالش دانا از تو می‌پرسد: %s","وقت یک آزمون کوتاه است؛ %s","کدام گزینه پاسخ این پرسش است؟ %s","ذهن آماده‌ای؟ %s","در این مرحله با %s روبه‌رو شدی!","یک نکته جالب: %s","می‌توانی درست حدس بزنی؟ %s","سؤال این مرحله: %s","دانش خودت را امتحان کن؛ %s","یک انتخاب هوشمندانه لازم است: %s","آماده‌ای؟ %s","چالش بعدی برای تو: %s"};
        for(int t=0;t<templates.length;t++) for(String[] f:facts){String q=String.format(templates[t],f[0]); questions.add(new Question(q,f[1],new String[]{f[2],f[3],f[4],f[5]},0));}
        Collections.shuffle(questions,new Random(20260919));
    }

    void startGame(){index=0;score=0;coin=50;Collections.shuffle(questions);showQuestion();}

    void showQuestion(){answered=false; Question q=questions.get(index); int[] colors={{6,22,72},{17,8,65},{4,46,69},{45,15,58},{10,38,75},{54,23,10}}[index%6];base(Color.rgb(colors[0],colors[1],colors[2]),Color.rgb(Math.max(0,colors[0]-2),Math.max(0,colors[1]-4),Math.min(80,colors[2]+5)));
        LinearLayout top=new LinearLayout(this);top.setOrientation(LinearLayout.HORIZONTAL);top.setGravity(Gravity.CENTER_VERTICAL);top.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        coins=text("🪙 "+fa(coin),18); coins.setGravity(Gravity.CENTER_RIGHT);top.addView(coins,new LinearLayout.LayoutParams(0,dp(48),1));
        progress=text("مرحله "+fa(index+1)+" از "+fa(questions.size()),16);progress.setGravity(Gravity.CENTER_LEFT);top.addView(progress,new LinearLayout.LayoutParams(0,dp(48),1));root.addView(top);
        ProgressBar pb=new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);pb.setMax(questions.size());pb.setProgress(index+1);pb.setProgressDrawable(bg(Color.rgb(30,210,220),Color.rgb(20,105,190),20));root.addView(pb,new LinearLayout.LayoutParams(-1,dp(9)));
        picture=text(q.icon,62);picture.setBackground(bg(Color.argb(110,20,90,180),Color.argb(70,10,30,90),28));root.addView(picture,new LinearLayout.LayoutParams(-1,dp(145)));
        questionText=text(q.q,21);questionText.setTextColor(Color.WHITE);questionText.setBackground(bg(Color.rgb(12,49,105),Color.rgb(7,25,70),24));LinearLayout.LayoutParams qp=new LinearLayout.LayoutParams(-1,dp(125));qp.setMargins(0,dp(10),0,dp(8));root.addView(questionText,qp);
        for(int i=0;i<4;i++){final int n=i;Button b=button(fa(i+1)+"   "+q.a[i]);b.setOnClickListener(v->answer(n,b));root.addView(b);} 
    }

    void answer(int n,Button chosen){if(answered)return;answered=true;Question q=questions.get(index);boolean ok=n==q.correct;if(ok){score+=10;coin+=5;chosen.setBackground(bg(Color.rgb(15,190,115),Color.rgb(4,105,70),25));chosen.setText("✓  "+chosen.getText());}else{coin=Math.max(0,coin-5);chosen.setBackground(bg(Color.rgb(210,62,82),Color.rgb(110,20,45),25));chosen.setText("✕  "+chosen.getText());}Toast.makeText(this,ok?"✓ پاسخ درست است!  +۱۰ امتیاز":"✕ پاسخ درست نیست",Toast.LENGTH_SHORT).show();new android.os.Handler().postDelayed(()->{index++;if(index<questions.size())showQuestion();else showResult();},1300);}

    void showResult(){base(Color.rgb(8,20,60),Color.rgb(32,7,62));root.addView(text("🏆  پایان چالش",32),new LinearLayout.LayoutParams(-1,dp(90)));root.addView(text("امتیاز شما:  "+fa(score),25));root.addView(text("سکه‌های شما:  "+fa(coin),21));root.addView(text("تعداد سؤال‌ها:  "+fa(questions.size()),18));Button again=button("🔄   دوباره بازی کن");again.setOnClickListener(v->startGame());root.addView(again);Button home=button("⌂   بازگشت به خانه");home.setOnClickListener(v->showHome());root.addView(home);}
}
