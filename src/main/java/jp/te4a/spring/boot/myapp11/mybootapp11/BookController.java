package jp.te4a.spring.boot.myapp11.mybootapp11;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("books") // URL:/booksのHTTPリクエストを処理
public class BookController {
    @Autowired
    BookService bookService;

    // 画面遷移時のデータの受け渡し関数
    @ModelAttribute
    BookForm setUpForm() {
        return new BookForm();
    }

    // /booksにGET要求
    @GetMapping
    String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    // /books/createにPOST要求
    @PostMapping(path="create")
    //検査したい変数に@Validatedを付けて、その結果(エラーの有無)をBindingResultに入れる
    String create(@Validated BookForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }
        bookService.save(form);
        return "redirect:/books";
    }

    // /books/editにパラメタformを含むPOST要求
    @PostMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, BookForm form) {
        BookForm bookForm = bookService.findOne(id);
        BeanUtils.copyProperties(bookForm,  form);
        return "books/edit";
    }

    // /books/editにPOST要求(編集画面で入力した情報を登録)
    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id, @Validated BookForm form, BindingResult result) {
        if(result.hasErrors()){
            return editForm(id, form);
        }
        bookService.update(form);
        return "redirect:/books";
    }

    // /books/deleteにPOST要求
    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    // /books/editにパラメタgoToTopを含むPOST要求
    @PostMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/books";
    }

}
