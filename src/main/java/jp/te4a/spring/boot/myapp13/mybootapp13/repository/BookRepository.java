package jp.te4a.spring.boot.myapp13.mybootapp13.repository;

import jp.te4a.spring.boot.myapp13.mybootapp13.bean.BookBean;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookBean, Integer> {
    
    @Query("SELECT X FROM BookBean X ORDER BY X.title")
    List<BookBean> findAllOrderbyTitle();


    /*DBを使う場合、上記メソッドはSQLの実行に置き換わる
     * 例）　保存用メソッド　＝　INSERT構文
     * 　　　取得用メソッド　＝　SELECT構文　の実行
     */
}
