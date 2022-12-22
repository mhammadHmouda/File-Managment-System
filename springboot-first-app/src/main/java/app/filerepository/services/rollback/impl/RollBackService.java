package app.filerepository.services.rollback.impl;

import app.filerepository.model.DBFile;
import app.filerepository.request.RollBackRequest;
import app.filerepository.repository.DBFileRepository;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.rollback.intf.IRollBackService;
import app.filerepository.services.rollback.utils.RollBackUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RollBackService implements IRollBackService {

    private static final Logger logger = LoggerFactory.getLogger(RollBackRequest.class);
    @Autowired
    private DBFileRepository dbFileRepository;

    public ResponseMessage rollBack(RollBackRequest rollBackRequest) throws Exception {
        try {
            List<DBFile> filesWithName = dbFileRepository.findByName(rollBackRequest.getFileName());
            if(filesWithName.size() == 0){
                logger.warn("Invalid file name: " + rollBackRequest.getFileName());
                return ResponseMessage.getInstance("Invalid file name!");
            }

            int latestVersion = RollBackUtils.getLatestVersion(filesWithName);

            if(rollBackRequest.getVersion() == latestVersion)
                return ResponseMessage.getInstance("This is a latest version!");

            List<DBFile> versionWantDelete = RollBackUtils.getTheFileWantDelete(filesWithName, rollBackRequest.getVersion());

            if(versionWantDelete.size() == 0)
                return ResponseMessage.getInstance("This version does not exist!");

            dbFileRepository.deleteAll(versionWantDelete);

            logger.info("RollBack done successfully of the version " + rollBackRequest.getVersion() );

            return ResponseMessage.getInstance("RollBack done successfully");
        }catch (Exception e){
            logger.error("Error in RollBack of version " + rollBackRequest.getVersion()+" for "+rollBackRequest.getFileName()+" file");
            throw new Exception(e.getMessage());
        }

    }

}