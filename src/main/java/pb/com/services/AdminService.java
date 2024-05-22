package pb.com.services; // このクラスが属するパッケージを宣言します。

import org.springframework.beans.factory.annotation.Autowired; // 依存性注入のための @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Service; // このクラスをサービスコンポーネントとして定義するための @Service アノテーションをインポートします。
import pb.com.models.dao.AdminDao; // models.dao パッケージから AdminDao インターフェースをインポートします。
import pb.com.models.entity.Admin; // models.entity パッケージから Admin エンティティをインポートします。

@Service // このクラスを Spring サービスコンポーネントとしてマークし、Spring のコンポーネントスキャンで検出され、Spring Bean として登録されるようにします。
public class AdminService {

    @Autowired // Spring に AdminDao 依存性を自動的に注入するよう指示します。
    private AdminDao adminDao; // AdminDao 型のプライベートフィールドを宣言します。

    // 保存処理（登録処理）
    public boolean createAdmin(String name, String email, String password) {
        // 指定されたメールアドレスの管理者が存在しない場合、新しい管理者を作成します
        if (adminDao.findByEmail(email) == null) {
            adminDao.save(new Admin(name, email, password)); // 新しい管理者を保存します
            return true; // 作成が成功した場合は true を返します
        } else {
            return false; // 既に管理者が存在する場合は false を返します
        }
    }

    // 指定されたメールアドレスの管理者を検索します
    public Admin findAdminByEmail(String email) {
        return adminDao.findByEmail(email); // メールアドレスに基づいて管理者を検索し、返します
    }

    // 指定されたIDの管理者を取得します
    public Admin getAdminById(Integer id) {
        // 管理者IDに基づいて管理者を検索し、存在しない場合は null を返します
        return adminDao.findById(id).orElse(null);
    }
}
