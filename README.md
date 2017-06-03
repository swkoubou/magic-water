# magic-water
工房のマツキヨでスポーンする水を管理するやつ

#導入方法
1. Scalaをインストールします  
	Macの場合  
	
	```
	brew install scala
	```  	
	Ubuntuの場合  
	
	```
	sudo -s
	apt install scala
	```  
	他のOSは知りません。

2. Playframeworkを導入します  
	導入方法はこちら   
	[アメリカ語が得意な場合](https://www.playframework.com/documentation/2.5.x/Installing)・[アメリカ語が苦手な場合(古くなってる可能性あり)](https://www.playframework.com/documentation/ja/2.4.x/Installing)

3. SQLiteのプラグインを導入します  
	プロジェクトのディレクトリに`lib/`ディレクトリを作成  
	[https://bitbucket.org/xerial/sqlite-jdbc/downloads/](https://bitbucket.org/xerial/sqlite-jdbc/downloads/)  
	から適当なバージョンのjarをダウンロードして`lib/`へ配置   
	例 
	 
	```
	cd lib/
	wget https://bitbucket.org/xerial/sqlite-jdbc/downloads/sqlite-jdbc-3.18.0.jar
	```
	
#動かし方
プロジェクトのディレクトリで

```a
ctivator run
```

これでデバッグモードで動く
	
	


