Feature: yeyou


  @yeyou
  Scenario Outline:    
    #登录
    Given I navigate to "https://pay.xunlei.com/game/index.html"
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
    Then I should see page url as "https://pay.xunlei.com/game/index.html"    
    
    #登录账号5给账号5仙侠道482区使用支付宝方式下单，金额为100元
    #选择游戏
    #Then I click on element having class "option_open_btn"
    Then I click on element having id "game"    
    Then I wait for 3 sec
    Then I click on element having css "body > div.g-main.mt20 > div > div > div > div:nth-child(2) > div > div > div:nth-child(1) > div.option_wp > div.tab_list > a:nth-child(6)"    
    Then I wait for 3 sec    
    Then I click on element having xpath "//a[text()='仙侠道']"
    Then I wait for 5 sec
    
    #选择区服
    Then I click on element having xpath "//*[@id="fenqu"]/div[1]/a[2]"
    Then I wait for 3 sec
    Then I click on element having xpath "//a[text()='牛X482区']"
    Then I wait for 3 sec
    
    #支付宝方式下单
    Then I click on element having xpath "//*[@id="tab1"]/div[2]"
    Then I wait for 3 sec
    Then I click on element having class "btn_orange"
    Then I wait for 5 sec
#    Then I switch to new window
#    Then I wait for 5 sec
#    Then I close new window
#    Then I wait for 5 sec   
#    Then I switch to window having title "迅雷牛X页游"
    Then I wait for 5 sec
    Then element having css "body > div:nth-child(11) > div.cont.txt_rows > p" should have partial text as "请在新打开的页面完成支付"
    Then I click on element having css "body > div:nth-child(11) > div.btn_wp > a:nth-child(2)"
    
    #验证数据库是否下单
    Then check order_data from db whith param "<username>" and param "E" should be "100.00"
    Then I wait for 3 sec
    
    #打开优惠券选择框
    Then I forcefully click on element having class "lnk_addtic"
    Then I wait for 3 sec
    
    #登录账号5给账号5仙侠道181区使用支付宝方式下单，金额为100元，使用5元代金券
    Then I enter "0000007IV9A5E72BFE9" into input field having css "#tabcont2 > div:nth-child(1) > div.codebox input"
    Then I wait for 3 sec 
    Then I click on element having css "#tabcont2 > div:nth-child(1) > div.codebox > div.inpwp a"
    Then I wait for 3 sec 
    Then I click on element having css "body > div:nth-child(10) > a"  
    Then I wait for 3 sec 
    Then I click on element having css "#tabcont2 > div:nth-child(1) > div.btn_wp.txtleft > a"
    Then I wait for 3 sec 
    
    #点击立即充值按钮               
    Then I click on element having class "btn_orange"
    Then I wait for 3 sec  
    Then I click on element having css "body > div:nth-child(11) > div.btn_wp > a:nth-child(2)"
    Then I wait for 3 sec 

    #验证数据库是否下单     
    Then check order_data from db whith param "<username>" and param "E" should be "95.00"
    Then I wait for 3 sec  
    
    #解冻代金券
    Then I forcefully click on element having class "lnk_addtic"
    Then I wait for 3 sec
    Then I click on element having css "#tabcont2 > div:nth-child(1) > div.tb_ticket.tb_ticket_scroll > div.tb_ticket_body.maxrow4 > table > tbody > tr.on > td:nth-child(6) > p > a"         
    Then I wait for 3 sec    
    Then I enter "<username>" into input field having css "body > div:nth-child(14) > div.cont.cont_big > div > input"    
    Then I wait for 3 sec           
    Then I click on element having css "body > div:nth-child(14) > div.btn_wp > a.btn.btn_blue"
    Then I wait for 3 sec    
    Then I click on element having css "body > div:nth-child(10) > a"  
    Then I wait for 3 sec 
    Then I click on element having css "body > div.pop.pop_ticket > a"  
    Then I wait for 3 sec 
    
    #登录账号5给账号3迅雷魔域“迅雷一区（电信大区）”-“迅雷浙闽大区”使用网银，金额为30元
    #输入账号
    Then I clear input field having css "body > div.g-main.mt20 > div > div > div > div:nth-child(1) > div:nth-child(1) > input" 
    Then I wait for 3 sec 
    Then I enter "paycenter203" into input field having css "body > div.g-main.mt20 > div > div > div > div:nth-child(1) > div:nth-child(1) > input"
    Then I wait for 3 sec  
    
    #选择游戏
    Then I click on element having id "game"
    Then I wait for 3 sec
    Then I click on element having xpath "/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/div[1]/a[4]"
    Then I wait for 5 sec
    Then I click on element having xpath "//a[text()='迅雷魔域']"    
    Then I wait for 3 sec
    
    #选择区服，子区服
    Then I click on element having xpath "//a[text()='迅雷一区（电信大区）']" 
    Then I wait for 3 sec
    Then I click on element having xpath "/html/body/div[2]/div/div/div/div[2]/div/div/div[5]/div[2]/a[2]"
    Then I wait for 3 sec
       
    #输入金额
    Then I enter "30" into input field having class "num"
    Then I wait for 3 sec  
    
    #网银方式下单
    Then I click on element having xpath "//*[@id="tab1"]/div[3]"
    Then I wait for 3 sec
    Then I click on element having class "btn_orange"
    Then I wait for 3 sec
    Then element having css "body > div:nth-child(11) > div.cont.txt_rows > p" should have partial text as "请在新打开的页面完成支付"
    Then I wait for 3 sec
    Then I click on element having css "body > div:nth-child(11) > div.btn_wp > a:nth-child(2)"
    Then I wait for 3 sec
    
    #验证数据库是否下单
    Then check order_datae from db whith param "<username>" and param "B" should be "30.00"
    Then I wait for 3 sec   
 
    #登录账号5给账号4天地诸神13区，使用雷点只充值1元
    #输入账号
    Then I clear input field having css "body > div.g-main.mt20 > div > div > div > div:nth-child(1) > div:nth-child(1) > input" 
    Then I wait for 3 sec
    Then I enter "paycenter204" into input field having css "body > div.g-main.mt20 > div > div > div > div:nth-child(1) > div:nth-child(1) > input"
    Then I wait for 3 sec
    
    #选择游戏
    Then I click on element having id "game"
    Then I wait for 3 sec
    Then I click on element having xpath "/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/div[1]/a[5]"
    Then I wait for 5 sec
    Then I click on element having xpath "//a[text()='天地诸神']" 
    Then I wait for 3 sec
    
    #选择区服
    Then I click on element having xpath "//*[@id="fenqu"]/div[1]/a[2]"
    Then I wait for 3 sec
    Then I click on element having css "#fenqu > div.option_list > div.option_group.option_group_min > ul:nth-child(2) > li:nth-child(1) > a"
    Then I wait for 3 sec

    #输入金额
    Then I enter "1" into input field having class "num"
    Then I wait for 3 sec  
    
    #雷点密码方式下单支付
    Then I click on element having xpath "//*[@id="tab1"]/div[4]"
    Then I wait for 3 sec
    Then I click on element having xpath "//*[@id="tab1"]/div[3]"
    Then I wait for 3 sec
    Then I click on element having xpath "//*[@id="tab1"]/div[4]"
    Then I wait for 10 sec
    Then I enter "123456qq" into input field having css "#payway1 > div:nth-child(4) > div.fm_group.fm_group_min > input"        
    Then I wait for 10 sec 
    Then I click on element having class "btn_orange"
    Then I wait for 3 sec    
    
    #验证数据库是否支付成功
    Then check order_dataa from db whith param "<username>" and param "A1" should be "1.00"
    Then I wait for 3 sec
    
    #点击继续购买，回到首页
    Then I click on element having css "body > div.g-main.mt20 > div > div > div > div.info_wp.mlr30.mb10.bb > div.info_left > p > a"
    Then I wait for 3 sec  
    
    #登录账号5给账号2攻城略地192区角色“龙三轩”使用财付通方式下单，金额为15元    
    Then I clear input field having css "body > div.g-main.mt20 > div > div > div > div:nth-child(1) > div:nth-child(1) > input" 
    Then I enter "paycenter202" into input field having css "body > div.g-main.mt20 > div > div > div > div:nth-child(1) > div:nth-child(1) > input"
    Then I wait for 3 sec
    
    #选择游戏
    Then I click on element having id "game"
    Then I wait for 3 sec
    Then I click on element having xpath "/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/div[1]/a[3]"
    Then I wait for 10 sec
    Then I click on element having xpath "//a[text()='攻城掠地']" 
    Then I wait for 3 sec
    
    #选择区服
    Then I click on element having xpath "//*[@id="fenqu"]/div[1]/a[2]"
    Then I wait for 3 sec
    Then I click on element having css "#fenqu > div.option_list > div.option_group.option_group_min > ul:nth-child(2) > li:nth-child(1) > a"
    Then I wait for 3 sec
    Then I click on element having css "body > div.g-main.mt20 > div > div > div > div:nth-child(2) > div > div > div:nth-child(4) > div.option_wp.option_wp_2 > a:nth-child(2)"
    Then I wait for 3 sec
    
    #输入金额
    Then I enter "15" into input field having class "num"
    Then I wait for 3 sec  
    
    #财付通方式下单
    Then I click on element having xpath "//*[@id="tab1"]/div[2]"
    Then I wait for 3 sec
    Then I click on element having xpath "//*[@id="payway1"]/div[2]/div/div[3]"
    Then I wait for 3 sec
    Then I click on element having class "btn_orange"
    Then I wait for 3 sec
    Then I click on element having css "body > div:nth-child(11) > div.btn_wp > a:nth-child(2)"
    Then I wait for 3 sec
    
    #验证数据库是否下单
    Then check order_data from db whith param "<username>" and param "E2" should be "15.00"
    Then I wait for 3 sec
    
    #打开优惠券选择框
    Then I forcefully click on element having class "lnk_addtic"
    Then I wait for 3 sec
    
    #登录账号5给账号6攻城略地190区角色“龙三轩”使用财付通方式下单，金额为15元，使用10元现金券
    Then I enter "0000007IV9A5E72BFE9" into input field having css "#tabcont2 > div:nth-child(1) > div.codebox input"
    Then I wait for 3 sec 
    Then I click on element having css "#tabcont2 > div:nth-child(1) > div.codebox > div.inpwp a"
    Then I wait for 3 sec
    Then I click on element having css "body > div:nth-child(10) > a"  
    Then I wait for 3 sec
    Then I click on element having css "#tabcont2 > div:nth-child(1) > div.btn_wp.txtleft > a"
    Then I wait for 3 sec
    
    #点击立即充值按钮              
    Then I click on element having class "btn_orange"
    Then I wait for 3 sec 
    Then I wait for 10 sec     
    Then I click on element having css "body > div:nth-child(11) > div.btn_wp > a:nth-child(2)"
    Then I wait for 3 sec 

    #验证数据库是否下单    
    Then check order_data from db whith param "<username>" and param "E2" should be "10.00"
    Then I wait for 3 sec 

       
    #解冻代金券
    Then I forcefully click on element having class "lnk_addtic"
    Then I wait for 10 sec
    Then I click on element having css "#tabcont2 > div:nth-child(1) > div.tb_ticket.tb_ticket_scroll > div.tb_ticket_body.maxrow4 > table > tbody > tr.on > td:nth-child(6) > p > a"         
    Then I wait for 3 sec  
    Then I enter "paycenter202" into input field having css "body > div:nth-child(14) > div.cont.cont_big > div > input"              
    Then I wait for 3 sec   
    Then I click on element having css "body > div:nth-child(14) > div.btn_wp > a.btn.btn_blue"
    Then I wait for 3 sec    
    Then I click on element having css "body > div:nth-child(10) > a"  
    Then I wait for 3 sec 
    Then I click on element having css "body > div.pop.pop_ticket > a"  
    Then I wait for 3 sec
     
    #退出
    Then I click on element having css "body > div.g-hd > div > div > div:nth-child(3) > a:nth-child(2)"           
    Then I wait for 3 sec

    #关闭浏览器
    Then I close browser
        
    Examples: 
      | username   | password | mobile      | newmobile   |
      | paycenter205 |   123456 | 17704026220 | 13000012349 |
      