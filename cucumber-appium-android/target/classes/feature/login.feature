Feature: login

  @login
  Scenario Outline: login 使用用户名密码
    Given I click on element having name "登录"
    Then I clear input field having class "EditText" with number 0
    Then I enter "<username>" into input field having class "EditText" with number 0
    Then I clear input field having class "EditText" with number 1
    Then I enter "<password>" into input field having class "EditText" with number 1
    Then I click on element having name "登录"
    #Then I should see element having class "TextView" with number 4 contains "登录成功"
		Then I should see elements having class "TextView" contains "登录成功"
		
    Examples: 
      | username | password |
      | onlypsw  |   123456 |
      
      
  @userinfo
  Scenario Outline: userinfo 获取用户信息
    Given I click on element having name "登录"
    Then I clear input field having class "EditText" with number 0
    Then I enter "<username>" into input field having class "EditText" with number 0
    Then I clear input field having class "EditText" with number 1
    Then I enter "<password>" into input field having class "EditText" with number 1
    Then I click on element having name "登录"
    #Then I should see element having class "TextView" with number 4 contains "登录成功"
		Then I should see elements having class "TextView" contains "登录成功"
		
		Given I click on element having name "用户信息"
		Then I should see elements having class "TextView" contains "onlypsw"
		
    Examples: 
      | username | password |
      | onlypsw  |   123456 |
  
      
