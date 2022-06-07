/**
 * 
 */
package mic.vn.client.service;

import java.sql.SQLException;
import java.util.List;

import mic.vn.client.model.DemoHtMaCb;

/**
 * @author truongnh
 *
 */
public interface IDemoHtMaCbService{
	List<DemoHtMaCb> getAll() throws SQLException;
	
	List<DemoHtMaCb> getAllProc() throws SQLException;

}
