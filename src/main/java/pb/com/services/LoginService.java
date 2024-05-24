package pb.com.services; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // 依存性注入のための @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Service; // このクラスをサービスコンポーネントとして定義するための @Service アノテーションをインポートします。
import pb.com.models.dao.AdminDao; // models.dao パッケージから AdminDao インターフェースをインポートします。
import pb.com.models.entity.Admin; // models.entity パッケージから Admin エンティティをインポートします。

@Service  // このクラスがサービスとして働く
public class LoginService {

	 @Autowired // AdminDaoの必要なものを自動で入れてもらう
	 private AdminDao adminDao; // AdminDaoのデータを使うための変数を作る

    // ログイン処理
    public Admin loginCheck(String email, String password) {
        return adminDao.findByEmailAndPassword(email, password);
        // adminDao オブジェクトの findByEmailAndPassword メソッドを呼び出し、
        // email と password をパラメータとして渡してログイン認証を行います。
        // 認証が成功した場合は Admin オブジェクトを返し、失敗した場合は null を返します。
    }
}
