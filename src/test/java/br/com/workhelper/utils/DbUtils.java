package br.com.workhelper.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {

    private String url;
    private String username;
    private String password;

    public DbUtils(Map<String, Object> config) {
        this.url = (String) config.get("url");
        this.username = (String) config.get("username");
        this.password = (String) config.get("password");
    }

    // SELECT
    public List<Map<String, Object>> readRows(String query) {
        List<Map<String, Object>> rows = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(url, username, password);
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(query)) {

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                rows.add(row);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar query no banco: " + query, e);
        }
        return rows;
    }

    // INSERT/UPDATE/DELETE
    public int update(String query) {
        try (Connection c = DriverManager.getConnection(url, username, password);
             Statement s = c.createStatement()) {
            return s.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar update: " + query, e);
        }
    }
}