<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="hot_sale">
	<h2>热卖推荐</h2>
	<ul>
		<c:if test="${requestScope.hotProducts!=null }">
			<c:forEach items="${requestScope.hotProducts }" var="p" end="4">
				<li>
					<dl>
						<dt>
							<a href="pview?pId=${p.hpId }" target="_self"><img
								src="${p.hpFileName }" /></a>
						</dt>
						<dd class="p_name">
							<a href="pview?pId=${p.hpId }" target="_self">${p.hpName }</a>
						</dd>
						<dd class="price">￥${p.hpPrice }</dd>
					</dl>
				</li>
			</c:forEach>
		</c:if>


	</ul>
</div>
