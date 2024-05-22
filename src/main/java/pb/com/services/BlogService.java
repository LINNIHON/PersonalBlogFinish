package pb.com.services; // このクラスが属するパッケージを宣言します。

import java.util.List; // List インターフェースをインポートします。

import org.springframework.beans.factory.annotation.Autowired; // 依存性注入のための @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Service; // このクラスをサービスコンポーネントとして定義するための @Service アノテーションをインポートします。
import org.springframework.transaction.annotation.Transactional; // トランザクション管理のための @Transactional アノテーションをインポートします。

import pb.com.models.dao.AdminDao; // models.dao パッケージから AdminDao インターフェースをインポートします。
import pb.com.models.dao.BlogDao; // models.dao パッケージから BlogDao インターフェースをインポートします。
import pb.com.models.entity.Admin; // models.entity パッケージから Admin エンティティをインポートします。
import pb.com.models.entity.Blog; // models.entity パッケージから Blog エンティティをインポートします。

@Service // このクラスを Spring サービスコンポーネントとしてマークし、Spring のコンポーネントスキャンで検出され、Spring Bean として登録されるようにします。
public class BlogService {

    @Autowired // Spring に BlogDao 依存性を自動的に注入するよう指示します。
    private BlogDao blogDao; // BlogDao 型のプライベートフィールドを宣言します。

    @Autowired // Spring に AdminDao 依存性を自動的に注入するよう指示します。
    private AdminDao adminDao; // AdminDao 型のプライベートフィールドを宣言します。

    @Autowired // Spring に AdminService 依存性を自動的に注入するよう指示します。
    private AdminService adminService; // AdminService 型のプライベートフィールドを宣言します。

    // 指定された管理者IDに関連するすべてのブログを取得します
    public List<Blog> selectAllBlog(Integer adminId) {
        return blogDao.findAllByAdmin_AdminId(adminId); // 管理者IDに基づいてすべてのブログを検索し、返します
    }

    // すべてのブログを取得します
    public List<Blog> getAllBlogs() {
        return blogDao.findAll(); // すべてのブログを検索し、返します
    }

    // ブログを保存または更新します
    public Blog saveOrUpdateBlog(Blog blog) {
        return blogDao.save(blog); // ブログを保存または更新し、その結果を返します
    }

    // 指定されたIDのブログを取得します
    public Blog getBlogById(Integer id) {
        return blogDao.findByPostId(id); // ポストIDに基づいてブログを検索し、返します
    }

    @Transactional // このメソッドがトランザクション内で実行されることを示します
    public Blog createBlogPost(String adminEmail, String title, String content) {
        Admin admin = adminService.findAdminByEmail(adminEmail); // 管理者のメールアドレスで管理者を検索します
        if (admin == null) {
            throw new IllegalArgumentException("Admin not found"); // 管理者が見つからない場合は例外をスローします
        }
        Blog blog = new Blog(admin, title, content); // 新しいブログエンティティを作成します
        return blogDao.save(blog); // ブログを保存し、その結果を返します
    }

    @Transactional // このメソッドがトランザクション内で実行されることを示します
    public boolean deleteBlog(Integer postId) {
        try {
            blogDao.deleteByPostId(postId); // ポストIDに基づいてブログを削除します
            return true; // 削除が成功した場合は true を返します
        } catch (Exception e) {
            e.printStackTrace(); // 例外が発生した場合はスタックトレースを出力します
            return false; // 削除が失敗した場合は false を返します
        }
    }

    // 指定された管理者IDに関連するすべてのブログを取得します
    public List<Blog> getAllBlogsByAdminId(Integer adminId) {
        return blogDao.findAllByAdmin_AdminId(adminId); // 管理者IDに基づいてすべてのブログを検索し、返します
    }
}
