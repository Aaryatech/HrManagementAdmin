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

<body onload="chkAssign()">


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
						<a href="${pageContext.request.contextPath}/showProjectHeaderList"
							class="breadcrumb-elements-item">Project List</a>

					</div>


				</div>
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
								<h6 class="card-title">Project Details</h6>
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



								<div class="form-group row">
									<label class="col-form-label col-lg-2" for="lvsName">
										Project Title:</label>
									<div class="col-lg-10">
										<input type="text" class="form-control"
											value="${updateProjectHeader.projectTitle}" name="lvsName"
											autocomplete="off" readonly>

									</div>
								</div>
								<div class="form-group row">
									<label class="col-form-label col-lg-2" for="lvsName">
										Project Description: </label>
									<div class="col-lg-10">
										<input type="text" class="form-control"
											value="${updateProjectHeader.projectDesc}" name="lvsName"
											readonly>

									</div>
								</div>
								<div class="form-group row">
									<label class="col-form-label col-lg-2" for="lvsName">
										Start Date: </label>
									<div class="col-lg-10">
										<input type="text" class="form-control"
											value="${updateProjectHeader.projectEstStartdt}"
											name="lvsName" readonly>

									</div>
								</div>
								<div class="form-group row">
									<label class="col-form-label col-lg-2" for="lvsName">
										End Date: </label>
									<div class="col-lg-10">
										<input type="text" class="form-control"
											value="${updateProjectHeader.projectEstEnddt}" name="lvsName"
											readonly>

									</div>
								</div>
								<hr>

								<br>
								<form
									action="${pageContext.request.contextPath}/submitUpdateProStatus"
									id="submitUpdateProStatus" method="post">



									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Project Status<span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="status" data-placeholder="Please Select"
												id="status"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Please Select</option>

												<c:choose>
													<c:when test="${updateProjectHeader.projectStatus eq '0'}">

														<option selected value="0">Created</option>
														<option value="1">Work in Progress</option>
														<option value="2">Completed</option>
														<option value="3">Cancelled</option>
														<option value="4">Hold</option>
													</c:when>
													<c:when test="${updateProjectHeader.projectStatus eq '1'}">

														<option selected value="1">Work in Progress</option>
														<option value="2">Completed</option>
														<option value="3">Cancelled</option>
														<option value="4">Hold</option>
													</c:when>
													<c:when test="${updateProjectHeader.projectStatus eq '2'}">
													
														<option selected value="2">Completed</option>
														<option value="3">Cancelled</option>
														<option value="4">Hold</option>
													</c:when>
													<c:when test="${updateProjectHeader.projectStatus eq '3'}">

														<option selected value="3">Cancelled</option>
														<option value="4">Hold</option>
													</c:when>
													<c:when test="${updateProjectHeader.projectStatus eq '4'}">

														<option selected value="4">Hold</option>
													</c:when>
													<c:otherwise>
														<option value="0">Created</option>
														<option value="1">Work in Progress</option>
														<option value="2">Completed</option>
														<option value="3">Cancelled</option>
														<option value="4">Hold</option>
													</c:otherwise>

												</c:choose>
											</select> <span class="validation-invalid-label" id="error_status"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="proComp">
											Project Completion<span style="color: red">* </span> :
										</label>
										<div class="col-lg-2">
											<input type="number" class="form-control" id="proComp"
												name="proComp" min="0" max="100" autocomplete="off"
												onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_proComp"
												style="display: none;">This field is required.</span>
										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="lvngReson">Remark
											: </label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Remark" onchange="trim(this)" id="remark"
												name="remark"> </textarea>
										</div>
									</div>
									<input type="hidden" class="form-control numbersOnly"
										id="empId" value="${updateProjectHeader.projectId}"
										name="projectId">

									<div class="col-md-12" style="text-align: center;">

										<button type="submit" class="btn bg-blue ml-3 legitRipple"
											id="submtbtn">
											Submit <i class="icon-paperplane ml-2"></i>
										</button>
										<a href="${pageContext.request.contextPath}/showApplyForLeave"><button
												type="button" class="btn btn-primary">
												<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;
												Cancel
											</button></a>

									</div>
								</form>
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

			$("#submitUpdateProStatus").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#status").val()) {

					isError = true;

					$("#error_status").show()
					//return false;
				} else {
					$("#error_status").hide()
				}

				if (!$("#proComp").val()) {

					isError = true;

					$("#error_proComp").show()

				} else {
					$("#error_proComp").hide()
				}

				if (!isError) {

					var x = confirm("Do you really want to submit the form?");
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

	<!-- Scrollable modal -->

	<!-- /scrollable modal -->
</body>
</html>