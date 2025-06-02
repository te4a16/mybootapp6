package jp.te4a.spring.boot.myapp13.mybootapp13.repository;

import jp.te4a.spring.boot.myapp13.mybootapp13.bean.UserBean;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserBean, String>{
    
}
