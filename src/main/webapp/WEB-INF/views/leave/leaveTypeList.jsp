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


				<div
					class="breadcrumb-line breadcrumb-line-light header-elements-md-inline">
					<div class="d-flex">
						<div class="breadcrumb">
							<a href="index.html" class="breadcrumb-item"><i
								class="icon-home2 mr-2"></i> Home</a> <span
								class="breadcrumb-item active">Dashboard</span>
						</div>

						<a href="#" class="header-elements-toggle text-default d-md-none"><i
							class="icon-more"></i></a>
							
							
							
					</div>


					<div class="breadcrumb justify-content-center">
						<c:if test="${addAccess == 0}">
						<a href="${pageContext.request.contextPath}/leaveTypeAdd"
							class="breadcrumb-elements-item"> Add Leave Type </a>
</c:if>
					</div>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Leave Type List</h5>
					<!-- 	<div class="header-elements">
							<div class="list-icons">
								<a class="list-icons-item" data-action="collapse"></a>
							</div>
						</div> -->
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
									<th>Leave Type</th>
									<th>Leave Short Name</th>
									<th>Working Hours</th>
									 
									<th class="text-center" width="10%">Actions</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${lvTypeList}" var="lvTypeList" varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${lvTypeList.lvTitle}</td>
										<td>${lvTypeList.lvTitleShort}</td>
										<td>${lvTypeList.lvWorkingHrs}</td>
										 
										
										<td class="text-center">
										
													<c:if test="${editAccess == 0}">
														<a
															href="${pageContext.request.contextPath}/editLeaveType?typeId=${lvTypeList.exVar1}"
															title="Edit"><i class="icon-pencil7" style="color: black;"></i></a></c:if>
															<c:if
																test="${deleteAccess == 0}">
														<a
															href="${pageContext.request.contextPath}/deleteLeaveType?typeId=${lvTypeList.exVar1}"
															onClick="return confirm('Are you sure want to Delete this Record');"
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