package jp.te4a.spring.boot.myapp8.mybootapp8;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //自動生成
    @Autowired
    BookRepository bookRepository;

    //データはBookFormで扱い、Repositoryを使う時はBookBeanに入れて渡す
    
    //追加処理
    public BookForm create(BookForm bookForm) {
        bookForm.setId(bookRepository.getBookId());
        BookBean bookBean = new BookBean();
        BeanUtils.copyProperties(bookForm, bookBean);
        bookRepository.create(bookBean);
        return bookForm;
    }

    //更新処理
    public BookForm update(BookForm bookForm) {
        BookBean bookBean = new BookBean();
        BeanUtils.copyProperties(bookForm, bookBean);
        bookRepository.update(bookBean);
        return bookForm;
    }

    //削除処理
    public void delete(Integer id) {  bookRepository.delete(id); }

    //取得処理（全件）
    public List<BookForm> findAll() {
        List<BookBean> beanList = bookRepository.findAll();
        List<BookForm> formList = new ArrayList<BookForm>();
        for(BookBean bookBean: beanList) {
            BookForm bookForm = new BookForm();
            BeanUtils.copyProperties(bookBean, bookForm);
            formList.add(bookForm);
        }
        return formList;
    }

    //取得処理（１件）
    public BookForm findOne(Integer id) {
        BookBean bookBean = bookRepository.findOne(id);
        BookForm bookForm = new BookForm();
        BeanUtils.copyProperties(bookBean, bookForm);
        return bookForm;
    }

    //保存処理
    // public BookForm save(BookForm bookForm) {
    //     BookBean bookBean = new BookBean();
    //     BeanUtils.copyProperties(bookForm, bookBean);
    //     bookRepository.save(bookBean);
    //     return bookForm;
    // }

    //保存用メソッド
    /*
    public BookBean save(BookBean bookBean) {
        return bookRepository.save(bookBean);
    }
    */

    //（全件）取得用メソッド
    /*
    public List<BookBean> findAll() {
        return bookRepository.findAll();
    }
    */

    /* 【Service/Repositoryの両方で保存/取得メソッドを作る利点】
        例）保存先がMapでなくDBに変わった場合も、
        RepositoryのメソッドだけSQL実行に変えればよい
        →Serviceクラスは変更なしで済む
    */
}
