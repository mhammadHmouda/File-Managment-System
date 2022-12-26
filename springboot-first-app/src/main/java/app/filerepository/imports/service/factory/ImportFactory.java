package app.filerepository.imports.service.factory;

import app.exception.ImportException;
import app.filerepository.imports.service.impl.ImportService;
import app.filerepository.imports.service.impl.OverwriteService;
import app.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static app.filerepository.imports.constant.ImportConstant.INVALID_TYPE;
import static app.filerepository.imports.constant.ImportType.IMPORT_WITHOUT_OVERWRITE;
import static app.filerepository.imports.constant.ImportType.IMPORT_WITH_OVERWRITE;

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

        throw new ImportException(INVALID_TYPE);

    }
}
