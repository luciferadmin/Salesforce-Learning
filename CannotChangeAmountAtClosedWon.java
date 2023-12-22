trigger CannotChangeAmountAtClosedWon on Opportunity (before update) {
    
    CannotChangeAmountAtClosedWonDispatcher.cannotChangeAmountAtClosedWonDispatch(Trigger.operationType);
}
