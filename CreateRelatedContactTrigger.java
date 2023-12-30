trigger CreateRelatedContact on Account (after insert) {
    if(trigger.isInsert){
        CreateRelatedContactHandler.handleCreateRelatedCon(trigger.new);
    }
	
}
