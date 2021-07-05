package model;

public class Relevant {
    public static Admin admin = new Admin(9999, "Admin", "aqws1234");

    public static String[] loginPageErrors = {
            "فیلد های خالی را پر کنید",
            "نام کاربری وارد شده وجود ندارد",
            "رمز وارد شده اشتباه است"
    };

    public static String[] signUpPageErrors = {
            "رمز عبور و تایید رمز عبور مطابقت ندارد !",
            "ثبت نام با نام کاربری %d با موفقیت انجام شد"
    };

}
