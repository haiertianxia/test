Feature: mycount


  @mycount
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
   #点击账户资料
   Then I click on element having id "to-account"
   Then I wait for 3 sec
   Then I click on element having css "#account-business-info > div.tab_wp > ul > li:nth-child(2) > a" 
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
      