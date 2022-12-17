package app.filerepository.controller;

import app.filerepository.response.ResponseFile;
import app.filerepository.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Export {
    @Autowired
    private ExportService exportService;

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        return exportService.getFile(fileName);
    }

    @GetMapping("/files")
    public List<ResponseFile> getListFiles() {
        return exportService.getAllFiles();
    }
}
