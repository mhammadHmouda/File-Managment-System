package app.filerepository.services.rollback.intf;

import app.filerepository.request.RollBackRequest;
import app.filerepository.response.ResponseMessage;

public interface IRollBackService{

    ResponseMessage rollBack(RollBackRequest rollBackRequest) throws Exception ;
}
