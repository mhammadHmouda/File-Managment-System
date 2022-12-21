package app.filerepository.service;

import app.filerepository.model.DBFile;
import app.filerepository.repository.DBFileRepository;
import app.filerepository.response.ResponseFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportService {

    @Autowired
    private DBFileRepository fileDBRepository;

    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

    public ResponseEntity<?> getFile(String val, String name) {
        List<DBFile> dbFiles = FactoryExport.factory(val, name, fileDBRepository);

        if(dbFiles.isEmpty()){
            return new ResponseEntity<>("File Not Exist!", HttpStatus.NOT_FOUND);
        }

        DBFile dbFile = dbFiles.stream().max(Comparator.comparing(DBFile::getVersion)).get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "/attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    public List<ResponseFile> getAllFiles() {
        return fileDBRepository.findAll().stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/exportByName/")
                    .path(dbFile.getFileName())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getFileName(), fileDownloadUri,
                    dbFile.getFileType(), dbFile.getData().length);

        }).collect(Collectors.toList());
    }
}
