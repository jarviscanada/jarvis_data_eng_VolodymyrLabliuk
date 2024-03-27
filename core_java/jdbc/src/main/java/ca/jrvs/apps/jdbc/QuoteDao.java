package ca.jrvs.apps.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuoteDao implements CrudDao<Quote, String> {

    private Connection c;
    private String url = "jdbc:postgresql://localhost:5432/stock_quote";
    private String username = "postgres";
    private String password = "password";

    public QuoteDao() {
        try {
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Quote save(Quote entity) throws IllegalArgumentException {
        try {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO quote (" +
                    "symbol, " +
                    "open, " +
                    "high, " +
                    "low, " +
                    "price, " +
                    "volume, " +
                    "latest_trading_day, " +
                    "previous_close, " +
                    "change, " +
                    "change_percent, " +
                    "timestamp" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                    "ON CONFLICT (symbol) DO UPDATE SET " +
                    "open = EXCLUDED.open, " +
                    "high = EXCLUDED.high, " +
                    "low = EXCLUDED.low, " +
                    "price = EXCLUDED.price, " +
                    "volume = EXCLUDED.volume, " +
                    "latest_trading_day = EXCLUDED.latest_trading_day, " +
                    "previous_close = EXCLUDED.previous_close, " +
                    "change = EXCLUDED.change, " +
                    "change_percent = EXCLUDED.change_percent, " +
                    "timestamp = EXCLUDED.timestamp");
            stmt.setString(1, entity.getTicker());
            stmt.setDouble(2, entity.getOpen());
            stmt.setDouble(3, entity.getHigh());
            stmt.setDouble(4, entity.getLow());
            stmt.setDouble(5, entity.getPrice());
            stmt.setInt(6, entity.getVolume());
            stmt.setDate(7, entity.getLatestTradingDay());
            stmt.setDouble(8, entity.getPreviousClose());
            stmt.setDouble(9, entity.getChange());
            stmt.setString(10, entity.getChangePercent());
            stmt.setTimestamp(11, entity.getTimestamp());

            stmt.executeUpdate();

            return entity;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Failed to save quote", e);
        }
    }

    @Override
    public Optional<Quote> findById(String s) throws IllegalArgumentException {
        try {
            if(s == null){
                throw new IllegalArgumentException();
            }
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM quote WHERE symbol = ?");
            stmt.setString(1, s);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Quote quote = new Quote();
                quote.setTicker(rs.getString("symbol"));
                quote.setOpen(rs.getDouble("open"));
                quote.setHigh(rs.getDouble("high"));
                quote.setLow(rs.getDouble("low"));
                quote.setPrice(rs.getDouble("price"));
                quote.setVolume(rs.getInt("volume"));
                quote.setLatestTradingDay(rs.getDate("latest_trading_day"));
                quote.setPreviousClose(rs.getDouble("previous_close"));
                quote.setChange(rs.getDouble("change"));
                quote.setChangePercent(rs.getString("change_percent"));
                quote.setTimestamp(rs.getTimestamp("timestamp"));
                return Optional.of(quote);
            } else {
                return Optional.empty();
            }
        } catch (SQLException | NumberFormatException e) {
            throw new IllegalArgumentException("Failed to find quote by ID", e);
        }
    }

    @Override
    public Iterable<Quote> findAll() {
        List<Quote> quotes = new ArrayList<>();
        try {
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM quote");

            while (rs.next()) {
                Quote quote = new Quote();
                quote.setTicker(rs.getString("symbol"));
                quote.setOpen(rs.getDouble("open"));
                quote.setHigh(rs.getDouble("high"));
                quote.setLow(rs.getDouble("low"));
                quote.setPrice(rs.getDouble("price"));
                quote.setVolume(rs.getInt("volume"));
                quote.setLatestTradingDay(rs.getDate("latest_trading_day"));
                quote.setPreviousClose(rs.getDouble("previous_close"));
                quote.setChange(rs.getDouble("change"));
                quote.setChangePercent(rs.getString("change_percent"));
                quote.setTimestamp(rs.getTimestamp("timestamp"));
                quotes.add(quote);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve quotes", e);
        }
        return quotes;
    }

    @Override
    public void deleteById(String s) throws IllegalArgumentException {
        if(s == null){
            throw new IllegalArgumentException();
        }
        try {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM quote WHERE symbol = ?");
            stmt.setString(1, s);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted == 0) {
                throw new IllegalArgumentException("Quote not found with symbol: " + s);
            }
        } catch (SQLException | NumberFormatException e) {
            throw new IllegalArgumentException("Failed to delete quote by symbol", e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            Statement stmt = c.createStatement();

            stmt.executeUpdate("DELETE FROM quote");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete all quotes", e);
        }
    }
}
