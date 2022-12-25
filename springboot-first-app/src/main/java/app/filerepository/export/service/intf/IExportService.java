package app.filerepository.export.service.intf;

import app.response.ResponseFile;

import javax.servlet.http.HttpServletRequest;

public interface IExportService {
    ResponseFile getFile(String val, String name , HttpServletRequest request) throws Exception;
}
