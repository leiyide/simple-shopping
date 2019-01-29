
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊-首页</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="middle">
		<div class="p_left">
			<!--商品分类-->
			<%@ include file="index_product_sort.jsp"%>
			<!--商品分类结束-->

			<div class="pre_look">
				<h3>最近浏览</h3>
				<dl>
					<c:forEach items="${sessionScope.viewedProduct }" var="p" end="3">
						<dt>
							<img style="width: 54px; height: 54px;" src="${p.hpFileName }" />
						</dt>
						<dd>
							<a href="pview?pId=${p.hpId }">${p.hpName }</a>
						</dd>
					</c:forEach>
				</dl>
			</div>
		</div>

		<div class="p_center">
			<div id="wrapper">
				<div id="focus">
					<ul>
						<li><a href="#"><img src="images/T1.jpg" /></a></li>
						<li><a href="#"><img src="images/T2.jpg" /></a></li>
						<li><a href="#"><img src="images/T3.jpg" /></a></li>
						<li><a href="#"><img src="images/T4.jpg" /></a></li>
						<li><a href="#"><img src="images/T5.jpg" /></a></li>
					</ul>
				</div>
			</div>
			<div class="p_list">
				<div class="p_info">
					<img src="images/icon_sale.png"/>商品列表 
				</div>
			</div>

			<ul class="product2">
				<c:if test="${requestScope.pageModel.totalPage==0}">
					<h4>找不到您搜索的商品！</h4>
				</c:if>
				<c:set value="${requestScope.Product}" var="products"></c:set>
				<c:forEach items="${products}" var="p">
					<li>
						<dl>
							<dt>
								<a href="productInfo?pId=${p.id }" target="_self"><img
									src="${p.file }" /></a>
							</dt>
							<dd class="title">
								<a href="pview?pId=${p.id}" target="_self">${p.name }</a>
							</dd>
							<dd class="price">￥${p.price}</dd>
						</dl>
					</li>

				</c:forEach>


			</ul>
			<!--分页-->
			<div class="pager">
				<ul>
					<ul>
						<li><a href="<%=request.getContextPath()%>/indexServlet?cate=${cate}&hpcId=${hpcId}&currentPage=${(pageView.currentPage-1)<1?1:(pageView.currentPage-1)}" id="lastPage">上一页</a></li>
						<c:forEach items="${requestScope.pageView.recordList}" var="pl">
							<li><a
								href="<%=request.getContextPath()%>/indexServlet?cate=${cate}&hpcId=${hpcId}&currentPage=${pl }&hpcId=${requestScope.pageModel.hpcId}">${pl }</a></li>
						</c:forEach>
						<li><a href="<%=request.getContextPath()%>/indexServlet?cate=${cate}&hpcId=${hpcId}&currentPage=${(pageView.currentPage+1)<1?pageView.pageCount:(pageView.currentPage+1)}" id="nextPage">下一页</a></li>
					</ul>
				</ul> 
				<input type="hidden" id="source"
					value="${requestScope.pageModel.source}" /><input type="hidden"
					id="hpcId" value="${requestScope.pageModel.hpcId}" /> <input
					type="hidden" id="totalPage"
					value="${requestScope.pageModel.totalPage}" /> <input
					type="hidden" id="currentPage"
					value="${requestScope.pageModel.currentPage}" /> <input
					type="hidden" id="queryName"
					value="${requestScope.pageModel.qname}" />
			</div>
		</div>


		<div id="p_right">
			<%@ include file="index_news.jsp"%>
			<%@ include file="hotproduct.jsp"%>
		</div>
	</div>

	<div id="foot">Copyright © 2016 上海海文 All Rights Reserved.</div>
</body>
</html>

