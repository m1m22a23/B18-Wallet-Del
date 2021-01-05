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
import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.group8.b18wallet.model.Income;
import oit.is.group8.b18wallet.model.IncomeMapper;
import oit.is.group8.b18wallet.model.Spend;
import oit.is.group8.b18wallet.model.SpendMapper;
import oit.is.group8.b18wallet.service.AsyncCheckService;

@Controller
@RequestMapping("/check")
public class CheckController {
  @Autowired
  IncomeMapper incomeMapper;
  @Autowired
  SpendMapper spendMapper;
  @Autowired
  AsyncCheckService checkService;

  /**
   * sample21というGETリクエストがあったら，sample21()を呼び出して，sample21.htmlを返すメソッド
   *
   * @return
   */

  @GetMapping("step1")
  public String check(ModelMap model) {
    final ArrayList<Income> income = checkService.syncShowIncomeList();
    final ArrayList<Spend> spend = checkService.syncShowSpendList();
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }

  @GetMapping("step2")
  @Transactional
  public String check_income_D(@RequestParam Integer id, ModelMap model) {
    Income income2 = incomeMapper.selectById(id);
    model.addAttribute("income2", income2);

    incomeMapper.deleteById(id);

    final ArrayList<Income> income = checkService.syncShowIncomeList();
    final ArrayList<Spend> spend = checkService.syncShowSpendList();
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }

  @GetMapping("step3")
  @Transactional
  public String check_spend_D(@RequestParam Integer id, ModelMap model) {
    Spend spend2 = spendMapper.selectById(id);
    model.addAttribute("spend2", spend2);

    spendMapper.deleteById(id);

    ArrayList<Income> income = incomeMapper.getAllIncome();
    ArrayList<Spend> spend = spendMapper.getAllSpend();
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }

  @GetMapping("step4/1")
  @Transactional
  public String check_income_G(@RequestParam Integer id, ModelMap model) {
    // 編集対象の収入を取得
    Income income4 = incomeMapper.selectById(id);
    model.addAttribute("income4", income4);

    // 収入リストを取得
    ArrayList<Income> income = incomeMapper.getAllIncome();
    ArrayList<Spend> spend = spendMapper.getAllSpend();
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }

  @GetMapping("step4/2")
  @Transactional
  public String check_spend_G(@RequestParam Integer id, ModelMap model) {
    // 編集対象の収入を取得
    Spend spend4 = spendMapper.selectById(id);
    model.addAttribute("spend4", spend4);

    // 収入リストを取得
    ArrayList<Income> income = incomeMapper.getAllIncome();
    ArrayList<Spend> spend = spendMapper.getAllSpend();
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }

  /**
   * IDをクエリParamで，果物の名前と値段をフォームで受け取り，DBを更新する
   *
   * @param date
   * @param money
   * @param memo
   * @param model
   * @return
   */
  @PostMapping("step5/1")
  public String check_income_U(@RequestParam Integer id, @RequestParam String date, @RequestParam Integer money,
      @RequestParam String memo, ModelMap model, Principal prin) {
    // String loginUser = prin.getName();
    Income income5 = new Income(id, date, money, memo);
    // update
    incomeMapper.updateById(income5);
    model.addAttribute("income5", income5);
    // 収入リストを取得
    ArrayList<Income> income = incomeMapper.getAllIncome();
    ArrayList<Spend> spend = spendMapper.getAllSpend();
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }

  /**
   * IDをクエリParamで，果物の名前と値段をフォームで受け取り，DBを更新する
   *
   * @param date
   * @param money
   * @param memo
   * @param model
   * @return
   */
  @PostMapping("step5/2")
  public String check_spend_U(@RequestParam Integer id, @RequestParam String date, @RequestParam Integer money,
      @RequestParam String memo, ModelMap model, Principal prin) {
    // String loginUser = prin.getName();
    Spend spend5 = new Spend(id, date, money, memo);
    // update
    spendMapper.updateById(spend5);
    model.addAttribute("spend5", spend5);
    // 収入リストを取得
    ArrayList<Income> income = incomeMapper.getAllIncome();
    ArrayList<Spend> spend = spendMapper.getAllSpend();
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }

  /**
   * @param date_ref
   * @param model
   * @return
   */
  /*@GetMapping("step6")
  public String check_date_ref(@RequestParam String date_ref, ModelMap model) {
    ArrayList<Income> income = incomeMapper.getAllByDate(date_ref);
    ArrayList<Spend> spend = spendMapper.getAllByDate(date_ref);
    model.addAttribute("incomes", income);
    model.addAttribute("spends", spend);
    return "check.html";
  }*/

  @GetMapping("step7")
  public SseEmitter service_income() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.checkService.asyncShowIncomeList(sseEmitter);
    return sseEmitter;
  }

  @GetMapping("step8")
  public SseEmitter service_spend() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.checkService.asyncShowSpendList(sseEmitter);
    return sseEmitter;
  }

}
