/** 
* @description       : Calculate Opportunity Discount price Trigger.                       
* @author            : Rajnish Kumar 
* @group             : Service
* @last modified on  : 20.12.2023
* @last modified by  : Rajnish Kumar 
* Modifications Log
* Ver   Date         Author     Modification
* 1.0   20.12.2023   Rajnish   Initial Version
**/
trigger CalculateOpportunityDiscountTrigger on Opportunity (before insert) {
	OpportunityDiscountTriggerDispatcher.dispatch(Trigger.OperationType);
}
