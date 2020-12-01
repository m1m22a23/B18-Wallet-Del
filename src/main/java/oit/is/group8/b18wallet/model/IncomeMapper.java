package oit.is.group8.b18wallet.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IncomeMapper {

  @Select("SELECT * from income")
  ArrayList<Income> getAllIncome();

  @Insert("INSERT INTO income (user_id, date, money, memo) VALUES (#{user_id},#{date},#{money},#{memo});")
  void insertIncome(Income income);
}
