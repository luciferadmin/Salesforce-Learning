/**
 * @description       : Apex Trigger Handler to create tasks and send emails for new accounts missing information.
 * @author            : Rajnish Kumar
 * @group             : Service
 * @last modified on  : 26.12.2023
 * @last modified by  : Rajnish Kumar 
 * Modifications Log
 * Ver   Date         Author        Modification
 * 1.0   26.12.2023   Rajnish Kumar Initial Version
 */

public class SendEmailTriggerHandler {
    /**
     * @description: Handles the logic after the insertion of new accounts.
     * @param accountList: List of new accounts affected by the trigger.
     */
    public static void handleAfterInsert(List<Account> accountList) {
        // Lists to store task records and email messages
        List<Task> taskRecordToInsertList = new List<Task>();
        List<Messaging.SingleEmailMessage> emailMessages = new List<Messaging.SingleEmailMessage>();
        
        // Query for additional details of new accounts
        List<Account> newAccountList = [SELECT Id, Name, Phone, Industry, OwnerId, Owner.Name, Owner.Email 
                                        FROM Account WHERE Id IN :accountList];
        
        for(Account acc : newAccountList) {
            // Check if Phone and Industry are null
            if(acc.Phone == null && acc.Industry == null) {
                // Requirement #1 - Create Task
                Task t = new Task();
                t.Subject = 'Created from Apex Trigger';
                t.Description = 'Created from Apex Trigger';
                t.Status = 'Not Started';
                t.Priority = 'High';
                t.ActivityDate = System.today().addDays(7);
                t.WhatId = acc.Id;
                t.OwnerId = acc.OwnerId;
                taskRecordToInsertList.add(t);
                
                // Requirement #2 - Send Email
                // Prepare Email - DRAFT Email
                Messaging.SingleEmailMessage email = new Messaging.SingleEmailMessage();
                email.setSubject('New Account ' + acc.Name + ' has been assigned!');
                
                // OwnerId (User/Queue)
                String emailBody = 'Dear ' + acc.Owner.Name + '<br/><br/>';
                emailBody += 'A new account <b>' + acc.Name + '</b> has been created in Salesforce. The account is missing some important information, Phone & Industry.<br/><br/>';
                emailBody += 'Please try to collect this information and update the account ASAP.<br/><br/>';
                emailBody += '<b>Thanks & Regards,<br/>';
                emailBody += 'Panther Schools</b>';
                
                email.setHtmlBody(emailBody);
                
                List<String> toAddress = new List<String>();
                // Lead/Contact/User - Email/Id
                toAddress.add(acc.Owner.Email); // OwnerEmail
                email.setToAddresses(toAddress);
                
                // Add Email to the List
                emailMessages.add(email);
            }
        }
        
        // Insert Task Records
        insert taskRecordToInsertList;
        
        // Send Email
        List<Messaging.SendEmailResult> sendEmailResults = Messaging.sendEmail(emailMessages, false);
        
        for(Messaging.SendEmailResult sr: sendEmailResults) {
            Boolean isSuccess = sr.isSuccess();
            if(isSuccess) {
                System.debug('Email Sent Successfully!!');
            } else {
                System.debug('Error while sending Email \n ');
                List<Messaging.SendEmailError> errors = sr.getErrors();
                System.debug(errors);
            }
        }
    }
}
