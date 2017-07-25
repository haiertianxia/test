Feature: weblogin

  @loginByUserNameAndPWD
  Scenario Outline: weblogin 使用用户名密码
    Given I navigate to "http://i.xunlei.com/login.html"
    Then I switch to frame having name "loginIframe"
    Then I enter "<username>" into input field having id "al_u"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "al_p"
    Then I wait for 3 sec
    Then I click on element having id "al_submit"
    Then I wait for 10 sec
    Then I should see page url as "http://dynamic.i.xunlei.com/user"
    Then I switch to main content
    Then I wait for element having css "span.l_out>a" to display
    Then I click on element having css "span.l_out>a"
    Then I wait for 3 sec
    Then I should see page url as "http://i.xunlei.com/login.html"

    Examples: 
      | username 			| password |
      | autousername 	|   123456 |

