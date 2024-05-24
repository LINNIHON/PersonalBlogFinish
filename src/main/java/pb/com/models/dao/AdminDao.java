package pb.com.models.dao; // このインターフェースが属するパッケージを宣言します。

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository インターフェースをインポートします。
import org.springframework.stereotype.Repository; // @Repository アノテーションをインポートします。
import pb.com.models.entity.Admin; // Admin エンティティをインポートします。

@Repository //これは「データベースから情報を取り出すクラスですよ」と教えるためのアノテーションです。
public interface AdminDao extends JpaRepository<Admin, Integer> {

    // 保存処理と更新処理（インサートとアップデート）
    Admin save(Admin admin); // 管理者情報をデータベースに保存または更新します。

    // 指定されたメールアドレスに基づいて管理者情報を検索するメソッド
    // SQL: SELECT * FROM admin WHERE email = ?
    // 用途：管理者の登録処理をするときに、同じメールアドレスはあってはならない
    Admin findByEmail(String email); // 管理者のメールアドレスに基づいて管理者情報を検索します。

    // 指定されたメールアドレスとパスワードに基づいて管理者情報を検索するメソッド
    // SQL: SELECT * FROM admin WHERE email = ? AND password = ?
    // 用途：ログイン処理に使用。入力したメールアドレスとパスワードが一致しているときだけデータを取得
    Admin findByEmailAndPassword(String email, String password); // メールアドレスとパスワードに基づいて管理者情報を検索します。

    // 指定された管理者IDに基づいて管理者情報を検索するメソッド
    // SQL: SELECT * FROM admin WHERE admin_id = ?
    Admin findByAdminId(Integer adminId); // 管理者IDに基づいて管理者情報を検索します。
}
