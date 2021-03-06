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

<%-- 
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
						<c:if test="${addAccess == 0}">
							<a href="${pageContext.request.contextPath}/addProjectHeader"
								class="breadcrumb-elements-item"> Add Project </a>
						</c:if>

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
								<td width="60%"><h5 class="card-title">Project List</h5></td>
								<%-- <td width="40%" align="right">
								 <a
									href="${pageContext.request.contextPath}/addProjectHeader"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary">Add Project  </button>
								</a> </td> --%>
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
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="printtable1">
							<thead>
								<tr class="bg-blue">

									<th width="3%">Sr. No.</th>
									<th width="15%">Project Title</th>
									<th>Project Description</th>
									<th>Project Type Title</th>
									<th>Project Manager</th>
									<th>Location Name</th>
									<th>Customer Name</th>
									<th width="8%" class="text-center">Project Completion</th>
									<th>Status</th>


									<th width="8%" class="text-center">Actions</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${projectHeaderList}" var="project"
									varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${project.projectTitle}</td>
										<td>${project.projectDesc}</td>
										<td>${project.projectTypeTitle}</td>
										<td>${project.empCode}</td>
										<td>${project.locName}</td>
										<td>${project.custName}</td>
										<td>${project.projectCompletion}</td>
										<c:if test="${project.projectStatus=='0'}">
											<td><span class="badge badge-success">Created</span></td>
										</c:if>

										<c:if test="${project.projectStatus eq '1'}">
											<td><span class="badge badge-info">Work in
													Progress</span></td>
										</c:if>
										<c:if test="${project.projectStatus eq '2'}">
											<td><span class="badge badge-secondary">Completed</span></td>
										</c:if>
										<c:if test="${project.projectStatus eq '3'}">
											<td><span class="badge badge-danger">Cancelled</span></td>
										</c:if>
										<c:if test="${project.projectStatus eq '4'}">
											<td><span class="badge badge-danger">On Hold</span></td>
										</c:if>


										<td class="text-center"><a
											href="${pageContext.request.contextPath}/projectAllotment?projectId=${project.exVar1}"
											title="Project Team"> <i class="fas fa-user-friends "
												style="color: black;"></i></a></td>
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