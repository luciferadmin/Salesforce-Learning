// Trigger that updates the Contact_Count__c field on related Account records
// based on changes to Contact records (insert, update, delete, undelete).

trigger CountContactTrigger on Contact (after insert, after update, after delete, after undelete) {

    // Set to store unique Account Ids affected by Contact changes
    Set<Id> accIds = new Set<Id>();

    // Handling insert and undelete events
    if ((Trigger.isInsert) || (Trigger.isUndelete)) {
        for (Contact con : Trigger.new) {
            accIds.add(con.AccountId);
        }
    }

    // Handling update events
    if (Trigger.isUpdate) {
        for (Contact con : Trigger.new) {
            // Check if AccountId has been changed in the update
            if (con.AccountId != Trigger.oldMap.get(con.Id).AccountId) {
                // Add new and old AccountIds to the set
                if (con.AccountId != null) {
                    accIds.add(con.AccountId);
                }
                if (Trigger.oldMap.get(con.Id).AccountId != null) {
                    accIds.add(Trigger.oldMap.get(con.Id).AccountId);
                }
            }
        }
    }

    // Handling delete events
    if (Trigger.isDelete) {
        for (Contact con : Trigger.old) {
            // Add AccountIds from deleted contacts to the set
            if (con.AccountId != null) {
                accIds.add(con.AccountId);
            }
        }
    }

    // If there are affected Account records, query and update them
    if (!accIds.isEmpty()) {
        // Query for related Account records and their Contacts
        List<Account> accountsToUpdate = [SELECT Id, Contact_Count__c, (SELECT Id FROM Contacts) FROM Account WHERE Id IN :accIds];

        // List to store updated Account records
        List<Account> accountsToUpdateList = new List<Account>();

        // Update Contact_Count__c on each Account based on the number of Contacts
        for (Account acc : accountsToUpdate) {
            acc.Contact_Count__c = acc.Contacts.size();
            accountsToUpdateList.add(acc);
        }

        // Perform the update on Account records
        update accountsToUpdateList;
    }
}
