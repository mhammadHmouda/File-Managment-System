package app.filerepository.services.deleteservice.impl;

import app.filerepository.model.DBFile;
import app.filerepository.repository.DBFileRepository;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.deleteservice.intf.IDeleteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteService implements IDeleteService {

    @Autowired
    private DBFileRepository dbFileRepository;
    private static final Logger logger = LoggerFactory.getLogger(DeleteService.class);

    public ResponseMessage delete(String id){
        Optional<DBFile> file = dbFileRepository.findById(id);
        if(file.isPresent()){
            dbFileRepository.deleteById(id);
            logger.info("The file with Id : "+ id +" is deleted");
            return new ResponseMessage("File deleted successfully!");
        } else {
            logger.warn("The file with Id : "+ id + " is not exist!!");
            return new ResponseMessage("File not exist!");
        }
    }

    public ResponseMessage deleteAllFiles(){
        try {
            if(dbFileRepository.findAll().toArray().length != 0){
                dbFileRepository.deleteAll();
                logger.info("Files deleted successfully!");
                return new ResponseMessage("Files deleted successfully!");
            }
            logger.warn("No file to deleted!");
            return new ResponseMessage("No file to deleted!");

        }catch (Exception e){
            logger.error("Cannot delete these files!!!");
            return new ResponseMessage("Cannot delete these files!!!");
        }
    }
}
