package undp.judgment.list.judgment.models.jpa;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class FileEntity implements Serializable {

    @NotNull
    private Long caseId;
    @NotNull
    private Long definitionId;

    private MultipartFile file;

    public FileEntity(@NotNull Long caseId, @NotNull Long definitionId) {
        this.caseId = caseId;
        this.definitionId = definitionId;
    }

    public FileEntity() {

    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(Long definitionId) {
        this.definitionId = definitionId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


}
