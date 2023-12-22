/** 
 * @description       : Helper Class for Handling Restrictions on Amount and Discount Changes at Closed Won Stage.
 * @author            : Rajnish
 * @group             : Service
 * @last modified on  : 23/12/23
 * @last modified by  : Rajnish
 * Modifications Log
 * Ver   Date         Author                          Modification
 * 1.0   23/12/23       Rajnish                     Initial Version
 **/

public class CannotChangeAmountAtClosedWonHelper {
    /**
     * @description: Handles restrictions on Amount and Discount changes when the StageName is set to "Closed Won."
     * @param newOppRecords: List of new Opportunity records to be processed.
     * @param oldOppRecords: Map of old Opportunity records (Id to Opportunity) for comparison.
     */
    public static void handleBeforeUpdate(List<Opportunity> newOppRecords, Map<Id, Opportunity> oldOppRecords) {
        for (Opportunity newRecord : newOppRecords) {
            Opportunity oldRecord = oldOppRecords.get(newRecord.Id);
            // Check if both old and new stages are "Closed Won" and Amount is changing
            if (oldRecord.StageName == 'Closed Won' && newRecord.StageName == 'Closed Won' && 
                oldRecord.Amount != newRecord.Amount) {
                newRecord.Amount.addError('Amount cannot be changed once the Stage is set to Closed Won.');
            }
            // Check if both old and new stages are "Closed Won" and Discount is changing
            if (oldRecord.StageName == 'Closed Won' && newRecord.StageName == 'Closed Won' &&
                oldRecord.Discount__c != newRecord.Discount__c) {
                newRecord.Discount__c.addError('Discount cannot be changed once the Stage is set to Closed Won.');
            }
        }
    }
}
