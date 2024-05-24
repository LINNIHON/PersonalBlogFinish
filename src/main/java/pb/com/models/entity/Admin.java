package pb.com.models.entity; // このクラスが属するパッケージを宣言します。

import jakarta.persistence.Entity; // JPA の @Entity アノテーションをインポートします。
import jakarta.persistence.GeneratedValue; // 主キーの自動生成のための @GeneratedValue アノテーションをインポートします。
import jakarta.persistence.GenerationType; // 主キー生成戦略を定義するための GenerationType 列挙型をインポートします。
import jakarta.persistence.Id; // JPA の @Id アノテーションをインポートします。

@Entity //このクラスがデータベースのテーブルに対応するよ
public class Admin {

    @Id //これはデータベースの中で主キーだよ
    @GeneratedValue(strategy = GenerationType.AUTO) 
//    「新しいデータができたとき、この番号を自動で作ってね」という意味です。
//    　新しいデータを追加するとき、IDが1、2、3と順番に増えていくようにします。
    private Integer adminId; // 管理者ID（主キー）

    private String name; // 管理者の名前
    private String email; // 管理者のメールアドレス
    private String password; // 管理者のパスワード

    // デフォルトコンストラクタ
    public Admin() {
    }

    // パラメータ付きコンストラクタ
    public Admin(String name, String email, String password) {
        this.name = name; // 名前を初期化します
        this.email = email; // メールアドレスを初期化します
        this.password = password; // パスワードを初期化します
    }

    // Getter と Setter メソッド
    public Integer getAdminId() {
        return adminId; // 管理者IDを返します
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId; // 管理者IDを設定します
    }

    public String getName() {
        return name; // 名前を返します
    }

    public void setName(String name) {
        this.name = name; // 名前を設定します
    }

    public String getEmail() {
        return email; // メールアドレスを返します
    }

    public void setEmail(String email) {
        this.email = email; // メールアドレスを設定します
    }

    public String getPassword() {
        return password; // パスワードを返します
    }

    public void setPassword(String password) {
        this.password = password; // パスワードを設定します
    }
}
