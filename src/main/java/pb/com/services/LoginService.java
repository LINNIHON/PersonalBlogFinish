package pb.com.services; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // 依存性注入のための @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Service; // このクラスをサービスコンポーネントとして定義するための @Service アノテーションをインポートします。
import pb.com.models.dao.AdminDao; // models.dao パッケージから AdminDao インターフェースをインポートします。
import pb.com.models.entity.Admin; // models.entity パッケージから Admin エンティティをインポートします。

@Service // このクラスを Spring サービスコンポーネントとしてマークし、Spring のコンポーネントスキャンで検出され、Spring Bean として登録されるようにします。
public class LoginService {

    @Autowired // Spring に AdminDao 依存性を自動的に注入するよう指示します。
    private AdminDao adminDao; // AdminDao 型のプライベートフィールドを宣言します。

    // ログイン処理
    public Admin loginCheck(String email, String password) {
        // adminDao オブジェクトの findByEmailAndPassword メソッドを呼び出し、
        // email と password をパラメータとして渡してログイン認証を行います。
        // 認証が成功した場合は Admin オブジェクトを返し、失敗した場合は null を返します。
        return adminDao.findByEmailAndPassword(email, password);
    }
}
