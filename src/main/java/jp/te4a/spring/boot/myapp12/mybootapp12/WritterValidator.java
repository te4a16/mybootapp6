package jp.te4a.spring.boot.myapp12.mybootapp12;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//エラーチェック処理
public class WritterValidator implements ConstraintValidator<Writter, String>{
    String ok;
    @Override
    public void initialize(Writter wr) { ok = wr.ok();}
    @Override
    public boolean isValid(String in, ConstraintValidatorContext cxt){
        if (in == null) {
            return false;
        }
        System.out.println(in.equals(ok));
        return !in.equals(ok);
    }
}
