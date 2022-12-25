package app.filerepository.imports.controller;//package app.filerepository.imports.controller;

import app.config.TokenProvider;
import app.filerepository.imports.service.enums.ImportEnum;
import app.filerepository.imports.service.factory.ImportFactory;

import app.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static app.userauth.role.RoleEnum.ADMIN;
import static app.userauth.role.RoleEnum.STAFF;

@RestController
public class Import {

    @Autowired
    ImportFactory importFactory;
    @Autowired
    TokenProvider jwtTokenUtils;
    private static final Logger logger = LoggerFactory.getLogger(Import.class);


    @PostMapping("/uploadFile")
    public ResponseMessage uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String role =  jwtTokenUtils.getRole(request);
        logger.info("UploadFile Response created");


        if(role.equalsIgnoreCase(ADMIN.name()) || role.equalsIgnoreCase(STAFF.name())) {
            return importFactory.factory(file, ImportEnum.IMPORT_WITHOUT_OVERWRITE.name());
        }
        else {
            return ResponseMessage.getInstance("Error !! ,"+"Role: "+role+ " has no permission to upload this file");
        }
    }

    @PostMapping("/overwriteFile")
    public ResponseMessage overwriteFile(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info("Overwrite file controller");
        return importFactory.factory(file, ImportEnum.IMPORT_WITH_OVERWRITE.name());
    }

}
