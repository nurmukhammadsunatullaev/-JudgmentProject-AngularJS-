package undp.judgment.list.judgment.services.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import undp.judgment.list.judgment.models.template.JudgementEntity;
import undp.judgment.list.judgment.services.LocaleMessageService;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JudgementPartRowMapper implements RowMapper<JudgementEntity> {

    @Autowired
    private LocaleMessageService messageService;

    @Override
    public JudgementEntity mapRow(ResultSet resultSet, int i) throws SQLException {

        JudgementEntity judgementEntity=new JudgementEntity();
        judgementEntity.setCaseresult(messageService.getMessage("application.judgment.result_"+resultSet.getString("caseResult")));
        judgementEntity.setCaseglobalnumber(resultSet.getString("caseGlobalNumber"));
        judgementEntity.setDemandantname(resultSet.getString("demandantName"));
        judgementEntity.setDefendantname(resultSet.getString("defendantName"));
        return judgementEntity;

    }
}
