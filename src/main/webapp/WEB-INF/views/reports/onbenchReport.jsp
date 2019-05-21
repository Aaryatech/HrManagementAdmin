<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
<c:url var="empInfoCountList" value="/empInfoCountList" />
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


					<%-- <div class="breadcrumb justify-content-center">
						<a href="${pageContext.request.contextPath}/employeeAdd"
							class="breadcrumb-elements-item"> Add Employee </a>

					</div> --%>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">On Bench Report</h5>
						<!-- <div class="header-elements">
							<div class="list-icons">
								<a class="list-icons-item" data-action="collapse"></a>
							</div> 
						</div>-->
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

						<form
							action="${pageContext.request.contextPath}/onbenchReport"
							id="onbenchReport" method="GET">

							<div class="form-group row">
							<div class="col-md-2"></div>
								<label class="col-form-label col-lg-1" for="fromDate">From
									Date :*</label>
								<div class="col-md-2">
									<input type="text" class="form-control datepickerclass "
										name="fromDate" id="fromDate" value="${fromDate}" autocomplete="off"> <span
										class="validation-invalid-label" id="error_fromDate"
										style="display: none;">This field is required.</span>
								</div>

								<div class="col-md-1"></div>
								<label class="col-form-label col-lg-1" for="toDate">To
									Date :*</label>
								<div class="col-md-2">
									<input type="text" class="form-control datepickerclass "
										name="toDate" id="toDate" autocomplete="off" value="${toDate}"> <span
										class="validation-invalid-label" id="error_toDate"
										style="display: none;">This field is required.</span>
								</div>

								 
							</div>
							
							<div class="co-md-12" style="text-align: center;">
							<input type="submit" class="btn bg-blue ml-3 legitRipple"
									id="submtbtn" value="Submit">
							</div>
						</form>
						<div id='loader' style='display: none;'>
							<img
								src='${pageContext.request.contextPath}/resources/assets/images/giphy.gif'
								width="150px" height="150px"
								style="display: block; margin-left: auto; margin-right: auto">
						</div>
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="bootstrap-data-table">
							<thead>
								<tr class="bg-blue" style="text-align: center;">
									<th width="10%">Sr.no</th>

									<th>Employee Name </th>
									<th>Activity</th> 
									<th>CTC</th>  
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="list"
									varStatus="count">
									<tr>
										<td>${count.index+1}</td> 
										<td>${list.empSname}&nbsp;${list.empFname}</td> 
										<td>${list.type}</td> 
										<td align="right">${list.ctc}</td>
										
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>

				</div>
				<!-- /highlighting rows and columns -->


				<!-- /content area -->
			</div>

			<!-- Footer -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
			<!-- /footer -->

		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->
	<script type="text/javascript">
		// Single picker
		$('.datepickerclass').daterangepicker({
			singleDatePicker : true,
			selectMonths : true,
			selectYears : true,
			locale : {
				format : 'DD-MM-YYYY'
			}
		});

		//daterange-basic_new
		// Basic initialization
		$('.daterange-basic_new').daterangepicker({
			applyClass : 'bg-slate-600',

			cancelClass : 'btn-light',
			locale : {
				format : 'DD-MM-YYYY',
				separator : ' to '
			}
		});
	</script>

</body>
</html>