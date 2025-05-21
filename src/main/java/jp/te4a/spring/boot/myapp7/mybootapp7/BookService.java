package jp.te4a.spring.boot.myapp7.mybootapp7;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //自動生成
    @Autowired
    BookRepository bookRepository;

    //保存用メソッド
    public BookBean save(BookBean bookBean) {
        return bookRepository.save(bookBean);
    }

    //（全件）取得用メソッド
    public List<BookBean> findAll() {
        return bookRepository.findAll();
    }

    /* 【Service/Repositoryの両方で保存/取得メソッドを作る利点】
        例）保存先がMapでなくDBに変わった場合も、
        RepositoryのメソッドだけSQL実行に変えればよい
        →Serviceクラスは変更なしで済む
    */
}
