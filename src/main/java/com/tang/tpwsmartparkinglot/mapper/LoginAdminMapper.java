package com.tang.tpwsmartparkinglot.mapper;

import com.tang.tpwsmartparkinglot.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LoginAdminMapper {
    @Select("select * from admin where username = #{username} ")
    Admin getAdmin(String username);
    @Update("update admin set password = #{newPassword} where username = #{username}")
    Integer updatePassword(@Param("newPassword") String newPassword, @Param("username") String username);
}
