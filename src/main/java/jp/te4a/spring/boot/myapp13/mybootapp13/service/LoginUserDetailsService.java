package jp.te4a.spring.boot.myapp13.mybootapp13.service;

import jp.te4a.spring.boot.myapp13.mybootapp13.repository.UserRepository;
import jp.te4a.spring.boot.myapp13.mybootapp13.bean.UserBean;
import jp.te4a.spring.boot.myapp13.mybootapp13.security.LoginUserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    @Override
    //認証のため、ユーザ名を指定してDBからユーザ情報取得
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBean> opt = userRepository.findById(username);
        UserBean userBean = opt.orElseThrow(() -> new UsernameNotFoundException("The requested user is not found."));
        return new LoginUserDetails(userBean, true, true, true, getAuthorities(userBean));
    }
    
    private Collection<GrantedAuthority> getAuthorities(UserBean userBean) {
 
        /*List<GrantedAuthority> authList = null;
        if(管理者の条件) {
            authList = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER", 
                "ROLE_OTHER");
        } else if(一般ユーザの条件) {
            authList = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_OTHER");
        } else {
            authList = AuthorityUtils.createAuthorityList("ROLE_OTHER");
        }
        */

        return AuthorityUtils.createAuthorityList("ROLE_USER"); //権限を指定
    }
}
