package app.versioncontrol.controller;


import app.userauth.config.TokenProvider;
import app.response.ResponseMessage;
import app.versioncontrol.request.RollBackRequest;
import app.versioncontrol.service.intf.IRollBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static app.userauth.role.RoleEnum.ADMIN;

@RestController
public class RollBack {
    @Autowired
    IRollBackService rollBackService;

    @Autowired
    TokenProvider jwtTokenUtils;
    private static final Logger logger = LoggerFactory.getLogger(RollBack.class);
    @PostMapping("/rollBack")
    public synchronized ResponseMessage rollBack(@RequestBody RollBackRequest rollBackRequest, HttpServletRequest request) throws Exception {
        String role =  jwtTokenUtils.getRole(request);

        if(role.equalsIgnoreCase(ADMIN.name())) {
            logger.info("rollBack Response created");
            return rollBackService.rollBack(rollBackRequest);
        }
        return ResponseMessage.getInstance("Not authorized");
    }

}
