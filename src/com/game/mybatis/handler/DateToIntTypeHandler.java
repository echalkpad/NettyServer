package com.game.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes(Date.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class DateToIntTypeHandler implements TypeHandler<Date>{

	@Override
	public Date getResult(ResultSet rs, String columnName) throws SQLException {
		Integer time = rs.getInt(columnName);
		return new Date(time);
	}

	@Override
	public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer time = rs.getInt(columnIndex);
		return new Date(time);
	}

	@Override
	public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer time = cs.getInt(columnIndex);
		return new Date(time);
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
		ps.setLong(i, parameter.getTime());
	}
}
