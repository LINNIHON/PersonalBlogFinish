package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Controller; // @Controller アノテーションをインポートします。
import org.springframework.web.bind.annotation.GetMapping; // @GetMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.PostMapping; // @PostMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.RequestParam; // @RequestParam アノテーションをインポートします。

import jakarta.servlet.http.HttpSession; // HttpSession クラスをインポートします。
import pb.com.services.LoginService; // LoginService クラスをインポートします。
import pb.com.models.entity.Admin; // Admin エンティティをインポートします。

@Controller // このクラスが Spring MVC のコントローラであることを示します。
public class LoginController {
    
    // ログインサービスを自動的に注入
    @Autowired
    private LoginService loginService; // LoginService クラスのインスタンスを注入します。

    // セッション管理用のHttpSessionを自動的に注入
    @Autowired
    private HttpSession session; // HttpSession クラスのインスタンスを注入します。

    // ログイン画面の表示を処理するメソッド
    @GetMapping("/admin/login") // GETリクエストが "/admin/login" にマッピングされるように設定します。
    public String getLoginPage() {
        return "login.html"; // "login.html" ページを返します。
    }

    // ログイン処理を行うメソッド
    @PostMapping("/admin/login/process") // POSTリクエストが "/admin/login/process" にマッピングされるように設定します。
    public String loginProcess(@RequestParam String email, @RequestParam String password) {
        // ログインチェックを行い、結果をAdminオブジェクトに格納
        Admin admin = loginService.loginCheck(email, password); // メールとパスワードでログインをチェックします。

        // ログイン成功の場合、セッションに管理者情報を保存し、ブログリストページにリダイレクト
        if (admin != null) {
            session.setAttribute("loginAdminInfo", admin); // ログイン成功時にセッションに管理者情報を保存します。
            return "redirect:/blog/list"; // ログイン成功後のリダイレクト先
        } else {
            // ログイン失敗の場合、再度ログインページを表示
            return "login.html"; // ログイン失敗時に再度ログインページを表示します。
        }
    }

    // ログイン画面から新規登録画面への遷移を処理するメソッド
    @GetMapping("/admin/login/register") // GETリクエストが "/admin/login/register" にマッピングされるように設定します。
    public String redirectToRegisterPage() {
        return "redirect:/admin/register"; // 新規登録ページにリダイレクトします。
    }
}
