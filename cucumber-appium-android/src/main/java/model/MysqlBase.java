package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MysqlBase {
	private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	private String driver=MYSQL_DRIVER_CLASS;
	private String url;
	private String userName;
	private String password;
	private String sql;
	private String[] params;
	private ResultSet rs;
	private int size;
	private Map<String, String> columnLabels;
	private String[] columnIndexs;
	private int affected_rows_num;

	private Connection conn = null;
	private PreparedStatement preparedStatement = null;
	
	/**
	 * 建立数据库连接
	 * 
	 * @param driver
	 *            ：驱动类
	 * @param url
	 *            ：数据库连接串
	 * @param userName
	 *            ：用户名
	 * @param password
	 *            ：密码
	 * @return ：数据库连接
	 */
	private Connection getConnection(String driver, String url, String userName, String password) {
		try {
			if (driver.isEmpty()) {
				Class.forName("com.mysql.jdbc.Driver");
			} else {
				Class.forName(driver);
			}

			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public MysqlBase executeQuery_sql() {
		Connection conn = getConnection(driver, url, userName, password);
		try {
			preparedStatement = conn.prepareStatement(sql);
			System.out.println("sql:" + sql);
			if (params != null && !params.equals("")) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setString(i + 1, params[i]);
				}
			}
			rs = preparedStatement.executeQuery();
			size = rs.getRow();
			int j = 0;

			// 将第一行的值存到resp
			while (rs.next()) {
				if (j == 0) {
					ResultSetMetaData md = rs.getMetaData();
					Map<String, String> map = new HashMap<String, String>();
					String[] arry = new String[md.getColumnCount()];
					for (int i = 1; i < md.getColumnCount(); i++) {
						// 默认转成String
						map.put(md.getColumnLabel(i), rs.getObject(i).toString());
						arry[i - 1] = rs.getObject(i).toString();
					}
					setColumnLabels(map);
					setColumnIndexs(arry);
				} else {
					j++;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			free(conn, preparedStatement, rs);
		}
		return this;
	}

	public String executeQueryRtnFirstColumn() {
		String str=null;
		Connection conn = getConnection(driver, url, userName, password);
		try {
			preparedStatement = conn.prepareStatement(sql);
			System.out.println("sql:" + sql);
			if (params != null && !params.equals("")) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setString(i + 1, params[i]);
				}
			}
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				str=rs.getString(1);
				break;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			free(conn, preparedStatement, rs);
		}
		return str;
	}
	public MysqlBase executeUpdate_sql() {

		Connection conn = getConnection(driver, url, userName, password);
		try {
			preparedStatement = conn.prepareStatement(sql);
			System.out.println("sql:" + sql);
			if (params != null && !params.equals("")) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setString(i + 1, params[i]);
				}
			}
			affected_rows_num = preparedStatement.executeUpdate();
			if (affected_rows_num <= 0) {
				System.err.println("执行sql影响的行数:" + affected_rows_num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			free(conn, preparedStatement, rs);
		}
		return this;
	}
	/**
	 * 释放连接
	 * 
	 * @param conn:数据库连接
	 */
	private  void freeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 释放statement
	 * 
	 * @param statement：数据库语句
	 */
	private  void freeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 释放resultset
	 * 
	 * @param rs：结果集
	 */
	private  void freeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 释放资源
	 * 
	 * @param conn:数据库连接
	 * @param statement：数据库语句
	 * @param rs：结果集
	 */
	public  void free(Connection conn, Statement statement, ResultSet rs) {
		if (rs != null) {
			freeResultSet(rs);
		}
		if (statement != null) {
			freeStatement(statement);
		}
		if (conn != null) {
			freeConnection(conn);
		}
	}

	public void addSqlParam(String param) {
		if (params == null || params.length == 0) {
			String h = param;
			String[] ary = { h };
			setParams(ary);
		} else {
			int length = params.length;
			String[] old = params;
			String[] ary = new String[length + 1];
			for (int i = 0; i < old.length; i++) {
				ary[i] = old[i];
			}
			ary[length] = param;
			setParams(ary);
		}
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Map<String, String> getColumnLabels() {
		return columnLabels;
	}

	public void setColumnLabels(Map<String, String> columnLabels) {
		this.columnLabels = columnLabels;
	}

	public String[] getColumnIndexs() {
		return columnIndexs;
	}

	public void setColumnIndexs(String[] columnIndexs) {
		this.columnIndexs = columnIndexs;
	}

	public int getAffected_rows_num() {
		return affected_rows_num;
	}

	public void setAffected_rows_num(int affected_rows_num) {
		this.affected_rows_num = affected_rows_num;
	}
}
