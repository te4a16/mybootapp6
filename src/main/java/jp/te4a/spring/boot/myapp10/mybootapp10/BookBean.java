package jp.te4a.spring.boot.myapp10.mybootapp10;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DB(テーブル)と連動
@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBean {
    @Id //主キー指定
    @GeneratedValue //DB側で自動採番
    private Integer id ;
    @Column(nullable = false) //NOT NULL制約指定
    private String title;
    private String writter;
    private String publisher;
    private Integer price;
}
