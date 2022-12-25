package app.versioncontrol.service.intf;

import app.versioncontrol.request.RollBackRequest;
import app.response.ResponseMessage;

public interface IRollBackService{

    ResponseMessage rollBack(RollBackRequest rollBackRequest) throws Exception ;
}
