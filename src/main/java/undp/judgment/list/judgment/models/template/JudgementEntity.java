package undp.judgment.list.judgment.models.template;


public class JudgementEntity {
    private Long caseid;
    private String caseresult;
    private String caseglobalnumber;
    private String casetypename;
    private String demandantname;
    private String defendantname;
    private String courtname;
    private String judgename;
    private Long judgementid;
    private Short judgementprivacy;
    private String judgementlinkcode;
    private String judgementsigneddate;
    private String rowColor;

    public JudgementEntity(Long caseid, Long judgementid) {
        this.caseid = caseid;
        this.judgementid = judgementid;
    }

    public JudgementEntity() {
    }

    public Long getCaseid() {
        return caseid;
    }

    public void setCaseid(Long caseid) {
        this.caseid = caseid;
    }

    public String getCaseresult() {
        return caseresult;
    }

    public void setCaseresult(String caseresult) {
        this.caseresult = caseresult;
    }

    public String getCaseglobalnumber() {
        return caseglobalnumber;
    }

    public void setCaseglobalnumber(String caseglobalnumber) {
        this.caseglobalnumber = caseglobalnumber;
    }

    public String getCasetypename() {
        return casetypename;
    }

    public void setCasetypename(String casetypename) {
        this.casetypename = casetypename;
    }

    public String getDemandantname() {
        return demandantname;
    }

    public void setDemandantname(String demandantname) {
        this.demandantname = demandantname;
    }

    public String getDefendantname() {
        return defendantname;
    }

    public void setDefendantname(String defendantname) {
        this.defendantname = defendantname;
    }

    public String getCourtname() {
        return courtname;
    }

    public void setCourtname(String courtname) {
        this.courtname = courtname;
    }


    public String getJudgename() {
        return judgename;
    }

    public void setJudgename(String judgename) {
        this.judgename = judgename;
    }

    public Long getJudgementid() {
        return judgementid;
    }

    public void setJudgementid(Long judgementid) {
        this.judgementid = judgementid;
    }

    public Short getJudgementprivacy() {
        return judgementprivacy;
    }

    public void setJudgementprivacy(Short judgementprivacy) {
        this.judgementprivacy = judgementprivacy;
    }

    public String getJudgementlinkcode() {
        return judgementlinkcode;
    }

    public void setJudgementlinkcode(String judgementlinkcode) {
        this.judgementlinkcode = judgementlinkcode;
    }

    public String getJudgementsigneddate() {
        return judgementsigneddate;
    }

    public void setJudgementsigneddate(String judgementsigneddate) {
        this.judgementsigneddate = judgementsigneddate;
    }

    public String getRowColor() {
        return rowColor;
    }

    public void setRowColor(String rowColor) {
        this.rowColor = rowColor;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof JudgementEntity){
            JudgementEntity entity= (JudgementEntity) obj;
            return this.caseid.equals(entity.caseid) && this.judgementid.equals(entity.judgementid);
        }
        return false;
    }
}
