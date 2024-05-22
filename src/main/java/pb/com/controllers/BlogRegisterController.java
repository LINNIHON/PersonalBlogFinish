package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Controller; // @Controller アノテーションをインポートします。
import org.springframework.ui.Model; // SpringのModelインターフェースをインポートします。
import org.springframework.web.bind.annotation.GetMapping; // @GetMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.PostMapping; // @PostMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.RequestParam; // @RequestParam アノテーションをインポートします。

import pb.com.models.entity.Admin; // Admin エンティティをインポートします。
import pb.com.models.entity.Blog; // Blog エンティティをインポートします。
import pb.com.services.BlogService; // BlogService クラスをインポートします。
import jakarta.servlet.http.HttpSession; // HttpSession クラスをインポートします。

@Controller // このクラスが Spring MVC のコントローラであることを示します。
public class BlogRegisterController {

    // BlogServiceを自動的に注入
    @Autowired
    private BlogService blogService; // BlogService クラスのインスタンスを注入します。
    
    // セッション管理用のHttpSessionを自動的に注入
    @Autowired
    private HttpSession session; // HttpSession クラスのインスタンスを注入します。
    
    // ブログ登録ページの表示を処理するメソッド
    @GetMapping("/blog/register") // GETリクエストが "/blog/register" にマッピングされるように設定します。
    public String getBlogRegisterPage(Model model) {
        // セッションからログイン管理者情報を取得
        Admin admin = (Admin) session.getAttribute("loginAdminInfo"); // セッションからログイン中の管理者情報を取得します。
        
        // 管理者情報がない場合、ログインページにリダイレクト
        if (admin == null) {
            return "redirect:/admin/login"; // ログインしていない場合はログインページにリダイレクトします。
        } else {
            // 管理者名をモデルに追加してブログ登録ページを表示
            model.addAttribute("adminName", admin.getName()); // 管理者名をモデルに追加します。
            return "blog_register.html"; // ブログ登録ページを表示します。
        }
    }
    
    // ブログ登録処理を行うメソッド
    @PostMapping("/blog/register/process") // POSTリクエストが "/blog/register/process" にマッピングされるように設定します。
    public String blogRegisterProcess(@RequestParam String title,
                                      @RequestParam String content) {
        // セッションからログイン管理者情報を取得
        Admin admin = (Admin) session.getAttribute("loginAdminInfo"); // セッションからログイン中の管理者情報を取得します。
        
        // 管理者情報がない場合、ログインページにリダイレクト
        if (admin == null) {
            return "redirect:/admin/login"; // ログインしていない場合はログインページにリダイレクトします。
        } else {
            // 新しいブログオブジェクトを作成して保存
            Blog blog = new Blog(admin, title, content); // 新しいブログオブジェクトを作成します。
            blogService.saveOrUpdateBlog(blog); // ブログを保存または更新します。
            // ブログリストページにリダイレクト
            return "redirect:/blog/list"; // ブログリストページにリダイレクトします。
        }
    }
}
