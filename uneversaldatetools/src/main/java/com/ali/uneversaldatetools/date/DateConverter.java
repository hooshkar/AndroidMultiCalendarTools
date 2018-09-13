package com.ali.uneversaldatetools.date;

import com.ali.uneversaldatetools.model.DateModel;

/**
 * Created by ali on 9/5/18.
 */

public class DateConverter {

    static boolean IsJalaliLeap(int year) {
        if (year >= 1 && year <= 474) {
            year += 2346;
        } else if (year > 474) {
            year -= 474;
        }

        year = year % 2820;

        year = year % 128;

        year -= year >= 29 ? 29 : 0;

        year = year % 33;

        year -= year >= 5 ? 5 : 0;

        year = year % 4;

        return year == 0;
    }

    static boolean IsGregorianLeap(int year) {
        return year % 400 == 0 || (year % 100 != 0 & year % 4 == 0);
    }

    public static boolean IsHijriLeap(int year) {
        int[] yearsLeap = {2, 5, 7, 10, 13, 15, 18, 21, 24, 26, 29};

        year = year % 30;

        for (int i = 0; i < 11; i++) {
            if (year == yearsLeap[i]) {
                return true;
            }
        }

        return false;
    }


    static int JalaliToDays(int year, int month, int day) {
        return day +
                (month - 1 <= 6 ? (month - 1) * 31 : (month - 1) * 30 + 6) +
                (year - 1) * 365 +
                LeapsJalaliBehind(year - 1);
    }

    static int GregorianToDays(int year, int month, int day) {
        int[] monthDays = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        return day +
                monthDays[month - 1] +
                (year - 1) * 365 +
                LeapsGregorianBehind(year) +
                ((month - 1 >= 2) && IsGregorianLeap(year) ? 1 : 0);
    }

    static int HijriToDays(int year, int month, int day) {
        int[] monthDays = {0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, 325};

        return day + monthDays[month - 1] + (year - 1) * 354 + LeapsHijriBehind(year - 1);
    }

    static DateModel DaysToJalali(int days) {

        DateModel jalali = new DateModel();

        if (days >= 1 & days <= 173125) {
            jalali.year -= 2346;
            days += 856858;
        } else if (days > 173125) {
            jalali.year += 474;
            days -= 173125;
        }

        jalali.year += (days / 1029983) * 2820;

        days = days % 1029983;

        jalali.year += (days / 46751) * 128;

        days = days % 46751;

        if (days >= 10592) {
            jalali.year += 29;

            days -= 10592;

            jalali.year += (days / 12053) * 33;

            days = days % 12053;

            if (days >= 1826) {
                jalali.year += 5;

                days -= 1826;

                jalali.year += (days / 1461) * 4;

                days = days % 1461;
            }
        } else if (days >= 1826) {
            jalali.year += 5;

            days -= 1826;

            jalali.year += (days / 1461) * 4;

            days = days % 1461;
        }

        if (days == 0 & IsJalaliLeap(jalali.year)) {
            jalali.year -= 1;
            days = 366;
        } else if (days == 1460) {
            jalali.year += 3;
            days = 365;
        } else {
            jalali.year += days / 365;
            days = days % 365;
            if (days == 0) {
                jalali.year -= 1;
                days = 365;
            }
        }

        if (days > 336) {
            jalali.month = 11;
            jalali.day = days - 336;
        } else if (days > 306) {
            jalali.month = 10;
            jalali.day = days - 306;
        } else if (days > 276) {
            jalali.month = 9;
            jalali.day = days - 276;
        } else if (days > 246) {
            jalali.month = 8;
            jalali.day = days - 246;
        } else if (days > 216) {
            jalali.month = 7;
            jalali.day = days - 216;
        } else if (days > 186) {
            jalali.month = 6;
            jalali.day = days - 186;
        } else if (days > 155) {
            jalali.month = 5;
            jalali.day = days - 155;
        } else if (days > 124) {
            jalali.month = 4;
            jalali.day = days - 124;
        } else if (days > 93) {
            jalali.month = 3;
            jalali.day = days - 93;
        } else if (days > 62) {
            jalali.month = 2;
            jalali.day = days - 62;
        } else if (days > 31) {
            jalali.month = 1;
            jalali.day = days - 31;
        } else {
            jalali.day = days;
        }

        jalali.year += 1;

        jalali.month += 1;

        return jalali;
    }

