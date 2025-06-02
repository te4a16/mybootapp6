package jp.te4a.spring.boot.myapp13.mybootapp13.service;

import jp.te4a.spring.boot.myapp13.mybootapp13.repository.UserRepository;
import jp.te4a.spring.boot.myapp13.mybootapp13.bean.UserBean;
import jp.te4a.spring.boot.myapp13.mybootapp13.form.UserForm;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserForm create(UserForm userForm){
        //ユーザ作成時にパスワードをエンコードする
        userForm.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));

        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(userForm, userBean); //画面用ユーザ情報(Form)→DB用ユーザ情報(Bean)

        userRepository.save(userBean); //ユーザをDBに追加
        return userForm;

    }
}
