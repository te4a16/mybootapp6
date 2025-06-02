package jp.te4a.spring.boot.myapp12.mybootapp12;

import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//エラーチェック用のアノテーション
@Documented
@Constraint(validatedBy = TestValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestValid {
    String param();
    String message() default "input other than {param}.";
    //Classオブジェクトを得る（戻り値とする） メソッドgroups()
    //デフォルト値は空のクラス
    Class<?>[] groups() default {};
    //Payloadクラスを継承したClassオブジェクトを得る
    //（戻り値とする）メソッドpayload()、デフォルト値は空のクラス
    Class<? extends Payload>[] payload() default{};
}