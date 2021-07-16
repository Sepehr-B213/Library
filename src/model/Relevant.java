
/*
        az in class ke vizhegi haaye aan be soorat static tarif shodeh ast dar 2 ja estefadeh kardeh im :

        1 : payaam haai ke be karbar namayesh dade mishavad dar in classs tarif mishavad ta dar soort tose eh narmafzar
        baraye zaban haye digar ya kar haaaye digar aasaan tar shavad.(ossol mohandesi)

        2 : dar hengam login yek karbar hoviayat in shakhs dar in class save mishavad ta dar noghaat mokhtalef project
        betavaanim az aan estefadeh konim .
 */


package model;

import javafx.stage.Stage;

import java.sql.Date;

public class Relevant { // igad nemoone haaye static az user,admin,book va librarian baraye
    // dastresi aasan dar noghat mokhtalef progect

    public static Stage loginStage = null;

    public static User user;

    public static Admin admin = new Admin(9999, "Admin", "aqws1234");

    public static Librarian librarian;

    public static Book book;

    public static String[] loginPageErrors = { // arrays of errors for LoginPage
            "فیلد های خالی را پر کنید",
            "نام کاربری وارد شده وجود ندارد",
            "رمز وارد شده اشتباه است"
    };


    public static String[] signUpPageErrors = { // arrays of errors for SighUpPage
            "فیلد های خالی را پر کنید",
            "رمز عبور و تایید رمز عبور مطابقت ندارد",
            "ثبت نام با نام کاربری %d با موفقیت انجام شد"
    };


    public static String[] addBookPageErrors = { // arrays of errors for BookPage
            "لطفا فیلد های خالی را پر کنید.",
            "کتاب شما با موفقیت به فهرست کتاب ها افزوده شد.",
            "متاسفانه مشکلی در افزودن کتاب به فهرست به وجود آمد."
    };


    public static String[] AddLibrarianPageErrors = { // arrays of errors for LibrarianPage
            "فیلد های خالی را پر کنید",
            "رمز و تایید رمز مطابقت ندارد",
            "ثبت نام %d انجام شد"
    };

    public static String[] settingPageErrors = { // arrays of errors for SettingPage
            "فیلد های خالی را پر کنید",
            "رمز عبور و تایید رمز عبور مطابقت ندارد",
            "تغییرات با موفقیت اعمال شد"
    };

    public static Date date1(Date date, int dayNumber) {
        if(dayNumber > 0) {
            String dateStr = date.toString();
            int month = Integer.parseInt(dateStr.substring(5, 7));
            int day = Integer.parseInt(dateStr.substring(8, 10));
            int year = Integer.parseInt(dateStr.substring(0, 4));

            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (day + dayNumber > 31) {
                        dayNumber -= 32 - day;
                        day = 1;
                        if (month == 12) {
                            month = 1;
                            year += 1;
                        } else
                            month += 1;
                    } else {
                        day += dayNumber;
                        dayNumber = 0;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day + dayNumber > 30) {
                        dayNumber -= 31 - day;
                        day = 1;
                        month += 1;
                    } else {
                        day += dayNumber;
                        dayNumber = 0;
                    }
                    break;
                case 2:
                    if (year % 4 == 0) {
                        if (day + dayNumber > 29) {
                            dayNumber -= 30 - day;
                            day = 1;
                            month += 1;
                        } else {
                            day += dayNumber;
                            dayNumber = 0;
                        }
                    } else {
                        if (day + dayNumber > 28) {
                            dayNumber -= 29 - day;
                            day = 1;
                            month += 1;
                        } else {
                            day += dayNumber;
                            dayNumber = 0;
                        }
                    }
                    break;
                default:
                    return null;
            }

            String resultDateStr =  String.format("%04d-%02d-%02d", year, month, day);
            return date1(Date.valueOf(resultDateStr), dayNumber);
        }
        else if(dayNumber == 0) {
            return date;
        }
        else {
            return null;
        }
    }
}
