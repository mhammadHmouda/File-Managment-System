package app.filerepository.services.deleteservice.intf;

import app.filerepository.response.ResponseMessage;

public interface IDeleteService {
    ResponseMessage delete(String id);
    ResponseMessage deleteAllFiles();
}
