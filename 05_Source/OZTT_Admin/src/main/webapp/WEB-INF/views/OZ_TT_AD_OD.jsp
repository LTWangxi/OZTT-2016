<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title><fmt:message key="OZ_TT_AD_OL_title" /></title>
  
  <script type="text/javascript">
	function changeOrderStatus(orderNo, status) {
		var pageNo = $("#hiddenPageNo").val();
		location.href="${pageContext.request.contextPath}/OZ_TT_AD_OD/changeStatus?orderNo="+orderNo+"&status="+status+"&pageNo="+pageNo;
	}
	
	function backToOrderList(){
		var pageNo = $("#hiddenPageNo").val();
		location.href= "${pageContext.request.contextPath}/OZ_TT_AD_OL/pageSearch?pageNo="+pageNo;
	}
  
  </script>
</head>
<body>
	<!-- BEGIN CONTENT -->

	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="#">
								<fmt:message key="COMMON_HOME" />
							</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">
								<fmt:message key="OZ_TT_AD_MN_order" />
							</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">
								<fmt:message key="OZ_TT_AD_OD_menu" />
							</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<form:form cssClass="form-horizontal" action="" method="post" id="olForm" modelAttribute="ozTtAdOdDto" commandName="ozTtAdOdDto" role="form">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-1 control-label textleft"><fmt:message key="OZ_TT_AD_OD_orderNo" /></label>
					<div class="col-md-3">
						<label class="control-label textleft">${ozTtAdOdDto.orderNo}</label>
					</div>
					<label class="col-md-1 control-label textleft"><fmt:message key="OZ_TT_AD_OD_customer" /></label>
					<div class="col-md-3">
						<label class="control-label textleft">${ozTtAdOdDto.customerNo}</label>
					</div>
					
					<label class="col-md-1 control-label textleft"><fmt:message key="OZ_TT_AD_OD_orderStatus" /></label>
					<div class="col-md-3">
						<label class="control-label textleft">${ozTtAdOdDto.orderStatusView}</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-1 control-label textleft"><fmt:message key="OZ_TT_AD_OD_receiver" /></label>
					<div class="col-md-3">
						<label class="control-label textleft">${ozTtAdOdDto.receiver}</label>
					</div>
					
					<label class="col-md-1 control-label textleft"><fmt:message key="OZ_TT_AD_OD_phone" /></label>
					<div class="col-md-3">
						<label class="control-label textleft">${ozTtAdOdDto.phone}</label>
					</div>
					<label class="col-md-1 control-label textleft"><fmt:message key="OZ_TT_AD_OD_receaddress" /></label>
					<div class="col-md-3">
						<label class="control-label textleft">${ozTtAdOdDto.receAddress}</label>
					</div>
					
					
				</div>
				
				<div class="form-group">
					<label class="col-md-1 control-label textleft"><fmt:message key="OZ_TT_AD_OD_yunfei" /></label>
					<div class="col-md-3">
						<label class="control-label textleft">${ozTtAdOdDto.yunfei}</label>
					</div>
					<label class="col-md-1 control-label"><fmt:message key="OZ_TT_AD_OD_wantarrivetime" /></label>
					<div class="col-md-3">
						<label class="control-label textleft">${ozTtAdOdDto.wantArriveTime}</label>
					</div>
					
					
					<div class="col-md-4">
						
					</div>
				</div>
				
				<h4 class="form-section"></h4>
				
				<div class="table-scrollable">
					<table class="table table-striped table-bordered table-hover">
					<thead>
					<tr>
						<th scope="col">
							 <fmt:message key="COMMON_NUM" />
						</th>
						<th scope="col">
							 <fmt:message key="OZ_TT_AD_OD_DE_goodsId" />
						</th>
						<th scope="col">
							 <fmt:message key="OZ_TT_AD_OD_DE_goodsName" />
						</th>
						<th scope="col">
							 <fmt:message key="OZ_TT_AD_OD_DE_goodsPrice" />
						</th>
						<th scope="col">
							 <fmt:message key="OZ_TT_AD_OD_DE_goodsQuantity" />
						</th>
						<th scope="col">
							 <fmt:message key="OZ_TT_AD_OD_DE_goodsTotalAmount" />
						</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="orderItem" items="${ ozTtAdOdDto.itemList }">
					<tr>
						<td>
							 ${orderItem.detailNo }
						</td>
						<td>
							 ${orderItem.goodsId }
						</td>
						<td>
							 ${orderItem.goodsName }
						</td>
						<td>
							 ${orderItem.goodsPrice }
						</td>
						<td>
							 ${orderItem.goodsQuantity }
						</td>
						<td>
							 ${orderItem.goodsTotalAmount }
						</td>	
					</tr>
					</c:forEach>
					</tbody>
					</table>
				</div>
				
			</div>
			
			</form:form>
			
			<h4 class="form-section"></h4>
			
			<div class="row" style="padding:15px">
				
				<div class="col-md-8 textleft">
					<c:if test="${ ozTtAdOdDto.orderStatus == '0' }">
					<button type="button" class="btn green mybtn" onclick="changeOrderStatus('${ozTtAdOdDto.orderNo}','1')"><i class="fa fa-check"></i><fmt:message key="OZ_TT_AD_OD_changeToPaySu" /></button>
					<span class="spanTip"><fmt:message key="OZ_TT_AD_OD_changeToPaySuTip" /></span>
					</c:if>
					<c:if test="${ ozTtAdOdDto.orderStatus == '1' }">
						<button type="button" class="btn green mybtn" onclick="changeOrderStatus('${ozTtAdOdDto.orderNo}','2')"><i class="fa fa-check"></i><fmt:message key="OZ_TT_AD_OD_inControl" /></button>
					
						<span class="spanTip"><fmt:message key="OZ_TT_AD_OD_inControlTip" /></span>
					</c:if>
					<c:if test="${ ozTtAdOdDto.orderStatus == '2' }">
						<button type="button" class="btn green mybtn" onclick="changeOrderStatus('${ozTtAdOdDto.orderNo}','3')"><i class="fa fa-check"></i><fmt:message key="OZ_TT_AD_OD_controled" /></button>
					
						<span class="spanTip"><fmt:message key="OZ_TT_AD_OD_controledTip" /></span>
					</c:if>
				</div>
				<div class="col-md-4 textright">
					<button type="button" class="btn green mybtn" onclick="backToOrderList()"><i class="fa fa-reply"></i><fmt:message key="COMMON_BACK" /></button>
				</div>
				
			
			</div>
		</div>
	</div>
	<!-- END CONTENT -->
	<input type="hidden" value="${pageNo}" id="hiddenPageNo" ></input>
</body>
</html>