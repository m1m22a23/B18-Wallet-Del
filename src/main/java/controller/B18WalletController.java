package oit.is.group8.b18wallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class B18WalletController {

  /**
   * sample21というGETリクエストがあったら，sample21()を呼び出して，sample21.htmlを返すメソッド
   *
   * @return
   */
  @GetMapping("/output")
  public String home(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "home.html";

  }

  @GetMapping("")
  public String home() {
    return "home.html";
  }

}
