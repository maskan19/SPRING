package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Factory Object[Method] Pattern : 객체 생성을 전담하는 객체를 운영하는 구조.
 *
 * pooling 정책
 */
public class ConnectionFactory {
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	private static String connectionMessage;
	private static DataSource ds;
	static {
//		Properties properties = new Properties();
//		try(
//			InputStream is = ConnectionFactory.class.getResourceAsStream("dbInfo.properties");
		// 상대 경로
//		) {
//			properties.load(is);
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo", Locale.ENGLISH);
		// 클래스패스 리소스를 클래스인 것처럼 사용하는 BASE NAME
		//_**가 붙지 않는 파일이 default lang

		driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		connectionMessage = bundle.getString("connectionMessage");
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
		long maxWait = Integer.parseInt(bundle.getString("maxWait"));

		ds = new BasicDataSource();
		((BasicDataSource) ds).setDriverClassName(driverClassName);
		((BasicDataSource) ds).setUrl(url);
		((BasicDataSource) ds).setUsername(user);
		((BasicDataSource) ds).setPassword(password);
		((BasicDataSource) ds).setInitialSize(initialSize);// 기본 생성 pool의 개숰
		((BasicDataSource) ds).setMaxTotal(maxTotal);// 최대 pool의 개수
		((BasicDataSource) ds).setMaxWaitMillis(maxWait);// 기본 생성 개수를 초과한 경우 최대 2초 기다린 후 maxtotal까지 만듬
		// sqlexception을 runtimeexception으로 처리함

//			ds = new OracleDataSource();
//			((OracleDataSource)ds).setURL(url);
//			((OracleDataSource)ds).setUser(user);
//			((OracleDataSource)ds).setPassword(password);

//			Class.forName(driverClassName);

//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
	}

	public static Connection getConnection() throws SQLException {
		System.out.println(connectionMessage);
		return ds.getConnection();
	}
}
