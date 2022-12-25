package app.filerepository.services.importservice.factory;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.importservice.impl.ImportService;
import app.filerepository.services.importservice.impl.OverwriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import static app.filerepository.services.importservice.ImportEnum.IMPORT_WITHOUT_OVERWRITE;
import static app.filerepository.services.importservice.ImportEnum.IMPORT_WITH_OVERWRITE;

@Component
public class ImportFactory {
    @Autowired
    ImportService importFile;

    @Autowired
    OverwriteService overwriteFile;

    public ResponseMessage factory(MultipartFile file, String type) throws Exception {

        if (type.equalsIgnoreCase(IMPORT_WITHOUT_OVERWRITE.name())){
            return importFile.save(file);
        }
        else if(type.equalsIgnoreCase(IMPORT_WITH_OVERWRITE.name())) {
            return overwriteFile.save(file);
        }
        throw new Exception("Use the valid type of import file ! ");
    }
}
