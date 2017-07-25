package utils;

import java.sql.SQLException;
import java.util.Map;

import org.junit.Test;

import config.ConfigManager;
import junit.framework.Assert;
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

	public static void checkOrderData2( String param) throws SQLException {
		MysqlBase base = new MysqlBase();
		base.setUrl("jdbc:mysql://10.10.1.62:3306/xlpayproxy");
		base.setUserName("root");
		base.setPassword("sd-9898w");
		System.out.println("aaa");
		base.setSql("SELECT orderamt ,paytype,BizNo,BizOrderId FROM bizorder WHERE usershow= ? ORDER BY seqid DESC LIMIT 1");
		base.setParams(new String[] { param });
	    base=	base.executeQuery_sql();
	    Map map= base.getColumnLabels();
	    System.out.println(map.get("orderamt"));
	    System.out.println(map.get("paytype"));
	    System.out.println(map.get("BizNo"));
	    System.out.println(map.get("BizOrderId"));
	    String bizOrderId=(String) map.get("BizOrderId");
	    System.out.println(bizOrderId);
	    
	    //SELECT fpresult FROM callback_queues WHERE forderid =2016112402460100000077 
	    
	    
		MysqlBase base2 = new MysqlBase();
		base2.setUrl("jdbc:mysql://10.10.63.10:3306/vws_center");
		base2.setUserName("root");
		base2.setPassword("sd-9898w");

//		base2.setSql("SELECT fpresult FROM callback_queues WHERE forderid = ? ");
//		base2.setParams(new String[] { bizOrderId });
		
		base2.setSql("SELECT fpresult FROM callback_queues WHERE forderid = '2016112402460100000077' ");
//	    base2=	base2.executeQuery_sql();
//	    Map map2= base2.getColumnLabels();
//	    String fpresult=(String) map2.get("fpresult");
        System.out.println(base2.executeQueryRtnFirstColumn());
//        System.out.println("fpresult:"+fpresult);
        Assert.assertEquals("2", base2.executeQueryRtnFirstColumn());
        
	}
	
	public static void checkOrderData( String userNme,String paytypeData,String orderamtData) throws SQLException {
		MysqlBase base = new MysqlBase();
		base.setUrl("jdbc:mysql://10.10.1.62:3306/xlpayproxy");
		base.setUserName("root");
		base.setPassword("sd-9898w");
		base.setSql("SELECT orderamt ,BizOrderId FROM bizorder WHERE usershow= ? and paytype= ? and BizNo=000001050 ORDER BY seqid DESC LIMIT 1");
		base.setParams(new String[] { userNme, paytypeData});
	    base=	base.executeQuery_sql();
	    Map map= base.getColumnLabels();
	    String orderamt=(String) map.get("orderamt");
	    Assert.assertEquals(orderamtData, orderamt);
	 
	}
	
	public static void checkOrderData1( String userNme,String paytypeDate,String orderamtData) throws SQLException {
		MysqlBase base = new MysqlBase();
		base.setUrl("jdbc:mysql://10.10.1.62:3306/xlpayproxy");
		base.setUserName("root");
		base.setPassword("sd-9898w");
		base.setSql("SELECT orderamt ,BizOrderId FROM bizorderok WHERE usershow= ? and paytype= ? and BizNo=000001050 ORDER BY seqid DESC LIMIT 1");
		base.setParams(new String[] { userNme, paytypeDate});
	    base=	base.executeQuery_sql();
	    Map map= base.getColumnLabels();
	    String orderamt=(String) map.get("orderamt");
	    Assert.assertEquals(orderamtData, orderamt);	    
        
	}
	
	public static void checkOrderData3( String userNme,String paytypeData,String orderamtData) throws SQLException {
		MysqlBase base = new MysqlBase();
		base.setUrl("jdbc:mysql://10.10.1.62:3306/xlpayproxy");
		base.setUserName("root");
		base.setPassword("sd-9898w");
		base.setSql("SELECT orderamt ,PayType FROM bizorder WHERE usershow= ? and BizNo=000001050 ORDER BY seqid DESC LIMIT 1");
		base.setParams(new String[] { userNme});
	    base=	base.executeQuery_sql();
	    Map map= base.getColumnLabels();
	    String orderamt=(String) map.get("orderamt");
	    String paytype=(String) map.get("PayType");
	    Assert.assertEquals(orderamtData, orderamt);	  
	    Assert.assertTrue(paytype.indexOf('B')!=-1);       
	}
	
	public static void checkPayMoney( String xunleiid,String orderamtData) throws SQLException {
		MysqlBase base = new MysqlBase();
		base.setUrl("jdbc:mysql://10.10.1.62:3306/xlpayproxy");
		base.setUserName("root");
		base.setPassword("sd-9898w");
		base.setSql("SELECT orderamt  FROM bizorder WHERE xunleiid= ?  ORDER BY seqid DESC LIMIT 1");
		base.setParams(new String[] {xunleiid});
	    base=	base.executeQuery_sql();
	    Map map= base.getColumnLabels();
	    String orderamt=(String) map.get("orderamt");
	    Assert.assertEquals(orderamtData, orderamt);	    
        
	}
	
	//@Test
	//public void test() {
		//System.out.println(getValidateCode("aq_center", "17704026214"));
	//}
	
//	@Test
//	public void test2() throws SQLException {
//		checkOrderData("paycenter205","E");
//	}
	
	@Test
	public void test3() throws SQLException {
		checkOrderData3("paycenter205", "B", "30.00");
	}	
}
