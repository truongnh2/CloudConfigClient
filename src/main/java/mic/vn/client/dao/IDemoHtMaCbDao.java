package mic.vn.client.dao;

import java.sql.SQLException;
import java.util.List;

import mic.vn.client.model.DemoHtMaCb;

public interface IDemoHtMaCbDao {
	public String add(DemoHtMaCb htMaCb) throws SQLException;

	public void delete(int ma) throws SQLException;

	public DemoHtMaCb htMaCb(int ma) throws SQLException;

	public List<DemoHtMaCb> getHtMaCb() throws SQLException;

	public void update(DemoHtMaCb htMaCb) throws SQLException;
	
	public List<DemoHtMaCb> getHtMaCbProc() throws SQLException;
}
