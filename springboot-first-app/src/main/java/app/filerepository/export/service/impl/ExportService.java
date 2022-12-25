package app.filerepository.export.service.impl;

import app.config.TokenProvider;
import app.filerepository.export.service.factory.FactoryExport;
import app.filerepository.export.service.intf.IExportService;
import app.filerepository.imports.encrypt.EncryptionService;
import app.model.DBFile;
import app.repository.DBFileRepository;
import app.response.ResponseFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;

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


        String role =  jwtTokenUtils.getRole(request);
        if(!role.equalsIgnoreCase(ADMIN.name()) &&!role.equalsIgnoreCase(STAFF.name())){
            throw new Exception("Unauthorized , No Permission to Export a File");
        }
        List<DBFile> dbFiles = FactoryExport.factory(val, name, fileDBRepository);

        if(dbFiles.isEmpty()){
            logger.warn("This Classify don't match any file");
            throw new FileNotFoundException("File Not Found!");
        }

        DBFile dbFile = dbFiles.stream().max(Comparator.comparing(DBFile::getVersion)).get();

        byte[] data = EncryptionService.decrypt(dbFile.getData());

        dbFile.setData(data);

        logger.info("File is fetched successfully!!");

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/exportByName/").path(dbFile.getFileName()).toUriString();

        return ResponseFile.getInstance(dbFile.getFileName(), fileDownloadUri, dbFile.getFileType(), dbFile.getSize());
    }

}
