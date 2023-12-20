/** 
* @description       : Apex Trigger Handler to Calculate Opportunity Discount Price.                       
* @author            : Rajnish Kumar 
* @group             : Service
* @last modified on  : 20.12.2023
* @last modified by  : Rajnish Kumar 
* Modifications Log
* Ver   Date         Author     Modification
* 1.0   20.12.2023   Rajnish   Initial Version
**/

public class OpportunityDiscountTriggerHandler {
    
    /**
    * @description: Trigger handler method to calculate discount and update fields.
    * @param lstOpp: List of Opportunities to be processed.
    */
    public static void handleBeforeInsert(List<Opportunity> lstOpp){
        for(Opportunity newLstOpp : lstOpp){
            // Check if Amount and Discount fields are not blank
            if(newLstOpp.Amount != null && newLstOpp.Discount__c != null){
                
                // Calculate discount based on the given formula
                Decimal discount = (newLstOpp.Discount__c * newLstOpp.Amount) / 100;
                
                // Calculate discounted amount
                Decimal discountedAmount = newLstOpp.Amount - discount;
                
                // Update Discounted Price and Price After Discount fields
                newLstOpp.Discounted_Price__c = discount;
                newLstOpp.Price_After_Discount__c = discountedAmount;
            }
        }
    }
}
