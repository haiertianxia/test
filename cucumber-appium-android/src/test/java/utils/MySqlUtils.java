package utils;

import org.junit.Test;

import config.ConfigManager;
import model.MysqlBase;

public class MySqlUtils {

	private static final String aq_validate_code_sql = "SELECT CODE FROM validate_code WHERE account= ? ORDER BY send_time DESC LIMIT 1";

	public static String getValidateCode(String DbName, String param) {
		ConfigManager config = new ConfigManager();
		MysqlBase base = new MysqlBase();
		if (DbName.equals("aq_center")) {
			base.setUrl(config.get("aq_db_url"));
			base.setUserName(config.get("aq_db_user"));
			base.setPassword(config.get("aq_db_psw"));
		}

		base.setSql(aq_validate_code_sql);
		base.setParams(new String[] { param });
		String validate_code = base.executeQueryRtnFirstColumn();

		return validate_code;
	}

	@Test
	public void test() {
		System.out.println(getValidateCode("aq_center", "17704026214"));
	}
}
