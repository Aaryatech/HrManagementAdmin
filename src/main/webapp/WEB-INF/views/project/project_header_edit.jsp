<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
						<a href="${pageContext.request.contextPath}/showProjectHeaderList"
							class="breadcrumb-elements-item"> Project List</a>

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
								<h6 class="card-title">Edit Project</h6>
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
									action="${pageContext.request.contextPath}/submitEditProjectHeader"
									id="submitInsertProjectHeader" method="post">

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Location <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="locId" data-placeholder="Select Location"
												id="locId"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Select Location</option>
												<%-- <c:forEach items="${locationList}" var="location">
													<option value="${location.locId}">${location.locName}</option>
												</c:forEach> --%>
												<c:forEach items="${locationList}" var="location">
													<c:choose>
														<c:when
															test="${location.locId == editProjectHeader.locId}">
															<option selected value="${location.locId}">${location.locName}</option>
														</c:when>
														<c:otherwise>
															<option value="${location.locId}">${location.locName}</option>
														</c:otherwise>

													</c:choose>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_locId"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Project Type <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="projectTypeId"
												data-placeholder="Select Project Type" id="projectTypeId"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Select Project Type</option>

												<c:forEach items="${projectTypelist}" var="projectType">
													<c:choose>
														<c:when
															test="${projectType.projectTypeId == editProjectHeader.projectTypeId}">
															<option selected value="${projectType.projectTypeId}">${projectType.projectTypeTitle}</option>
														</c:when>
														<c:otherwise>
															<option value="${projectType.projectTypeId}">${projectType.projectTypeTitle}</option>
														</c:otherwise>

													</c:choose>
												</c:forEach>
												<%-- <c:forEach items="${projectTypelist}" var="projectType">
													<option value="${projectType.projectTypeId}">${projectType.projectTypeTitle}</option>
												</c:forEach> --%>
											</select> <span class="validation-invalid-label"
												id="error_projectTypeId" style="display: none;">This
												field is required.</span>
										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Customer <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="custId" data-placeholder="Select Customer"
												id="custId"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Select Customer</option>

												<c:forEach items="${custlist}" var="cust">
													<c:choose>
														<c:when test="${cust.custId == editProjectHeader.custId}">
															<option selected value="${cust.custId}">${cust.custName}</option>
														</c:when>
														<c:otherwise>
															<option value="${cust.custId}">${cust.custName}</option>
														</c:otherwise>

													</c:choose>
												</c:forEach>



												<%-- 	<c:forEach items="${custlist}" var="cust">
													<option value="${cust.custId}">${cust.custName}</option>
												</c:forEach> --%>
											</select> <span class="validation-invalid-label" id="error_custId"
												style="display: none;">This field is required.</span>
										</div>
									</div>



									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Project Manager <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="empId"
												data-placeholder="Select Project Manager " id="empId"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Select Project Manager</option>

												<c:forEach items="${empList}" var="emp">
													<c:choose>
														<c:when
															test="${emp.empId == editProjectHeader.projectManagerEmpId}">
															<option selected value="${emp.empId}">${emp.empSname}${emp.empFname}</option>
														</c:when>
														<c:otherwise>
															<option value="${emp.empId}">${emp.empSname}&nbsp;${emp.empFname}</option>
														</c:otherwise>

													</c:choose>
												</c:forEach>
												<%-- <c:forEach items="${empList}" var="emp">
													<option value="${emp.empId}">${emp.empSname}${emp.empFname}</option>
												</c:forEach> --%>
											</select> <span class="validation-invalid-label" id="error_empId"
												style="display: none;">This field is required.</span>
										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="projectTitle">
											Project Title <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Project Title" id="projectTitle"
												value="${editProjectHeader.projectTitle}"
												name="projectTitle" autocomplete="off" onchange="trim(this)">
											<span class="validation-invalid-label"
												id="error_projectTitle" style="display: none;">This
												field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="projectDesc">
											Project Description <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Project Description" id="projectDesc"
												value="${editProjectHeader.projectDesc}" name="projectDesc"
												autocomplete="off" onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_projectDesc"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Billing Type<span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="billingType" data-placeholder="Please Select"
												id="billingType"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Please Select</option>

												<option ${editProjectHeader.exInt1 == '1'  ? 'Selected': '' }
													value="1">Support - Fix Monthly Billable</option>
												<option ${editProjectHeader.exInt1 == '2' ? 'Selected': '' }
													value="2">Project Revenue</option>

											</select> <span class="validation-invalid-label"
												id="error_billingType" style="display: none;">This
												field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="add">Revenue
											<span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control numbersOnly"
												placeholder="Revenue Amount" id="project_revenue"
												value="${editProjectHeader.exVar1}" name="project_revenue"
												autocomplete="off" onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_project_revenue"
												style="display: none;">This field is required.</span>
										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="add">Project
											City <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control numbersOnly"
												placeholder="Enter Project City" id="projectCity"
												value="${editProjectHeader.projectCity}" name="projectCity"
												autocomplete="off" onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_projectCity"
												style="display: none;">This field is required.</span>
										</div>
									</div>





									<!-- <div class="form-group row">
										<label class="col-form-label col-lg-2">Project
											Establishment Date :*</label>
										<div class="col-lg-10">
											<input type="text" class="form-control daterange-basic_new "
												value="21-04-2019 @ 21-05-2019" name="dateRange"
												data-placeholder="Select Date" id="dateRange"> <span
												class="validation-invalid-label" id="error_Range"
												style="display: none;">This field is required.</span>

										</div>
									</div> -->

									<div class="form-group row">
										<label class="col-form-label col-lg-2">Project
											Estimated Start Date :</label>
										<div class="col-lg-10">
											<input type="text" class="form-control datepickerclass "
												value="${editProjectHeader.projectEstStartdt}"
												value="21-04-2019" name="fromDate" id="fromDate">
										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2">Project
											Estimated End Date:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control datepickerclass "
												value="${editProjectHeader.projectEstEnddt}"
												value="21-04-2019" name="toDate" id="toDate">
										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="add">Project
											Estimated Man Hours <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control numbersOnly"
												placeholder="Project Establishment Man Hours"
												value="${editProjectHeader.projectEstManhrs}"
												id="project_est_manhrs" name="project_est_manhrs"
												autocomplete="off" onchange="trim(this)"> <span
												class="validation-invalid-label"
												id="error_project_est_manhrs" style="display: none;">This
												field is required.</span>
										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="add">Project
											Estimated Budget <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control numbersOnly"
												placeholder="Project Establishment Budget"
												id="project_est_budget" name="project_est_budget"
												value="${editProjectHeader.projectEstBudget}"
												autocomplete="off" onchange="trim(this)"> <span
												class="validation-invalid-label"
												id="error_project_est_budget" style="display: none;">This
												field is required.</span>
										</div>
									</div>

									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">
											<!-- <button type="reset" class="btn btn-light legitRipple">Reset</button> -->
											<button type="submit" class="btn bg-blue ml-3 legitRipple"
												id="submtbtn">
												Submit <i class="icon-paperplane ml-2"></i>
											</button>
											<a
												href="${pageContext.request.contextPath}/showProjectHeaderList"><button
													type="button" class="btn btn-primary">
													<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;
													Cancel
												</button></a>
										</div>
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

			$("#submitInsertProjectHeader").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#locId").val()) {

					isError = true;

					$("#error_locId").show()
					//return false;
				} else {
					$("#error_locId").hide()
				}

				if (!$("#custId").val()) {

					isError = true;

					$("#error_custId").show()

				} else {
					$("#error_custId").hide()
				}

				if (!$("#empId").val()) {

					isError = true;

					$("#error_empId").show()

				} else {
					$("#error_empId").hide()
				}

				if (!$("#projectTypeId").val()) {

					isError = true;

					$("#error_projectTypeId").show()

				} else {
					$("#error_projectTypeId").hide()
				}

				if (!$("#projectTitle").val()) {

					isError = true;

					$("#error_projectTitle").show()

				} else {
					$("#error_projectTitle").hide()
				}

				if (!$("#projectDesc").val()) {

					isError = true;

					$("#error_projectDesc").show()

				} else {
					$("#error_projectDesc").hide()
				}

				if (!$("#projectCity").val()) {

					isError = true;

					$("#error_projectCity").show()

				} else {
					$("#error_projectCity").hide()
				}

				if (!$("#project_est_manhrs").val()) {

					isError = true;

					$("#error_project_est_manhrs").show()

				} else {
					$("#error_project_est_manhrs").hide()
				}

				if (!$("#project_est_budget").val()) {

					isError = true;

					$("#error_project_est_budget").show()

				} else {
					$("#error_project_est_budget").hide()
				}
				if (!$("#billingType").val()) {

					isError = true;

					$("#error_billingType").show()

				} else {
					$("#error_billingType").hide()
				}
				if (!$("#project_revenue").val()) {

					isError = true;

					$("#error_project_revenue").show()

				} else {
					$("#error_project_revenue").hide()
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

	<!-- <script type="text/javascript">
	$('#submtbtn').on('click', function() {
        swalInit({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
            buttonsStyling: false
        }).then(function(result) {
            if(result.value) {
                swalInit(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                );
            }
            else if(result.dismiss === swal.DismissReason.cancel) {
                swalInit(
                    'Cancelled',
                    'Your imaginary file is safe :)',
                    'error'
                );
            }
        });
    });
	
	</script> -->

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