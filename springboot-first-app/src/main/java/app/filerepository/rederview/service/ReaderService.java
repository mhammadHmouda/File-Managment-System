package app.filerepository.rederview.service;

import app.model.DBFile;
import app.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderService {

    @Autowired
    DBFileRepository fileDBRepository;

    public List<String> getAllFiles() {
        return fileDBRepository.findAll().stream().map(DBFile::getFileName).collect(Collectors.toList());
    }

}
