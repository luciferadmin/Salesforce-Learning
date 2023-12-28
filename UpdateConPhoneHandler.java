/**
 * @description       : Apex Handler to update Contact Phone based on the associated Account Phone.
 * @author            : Rajnish
 * @group             : Service
 * @last modified on  : 28.12.2023
 * @last modified by  : Your Name
 * Modifications Log
 * Ver   Date         Author        Modification
 * 1.0   28.12.2023   Rajnish     Initial Version
 */

public class UpdateConPhoneHandler {
    /**
     * @description: Updates Contact Phone based on the associated Account Phone.
     * @param lstAcc: List of Account records to process.
     */
    public static void updateConPhone(List<Account> lstAcc) {
        // Set to store unique Account IDs with non-null Phones.
        Set<Id> accIdSet = new Set<Id>();
        
        // Iterate through the provided Account records.
        for (Account acc : lstAcc) {
            // Check if the Account has a non-null Phone.
            if (acc.Phone != null) {
                accIdSet.add(acc.Id);
            }
        }
        
        // Query Contacts associated with the identified Account IDs.
        List<Contact> lstCon = [SELECT Id, Phone, Account.Phone FROM Contact WHERE AccountId IN :accIdSet];
        
        // List to store Contacts to be updated.
        List<Contact> lstToUpdate = new List<Contact>();
        
        // Iterate through the queried Contacts.
        for (Contact con : lstCon) {
            // Update Contact Phone with the associated Account Phone.
            con.Phone = con.Account.Phone;
            lstToUpdate.add(con);
        }
        
        // Check if there are Contacts to be updated.
        if (!lstToUpdate.isEmpty()) {
            // Update the list of Contacts.
            update lstToUpdate;
        }
    }
}
