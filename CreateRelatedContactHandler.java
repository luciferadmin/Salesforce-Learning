/**
* @description       : Apex Handler to create related Contacts based on specified conditions in Account records.
* @author            : Rajnish
* @group             : Service
* @last modified on  : 30.12.2023
* @last modified by  : Rajnish
* Modifications Log
* Ver   Date         Author           Modification
* 1.0   30.12.2023   Rajnish       Initial Version
*/

public class CreateRelatedContactHandler {
    /**
* @description: Creates related Contacts based on specified conditions in provided Account records.
* @param lstAcc: List of Account records to process.
*/
    public static void handleCreateRelatedCon(List<Account> lstAcc) {
        // List to store Contacts to be created.
        List<Contact> conList = new List<Contact>();
        Contact con = new Contact();
        // Iterate through the provided Account records.
        for (Account objAcc : lstAcc) {
            // Check if the Account has the condition to create a related Contact.
            if (objAcc.Create_Contact__c == true) {
                // Create a new Contact with specified details.
                con.LastName = objAcc.Name + '  Related';
                con.AccountId = objAcc.Id;
                con.Phone = objAcc.Phone;
                conList.add(con);
            }
        }
        
        // Check if there are Contacts to be created.
        if (!conList.isEmpty()) {
            // Insert the list of Contacts.
            insert conList;
        }
    }
}
