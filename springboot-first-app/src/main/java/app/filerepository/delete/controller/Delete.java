package app.filerepository.delete.controller;

import app.exception.DeleteException;
import app.userauth.config.TokenProvider;
import app.response.ResponseMessage;
import app.filerepository.delete.service.intf.IDeleteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import static app.filerepository.delete.constant.DeleteEndPoint.*;
import static app.filerepository.delete.constant.ResponseConstant.*;
import static app.userauth.role.RoleEnum.ADMIN;

@RestController
public class Delete {
    @Autowired
    private IDeleteService deleteService;
    @Autowired
    private TokenProvider jwtTokenUtils;
    private static final Logger logger = LoggerFactory.getLogger(Delete.class);

    @DeleteMapping(DELETE_BY_ID_END_POINT)
    public ResponseMessage deleteFile(@PathVariable String fileId, HttpServletRequest request) throws DeleteException {
        String role = jwtTokenUtils.getRole(request);
        if(role.equalsIgnoreCase(ADMIN.name())){
            logger.info("create delete response for "+fileId);
            return deleteService.delete(fileId);
        }
        return ResponseMessage.getInstance(NO_PERMISSION_TO_DELETE_FILE);
    }

    @DeleteMapping(DELETE_ALL_FILE_END_POINT)
    public ResponseMessage deleteAllFiles(HttpServletRequest request) throws DeleteException {
        String role = jwtTokenUtils.getRole(request);
        logger.info("deleteAllFiles Response created ");
        if(role.equalsIgnoreCase(ADMIN.name())){
            return deleteService.deleteAllFiles();
        }
        return ResponseMessage.getInstance(NO_PERMISSION_TO_DELETE_FILE);
    }
}
