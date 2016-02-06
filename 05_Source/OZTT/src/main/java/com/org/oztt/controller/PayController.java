package com.org.oztt.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.oztt.contants.CommonConstants;
import com.org.oztt.contants.CommonEnum;
import com.org.oztt.entity.TConsOrder;
import com.org.oztt.formDto.PaypalParam;
import com.org.oztt.service.OrderService;
import com.org.oztt.service.PaypalService;

@Controller
@RequestMapping("/Pay")
public class PayController extends BaseController {

    @Resource
    private PaypalService paypalService;

    @Resource
    private OrderService  orderService;

    /**
     * 付款
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "payment")
    public String payment(Model model, HttpServletResponse response, HttpSession session, String hidDeliMethod,
            String hidAddressId, String hidPayMethod) {
        try {
            String customerNo = (String) session.getAttribute(CommonConstants.SESSION_CUSTOMERNO);
            // 先判断付款方式
            String rb = orderService.insertOrderInfo(customerNo, hidPayMethod, hidDeliMethod, hidAddressId);
            if (!StringUtils.isEmpty(rb)) {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(rb);
                return null;
            }
            if (CommonEnum.DeliveryMethod.COD.getCode().equals(hidDeliMethod)) {
                // 货到付款
                return "redirect:/Notice/codNotice";
            }
            return null;

        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return CommonConstants.ERROR_PAGE;
        }
    }

    /**
     * 已经有订单，直接付款
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "paymentHasOrder")
    public String paymentHasOrder(Model model, HttpServletResponse response, HttpSession session, String hidDeliMethod,
            String hidAddressId, String hidPayMethod, String orderId) {
        try {
            TConsOrder tConsOrder = orderService.selectByOrderId(orderId);
            // 先判断付款方式
            String rb = "";
            if (CommonEnum.PaymentMethod.PAYPAL.getCode().equals(hidPayMethod)) {
                // 货到付款
                PaypalParam paypalParam = new PaypalParam();
                paypalParam.setOrderId(orderId);
                if (CommonEnum.DeliveryMethod.NORMAL.getCode().equals(hidDeliMethod)) {
                    // 普通快递
                    paypalParam.setPrice(tConsOrder.getOrderamount().add(tConsOrder.getDeliverycost()).toString());
                }
                else if (CommonEnum.DeliveryMethod.SELF_PICK.getCode().equals(hidDeliMethod)) {
                    // 来店自提
                    paypalParam.setPrice(tConsOrder.getOrderamount().toString());
                }
                paypalParam.setNotifyUrl(getApplicationMessage("notifyUrl") + orderId); //这里是不是通知画面，做一些对数据库的更新操作等
                paypalParam.setCancelReturn(getApplicationMessage("cancelReturn") + orderId);//应该返回未完成订单画面订单画面
                paypalParam.setOrderInfo(getApplicationMessage("orderInfo"));
                paypalParam.setReturnUrl(getApplicationMessage("returnUrl"));// 同样是当前订单画面
                rb = paypalService.buildRequest(paypalParam);
            }
            if (!StringUtils.isEmpty(rb)) {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(rb);
                return null;
            }

            return null;

        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return CommonConstants.ERROR_PAGE;
        }
    }

    /**
     * 付款后的通知
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "paypalNotify")
    public String paypalNotify(Model model, HttpServletResponse response, HttpSession session,
            @RequestParam String orderId) {
        try {
            String customerNo = (String) session.getAttribute(CommonConstants.SESSION_CUSTOMERNO);
            orderService.updateRecordAfterPay(orderId, customerNo);
            return "redirect:/OZ_TT_GB_OL/itemList?clearCont=1";
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return CommonConstants.ERROR_PAGE;
        }
    }

}
