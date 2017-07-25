Feature: mpayHighGame 


@mpayHighGame 
Scenario Outline: 
#登录
	Given I navigate to "http://m.pay.xunlei.com/" 
	Then I wait for 5 sec 
	#    Then I refresh page
	#    Then I refresh page
	#    Then I refresh page
	#    Then I click on element having css "a[data-clickid='supervip']"
	
	#    Then I click on element having css "body > div > div > div.pri_user > div.pri_opt > a"
	Then executejs css ".btn_chg" 
	#    Then I click on element having css ".btn_chg"   
	#    Then I click on element having class "btn_chg"
	#    Then I click on element having xpath "/html/body/div/div/div[1]/div[2]/a/span[2]"
	#登录
	Then I wait for 3 sec 
	Then I switch to frame having name "loginIframe" 
	
	Then I enter "<username>" into input field having id "al_u" 
	Then I wait for 3 sec 
	Then I enter "<password>" into input field having id "al_p" 
	Then I wait for 3 sec 
	Then executejs id "al_submit" 
	Then I switch to main content 
	Then I wait for 3 sec 
	Then I should see page url as "http://m.pay.xunlei.com/" 
	Then I wait for 2 sec 
	# Then I mouseClick css "body > div > div > div.pri_list > ul > li:nth-child(1) > a" 
	#直接使链接跳转到点击超级会员应跳的页面
	Then I gettohrefurl css "a[data-clickid='highgame']" 
#	Then I wait for load 5 sec 
	#    Then I should see page url as "http://m.pay.xunlei.com/vippay/pay.html?bizNo=supervip"
	# Then I mouseClick   css "body > div.wrap > div > div.pay_main > div.pay_type > ul > li.cur > span"
	#向下滑动滚动条
	Then I wait for load 30 sec
	Then I scroll to end of page 
	#选择支付宝
	Then executejs css "body > div.wrap > div > div.pay_way > ul > li:nth-child(2)"  
	Then I wait for 3 sec 
	
	#点击确认支付
	Then executejs css "body > div.wrap > div > div.sumbit > a"
	#Then I gettohrefurl css ".btn_pay" 
	
	
	Then I wait for 7 sec  
	Then I navigate back
	Then I wait for 7 sec 
	#校验支付金额刚登录进来默认高级网游
	Then  check order_data from db whith param "150020403" should be "360.00" 
	#选择网游
	Then executejs css "body > div.wrap > div > div.pay_main > div.pay_type > ul > li:nth-child(1)"
	Then I wait for 2 sec
	Then I scroll to end of page
	Then I wait for 3 sec
	#选择支付宝
	Then executejs css "body > div.wrap > div > div.pay_way > ul > li:nth-child(2)"  
	Then I wait for 3 sec 
	
	#点击确认支付
	Then executejs css "body > div.wrap > div > div.sumbit > a"
	Then I wait for 6 sec
	Then I navigate back
	Then I wait for 6 sec 
	#校验超级下单的金额
	Then check order_data from db whith param "150020403" should be "149.00"
	
	Examples: 
		| username   | password | mobile      | newmobile   |
		| paycenter889 |   123456 | 17704026220 | 13000012349 |    
		
   