### Personal Blog System

## プロジェクト概要
このプロジェクトは、個人ブログシステムの開発を目指したものです。主な機能として、ユーザー登録、ログイン、ブログの登録、編集、リスト表示などがあります。**Less is more**という考え方に基づき、写真機能などの不要な機能をブログから除外しました。これは、ECサイトと異なるシンプルなレイアウトを実現するためです。

この課題の設計意図は、フロントエンドとバックエンドがどのように連携するかを学生が理解しているかを評価することにあるため、私はCRUD機能（Create、Read、Update、Delete）が正確に動作するかどうかを力を入れました。とくに、ステータスコードの管理には、調査した列挙型クラスHttpStatusを使用することに挑戦してみました。

## ファイル構成

### コントローラー
- **AdminRegisterController.java**: 管理者の登録処理を担当するコントローラー。
- **BlogDeleteController.java**: ブログ削除の処理を担当するコントローラー。
- **BlogEditController.java**: ブログ編集の処理を担当するコントローラー。
- **BlogListController.java**: ブログリストの表示処理を担当するコントローラー。
- **BlogRegisterController.java**: ブログ登録の処理を担当するコントローラー。
- **LoginController.java**: ユーザーのログイン処理を担当するコントローラー。
- **LogoutController.java**: ユーザーのログアウト処理を担当するコントローラー。

### DAO (Data Access Object)
- **AdminDao.java**: 管理者データの操作を行うDAOクラス。
- **BlogDao.java**: ブログデータの操作を行うDAOクラス。

### エンティティ
- **Admin.java**: 管理者情報を表すエンティティクラス。
- **Blog.java**: ブログ情報を表すエンティティクラス。

### サービス
- **AdminService.java**: 管理者に関連するビジネスロジックを担当するサービスクラス。
- **BlogService.java**: ブログに関連するビジネスロジックを担当するサービスクラス。
- **LoginService.java**: ログインに関連するビジネスロジックを担当するサービスクラス。

### アプリケーション
- **PersonalBlogApplication.java**: アプリケーションのエントリーポイントとなるクラス。

### HTMLファイル
- **blog_editing.html**: ブログ編集ページのHTMLファイル。
- **blog_list.html**: ブログリストページのHTMLファイル。
- **blog_register.html**: ブログ登録ページのHTMLファイル。
- **error.html**: エラーページのHTMLファイル。
- **login.html**: ログインページのHTMLファイル。
- **register.html**: 登録ページのHTMLファイル。

### スタイルシート
- **blog_editing.css**: ブログ編集ページのスタイルシート。
- **blog_list.css**: ブログリストページのスタイルシート。
- **blog_register.css**: ブログ登録ページのスタイルシート。
- **login.css**: ログインページのスタイルシート。
- **register.css**: 登録ページのスタイルシート。

### 画像
- **background.png**: プロジェクトで使用される背景画像。
- **blogpic.png**: ブログで使用される画像。

## 開発環境
- **言語**: Java
- **フレームワーク**: Spring Boot
- **ビルドツール**: Maven
- **データベース**: PostgreSQL
- **IDE**: SpringTool
- **バージョン管理**: Git (GitHub)

## 設計書関係
- **ユースケース図**: [リンク](https://docs.google.com/spreadsheets/d/1ff34zrS7zA0ML4ssA0zZEDyBhvVmqPAi/edit#gid=827394712)
- **テーブル定義書**: [リンク](https://docs.google.com/spreadsheets/d/1ff34zrS7zA0ML4ssA0zZEDyBhvVmqPAi/edit#gid=827394712)
- 
## 参考文献
- [参考動画](https://www.youtube.com/watch?v=uTCmNDnP2VY&t=26s)
- [Spring Framework Documentation](https://spring.pleiades.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html)

### 困っていること
- 不要なテーブルを想定した
- バックグラウンドイメージのURL
- 属性名の不一致
- 削除時WEBの再読込み
