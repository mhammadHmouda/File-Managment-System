package app.filerepository.controller;

import app.filerepository.enums.ClassificationExport;
import app.filerepository.response.ResponseFile;
import app.filerepository.services.exportservice.impl.ExportService;
import app.filerepository.services.exportservice.intf.IExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Export {
    @Autowired
    private IExportService exportService;
    private static final Logger logger = LoggerFactory.getLogger(Export.class);
    @GetMapping("/exportByName/{fileName}")
    public ResponseEntity<?> exportByName(@PathVariable String fileName) {
        logger.info("create exportByName response for: "+fileName);
        return exportService.getFile(fileName, ClassificationExport.FILE_NAME.name());
    }
    @GetMapping("/exportByType/{fileType}")
    public ResponseEntity<?> exportByType(@PathVariable String fileType) {
        logger.info("create exportByType response for: "+fileType);
        return exportService.getFile(fileType, ClassificationExport.FILE_TYPE.name());
    }
    @GetMapping("/exportBySize/{size}")
    public ResponseEntity<?> exportBySize(@PathVariable String size) {
        logger.info("create exportBySize response for: "+size);
        return exportService.getFile(size, ClassificationExport.SIZE.name());
    }
    @GetMapping("/exportByDate/{importDate}")
    public ResponseEntity<?> exportByDate(@PathVariable String importDate) {
        logger.info("create exportByDate response for: "+importDate);
        return exportService.getFile(importDate, ClassificationExport.DATE.name());
    }
    @GetMapping("/exportByVersion/{version}")
    public ResponseEntity<?> exportByVersion(@PathVariable String version) {
        logger.info("create exportByVersion response for: "+version);
        return exportService.getFile(version, ClassificationExport.VERSION.name());
    }
    @GetMapping("/files")
    public List<ResponseFile> getListFiles() {
        return exportService.getAllFiles();
    }
}
