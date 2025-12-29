package connexion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class EtablirConnex{
    static Properties props;
	public static Connection getConnection() throws Exception{
            Class.forName(getProps().getProperty("classname"));
            //String url="jdbc:postgresql://localhost:5432/rencontre?user=postgres&password=root&ssl=true&sslmode=disable";
            return DriverManager.getConnection(getProps().getProperty("url"), getProps());
	}

    public static Properties getProps() throws IOException{
        if(props==null){
            try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")) {
                props=new Properties();
                props.load(in);
            }
        }
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
    public static void main(String[] gygy)throws Exception{
        Connection conn= EtablirConnex.getConnection();
    }
}