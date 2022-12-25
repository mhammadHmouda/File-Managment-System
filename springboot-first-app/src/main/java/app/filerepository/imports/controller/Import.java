package app.filerepository.imports.controller;

import app.response.ResponseMessage;
import app.filerepository.imports.service.enums.ImportEnum;
import app.filerepository.imports.service.factory.ImportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Import {

    @Autowired
    ImportFactory importFactory;
    private static final Logger logger = LoggerFactory.getLogger(Import.class);
    @PostMapping("/uploadFile")
    public ResponseMessage uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info("UploadFile Response created");
        return importFactory.factory(file, ImportEnum.IMPORT_WITHOUT_OVERWRITE.name());
    }

    @PostMapping("/overwriteFile")
    public ResponseMessage overwriteFile(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info("Overwrite file controller");
        return importFactory.factory(file, ImportEnum.IMPORT_WITH_OVERWRITE.name());
    }

}
