package pb.com.models.dao; // このインターフェースが属するパッケージを宣言します。

import java.util.List; // List コレクションフレームワークをインポートします。
import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository インターフェースをインポートします。
import org.springframework.stereotype.Repository; // @Repository アノテーションをインポートします。
import pb.com.models.entity.Blog; // Blog エンティティをインポートします。

@Repository //これは「データベースから情報を取り出すクラスですよ」と教えるためのアノテーションです。
public interface BlogDao extends JpaRepository<Blog, Integer> {
    
    // 保存処理と更新処理（インサートとアップデート）
    Blog save(Blog blog); // ブログエンティティを保存または更新します。

    // 管理者IDでブログを取得するメソッド
    // SQL: SELECT * FROM blog WHERE admin_id = ?
    List<Blog> findAllByAdmin_AdminId(Integer adminId); // 指定された管理者IDに基づいてすべてのブログを取得します。

    // 指定されたポストIDに基づいてブログを取得するメソッド
    // SQL: SELECT * FROM blog WHERE post_id = ?
    Blog findByPostId(Integer postId); // 指定されたポストIDに基づいてブログを取得します。

    // 指定されたポストIDに基づいてブログを削除するメソッド
    // SQL: DELETE FROM blog WHERE post_id = ?
    void deleteByPostId(Integer postId); // 指定されたポストIDに基づいてブログを削除します。
}
