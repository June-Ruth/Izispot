package com.demo.constants;

public final class DbConstants {

    public static final String SAVE_TICKET =
            "insert into ticket" +
                    "(VEHICLE_NUMBER, VEHICLE_TYPE, IS_SUBSCRIBER, IN_TIME, OUT_TIME, PRICE)" +
                    "values (?,?,?,?,?,?)";

    public static final String GET_LAST_TICKET_BY_NUMBER_VEHICLE =
            "select * from ticket where VEHICLE_NUMBER = ?" +
            "and OUT_TIME IS NULL LIMIT 1";

    public static final String UPDATE_TICKET_BY_ID =
            "update ticket set PRICE = ?, OUT_TIME = ? where ID = ?";
}
