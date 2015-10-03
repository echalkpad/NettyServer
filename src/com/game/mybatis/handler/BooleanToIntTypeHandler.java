package com.game.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes(Boolean.class)
@MappedJdbcTypes({JdbcType.INTEGER})
public class BooleanToIntTypeHandler implements TypeHandler<Boolean>{

	@Override
	public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return value == 0 ? false : true;
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer value = rs.getInt(columnIndex);
		return value == 0 ? false : true;
	}

	@Override
	public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return value == 0 ? false : true;
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
		if(parameter == false){
			ps.setInt(i, 0);
		}else{
			ps.setInt(i, 1);
		}
	}

	

}
