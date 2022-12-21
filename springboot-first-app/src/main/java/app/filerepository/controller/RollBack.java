package app.filerepository.controller;


import app.filerepository.model.DBFile;
import app.filerepository.model.RollBackRequest;
import app.filerepository.service.ExportService;
import app.filerepository.service.RollBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RollBack {

    @Autowired
    RollBackService rollBack = new RollBackService();
    private static final Logger logger = LoggerFactory.getLogger(RollBack.class);
    @PostMapping("/rollBack")
    ResponseEntity<?> rollBack(@RequestBody RollBackRequest rollBackRequest) throws Exception {
        logger.info("rollBack Response created");
        return rollBack.rollBack(rollBackRequest);
    }

}
