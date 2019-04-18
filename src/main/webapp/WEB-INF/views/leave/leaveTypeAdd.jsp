<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib
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
<div class="breadcrumb justify-content-center">
						<a href="${pageContext.request.contextPath}/showLeaveTypeList"
							class="breadcrumb-elements-item">  Leave Type List </a>

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
								<h6 class="card-title">Add Leave Type</h6>
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
									action="${pageContext.request.contextPath}/submitInsertLeaveType"
									id="submitInsertCompany" method="post"
								>
									
									
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="leaveTypeTitle">
											 Title:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Leave Type" id="leaveTypeTitle"
												name="leaveTypeTitle" autocomplete="off" onchange="trim(this)">
											<span class="validation-invalid-label" id="error_leaveType"
												style="display: none;">This field is required.</span>
										</div>
									</div>

												<div class="form-group row">
										<label class="col-form-label col-lg-2" for="leaveShortTypeTitle">
											Short Title:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Leave Short Name " id="leaveShortTypeTitle"
												name="leaveShortTypeTitle" autocomplete="off" onchange="trim(this)">
											<span class="validation-invalid-label" id="error_leaveScType"
												style="display: none;">This field is required.</span>
										</div>
									</div>


								<div class="form-group row">
										<label class="col-form-label col-lg-2" for="leaveWorlHrs">Working Hours
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Leave Working Hours " id="leaveWorlHrs"
												name="leaveWorlHrs" autocomplete="off" onchange="trim(this)">
											<span class="validation-invalid-label" id="error_leaveWHours"
												style="display: none;">This field is required.</span>
										</div>
									</div>



									<div class="form-group row">
										<label class="col-form-label col-lg-2">Color:</label>
										<div class="col-lg-10">
											<input type="text" id="leaveColor" name="leaveColor" class="form-control colorpicker-show-input" data-preferred-format="hex" value="#f75d1c" data-fouc>
										</div>
									</div>
											<div class="form-group row">
										<label class="col-form-label col-lg-2" for="comoffallowed">Is Structured
											: *</label>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												class="form-check-input" name="isStructured"
												id="isStructured" checked value="1"> Yes
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												class="form-check-input" name="isStructured"
												id="isStructured" value="0"> No
											</label>
										</div>
									</div>
									
									
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Leave Summary:</label>
										<div class="col-lg-10">
											<select name="summId" data-placeholder="Select a Summary" id="summId"
												class="form-control form-control-select2 select2-hidden-accessible"
												required data-fouc="" tabindex="-1" aria-hidden="true">
										
											<c:forEach items="${sumList}" var="sumList">
																		
											<option value="${sumList.lvSumupId}">${sumList.lvSumupTitle}</option>
 
									</c:forEach>

											
											</select>
										</div>
									</div>
											
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="remark">Any
											Remark:</label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Any Remark" onchange="trim(this)" id="remark"
												name="remark"></textarea>
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

		function numbersOnlyNotZero(id_number) {

			var mob = /^[1-9][0-9]+$/;

			if (mob.test($.trim(id_number)) == false) {

				//alert("Please enter a valid email address .");
				return false;

			}
			return true;
		}

		
		$(document).ready(function($) {

			$("#submitInsertCompany").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#leaveTypeTitle").val()) {

					isError = true;

					$("#error_leaveType").show()
					//return false;
				} else {
					$("#error_leaveType").hide()
				}


				if (!$("#leaveShortTypeTitle").val()) {

					isError = true;

					$("#error_leaveScType").show()
					//return false;
				} else {
					$("#error_leaveScType").hide()
				}

				

				if (!$("#leaveWorlHrs").val()) {

					isError = true;

					$("#error_leaveWHours").show()
					//return false;
				} else {
					$("#error_leaveWHours").hide()
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