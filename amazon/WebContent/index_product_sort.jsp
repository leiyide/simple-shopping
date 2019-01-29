<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="p_category">
	<h2>商品分类</h2>


	<c:set var="categoryInfo" value="${requestScope.parent }"></c:set>

	<c:forEach items="${categoryInfo }" var="c">
		<dl>
			<dt>
				<a href="indexServlet?cate=parent&hpcId=${c.id  }">${c.name}</a>
			</dt>
			<c:forEach items="${child[c.id] }" var="category">
				<dd>
					<a href="indexServlet?cate=child&hpcId=${category.id }">${category.name }</a>
				</dd>
			</c:forEach>
		</dl>
	</c:forEach>


</div>

