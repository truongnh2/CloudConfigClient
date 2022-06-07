/**
 * 
 */
package mic.vn.client.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mic.vn.client.dao.DemoHtMaCbDaoImpl;
import mic.vn.client.model.DemoHtMaCb;

/**
 * @author truongnh
 *
 */
@Service
public class DemoHtMaCbService implements IDemoHtMaCbService {
	private DemoHtMaCbDaoImpl demoHtMaCbDaoImpl = new DemoHtMaCbDaoImpl();

	@Override
	public List<DemoHtMaCb> getAll() throws SQLException {
		// TODO Auto-generated method stub
		List<DemoHtMaCb> demoHtMaCbs =demoHtMaCbDaoImpl.getHtMaCb();
		return demoHtMaCbs;
	}

	@Override
	public List<DemoHtMaCb> getAllProc() throws SQLException {
		// TODO Auto-generated method stub
		List<DemoHtMaCb> demoHtMaCbs =demoHtMaCbDaoImpl.getHtMaCbProc();
		return demoHtMaCbs;
	}

}
