package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import java.util.List; // List コレクションフレームワークをインポートします。

import org.springframework.beans.factory.annotation.Autowired; // @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Controller; // @Controller アノテーションをインポートします。
import org.springframework.ui.Model; // Spring の Model インターフェースをインポートします。
import org.springframework.web.bind.annotation.GetMapping; // @GetMapping アノテーションをインポートします。

import jakarta.servlet.http.HttpSession; // HttpSession クラスをインポートします。
import pb.com.models.entity.Admin; // Admin エンティティをインポートします。
import pb.com.models.entity.Blog; // Blog エンティティをインポートします。
import pb.com.services.BlogService; // BlogService クラスをインポートします。

@Controller // このクラスが Spring MVC のコントローラであることを示します。
public class BlogListController {

    // セッション管理用の HttpSession を自動的に注入
    @Autowired
    private HttpSession session; // HttpSession クラスのインスタンスを注入します。

    // BlogService を自動的に注入
    @Autowired
    private BlogService blogService; // BlogService クラスのインスタンスを注入します。

    // ブログリストページの表示を処理するメソッド
    @GetMapping("/blog/list") // GET リクエストが "/blog/list" にマッピングされるように設定します。
    public String getBlogList(Model model) {
        // セッションからログイン管理者情報を取得
        Admin admin = (Admin) session.getAttribute("loginAdminInfo"); // セッションからログイン中の管理者情報を取得します。

        // 管理者情報がない場合、ログインページにリダイレクト
        if (admin == null) {
            return "redirect:/admin/login"; // ログインしていない場合はログインページにリダイレクトします。
        } else {
            try {
                // 管理者名をモデルに追加
                model.addAttribute("adminName", admin.getName()); // 管理者名をモデルに追加します。

                // 管理者IDに基づいて全ブログを取得し、モデルに追加
                List<Blog> blogList = blogService.selectAllBlog(admin.getAdminId()); // 管理者IDに基づいてブログリストを取得します。
                model.addAttribute("blogList", blogList); // ブログリストをモデルに追加します。

                // ブログリストページを表示
                return "blog_list.html"; // ブログリストページを表示します。
            } catch (Exception e) {
                // 例外が発生した場合、エラーページを表示
                e.printStackTrace(); // 例外のスタックトレースを出力します。
                return "error"; // エラーページを表示します。
            }
        }
    }
}
