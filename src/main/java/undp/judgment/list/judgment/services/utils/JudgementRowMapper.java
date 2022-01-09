package undp.judgment.list.judgment.services.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import undp.judgment.list.judgment.models.template.JudgementEntity;
import undp.judgment.list.judgment.services.LocaleMessageService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

@Component
public class JudgementRowMapper implements RowMapper<JudgementEntity> {

    @Autowired
    private   LocaleMessageService messageService;

    @Override
    public JudgementEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            JudgementEntity judgementEntity=new JudgementEntity();
            judgementEntity.setCaseid(resultSet.getLong("caseId"));
            judgementEntity.setCaseresult(messageService.getMessage("application.judgment.result_"+resultSet.getString("caseResult")));
            judgementEntity.setCasetypename(resultSet.getString("caseTypeName"));
            judgementEntity.setCaseglobalnumber(resultSet.getString("caseGlobalNumber"));
            judgementEntity.setDemandantname(resultSet.getString("demandantName"));
            judgementEntity.setDefendantname(resultSet.getString("defendantName"));
            judgementEntity.setCourtname(resultSet.getString("courtName"));
            judgementEntity.setJudgename(resultSet.getString("judgeName"));
            judgementEntity.setJudgementid(resultSet.getLong("judgementId"));
            judgementEntity.setJudgementprivacy(resultSet.getShort("judgementPrivacy"));
            judgementEntity.setJudgementsigneddate(resultSet.getString("judgementSignedDate"));
            judgementFilePath(judgementEntity);
            return judgementEntity;
    }


    private void judgementFilePath(JudgementEntity judgementEntity){

            String url=judgementEntity.getJudgementid().toString();
            String uploadedFilePath=getUploadedFilePath(judgementEntity);
            if(uploadedFilePath!=null){
                    judgementEntity.setJudgementlinkcode(uploadedFilePath);
                    return;
            }

            if(judgementEntity.getJudgementprivacy()==0){
                    if(url!=null) {
                            url = new String(Base64.getEncoder().encodeToString(url.replaceAll("1", "a").replaceAll("9", "f").getBytes()));
                            judgementEntity.setRowColor("active");
                            judgementEntity.setJudgementlinkcode("http://v3.esud.uz/crm/download?t="+url);
                    }
                    return;
            }
            judgementEntity.setRowColor("danger");
            judgementEntity.setJudgementlinkcode("#");


    }

    private String getUploadedFilePath(JudgementEntity judgementEntity){
            String filePath=new StringBuilder()
                    .append(judgementEntity.getCaseid())
                    .append("_")
                    .append(judgementEntity.getJudgementid())
                    .append(".pdf")
                    .toString();

            if(judgementEntity.getJudgementprivacy()==4){
                    judgementEntity.setDefendantname("-");
                    judgementEntity.setDemandantname("-");
            }

            if(!Files.exists(Paths.get("uploads/"+filePath))){
                    return null;
            }
            else{
                    judgementEntity.setRowColor("success");
                    return "/pdf/"+filePath;
            }

    }






}
