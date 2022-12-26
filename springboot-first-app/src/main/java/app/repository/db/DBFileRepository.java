package app.repository.db;

import app.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import static app.repository.constant.QueryConstant.*;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    @Query(value = QUERY_OF_NAME, nativeQuery = true)
    List<DBFile> findByName(String fileName);

    @Query(value = QUERY_OF_TYPE, nativeQuery = true)
    List<DBFile> findByType(String fileType);

    @Query(value = QUERY_OF_SIZE, nativeQuery = true)
    List<DBFile> findBySize(Integer size);

    @Query(value = QUERY_OF_DATE, nativeQuery = true)
    List<DBFile> findByDate(String importDate);

    @Query(value = QUERY_BETWEEN_DATE, nativeQuery = true)
    List<DBFile> getAllBetweenDates(String startDate, String endDate);


}
