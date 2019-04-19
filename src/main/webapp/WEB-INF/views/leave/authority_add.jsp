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
						<a href="${pageContext.request.contextPath}/holidayAdd"
							class="breadcrumb-elements-item"> Add Holiday </a>

					</div>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Leave Authority</h5>
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
							action="${pageContext.request.contextPath}/submitAuthorityList"
							method="post">

							<div class="row">
								<div class="col-md-6">

									<table
										class="table table-bordered table-hover datatable-highlight datatable-button-html5-basic1  datatable-button-print-columns1"
										id="printtable1">
										<thead>
											<tr class="bg-blue">
												<th class="check" style="text-align: center; width: 5%;"><input
													type="checkbox" name="selAll" id="selAll" />Select All</th>

												<!-- <th width="10%">Sr. No.</th> -->
												<th>Employee Code</th>
												<th>Employee Name</th>
												<th>Department</th>
												<th>Designation</th>

											</tr>
										</thead>
										<tbody>


											<c:forEach items="${lvStructureList}" var="structure"
												varStatus="count">
												<tr>
													<td style="text-align: center;"><input type="checkbox"
														class="chk" name="empIds" id="empIds${count.index+1}"
														value="${structure.empId}" /></td>
													<%-- 	<td>${count.index+1}</td> --%>
													<td>${structure.empCode}</td>
													<td>${structure.empFname}${structure.empMname}${structure.empSname}</td>
													<td>${structure.empDeptName}</td>
													<td>${structure.empCatName}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>

								<div class="col-md-6">
									<table
										class="table table-bordered table-hover datatable-highlight datatable-button-html5-basic1  datatable-button-print-columns1"
										id="printtable2">
										<thead>
											<tr class="bg-blue">
												<th class="check" style="text-align: center;">Select
													Authority</th>

												<!-- <th width="10%">Sr. No.</th> -->
												<th width="10%">Employee Code</th>
												<th width="10%">Employee Name</th>
												<th width="10%">Department</th>
												<th width="10%">Designation</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${lvStructureList}" var="structure"
												varStatus="count">
												<tr>
													<td width="60%"><input type="radio" class="chk"
														name="iniAuthEmpId" id="iniAuthEmpId${count.index+1}"
														value="${structure.empId}" />Initial <input type="radio"
														class="chk" name="finAuthEmpId"
														id="finAuthEmpId${count.index+1}"
														value="${structure.empId}" />Final <input type="checkbox"
														class="chk" name="repToEmpIds"
														id="repToEmpIds${count.index+1}"
														value="${structure.empId}" />Reporting</td>
													<%-- <td>${count.index+1}</td> --%>
													<td width="10%">${structure.empCode}</td>
													<td>${structure.empFname}${structure.empMname}
														${structure.empSname}</td>
													<td width="10%">${structure.empDeptName}</td>
													<td width="10%">${structure.empCatName}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-1">

									<input type="submit" class="btn btn-primary" value="Add"
										id="deleteId"
										onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to Submit record');}"
										style="align-content: center; width: 113px; margin-left: 40px;">


								</div>
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
		$(document).ready(
				function() {
					$('#bootstrap-data-table-export').DataTable();

					$("#selAll1").click(
							function() {
								$('#printtable2 tbody input[type="checkbox"]')
										.prop('checked', this.checked);
							});
				});
	</script>
</body>
</html>