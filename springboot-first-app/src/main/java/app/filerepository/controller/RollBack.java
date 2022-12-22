package app.filerepository.controller;


import app.filerepository.request.RollBackRequest;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.rollback.intf.IRollBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RollBack {
    @Autowired
    IRollBackService rollBackService;
    private static final Logger logger = LoggerFactory.getLogger(RollBack.class);
    @PostMapping("/rollBack")
    ResponseMessage rollBack(@RequestBody RollBackRequest rollBackRequest) throws Exception {
        logger.info("rollBack Response created");
        return rollBackService.rollBack(rollBackRequest);
    }

}
