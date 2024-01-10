package com.rabbit.todoapi.build;

import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.MySQLDialect;

public class MySQLDialectUTF8 extends MySQLDialect {
    private static final int MY_SQL_DIALECT_VERSION_MAJOR = 8;

    public MySQLDialectUTF8() {
        super(DatabaseVersion.make(MY_SQL_DIALECT_VERSION_MAJOR));
    }
    @Override
    public String getTableTypeString(){
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";
    }
}
