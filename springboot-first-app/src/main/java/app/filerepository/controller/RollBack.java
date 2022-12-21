package app.filerepository.controller;


import app.filerepository.model.RollBackRequest;
import app.filerepository.services.rollback.impl.RollBackService;
import app.filerepository.services.rollback.intf.IRollBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RollBack {

    @Autowired
    IRollBackService rollBackService;
    private static final Logger logger = LoggerFactory.getLogger(RollBack.class);
    @PostMapping("/rollBack")
    ResponseEntity<?> rollBack(@RequestBody RollBackRequest rollBackRequest) throws Exception {
        logger.info("rollBack Response created");
        return rollBackService.rollBack(rollBackRequest);
    }

}
