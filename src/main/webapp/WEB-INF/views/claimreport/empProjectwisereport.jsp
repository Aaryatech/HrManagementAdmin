<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
<c:url var="empInfoHistoryReportList" value="/empInfoHistoryReportList" />
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

			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Employee Project wise Claim Report</h5>
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
							action="${pageContext.request.contextPath}/empProjectwisereport"
							id="submitInsertLeave" method="get">
							<div class="form-group row">
								<label class="col-form-label col-lg-1" for="date">Select
									Date <span style="color: red">* </span> :
								</label>
								<div class="col-md-2">
									<input type="text" class="form-control daterange-basic_new"
										id="date" name="date" value="${date}" autocomplete="off">
								</div>



								<button type="submit" class="btn bg-blue ml-3 legitRipple"
									id="submtbtn">
									Search <i class="icon-paperplane ml-2"></i>
								</button>

								<c:if test="${list.size()>0}">
									<button type="button" class="btn bg-blue ml-3 legitRipple"
										id="submtbtn"
										onclick="getProgReport(0,'exelForEmployeeProjectWiseClaim')">
										Excel <i class="icon-paperplane ml-2"></i>
									</button>
								</c:if>
							</div>
							<div id='loader' style='display: none;'>
								<img
									src='${pageContext.request.contextPath}/resources/assets/images/giphy.gif'
									width="150px" height="150px"
									style="display: block; margin-left: auto; margin-right: auto">
							</div>

							<div class="table-responsive">
								<table
									class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic1  datatable-button-print-columns1"
									id="bootstrap-data-table">
									<thead>
										<tr class="bg-blue">
											<th width="10%">Sr.no</th>
											<th>Employee</th>
											<c:forEach items="${projectHeaderList}"
												var="projectHeaderList">
												<th>${projectHeaderList.projectTitle}</th>
											</c:forEach>
											<th>Total</th>
										</tr>
									</thead>

									<tbody>

										<c:set value="0" var="finalTotal"></c:set>
										<c:forEach items="${list}" var="list" varStatus="count">
											<tr>
												<c:set value="0" var="empTotal"></c:set>
												<td>${count.index+1}</td>
												<td>${list.empName}</td>
												<c:forEach items="${projectHeaderList}"
													var="projectHeaderList">
													<c:forEach items="${list.list}" var="detailList">
														<c:if
															test="${projectHeaderList.projectId==detailList.typeId}">
															<td style="text-align: right;">${detailList.amt}</td>
															<c:set value="${empTotal+detailList.amt}" var="empTotal"></c:set>
														</c:if>
													</c:forEach>
												</c:forEach>
												<td style="text-align: right;">${empTotal}</td>
												<c:set value="${finalTotal+empTotal}" var="finalTotal"></c:set>
											</tr>
										</c:forEach>
										<c:if test="${list.size()>0}">
											<tr>
												<td>-</td>
												<td>Total</td>
												<c:forEach items="${projectHeaderList}"
													var="projectHeaderList">
													<c:set value="0" var="typeTotal"></c:set>
													<c:forEach items="${list}" var="list" varStatus="count">
														<c:forEach items="${list.list}" var="detailList">
															<c:if
																test="${projectHeaderList.projectId==detailList.typeId}">

																<c:set value="${typeTotal+detailList.amt}"
																	var="typeTotal"></c:set>
															</c:if>
														</c:forEach>
													</c:forEach>

													<td style="text-align: right;">${typeTotal}</td>
												</c:forEach>
												<td style="text-align: right;">${finalTotal}</td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>

						</form>
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
		//use this function for all reports just get mapping form action name dynamically as like of prm from every report pdf,excel function	
		function getProgReport(prm, mapping) {

			window.open("${pageContext.request.contextPath}/" + mapping + "/",
					"_blank");

		}
	</script>
	<!-- <script type="text/javascript">
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

				$.getJSON('${empInfoHistoryReportList}', {
					empId : empId,
					calYrId : calYrId,
					ajax : 'true',
				},

				function(data) {

					var dataTable = $('#bootstrap-data-table').DataTable();
					dataTable.clear().draw();

					$.each(data, function(i, v) {
						
					 
						var str=v.balLeave + v.lvsAllotedLeaves
						- v.sactionLeave
						- v.aplliedLeaeve ;
						dataTable.row.add(
								[
										i + 1,
										v.lvTitle,
										v.balLeave.toFixed([2]),
										v.lvsAllotedLeaves.toFixed([2]),
										v.sactionLeave.toFixed([2]),
										v.aplliedLeaeve.toFixed([2]),
										str.toFixed([2])
										]).draw();
					});
					$("#loader").hide();

				});

			}//end of if valid ==true

		}
	</script> -->
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