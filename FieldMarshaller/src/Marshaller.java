import java.sql.*;
//import com.ibm.as400.access.AS400JDBCDriver;

public class Marshaller {

    static final String JDBC_DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
//    static final String JDBC_DRIVER2 = "com.ibm.db2.jcc.DB2Driver";
    static final String DB_URL = "jdbc:as400://10.132.190.237/kfilfin";


    static final String USER = "cscp";
    static final String PASS = "cscp";
    public static void main(String[] args) {
        String date="";
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT substr(TISCTL,135,7) from kfilfin.tipf";
            ResultSet rs = stmt.executeQuery(sql);


            while(rs.next()){
                date = rs.getString(1);
                System.out.println("##############  "+ date);
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){

            se.printStackTrace();
        }catch(Exception e){

            e.printStackTrace();
        }finally{

            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing can be done
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("tapedzaaaaaaa!!!!!");
    }

}
