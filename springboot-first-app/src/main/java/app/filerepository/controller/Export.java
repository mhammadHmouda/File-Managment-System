package app.filerepository.controller;

import app.filerepository.services.exportservice.enums.ExportType;
import app.filerepository.response.ResponseFile;
import app.filerepository.services.exportservice.intf.IExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class Export {
    @Autowired
    private IExportService exportService;
    private static final Logger logger = LoggerFactory.getLogger(Export.class);
    @GetMapping("/exportByName/{fileName}")
    public ResponseFile exportByName(@PathVariable String fileName) throws FileNotFoundException {
        logger.info("create exportByName response for: "+fileName);
        return exportService.getFile(fileName, ExportType.FILE_NAME.name());
    }
    @GetMapping("/exportByType/{fileType}")
    public ResponseFile exportByType(@PathVariable String fileType) throws FileNotFoundException {
        logger.info("create exportByType response for: "+fileType);
        return exportService.getFile(fileType, ExportType.FILE_TYPE.name());
    }
    @GetMapping("/exportBySize/{size}")
    public ResponseFile exportBySize(@PathVariable String size) throws FileNotFoundException {
        logger.info("create exportBySize response for: "+size);
        return exportService.getFile(size, ExportType.SIZE.name());
    }
    @GetMapping("/exportByDate/{importDate}")
    public ResponseFile exportByDate(@PathVariable String importDate) throws FileNotFoundException {
        logger.info("create exportByDate response for: "+importDate);
        return exportService.getFile(importDate, ExportType.DATE.name());
    }
    @GetMapping("/exportByVersion/{version}")
    public ResponseFile exportByVersion(@PathVariable String version) throws FileNotFoundException {
        logger.info("create exportByVersion response for: "+version);
        return exportService.getFile(version, ExportType.VERSION.name());
    }
    @GetMapping("/files")
    public List<ResponseFile> getListFiles() {
        return exportService.getAllFiles();
    }
}
