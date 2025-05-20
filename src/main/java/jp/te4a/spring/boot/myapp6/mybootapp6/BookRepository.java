package jp.te4a.spring.boot.myapp6.mybootapp6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    
    //DBの代わりにMapにデータを保存
    private final ConcurrentMap<Integer, BookBean> bookMap = new ConcurrentHashMap<>();

    //保存用メソッド
    public BookBean save(BookBean bookBean) {
        return bookMap.put(bookBean.getId(), bookBean);
    }

    //削除用メソッド
    public void delete(Integer bookId) {
        bookMap.remove(bookId);
    }

    //（全件）取得用メソッド
    public List<BookBean> findAll() {
        return new ArrayList<>(bookMap.values());
    }

    /*DBを使う場合、上記メソッドはSQLの実行に置き換わる
     * 例）　保存用メソッド　＝　INSERT構文
     * 　　　取得用メソッド　＝　SELECT構文　の実行
     */
}
