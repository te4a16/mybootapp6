package jp.te4a.spring.boot.myapp13.mybootapp13.security;

import jp.te4a.spring.boot.myapp13.mybootapp13.bean.UserBean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class LoginUserDetails extends User{
    private final UserBean user; //認証に使うユーザクラス
    //認証ユーザ作成(コンストラクタ)
    public LoginUserDetails(UserBean userBean,
        boolean accountNonExpried, //アカウント無効か
        boolean credenttialsNonExpired, //認証無効か
        boolean accountNonLocked, //ロック状態か
        //↓認証情報は呼び出し時にリストで設定
        Collection<GrantedAuthority> authorities) {
        //すべて正常(true)でユーザ認証
        super(userBean.getUsername(), userBean.getPassword(),
               true, true, true, true, authorities);
        this.user = userBean;
    }
}
