<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<c:url var="addStrDetail" value="/addStrDetail" />


<c:url var="chkNumber" value="/chkNumber" />

<c:url var="getLeaveStructureForEdit" value="/getLeaveStructureForEdit" />
<c:url var="getHolidayAndWeeklyOffList"
	value="/getHolidayAndWeeklyOffList" />
<c:url var="calholidayWebservice" value="/calholidayWebservice" />
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

 
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">

				<!-- Form validation -->
				<div class="row">
					<div class="col-md-12">
						<!-- Title -->
						<!-- <div class="mb-3">
							<h6 class="mb-0 font-weight-semibold">Hidden labels</h6>
							<span class="text-muted d-block">Inputs with empty values</span>
						</div> -->
						<!-- /title -->


						<div class="card">
					 
							<div class="card-header header-elements-inline">
 						<table width="100%">
							<tr width="100%">
								<td width="60%"><h5 class="card-title">Add KPI</h5></td>
								<td width="40%" align="right">
							  
								 <a
									href="${pageContext.request.contextPath}/showAddKra?empId=${editKra.exVar3}&finYrId=${editKra.exVar2}"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary">KRA List </button>
								</a> </td>
							</tr>
						</table>
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



								<div class="form-group row">
									<label class="col-form-label col-lg-2" for="lvsName">
										Employee Code : </label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="empCode"
											value="${empInfo.empCode}" name="lvsName" autocomplete="off"
											onchange="trim(this)" readonly>

									</div>
								</div>
								<div class="form-group row">
									<label class="col-form-label col-lg-2" for="lvsName">
										Employee Name : </label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="empName"
											value="${empInfo.empFname} ${empInfo.empMname} ${empInfo.empSname}   "
											name="lvsName" autocomplete="off" onchange="trim(this)"
											readonly>

									</div>
								</div>

								<div class="form-group row">
									<label class="col-form-label col-lg-2" for="lvsName">
										KRA Title: </label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="empName"
											value="${editKra.kraTitle}" name="lvsName" autocomplete="off"
											onchange="trim(this)" readonly>

									</div>
								</div>

								<div class="form-group row">
									<label class="col-form-label col-lg-2" for="lvsName">
										Financial Year : </label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="empName"
											value="${finYr.finYrFrom} To  ${finYr.finYrFrom} "
											name="lvsName" autocomplete="off" onchange="trim(this)"
											readonly>

									</div>
								</div>
								<hr>
								<form
									action="${pageContext.request.contextPath}/submitInsertKpi"
									id="submitInsertLeave" method="post">


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="kra_review">
											KPI Title <span style="color:red">* </span>: </label>
										<div class="col-lg-4">
											<input type="text" class="form-control"
												placeholder="KPI Title" id="kpi_title" name="kpi_title"
												autocomplete="off"> <span
												class="validation-invalid-label" id="error_kpi_title"
												style="display: none;">This field is required.</span>
										</div>
									</div>
                            <div class="form-group row">
										<label class="col-form-label col-lg-2" for="lvngReson">Remark
											: </label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Remark" onchange="trim(this)" id="leaveRemark"
												name="remark">  </textarea>
										</div>
									</div>

									<input type="hidden" class="form-control numbersOnly"
										id="empId" value="${empInfo.empId}" name="empId"> <input
										type="hidden" class="form-control numbersOnly" id="kraId"
										value="${editKra.kraId}" name="kraId">

									<div class="col-md-12" style="text-align: center;">

										<button type="submit" class="btn bg-blue ml-3 legitRipple"
											id="submtbtn">
											Add <i class="icon-paperplanereviewreview ml-2"></i>
										</button>


									</div>
								</form>
								<h6 class="card-title">KPI List</h6>

								<div class="table-responsive">
									<table
										class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
										id="printtable1">


										<thead>
											<tr class="bg-blue" style="text-align: center;">
												<th width="10%">Sr.no</th>
												<th>KPI Title</th>
												<th>Remark</th>


												<th width="10%" class="text-center">Actions</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach items="${kpiList}" var="kpiList"
												varStatus="count">
												<tr>
													<td>${count.index+1}</td>
													<td>${kpiList.kpiTitle}</td>
													<td>${kpiList.remark}</td>

													<td class="text-center">
													 <a
														href="${pageContext.request.contextPath}/showAddKpiReview?kpiId=${kpiList.exVar1}"
														title="Add Review"><i class="icon-diff-added"
															style="color: black;"></i></a>
													<a
														href="${pageContext.request.contextPath}/showEditKpi?kpiId=${kpiList.exVar1}"><i
															class="icon-pencil7" style="color: black;"></i></a> <a
														href="${pageContext.request.contextPath}/deleteKpi?kpiId=${kpiList.exVar1}"
														onClick="return confirm('Are you sure want to delete this record');"
														title="Delete"><i class="icon-trash"
															style="color: black;"></i> </a>
														 
														
															</td>
														 
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
								<br>

							</div>
						</div>


					</div>
				</div>

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
		function calculateDiff() {

			var daterange = document.getElementById("leaveDateRange").value;
			var res = daterange.split(" to ");

			var date1res = res[0].split("-");
			var date2res = res[1].split("-");

			var date1 = new Date(date1res[2], date1res[1] - 1, date1res[0])//converts string to date object

			var date2 = new Date(date2res[2], date2res[1] - 1, date2res[0])

			const diffTime = Math.abs(date2.getTime() - date1.getTime());
			const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
			document.getElementById("noOfDays").value = diffDays + 1;

			//document.getElementById("noOfDaysExclude").value = diffDays + 1;

			return (diffDays + 1);
		}
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

	<script>
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		$(document).ready(function($) {

			$("#submitInsertLeave").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#kpi_title").val()) {

					isError = true;

					$("#error_kpi_title").show()
					//return false;
				} else {
					$("#error_kpi_title").hide()
				}

				if (!isError) {

					var x =true;
					if (x == true) {

						document.getElementById("submtbtn").disabled = true;
						return true;
					}
				}
				return false;
			});
		});
		//
	</script>
	<script>
		function submitForm() {
			$('#modal_scrollable').modal('hide');
			document.getElementById("submtbtn").disabled = true;
			document.getElementById("submitInsertLeave").submit();

		}
	</script>
	<!-- Scrollable modal -->
	<div id="modal_scrollable" class="modal fade" data-backdrop="false"
		tabindex="-1">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header pb-3">

					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body py-0">
					<h5 class="modal-title">Leave Details</h5>
					<br>

					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="lvType">
							Employee Code : </label> <label class="col-form-label col-lg-2"
							id="empCode1" for="empCode1"> </label>

					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="lvType">
							Employee Name : </label> <label class="col-form-label col-lg-6"
							id="empName1" for="empName1"> </label>

					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="lvType"> Leave
							Type : </label> <label class="col-form-label col-lg-6" id="lvType"
							for="lvType"> </label>

					</div>


					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="fromdate1">
							From Date : </label> <label class="col-form-label col-lg-3"
							id="fromdate1" for="noOfDays1"> </label> <label
							class="col-form-label col-lg-3" for="todate1"> To Date :
						</label> <label class="col-form-label col-lg-2" id="todate1"
							for="noOfDays1"> </label>

					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="noOfDays"> No.
							of Days : </label> <label class="col-form-label col-lg-3" id="noOfDays1"
							for="noOfDays1"> </label>

					</div>
				</div>

				<div class="modal-footer pt-3">
					<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn bg-primary" onclick="submitForm()">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /scrollable modal -->
</body>
</html>