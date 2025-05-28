package jp.te4a.spring.boot.myapp11.mybootapp11;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

//画面と連動
@Data
@NoArgsConstructor //引数なしのコンストラクタを自動生成
public class BookForm {
    private Integer id ;
    @NotNull
    @Size(min = 3)
    private String title;
    @Size(min = 3, max = 20)
    private String writter;
    private String publisher;
    @Min(0)
    private Integer price;
}
