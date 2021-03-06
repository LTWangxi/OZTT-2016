package com.org.oztt.schedue;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.oztt.controller.BaseController;
import com.org.oztt.service.OrderService;

@Controller
@RequestMapping("/cleanOrderInfo")
public class QuartzController extends BaseController {
    
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/cleanorder")
    public void cleanorder() throws Exception {
        logger.info("START THE QUARTZ FOR CLEAN ORDER");
        orderService.cleanOrderInfo();
        logger.info("END   THE QUARTZ FOR CLEAN ORDER");
    }

}
