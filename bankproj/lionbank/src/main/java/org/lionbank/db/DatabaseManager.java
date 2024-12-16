package org.lionbank.db;

import java.sql.*;
import javax.security.auth.login.AccountNotFoundException;

public class DatabaseManager {
  private final Connection connection;

  public DatabaseManager(String url, String user, String password)
      throws SQLException, ClassNotFoundException {
//    Class.forName("com.mysql.jdbc.Driver");
    this.connection = DriverManager.getConnection(url, user, password);
  }

  public boolean isCustomerExists(String customerId) throws SQLException {
    String query = "SELECT COUNT(*) FROM customers WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, customerId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return rs.getInt(1) > 0;
      }
      return false;
    }
  }

  public void addCustomer(String id, String name) throws SQLException {
    String query = "INSERT INTO customers (id, name) VALUES (?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, id);
      stmt.setString(2, name);
      stmt.executeUpdate();
    }
  }

  public void addAccount(String customerId, String accountId) throws SQLException {
    String query = "INSERT INTO accounts (account_id, customer_id, balance) VALUES (?, ?, 0)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, accountId);
      stmt.setString(2, customerId);
      stmt.executeUpdate();
    }
  }

  public int getAccountCountByCustomer(String customerId) throws SQLException {
    String query = "SELECT COUNT(*) FROM accounts WHERE customer_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, customerId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return rs.getInt(1);
      }
      return 0;
    }
  }

  public double getBalance(String accountId) throws AccountNotFoundException, SQLException {
    String query = "SELECT balance FROM accounts WHERE account_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, accountId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return rs.getDouble(1);
      } else {
        throw new AccountNotFoundException("Account not found.");
      }
    }
  }

  public void updateBalance(String accountId, double amount) throws AccountNotFoundException, SQLException {
    double currentBalance = getBalance(accountId);
    String query = "UPDATE accounts SET balance = ? WHERE account_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setDouble(1, currentBalance + amount);
      stmt.setString(2, accountId);
      stmt.executeUpdate();
    }
  }
}