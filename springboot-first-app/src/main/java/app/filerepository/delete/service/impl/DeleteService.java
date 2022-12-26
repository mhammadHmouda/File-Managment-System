package app.filerepository.delete.service.impl;

import app.exception.DeleteException;
import app.filerepository.delete.service.intf.IDeleteService;
import app.model.DBFile;
import app.repository.db.DBFileRepository;
import app.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static app.filerepository.delete.constant.ResponseConstant.*;

@Service
public class DeleteService implements IDeleteService {
    @Autowired
    private DBFileRepository dbFileRepository;
    private static final Logger logger = LoggerFactory.getLogger(DeleteService.class);

    public ResponseMessage delete(String id) throws DeleteException {
        try {

            DBFile file = dbFileRepository.findById(id).get();
            logger.warn("The file with Id : " + id + " is not exist!!");
            return ResponseMessage.getInstance(SUCCESS);
        }catch (Exception e){
            throw new DeleteException(SOMETHING_ERROR);
        }
    }

    public ResponseMessage deleteAllFiles() throws DeleteException {
        try {
            if(dbFileRepository.findAll().size() != 0){
                dbFileRepository.deleteAll();
                logger.info("Files deleted successfully!");
                return ResponseMessage.getInstance(SUCCESS);
            }
            logger.warn("No file to deleted!");
            return ResponseMessage.getInstance(NO_ANY_FILE_EXIST);

        }catch (Exception e){
            logger.error("Cannot delete these files!!!");
            throw new DeleteException(SOMETHING_ERROR);
        }
    }
}
