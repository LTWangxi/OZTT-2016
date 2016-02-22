package com.org.oztt.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.oztt.contants.CommonConstants;
import com.org.oztt.contants.CommonEnum;
import com.org.oztt.entity.TConsOrder;
import com.org.oztt.formDto.OzTtAdOdDto;
import com.org.oztt.service.CommonService;
import com.org.oztt.service.OrderService;

/**
 * 订单详细画面
 * 
 * @author linliuan
 */
@Controller
@RequestMapping("/OZ_TT_AD_GD")
public class OzTtAdGdController extends BaseController {

    @Resource
    private CommonService commonService;

    @Resource
    private OrderService  orderService;

    /**
     * 订单详细画面
     * 
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(Model model, HttpServletRequest request, HttpSession session, String orderNo, String pageNo) {
        try {
            OzTtAdOdDto ozTtAdOdDto = orderService.getOrderDetailForAdmin(orderNo);
            model.addAttribute("ozTtAdOdDto", ozTtAdOdDto);
            model.addAttribute("pageNo", pageNo);
            return "OZ_TT_AD_OD";
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return CommonConstants.ERROR_PAGE;
        }
    }

    /**
     * 订单改变状态
     * 
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/changeStatus")
    public String changeStatus(Model model, HttpServletRequest request, HttpSession session, String orderNo,
            String status, String pageNo) {
        try {
            if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(status)) {
                throw new Exception();
            }
            if (CommonEnum.HandleFlag.NOT_PAY.getCode().equals(status)) {
                // 如果是未付款的情况下
                orderService.updateRecordAfterPay(orderNo, "ADMIN");
            }
            else {
                // 检索当前订单，更新状态为已经付款
                TConsOrder tConsOrder = orderService.selectByOrderId(orderNo);
                tConsOrder.setHandleflg(status);
                orderService.updateOrderInfo(tConsOrder);
            }
            return "redirect:/OZ_TT_AD_OD/init?orderNo=" + orderNo + "&pageNo=" + pageNo;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return CommonConstants.ERROR_PAGE;
        }
    }

}
