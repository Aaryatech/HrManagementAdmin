<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
								<td width="60%"><h5 class="card-title">Add Customer</h5></td>
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
										action="${pageContext.request.contextPath}/submitEditCustomer"
										id="submitEditCustomer" method="post">


										<div class="form-group row">
											<label class="col-form-label col-lg-2" for="custName">
												Project Type Title <span style="color:red">* </span>: </label>
											<div class="col-lg-10">
												<input type="text" class="form-control"
													placeholder="Enter Customer Name" id="custName"
													value="${editCust.custName}" name="custName"
													autocomplete="off" onchange="trim(this)"> <span
													class="validation-invalid-label" id="error_custName"
													style="display: none;">This field is required.</span>
											</div>
										</div>


										<div class="form-group row">
											<label class="col-form-label col-lg-2" for="remark">Any
												Remark :</label>
											<div class="col-lg-10">
												<textarea rows="3" cols="3" class="form-control"
													placeholder="Any Remark" onchange="trim(this)" id="remark"
													name="remark">${editCust.custRemarks}</textarea>
											</div>
										</div>

										<div class="form-group row mb-0">
											<div class="col-lg-10 ml-lg-auto">

												<button type="submit" class="btn bg-blue ml-3 legitRipple"
													id="submtbtn">
													Submit <i class="icon-paperplane ml-2"></i>
												</button>
													<a href="${pageContext.request.contextPath}/showCustomerList"><button
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

			function numbersOnlyNotZero(id_number) {

				var mob = /^[1-9][0-9]+$/;

				if (mob.test($.trim(id_number)) == false) {

					//alert("Please enter a valid email address .");
					return false;

				}
				return true;
			}

			$(document)
					.ready(
							function($) {

								$("#submitEditCustomer")
										.submit(
												function(e) {
													var isError = false;
													var errMsg = "";

													if (!$("#custName").val()) {

														isError = true;

														$("#error_custName")
																.show()
														//return false;
													} else {
														$("#error_custName")
																.hide()
													}

													if (!isError) {

														var x = true;
														if (x == true) {

															document
																	.getElementById("submtbtn").disabled = true;
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