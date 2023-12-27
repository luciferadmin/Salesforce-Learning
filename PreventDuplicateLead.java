public class PreventDuplicateLeadHandler {
    public static void preventDuplicateLeadHandler(List<Lead> newLeadRecord){
        Set<String> leadCompany = new Set<String>();
        Set<String> leadEmail = new Set<String>();
        for(Lead newLead : newLeadRecord){
            leadCompany.add(newLead.Company);
            leadEmail.add(newLead.Email);
            
        }
        Integer countEmail =[select count() from Lead where Email In:leadEmail];
        Integer countCompany =[select count() from Lead where Company In:leadCompany];
        
        if(countEmail>0 && countCompany>0){
            for(Lead objLead: newLeadRecord){
                objLead.addError('You are trying to save duplcate email & Company');
            }
        }
    }

}
