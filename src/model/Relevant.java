package model;

import javafx.stage.Stage;

public class Relevant {

    public static Stage loginStage = null;

    public static User user;

    public static Admin admin = new Admin(9999, "Admin", "aqws1234");

    public static Librarian librarian;

    public static Book book;

    public static String[] loginPageErrors = {
            "فیلد های خالی را پر کنید",
            "نام کاربری وارد شده وجود ندارد",
            "رمز وارد شده اشتباه است"
    };

    public static String[] signUpPageErrors = {
            "فیلد های خالی را پر کنید",
            "رمز عبور و تایید رمز عبور مطابقت ندارد",
            "ثبت نام با نام کاربری %d با موفقیت انجام شد"
    };

    public static String[] addBookPageErrors = {
            "فیلد های خالی را پر کنید",
            "کتاب شما با موفقیت افزوده شد",
            "متاسفانه مشکلی به وجود آمد"
    };

    public static String[] AddLibrarianPageErrors = {
            "فیلد های خالی را پر کنید",
            "رمز و تایید رمز مطابقت ندارد",
            "ثبت نام %d انجام شد"
    };
}
