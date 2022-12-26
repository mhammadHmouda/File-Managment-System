package app.versioncontrol.service.impl;

import app.exception.RollBackException;
import app.model.DBFile;
import app.repository.db.DBFileRepository;
import app.response.ResponseMessage;
import app.versioncontrol.request.RollBackRequest;
import app.versioncontrol.service.intf.IRollBackService;
import app.versioncontrol.service.utils.RollBackUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static app.versioncontrol.constant.ConstantRollBack.*;

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
                return ResponseMessage.getInstance(INVALID_FILE_NAME);
            }

            int latestVersion = RollBackUtils.getLatestVersion(filesWithName);

            if(rollBackRequest.getVersion() == latestVersion)
                return ResponseMessage.getInstance(LATEST_VERSION);

            List<DBFile> versionWantDelete = RollBackUtils.getTheFileWantDelete(filesWithName, rollBackRequest.getVersion());

            if(versionWantDelete.size() == 0)
                return ResponseMessage.getInstance(NOT_EXIST_FILE);

            dbFileRepository.deleteAll(versionWantDelete);

            logger.info("RollBack done successfully of the version " + rollBackRequest.getVersion() );

            return ResponseMessage.getInstance(SUCCESS_ROLL_BACK);

        }catch (Exception e){
            logger.error("Error in RollBack of version " + rollBackRequest.getVersion()+" for "+rollBackRequest.getFileName()+" file");
            throw new RollBackException(e.getMessage());
        }

    }

}