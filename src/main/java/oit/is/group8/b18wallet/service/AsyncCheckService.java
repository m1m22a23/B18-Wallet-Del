package oit.is.group8.b18wallet.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.group8.b18wallet.model.Income;
import oit.is.group8.b18wallet.model.IncomeMapper;
import oit.is.group8.b18wallet.model.Spend;
import oit.is.group8.b18wallet.model.SpendMapper;

@Service
public class AsyncCheckService {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncCheckService.class);

  @Autowired
  IncomeMapper incomeMapper;
  @Autowired
  SpendMapper spendMapper;

  /**
   *
   * @param id
   * @return
   */
  @Transactional
  public Income syncdeleteIncome(int id) {
    Income income = incomeMapper.selectById(id);

    // 削除
    incomeMapper.deleteById(id);

    this.dbUpdated = true;

    return income;
  }

  /**
   *
   * @param id
   * @return
   */
  @Transactional
  public Spend syncdeleteSpend(int id) {
    Spend spend = spendMapper.selectById(id);

    // 削除
    spendMapper.deleteById(id);

    this.dbUpdated = true;

    return spend;
  }

}
