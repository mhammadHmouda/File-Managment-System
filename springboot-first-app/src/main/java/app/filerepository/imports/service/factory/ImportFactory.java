package app.filerepository.imports.service.factory;

import app.filerepository.imports.service.enums.ImportEnum;
import app.response.ResponseMessage;
import app.filerepository.imports.service.impl.ImportService;
import app.filerepository.imports.service.impl.OverwriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImportFactory {
    @Autowired
    ImportService importFile;

    @Autowired
    OverwriteService overwriteFile;

    public ResponseMessage factory(MultipartFile file, String type) throws Exception {

        if (type.equalsIgnoreCase(ImportEnum.IMPORT_WITHOUT_OVERWRITE.name())){
            return importFile.save(file);
        }
        else if(type.equalsIgnoreCase(ImportEnum.IMPORT_WITH_OVERWRITE.name())) {
            return overwriteFile.save(file);
        }
        throw new Exception("Use the valid type of import file ! ");

    }
}
