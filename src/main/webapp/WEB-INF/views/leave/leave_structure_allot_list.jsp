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

					<%-- 	<div class="breadcrumb justify-content-center">
						<a href="${pageContext.request.contextPath}/holidayAdd"
							class="breadcrumb-elements-item"> Add Holiday </a>

					</div> --%>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Leave Structure Allotment List</h5>
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
							action="${pageContext.request.contextPath}/submitStructureList"
							method="post">

							<div class="form-group row">
								<label class="col-form-label col-lg-2" for="select2">Select
									Structure :</label>
								<div class="col-lg-10">
									<select name="lvsId" data-placeholder="Select Structure"
										id="lvsId"
										class="form-control form-control-select2 select2-hidden-accessible"
										required="" data-fouc="" tabindex="-1" aria-hidden="true">
										<option></option>
										<c:forEach items="${lStrList}" var="str">

											<option value="${str.lvsId}">${str.lvsName}</option>


										</c:forEach>
									</select> <span class="validation-invalid-label" id="error_locName"
										style="display: none;">This field is required.</span>
								</div>
							</div>

							<table
								class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
								id="printtable1">
								<thead>
									<tr class="bg-blue">
										<th class="check" style="text-align: center; width: 5%;"><input
											type="checkbox" name="selAll" id="selAll" /></th>

										<th width="10%">Sr. No.</th>
										<th>Employee Code</th>
										<th>Employee Name</th>
										<th>Department</th>
										<th>Designation</th>
										<th>Structure</th>


										<!-- <th width="10%" class="text-center">Actions</th> -->
									</tr>
								</thead>
								<tbody>


									<c:forEach items="${lvStructureList}" var="structure"
										varStatus="count">
										<tr>



											<c:set var="countOf" value="0"></c:set>
											<c:forEach items="${calAllotList}" var="calender"
												varStatus="count1">
												<c:if test="${calender.empId == structure.empId}">
													<c:set var="countOf" value="1"></c:set>
												</c:if>


											</c:forEach>



											<c:choose>
												<c:when test="${countOf==1}">
													<td></td>
												</c:when>
												<c:otherwise>
													<td><input type="checkbox" class="chk" name="empIds"
														id="empIds${count.index+1}" value="${structure.empId}" /></td>
												</c:otherwise>
											</c:choose>


											<td>${count.index+1}</td>
											<td>${structure.empCode}</td>
											<td>${structure.empSname} ${structure.empFname}</td>
											<td>${structure.empDeptName}</td>
											<td>${structure.empCatName}</td>
											<td>${structure.lvsName}</td>

											<%-- <td class="text-center">
											<div class="list-icons">
												<div class="dropdown">
													<a href="#" class="list-icons-item" data-toggle="dropdown">
														<i class="icon-menu9"></i>
													</a>

													<div class="dropdown-menu dropdown-menu-right">
														<a
															href="${pageContext.request.contextPath}/editHoliday?holidayId=${holiday.exVar1}"
															class="dropdown-item"><i class="icon-pencil7"></i>Edit</a>
														<a
															href="${pageContext.request.contextPath}/deleteHoliday?holidayId=${holiday.exVar1}"
															class="dropdown-item"><i class="icon-trash"></i>
															Delete</a>
													</div>
												</div>
											</div>
										</td> --%>
										</tr>
									</c:forEach>

								</tbody>
							</table>
							<div class="col-lg-1">

								<input type="submit" class="btn btn-primary" value="Add"
									id="deleteId"
									onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to Submit record');}"
									style="align-content: center; width: 113px; margin-left: 40px;">


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


</body>
</html>