<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
										<td width="60%"><h5 class="card-title">Add Employee Document</h5></td>
										<td width="40%" align="right">
											<a
									href="${pageContext.request.contextPath}/showEmpTypeList"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary"> Employee Document List </button>
								</a> 
										</td>
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

								<form
									action="${pageContext.request.contextPath}/submitInsertEmpDoc"
									id="submitInsertEmpType" method="post">

									<div class="form-group row">
										<label class="col-form-label col-lg-3">Select Employee
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-9">
											<select name="select2" data-placeholder="Select a State..."
												class="form-control form-control-select2 select2-hidden-accessible"
												required="" data-fouc="" tabindex="-1" aria-hidden="true">
												<option></option>
												<optgroup label="Alaskan/Hawaiian Time Zone">
													<option value="AK">Alaska</option>
													<option value="HI">Hawaii</option>
												</optgroup>

												</optgroup>
											</select><span
												class="select2 select2-container select2-container--default"
												dir="ltr" style="width: 100%;"><span
												class="selection"><span
													class="select2-selection select2-selection--single"
													role="combobox" aria-haspopup="true" aria-expanded="false"
													tabindex="0" aria-labelledby="select2-select2-fy-container"><span
														class="select2-selection__rendered"
														id="select2-select2-fy-container"><span
															class="select2-selection__placeholder">Select a
																State...</span></span><span class="select2-selection__arrow"
														role="presentation"><b role="presentation"></b></span></span></span><span
												class="dropdown-wrapper" aria-hidden="true"></span></span> <label
												id="select2-error" class="validation-invalid-label"
												for="select2">This field is required.</label>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="empTypeName">Employee
											Type Name : *</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Employee Type Name" id="empTypeName"
												name="empTypeName" autocomplete="off" onchange="trim(this)">
											<span class="validation-invalid-label" id="error_empTypeName"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="empShortName">Employee
											Type Short Name : *</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Employee Type Short Name" id="empShortName"
												name="empShortName" autocomplete="off" onchange="trim(this)"
												maxlength="5"> <span
												class="validation-invalid-label" id="error_empShortName"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="comoffallowed">Comp
											Off Request Allowed : *</label>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												class="form-check-input" name="comoffallowed"
												id="comoffallowed" checked value="1"> Yes
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												class="form-check-input" name="comoffallowed"
												id="comoffallowed" value="0"> No
											</label>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="remark">Remark
											: </label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Any Remark" onchange="trim(this)" id="remark"
												name="remark"></textarea>

										</div>
									</div>

									Remaining assign role

									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">
											<button type="reset" class="btn btn-light legitRipple">Reset</button>
											<button type="submit" class="btn bg-blue ml-3 legitRipple"
												id="submtbtn">
												Submit <i class="icon-paperplane ml-2"></i>
											</button>
											<a href="${pageContext.request.contextPath}/showEmpTypeList"><button
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

		$(document).ready(function($) {

			$("#submitInsertEmpType").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#empTypeName").val()) {

					isError = true;

					$("#error_empTypeName").show()
					//return false;
				} else {
					$("#error_empTypeName").hide()
				}

				if (!$("#empShortName").val()) {

					isError = true;

					$("#error_empShortName").show()

				} else {
					$("#error_empShortName").hide()
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

</body>
</html>