@tag
   Feature: Submit order funtionality
   this feature includes the flow of login to e commerce website and adding the product to cart
   
   # Runs before every scenario in this feature file
    Background: 
    Given Go to application login page
    
    Scenario Outline: Adding item to cart
    Given Enter an valid username <username> and password <password>
    When Add the product <productname> to cart
    And Checkout the details for <productName> and submit the order
    Then Check for the message "THANKYOU FOR THE ORDER." in confirmation page
    
    Examples:
	|username				|password			|productname	|
	|webauto@gmail.com		|Password@123		|IPHONE 13 PRO	|	