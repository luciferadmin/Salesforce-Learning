@isTest
public class ShippingToBillingTest {

       @isTest
    static void testTriggerFunctionality() {
        // Create a new Account without specifying Billing and Shipping Address fields
        Account testAccount = new Account(Name = 'Test Account');
        
        // Set Shipping Address fields
        testAccount.ShippingStreet = '123 Shipping St';
        testAccount.ShippingCity = 'Shipping City';
        testAccount.ShippingState = 'Shipping State';
        testAccount.ShippingPostalCode = '12345';
        testAccount.ShippingCountry = 'Shipping Country';

        // Insert the Account
        Test.startTest();
        insert testAccount;
        Test.stopTest();

        // Retrieve the inserted Account from the database
        Account insertedAccount = [SELECT Id, BillingStreet, BillingCity, BillingState, BillingPostalCode, BillingCountry
                                   FROM Account WHERE Id = :testAccount.Id];

        // Verify that Billing Address fields are set to the same values as Shipping Address fields
        System.assertEquals(testAccount.ShippingStreet, insertedAccount.BillingStreet, 'BillingStreet should match ShippingStreet');
        System.assertEquals(testAccount.ShippingCity, insertedAccount.BillingCity, 'BillingCity should match ShippingCity');
        System.assertEquals(testAccount.ShippingState, insertedAccount.BillingState, 'BillingState should match ShippingState');
        System.assertEquals(testAccount.ShippingPostalCode, insertedAccount.BillingPostalCode, 'BillingPostalCode should match ShippingPostalCode');
        System.assertEquals(testAccount.ShippingCountry, insertedAccount.BillingCountry, 'BillingCountry should match ShippingCountry');
    }
}
