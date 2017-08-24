package org.stg.domain.account;

import java.util.List;

import org.stg.core.Consts;
import org.stg.core.OrgUtils;
import org.stg.core.Id;

public class FSCAccount extends IndustriesAccount{

    String sourceSystem;
    String active;
    String bankNumber;
    String borrowingHistory;
    String borrowingPriorities;
    String branchCode;
    String branchName;
    String clientCat;
    String conversionDateTime;
    String creditRating;
    Long creditScore;
    String customerID;
    String customerPriority;
    List<String> customerSegment;
    String customerType;
    Id referredByContact;
    List<String> financialInterests;
    String notes;
    String individualId;
    String individualType;
    Id referredByUser;
    String investmentExperience;
    List<String> investmentObjectives;
    String kYCDate;
    String kYCStatus;
    String lastInteraction;
    String lastReview;
    String lastUsedChannel;
    Float lifetimeValue;
    List<String> marketingSegment;
    Float netWorth;
    String nextInteraction;
    String nextReview;
    Integer numberofLocations;
    List<String> personalInterests;
    Id primaryContact;
    String relationshipStartDate;
    String reviewFrequency;
    String riskTolerance;
    String serviceModel;
    String sLA;
    String sLAExpirationDate;
    String sLASerialNumber;
    String sourceSystemId;
    String status;
    String timeHorizon;
    Float totalAUMJointOwner;
    Float totalAUMPrimaryOwner;
    Float totalBankDepositsJointOwner;
    Float totalBankDepositsPrimaryOwner;
    Float totalFinAcctsJointOwner;
    Float totalFinAcctsPrimaryOwner;
    Float totalHeldFinAcctsJointOwner;
    Float totalHeldFinAcctsPrimaryOwner;
    Float totalInsuranceJointOwner;
    Float totalInsurancePrimaryOwner;
    Float totalInvestmentsJointOwner;
    Float totalInvestmentsPrimaryOwner;
    Float totalLiabilitiesJointOwner;
    Float totalLiabilitiesPrimaryOwner;
    Float totalNonfinancialAssetsJointOwner;
    Float totalNonfinancialAssetsPrimaryOwner;
    Float totalOutstandingCreditJointOwner;
    Float totalOutstandingCreditPrimaryOwner;
    Float totalRevenue;
    String upsellOpportunity;

    @Override
    public String getCSVHeader(String nameSpace) {
        return "BillingStreet,BillingCity,BillingPostalCode,BillingState,BillingCountry,"+OrgUtils.getFQFieldName(nameSpace,"BorrowingHistory__c")+","+OrgUtils.getFQFieldName(nameSpace,"SourceSystemId__c")+","+OrgUtils.getFQFieldName(nameSpace,"SourceSystem__c")+",RecordTypeId,Name";
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_ACCOUNT;
    }

    public String toCsvString() {
        return
                + streetNumber + " " + street + "," + city + "," + postalCode + ","
                + state + "," + country + "," + borrowingHistory
                + "," + sourceSystemId + "," + sourceSystem + ","
                +  recordTypeId+"," + accountName;
    }



}
