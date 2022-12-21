package app.filerepository.service;

import app.filerepository.model.DBFile;
import app.filerepository.uploadfile.UploadFileResponse;
import app.filerepository.repository.DBFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.*;

@Service
public class ImportService {

    @Autowired
    private DBFileRepository fileDBRepository;
    private static final Logger logger = LoggerFactory.getLogger(ImportService.class);
    public UploadFileResponse store(MultipartFile file) throws IOException {

        List<DBFile> files = fileDBRepository.findByName(file.getOriginalFilename());

        int version = 1;

        if (!files.isEmpty()) {
            version = files.stream()
                    .max(Comparator.comparing(DBFile::getVersion))
                    .get().getVersion() + 1;
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        DBFile FileDB = new DBFile(fileName, file.getContentType(), file.getSize(), version, file.getBytes());

        DBFile dbFile = fileDBRepository.save(FileDB);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/exportByName/")
                .path(dbFile.getFileName())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }


}
