package pb.com.controllers; // このクラスが属するパッケージを宣言します。
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import pb.com.services.AdminService; // AdminService クラスをインポートします。

@Controller//コントローラですよ
public class AdminRegisterController {

    @Autowired//インスタンスが来ますよ
    private AdminService adminService; // AdminServiceのインスタンスですよ

    // 管理者登録ページの表示を処理するメソッド
    @GetMapping("/admin/register") //"/admin/register"パスに送信
    public String getAdminRegisterPage() {
        return "register.html"; // 登録ページを表示します。
    }
//    getAdminRegisterPageメソッド
//    /admin/register にアクセスします。
//    登録ページ (register.html) を表示します。

    // 管理者登録処理を行うメソッド
    @PostMapping("/admin/register/process") //"/admin/register/process"パスに送信。
    public String adminRegisterProcess(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        //@RequestParamアノテーションは、メソッドの引数として受け取るために使用される
    	// 新しい管理者を作成し、登録が成功したかどうかを確認
        boolean isRegistered = adminService.createAdmin(name, email, password); // 管理者の登録処理を実行

        // 登録が成功しなかった場合（既に存在するメールアドレスの場合）、再度登録ページを表示
        if (!isRegistered) {
            return "register.html"; // 登録に失敗した場合は再度登録ページを表示します。
        }

        // 登録が成功した場合、ログインページにリダイレクト
        return "redirect:/admin/login"; // 登録に成功した場合はログインページにリダイレクトします。
    }
//    adminRegisterProcessメソッド
//    /admin/register/process に名前、メールアドレス、パスワードを送ります。
//    管理者サービスを使って新しい管理者を作成します。
//    登録が成功したかどうかを確認します。
//    失敗した場合、再度登録ページ (register.html) を表示します。
//    成功した場合、ログインページに移動します (/admin/login)。
}
