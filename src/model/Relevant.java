
/*
        az in class ke vizhegi haaye aan be soorat static tarif shodeh ast dar 2 ja estefadeh kardeh im :

        1 : payaam haai ke be karbar namayesh dade mishavad dar in classs tarif mishavad ta dar soort tose eh narmafzar
        baraye zaban haye digar ya kar haaaye digar aasaan tar shavad.(ossol mohandesi)

        2 : dar hengam login yek karbar hoviayat in shakhs dar in class save mishavad ta dar noghaat mokhtalef project
        betavaanim az aan estefadeh konim .
 */


package model;

import javafx.stage.Stage;

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
            "رمز عبور و تایید رمز عبور مطابقت ندارد !",
            "ثبت نام با نام کاربری %d با موفقیت انجام شد"
    };
  

    public static String[] addBookPageErrors = { // arrays of errors for BookPage
            "لطفا فیلد های خالی را پر کنید.",
            "کتاب شما با موفقیت به فهرست کتاب ها افزوده شد.",
            "متاسفانه مشکلی در افزودن کتاب به فهرست به وجود آمد."



    public static String[] AddLibrarianPageErrors = { // arrays of errors for LibrarianPage
            "فیلد های خالی را پر کنید",
            "رمز و تایید رمز مطابقت ندارد",
            "ثبت نام %d انجام شد"
    };

    public static String[] settingPageErrors = { // arrays of errors for SettingPage
            "فیلد های خالی را پر کنید",
            "رمز عبور و تایید رمز عبور مطابقت ندارد"
    };
}
