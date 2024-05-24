package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import java.util.List; // List コレクションフレームワークをインポートします。

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 

import jakarta.servlet.http.HttpSession; 
import pb.com.models.entity.Admin; // Admin エンティティをインポートします。
import pb.com.models.entity.Blog; // Blog エンティティをインポートします。
import pb.com.services.BlogService; // BlogService クラスをインポートします。

@Controller//コントローラですよ
public class BlogListController {
    @Autowired // HttpSessionのインスタンスですよ
    private HttpSession session; 
    
    @Autowired//インスタンスが来ますよ
    private BlogService blogService; // blogServiceのインスタンスですよ

    // ブログ一覧の表示を処理するメソッド
    @GetMapping("/blog/list") 
    public String getBlogList(Model model) {
        // セッションからログイン管理者情報を取得
        Admin admin = (Admin) session.getAttribute("loginAdminInfo"); // セッションからログイン中の管理者情報を取得します。
        //session は、ユーザーごとに情報を保存するための特別な箱のようなものです。この箱にいろいろな情報を入れておくと、ページが変わってもその情報を覚えてくれます。
    	//getAttribute は、セッションの箱から特定の情報を取り出すための方法です。
    	//"loginAdminInfo" は、その情報の名前です。この名前でセッションの箱に入れた情報を取り出します。ここでは、ログインしている管理者の情報を指します。
    	//(Admin) は、取り出した情報を Admin 型に変換するためのものです。こうすることで、この情報が管理者の情報として使えるようになります。
    	//Admin は、管理者の情報を表すクラスです。このクラスには、管理者のID、名前、メールアドレスなどの情報が入っています。
    	//admin は、このクラスの情報を入れておくための変数の名前です。
        
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
                return "error.html"; // エラーページを表示します。
            }
        }
    }
    
//      ブログの更新リクエスト:
//    	/blog/edit/process にデータを送ると、ブログの更新が始まります。
    
//    	ログインチェック:
//    	ログインしていない場合は、ログインページに移動します。
    
//    	ブログの取得:
//    	postId を使ってブログを探し、見つからない場合はブログリストページに移動します。
    
//    	ブログの更新:
//    	新しいタイトルと内容を設定します。
    
//    	ブログの保存:
//    	更新されたブログを保存します。
    
//    	リダイレクト:
//    	更新が完了したら、ブログリストページに移動します。
}
