<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
<c:url var="empInfoHistoryList" value="/empInfoHistoryList" />
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
						<h5 class="card-title">Employee Leave History</h5>
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


						<div class="form-group row">
							<label class="col-form-label col-lg-2" for="select2">Select
								Employee :*</label>
							<div class="col-md-2">
								<select name="empId" data-placeholder="Select Employee"
									id="empId"
									class="form-control form-control-select2 select2-hidden-accessible"
									tabindex="-1" aria-hidden="true">
									<option value="">Select Employee</option>
									<c:forEach items="${employeeInfoList}" var="empInfo">
										<option value="${empInfo.empId}">${empInfo.empSname}
											${empInfo.empFname} ${empInfo.empMname}</option>
									</c:forEach>
								</select> <span class="validation-invalid-label" id="error_empId"
									style="display: none;">This field is required.</span>
							</div>


							<label class="col-form-label col-lg-2" for="select2">Select
								Year :*</label>
							<div class="col-md-2">
								<select name="calYrId" data-placeholder="Select Year"
									id="calYrId"
									class="form-control form-control-select2 select2-hidden-accessible"
									tabindex="-1" aria-hidden="true">
									<option value="">Select Calendar Year</option>
									<c:forEach items="${calYearList}" var="calYear">
										<option value="${calYear.calYrId}">${calYear.calYrFromDate}
											to ${calYear.calYrToDate}</option>
									</c:forEach>
								</select> <span class="validation-invalid-label" id="error_calYrId"
									style="display: none;">This field is required.</span>
							</div>




							<button type="button" class="btn bg-blue ml-3 legitRipple"
								id="submtbtn" onclick="show()">
								Submit <i class="icon-paperplane ml-2"></i>
							</button>

						</div>
						<div id='loader' style='display: none;'>
							<img
								src='${pageContext.request.contextPath}/resources/assets/images/giphy.gif'
								width="150px" height="150px" style="display: block; margin-left: auto; margin-right: auto">
						</div>
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="bootstrap-data-table">
							<thead>
								<tr class="bg-blue">
									<th width="10%">Sr.no</th>
									<th>Name</th>
									<th>Department Name</th>
									<th>Leave Type</th>
									<th>Code</th>
									<th>Leave Days</th>
									<th>Leave Duration</th>
									<th>Date</th>
									<th>Action By</th>
									<th>Leave Status</th>

									<th class="text-center" width="10%">Actions</th>
								</tr>
							</thead>
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
		function show() {

			//alert("Hi View Orders  ");

			var empId = document.getElementById("empId").value;
			var calYrId = document.getElementById("calYrId").value;
			//var toDate = document.getElementById("to_date").value;

			//alert(compId);

			var valid = true;

			if (empId == null || empId == "") {
				valid = false;
				alert("Please Select Employee");
			}

			var valid = true;
			if (calYrId == null || calYrId == "") {
				valid = false;
				alert("Please Select Year");

				var dataTable = $('#bootstrap-data-table').DataTable();
				dataTable.clear().draw();

			}
			$("#loader").show();

			if (valid == true) {

				$
						.getJSON(
								'${empInfoHistoryList}',
								{
									empId : empId,
									calYrId : calYrId,
									ajax : 'true',
								},

								function(data) {

									var dataTable = $('#bootstrap-data-table')
											.DataTable();
									dataTable.clear().draw();

									$
											.each(
													data,
													function(i, v) {

														var str = '<a href="${pageContext.request.contextPath}/empDetailHistory?leaveId='
																+ v.exVar1
																+ '" ><i class="icon-list-unordered"   style="color:black"></i></a>'

														var current_status;
														if (v.exInt1 == 1) {
															/* current_status="Pending"; */

															current_status = '<span class="badge badge-info">Initial Applied</span>';
														} else if (v.exInt1 == 2) {

															current_status = '<span class="badge badge-secondary">Approve By Initial Authority</span>';
														} else if (v.exInt1 == 3) {
															current_status = '<span class="badge badge-success">Approve By Final Authority</span>';
														} else if (v.exInt1 == 7) {
															current_status = '<span class="badge badge-danger">Cancel By Employee</span>';
														} else if (v.exInt1 == 8) {
															current_status = '<span class="badge badge-danger">Reject By Initial Authority</span>';
														} else if (v.exInt1 == 9) {
															current_status = '<span class="badge badge-danger">Reject By Final Authority</span>';
														}

														var duration;
														if (v.leaveDuration == 1) {
															/* current_status="Pending"; */

															duration = 'Full Day';
														}
														if (v.leaveDuration == 2) {
															/* current_status="Pending"; */

															duration = 'Half Day';
														}

														dataTable.row
																.add(
																		[
																				i + 1,
																				v.empSname
																						+ " "
																						+ v.empFname,
																				v.empDeptName,
																				v.lvTitle,
																				v.empCode,
																				v.leaveNumDays,
																				duration,
																				v.leaveFromdt
																						+ " To "
																						+ v.leaveTodt,
																				v.userName,
																				current_status,
																				str ])
																.draw();
													});
									$("#loader").hide();

								});

			}//end of if valid ==true

		}

		function callDetail(exVar1, empId) {
			alert(exVar1);
			window
					.open("${pageContext.request.contextPath}/empDetailHistory?empId="
							+ exVar1);

		}

		function callDelete(weighId) {
			window.open("${pageContext.request.contextPath}/deleteWeighing/"
					+ weighId);

		}
	</script>
	<script>
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		function validateEmail(email) {

			var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

			if (eml.test($.trim(email)) == false) {

				return false;

			}

			return true;

		}
		function validateMobile(mobile) {
			var mob = /^[1-9]{1}[0-9]{9}$/;

			if (mob.test($.trim(mobile)) == false) {

				//alert("Please enter a valid email address .");
				return false;

			}
			return true;

		}
		$(document).ready(function($) {

			$("#submtbtn").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#clYrId").val()) {

					isError = true;

					$("#error_clYrId").show()

				} else {
					$("#error_clYrId").hide()
				}

				if (!$("#empId").val()) {

					isError = true;

					$("#error_empId").show()

				} else {
					$("#error_empId").hide()
				}

				/* if (!$("#calYrId").val()) {

					isError = true;

					$("#error_calYrId").show()

				} else {
					$("#error_calYrId").hide()
				} */

				if (!$("#dateRange").val()) {

					isError = true;

					$("#error_Range").show()

				} else {
					$("#error_Range").hide()
				}

				if (!isError) {

					var x = confirm("Do you really want to submit the form?");
					if (x == true) {

						document.getElementById("submtbtn").disabled = true;
						return true;
					}
					//end ajax send this to php page
				}
				return false;
			});
		});
		//
	</script>
</body>
</html>