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


				<%-- <div
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
						<a href="${pageContext.request.contextPath}/showApplyForClaim"
							class="breadcrumb-elements-item"> Employee List </a>

					</div>


				</div> --%>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					 
					
						
					<div class="card-header header-elements-inline">
 						<table width="100%">
							<tr width="100%">
								<td width="60%"><h5 class="card-title">Employee Claim Details List</h5></td>
								<td width="40%" align="right">
								 <a
									href="${pageContext.request.contextPath}/showApplyForClaim"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary">Employee List  </button>
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
						<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Employee Code
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												 Value="${lvEmp.empCode}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Employee Name
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												 Value="${lvEmp.empFname} ${lvEmp.empSname}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Claim Title
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												 Value="${lvEmp.claimTitle}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Claim Date
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												  Value="${lvEmp.caFromDt} to ${lvEmp.caToDt} "
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
									
						
						<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Claim Amount
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												 Value="${lvEmp.claimAmount}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="compName">Project Title
											: </label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												 Value="${lvEmp.projectTitle}"
												name="compName" autocomplete="off" readonly>
											
										</div>
									</div>
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic1  datatable-button-print-columns1"
							id="printtable1">
							<thead>
								<tr class="bg-blue">
									<th width="10%">Sr.no</th>
									<th>Claim Type</th>
									<th>Amount</th>
									<th>Remark</th>

								</tr>
							</thead>
							<tbody>


								<c:forEach items="${claimList1}" var="lvTypeList"
									varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${lvTypeList.claimTypeTitle}</td>
										<td>${lvTypeList.claimAmount}</td>
										<td>${lvTypeList.claimRemarks}</td>


									</tr>
								</c:forEach>

							</tbody>
						</table>
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