# Android multi calender tools library for android
android date tools and <b>date picker</b> for tree calenders "Jalali" and "Hijri" and "Gregorian"

# screen shots
todo put screen shots here

# installation
Add it in your root build.gradle at the end of repositories:
~~~Groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
~~~
Add the dependency
~~~Groovy
dependencies {
	   implementation 'TODO'
}
~~~

# how to use
<b>date converter:</b> more
[here](https://github.com/hooshkar/AndroidMultiCalenderTools/wiki/Convert).
~~~java
DateModel hijri = DateConverter.GregorianToHijri(2018, 1, 1);
//hijri.day;
//hijri.month;
//hijri.year;
~~~

<b>date picker:</b> <br>
xml:
~~~xml
<com.ali.uneversaldatetools.datePicker.UDatePicker
        android:id="@+id/date_picker"
        android:layout_width="300dp"
        android:layout_height="350dp"/>
~~~
java:
~~~java
UDatePicker uDatePicker = findViewById(R.id.date_picker);

//push date to show in first time
uDatePicker.ShowDatePicker(dateSystem);

//or use current date
uDatePicker.ShowDatePicker(Calendar.Jalali);

//get on day selected event
uDatePicker.setOnDateSelected((dateSystem, unixTime) -> {
    //do something...
});

//or get date when ever you want
uDatePicker.getSelectedUnixTime();//as a unix time
uDatePicker.getSelectedDate();//as a DateSystem object

~~~

# supported language:
1.persian <br>
2.english <br>
and its easy to add new language see
[here](https://github.com/hooshkar/AndroidMultiCalenderTools/wiki/language).

# todo
1. <s>fix year picker</s>
2. add dialog mode
3. jitpack realse

# LICENCE
under [MIT](https://github.com/hooshkar/AndroidMultiCalenderTools/blob/master/LICENSE) licence
