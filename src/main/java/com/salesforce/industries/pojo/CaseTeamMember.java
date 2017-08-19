package com.salesforce.industries.pojo;

import com.salesforce.industries.core.Consts;

public class CaseTeamMember extends PojoBase {

    public CaseTeamMember() {

    }

    public CaseTeamMember(String memberId, String caseId, String teamRoleId, String teamRoleName) {
        super();
        this.memberId = memberId;
        this.caseId = caseId;
        this.teamRoleId = teamRoleId;
        this.teamRoleName = teamRoleName;
    }

    String memberId;
    String caseId;
    String teamRoleId;
    String teamRoleName;

    public String getMemberId() {
        return memberId;
    }
    public String getCaseId() {
        return caseId;
    }
    public String getTeamRoleId() {
        return teamRoleId;
    }
    public String getTeamRoleName() {
        return teamRoleName;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    public void setTeamRoleId(String teamRoleId) {
        this.teamRoleId = teamRoleId;
    }
    public void setTeamRoleName(String teamRoleName) {
        this.teamRoleName = teamRoleName;
    }

    @Override
    public String toCsvString() {
        return memberId + "," + caseId +  "," + teamRoleId;
    }

    @Override
    public String getCSVHeader(String nameSpace) {
        return "MEMBERID,PARENTID,TEAMROLEID";
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_CASETEAM;
    }

}
