Feature: thirdlogin

  @loginByQQ
  Scenario Outline: weblogin 使用QQ登陆不绑定手机直接跳过
    Given I navigate to "http://i.xunlei.com/login.html"
    Then I switch to frame having name "loginIframe"
    Then I wait for element having id "img_qq" to display
    Then I click on element having id "img_qq"
    Then I wait for 3 sec
    Then I switch to new window
    Then I switch to frame having name "ptlogin_iframe"
    Then I wait for element having id "switcher_plogin" to display
    Then I click on element having id "switcher_plogin"
    Then I enter "<username>" into input field having id "u"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "p"
    Then I wait for 3 sec
    Then I click on element having id "login_button"
    Then I wait for 10 sec
    
    # modify for css change
		#Then I wait for element having class "link" to display
    #Then I click on element having class "link"
    
    Then I wait for element having xpath "/html/body/div/div[4]/div/div[3]/div/a[2]" to display
    Then I click on element having xpath "/html/body/div/div[4]/div/div[3]/div/a[2]"
    
    
    Then I wait for element having css "p.mi_name" to display
    Then I should see page url as "http://dynamic.i.xunlei.com/user"
    Then I switch to main content
    Then I wait for element having css "span.l_out>a" to display
    Then I click on element having css "span.l_out>a"
    Then I wait for 3 sec
    Then I should see page url as "http://i.xunlei.com/login.html"

    Examples: 
      | username   | password |
      | 3495822105 | qwer1234 |

  #@loginByWeiBo 
  #Scenario Outline: weblogin 使用微博登陆(已绑定手机) 网站需要验证码已不能使用
    #Given I navigate to "http://i.xunlei.com/login.html"
    #Then I switch to frame having name "loginIframe"
    #Then I wait for element having id "img_sina" to display
    #Then I click on element having id "img_sina"
    #Then I wait for 3 sec
    #Then I switch to new window
    #Then I enter "<username>" into input field having id "userId"
    #Then I wait for 3 sec
    #Then I enter "<password>" into input field having id "passwd"
    #Then I wait for 3 sec
    #Then I click on element having css "p.oauth_formbtn>a[action-type='submit']"
    #Then I wait for 10 sec
  #
    #Then I wait for element having css "p.mi_name" to display
    #Then I should see page url as "http://dynamic.i.xunlei.com/user"
    #Then I switch to main content
    #Then I wait for element having css "span.l_out>a" to display
    #Then I click on element having css "span.l_out>a"
    #Then I wait for 3 sec
    #Then I should see page url as "http://i.xunlei.com/login.html"
#
    #Examples: 
      #| username    | password     |
      #| 17704026214 | love19871201 |

  @loginByXiaoMi
  Scenario Outline: weblogin 使用小米登陆
    Given I navigate to "http://i.xunlei.com/login.html"
    Then I switch to frame having name "loginIframe"
    Then I wait for element having id "tl_arrow___" to display
    Then I click on element having id "tl_arrow___"
    Then I wait for element having id "icon_xiaomi" to display
    Then I click on element having id "icon_xiaomi"
    Then I wait for 3 sec
    Then I switch to new window
    Then I enter "<username>" into input field having id "username"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "pwd"
    Then I wait for 3 sec
    Then I click on element having id "login-button"
    Then I wait for 10 sec
    Then I wait for element having css "p.mi_name" to display
    Then I should see page url as "http://dynamic.i.xunlei.com/user"
    Then I switch to main content
    Then I wait for element having css "span.l_out>a" to display
    Then I click on element having css "span.l_out>a"
    Then I wait for 3 sec
    Then I should see page url as "http://i.xunlei.com/login.html"

    Examples: 
      | username    | password     |
      | 17704026214 | love19871201 |

  @loginByRenRen
  Scenario Outline: weblogin 使用人人登陆不绑定手机直接跳过
    Given I navigate to "http://i.xunlei.com/login.html"
    Then I switch to frame having name "loginIframe"
    Then I wait for element having id "tl_arrow___" to display
    Then I click on element having id "tl_arrow___"
    Then I wait for element having id "icon_renren" to display
    Then I click on element having id "icon_renren"
    Then I wait for 3 sec
    Then I switch to new window
    Then I enter "<username>" into input field having id "rrid"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "rrpw"
    Then I wait for 3 sec
    Then I click on element having id "submit"
    Then I wait for 5 sec
    
    # modify for css change
    #Then I wait for element having class "link" to display
    #Then I click on element having class "link"
    
    Then I wait for element having xpath "/html/body/div/div[4]/div/div[3]/div/a[2]" to display
    Then I click on element having xpath "/html/body/div/div[4]/div/div[3]/div/a[2]"
    
    Then I wait for element having css "p.mi_name" to display
    Then I should see page url as "http://dynamic.i.xunlei.com/user"
    Then I switch to main content
    Then I wait for element having css "span.l_out>a" to display
    Then I click on element having css "span.l_out>a"
    Then I wait for 3 sec
    Then I should see page url as "http://i.xunlei.com/login.html"

    Examples: 
      | username     | password |
      | haiertianxia | love1201 |

  @loginByTianYi
  Scenario Outline: weblogin 使用天翼登陆不绑定手机直接跳过
    Given I navigate to "http://i.xunlei.com/login.html"
    Then I switch to frame having name "loginIframe"
    Then I wait for element having id "tl_arrow___" to display
    Then I click on element having id "tl_arrow___"
    Then I wait for element having id "icon_tianyi" to display
    Then I click on element having id "icon_tianyi"
    Then I wait for 3 sec
    Then I switch to new window
    Then I enter "<username>" into input field having id "userName"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "password"
    Then I wait for 3 sec
    Then I click on element having id "logon"
    Then I wait for 10 sec
    
    # modify for css change
    #Then I wait for element having class "link" to display
    #Then I click on element having class "link"
    
    Then I wait for element having xpath "/html/body/div/div[4]/div/div[3]/div/a[2]" to display
    Then I click on element having xpath "/html/body/div/div[4]/div/div[3]/div/a[2]"
    
    Then I wait for element having css "p.mi_name" to display
    Then I should see page url as "http://dynamic.i.xunlei.com/user"
    Then I switch to main content
    Then I wait for element having css "span.l_out>a" to display
    Then I click on element having css "span.l_out>a"
    Then I wait for 3 sec
    Then I should see page url as "http://i.xunlei.com/login.html"

    Examples: 
      | username    | password |
      | 17704026214 | love1987 |
