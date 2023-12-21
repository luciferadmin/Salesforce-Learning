/**
 * @description : Apex Trigger Handler to Prevent Duplicate Account Record Creation.
 * @author      : Rajnish Kumar
 * @group       : Service
 * @last modified on: 21.12.2023
 * @last modified by: Rajnish Kumar
 *
 * The business got to know that there are multiple accounts with the same name and rating.
 *  Now, as a developer, you need to make sure that no new duplicates are being created with the same name and rating.
 */
public class DuplicateAccountRecordTriggerHelper {

    /**
     * Prevents the creation of duplicate Account records based on name and rating.
     *
     * @param newAccList The list of new Account records being inserted or updated.
     */
    public static void duplicateAccountRecordPrevent(List<Account> newAccList) {
        // Collect unique names and ratings from the new Account records.
        Set<String> accNames = new Set<String>();
        Set<String> accRatings = new Set<String>();
        for (Account acc : newAccList) {
            accNames.add(acc.Name);
            accRatings.add(acc.Rating);
        }

        // Query for existing Account records with the same names and ratings.
        List<Account> existingAccounts = [
            SELECT Id, Name, Rating
            FROM Account
            WHERE Name IN :accNames 
            AND Rating IN :accRatings
            AND Id NOT IN :newAccList 
        ];

        // Compare the new accounts with existing accounts to identify duplicates.
        for (Account newAcc : newAccList) {
            for (Account existingAcc : existingAccounts) {
                if (existingAcc.Rating == newAcc.Rating && existingAcc.Name == newAcc.Name) {
                    // Add errors to the new Account record to prevent its creation.
                    newAcc.Name.addError('Name Cannot be duplicate');
                    newAcc.Rating.addError('Rating Cannot be duplicate ');
                }
            }
        }
    }
}
