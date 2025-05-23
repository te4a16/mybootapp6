package jp.te4a.spring.boot.myapp8.mybootapp8;

import lombok.Data;
import lombok.NoArgsConstructor;

//画面と連動
@Data
@NoArgsConstructor //引数なしのコンストラクタを自動生成
public class BookForm {
    private Integer id ;
    private String title;
    private String writter;
    private String publisher;
    private Integer price;
}
