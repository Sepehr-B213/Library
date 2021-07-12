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
            "رمز عبور و تایید رمز عبور مطابقت ندارد !",
            "ثبت نام با نام کاربری %d با موفقیت انجام شد"
    };

    public static String[] addBookPageErrors = {
            "لطفا فیلد های خالی را پر کنید.",
            "کتاب شما با موفقیت به فهرست کتاب ها افزوده شد.",
            "متاسفانه مشکلی در افزودن کتاب به فهرست به وجود آمد."
    };
}