    static DateModel DaysToGregorian(int days) {

        DateModel miladi = new DateModel();

        int quadricent = days / 146097;

        days = days % 146097;

        int cent = days / 36524;

        days = days % 36524;

        int quad = days / 1461;

        days = days % 1461;

        miladi.year = (quadricent * 400 + cent * 100 + quad * 4);

        int one = 0;

        if (days == 0 & IsGregorianLeap(miladi.year)) {
            one -= 1;
            days = 366;
        } else if (days == 1460) {
            one = 3;
            days = 365;
        } else {
            one = days / 365;
            days = days % 365;
            if (days == 0) {
                one -= 1;
                days = 365;
            }
        }

        miladi.year += one;

        if (IsGregorianLeap(miladi.year + 1)) {
            days--;
        }
        if (days > 334) {
            miladi.month = 11;
            miladi.day = days - 334;
        } else if (days > 304) {
            miladi.month = 10;
            miladi.day = days - 304;
        } else if (days > 273) {
            miladi.month = 9;
            miladi.day = days - 273;
        } else if (days > 243) {
            miladi.month = 8;
            miladi.day = days - 243;
        } else if (days > 212) {
            miladi.month = 7;
            miladi.day = days - 212;
        } else if (days > 181) {
            miladi.month = 6;
            miladi.day = days - 181;
        } else if (days > 151) {
            miladi.month = 5;
            miladi.day = days - 151;
        } else if (days > 120) {
            miladi.month = 4;
            miladi.day = days - 120;
        } else if (days > 90) {
            miladi.month = 3;
            miladi.day = days - 90;
        } else if (days > 59) {
            miladi.month = 2;
            miladi.day = days - 59;
        } else if (days > 31) {
            miladi.month = 1;
            miladi.day = days - 31;

            if (IsGregorianLeap(miladi.year + 1)) {
                miladi.day++;
            }
        } else if (IsGregorianLeap(miladi.year + 1) & days == 31) {
            miladi.month = 1;
            miladi.day = 1;
        } else {
            miladi.day = days;

            if (IsGregorianLeap(miladi.year + 1)) {
                miladi.day++;
            }
        }

        miladi.year += 1;
        miladi.month += 1;

        return miladi;
    }

