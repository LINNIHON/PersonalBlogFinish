package pb.com.controllers; // このクラスが入っているフォルダーの名前です。

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession; 

@Controller // これは司令塔ですよ。
public class LogoutController {

    // ログアウト処理を行うメソッド
    @GetMapping("/admin/logout") // "/admin/logout"にアクセスしたらこのメソッドが呼ばれます。
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // セッションがあるかどうかチェックします。あれば取り出します。
//          false を指定すると:
//        	もしセッションがなければ、新しいセッションを作りません。
//        	その代わりに、null を返します。null は、「何もないよ」という意味です。
        
//        	true を指定すると:
//        	もしセッションがなければ、新しいセッションを作ります。
//        	簡単に言うと、false は「新しいセッションを作らないでね」、true は「新しいセッションを作ってね」という意味です。
//          つまり
//          セッションが存在する場合:
//        	既存のセッションオブジェクトが session 変数に代入
//        	セッションが存在しない場合:
//        	新しいセッションは作成されず、null が session 変数に代入
        
        if (session != null) {
            // セッションを無効にする
            session.invalidate(); // セッションを無効にして、すべての情報を消します。
        }

        // ログインページに移動する
        return "redirect:/admin/login"; // ログアウトした後、ログインページに移動します。
    }
    
//    セッションを確認
//    request.getSession(false) で、今のセッションがあるかどうかをチェックします。
//    セッションがあれば、session という変数に入れます。
    
//    セッションを無効にする
//    セッションがある場合、session.invalidate() でセッションを無効にして、ログイン情報などを消します。

//    ログインページに移動
//    最後に、return "redirect:/admin/login" でログインページに移動します。
}
