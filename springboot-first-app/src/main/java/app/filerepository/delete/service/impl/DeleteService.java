package app.filerepository.delete.service.impl;

import app.model.DBFile;
import app.repository.DBFileRepository;
import app.response.ResponseMessage;
import app.filerepository.delete.service.intf.IDeleteService;
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
            return ResponseMessage.getInstance("File deleted successfully!");
        } else {
            logger.warn("The file with Id : "+ id + " is not exist!!");
            return ResponseMessage.getInstance("File not exist!");
        }
    }

    public ResponseMessage deleteAllFiles(){
        try {
            if(dbFileRepository.findAll().size() != 0){
                dbFileRepository.deleteAll();
                logger.info("Files deleted successfully!");
                return ResponseMessage.getInstance("Files deleted successfully!");
            }
            logger.warn("No file to deleted!");
            return ResponseMessage.getInstance("No file to deleted!");

        }catch (Exception e){
            logger.error("Cannot delete these files!!!");
            return ResponseMessage.getInstance("Cannot delete these files!!!");
        }
    }
}