    static DateModel DaysToHijri(int days) {

        DateModel hijri = new DateModel();

        hijri.year = days / 10631 * 30;

        days = days % 10631;

        if (days >= 10277) {
            days -= 10277;
            hijri.year += 29;
        } else if (days >= 9214) {
            days -= 9214;
            hijri.year += 26;
        } else if (days >= 8505) {
            days -= 8505;
            hijri.year += 24;
        } else if (days >= 7442) {
            days -= 7442;
            hijri.year += 21;
        } else if (days >= 6379) {
            days -= 6379;
            hijri.year += 18;
        } else if (days >= 5316) {
            days -= 5316;
            hijri.year += 15;
        } else if (days >= 4607) {
            days -= 4607;
            hijri.year += 13;
        } else if (days >= 3544) {
            days -= 3544;
            hijri.year += 10;
        } else if (days >= 2481) {
            days -= 2481;
            hijri.year += 7;
        } else if (days >= 1772) {
            days -= 1772;
            hijri.year += 5;
        } else if (days >= 709) {
            days -= 709;
            hijri.year += 2;
        }

        if (days == 0 & IsHijriLeap(hijri.year)) {
            hijri.year -= 1;
            days = 355;
        } else {
            hijri.year += days / 354;

            days = days % 354;

            if (days == 0) {
                hijri.year -= 1;
                days = 354;
            }
        }

        if (days > 325) {
            hijri.month = 11;
            hijri.day = days - 325;
        } else if (days > 295) {
            hijri.month = 10;
            hijri.day = days - 295;
        } else if (days > 266) {
            hijri.month = 9;
            hijri.day = days - 266;
        } else if (days > 236) {
            hijri.month = 8;
            hijri.day = days - 236;
        } else if (days > 207) {
            hijri.month = 7;
            hijri.day = days - 207;
        } else if (days > 177) {
            hijri.month = 6;
            hijri.day = days - 177;
        } else if (days > 148) {
            hijri.month = 5;
            hijri.day = days - 148;
        } else if (days > 118) {
            hijri.month = 4;
            hijri.day = days - 118;
        } else if (days > 89) {
            hijri.month = 3;
            hijri.day = days - 89;
        } else if (days > 59) {
            hijri.month = 2;
            hijri.day = days - 59;
        } else if (days > 30) {
            hijri.month = 1;
            hijri.day = days - 30;
        } else {
            hijri.day = days;
        }

        hijri.year += 1;
        hijri.month += 1;

        return hijri;
    }


    private static int LeapsJalaliBehind(int year) {
        int leaps = 0;

        if (year >= 1 & year <= 474) {
            year += 2346;

            leaps -= 568;
        } else if (year > 474) {
            year -= 474;

            leaps += 115;
        }

        leaps += year / 2820 * 683;

        year = year % 2820;

        leaps += year / 128 * 31;

        year = year % 128;

        if (year >= 29) {
            year -= 29;

            leaps += 7;

            leaps += year / 33 * 8;

            year = year % 33;

            if (year < 5) {
                return leaps;
            }

            year -= 5;

            leaps += 1;

            leaps += year / 4;
        } else {
            if (year < 5) {
                return leaps;
            }

            year -= 5;

            leaps += 1;

            leaps += year / 4;
        }

        return leaps;
    }

    private static int LeapsGregorianBehind(int year) {
        return ((year - 1) / 4) + ((year - 1) / 400) - ((year - 1) / 100);
    }

    private static int LeapsHijriBehind(int year) {
        int leaps = year / 30 * 11;

        year = year % 30;

        if (year >= 29) leaps += 11;
        else if (year >= 26)
            leaps += 10;
        else if (year >= 24)
            leaps += 9;
        else if (year >= 21)
            leaps += 8;
        else if (year >= 18)
            leaps += 7;
        else if (year >= 15)
            leaps += 6;
        else if (year >= 13)
            leaps += 5;
        else if (year >= 10)
            leaps += 4;
        else if (year >= 7)
            leaps += 3;
        else if (year >= 5)
            leaps += 2;
        else if (year >= 2)
            leaps += 1;
        return leaps;
    }


    static DateModel JalaliToGregorian(int year, int month, int day) {
        return DaysToGregorian(JalaliToDays(year, month, day) + 226895);
    }

    static DateModel JalaliToHijri(int year, int month, int day) {
        return DaysToHijri(JalaliToDays(year, month, day) - 119);
    }

    static DateModel GregorianToJalali(int year, int month, int day) {
        return DaysToJalali(GregorianToDays(year, month, day) - 226895);
    }

    static DateModel GregorianToHijri(int year, int month, int day) {
        return DaysToHijri(GregorianToDays(year, month, day) - 227014);
    }

    static DateModel HijriToJalali(int year, int month, int day) {
        return DaysToJalali(HijriToDays(year, month, day) + 119);
    }

    static DateModel HijriToGregorian(int year, int month, int day) {
        return DaysToGregorian(HijriToDays(year, month, day) + 227014);
    }

}
