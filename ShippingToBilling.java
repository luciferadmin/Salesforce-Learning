/** 
* @description       : Set Shipping Address into the Billing Address.						
* @author            : Rajnish Kumar 
* @group             : Service
* @last modified on  : 17.12.2023
* @last modified by  : Rajnish Kumar 
* Modifications Log
* Ver   Date         Author     Modification
* 1.0   17.12.2023   Rajnish   Initial Version
**/
// Trigger to synchronize Shipping Address fields to Billing Address fields on Account records before insertion and update
trigger ShippingToBilling on Account (before insert, before update) {
    // Loop through the new and updated Account records
    for(Account acc : trigger.new){
        // Copy Shipping Address fields to Billing Address fields
        acc.BillingStreet = acc.ShippingStreet;
        acc.BillingCity = acc.ShippingCity;
        acc.BillingState = acc.ShippingState;
        acc.BillingPostalCode = acc.ShippingPostalCode;
        acc.BillingCountry = acc.ShippingCountry;
    }
}
