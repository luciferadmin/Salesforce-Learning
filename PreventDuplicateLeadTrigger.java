trigger PreventDuplicateLead on Lead (before insert, before update) {
    PreventDuplicateLeadHandler.preventDuplicateLeadHandler(trigger.new);
}
