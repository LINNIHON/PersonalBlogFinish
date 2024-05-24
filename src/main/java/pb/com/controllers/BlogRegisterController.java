package pb.com.controllers; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam;

import pb.com.models.entity.Admin; // Admin エンティティをインポートします。
import pb.com.models.entity.Blog; // Blog エンティティをインポートします。
import pb.com.services.BlogService; // BlogService クラスをインポートします。
import jakarta.servlet.http.HttpSession; 

@Controller // このクラスがコントローラですよ。
public class BlogRegisterController {

	// BlogServiceのインスタンスを作りますよ。
    @Autowired
    private BlogService blogService; // ブログのサービスクラスです。
    
 // HttpSessionのインスタンスを作りますよ。
    @Autowired
    private HttpSession session; // ユーザーごとに情報を保存する箱です。
    
    // ブログ登録ページの表示を処理するメソッド
    @GetMapping("/blog/register") // /blog/register にアクセスしたときにこのメソッドが呼ばれます。
    public String getBlogRegisterPage(Model model) {
        // セッションからログイン管理者情報を取得
        Admin admin = (Admin) session.getAttribute("loginAdminInfo");
        //session は、ユーザーごとに情報を保存するための特別な箱のようなものです。この箱にいろいろな情報を入れておくと、ページが変わってもその情報を覚えてくれます。
    	//getAttribute は、セッションの箱から特定の情報を取り出すための方法です。
    	//"loginAdminInfo" は、その情報の名前です。この名前でセッションの箱に入れた情報を取り出します。ここでは、ログインしている管理者の情報を指します。
    	//(Admin) は、取り出した情報を Admin 型に変換するためのものです。こうすることで、この情報が管理者の情報として使えるようになります。
    	//Admin は、管理者の情報を表すクラスです。このクラスには、管理者のID、名前、メールアドレスなどの情報が入っています。
    	//admin は、このクラスの情報を入れておくための変数の名前です。
        
        // 管理者がログインしているか確認します。
        // 管理者情報がない場合、ログインページにリダイレクト
        if (admin == null) {
            return "redirect:/admin/login"; // ログインしていない場合はログインページにリダイレクトします。
        } else {
        	// 管理者がログインしている場合、管理者名をモデルに追加してブログ登録ページを表示します.
            model.addAttribute("adminName", admin.getName()); // 管理者の名前を画面に渡します。
            return "blog_register.html"; // ブログ登録ページを表示します。
        }
    }
    
    // 1. /blog/register にアクセスします。
    // 2. セッションからログイン管理者の情報を取得します。
    // 3. 管理者がログインしていない場合、ログインページに移動します。
    // 4. 管理者がログインしている場合、管理者名を画面に渡してブログ登録ページを表示します。
    
    // ブログ登録処理を行うメソッド
    @PostMapping("/blog/register/process") // /blog/register/process にデータを送るとこのメソッドが呼ばれます。
    public String blogRegisterProcess(@RequestParam String title,
                                      @RequestParam String content) {
    	//@RequestParam String title: ブログのタイトルを受け取ります。
    	//@RequestParam String content: ブログの内容を受け取ります。
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
            // 新しいブログオブジェクトを作成して保存
            Blog blog = new Blog(admin, title, content); // 新しいブログオブジェクトを作成します。
            blogService.saveOrUpdateBlog(blog); // ブログを保存または更新します。
            // ブログリストページにリダイレクト
            return "redirect:/blog/list"; // ブログリストページにリダイレクトします。
        }
    }
    // 1. /blog/register/process にデータを送ります。
    // 2. セッションからログイン管理者の情報を取得します。
    // 3. 管理者がログインしていない場合、ログインページに移動します。
    // 4. 管理者がログインしている場合、新しいブログを作成して保存します。
    // 5. ブログリストページに移動します。
}
