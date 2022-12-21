package app.filerepository.controller;

import app.filerepository.response.ResponseMessage;
import app.filerepository.service.DeleteService;
import app.filerepository.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Delete {
    @Autowired
    private DeleteService deleteService;
    private static final Logger logger = LoggerFactory.getLogger(Delete.class);

    @DeleteMapping("/delete/{fileId}")
    public ResponseMessage deleteFile(@PathVariable String fileId){
        logger.info("create delete response for "+fileId);
        return deleteService.delete(fileId);
    }

    @DeleteMapping("/deleteAll")
    public ResponseMessage deleteAllFiles(){
        logger.info("deleteAllFiles Response created ");
        return deleteService.deleteAllFiles();
    }
}
