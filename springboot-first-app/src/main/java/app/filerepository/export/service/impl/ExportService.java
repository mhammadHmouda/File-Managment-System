package app.filerepository.export.service.impl;

import app.exception.ExportException;
import app.filerepository.export.service.factory.FactoryExport;
import app.filerepository.export.service.intf.IExportService;
import app.filerepository.imports.encrypt.EncryptionService;
import app.model.DBFile;
import app.repository.db.DBFileRepository;
import app.response.ResponseFile;
import app.userauth.config.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

import static app.filerepository.export.service.enums.ExportConstant.*;
import static app.userauth.role.RoleEnum.ADMIN;
import static app.userauth.role.RoleEnum.STAFF;

@Service
public class ExportService implements IExportService {

    @Autowired
    private DBFileRepository fileDBRepository;

    @Autowired
    TokenProvider jwtTokenUtils;

    @Autowired
    private EncryptionService service;
    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

    public ResponseFile getFile(String val, String name , HttpServletRequest request) throws Exception {


        try {

            String role = jwtTokenUtils.getRole(request);
            if (!role.equalsIgnoreCase(ADMIN.name()) && !role.equalsIgnoreCase(STAFF.name())) {
                throw new Exception(EXPORT_FILE_UNAUTHORIZED);
            }

            List<DBFile> dbFiles = FactoryExport.factory(val, name, fileDBRepository);

            if (dbFiles.isEmpty()) {
                logger.warn("This export don't match any file");
                throw new ExportException(NOT_EXIST);
            }

            DBFile dbFile = dbFiles.stream().max(Comparator.comparing(DBFile::getVersion)).get();

//        byte[] data = EncryptionService.decrypt(dbFile.getData());

//        dbFile.setData(data);

            logger.info("File is fetched successfully!!");

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(DOWNLOAD_URL).path(dbFile.getFileName()).toUriString();

            return ResponseFile.getInstance(dbFile.getFileName(), fileDownloadUri, dbFile.getFileType(), dbFile.getSize());

        }catch (Exception e){
            throw new Exception(INVALID_EXPORT);
        }
    }

}
