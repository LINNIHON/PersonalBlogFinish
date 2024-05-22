package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Controller; // @Controller アノテーションをインポートします。
import org.springframework.web.bind.annotation.GetMapping; // @GetMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.PostMapping; // @PostMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.RequestParam; // @RequestParam アノテーションをインポートします。
import pb.com.services.AdminService; // AdminService クラスをインポートします。

@Controller // このクラスが Spring MVC のコントローラであることを示します。
public class AdminRegisterController {

    // 管理者サービスを自動的に注入
    @Autowired
    private AdminService adminService; // AdminService クラスのインスタンスを注入します。

    // 管理者登録ページの表示を処理するメソッド
    @GetMapping("/admin/register") // GETリクエストが "/admin/register" にマッピングされるように設定します。
    public String getAdminRegisterPage() {
        return "register.html"; // 登録ページを表示します。
    }

    // 管理者登録処理を行うメソッド
    @PostMapping("/admin/register/process") // POSTリクエストが "/admin/register/process" にマッピングされるように設定します。
    public String adminRegisterProcess(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        // 新しい管理者を作成し、登録が成功したかどうかを確認
        boolean isRegistered = adminService.createAdmin(name, email, password); // 管理者の登録処理を実行します。

        // 登録が成功しなかった場合（既に存在するメールアドレスの場合）、再度登録ページを表示
        if (!isRegistered) {
            return "register.html"; // 登録に失敗した場合は再度登録ページを表示します。
        }

        // 登録が成功した場合、ログインページにリダイレクト
        return "redirect:/admin/login"; // 登録に成功した場合はログインページにリダイレクトします。
    }
}
