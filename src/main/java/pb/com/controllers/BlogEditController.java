package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Controller; // @Controller アノテーションをインポートします。
import org.springframework.ui.Model; // Spring の Model インターフェースをインポートします。
import org.springframework.web.bind.annotation.GetMapping; // @GetMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.PathVariable; // @PathVariable アノテーションをインポートします。
import org.springframework.web.bind.annotation.PostMapping; // @PostMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.RequestParam; // @RequestParam アノテーションをインポートします。

import jakarta.servlet.http.HttpSession; // HttpSession クラスをインポートします。
import pb.com.models.entity.Admin; // Admin エンティティをインポートします。
import pb.com.models.entity.Blog; // Blog エンティティをインポートします。
import pb.com.services.BlogService; // BlogService クラスをインポートします。

@Controller // このクラスが Spring MVC のコントローラであることを示します。
public class BlogEditController {

    @Autowired // BlogService を自動的に注入します。
    private BlogService blogService;

    @Autowired // HttpSession を自動的に注入します。
    private HttpSession session;

    // ブログ編集ページの表示を処理するメソッド
    @GetMapping("/blog/editing/{postId}") // GET リクエストが "/blog/editing/{postId}" にマッピングされるように設定します。
    public String getBlogEditPage(@PathVariable Integer postId, Model model) {
        Admin admin = (Admin) session.getAttribute("loginAdminInfo"); // セッションからログイン管理者情報を取得します。
        if (admin == null) {
            return "redirect:/admin/login"; // ログインしていない場合はログインページにリダイレクトします。
        } else {
            Blog blog = blogService.getBlogById(postId); // 指定された postId に基づいてブログを取得します。
            if (blog == null) {
                return "redirect:/blog/list"; // ブログが存在しない場合はブログリストページにリダイレクトします。
            } else {
                model.addAttribute("adminName", admin.getName()); // 管理者名をモデルに追加します。
                model.addAttribute("blog", blog); // 取得したブログをモデルに追加します。
                return "blog_editing.html"; // ブログ編集ページを表示します。
            }
        }
    }

    // ブログ更新処理を行うメソッド
    @PostMapping("/blog/edit/process") // POST リクエストが "/blog/edit/process" にマッピングされるように設定します。
    public String blogUpdate(@RequestParam Integer postId, @RequestParam String title, @RequestParam String content) {
        Admin admin = (Admin) session.getAttribute("loginAdminInfo"); // セッションからログイン管理者情報を取得します。
        if (admin == null) {
            return "redirect:/admin/login"; // ログインしていない場合はログインページにリダイレクトします。
        } else {
            Blog blog = blogService.getBlogById(postId); // 指定された postId に基づいてブログを取得します。
            if (blog == null) {
                return "redirect:/blog/list"; // ブログが存在しない場合はブログリストページにリダイレクトします。
            }
            blog.setTitle(title); // ブログのタイトルを更新します。
            blog.setContent(content); // ブログの内容を更新します。
            blogService.saveOrUpdateBlog(blog); // ブログを保存または更新します。
            return "redirect:/blog/list"; // ブログリストページにリダイレクトします。
        }
    }
}
