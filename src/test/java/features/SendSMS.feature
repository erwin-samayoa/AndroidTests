Feature: Send SMS

  Test of sending messages and deleting them

 Scenario Outline: The user can send a message
   Given I'm in the messenger app
   When I receive a "<message>" from "<number>"
   Then I can delete it
   Examples:
     |number|message|
     |1234567890|Test1|
     |1234567891|Test2|
     |1234567892|Test3|