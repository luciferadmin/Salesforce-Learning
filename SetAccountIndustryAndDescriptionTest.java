@isTest
public class SetAccountIndustryAndDescriptionTest {

    @isTest
    static void testTriggerFunctionality() {
        // Create a new Account without specifying Industry and Description
        Account testAccount = new Account(Name='Test Account');
        
        // Insert the Account
        Test.startTest();
        insert testAccount;
        Test.stopTest();

        // Retrieve the inserted Account from the database
        Account insertedAccount = [SELECT Id, Industry, Description FROM Account WHERE Id = :testAccount.Id];

        // Verify that Industry is set to 'Education' if it was null
        System.assertEquals('Education', insertedAccount.Industry, 'Industry should be set to Education');

        // Verify that Description is set to 'Account Description is blank' if it was null
        System.assertEquals('Account Description is blank', insertedAccount.Description);
        
    }
}
