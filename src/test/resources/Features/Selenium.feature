Feature: Login to Google

  @Selenium
  Scenario: Login to Google
      Given Login and Search Google
      And Test selenium 4 features
      Then Search On Google

  @ChromeDevTools
  Scenario: Chrome Dev Tool Features
    When Collect Performance Metrics
    When Override Device Mode
    When Listen Call Logs
