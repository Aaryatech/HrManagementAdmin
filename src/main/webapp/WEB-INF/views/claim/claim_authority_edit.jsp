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
						<a href="${pageContext.request.contextPath}/addClaimAuthority"
							class="breadcrumb-elements-item"> Add Claim Authority</a>

					</div>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Edit Claim Authority</h5>
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
						<form
							action="${pageContext.request.contextPath}/editSubmitClaimAuthorityList"
							method="post" id="frmAddLeaveAuthority">

							<div class="row">
								<div class="col-md-6">

									<table class="table datatable-scroll-y" width="100%"
										id="printtable1">
										<thead>
											<tr class="bg-blue">
												<th class="check" style="text-align: center; width: 5%;"><input
													type="checkbox" name="selAll" id="selAll" /></th>


												<th>Employee Code</th>
												<th>Employee Name</th>
												<th>Department</th>
												<!-- <th>Designation</th> -->

											</tr>
										</thead>
										<tbody>


											<c:forEach items="${empListAuth}" var="emp" varStatus="count">
												<tr>
													<td style="text-align: center;"><input type="checkbox"
														class="chk1" name="empIds" id="empIds${count.index+1}"
														checked value="${emp.empId}" /></td>

													<td width="10%">${emp.empCode}</td>
													<td>${emp.empSname}${emp.empFname}</td>
													<td width="10%">${emp.empDept}</td>
													<%-- <td width="10%">${emp.empCategory}</td> --%>
												</tr>
											</c:forEach>

										</tbody>
									</table>
									<span class="validation-invalid-label" id="error_table1"
										style="display: none;">Please select one employee.</span>
								</div>

								<div class="col-md-6">
									<table class="table datatable-scroll-y" width="100%"
										id="printtable2">
										<thead>
											<tr class="bg-blue">
												<th class="check" style="text-align: center;">Select
													Authority</th>

												<!-- <th width="10%">Sr. No.</th> -->
												<th>Employee Code</th>
												<th>Employee Name</th>
												<th>Department</th>
												<!-- <th width="10%">Desgn</th> -->
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${empList}" var="emp" varStatus="count">
												<tr>
													<td><c:choose>
															<c:when
																test="${claimAuthority.caIniAuthEmpId==emp.empId}">
																<input type="radio" name="iniAuthEmpId"
																	id="iniAuthEmpId${count.index+1}" value="${emp.empId}"
																	checked />Initial</c:when>
															<c:otherwise>
																<input type="radio" name="iniAuthEmpId"
																	id="iniAuthEmpId${count.index+1}" value="${emp.empId}" />Initial
																</c:otherwise>
														</c:choose> <c:choose>
															<c:when
																test="${claimAuthority.caFinAuthEmpId==emp.empId}">
																<input type="radio" name="finAuthEmpId"
																	id="finAuthEmpId${count.index+1}" value="${emp.empId}"
																	checked />Final
														</c:when>
															<c:otherwise>
																<input type="radio" name="finAuthEmpId"
																	id="finAuthEmpId${count.index+1}" value="${emp.empId}" />Final
																</c:otherwise>
														</c:choose> <c:set var="countOf" value="0"></c:set> <c:forEach
															items="${reportingIdList}" var="reportId"
															varStatus="count">
															<c:if test="${emp.empId==reportId}">
																<c:set var="countOf" value="1"></c:set>
															</c:if>


														</c:forEach> <c:choose>
															<c:when test="${countOf==1}">

																<input type="checkbox" class="chk" name="repToEmpIds"
																	id="repToEmpIds${count.index+1}" value="${emp.empId}"
																	checked />Reporting 
															 
																</c:when>
															<c:otherwise>
																<input type="checkbox" class="chk" name="repToEmpIds"
																	id="repToEmpIds${count.index+1}" value="${emp.empId}" />Reporting </c:otherwise>
														</c:choose></td>
													<td width="10%">${emp.empCode}</td>
													<td>${emp.empSname}${emp.empFname}</td>
													<td width="10%">${emp.empDept}</td>
													<%-- <td width="10%">${emp.empCategory}</td> --%>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
							<div class="col-md-12" style="text-align: center;">

								<input type="submit" class="btn btn-primary" value="Add"
									id="submtbtn"> <a
									href="${pageContext.request.contextPath}/claimAuthorityList"><button
										type="button" class="btn btn-primary">Cancel</button></a>

							</div>
						</form>

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

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#frmAddLeaveAuthority")
									.submit(
											function(e) {

												var isError = false;
												var errMsg = "";
												$("#error_table1").hide();
												//search
												// $("#frmAddLeaveAuthority :input[type='search']").val("");
												var table = $('#printtable1')
														.DataTable();
												table.search("").draw();
												var table = $('#printtable2')
														.DataTable();
												table.search("").draw();

												var checkedVals = $(
														'.chk1:checkbox:checked')
														.map(function() {
															return this.value;
														}).get();
												checkedVals = checkedVals
														.join(',');

												if (checkedVals == '') {
													$("#error_table1")
															.html(
																	"Please select one employee.");
													$("#error_table1").show();
													return false;
												}

												var off_payment_method = document
														.getElementsByName('iniAuthEmpId');
												var ischecked_method = false;
												for (var i = 0; i < off_payment_method.length; i++) {
													if (off_payment_method[i].checked) {
														ischecked_method = true;
														break;
													}
												}
												if (!ischecked_method) { //payment method button is not checked
													$("#error_table1")
															.html(
																	"Select one employee as Initial Authority.");
													$("#error_table1").show();
													return false;
												}

												var finAuthEmpId = document
														.getElementsByName('finAuthEmpId');
												var finAuthEmpId_method = false;
												for (var i = 0; i < finAuthEmpId.length; i++) {
													if (finAuthEmpId[i].checked) {
														finAuthEmpId_method = true;
														break;
													}
												}
												if (!finAuthEmpId_method) { //payment method button is not checked
													$("#error_table1")
															.html(
																	"Select one employee as Final Authority.");
													$("#error_table1").show();
													return false;
												}

												var checkedVals1 = $(
														'.chk:checkbox:checked')
														.map(function() {
															return this.value;
														}).get();
												checkedVals1 = checkedVals1
														.join(',');

												if (checkedVals1 == '') {

													$("#error_table1")
															.html(
																	"Select one employee as reporting.");
													$("#error_table1").show();
													return false;
												}

												//return false; //yaad rakhna to  remove it
												if (!isError) {

													var x = confirm("Do you really want to submit the form?");
													if (x == true) {

														document
																.getElementById("submtbtn").disabled = true;
														return true;
													}
													//end ajax send this to php page
												}
												return false;
											});

						});
	</script>


</body>
</html>