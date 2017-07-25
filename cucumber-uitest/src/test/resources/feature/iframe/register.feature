Feature: register

  @registerbymobile
  Scenario Outline: weblogin 使用用户名密码
    Given I navigate to "http://i.xunlei.com/login.html"
    Then I switch to frame having name "loginIframe"
    Then I click on element having id "turnRegister"
    Then I wait for 3 sec
    Then I enter "<mobile>" into input field having id "pr_m"
    Then I wait for 3 sec
    Then I click on element having id "pr_gc"
    Then I wait for 3 sec
    Then if verifycode displayed then input verifycode having id "pr_captcha"
    Then I wait for 3 sec
    Then I click on element having id "pr_ccb"
    Then I wait for 3 sec
   	Then I enter from redis key "<mobile>" into input field having id "pr_c"
    Then I wait for 3 sec
    Then I click on element having id "pr_submit"
    Then I wait for 3 sec
    Then I enter "<password>" into input field having id "pr_p"
    Then I wait for 3 sec
    Then I click on element having id "pr_finish"
    Then I wait for 10 sec
    Then I should see page url as "http://dynamic.i.xunlei.com/user"
    # 从cookie 获取uid 解除手机绑定
    Then I unbind mobile "<mobile>" from Cookie key
    Examples: 
      | mobile      | password   |
      | 17704026214 | test123456 | 
