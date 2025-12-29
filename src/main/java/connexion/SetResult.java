package connexion;
import java.sql.*;
import java.util.Vector;
import source.Col;
public class SetResult{
	String sql;
	ResultSet rs;
	ResultSetMetaData rsmd;
        Col[] listCol;
        
	public SetResult(ResultSet rs,String sql) throws Exception{
		this.rs=rs;
		this.rsmd=this.rs.getMetaData();
		this.sql=sql;
	}
	public ResultSet getRS(){
		return this.rs;
	}
	public ResultSetMetaData getRSMD(){
		return this.rsmd;
	}
	public int nbrColumn() throws Exception{
    	 int nbr = this.rsmd.getColumnCount();
		return nbr;
	}
	public String[] getColName() throws Exception{
		int nbr=this.nbrColumn();
		String[] val=new String[nbr];
		for(int i=1;i<=nbr;i++){
			val[i-1]=this.rsmd.getColumnName(i);
		}
		return val;
	}
	public int nbrData() throws Exception{
		Connection co=(new EtablirConnex()).getConnection();
		Statement stat=co.createStatement();
		ResultSet res=stat.executeQuery(sql);
		int retour=0;
		while(res.next()){
			retour++;
		}
		stat.close();
		co.close();
		return retour;
	}
	public Object[][] getData(int nbrColumn,int nbrData) throws Exception{
		Object[][] val=new Object[nbrData][nbrColumn];
		int u=0;
		 while(rs.next()){
			for(int ii=1;ii<=nbrColumn;ii++){
				val[u][ii-1]=rs.getObject(ii);
			}
			u++;
		}
		return val;
	}

        public Col[] getListCol()throws Exception {
            if(listCol == null){
                int columnCount = getRSMD().getColumnCount();
                listCol = new Col[columnCount];
                Vector list = new Vector();                
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = getRSMD().getColumnName(i);
                    int colType = getRSMD().getColumnType(i);
                    String dataType = getRSMD().getColumnTypeName(i);
                    int characterMaximumLength = getRSMD().getColumnDisplaySize(i);
                    int numericPrecision = getRSMD().getPrecision(i);
                    String typejava = getRSMD().getColumnClassName(i);
                    list.add(new Col(columnName, colType, dataType, characterMaximumLength, numericPrecision,typejava));
                }
                list.copyInto(listCol);
            }
            return listCol;
        }

        public void setListCol(Col[] listCol) {
            this.listCol = listCol;
        }
        
        
}