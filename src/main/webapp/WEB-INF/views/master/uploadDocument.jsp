<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
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
						<a href="${pageContext.request.contextPath}/showEmpList"
							class="breadcrumb-elements-item"> Employee List</a>

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
								<h6 class="card-title">Upload Document</h6>
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
									action="${pageContext.request.contextPath}/submitInsertEmpDoc"
									id="submitInsertEmpTypee" method="post"
									enctype="multipart/form-data">
									
									<div class="form-group row">
										<%-- <label class="col-form-label col-lg-1" for="empShortName">Employee Code :</label>
										<div class="col-lg-2">
											<div class="form-check form-check-inline">
												 ${empInfo.empCode}</div>
										</div> --%>
										<label class="col-form-label col-lg-1" for="empShortName">Employee Name :</label>
										<div class="col-lg-5">
											<div class="form-check form-check-inline">
												${empInfo.empSname} ${empInfo.empFname}</div>
										</div>
									</div>


									<c:forEach items="${docList}" var="docList" varStatus="count">
										<div class="form-group row">
											<label class="col-form-label col-lg-2"
												for="doc${count.index}"> ${docList.doctypeName} <c:if
													test="${docList.isRequired==1}">
													<span class="text-danger">*</span>
												</c:if>
											</label>
											<div class="col-lg-5">

												<c:choose>
													<c:when
														test="${docList.imageName eq '0' && docList.isRequired==1}">


														<input type="file" class="form-control"
															id="doc${count.index}" name="doc"
															accept=".jpg,.png,.gif,.doc,.xls,.pdf" required> 
														<span class="form-text text-muted">Only .jpg,.png,.gif,.doc,.xls,.pdf</span>
														<span class="validation-invalid-label"
															id="error_doc${docList.doctypeId}" style="display: none;">This
															field is required.</span>
													</c:when>
													<c:otherwise>

														<input type="file" class="form-control"
															id="doc${count.index}" name="doc"
															accept=".jpg,.png,.gif,.doc,.xls,.pdf"> 
														<span class="form-text text-muted">Only .jpg,.png,.gif,.doc,.xls,.pdf</span>
														<span class="validation-invalid-label"
															id="error_doc${docList.doctypeId}" style="display: none;">This
															field is required.</span>

													</c:otherwise>
												</c:choose>
											</div>
											<c:if test="${docList.imageName != '0'}">
												<div class="col-lg-5">
													<a href="${docUrl}${docList.imageName}" target="_blank" title="Open File"> <i class="far fa-file-alt mr-3 fa-2x" style="color: black;"></i>
													</a>
												</div>
											</c:if>
											<input type="hidden" name="prevName${count.index}"
												id="prevName${count.index}" value="${docList.imageName}">
											<span class="validation-invalid-label"
												id="error_fileName${count.index}" style="display: none;">This
												field is required.</span>
										</div>
									</c:forEach>


									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">

											<button type="submit" class="btn bg-blue ml-3 legitRipple"
												id="submtbtn">
												Submit <i class="icon-paperplane ml-2"></i>
											</button>
											<a href="${pageContext.request.contextPath}/showEmpList"><button
													type="button" class="btn btn-primary">
													<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;
													Cancel
												</button></a> <input type="hidden" name="empId" id="empId"
												value="${empId}">
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


</body>
</html>