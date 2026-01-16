Feature: Login de Usuário

  Background:
    * url baseUrl

  Scenario: Logar usuário com sucesso
    Given path 'auth', 'login'
    * def payload = read('data/credentials.json')
    * print 'Credenciais: ', payload
    And request payload
    When method post
    Then status 200
    And match response.token == '#string'
    * print 'Token: ', response.token