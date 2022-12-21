package app.filerepository.services.rollback.impl;

import app.filerepository.model.DBFile;
import app.filerepository.model.RollBackRequest;
import app.filerepository.repository.DBFileRepository;
import app.filerepository.services.rollback.intf.IRollBackService;
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
    public ResponseEntity<?> rollBack(RollBackRequest rollBackRequest) throws Exception {
        try {
            List<DBFile> filesWithName = dbFileRepository.findByName(rollBackRequest.getFileName());
            if(filesWithName.size() == 0){
                logger.warn("Invalid file name!");
                return new ResponseEntity<>("Invalid file name!", HttpStatus.NOT_FOUND);
            }
            int latestVersion = 0;
            for (DBFile dbFile : filesWithName) {
                if(dbFile.getVersion() > latestVersion)
                    latestVersion = dbFile.getVersion();
            }
            if(rollBackRequest.getVersion() == latestVersion){
                return new ResponseEntity<>("This is a latest version!", HttpStatus.BAD_REQUEST);
            }
            List<DBFile> versionWantDelete = filesWithName.stream()
                    .filter(file -> file.getVersion() > rollBackRequest.getVersion())
                    .collect(Collectors.toList());
            if(versionWantDelete.size() == 0){
                return new ResponseEntity<>("This version does not exist!",HttpStatus.NOT_FOUND );
            }
            dbFileRepository.deleteAll(versionWantDelete);
            logger.info("All version greater than "+rollBackRequest.getVersion()+" Version is deleted");

            return new ResponseEntity<>("The RollBack of version " + rollBackRequest.getVersion() + " done successfully", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Error in RollBack of version " + rollBackRequest.getVersion()+" for "+rollBackRequest.getFileName()+" file");
            throw new Exception(e.getMessage());
        }

    }

}