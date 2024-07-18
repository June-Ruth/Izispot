package com.demo.constants;

public final class DbConstants {

    public static final String SAVE_TICKET =
            "insert into ticket" +
                    "(VEHICLE_NUMBER, VEHICLE_TYPE, IS_SUBSCRIBER, IN_TIME, OUT_TIME, PRICE)" +
                    "values (?,?,?,?,?,?)";
}
