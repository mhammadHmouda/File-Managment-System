package app.filerepository.export.controller;

import app.filerepository.export.service.intf.IExportService;
import app.response.ResponseFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static app.filerepository.export.constant.EndPoints.*;
import static app.filerepository.export.service.enums.ExportType.*;

@RestController
public class Export {
    @Autowired
    private IExportService exportService;
    private static final Logger logger = LoggerFactory.getLogger(Export.class);
    @GetMapping(EXPORT_BY_NAME_END_POINT)
    public ResponseFile exportByName(@PathVariable String fileName) throws Exception {
        logger.info("create exportByName response for: "+fileName);
        return exportService.getFile(fileName, NAME.name());
    }
    @GetMapping(EXPORT_BY_TYPE_END_POINT)
    public ResponseFile exportByType(@PathVariable String fileType) throws Exception {
        logger.info("create exportByType response for: "+fileType);
        return exportService.getFile(fileType, TYPE.name());
    }
    @GetMapping(EXPORT_BY_SIZE_END_POINT)
    public ResponseFile exportBySize(@PathVariable String size) throws Exception {
        logger.info("create exportBySize response for: "+size);
        return exportService.getFile(size, SIZE.name());
    }
    @GetMapping(EXPORT_BY_DATE_END_POINT)
    public ResponseFile exportByDate(@PathVariable String importDate) throws Exception {
        logger.info("create exportByDate response for: "+importDate);
        return exportService.getFile(importDate, DATE.name());
    }

}
