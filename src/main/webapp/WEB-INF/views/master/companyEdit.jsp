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
								<td width="60%"><h5 class="card-title">Edit Company</h5></td>
								<td width="40%" align="right">
							  
								 <%-- <a
									href="${pageContext.request.contextPath}/showAddKra?empId=${editKra.exVar3}&finYrId=${editKra.exVar2}"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary">KRA List </button>
								</a>  --%></td>
							</tr>
						</table>
					</div>


							<div class="card-body">
								<form
									action="${pageContext.request.contextPath}/submitEditCompany"
									id="submitInsertCompany" method="post"
									enctype="multipart/form-data">
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Company
											name <span style="color:red">* </span>: </label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Company Name" id="compName" name="compName" autocomplete="off"
												onchange="trim(this)" value="${editCompany.companyName}"> <span
												class="validation-invalid-label" id="error_compName"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compImg">Company
											Image : </label>
										<div class="col-lg-10">
											<input type="file" class="form-control" id="compImg" name="compImg" accept=".jpg,.png,.gif">
 											<!-- * Only jpg,gif,png * Best image size is 369px × 64px -->
										<!-- 	<span class="validation-invalid-label" id="error_compImg"
												style="display: none;">This field is required.</span> -->
												<input type="hidden"  id="imageName" name="imageName"   value="${editCompany.companyLogo}">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="remark">Any Remark :</label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Any Remark" onchange="trim(this)" id="remark" name="remark">${editCompany.companyRemarks}</textarea>
										</div>
									</div>

									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">
											
											<button type="submit" class="btn bg-blue ml-3 legitRipple" id="submtbtn">
												Submit <i class="icon-paperplane ml-2"></i>
											</button>
												<a href="${pageContext.request.contextPath}/showCompanyList"><button
										type="button" class="btn btn-primary"><i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp; Cancel</button></a>
										
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

			$("#submitInsertCompany").submit(function(e) {
				var isError = false;
				var errMsg = "";

				if (!$("#compName").val()) {

					isError = true;

					$("#error_compName").show()
					//return false;
				} else {
					$("#error_compName").hide()
				}

				if (!$("#imageName").val()) {

					isError = true;

					$("#error_compImg").show()

				} else {
					$("#error_compImg").hide()
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

</body>
</html>