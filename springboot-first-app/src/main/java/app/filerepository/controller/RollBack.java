package app.filerepository.controller;


import app.filerepository.model.DBFile;
import app.filerepository.model.RollBackRequest;
import app.filerepository.service.RollBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RollBack {

    @Autowired
    RollBackService rollBack = new RollBackService();

    @PostMapping("/rollBack")
    ResponseEntity<?> rollBack(@RequestBody RollBackRequest rollBackRequest) throws Exception {
        return rollBack.rollBack(rollBackRequest);
    }

}
