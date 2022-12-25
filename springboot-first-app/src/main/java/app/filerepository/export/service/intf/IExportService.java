package app.filerepository.export.service.intf;

import app.response.ResponseFile;

public interface IExportService {
    ResponseFile getFile(String val, String name) throws Exception;
}
