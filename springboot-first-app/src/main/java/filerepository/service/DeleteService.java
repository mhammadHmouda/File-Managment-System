package filerepository.service;

import filerepository.model.DBFile;
import filerepository.repository.DBFileRepository;
import filerepository.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public ResponseMessage delete(String id){
        Optional<DBFile> file = dbFileRepository.findById(id);
        if(file.isPresent()){
            dbFileRepository.deleteById(id);
            return new ResponseMessage("File deleted successfully!");
        } else {
            return new ResponseMessage("File not exist!");
        }
    }

    public ResponseMessage deleteAllFiles(){
        try {
            if(dbFileRepository.findAll().toArray().length != 0){
                dbFileRepository.deleteAll();
                return new ResponseMessage("Files deleted successfully!");
            }
            return new ResponseMessage("No file to deleted!");

        }catch (Exception e){
            return new ResponseMessage("Cannot delete these files!!!");
        }
    }
}
