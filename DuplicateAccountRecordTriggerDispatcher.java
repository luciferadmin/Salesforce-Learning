/** 
* @description       : Trigger Dispatcher Class for Duplicate Account Record Prevention.                       
* @author            : Rajnish
* @group             : Service
* @last modified on  : 21/12/23
* @last modified by  : Rajnish
* Modifications Log
* Ver   Date         Author              Modification
* 1.0   21/12/23     Rajnish           Initial Version
**/

public class DuplicateAccountRecordTriggerDispatcher {
    
    /**
    * @description: Dispatches the Duplicate Account Record Prevention logic based on the specified operation type.
    * @param operationType: Trigger operation type (e.g., BEFORE_INSERT, BEFORE_UPDATE).
    */
    public static void duplicateAccountRecordTriggerDispatch(System.TriggerOperation operationType) {
        // Switch on the specified operation type
        switch on operationType {
            // When the trigger operates before insert
            WHEN BEFORE_INSERT {
                // Call the duplicateAccountRecordPrevent method of DuplicateAccountRecordTriggerHelper
                DuplicateAccountRecordTriggerHelper.duplicateAccountRecordPrevent(Trigger.new);
            }
            // When the trigger operates before update
            WHEN BEFORE_UPDATE {
                // Call the duplicateAccountRecordPrevent method of DuplicateAccountRecordTriggerHelper
                DuplicateAccountRecordTriggerHelper.duplicateAccountRecordPrevent(Trigger.new);
            }
            // Add more cases for other trigger operations if needed
        }
    }
}
