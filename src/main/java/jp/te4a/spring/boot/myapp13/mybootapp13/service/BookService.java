package jp.te4a.spring.boot.myapp13.mybootapp13.service;

import jp.te4a.spring.boot.myapp13.mybootapp13.form.BookForm;
import jp.te4a.spring.boot.myapp13.mybootapp13.bean.BookBean;
import jp.te4a.spring.boot.myapp13.mybootapp13.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //自動生成
    @Autowired
    BookRepository bookRepository;

    //追加
    public BookForm save(BookForm bookForm){
        BookBean bookBean = new BookBean();
        BeanUtils.copyProperties(bookForm, bookBean);
        bookRepository.save(bookBean);
        return bookForm;
    }

    //更新
    public BookForm update(BookForm bookForm) {
        BookBean bookBean = new BookBean();
        BeanUtils.copyProperties(bookForm, bookBean);
        bookRepository.save(bookBean);
        return bookForm;
    }

    //削除
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
    
    //1件取得
    public BookForm findOne(Integer id) {
        BookForm bookForm = new BookForm();
        Optional<BookBean> opt = bookRepository.findById(id);
        BeanUtils.copyProperties(opt.get(), bookForm);
        return bookForm;
    }

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
    

    //データはBookFormで扱い、Repositoryを使う時はBookBeanに入れて渡す

    /* 【Service/Repositoryの両方で保存/取得メソッドを作る利点】
        例）保存先がMapでなくDBに変わった場合も、
        RepositoryのメソッドだけSQL実行に変えればよい
        →Serviceクラスは変更なしで済む
    */
}
