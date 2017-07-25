Feature: wap 安全中心设置手机
  
  内网账号：
  
  bindmobile：已绑定安全手机
  setmobile1:未绑定手机  uid: 150010574
   
  初始密码： 123456

  @setMobile
  Scenario Outline: 已绑定手机,修改绑定手机
    Given I navigate to "http://10.10.98.7:9988/service/vip?request=retakePsw&userno=<username>&utype=0"
    Then I wait for 3 sec
    Given I navigate to "https://aq.xunlei.com/wap/login.html"
    Then I switch to frame having name "loginIframe"
    Then I enter "<username>" into input field having id "al_u"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "al_p"
    Then I wait for 3 sec
    Then if verifycode displayed then input verifycode
    Then I wait for 3 sec
    Then I click on element having id "al_submit"
    Then I wait for 10 sec
    Then I switch to main content
    Then I should see page url as "https://aq.xunlei.com/wap/index.html"
    Then I wait for element having id "setMobile" to display
    #	 点击设置手机
    Then I click on element having id "setMobile"
    Then I wait for element having id "codeInput" to display
    Then I click on link having partial text "获取短信验证码"
    
    Then I wait for 3 sec
    Then I get validate_code from "aq_center" with account "<mobile>" and enter value into input field having id "codeInput"
    Then I wait for 3 sec
    Then I click on link having partial text "下一步"
    
    Then I wait for element having id "mobile" to display
    Then I should see page title as "输入新手机"
    Then I enter "<newmobile>" into input field having id "mobile"
    Then I click on link having partial text "下一步"
    
    Then I wait for 3 sec
    Then I wait for element having id "smsCode" to display
    Then I get validate_code from "aq_center" with account "<newmobile>" and enter value into input field having id "smsCode"
    Then I click on link having partial text "完成"
    
    # success
    Then I wait for element having class "ico-sucess" to display
    Then I should see page title as "设置成功"

    Examples: 
      | username   | password | mobile      | newmobile   |
      | bindmobile |   123456 | 17704026220 | 13000012349 |
      | bindmobile |   123456 | 13000012349 | 17704026220 |
  
   @setMobile
  Scenario Outline: 未绑定手机
    # Given 初始化 解绑手机
    # http://topsecret.vip.xunlei.com:8888/service/gateway?request=unbind&userid=150010574&nametype=5
    # http://topsecret.vip.xunlei.com:8888/service/gateway?request=setuserinfo&userid=150010574&field=mobile&value=
    Given I navigate to "http://10.10.98.7:9988/service/vip?request=retakePsw&userno=<username>&utype=0"
    Then I wait for 3 sec
    Given I navigate to "http://topsecret.vip.xunlei.com:8888/service/gateway?request=unbind&userid=<uid>&nametype=5"
    Then I wait for 3 sec
    Given I navigate to "http://topsecret.vip.xunlei.com:8888/service/gateway?request=setuserinfo&userid=<uid>&field=mobile&value="
    Then I wait for 3 sec
    Given I navigate to "https://aq.xunlei.com/wap/login.html"
    Then I wait for 3 sec
    Then I switch to frame having name "loginIframe"
    Then I enter "<username>" into input field having id "al_u"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "al_p"
    Then I wait for 3 sec
    Then if verifycode displayed then input verifycode
    Then I wait for 3 sec
    Then I click on element having id "al_submit"
    Then I wait for 10 sec
    Then I switch to main content
    Then I should see page url as "https://aq.xunlei.com/wap/index.html"
    Then I wait for element having id "setMobile" to display
    #	 点击设置手机
    Then I click on element having id "setMobile"
    
    Then I wait for element having id "pwd" to display
    Then I enter "<password>" into input field having id "pwd"
    Then I click on link having partial text "下一步"
    
		Then I wait for element having id "mobile" to display
    Then I should see page title as "输入新手机"
    Then I enter "<newmobile>" into input field having id "mobile"
    Then I click on link having partial text "下一步"
    
    Then I wait for 3 sec
    Then I wait for element having id "smsCode" to display
    Then I get validate_code from "aq_center" with account "<newmobile>" and enter value into input field having id "smsCode"
    Then I click on link having partial text "完成"
    
    # success
    Then I wait for element having class "ico-sucess" to display
    Then I should see page title as "设置成功"

    Examples: 
      | username   | password |uid         | newmobile   |
      | setmobile1 |   123456 |150010574   | 13000012350 |
