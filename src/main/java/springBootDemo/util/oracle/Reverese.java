package springBootDemo.util.oracle;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reverese {
	private  String driverName 	= "oracle.jdbc.driver.OracleDriver";
//	private  String url			= "jdbc:oracle:thin:@134.64.15.212:1521:oratc";//oracle连接串
//	private  String user			= "cath_thinkops";//用户
//	private  String password		= "thinkops2016";//密码
//	private static String tableName="idc_dispatch_group_member";//表名,建议大写
	private  String url			= "jdbc:oracle:thin:@192.168.80.151:1521:orcl";//oracle连接串
	private  String user			= "cath_thinkops";//用户
	private  String password		= "thinkops2016";//密码
	private static String tableName="USER_INFO";//表名,建议大写
	private static boolean ifNote = true;  //是否在类名和set方法前加注解
	
	//核心sql
	private static final String sql = "SELECT DECODE(tc.DATA_TYPE,"+"\n"+
				"            'VARCHAR2',"+"\n"+
				"            'String',"+"\n"+
				"            'NUMBER',"+"\n"+
				"            'Long',"+"\n"+
				"            'DATE',"+"\n"+
				"            'Date',"+"\n"+
				"           tc.DATA_TYPE) columnType, --字段类型转换"+"\n"+
				" 			LOWER(tc.COLUMN_NAME) columnName,"+"\n"+
				"     REPLACE(uc.COMMENTS, chr(10), '') descn --解决注释有换行的情况"+"\n"+
				"from user_tab_columns tc, user_col_comments uc"+"\n"+
				"WHERE --关联字段注释表"+"\n"+
				"tc.COLUMN_NAME = uc.COLUMN_NAME"+"\n"+
				"AND tc.TABLE_NAME = uc.table_name"+"\n"+
				"AND tc.table_name = '"+tableName.toUpperCase()+"' --表名"+"\n"+
				"ORDER by tc.column_id --按照表字段顺序排列输出";
	private  Connection conn = null;
	static{
		System.out.println(sql);
	}
	public  Statement getStatement(){
		getConn();
		if(null==conn)
			return null;
		else{
			try {
				return conn.createStatement();
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("oracle数据库连接出错!");
				return null;
			}
		}
	}
	private  Connection getConn(){
		try {
			Long start = System.currentTimeMillis();
//			System.out.println("***driverName:"+driverName+"***");
//			System.out.println("***url:"+url+"***");
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			Long end = System.currentTimeMillis();
//			System.out.println("***getConn 花费:"+(end-start)/1000.0+"秒***");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   	return null;
	}
	public void disConn(){
		try {
			conn.close();
		}catch(Exception e){
			System.out.println("***conn close error***");
		}
	}
	public static void main(String[] args) {
		String getModel="	public TYPE getNAME() {"+"\n"+
						"		return VALUE;"+"\n"+
						"	}";
		String setModel="	public void setNAME(TYPE VALUE) {"+"\n"+
						"		this.VALUE = VALUE;"+"\n"+
						"	}";
		List<String> columnTypes=new ArrayList<String>();
		List<String> columnNames=new ArrayList<String>();
		List<String> columnNamesNew=new ArrayList<String>();
		List<String> columnDesc=new ArrayList<String>();
		Reverese oc = new Reverese();
		try {
			Long start = System.currentTimeMillis();
			ResultSet rs = oc.getStatement().executeQuery(oc.sql);
			rs.setFetchSize(100);//数据多的时候很关键，不过一般不能设置过大
			Long end = System.currentTimeMillis();
//			System.out.println("***query 花费:"+(end-start)/1000.0+"秒***");
			List<String> keylist = new ArrayList<String>();
//			System.out.println("rs:");
			while(rs.next()){
				String columnType=rs.getString("COLUMNTYPE");
				String columnName=rs.getString("COLUMNNAME");
				String descn =rs.getString("DESCN");
				columnTypes.add(columnType);
				columnNames.add(columnName);
				columnDesc.add(descn);
//				System.out.println(columnTypes.size()+" "+columnNames.size()+" "+columnDesc.size());
			}
			Long end1 = System.currentTimeMillis();
//			System.out.println("***while 花费:"+(end1-end)/1000.0+"秒***");
		} catch (SQLException e) {
			System.out.println("Oracle查询数据出错!");
		}finally{
			oc.disConn();
		}
		//
		for (int i = 0; i <columnNames.size(); i++) {
			String columnType=columnTypes.get(i);
			String columnName=columnNames.get(i);
			String descn =columnDesc.get(i);
			String[] columnNameStr = columnName.split("_");
			String columnNameNew="";
			for (int j = 0; j < columnNameStr.length; j++) {
				String tmp ="";
				if(j==0){
					tmp= columnNameStr[j];
				}else{
					tmp= columnNameStr[j].substring(0, 1).toUpperCase()+columnNameStr[j].substring(1, columnNameStr[j].length());
				}
				columnNameNew+=tmp;
			}
			columnNamesNew.add(columnNameNew);
		}
		
		String tableNameU = oc.tableName.toUpperCase();
		String tableNameL = oc.tableName.toLowerCase();
		String[] tableNameLs=tableNameL.split("_");
		String className = "";
		for (int i = 0; i < tableNameLs.length; i++) {
			className+=tableNameLs[i].substring(0, 1).toUpperCase()+tableNameLs[i].substring(1, tableNameLs[i].length());
		}
		System.out.println("import javax.persistence.*;");
		System.out.println("import java.io.Serializable;");
		System.out.println("import java.util.Date;");
		System.out.println("");
		if(oc.ifNote){
			System.out.println("@Entity");
			System.out.println("@Table(name = \""+tableNameU+"\", schema = \""+oc.user.toUpperCase()+"\")");
		}
		System.out.println("public class "+className+" implements Serializable {");
		for (int i = 0; i <columnNames.size(); i++) {
			String columnType=columnTypes.get(i);
			String columnName=columnNames.get(i);
			String columnNameNew=columnNamesNew.get(i);
			String descn =columnDesc.get(i);
			String typeNameAndeDesc="	private "+columnType+" "+columnNameNew+";//"+descn;
			System.out.println(typeNameAndeDesc);
		}
		System.out.println("");
		for (int i = 0; i <columnNames.size(); i++) {
			String columnType=columnTypes.get(i);
			String columnName=columnNames.get(i);
			String columnNameNew=columnNamesNew.get(i);
			String getStr = getModel.replaceAll("TYPE",columnType).replaceAll("NAME",columnNameNew.substring(0, 1).toUpperCase()+columnNameNew.substring(1,columnNameNew.length())).replaceAll("VALUE",columnNameNew);
			String setStr = setModel.replaceAll("TYPE",columnType).replaceAll("NAME",columnNameNew.substring(0, 1).toUpperCase()+columnNameNew.substring(1,columnNameNew.length())).replaceAll("VALUE",columnNameNew);
			if(oc.ifNote){
				System.out.println("	@Basic");
				System.out.println("	@Column(name = \""+columnName.toUpperCase()+"\")");
			}
			System.out.println(getStr);
			System.out.println("");
			System.out.println(setStr);
			System.out.println("");
		}
		System.out.println("}");
	}
}