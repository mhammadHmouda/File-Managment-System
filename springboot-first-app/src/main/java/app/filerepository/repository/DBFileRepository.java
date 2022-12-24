package app.filerepository.repository;

import app.filerepository.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    @Query(value = "select * from files3 where file_name like ?", nativeQuery = true)
    List<DBFile> findByName(String fileName);

    @Query(value = "select * from files3 where file_type like %?1", nativeQuery = true)
    List<DBFile> findByType(String fileType);

    @Query(value = "select * from files3 where size <= ?", nativeQuery = true)
    List<DBFile> findBySize(Integer size);

    @Query(value = "select * from files3 where version like %?1", nativeQuery = true)
    List<DBFile> findByVersion(String version);

    @Query(value = "select * from files3 where import_date like %?1", nativeQuery = true)
    List<DBFile> findByDate(String importDate);

    @Query(value = "select * from files3 where import_date BETWEEN ? AND ?", nativeQuery = true)
    List<DBFile> getAllBetweenDates(String startDate, String endDate);


}
