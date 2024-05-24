package pb.com.services; // このクラスが属するパッケージを宣言します。

import java.util.List; // List インターフェースをインポートします。

import org.springframework.beans.factory.annotation.Autowired; // 依存性注入のための @Autowired アノテーションをインポートします。
import org.springframework.stereotype.Service; // このクラスをサービスコンポーネントとして定義するための @Service アノテーションをインポートします。
import org.springframework.transaction.annotation.Transactional; // トランザクション管理のための @Transactional アノテーションをインポートします。

import pb.com.models.dao.AdminDao; // models.dao パッケージから AdminDao インターフェースをインポートします。
import pb.com.models.dao.BlogDao; // models.dao パッケージから BlogDao インターフェースをインポートします。
import pb.com.models.entity.Admin; // models.entity パッケージから Admin エンティティをインポートします。
import pb.com.models.entity.Blog; // models.entity パッケージから Blog エンティティをインポートします。

@Service  // このクラスがサービスとして働く
public class BlogService {

    @Autowired // blogDaoの必要なものを自動で入れてもらう
    private BlogDao blogDao; // BlogDaoのデータを使うための変数を作る 

    @Autowired // AdminDaoの必要なものを自動で入れてもらう
    private AdminDao adminDao;  // AdminDaoのデータを使うための変数を作る

    @Autowired // AdminServiceの必要なものを自動で入れてもらう
    private AdminService adminService; // AdminServiceのデータを使うための変数を作る

    // 指定された管理者IDに関連するすべてのブログを取得します
    public List<Blog> selectAllBlog(Integer adminId) {
        return blogDao.findAllByAdmin_AdminId(adminId); // 管理者IDに基づいてすべてのブログを検索し、返します
    }
//    管理者IDを使ってブログを探します。
//    見つけたブログをリスト（一覧）で返します。
    
    //いらないかも
    // すべてのブログを取得します
    public List<Blog> getAllBlogs() {
        return blogDao.findAll(); // すべてのブログを検索し、返します
    }
//    すべてのブログを探します。
//    見つけたブログをリスト（一覧）で返します。

    // ブログを保存または更新します
    public Blog saveOrUpdateBlog(Blog blog) {
        return blogDao.save(blog); // ブログを保存または更新し、その結果を返します
    }
//    ブログをデータベースに保存または更新します。
//    保存または更新されたブログを返します。

    // 指定されたIDのブログを取得します
    public Blog getBlogById(Integer id) {
        return blogDao.findByPostId(id); // ポストIDに基づいてブログを検索し、返します
    }
//    ブログのIDを使ってブログを探します。
//    見つけたブログを返します。

    @Transactional //途中で問題が起きたら、全部やめて最初の状態に戻します。
//    @Transactional は、「この部分の仕事を一つのまとまりとして扱います」というアノテーションです。
//    もし途中で問題が起きたら、全部元に戻します。
//    これで、データベースの操作が安全に行えるようになります。
    public Blog createBlogPost(String adminEmail, String title, String content) {
        Admin admin = adminService.findAdminByEmail(adminEmail); // 管理者のメールアドレスで管理者を検索します
        if (admin == null) {
            throw new IllegalArgumentException("Admin not found"); // 管理者が見つからない場合は例外をスローします
        }
        Blog blog = new Blog(admin, title, content); // 新しいブログエンティティを作成します
        return blogDao.save(blog); // ブログを保存し、その結果を返します
    }
//    管理者のメールアドレスを使って管理者を探します。
//    管理者が見つからない場合は、エラーを出します。
//    新しいブログを作ります。
//    ブログをデータベースに保存します。
//    保存されたブログを返します。

    @Transactional //途中で問題が起きたら、全部やめて最初の状態に戻します。
//  @Transactional は、「この部分の仕事を一つのまとまりとして扱います」というアノテーションです。
//  もし途中で問題が起きたら、全部元に戻します。
//  これで、データベースの操作が安全に行えるようになります。
    public boolean deleteBlog(Integer postId) {
        try {
            blogDao.deleteByPostId(postId); // ポストIDに基づいてブログを削除します
            return true; // 削除が成功した場合は true を返します
        } catch (Exception e) {
            e.printStackTrace(); // 例外が発生した場合はスタックトレースを出力します
            return false; // 削除が失敗した場合は false を返します
        }
    }
//    ブログのIDを使ってブログを削除します。
//    削除が成功したかどうかを返します。

    // 指定された管理者IDに関連するすべてのブログを取得します
    public List<Blog> getAllBlogsByAdminId(Integer adminId) {
        return blogDao.findAllByAdmin_AdminId(adminId); // 管理者IDに基づいてすべてのブログを検索し、返します
    }
//    管理者IDを使ってブログを探します。
//    見つけたブログをリスト（一覧）で返します。
}
