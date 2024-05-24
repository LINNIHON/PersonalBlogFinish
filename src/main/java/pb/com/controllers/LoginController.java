package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // 必要なクラスをインポートします。
import org.springframework.stereotype.Controller; // コントローラとして使います。
import org.springframework.web.bind.annotation.GetMapping; // GETリクエストを処理します。
import org.springframework.web.bind.annotation.PostMapping; // POSTリクエストを処理します。
import org.springframework.web.bind.annotation.RequestParam; // リクエストパラメータを取得します。

import jakarta.servlet.http.HttpSession; // セッションを扱います。
import pb.com.services.LoginService; // ログインサービスを扱います。
import pb.com.models.entity.Admin; // 管理者情報を扱います。

@Controller // これはコントローラです。
public class LoginController {
    
    @Autowired // ログインサービスのインスタンスを作ります。
    private LoginService loginService; // ログインサービスを使います。

    @Autowired // セッションのインスタンスを作ります。
    private HttpSession session; // セッションを使います。

    // ログイン画面を表示するメソッド
    @GetMapping("/admin/login") // "/admin/login"にアクセスしたらこのメソッドが呼ばれます。
    public String getLoginPage() {
        return "login.html"; // ログインページを表示します。
    }
    // getLoginPage:
    // 1. /admin/login にアクセスします。
    // 2. ログインページを表示します。

    // ログイン処理を行うメソッド
    @PostMapping("/admin/login/process") // "/admin/login/process"にデータを送るとこのメソッドが呼ばれます。
    public String loginProcess(@RequestParam String email, @RequestParam String password) {
        // ログインチェックを行います。
        Admin admin = loginService.loginCheck(email, password); // メールとパスワードでログインを確認します。

        // ログイン成功の場合
        if (admin != null) {
            session.setAttribute("loginAdminInfo", admin); // 管理者情報をセッションに保存します。
            return "redirect:/blog/list"; // ブログリストページに移動します。
        } else {
            // ログイン失敗の場合
            return "login.html"; // もう一度ログインページを表示します。
        }
    }
    // 1. /admin/login/process にメールとパスワードを送ります。
    // 2. ログインチェックを行います。
    // 3. ログイン成功の場合、セッションに管理者情報を保存し、ブログリストページに移動します。
    // 4. ログイン失敗の場合、再度ログインページを表示します。

    // ログイン画面から新規登録画面への移動を処理するメソッド
    @GetMapping("/admin/login/register") // "/admin/login/register"にアクセスしたらこのメソッドが呼ばれます。
    public String redirectToRegisterPage() {
        return "redirect:/admin/register"; // 新規登録ページに移動します。
    }
    // redirectToRegisterPage:
    // 1. /admin/login/register にアクセスします。
    // 2. 新規登録ページに移動します。
}
