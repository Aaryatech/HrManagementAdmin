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
						<%-- <a href="${pageContext.request.contextPath}/holidayAdd"
							class="breadcrumb-elements-item"> Add Holiday </a>
 --%>
					</div>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Employee Leave List</h5>
						<!-- <div class="header-elements">
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

									<th width="10%">Sr. No.</th>
									<th>Leave Title</th>
									<th>From Date</th>
									<th>To Date</th>
									<th>Duration</th>
									<th>No. of Days</th>
									<th>Reason</th>
									<th>Status</th>
								
									<th width="10%" class="text-center">Actions</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${leaveHistoryList}" var="holiday" varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${holiday.lvTitle}</td>
										<td>${holiday.leaveFromdt}</td>
										<td>${holiday.leaveTodt}</td>
										<c:if test="${holiday.leaveDuration==1}">
										<td>Full Day</td></c:if>
										<c:if test="${holiday.leaveDuration==2}">
										<td>Half Day</td></c:if>
										
										<td>${holiday.leaveNumDays}</td>
										
										<td>${holiday.leaveEmpReason}</td>
										<c:if test="${holiday.exInt1==1}">
										<td><span class="badge badge-info">Initial Pending</span></td>
										</c:if>
										<c:if test="${holiday.exInt1==2}">
										<td><span class="badge badge-secondary">Final Pending</span></td>
										</c:if>
											<c:if test="${holiday.exInt1==3}">
										<td><span class="badge badge-success">Final Approved</span></td>
										</c:if>
											<c:if test="${holiday.exInt1==7}">
										<td><span class="badge badge-danger"> Leave Cancelled</span></td>
										</c:if>
											<c:if test="${holiday.exInt1==8}">
										<td><span class="badge badge-danger">Initial Rejected</span></td>
										</c:if>
											<c:if test="${holiday.exInt1==9}">
										<td><span class="badge badge-danger">Final Reject</span></td>
										</c:if>
										<td class="text-center">
											
														<a
															href="${pageContext.request.contextPath}/showLeaveHistDetailList?leaveId=${holiday.exVar1}"
															><i class="icon-history" style="color: black;"></i></a>
													
												
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