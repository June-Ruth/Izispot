package com.demo.dao;

import com.demo.model.Vehicle;
import com.demo.configuration.DataBaseManager;
import com.demo.constants.DbConstants;
import com.demo.model.Ticket;

import java.sql.*;

public class TicketDao {

    public boolean saveTicket(Ticket ticket) {
        try (Connection connection = DataBaseManager.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(DbConstants.SAVE_TICKET)
        ) {
            ps.setString(1, ticket.getVehicleNumber());
            ps.setString(2, ticket.getVehicle().toString());
            ps.setBoolean(3, ticket.isSubscriber());
            ps.setTimestamp(4, Timestamp.valueOf(ticket.getInTime()));
            ps.setNull(5, Types.TIMESTAMP);
            ps.setNull(6, Types.DOUBLE);
            int updateRowCount = ps.executeUpdate();
            return updateRowCount == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Ticket getTicket(String vehicleNumber) {
        Ticket ticket = new Ticket();
        try (Connection connection = DataBaseManager.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(DbConstants.GET_LAST_TICKET_BY_NUMBER_VEHICLE)
        ) {
            ps.setString(1, vehicleNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ticket.setId(rs.getInt("ID"));
                ticket.setVehicleNumber(rs.getString("VEHICLE_NUMBER"));
                ticket.setVehicle(Vehicle.valueOf(rs.getString("VEHICLE_TYPE")));
                ticket.setSubscriber(rs.getBoolean("IS_SUBSCRIBER"));
                ticket.setInTime(rs.getTimestamp("IN_TIME").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public boolean updateTicket(Ticket ticket) {
        try (Connection connection = DataBaseManager.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(DbConstants.UPDATE_TICKET_BY_ID)
        ) {
            ps.setDouble(1, ticket.getPrice());
            ps.setTimestamp(2, Timestamp.valueOf(ticket.getOutTime()));
            ps.setInt(3, ticket.getId());
            int updateRowCount = ps.executeUpdate();
            return updateRowCount == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
