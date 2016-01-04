package cn.org.quark.core.init;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.org.quark.core.utils.DBUnitUtils;

public class InitManager  {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Resource location;
	private String schema;
	private String dbtype;
	public void init() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from CORE_ROLES",Integer.class);
		if (count == 0) {
			File f;
			try {
				f = location.getFile();
				if(f.exists() && f.isDirectory()){
					DBUnitUtils db = new DBUnitUtils(jdbcTemplate.getDataSource(),schema,dbtype);
					for(File xml:f.listFiles()){
						if(xml.getName().endsWith(".xml")){
							db.insertTestData(xml);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("系统初始化完毕，如果不能登陆系统，请重新启动服务器！");
			//java.lang.System.exit(0);
		}
	}

	public void setLocation(Resource location) {
		this.location = location;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	
}
