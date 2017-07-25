Feature: pointPay


  @pointPay
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
    Then I wait for 10 sec  
    #点击会员服务
   Then I click on element having css "#side-nav > li:nth-child(2) > a"
   Then I wait for 3 sec
   #点击白金开通
   Then I click on element having css "#bjvip > a"
   Then I wait for 3 sec
   #选择白金会员
   Then I click on element having css "#tab-bjvip"
   Then I wait for 3 sec
   #选择单月
   Then I click on element having css "#l_tabcont > div.choicebox_wp.tab_area.mb30 > div:nth-child(3) > span.tip" 
   Then I wait for 2 sec
   #选择雷点支付
   Then I click on element having css "#l_tabs > ul.tab_list > li:nth-child(6)" 
   Then I wait for 2 sec
   #输入密码
   Then I enter "123456qq" into input field having css "#l_tabcont > div:nth-child(5) > div.bd_payway > p.fm_group > input"
   Then I wait for 2 sec
   #确认支付
   Then I click on element having css "#l_tabcont > div:nth-child(5) > div.bd_payway > div > div > a"
   Then I wait for 2 sec 
   Then I navigate back 
   Then I wait for 2 sec 
   #点击退出
   Then I click on element having css "body > div:nth-child(7) > header > div > div:nth-child(2) > a:nth-child(2)"
   Then I wait for 1 sec
   Then I delete cookies
   Then I clear localStorage
    #关闭浏览器
    Then I close browser
    Then I kill process    
    Examples: 
      | username   | password | mobile      | newmobile   |
      | paycenter202|   123456 | 17704026220 | 13000012349 |
      