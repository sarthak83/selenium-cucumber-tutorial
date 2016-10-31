@FaceBookTest
Feature: Test various annotations in Cucumber

#Scenario with AND
@TestThen
Scenario: Facebook login used to learn annotation THEN
Given I am on facebook login page
When I enter username as "TEST"
And I enter password as "THEN"
Then I get an error page


#Scenario with BUT
@TestBut
Scenario: Facebook login used to learn annotation BUT
Given I am on facebook login page
When I enter username as "TEST"
And I enter password as "BUT"
Then I get an error page
But I am served with relogin page

@TestSceanrioOutline
Scenario Outline: Test the scenario outline feature by giving bunch of inputs
Given I am on facebook login page
When I enter username as "<uname>"
And I enter password as "<pass>"
Then I get an error page

Examples:
|uname			|pass			|
|username1		|password1		|
|username2		|password2		|

@TestDataTable
Scenario: Test data table feature in facebook registration.
Given the following values:
|FirstName	|Roger		|
|LastName	|Federer	|
|email		|rog.fed	|
|re-email	|rog.fed	|
|password	|roger1232	|
|dob		|10			|
Then user registration should be unsuccessful



