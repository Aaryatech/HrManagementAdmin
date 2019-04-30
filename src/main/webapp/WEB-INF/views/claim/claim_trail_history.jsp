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
						<a href="${pageContext.request.contextPath}/showClaimApprovalByAuthority"
							class="breadcrumb-elements-item">Employee Claim History </a>

					</div>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Claim Trail History</h5>
						<div class="header-elements">
							<div class="list-icons">
								<a class="list-icons-item" data-action="collapse"></a>
							</div>
						</div>
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
								session.removeAttribute("errorMsg");
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
								session.removeAttribute("successMsg");
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
									<th>Name</th>								
									<th>Remark</th>
									<th>Date</th>
									<th>Action By</th>
									<th>Claim Status</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${employeeList}" var="empTrailList" varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${empTrailList.empSname} ${empTrailList.empFname} </td>
									
										
										<c:choose>
										<c:when test="${empTrailList.empRemarks=='null' || empty empTrailList.empRemarks}">
											<td>-</td>
										</c:when>
										<c:otherwise>
										<td>${empTrailList.empRemarks}</td>
										</c:otherwise>
										</c:choose>									
										<td>${empTrailList.makerEnterDatetime}</td>
										<td>${empTrailList.userName}</td>
										<c:if test="${empTrailList.claimStatus==1}">
										<td><span class="badge badge-info">Initial Applied</span></td>
										</c:if>
										<c:if test="${empTrailList.claimStatus==2}">
										<td><span class="badge badge-secondary">Approve By Initial Authority</span></td>
										</c:if>
											<c:if test="${empTrailList.claimStatus==3}">
										<td><span class="badge badge-success">Approve By Final Authority</span></td>
										</c:if>
											<c:if test="${empTrailList.claimStatus==7}">
										<td><span class="badge badge-danger">Cancel By Employee</span></td>
										</c:if>
											<c:if test="${empTrailList.claimStatus==8}">
										<td><span class="badge badge-danger">Reject By Initial Authority</span></td>
										</c:if>
											<c:if test="${empTrailList.claimStatus==9}">
										<td><span class="badge badge-danger">Reject By Final Authority</span></td>
										</c:if>
	
									<%-- 	<td class="text-center">
											<div class="list-icons">
												<div class="dropdown">
													<a href="#" class="list-icons-item" data-toggle="dropdown">
														<i class="icon-menu9"></i>
													</a>

													<div class="dropdown-menu dropdown-menu-right">
														<a
															href="${pageContext.request.contextPath}/editEmp?typeId=${lvTypeList.exVar1}"
															class="dropdown-item"><i class="icon-pencil7"></i>Edit</a>
														<a
															href="${pageContext.request.contextPath}/deleteEmployee?typeId=${lvTypeList.exVar1}"
															class="dropdown-item"><i class="icon-trash"></i>
															Delete</a> <a
															href="${pageContext.request.contextPath}/uploadDocument?empId=${lvTypeList.exVar1}"
															class="dropdown-item"><i class="icon-file-upload"></i> 
															Document upload</a>

													</div>
												</div>
											</div>
										</td> --%>
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