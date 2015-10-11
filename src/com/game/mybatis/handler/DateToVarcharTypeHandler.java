package com.game.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

//@MappedTypes(Date.class)
//@MappedJdbcTypes(JdbcType.INTEGER)
public class DateToVarcharTypeHandler implements TypeHandler<Date>{

	@Override
	public Date getResult(ResultSet rs, String columnName) throws SQLException {
		String timestamp = rs.getString(columnName);
		if (timestamp != null && timestamp != "") {
          return new Date(Long.parseLong(timestamp));
        }
        return null;
	}

	@Override
	public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
		String timestamp = rs.getString(columnIndex);
		if (timestamp != null && timestamp != "") {
          return new Date(Long.parseLong(timestamp));
        }
        return null;
	}

	@Override
	public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String timestamp = cs.getString(columnIndex);
		if (timestamp != null && timestamp != "") {
          return new Date(Long.parseLong(timestamp));
        }
        return null;
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
		if(parameter == null){
			ps.setString(i, null);
		}else{
			ps.setString(i, String.valueOf(parameter.getTime()));	
		}
	}
}
