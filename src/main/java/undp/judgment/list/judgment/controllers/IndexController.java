package undp.judgment.list.judgment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import undp.judgment.list.judgment.models.template.JudgementEntity;
import undp.judgment.list.judgment.services.JudgementService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@SessionAttributes({"judgements"})
public class IndexController {

    private final JudgementService judgementService;

    @Autowired
    public IndexController(JudgementService judgementService) {
        this.judgementService = judgementService;
    }


    @ModelAttribute("judgements")
    public List<JudgementEntity> getAttributes(){
        return judgementService.findAll();
    }

    @GetMapping("/")
    public String actionIndexGet(){
        return "index";
    }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "redirect:/";
    }

    @RequestMapping("/pdf/{fileName}")
    public void downloadPDFResource( HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName)
    {
        String dataDirectory = "uploads/";
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file))
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
