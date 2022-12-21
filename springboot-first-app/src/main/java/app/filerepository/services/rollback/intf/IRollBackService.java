package app.filerepository.services.rollback.intf;

import app.filerepository.model.RollBackRequest;
import org.springframework.http.ResponseEntity;

public interface IRollBackService{

    ResponseEntity<?> rollBack(RollBackRequest rollBackRequest) throws Exception ;
}
