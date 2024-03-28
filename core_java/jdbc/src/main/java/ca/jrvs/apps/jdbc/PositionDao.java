package ca.jrvs.apps.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionDao implements CrudDao<Position, String> {

    private Connection c;

    // Constructor to initialize the Connection
    public PositionDao(Connection c) {
        this.c = c;
    }

    @Override
    public Position save(Position entity) throws IllegalArgumentException {
        try {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO position (" +
                        "symbol, " +
                        "number_of_shares, " +
                        "value_paid " +
                    ") VALUES (?, ?, ?) " +
                    "ON CONFLICT (symbol) DO UPDATE SET " +
                    "number_of_shares = EXCLUDED.number_of_shares, " +
                    "value_paid = EXCLUDED.value_paid ");

                stmt.setString(1, entity.getTicker());
                stmt.setDouble(2, entity.getNumOfShares());
                stmt.setDouble(3, entity.getValuePaid());
                stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public Optional<Position> findById(String s) throws IllegalArgumentException {
        PreparedStatement stmt;
        try {
            stmt = c.prepareStatement("SELECT * FROM position WHERE symbol = ?");
            stmt.setString(1, s);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Position position = new Position();
                position.setTicker(rs.getString("symbol"));
                position.setNumOfShares(rs.getInt("number_of_shares"));
                position.setValuePaid(rs.getDouble("value_paid"));
                return Optional.of(position);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        try {
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM position");

            while (rs.next()) {
                Position position = new Position();
                position.setTicker(rs.getString("symbol"));
                position.setNumOfShares(rs.getInt("number_of_shares"));
                position.setValuePaid(rs.getDouble("value_paid"));
                positions.add(position);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve quotes", e);
        }
        return positions;
    }

    @Override
    public void deleteById(String s) throws IllegalArgumentException {
        try {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM position WHERE symbol = ?");
            stmt.setString(1, s);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted == 0) {
                throw new IllegalArgumentException("Position not found with symbol: " + s);
            }
        } catch (SQLException | NumberFormatException e) {
            throw new IllegalArgumentException("Failed to delete quote by symbol", e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            Statement stmt = c.createStatement();

            stmt.executeUpdate("DELETE FROM position");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete all positions", e);
        }
    }
}
