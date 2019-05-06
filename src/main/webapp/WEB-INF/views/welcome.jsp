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





				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-body">
					<div class="card-header header-elements-inline">
						<h3 class="card-title">WELCOME</h3>
							</div>
						
								<h6 class="card-title">Leave Authority</h6>
								
						
							<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Initial Authority
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												placeholder="Enter Company Name" id="compName" Value="${iniFname}${space1}${iniSname}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Final Authority
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												placeholder="Enter Company Name" id="compName" Value="${finFname}${space1}${finSname}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
						
					<h6 class="card-title">Claim Authority</h6>
								
						<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Initial Authority
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												placeholder="Enter Company Name" id="compName" Value="${iniClFname}${space1}${iniClSname}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Final Authority
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												placeholder="Enter Company Name" id="compName" Value="${finClFname}${space1}${finClSname}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
						
						
						
				
				

						<%
							if (session.getAttribute("errorMsg") != null) {
						%>
						 

						<%
							session.removeAttribute("errorMsg");
							}
						%>
						<%
							if (session.getAttribute("successMsg") != null) {
						%>
						 
						<%
							session.removeAttribute("successMsg");
							}
						%>

					</div>

				</div>
				<!-- /highlighting rows and columns -->

			</div>
			<!-- /content area -->


			<!-- Footer -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
			<!-- /footer -->

		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->

</body>
</html>