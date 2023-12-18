/** 
* @description       : create a Task Record under that Account and assign the Task to the Account Owner.						
* @author            : Rajnish Kumar 
* @group             : Service
* @last modified on  : 18.12.2023
* @last modified by  : Rajnish Kumar 
* Modifications Log
* Ver   Date         Author     Modification
* 1.0   18.12.2023   Rajnish   Initial Version
	When the Account is Created, create a Task Record under that Account and assign the
	Task to the Account Owner. Use the below information
	Subject – Created from Apex Trigger
    Comments – Created from Apex Trigger
    Due Date – Todays Date + 7
    Status – Not Started
    Priority – High
    Related To (What) – Account Id
    Assigned To (OwnerId) – Account Owner Id
**/
// Trigger to create a related Task for each newly inserted Account and assign it to the Account Owner
trigger RelatedTaskWithAccOwner on Account (after insert) {
    // Check if the trigger context is after insert
    if (Trigger.isInsert && Trigger.isAfter) {
        // Create a list to store Task records
        List<Task> taskList = new List<Task>();

        // Loop through the newly inserted Account records
        for (Account acc : Trigger.new) {
            // Create a new Task
            Task newTask = new Task();
            
            // Set Task fields
            newTask.Subject = 'Created from Apex Trigger';
            newTask.Status = 'Not Started';
            newTask.Priority = 'High';
            newTask.WhatId = acc.Id;  // Associate the task with the Account
            newTask.OwnerId = acc.OwnerId;  // Assign the task to the Account Owner
            newTask.Description = 'Created from Apex Trigger';

            // Add the Task to the list
            taskList.add(newTask);
        }

        // Check if the task list is not empty before inserting
        if (!taskList.isEmpty()) {
            // Insert the list of Task records
            insert taskList;
        }
    }
}
