trigger UpdateConPhone on Account (after insert,after update) {
    if(trigger.isAfter && (trigger.isUpdate || trigger.isInsert)){
        UpdateConPhoneHandler.updateConPhone(trigger.new);
    }
        
    }
