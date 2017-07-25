Feature: wap 安全中心找回密码
  
  内网账号：
  changepsw1：只有密码
  changepsw2：有设置密保
  changepsw3：有登陆邮箱
  changepsw4：有登陆邮箱和密保
  changepsw5：有安全邮箱
  changepsw6：有安全邮箱未激活
  changepsw7：有安全邮箱未激活和密保
  changepsw8：有安全邮箱和密保
  
  changepsw9：有登陆手机
  changepsw10：有登陆手机,安全邮箱
  changepsw11：有登陆手机,安全邮箱,密保
  
  changepsw13：有安全手机,安全邮箱
  changepsw14：有安全手机,安全邮箱,密保
  changepsw15：有安全手机
   
  初始密码： 123456

  @findPwd
  Scenario Outline: 通过手机找回密码
    Given I navigate to "http://10.10.98.7:9988/service/vip?request=retakePsw&userno=<username>&utype=0"
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
    Then I wait for element having id "findPwd" to display
    #	 点击 找回登录密码
    Then I click on element having id "findPwd"
    Then I wait for element having id "codeInput" to display
    #	输入验证码
    Then I enter from cookie key "CODE"  into input field having id "codeInput"
    Then I wait for 3 sec
    Then I click on link having partial text "下一步"
    Then I wait for 3 sec
    Then I wait for element having id "codeInput" to display
    Then I click on link having partial text "获取短信验证码"
    Then I wait for 3 sec
    Then I get validate_code from "aq_center" with account "<account>" and enter value into input field having id "codeInput"
    Then I wait for 3 sec
    #	 点击 下一步
    Then I click on link having partial text "下一步"
    Then I wait for element having id "psw" to display
    Then I should see page title as "设置新密码"
    Then I enter "<newpwd>" into input field having id "psw"
    Then I enter "<newpwd>" into input field having id "psw2"
    Then I enter from cookie key "CODE"  into input field having id "codeInput"
    Then I click on link having partial text "完成"
    # success
    Then I wait for element having class "ico-sucess" to display
    Then I should see page title as "设置成功"

    Examples: 
      | username    | password | account     | newpwd     |
      | changepsw9  |   123456 | 13100000123 | test123456 |
      #| changepsw10 |   123456 | 13000012346 | test123456 |
      #| changepsw15 |   123456 | 13000012348 | test123456 |

  @findPwd
  Scenario Outline: 未绑定手机，验证失败，提示 其他方式找回密码
    Given I navigate to "http://10.10.98.7:9988/service/vip?request=retakePsw&userno=<username>&utype=0"
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
    Then I wait for element having id "findPwd" to display
    #	 点击 找回登录密码
    Then I click on element having id "findPwd"
    Then I wait for 3 sec
    ## 验证失败
    Then I wait for element having class "ico-fail" to display
    Then element having css "p.title" should have text as "验证失败！"
    Then element having css "body > div > div.cbmsg > div > p:nth-child(2)" should have text as "未设置手机，无法找回密码"
    Then element having css "p.tipmsg" should have partial text as "aq.xunlei.com"
    #Then element having css "p.tipmsg>a" should have attribute "href" with value "https://aq.xunlei.com"
    Then element having css "body > div > div.nf-ft > a" should have text as "其他方式找回密码"
    Then element having css "div.nf-ft>a" should have attribute "href" with value "https://aq.xunlei.com/password_find.html"

    Examples: 
      | username   | password |
      | changepsw1 |   123456 |
      | changepsw2 |   123456 | 
      | changepsw3 |   123456 |
      | changepsw4 |   123456 | 
      | changepsw5 |   123456 | 
      | changepsw6 |   123456 |
      | changepsw7 |   123456 | 
      | changepsw8 |   123456 |
