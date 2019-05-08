<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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




				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">

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
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Claim List</h5>
						<!-- <div class="header-elements">
							<div class="list-icons">
								<a class="list-icons-item" data-action="collapse"></a>
							</div>
						</div> -->
					</div>

					<div class="card-body">
						<ul class="nav nav-tabs nav-tabs-highlight nav-justified1">
							<li class="nav-item"><a href="#highlighted-justified-tab1"
								class="nav-link active" data-toggle="tab">Pending
									Task(${list1Count})</a></li>
							<li class="nav-item"><a href="#highlighted-justified-tab2"
								class="nav-link" data-toggle="tab">Information(${list2Count})</a></li>

						</ul>



						<div class="tab-content">
							<div class="tab-pane fade show active"
								id="highlighted-justified-tab1">
								<table
									class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
									id="printtable1">
									<thead>
										<tr class="bg-blue">
											<th width="10%">Sr.no</th>
											<th>Employee Code</th>
											<th>Employee Name</th>
											<th>Type</th>
											<th>Claim Date</th>
											<th>Claim Amount</th>
											<th>Project</th>
											<th>Status</th>

											<th class="text-center" width="10%">Actions</th>
										</tr>
									</thead>
									<tbody>


										<c:forEach items="${claimListForApproval}" var="claimList"
											varStatus="count">

											<tr>
												<td>${count.index+1}</td>
												<td>${claimList.empCode}</td>
												<td>${claimList.empName}</td>
												<td>${claimList.claimTypeName}</td>
												<td>${claimList.claimDate}</td>
												<td>${claimList.claimAmount}</td>
												<td>${claimList.projectTitle}</td>


												<c:choose>
													<c:when test="${claimList.exInt1==1}">
														<td><span class="badge badge-info">Initial
																Applied</span></td>
													</c:when>
													<c:when test="${claimList.exInt1==2}">
														<td><span class="badge badge-secondary">Initial
																Approved</span></td>
													</c:when>

												</c:choose>

												<td class="text-center"><c:choose>
														<c:when test="${claimList.caFinAuthEmpId==empIdOrig}">

															<a
																href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList.claimRemarks}&claimId=${claimList.circulatedTo}&stat=3"
																title="Approve"><i class="icon-checkmark4 "
																style="color: black;"></i></a>

															<a
																href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList.claimRemarks}&claimId=${claimList.circulatedTo}&stat=9"
																title="Reject"><i class="icon-x"
																style="color: black;"></i></a>

															<c:if test="${leaveList.empId==empIdOrig}">
																<a
																	href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList.claimRemarks}&claimId=${claimList.circulatedTo}&stat=7"
																	title="Cancel"><i class="icon-cancel-squareed65"
																	style="color: black;"></i></a>
															</c:if>

														</c:when>

														<c:when test="${claimList.caIniAuthEmpId==empIdOrig}">

															<a
																href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList.claimRemarks}&claimId=${claimList.circulatedTo}&stat=2"
																title="Approve"><i class="icon-checkmark4 "
																style="color: black;"></i></a>

															<a
																href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList.claimRemarks}&claimId=${claimList.circulatedTo}&stat=8"
																title="Reject"><i class="icon-x"
																style="color: black;"></i></a>
															<c:if test="${leaveList.empId==empIdOrig}">

																<a
																	href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList.claimRemarks}&claimId=${claimList.circulatedTo}&stat=7"
																	title="Cancel"><i class="icon-cancel-squareed65"
																	style="color: black;"></i></a>
															</c:if>
														</c:when>

													</c:choose> <a
													href="${pageContext.request.contextPath}/claimDetailHistory?&claimId=${claimList.circulatedTo}"
													style="color: black"><i class="icon-list-unordered"></i></a></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>

							<div class="tab-pane fade" id="highlighted-justified-tab2">
								<table
									class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
									id="printtable1">
									<thead>
										<tr class="bg-blue">
											<th width="10%">Sr.no</th>
											<th>Employee Code</th>
											<th>Employee Name</th>
											<th>Type</th>
											<th>Claim Date</th>
											<th>Claim Amount</th>
											<th>Project</th>
											<th>Status</th>

											<th class="text-center" width="10%">Actions</th>
										</tr>
									</thead>
									<tbody>


										<c:forEach items="${claimListForApproval1}" var="claimList1"
											varStatus="count">

											<tr>
												<td>${count.index+1}</td>
												<td>${claimList1.empCode}</td>
												<td>${claimList1.empName}</td>
												<td>${claimList1.claimTypeName}</td>
												<td>${claimList1.claimDate}</td>
												<td>${claimList1.claimAmount}</td>
												<td>${claimList1.projectTitle}</td>
												<c:if test="${claimList1.exInt1==1}">
													<td><span class="badge badge-info">Initial
															Applied</span></td>
												</c:if>
												<c:if test="${claimList1.exInt1==2}">
													<td><span class="badge badge-secondary">Approve
															By Initial Authority</span></td>
												</c:if>
												<c:if test="${claimList1.exInt1==3}">
													<td><span class="badge badge-success">Approve
															By Final Authority</span></td>
												</c:if>
												<c:if test="${claimList1.exInt1==7}">
													<td><span class="badge badge-danger">Cancel By
															Employee</span></td>
												</c:if>
												<c:if test="${claimList1.exInt1==8}">
													<td><span class="badge badge-danger">Reject By
															Initial Authority</span></td>
												</c:if>
												<c:if test="${claimList1.exInt1==9}">
													<td><span class="badge badge-danger">Reject By
															Final Authority</span></td>
												</c:if>
												<td class="text-center"><c:choose>
														<c:when test="${claimList1.caFinAuthEmpId==empIdOrig}">

															<a
																href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList1.claimRemarks}&claimId=${claimList1.circulatedTo}&stat=3"
																title="Approve"><i class="icon-checkmark4 "
																style="color: black;"></i></a>

															<a
																href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList1.claimRemarks}&claimId=${claimList1.circulatedTo}&stat=9"
																title="Reject"><i class="icon-x"
																style="color: black;"></i></a>
														</c:when>

														<c:when test="${claimList1.caIniAuthEmpId==empIdOrig}">

															<%-- <a
															href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${leaveList.leaveTypeName}&leaveId=${leaveList.circulatedTo}&stat=2"
															class="dropdown-item"><i class="icon-pencil7"></i>Approve</a>
															
													<a
															href="${pageContext.request.contextPath}/approveLeaveByInitialAuth?empId=${leaveList.leaveTypeName}&leaveId=${leaveList.circulatedTo}&stat=8"
															class="dropdown-item"><i class="icon-pencil7"></i>Reject</a> --%>



														</c:when>

														<c:otherwise>

														</c:otherwise>
													</c:choose> <c:if test="${claimList1.empId==empIdOrig}">

														<a
															href="${pageContext.request.contextPath}/approveClaimByAuth?empId=${claimList1.claimRemarks}&claimId=${claimList1.circulatedTo}&stat=7"
															title="Cancel"><i class="icon-cancel-square"
															style="color: black;"></i></a>


													</c:if> <a
													href="${pageContext.request.contextPath}/claimDetailHistory?&claimId=${claimList1.circulatedTo}"
													style="color: black"><i class="icon-list-unordered"></i></a></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>


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