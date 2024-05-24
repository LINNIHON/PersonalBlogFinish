package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.PathVariable; 

import jakarta.servlet.http.HttpSession;
import pb.com.models.entity.Admin; // Admin エンティティをインポートします。
import pb.com.services.BlogService; // BlogService クラスをインポートします。

@Controller //コントローラですよ
public class BlogDeleteController {

    @Autowired //インスタンスが来ますよ
    private BlogService blogService;// blogServiceのインスタンスですよ

    @Autowired //インスタンスが来ますよ
    private HttpSession session;// HttpSessionのインスタンスですよ

    // ブログ削除処理を行うメソッド
    @DeleteMapping("/blog/delete/{id}") // "/blog/delete/任意のID" に送信する。
    //{id} の部分は動的に変わる値を受け取ることができ、その値はメソッドの引数として渡される。
    
    public ResponseEntity<Void> blogDelete(@PathVariable("id") Integer postId) {
    	//クライアントが DELETE /blog/delete/{id} というリクエストを送ると、このメソッドが呼び出される。
        //@PathVariable を使って、URL の {id} 部分を postId という変数に代入する。
        // セッションからログイン管理者情報を取得
        Admin admin = (Admin) session.getAttribute("loginAdminInfo");
        //セッションから loginAdminInfo という属性を取得し、それを Admin 型の admin 変数に格納します。
        //取得した管理者情報を使って、ブログ削除処理を実行。
        
        
        //これは、「ログインしていない人には、この操作はできませんよ」というメッセージをサーバーから送り返すためのコードです。
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 未ログイン、401 Unauthorized を返す
        } else {
            //これは、「リクエストは成功しましたが、返すデータはありません」というメッセージをサーバーから送り返すためのコードです。
            if (blogService.deleteBlog(postId)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 成功削除、204 No Content を返す
            } else {
             //これは、「サーバーで問題が起きたので、この操作はできませんでした」というメッセージをサーバーから送り返すためのコードです。   
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 削除失敗、500 Internal Server Error を返す
            }
//            ResponseEntity.status(HttpStatus.NO_CONTENT)
//            まず、「ステータスコードを 204（NO CONTENT）に設定しよう」と準備します。
//
//            .build()
//            そして、「すべての準備が整ったから、完成品を作ろう」と言って、実際の ResponseEntity オブジェクトを作ります。
            
        }
    }
    
//    URLの形：/blog/delete/数字 でブログを削除します。この「数字」はブログのIDです。
//
//    例：/blog/delete/1 はIDが1のブログを削除します。
//    メソッドの開始：
//
//    @DeleteMapping("/blog/delete/{id}") は、このURLにアクセスしたときに動く部分です。
//    {id} の部分は、URLから数字を受け取って、postId という変数に入れます。
//    セッションから管理者情報を取得：
//
//    session.getAttribute("loginAdminInfo") で、セッションに保存された管理者の情報を取り出します。
//    管理者情報が admin という変数に入ります。
//    未ログインの場合：
//
//    管理者情報がない場合（admin == null）、未ログインとして処理します。
//    401 Unauthorized というエラーを返します。これは「ログインしていません」という意味です。
//    ログインしている場合：
//
//    管理者情報がある場合（admin != null）、次に進みます。
//    ブログ削除の試み：
//
//    blogService.deleteBlog(postId) でブログを削除しようとします。
//    削除がうまくいった場合（true）、204 No Content を返します。これは「削除が成功しました」という意味です。
//    削除が失敗した場合（false）、500 Internal Server Error を返します。これは「サーバーでエラーが発生しました」という意味です。
}
