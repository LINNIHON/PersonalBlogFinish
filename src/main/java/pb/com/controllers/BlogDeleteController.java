package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // @Autowired アノテーションをインポートします。
import org.springframework.http.HttpStatus; // HTTPステータスコードをインポートします。
import org.springframework.http.ResponseEntity; // HTTPレスポンスエンティティをインポートします。
import org.springframework.stereotype.Controller; // @Controller アノテーションをインポートします。
import org.springframework.web.bind.annotation.DeleteMapping; // @DeleteMapping アノテーションをインポートします。
import org.springframework.web.bind.annotation.PathVariable; // @PathVariable アノテーションをインポートします。

import jakarta.servlet.http.HttpSession; // HttpSession クラスをインポートします。
import pb.com.models.entity.Admin; // Admin エンティティをインポートします。
import pb.com.services.BlogService; // BlogService クラスをインポートします。

@Controller // このクラスが Spring MVC のコントローラであることを示します。
public class BlogDeleteController {

    @Autowired // BlogService を自動的に注入します。
    private BlogService blogService;

    @Autowired // HttpSession を自動的に注入します。
    private HttpSession session;

    // ブログ削除処理を行うメソッド
    @DeleteMapping("/blog/delete/{id}") // DELETE リクエストが "/blog/delete/{id}" にマッピングされるように設定します。
    public ResponseEntity<Void> blogDelete(@PathVariable("id") Integer postId) {
        // セッションからログイン管理者情報を取得
        Admin admin = (Admin) session.getAttribute("loginAdminInfo");

        // 管理者情報がない場合、401 Unauthorized を返す
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 未ログイン、401 Unauthorized を返す
        } else {
            // ブログ削除処理を実行し、成功の場合204 No Content を返す
            if (blogService.deleteBlog(postId)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 成功削除、204 No Content を返す
            } else {
                // 削除失敗の場合、500 Internal Server Error を返す
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 削除失敗、500 Internal Server Error を返す
            }
        }
    }
}
