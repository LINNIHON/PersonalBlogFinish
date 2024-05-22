package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.stereotype.Controller; // @Controller アノテーションをインポートします。
import org.springframework.web.bind.annotation.GetMapping; // @GetMapping アノテーションをインポートします。

import jakarta.servlet.http.HttpServletRequest; // HttpServletRequest クラスをインポートします。
import jakarta.servlet.http.HttpSession; // HttpSession クラスをインポートします。

@Controller // このクラスが Spring MVC のコントローラであることを示します。
public class LogoutController {

    // ログアウト処理を行うメソッド
    @GetMapping("/admin/logout") // GETリクエストが "/admin/logout" にマッピングされるように設定します。
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // セッションが存在する場合に現在のセッションを取得します。存在しない場合は null を返します。
        if (session != null) {
            // セッションを無効化する
            session.invalidate(); // セッションを無効化します。これにより、セッションに関連付けられたすべての情報が破棄されます。
        }
        // ログインページにリダイレクト
        return "redirect:/admin/login"; // ログアウト後、ログインページにリダイレクトします。
    }
}
