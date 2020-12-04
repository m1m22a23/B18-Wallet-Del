package oit.is.group8.b18wallet.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SpendMapper {

  @Select("SELECT * from spend")
  ArrayList<Spend> getAllSpend();

  @Insert("INSERT INTO spend (user_id, date, money, memo) VALUES (#{user_id},#{date},#{money},#{memo});")
  void insertSpend(Spend spend);
}
