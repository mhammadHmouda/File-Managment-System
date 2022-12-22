package app.filerepository.services.rollback.intf;

import app.filerepository.request.RollBackRequest;
import app.filerepository.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

public interface IRollBackService{

    ResponseMessage rollBack(RollBackRequest rollBackRequest) throws Exception ;
}
