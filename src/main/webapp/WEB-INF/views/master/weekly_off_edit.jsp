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
						<a href="${pageContext.request.contextPath}/showWeeklyOffList"
							class="breadcrumb-elements-item"> Weekly Off List</a>

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
								<h6 class="card-title">Add Weekly Off</h6>
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
									action="${pageContext.request.contextPath}/submitEditInsertWeeklyOff"
									id="submitInsertWeeklyOff" method="post">
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Location :*</label>
										<div class="col-lg-10">
											<select name="locId" data-placeholder="Select Location"
												id="locId"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Please Select</option>
												<c:forEach items="${locationList}" var="location">

													<c:choose>
														<c:when test="${location.locId == editWeeklyOff.locId}">
															<option value="${location.locId}" selected="selected">${location.locName}</option>
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
											Weekly Off Type :*</label>
										<div class="col-lg-10">
											<select name="woType" data-placeholder="Please Select"
												id="woType"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Please Select</option>

												<option ${editWeeklyOff.woType == '0'  ? 'Selected': '' } value="0">All</option>
												<option ${editWeeklyOff.woType == '1' ? 'Selected': '' } value="1">Even</option>
												<option ${editWeeklyOff.woType == '2'  ? 'Selected': '' } value="2">Odd</option>
												<option ${editWeeklyOff.woType == '3' ? 'Selected': '' } value="3">1st</option>
												<option ${editWeeklyOff.woType == '4'  ? 'Selected': '' } value="4">2nd</option>
												<option ${editWeeklyOff.woType == '5'  ? 'Selected': '' } value="5">3rd</option>
												<option ${editWeeklyOff.woType == '6'  ? 'Selected': '' } value="6">4th</option>
												<!-- <option value="All">All</option>
												<option value="Even">Even</option>
												<option value="Odd">Odd</option>
												<option value="1st">1st</option>
												<option value="2nd">2nd</option>
												<option value="3rd">3rd</option>
												<option value="4th">4th</option> -->


											</select> <span class="validation-invalid-label" id="error_woType"
												style="display: none;">This field is required.</span>
										</div>
									</div>



									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Weekly Off Presently :*</label>
										<div class="col-lg-10">
											<select name="woPresently" data-placeholder="Please Select"
												id="woPresently"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Please Select</option>
												<!-- <option value="FULL DAY">FULL DAY</option>
												<option value="HALF DAY">HALF DAY</option> -->


												<option
													${editWeeklyOff.woPresently == 'FULL DAY'  ? 'Selected': '' }>FULL
													DAY</option>
												<option
													${editWeeklyOff.woPresently == 'HALF DAY' ? 'Selected': '' }>HALF
													DAY</option>


											</select> <span class="validation-invalid-label"
												id="error_woPresently" style="display: none;">This
												field is required.</span>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Weekly Off Day :*</label>
										<div class="col-lg-10">
											<select name="woDay" data-placeholder="Please Select"
												id="woDay"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Please Select</option>

												<option
													${editWeeklyOff.woDay == '0'  ? 'Selected': '' } value="0">SUNDAY</option>
												<option ${editWeeklyOff.woDay == '1' ? 'Selected': '' } value="1">MONDAY</option>
												<option
													${editWeeklyOff.woDay == '2'  ? 'Selected': '' } value="2">TUESDAY</option>
												<option
													${editWeeklyOff.woDay == '3' ? 'Selected': '' } value="3">WEDNESDAY</option>
												<option
													${editWeeklyOff.woDay == '4'  ? 'Selected': '' } value="4">THURSDAY</option>
												<option
													${editWeeklyOff.woDay == '5'  ? 'Selected': '' } value="5">FRIDAY</option>
												<option
													${editWeeklyOff.woDay == '6'  ? 'Selected': '' } value="6">SATURDAY</option>
												<!-- <option value="SUNDAY">SUNDAY</option>
												<option value="MONDAY">MONDAY</option>
												<option value="TUESDAY">TUESDAY</option>
												<option value="WEDNESDAY">WEDNESDAY</option>
												<option value="THURSDAY">THURSDAY</option>
												<option value="FRIDAY">FRIDAY</option>
												<option value="SATURDAY">SATURDAY</option> -->
											</select> <span class="validation-invalid-label" id="error_woDay"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="remark">Remark
											: </label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Any Remark" onchange="trim(this)"
												id="woRemarks" name="woRemarks"></textarea>

										</div>
									</div>

									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">
											<button type="reset" class="btn btn-light legitRipple">Reset</button>
											<button type="submit" class="btn bg-blue ml-3 legitRipple"
												id="submtbtn">
												Submit <i class="icon-paperplane ml-2"></i>
											</button>
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

			$("#submitInsertWeeklyOff").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#locId").val()) {

					isError = true;

					$("#error_locId").show()
					//return false;
				} else {
					$("#error_locId").hide()
				}

				if (!$("#woType").val()) {

					isError = true;

					$("#error_woType").show()

				} else {
					$("#error_woType").hide()
				}

				if (!$("#woPresently").val()) {

					isError = true;

					$("#error_woPresently").show()

				} else {
					$("#error_woPresently").hide()
				}

				if (!$("#woDay").val()) {

					isError = true;

					$("#error_woDay").show()

				} else {
					$("#error_woDay").hide()
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