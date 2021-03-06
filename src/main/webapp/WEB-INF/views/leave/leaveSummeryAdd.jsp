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
								<td width="60%"><h5 class="card-title">Add Leave Summary</h5></td>
								<td width="40%" align="right">
							  
								 <a
									href="${pageContext.request.contextPath}/showLeaveSummaryList"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary">Leave Summary List</button>
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

							<form
									action="${pageContext.request.contextPath}/submitInsertLeaveSummary"
									id="submitInsertEmpDept" method="post">
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="deptName">Department
											Name <span style="color:red">* </span>:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Employee Department Name" id="deptName"
												name="deptName" autocomplete="off" onchange="trim(this)">
											<span class="validation-invalid-label" id="error_deptName"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="leaveSumName">Leave Summary Title <span style="color:red">* </span>:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Leave Summary Title" id="leaveSumName"
												name=leaveSumName autocomplete="off" onchange="trim(this)"
											> <span
												class="validation-invalid-label" id="error_leaveSumName"
												style="display: none;">This field is required.</span>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="leaveSumShortName">Leave Summary Short Title <span style="color:red">* </span>:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Leave Summary Short Title" id="leaveSumShortName"
												name=leaveSumShortName autocomplete="off" onchange="trim(this)"
											> <span
												class="validation-invalid-label" id="error_leaveSumShortName"
												style="display: none;">This field is required.</span>
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

		$(document).ready(function($) {

			$("#submitInsertEmpDept").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#deptName").val()) {

					isError = true;

					$("#error_deptName").show()
					//return false;
				} else {
					$("#error_deptName").hide()
				}

				if (!$("#leaveSumName").val()) {

					isError = true;

					$("#error_leaveSumName").show()

				} else {
					$("#error_leaveSumName").hide()
				}
				
				if (!$("#leaveSumShortName").val()) {

					isError = true;

					$("#error_leaveSumShortName").show()

				} else {
					$("#error_leaveSumShortName").hide()
				}

				if (!isError) {

					var x = true;
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