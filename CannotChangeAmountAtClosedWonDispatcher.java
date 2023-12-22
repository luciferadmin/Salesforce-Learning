/** 
 * @description       : Trigger Dispatcher Class for Handling Restrictions on Amount and Discount Changes at Closed Won Stage.
 * @author            : Rajnish
 * @group             : Service
 * @last modified on  : 23/12/23
 * @last modified by  : Rajnish
 * Modifications Log
 * Ver   Date         Author                                     Modification
 * 1.0   23/12/23        Rajnish                                Initial Version
 **/

public class CannotChangeAmountAtClosedWonDispatcher {

    /**
     * @description: Dispatches the logic to handle restrictions on Amount and Discount changes
     *               when the StageName is set to "Closed Won."
     * @param operationType: Trigger operation type (e.g., BEFORE_UPDATE).
     */
    public static void cannotChangeAmountAtClosedWonDispatch(System.TriggerOperation operationType) {
        // Switch on the specified operation type
        switch on (operationType) {
            // When the trigger operates before update
            WHEN BEFORE_UPDATE {
                // Call the handleBeforeUpdate method of CannotChangeAmountAtClosedWonHelper
                CannotChangeAmountAtClosedWonHelper.handleBeforeUpdate(Trigger.new, (Map<Id, Opportunity>)Trigger.oldMap);
            }
            // Add more cases for other trigger operations if needed
        }
    }
}
