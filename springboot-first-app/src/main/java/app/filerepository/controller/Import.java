package app.filerepository.controller;


import app.filerepository.response.ResponseFile;
import app.filerepository.services.importservice.intf.IImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Import {

    @Autowired
    private IImportService importService;
    private static final Logger logger = LoggerFactory.getLogger(Import.class);
    @PostMapping("/uploadFile")
    public ResponseFile uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("uploadFile Response created");
        return importService.store(file);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<ResponseFile> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files).map(file -> {
            try {
                return uploadFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
