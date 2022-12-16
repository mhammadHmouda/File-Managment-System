package filerepository.controller;

import filerepository.response.ResponseMessage;
import filerepository.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Delete {
    @Autowired
    private DeleteService deleteService;


    @DeleteMapping("/delete/{fileId}")
    public ResponseMessage deleteFile(@PathVariable String fileId){
        return deleteService.delete(fileId);
    }

    @DeleteMapping("/deleteAll")
    public ResponseMessage deleteAllFiles(){
        return deleteService.deleteAllFiles();
    }
}
