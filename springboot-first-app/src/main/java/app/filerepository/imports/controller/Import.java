package app.filerepository.imports.controller;//package app.filerepository.imports.controller;

import app.userauth.config.TokenProvider;
import app.filerepository.imports.constant.ImportType;
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

import static app.filerepository.imports.constant.ImportConstant.*;
import static app.filerepository.imports.constant.ImportEndPoint.*;
import static app.userauth.role.RoleEnum.*;

@RestController
public class Import {

    @Autowired
    ImportFactory importFactory;
    @Autowired
    TokenProvider jwtTokenUtils;
    private static final Logger logger = LoggerFactory.getLogger(Import.class);

    @PostMapping(IMPORT_WITHOUT_OVERWRITE_END_POINT)
    public synchronized ResponseMessage uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String role =  jwtTokenUtils.getRole(request);

        if(role.equalsIgnoreCase(ADMIN.name()) || role.equalsIgnoreCase(STAFF.name())) {
            logger.info("UploadFile Response created");
            return importFactory.factory(file, ImportType.IMPORT_WITHOUT_OVERWRITE.name());
        }

        return ResponseMessage.getInstance(NO_PERMISSION_TO_IMPORT);
    }

    @PostMapping(IMPORT_WITH_OVERWRITE_END_POINT)
    public synchronized ResponseMessage overwriteFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String role =  jwtTokenUtils.getRole(request);

        if(role.equalsIgnoreCase(ADMIN.name())) {
            logger.info("Overwrite file controller");
            return importFactory.factory(file, ImportType.IMPORT_WITH_OVERWRITE.name());
        }

        return ResponseMessage.getInstance(NO_PERMISSION_TO_IMPORT);
    }

}
