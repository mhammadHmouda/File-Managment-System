package app.filerepository.rederview.service;

import app.exception.ReaderException;
import app.model.DBFile;
import app.repository.db.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static app.filerepository.rederview.constant.ReadConstant.EMPTY_DB;

@Service
public class ReaderService {

    @Autowired
    DBFileRepository fileDBRepository;

    public List<String> getAllFiles() throws ReaderException {
        try {

            return fileDBRepository.findAll().stream().map(DBFile::getFileName).collect(Collectors.toList());
        }catch (Exception e){
            throw new ReaderException(EMPTY_DB);
        }
    }


}
