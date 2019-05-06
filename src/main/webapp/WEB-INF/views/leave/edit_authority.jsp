<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
</head>
<style>
* {
  box-sizing: border-box;
}

.myInput {
  background-image: url('https://www.w3schools.com/css/searchicon.png');
  background-position: 8px 7px;
  background-repeat: no-repeat;
  width: 50%;
  font-size: 16px;
  padding: 5px 5px 5px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

#myTable {
  border-collapse: collapse;
  width: 100%;
  border: 1px solid #ddd;
  font-size: 18px;
}

#myTable th, #myTable td {
  text-align: left;
  padding: 12px;
}

#myTable tr {
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  background-color: #f1f1f1;
}
</style>
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
							class="breadcrumb-elements-item"> Add Authority</a>

					</div>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Edit Authority</h5>
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
							action="${pageContext.request.contextPath}/editSubmitAuthorityList"
							method="post">

							<div class="row">
								<div class="col-md-6">
									 
									 <div class="table-responsive">
									 
										<table
											class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic1  datatable-button-print-columns1"
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


												<c:forEach items="${empListAuth}" var="emp"
													varStatus="count">
													<tr>
														<td style="text-align: center;"><input
															type="checkbox" class="chk" name="empIds"
															id="empIds${count.index+1}" checked value="${emp.empId}" /></td>

														<td>${emp.empCode}</td>
														<td>${emp.empSname}${emp.empFname}</td>
														<td>${emp.empDept}</td>
														<%-- <td>${emp.empCategory}</td> --%>
													</tr>
												</c:forEach>

											</tbody>
										</table>
										</div>
									</div>

									<div class="col-md-6">
										 <div class="table-responsive">
											<input type="text" id="myInput1" class="myInput"
												onkeyup="myFunction1()" placeholder="Search for employee.."
												title="Type in a name">
											<table
												class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic1  datatable-button-print-columns1"
												id="printtable2">
												<thead>
													<tr class="bg-blue">
														<th class="check" style="text-align: center;">Select
															Authority</th>

														<!-- <th width="10%">Sr. No.</th> -->
														<th>Employee Code</th>
														<th>Employee Name</th>
														<th>Department</th>
														<!-- <th  >Desgn</th> -->
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${empList}" var="emp" varStatus="count">
														<tr>
															<td><c:choose>
																	<c:when
																		test="${leaveAuthority.iniAuthEmpId==emp.empId}">
																		<input type="radio" class="chk" name="iniAuthEmpId"
																			id="iniAuthEmpId${count.index+1}"
																			value="${emp.empId}" checked />Initial</c:when>
																	<c:otherwise>
																		<input type="radio" class="chk" name="iniAuthEmpId"
																			id="iniAuthEmpId${count.index+1}"
																			value="${emp.empId}" />Initial
																</c:otherwise>
																</c:choose> <c:choose>
																	<c:when
																		test="${leaveAuthority.finAuthEmpId==emp.empId}">
																		<input type="radio" class="chk" name="finAuthEmpId"
																			id="finAuthEmpId${count.index+1}"
																			value="${emp.empId}" checked />Final
														</c:when>
																	<c:otherwise>
																		<input type="radio" class="chk" name="finAuthEmpId"
																			id="finAuthEmpId${count.index+1}"
																			value="${emp.empId}" />Final
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
															<td>${emp.empCode}</td>
															<td>${emp.empSname}${space}${emp.empFname}</td>
															<td>${emp.empDept}</td>
															<%-- <td  >${emp.empCategory}</td> --%>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										 </div>
									 
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
	<script>
		function myFunction() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("printtable1");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[2];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>
	<script>
		function myFunction1() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("myInput1");
			filter = input.value.toUpperCase();
			table = document.getElementById("printtable2");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[2];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>
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