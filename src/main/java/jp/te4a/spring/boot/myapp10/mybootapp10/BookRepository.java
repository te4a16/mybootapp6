package jp.te4a.spring.boot.myapp10.mybootapp10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.BeanUtils;
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
