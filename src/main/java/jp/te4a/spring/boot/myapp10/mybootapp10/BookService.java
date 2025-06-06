package jp.te4a.spring.boot.myapp10.mybootapp10;

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
    
}
