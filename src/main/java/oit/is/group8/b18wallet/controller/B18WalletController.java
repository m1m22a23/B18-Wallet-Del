package oit.is.group8.b18wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

import oit.is.group8.b18wallet.model.Income;
import oit.is.group8.b18wallet.model.IncomeMapper;
import oit.is.group8.b18wallet.model.Spend;
import oit.is.group8.b18wallet.model.SpendMapper;

@Controller
// @RequestMapping("/home")
public class B18WalletController {
  @Autowired
  IncomeMapper incomeMapper;
  @Autowired
  SpendMapper spendMapper;

  /**
   * sample21というGETリクエストがあったら，sample21()を呼び出して，sample21.htmlを返すメソッド
   *
   * @return
   */
  /*
   * @GetMapping("/output") public String home(@RequestParam String name, ModelMap
   * model) { model.addAttribute("name", name); return "home.html";
   *
   * }
   */

  @GetMapping("/home")
  public String home() {
    return "home.html";
  }

  @GetMapping("/income")
  public String income() {
    return "income.html";
  }

  @GetMapping("/spend")
  public String spend() {
    return "spend.html";
  }

  @GetMapping("/check")
  public String check(ModelMap model) {
    ArrayList<Income> income = incomeMapper.getAllIncome();
    ArrayList<Spend> spend = spendMapper.getAllSpend();
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }

}
