package undp.judgment.list.judgment.controllers.rest.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import undp.judgment.list.judgment.models.template.*;
import java.util.List;


@RestController
public class DataController {

    @GetMapping(value = "/rest/judgements")
    public ResponseEntity<List<JudgementEntity>> getAttributes(@SessionAttribute("judgements") List<JudgementEntity> judgements){
        return new ResponseEntity<>(judgements,HttpStatus.OK);
    }

    @GetMapping(value = "/upload/rest/judgements")
    public ResponseEntity<List<JudgementEntity>> getJudgements(@SessionAttribute("judge_judgements") List<JudgementEntity> judgements){
        return new ResponseEntity<>(judgements,HttpStatus.OK);
    }

    @GetMapping(value = "/api/counts/{type}")
    public  ResponseEntity<CounterEntity> getCaseCount(@PathVariable(name = "type") Integer type){
        if(type.equals(1)){
            return new ResponseEntity<>(new CounterEntity(type,5431),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CounterEntity(type,0),HttpStatus.OK);
    }



}

