// Handler Class
public class ContactToAccountUpdateTriggerHandler {
    public static void handleAfterUpdate(List<Contact> newContacts, Map<Id, Contact> oldContactsMap) {
        List<Account> accountsToUpdate = new List<Account>();

        for (Contact updatedContact : newContacts) {
            Contact oldContact = oldContactsMap.get(updatedContact.Id);

            // Check if the description has changed
            if (updatedContact.Description != oldContact.Description) {
                // Fetch the associated Account
                Account associatedAccount = [SELECT Id, Description FROM Account WHERE Id = :updatedContact.AccountId LIMIT 1];

                // Update the Account's description
                associatedAccount.Description = updatedContact.Description;
                accountsToUpdate.add(associatedAccount);
            }
        }

        // Update the associated Accounts
        if (!accountsToUpdate.isEmpty()) {
            update accountsToUpdate;
        }
    }
}
