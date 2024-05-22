package pb.com.models.entity; // このクラスが属するパッケージを宣言します。

import jakarta.persistence.Entity; // JPA の @Entity アノテーションをインポートします。
import jakarta.persistence.GeneratedValue; // 主キーの自動生成のための @GeneratedValue アノテーションをインポートします。
import jakarta.persistence.GenerationType; // 主キー生成戦略を定義するための GenerationType 列挙型をインポートします。
import jakarta.persistence.Id; // JPA の @Id アノテーションをインポートします。
import jakarta.persistence.JoinColumn; // 外部キー列を定義するための @JoinColumn アノテーションをインポートします。
import jakarta.persistence.ManyToOne; // 多対一の関連を定義するための @ManyToOne アノテーションをインポートします。

@Entity // このクラスが JPA エンティティであることを示します。
public class Blog {

    @Id // このフィールドがエンティティの主キーであることを示します。
    @GeneratedValue(strategy = GenerationType.AUTO) // 主キーの値を自動生成する戦略を指定します。
    private Integer postId; // ブログのポストID（主キー）

    @ManyToOne // このエンティティが多対一の関連を持つことを示します。
    @JoinColumn(name = "admin_id", nullable = false) // 外部キー列を定義し、NULL を許可しないようにします。
    private Admin admin; // ブログの管理者

    private String title; // ブログのタイトル
    private String content; // ブログの内容

    // デフォルトコンストラクタ
    public Blog() {
    }

    // パラメータ付きコンストラクタ
    public Blog(Admin admin, String title, String content) {
        this.admin = admin; // 管理者を初期化します
        this.title = title; // タイトルを初期化します
        this.content = content; // 内容を初期化します
    }

    // Getter と Setter メソッド
    public Integer getPostId() {
        return postId; // ポストIDを返します
    }

    public void setPostId(Integer postId) {
        this.postId = postId; // ポストIDを設定します
    }

    public Admin getAdmin() {
        return admin; // 管理者を返します
    }

    public void setAdmin(Admin admin) {
        this.admin = admin; // 管理者を設定します
    }

    public String getTitle() {
        return title; // タイトルを返します
    }

    public void setTitle(String title) {
        this.title = title; // タイトルを設定します
    }

    public String getContent() {
        return content; // 内容を返します
    }

    public void setContent(String content) {
        this.content = content; // 内容を設定します
    }
}
