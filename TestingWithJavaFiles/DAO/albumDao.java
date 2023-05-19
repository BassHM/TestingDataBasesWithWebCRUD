package TestingWithJavaFiles.DAO;

import java.sql.*;
//Al transportarlo al proyecto final, hay que modificar los imports
import TestingWithJavaFiles.Connector.SQLServerConnector;
import java.util.List;

/**
 *
 * @author memoo
 */
public class albumDAO {

    class Album {

        public int id;
        public String title;

        public Album(int id, String title) {
            this.id = id;
            this.title = title;
        }
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    private Connection selectedMode(int mode) throws SQLException {
        Connection con = null;
        switch (mode) {
            case 1:
                break;
            case 2:

                break;
            case 3:

                break;

            case 4:
                con = SQLServerConnector.getConnection();
                break;
        }
        return con;
    }

    public long Insert(List<String> queries, int selectedMode) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        long start = getCurrentTime();
        for (int i = 0; i < queries.size(); i++) {
            try {
                con = selectedMode(selectedMode);
                stm = con.prepareStatement(queries.get(i));
                stm.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                SQLServerConnector.close(stm);
                SQLServerConnector.close(con);
            }
        }
        long end = getCurrentTime();
        return end - start;
    }

    public long Select(List<String> queries, int selectedMode) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        long start = getCurrentTime();
        for (int i = 0; i < queries.size(); i++) {
            try {
                con = selectedMode(selectedMode);
                stm = con.prepareStatement(queries.get(i));
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    System.out.println("id: " + id + " tilte: " + title);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                SQLServerConnector.close(rs);
                SQLServerConnector.close(stm);
                SQLServerConnector.close(con);
            }
        }
        long end = getCurrentTime();
        return end - start;
    }

    public long Update(List<String> queries, int selectedMode) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        long start = getCurrentTime();
        for (int i = 0; i < queries.size(); i++) {
            try {
                con = selectedMode(selectedMode);
                stm = con.prepareStatement(queries.get(i));
                stm.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                SQLServerConnector.close(stm);
                SQLServerConnector.close(con);
            }
        }
        long end = getCurrentTime();
        return end - start;
    }

    public long Delete(List<String> queries, int selectedMode) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        long start = getCurrentTime();
        for (int i = 0; i < queries.size(); i++) {
            try {
                con = selectedMode(selectedMode);
                stm = con.prepareStatement(queries.get(i));
                stm.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                SQLServerConnector.close(stm);
                SQLServerConnector.close(con);
            }
        }
        long end = getCurrentTime();
        return end - start;
    }
}
