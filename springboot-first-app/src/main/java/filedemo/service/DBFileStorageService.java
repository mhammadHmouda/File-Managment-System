package filedemo.service;

import filedemo.model.DBFile;
import filedemo.repository.DBFileRepository;
import filedemo.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository fileDBRepository;

    public DBFile store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        DBFile FileDB = new DBFile(fileName, file.getContentType(), file.getSize(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    public DBFile getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<DBFile> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public ResponseMessage delete(String id){
        Optional<DBFile> file = fileDBRepository.findById(id);
        if(file.isPresent()){
            fileDBRepository.deleteById(id);
            return new ResponseMessage("File deleted successfully!");
        } else {
            return new ResponseMessage("File not exist!");
        }
    }
}
