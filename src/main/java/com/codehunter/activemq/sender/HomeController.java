package com.codehunter.activemq.sender;

import com.codehunter.activemq.sdo.ICheckingAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  Logger log = LoggerFactory.getLogger(HomeController.class);
  private final ICheckingAccountService checkingAccountService;

  public HomeController(ICheckingAccountService checkingAccountService) {
    this.checkingAccountService = checkingAccountService;
  }

  @GetMapping("/get-data")
  public String getData() {
    log.info("Get data via ActiveMQ");
    String result = checkingAccountService.getData();
    log.info("Data return: " + result);
    return result;
  }


}
