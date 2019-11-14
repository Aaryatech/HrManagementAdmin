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


				 
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					 
					
						<div class="card-header header-elements-inline">
 						<table width="100%">
							<tr width="100%">
								<td width="60%"><h5 class="card-title">Location List</h5></td>
								<td width="40%" align="right">
							  
								 <a
									href="${pageContext.request.contextPath}/locationAdd"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary">Add Location </button>
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
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="printtable1">
							<thead>
								<tr class="bg-blue">

									<th width="10%">Sr. No.</th>
									<th>Location Name</th>
									<th>Location Short Name</th>
									<th>Contact Person</th>
									<th>Contact Person No.</th>
									<th>Contact Person Email</th>
									<th width="10%" class="text-center">Actions</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${locationList}" var="locationList"
									varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${locationList.locName}</td>
										<td>${locationList.locNameShort}</td>
										<td>${locationList.locHrContactPerson}</td>
										<td>${locationList.locHrContactNumber}</td>
										<td>${locationList.locHrContactEmail}</td>

										<td class="text-center">
										
													
													<c:if test="${editAccess == 0}">
														<a
															href="${pageContext.request.contextPath}/editLocation?locId=${locationList.exVar1}"
															title="Edit"><i class="icon-pencil7"  style="color: black;"></i></a></c:if>
													<c:if
																test="${deleteAccess == 0}">
														<a
															href="${pageContext.request.contextPath}/deleteLocation?locId=${locationList.exVar1}"
															onClick="return confirm('Are you sure want to delete this record');"
															title="Delete"><i class="icon-trash"  style="color: black;"></i>
															</a></c:if>
												
										</td>
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