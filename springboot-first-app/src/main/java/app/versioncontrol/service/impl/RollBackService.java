package app.versioncontrol.service.impl;

import app.model.DBFile;
import app.versioncontrol.request.RollBackRequest;
import app.repository.DBFileRepository;
import app.response.ResponseMessage;
import app.versioncontrol.service.intf.IRollBackService;
import app.versioncontrol.service.utils.RollBackUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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