package app.filerepository.delete.service.intf;

import app.response.ResponseMessage;

public interface IDeleteService {
    ResponseMessage delete(String id);
    ResponseMessage deleteAllFiles();
}
