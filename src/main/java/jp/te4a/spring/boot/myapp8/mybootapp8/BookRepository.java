package jp.te4a.spring.boot.myapp8.mybootapp8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    
    //DBの代わりにMapにデータを保存
    private final ConcurrentMap<Integer, BookBean> bookMap = new ConcurrentHashMap<>();
    private int BOOK_ID = 1;

    //IDを発行するメソッド
    public int getBookId(){
        return BOOK_ID++;
    }

    //保存用メソッド
    public BookBean save(BookBean bookBean) {
        return bookMap.put(bookBean.getId(), bookBean);
    }

    //削除用メソッド
    /*public void delete(Integer bookId) {
        bookMap.remove(bookId);
    }
    */

    //（全件）取得用メソッド
    /*public List<BookBean> findAll() {
        return new ArrayList<>(bookMap.values());
    }
    */

    //作成用メソッド
    public BookBean create(BookBean bookBean) {
        return bookMap.put(bookBean.getId(), bookBean);
    }

    //更新用メソッド
    public BookBean update(BookBean updateBookBean) {
        BookBean bookBean = bookMap.get(updateBookBean.getId());
        BeanUtils.copyProperties(updateBookBean, bookBean);
        return bookBean;
    }

    //削除用メソッド
    public void delete(Integer bookId) {
        bookMap.remove(bookId);
    }

    //取得（全件）用メソッド
    public List<BookBean> findAll() {
        return new ArrayList<>(bookMap.values());
    }

    //取得（１件）用メソッド
    public BookBean findOne(Integer id) {
        return bookMap.get(id);
    }

    /*DBを使う場合、上記メソッドはSQLの実行に置き換わる
     * 例）　保存用メソッド　＝　INSERT構文
     * 　　　取得用メソッド　＝　SELECT構文　の実行
     */
}
