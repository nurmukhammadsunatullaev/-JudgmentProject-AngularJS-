package undp.judgment.list.judgment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import undp.judgment.list.judgment.models.jpa.FileEntity;
import undp.judgment.list.judgment.models.jpa.UserEntity;
import undp.judgment.list.judgment.models.template.JudgementEntity;
import undp.judgment.list.judgment.services.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/upload")
@SessionAttributes({"judge_judgements"})
public class PersonalController {

    private final JudgementService judgementService;

    @Autowired
    public PersonalController(JudgementService judgementService) {
        this.judgementService = judgementService;
    }

    @ModelAttribute("judge_judgements")
    public List<JudgementEntity> getJudgements() {
        UserEntity  userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return judgementService.findByUserId(userEntity.getUserId());
    }

    @RequestMapping("/home")
    public String actionUploads(Model model){
        return "judge/index";
    }


    @GetMapping("/{caseId}/{definitionId}")
    public String actionUpload(Model model, @PathVariable("caseId") Long caseId, @PathVariable("definitionId") Long definitionId){
        UserEntity  userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JudgementEntity judgementEntity=judgementService.findByCaseId(caseId,userEntity.getUserId());
        if(judgementEntity==null){
            return "redirect:/upload/home";
        }
        model.addAttribute("judgement",judgementEntity);
        model.addAttribute("fileEntity",new FileEntity(caseId,definitionId));
        return "judge/upload";
    }


    @PostMapping("/file")
    public String actionFileUpload(@ModelAttribute(name="fileEntity") FileEntity fileEntity,@SessionAttribute("judge_judgements") List<JudgementEntity> judgements, Errors errors){

        if(errors.hasErrors()){
            return "judge/upload";
        }

        try {
            String filePath=new StringBuilder("uploads/")
                    .append(fileEntity.getCaseId())
                    .append("_")
                    .append(fileEntity.getDefinitionId())
                    .append(".pdf")
                    .toString();
            if(!Files.exists(Paths.get("uploads"))){
                Files.createDirectory(Paths.get("uploads"));
            }

            if(Files.exists(Paths.get(filePath))){
                Files.delete(Paths.get(filePath));
            }
            Files.write(Paths.get(filePath),fileEntity.getFile().getBytes());
            int index=judgements.indexOf(new JudgementEntity(fileEntity.getCaseId(),fileEntity.getDefinitionId()));
            if(index!=-1){
                judgements.get(index).setRowColor("success");
                judgements.get(index).setJudgementlinkcode(filePath.replace("uploads","/pdf"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/upload/home";
    }

}
