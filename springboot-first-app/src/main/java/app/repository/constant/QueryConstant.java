package app.repository.constant;

public class QueryConstant {
    public static final String QUERY_OF_NAME = "select * from files3 where file_name like ?";
    public static final String QUERY_OF_TYPE = "select * from files3 where file_type like %?1";
    public static final String QUERY_OF_SIZE = "select * from files3 where size <= ?";
    public static final String QUERY_OF_DATE = "select * from files3 where import_date like %?1";
    public static final String QUERY_BETWEEN_DATE = "select * from files3 where import_date BETWEEN ? AND ?";

}
