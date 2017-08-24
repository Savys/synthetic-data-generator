package org.stg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.stg.core.Consts;
import org.stg.pojo.IndustriesRecordType;

public class BaseIndustryService {

    @Autowired
    protected IndustriesRecordTypeService irtService;
    @Autowired
    protected OrgService orgService;


    public String getIndividualRecordTypeId(String entity) throws Exception {
        String recordType = null;
        List<IndustriesRecordType> irt = irtService.getIndustriesRecordTypes(Consts.RECORDTYPE_CATEGORY_INDIVIDUAL);
        for(int i=0;i<irt.size();i++) {
            IndustriesRecordType recType = irt.get(i);
            if(recType.getsObjectType().equalsIgnoreCase(entity)) {
                recordType = recType.getRecordTypeId();
                break;
            }
        }

        return recordType;
    }
}
