<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
</head>

<body>

	<!-- Main navbar -->
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<!-- /main navbar -->


	<!-- Page content -->
	<div class="page-content">

		<!-- Main sidebar -->
		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!-- /main sidebar -->


		<!-- Main content -->
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header page-header-light">

 
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					 
					
						
							<div class="card-header header-elements-inline">
 						<table width="100%">
							<tr width="100%">
								<td width="60%"><h5 class="card-title">Customer List</h5></td>
								<td width="40%" align="right">
							  <c:if test="${addAccess == 0}">
								 <a
									href="${pageContext.request.contextPath}/addCustomer"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary">Customer List </button>
								</a> </c:if></td>
							</tr>
						</table>
					</div>

					<div class="card-body">

						<%
							if (session.getAttribute("errorMsg") != null) {
						%>
						<div
							class="alert bg-danger text-white alert-styled-left alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">
								<span>×</span>
							</button>
							<span class="font-weight-semibold">Oh snap!</span>
							<%
								out.println(session.getAttribute("errorMsg"));
							%>
						</div>

						<%
							session.removeAttribute("errorMsg");
							}
						%>
						<%
							if (session.getAttribute("successMsg") != null) {
						%>
						<div
							class="alert bg-success text-white alert-styled-left alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">
								<span>×</span>
							</button>
							<span class="font-weight-semibold">Well done!</span>
							<%
								out.println(session.getAttribute("successMsg"));
							%>
						</div>
						<%
							session.removeAttribute("successMsg");
							}
						%>
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="printtable1">
							<thead>
								<tr class="bg-blue">
									<th width="10%">Sr.no</th>
									<th>Customer Name</th>
									<th>Customer Remark</th>


									<th class="text-center" width="10%">Actions</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${custlist}" var="cust" varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${cust.custName}</td>
									
										<c:choose>
										<c:when test="${cust.custRemarks=='null' || empty cust.custRemarks}">
											<td>-</td>
										</c:when>
										<c:otherwise>
										<td>${cust.custRemarks}</td>
										</c:otherwise>
										</c:choose>	
										<td class="text-center">
										
														<c:if test="${editAccess == 0}">
														<a
															href="${pageContext.request.contextPath}/editCustomer?custId=${cust.exVar1}"
															title="Edit"><i class="icon-pencil7" style="color: black;"></i></a></c:if>
															<c:if
																test="${deleteAccess == 0}">
														<a
															href="${pageContext.request.contextPath}/deleteCustomer?custId=${cust.exVar1}"
															onClick="return confirm('Are you sure want to delete this record');"
															title="Delete"><i class="icon-trash" style="color: black;"></i>
															</a></c:if>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

					</div>

				</div>
				<!-- /highlighting rows and columns -->

			</div>
			<!-- /content area -->


			<!-- Footer -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
			<!-- /footer -->

		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->

</body>
</html>