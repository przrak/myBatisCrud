package com.pr1zrak.crud.mappers;

import org.apache.ibatis.jdbc.SQL;

public class MyBatisUtil {

    public String getUserByName(String name){
            return new SQL() {{
                SELECT("*");
                FROM("person");
                WHERE("name like #{name} || '%'");
            }}.toString();
        }

}
