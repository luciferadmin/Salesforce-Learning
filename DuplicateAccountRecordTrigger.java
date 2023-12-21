trigger DuplicateAccountRecordTrigger on Account (before insert,before update) {
	DuplicateAccountRecordTriggerDispatcher.duplicateAccountRecordTriggerDispatch(Trigger.OperationType);
}
