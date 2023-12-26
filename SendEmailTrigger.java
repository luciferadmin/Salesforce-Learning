trigger SendEmailTrigger on Account (after insert) {
	SendEmailTriggerHandler.handleAfterInsert(trigger.new);
}
