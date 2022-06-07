package mic.vn.client.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import mic.vn.client.connection.DatabaseConnection;
import mic.vn.client.model.DemoHtMaCb;

public class DemoHtMaCbDaoImpl implements IDemoHtMaCbDao {
	//static DatabaseConnection databaseConnection = new DatabaseConnection();
	static Connection con = DatabaseConnection.getConnection();

	@Override
	public String add(DemoHtMaCb htMaCb) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int ma) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public DemoHtMaCb htMaCb(int ma) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DemoHtMaCb> getHtMaCb() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM DEMO_HT_MA_CB";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<DemoHtMaCb> ls = new ArrayList();

		while (rs.next()) {
			DemoHtMaCb htMaCb = new DemoHtMaCb();
			htMaCb.setMa(rs.getString("MA"));
			htMaCb.setMaDvi(rs.getString("MA_DVI"));
			htMaCb.setTen(rs.getString("TEN"));
			htMaCb.setSoCmt(rs.getString("SO_CMT"));
			htMaCb.setPhong(rs.getString("PHONG"));
			htMaCb.setCv(rs.getString("CV"));
			htMaCb.setMaTk(rs.getString("MA_TK"));
			htMaCb.setNhang(rs.getString("NHANG"));
			htMaCb.setTenNh(rs.getString("TEN_NH"));
			htMaCb.setMobi(rs.getString("MOBI"));
			htMaCb.setMail(rs.getString("MAIL"));
			htMaCb.setMaHr(rs.getString("MA_HR"));
			htMaCb.setNhom(rs.getString("NHOM"));
			htMaCb.setMuc(rs.getString("MUC"));
			htMaCb.setNsd(rs.getString("NSD"));
			htMaCb.setIdVung(rs.getString("IDVUNG"));
			
			ls.add(htMaCb);
		}
		return ls;
	}

	@Override
	public void update(DemoHtMaCb htMaCb) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DemoHtMaCb> getHtMaCbProc() throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sql = "{call PBH_API_TEST_LKE(?)}";
				CallableStatement call = con.prepareCall(sql);
				call.registerOutParameter (1, OracleTypes.CURSOR);
				call.execute ();
				ResultSet rs = (ResultSet)call.getObject (1);
				List<DemoHtMaCb> ls = new ArrayList();

				while (rs.next()) {
					DemoHtMaCb htMaCb = new DemoHtMaCb();
					htMaCb.setMa(rs.getString("MA"));
					htMaCb.setMaDvi(rs.getString("MA_DVI"));
					htMaCb.setTen(rs.getString("TEN"));
					htMaCb.setSoCmt(rs.getString("SO_CMT"));
					htMaCb.setPhong(rs.getString("PHONG"));
					htMaCb.setCv(rs.getString("CV"));
					htMaCb.setMaTk(rs.getString("MA_TK"));
					htMaCb.setNhang(rs.getString("NHANG"));
					htMaCb.setTenNh(rs.getString("TEN_NH"));
					htMaCb.setMobi(rs.getString("MOBI"));
					htMaCb.setMail(rs.getString("MAIL"));
					htMaCb.setMaHr(rs.getString("MA_HR"));
					htMaCb.setNhom(rs.getString("NHOM"));
					htMaCb.setMuc(rs.getString("MUC"));
					htMaCb.setNsd(rs.getString("NSD"));
					htMaCb.setIdVung(rs.getString("IDVUNG"));
					
					ls.add(htMaCb);
				}
				return ls;
	}

}
