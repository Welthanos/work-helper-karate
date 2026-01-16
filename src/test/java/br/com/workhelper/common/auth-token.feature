@ignore
Feature: Autenticação

  Background:
    * url baseUrl

  Scenario: Gerar token de autenticação
    Given path 'auth', 'login'
    * def payload = read('data/credentials.json')
    And request payload
    When method post
    Then status 200
    * def authToken = response.token