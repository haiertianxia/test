Feature: pointRecharge


  @pointRecharge
  Scenario Outline:    
    #登录
    Given I navigate to "https://pay.xunlei.com/"
    Then I wait for 5 sec
    Then I switch to frame having name "loginIframe"
    Then I wait for 5 sec
    Then I enter "<username>" into input field having id "al_u"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "al_p"
    Then I wait for 3 sec
    Then I click on element having id "al_submit"
    Then I wait for 3 sec
    #Then I refresh page
    #Then I switch to alert accept
    #Then I wait for load  5 sec 
    Then I switch to main content
    #Then I should see page url as "https://pay.xunlei.com/service.html"  
    Then I wait for 5 sec  
    
   #点击我的账户
   Then I click on element having css "#side-nav > li:nth-child(1) > a"
   Then I wait for 3 sec
   #点击充值
   Then I click on element having css "#profile > div > div > div.account_info > a.btn"
   Then I wait for 3 sec
   #点击立即充值
   Then I click on element having css "#l_tabcont > div:nth-child(2) > div > div > div.privilege_wp > div > a"
   Then I wait for 3 sec 
   #切换窗口，先关闭第三方窗口
   Then I close to window having title "支付宝 - 网上支付 安全快速！"
   Then I wait for 3 sec
   #切换支付中心
   Then I switch to previous having title "迅雷支付中心"
   Then I wait for 3 sec
   #关闭支付弹框
   Then executejs css "body > div:nth-child(7) > div:nth-child(2) > a" 
   Then I wait for 1 sec
  #点击财付通
   Then I click on element having xpath "//*[@id="l_tabcont"]/div[2]/div/div/div[2]/div[3]"
   Then I wait for 1 sec
   #点击立即充值
   Then executejs css "#l_tabcont > div:nth-child(2) > div > div > div.privilege_wp > div > a"
   Then I wait for 14 sec
   #切换窗口，关闭第三方窗口
   Then I close to teny window having title "财付通 - 会支付 会生活","Pay"
   Then I wait for 3 sec
   #切换支付中心
   Then I switch to previous having title "迅雷支付中心"
   Then I wait for 3 sec
   #关闭弹框
   Then executejs css "body > div:nth-child(7) > div:nth-child(2) > a" 
   Then I wait for 2 sec
   Then I navigate back
   Then I wait for 1 sec
   #点击退出
   Then I click on element having css "#head-info > a:nth-child(3)"
   Then I wait for 1 sec
   Then I delete cookies
   Then I clear localStorage
    #关闭浏览器
    Then I close browser
    Then I kill process    
    Examples: 
      | username   | password | mobile      | newmobile   |
      | paycenter202|   123456 | 17704026220 | 13000012349 |
      