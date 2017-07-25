Feature: highGame


  @highGame
  Scenario Outline:    
    #登录
    Given I navigate to "https://pay.xunlei.com/"
   
    Then I wait for 10 sec
    Then I switch to frame having id "loginIframe"
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
   #点击会员服务
   Then I click on element having css "#side-nav > li:nth-child(2) > a"
   Then I wait for 3 sec
   #点击高级网游开通
   Then I click on element having css "#gjjsq > a"
   Then I wait for 3 sec  
   Then I click on element having css "#l_tabs > ul > li:nth-child(2)"
   Then I wait for 3 sec  
   #点击单月
   Then I click on element having css "#l_tabcont > div.choicebox_wp.tab_area.mb30 > div.choicebox.lastdiv > span.tip" 
   Then I wait for 2 sec
   #点击支付平台
   Then I click on element having css "#l_tabs > ul.tab_list > li:nth-child(2)"
   Then I wait for 1 sec
   #点击支付宝
   Then I click on element having xpath "//*[@id="l_tabcont"]/div[2]/div[1]/div[1]/div[2]"
   Then I wait for 1 sec
   #点击开通
   #Then I click on element having css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   Then executejs css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   Then I wait for 14 sec
   #切换窗口，先关闭第三方窗口
   Then I close to window having title "支付宝 - 网上支付 安全快速！"
   Then I wait for 3 sec
   #切换支付中心
   Then I switch to previous having title "迅雷支付中心"
   Then I wait for 3 sec
   #关闭支付弹框
   Then executejs css "body > div:nth-child(8) > div:nth-child(2) > a" 
   Then check order_data from db whith param "150020403" should be "15.00"
   #点击财付通
   Then I click on element having xpath "//*[@id="l_tabcont"]/div[2]/div[1]/div[1]/div[3]"
   Then I wait for 1 sec
   #点击开通
   Then executejs css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   Then I wait for 14 sec
   #切换窗口，关闭第三方窗口
   Then I close to teny window having title "财付通 - 会支付 会生活","Pay"
   Then I wait for 3 sec
   #切换支付中心
   Then I switch to previous having title "迅雷支付中心"
   Then I wait for 3 sec
   #关闭弹框
   Then executejs css "body > div:nth-child(8) > div:nth-child(2) > a" 
   Then I wait for 2 sec
   #点击网上银行
   Then I click on element having css "#l_tabs > ul.tab_list > li:nth-child(3)"
   Then I wait for 2 sec
   #选择建设银行
   Then I click on element having css "#l_tabcont > div:nth-child(3) > div > div.choicebox_wp > div:nth-child(2) > span"
   Then I wait for 2 sec
   #点击开通
   Then executejs css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   Then I wait for 14 sec
   #关闭第三方窗口
   Then I close to window having title "中国建设银行 个人网上银行"
   Then I wait for 6 sec
    #切换支付中心
   Then I switch to previous having title "迅雷支付中心"
   Then I wait for 3 sec
   #关闭弹框
   Then executejs css "body > div:nth-child(8) > div:nth-child(2) > a" 
   Then I wait for 6 sec
   #点击高级网游开通
   Then I click on element having css "#l_tabs > ul > li:nth-child(3)"
   Then I wait for 3 sec
   #点击单月
   Then I click on element having css "#l_tabcont > div.choicebox_wp.tab_area.mb30 > div.choicebox.lastdiv > span.tip" 
   Then I wait for 2 sec
   #点击支付平台
   Then I click on element having css "#l_tabs > ul.tab_list > li:nth-child(2)"
   Then I wait for 1 sec
   #点击支付宝
   Then I click on element having xpath "//*[@id="l_tabcont"]/div[2]/div[1]/div[1]/div[2]"
   Then I wait for 1 sec
   #点击开通
   #Then I click on element having css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   Then executejs css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   Then I wait for 14 sec
   #切换窗口，先关闭第三方窗口
   Then I close to window having title "支付宝 - 网上支付 安全快速！"
   Then I wait for 3 sec
   #切换支付中心
   Then I switch to previous having title "迅雷支付中心"
   Then I wait for 3 sec
   #关闭支付弹框
   Then executejs css "body > div:nth-child(8) > div:nth-child(2) > a" 
   Then I wait for 2 sec
   #点击财付通
   Then I click on element having xpath "//*[@id="l_tabcont"]/div[2]/div[1]/div[1]/div[3]"
   Then I wait for 2 sec
   #点击开通
   Then executejs css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   Then I wait for 14 sec
   #切换窗口，关闭第三方窗口
   Then I close to teny window having title "财付通 - 会支付 会生活","Pay"
   Then I wait for 3 sec
   #切换支付中心
   Then I switch to previous having title "迅雷支付中心"
   Then I wait for 3 sec
   #关闭弹框
   Then executejs css "body > div:nth-child(8) > div:nth-child(2) > a" 
   #点击网上银行
   Then I click on element having css "#l_tabs > ul.tab_list > li:nth-child(3)"
   #选择建设银行
   Then I click on element having css "#l_tabcont > div:nth-child(3) > div > div.choicebox_wp > div:nth-child(2) > span"
   #点击开通
   Then executejs css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   #关闭第三方窗口
   Then I close to window having title "中国建设银行 个人网上银行"
   Then I wait for 3 sec
    #切换支付中心
   Then I switch to previous having title "迅雷支付中心"
   Then I wait for 3 sec
   #关闭弹框
   Then executejs css "body > div:nth-child(8) > div:nth-child(2) > a" 
   Then I wait for 6 sec
   Then check order_data from db whith param "150020403" should be "30.00"
   Then I click on element having css "body > div:nth-child(7) > header > div > div:nth-child(2) > a:nth-child(2)"
   Then I wait for 1 sec
   Then I delete cookies
   Then I clear localStorage
   #关闭浏览器
   Then I close browser
   Then I kill process
    
    Examples: 
      | username   | password | mobile      | newmobile   |
      | paycenter889|   123456 | 17704026220 | 13000012349 |
      