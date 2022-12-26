package app.filerepository.delete.service.intf;

import app.exception.DeleteException;
import app.response.ResponseMessage;

public interface IDeleteService {
    ResponseMessage delete(String id) throws DeleteException;
    ResponseMessage deleteAllFiles() throws DeleteException;
}
