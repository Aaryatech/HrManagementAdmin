<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
						<h5 class="card-title">Employee KRA KPI</h5>
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
								:*</label>
							<div class="col-md-2">
								<select name="empId" data-placeholder="Select " id="status"
									class="form-control form-control-select2 select2-hidden-accessible"
									tabindex="-1" aria-hidden="true">
									<option value="0">All</option>
									<option value="1">KRA Alloted</option>
									<option value="2">KPI Alloted</option>
									<option value="3">KRA Not Alloted</option>
									<option value="4">KPI Not Alloted</option>
									<option value="5">BothAlloted</option>

								</select> <span class="validation-invalid-label" id="error_status"
									style="display: none;">This field is required.</span>
							</div>


							<label class="col-form-label col-lg-2" for="select2">Select
								Financial Year :*</label>
							<div class="col-md-2">
								<select name="finYrId" data-placeholder="Select Year"
									id="finYrId"
									class="form-control form-control-select2 select2-hidden-accessible"
									tabindex="-1" aria-hidden="true">
									<option value="">Select Financial Year</option>
									<c:forEach items="${finYrList}" var="finYear">
										<option value="${finYear.finYrId}">${finYear.finYrFrom}
											to ${finYear.finYrTo}</option>
									</c:forEach>
								</select> <span class="validation-invalid-label" id="error_finYrId"
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
								width="150px" height="150px"
								style="display: block; margin-left: auto; margin-right: auto">
						</div>
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="bootstrap-data-table">
							<thead>
								<tr class="bg-blue">
									<th width="10%">Sr.no</th>

									<th>Employee Code</th>
									<th>Name</th>
									<th>Work Area</th>
									<th>KRA Count</th>
									<th>KPI Count</th>

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

			var status = document.getElementById("status").value;
			var finYrId = document.getElementById("finYrId").value;
			//var toDate = document.getElementById("to_date").value;

			//alert(compId);

			var valid = true;

			if (finYrId == null || finYrId == "") {
				valid = false;
				alert("Please Select Year");
			}

			var valid = true;
			if (status == null || status == "") {
				valid = false;
				alert("Please Select Condition");

				var dataTable = $('#bootstrap-data-table').DataTable();
				dataTable.clear().draw();

			}
			$("#loader").show();

			if (valid == true) {

				$
						.getJSON(
								'${empInfoCountList}',
								{
									finYrId : finYrId,
									status : status,
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

														var str = '<a href="${pageContext.request.contextPath}/showAddKra?empId='
																+ v.empMname
																+ '&finYrId='
																+ finYrId
																+ '" ><i class="icon-list-unordered"   style="color:black"></i></a>'

														dataTable.row
																.add(
																		[
																				i + 1,
																				v.empCode,
																				v.empSname
																						+ " "
																						+ v.empFname,
																				v.empTypeShortName
																						+ " "
																						+ v.empDeptShortName
																						+ " "
																						+ v.empCatShortName,
																				v.kraCount,
																				v.kpiCount,
																				str ])
																.draw();
													});
									$("#loader").hide();

								});

			}//end of if valid ==true

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