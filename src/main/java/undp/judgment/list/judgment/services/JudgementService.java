package undp.judgment.list.judgment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undp.judgment.list.judgment.models.template.JudgementEntity;
import undp.judgment.list.judgment.services.utils.JudgementPartRowMapper;
import undp.judgment.list.judgment.services.utils.JudgementRowMapper;
import java.util.List;

@Service
public class JudgementService {

        private String judgement_sql="SELECT caset.id_ AS caseId, caset.stateid_ caseResult, caset.globalnumber_ AS caseGlobalNumber, (select name_ from fieldgroupT where id_ = caset.groupid_) AS caseTypeName, caset.givername_ AS demandantName, caset.answername_ AS defendantName,  (select name_ from courtT where id_ = caset.courtid_) AS courtName, (select name_ from userT where id_ = caset.refereeid_) AS judgeName, casedefinitiont.id_ AS judgementId, casedefinitiont.reason_ AS judgementPrivacy,  to_char(signeddefinitiont.signdate_, 'YYYY/MM/DD') AS judgementSignedDate FROM signeddefinitiont JOIN casedefinitiont ON signeddefinitiont.definitionid_ = casedefinitiont.id_ JOIN caset ON casedefinitiont.caseid_ = caset.id_ WHERE signeddefinitiont.signdate_ > '2018-12-01'::date AND casedefinitiont.definid_ = '-59'::integer AND casedefinitiont.reason_ in (0, 4) AND caset.stateid_ in (12,13,21) order by signeddefinitiont.signdate_ DESC;";
    private String judgement_user_sql="SELECT caset.id_ AS caseId, caset.stateid_ caseResult, caset.globalnumber_ AS caseGlobalNumber, (select name_ from fieldgroupT where id_ = caset.groupid_) AS caseTypeName, caset.givername_ AS demandantName, caset.answername_ AS defendantName,  (select name_ from courtT where id_ = caset.courtid_) AS courtName, (select name_ from userT where id_ = caset.refereeid_) AS judgeName, casedefinitiont.id_ AS judgementId, casedefinitiont.reason_ AS judgementPrivacy,  to_char(signeddefinitiont.signdate_, 'YYYY/MM/DD') AS judgementSignedDate FROM signeddefinitiont JOIN casedefinitiont ON signeddefinitiont.definitionid_ = casedefinitiont.id_ JOIN caset ON casedefinitiont.caseid_ = caset.id_ WHERE signeddefinitiont.signdate_ > '2018-12-01'::date AND casedefinitiont.definid_ = '-59'::integer AND casedefinitiont.reason_ in (0, 4) AND caset.stateid_ in (12,13,21) AND caset.refereeid_=:userId order by signeddefinitiont.signdate_ DESC;";
    private String judgement_case_sql="SELECT givername_ AS demandantname, answername_ AS defendantname, globalnumber_ AS caseglobalnumber, stateid_  AS caseresult  FROM public.caset WHERE caset.id_=:caseId AND caset.refereeid_=:userId;";

    private final JudgementRowMapper judgementRowMapper;
    private final JudgementPartRowMapper judgementPartRowMapper;
    private final NamedParameterJdbcTemplate template;

    @Autowired
    public JudgementService(JudgementRowMapper judgementRowMapper, JudgementPartRowMapper judgementPartRowMapper, NamedParameterJdbcTemplate template) {
        this.judgementRowMapper = judgementRowMapper;
        this.judgementPartRowMapper = judgementPartRowMapper;
        this.template = template;
    }


    @Transactional
    public List<JudgementEntity> findAll(){
        return query(judgement_sql,judgementRowMapper,null);
    }

    public List<JudgementEntity> findByUserId(Integer userId){
        return query(judgement_user_sql,judgementRowMapper,parametersMap(new String[]{"userId"},new Object[]{userId}));
    }

    public JudgementEntity findByCaseId(Long caseId, Integer userId){
        return template.queryForObject(judgement_case_sql,parametersMap(
                new String[]{"caseId","userId"},
                new Object[]{caseId, userId}
        ),judgementPartRowMapper);
    }

    private MapSqlParameterSource parametersMap(String [] parametersName, Object [] parametersValue){
        MapSqlParameterSource map=new MapSqlParameterSource();
        for (int i=0;i<parametersName.length;i++){
            map.addValue(parametersName[i],parametersValue[i]);
        }
        return map;

    }

    private List<JudgementEntity> query(String sql, RowMapper rowMapper,MapSqlParameterSource map){


        List<JudgementEntity> judgements=
                map==null ?
                template.query(sql,rowMapper) :
                template.query(sql,map,rowMapper);
        return judgements;
    }



}
