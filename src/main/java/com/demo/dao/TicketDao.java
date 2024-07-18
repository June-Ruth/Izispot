package com.demo.dao;

import com.demo.configuration.DataBaseManager;
import com.demo.constants.DbConstants;
import com.demo.model.Ticket;

import java.sql.*;

public class TicketDao {

    public boolean saveTicket(Ticket ticket) {
        try(Connection connection = DataBaseManager.INSTANCE.getConnection();
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
}
