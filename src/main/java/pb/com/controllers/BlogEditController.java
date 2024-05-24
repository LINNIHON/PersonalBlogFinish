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

@Controller //コントローラですよ
public class BlogEditController {

    @Autowired //インスタンスが来ますよ
    private BlogService blogService;// blogServiceのインスタンスですよ

    @Autowired // HttpSessionのインスタンスですよ
    private HttpSession session;

    // ブログ編集画面の表示を処理するメソッド
    @GetMapping("/blog/editing/{postId}") // DBから{postId}を使ってブログを特定する 
    public String getBlogEditPage(@PathVariable Integer postId, Model model) {
    	//@RequestParamアノテーションは、メソッドの引数として受け取るために使用される
    	//Model (モデル):データを入れる特別な箱。
        //コントローラからビューにデータを渡すために使います。
    	
        Admin admin = (Admin) session.getAttribute("loginAdminInfo"); // セッションからログイン管理者情報を取得します。
        //session は、ユーザーごとに情報を保存するための特別な箱のようなものです。この箱にいろいろな情報を入れておくと、ページが変わってもその情報を覚えてくれます。
    	//getAttribute は、セッションの箱から特定の情報を取り出すための方法です。
    	//"loginAdminInfo" は、その情報の名前です。この名前でセッションの箱に入れた情報を取り出します。ここでは、ログインしている管理者の情報を指します。
    	//(Admin) は、取り出した情報を Admin 型に変換するためのものです。こうすることで、この情報が管理者の情報として使えるようになります。
    	//Admin は、管理者の情報を表すクラスです。このクラスには、管理者のID、名前、メールアドレスなどの情報が入っています。
    	//admin は、このクラスの情報を入れておくための変数の名前です。
        if (admin == null) {
            return "redirect:/admin/login"; // ログインしていない場合はログインページにリダイレクトします。
            //admin が null ということは、管理者がログインしていないということです。
            //その場合、ログインページに移動します（リダイレクトします）。
        } else {
        	//もし管理者がログインしている場合
        	//管理者がログインしている場合の処理を行います。
            Blog blog = blogService.getBlogById(postId); // 指定された postId に基づいてブログを取得します。
            if (blog == null) {
            //もしブログが存在しない場合
            //blog が null ということは、その番号のブログが存在しないということです。
            //その場合、ブログリストページに移動します（リダイレクトします）。
                return "redirect:/blog/list"; // ブログが存在しない場合はブログリストページにリダイレクトします。
            } else {
            //もしブログが存在する場合
            //ブログが存在する場合の処理を行います。
                model.addAttribute("adminName", admin.getName()); // 管理者名をモデルに追加します。
                //admin.getName() で管理者の名前を取得し、それをモデルに追加します。
                //これにより、画面（ビュー）で管理者の名前を表示できるようになります。
                model.addAttribute("blog", blog); // 取得したブログをモデルに追加します。
                //取得したブログの情報をモデルに追加します。
                //これにより、画面（ビュー）でブログの内容を表示できるようになります。
                return "blog_editing.html"; // ブログ編集ページを表示します。
                //最後に、ブログ編集ページを表示します。
            }
        }
        
//          ログインしていない場合:
//        	ログインページに移動します。
        
//        	ログインしている場合:
//        	ブログを探します。
        
//        	ブログが存在しない場合:
//        	ブログリストページに移動します。
        
//        	ブログが存在する場合:
//        	管理者の名前とブログの内容を画面に表示するための準備をします。
//        	ブログ編集ページを表示します。
    }

    // ブログ更新処理を行うメソッド
    @PostMapping("/blog/edit/process") ///blog/edit/process というアドレスにデータを送ると、このメソッドが呼び出されます。
    public String blogUpdate(@RequestParam Integer postId, @RequestParam String title, @RequestParam String content) {
    	//@RequestParam Integer postId:どのブログを更新するかを指定するための番号（ID）です。
    	//@RequestParam String title:新しいタイトルです。
    	//@RequestParam String content:新しい内容です。
        Admin admin = (Admin) session.getAttribute("loginAdminInfo"); // セッションからログイン管理者情報を取得します。
        if (admin == null) {
            return "redirect:/admin/login"; // ログインしていない場合はログインページにリダイレクトします。
            //session.getAttribute("loginAdminInfo") で、ログインしている管理者の情報を取得します。
            //もし、管理者がログインしていない場合は、ログインページに移動します（リダイレクトします）。
        } else {
            Blog blog = blogService.getBlogById(postId); // 指定された postId に基づいてブログを取得します。
            //管理者がログインしている場合、postId に基づいてブログを探します。
            if (blog == null) {
                return "redirect:/blog/list"; // ブログが存在しない場合はブログリストページにリダイレクトします。
            }
            //もし、ブログが見つからない場合は、ブログリストページに移動します。
            blog.setTitle(title); // ブログのタイトルを更新します。
            blog.setContent(content); // ブログの内容を更新します。
            blogService.saveOrUpdateBlog(blog); // ブログを保存または更新します。
            return "redirect:/blog/list"; // ブログリストページにリダイレクトします。
        }
        //最後に、ブログリストページに移動します。これで更新が完了します。
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
