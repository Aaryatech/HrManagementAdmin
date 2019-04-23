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
						<a href="${pageContext.request.contextPath}/addLeaveAuthority"
							class="breadcrumb-elements-item"> Add Authority</a> <a
							href="${pageContext.request.contextPath}/editLeaveAuthority"
							class="breadcrumb-elements-item"> Edit Authority</a>

					</div>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Leave Application List</h5>
						<div class="header-elements">
							<div class="list-icons">
								<a class="list-icons-item" data-action="collapse"></a>
							</div>
						</div>
					</div>

					<div class="card-body">
					 <ul class="nav nav-tabs nav-tabs-highlight nav-justified1">
									<li class="nav-item"><a href="#highlighted-justified-tab1" class="nav-link active" data-toggle="tab">Pending Task</a></li>
									<li class="nav-item"><a href="#highlighted-justified-tab2" class="nav-link" data-toggle="tab">Information</a></li>
									 
								</ul>

								<div class="tab-content">
									<div class="tab-pane fade show active" id="highlighted-justified-tab1">
										<table	class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="printtable1">
							<thead>
								<tr class="bg-blue">
									<th width="10%">Sr.no</th>
									<th>Employee Code</th>
									<th>Employee Name</th>
									<th>Type</th>
									<th>From Date</th>
									<th>To Date</th>
									<th>No. Of Days</th>
									<th class="text-center" width="10%">Actions</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${leaveListForApproval}" var="leaveList" varStatus="count">
								
									<tr>
										<td>${count.index+1}</td>
										<td>${leaveList.empCode}</td>
										<td>${leaveList.empFname}</td>
										<td>${leaveList.leaveTitle}</td>
										<td>${leaveList.leaveFromdt}</td>
										<td>${leaveList.leaveTodt}</td>
										<td>${leaveList.leaveNumDays}</td>
										
										<td class="text-center">
											<div class="list-icons">
												<div class="dropdown">
													<a href="#" class="list-icons-item" data-toggle="dropdown">
														<i class="icon-menu9"></i>
													</a>

													<div class="dropdown-menu dropdown-menu-right">
														<a
															href="${pageContext.request.contextPath}/approveLeaveByInitialAuth?empId=${leaveList.leaveTypeName}&leaveId=${leaveList.circulatedTo}&stat=2"
															class="dropdown-item"><i class="icon-pencil7"></i>Approve</a>
															
													<a
															href="${pageContext.request.contextPath}/approveLeaveByInitialAuth?empId=${leaveList.leaveTypeName}&leaveId=${leaveList.circulatedTo}&stat=8"
															class="dropdown-item"><i class="icon-pencil7"></i>Reject</a>
														
													</div>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
									</div>

									<div class="tab-pane fade" id="highlighted-justified-tab2">
<table	class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="printtable1">
							<thead>
								<tr class="bg-blue">
									<th width="10%">Sr.no</th>
									<th>Employee Code</th>
									<th>Employee Name</th>
									<th>Type</th>
									<th>From Date</th>
									<th>To Date</th>
									<th>No. Of Days</th>
									<th class="text-center" width="10%">Actions</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${leaveListForApproval1}" var="leaveList1" varStatus="count">
								
									<tr>
										<td>${count.index+1}</td>
										<td>${leaveList1.empCode}</td>
										<td>${leaveList1.empFname}</td>
										<td>${leaveList1.leaveTitle}</td>
										<td>${leaveList1.leaveFromdt}</td>
										<td>${leaveList1.leaveTodt}</td>
										<td>${leaveList1.leaveNumDays}</td>
										
										<td class="text-center">
											<div class="list-icons">
												<div class="dropdown">
													<a href="#" class="list-icons-item" data-toggle="dropdown">
														<i class="icon-menu9"></i>
													</a>

													<div class="dropdown-menu dropdown-menu-right">
														<a
															href="${pageContext.request.contextPath}/approveLeaveByInitialAuth?empId=${leaveList1.leaveTypeName}&leaveId=${leaveList1.circulatedTo}&stat=3"
															class="dropdown-item"><i class="icon-pencil7"></i>Approve</a>
															
													<a
															href="${pageContext.request.contextPath}/approveLeaveByInitialAuth?empId=${leaveList1.leaveTypeName}&leaveId=${leaveList1.circulatedTo}&stat=9"
															class="dropdown-item"><i class="icon-pencil7"></i>Reject</a>
														
													</div>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>										</div>

									 
								</div>

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

	<script type="text/javascript">
		$(document).ready(
				function() {
					$('#bootstrap-data-table-export').DataTable();

					$("#selAll").click(
							function() {
								$('#printtable1 tbody input[type="checkbox"]')
										.prop('checked', this.checked);
							});
				});
	</script>



</body>
</html>