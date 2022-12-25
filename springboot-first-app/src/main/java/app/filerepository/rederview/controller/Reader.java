package app.filerepository.rederview.controller;
import app.config.TokenProvider;
import app.filerepository.imports.service.factory.ImportFactory;
import app.filerepository.rederview.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import static app.userauth.role.RoleEnum.*;

@RestController
public class Reader {
    @Autowired
    private ReaderService service;

    @Autowired
    ImportFactory importFactory;
    @Autowired
    TokenProvider jwtTokenUtils;


    @GetMapping("/files")
    public List<String> getListFiles(HttpServletRequest request) throws Exception {
        String role = jwtTokenUtils.getRole(request);
        if(role.equalsIgnoreCase(READER.name())
                || role.equalsIgnoreCase(ADMIN.name())
                || role.equalsIgnoreCase(STAFF.name())) {
            return service.getAllFiles();
        }
throw new Exception("The role " + role + " does not exist");

    }


}