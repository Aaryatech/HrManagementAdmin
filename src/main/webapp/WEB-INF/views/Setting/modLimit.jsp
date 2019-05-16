<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
<c:url var="updateLeaveLimit" value="/updateLeaveLimit" />
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
						<c:if test="${addAccess == 0}">
							<a href="${pageContext.request.contextPath}/employeeAdd"
								class="breadcrumb-elements-item"> Add Employee </a>
						</c:if>

					</div>


				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">


				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Leave Limit</h5>
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
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="printtable1">
							<thead>
								<tr class="bg-blue">
									<th width="10%">Sr.no</th>
									<th>Name</th>


									<th class="text-center" width="10%">Actions</th>
								</tr>
							</thead>
							<tbody>
 								<tr>
									<td>${count.index+1}</td>
									<td>${setlimit.group}</td>
									<td class="text-center"><input type="checkbox"
										class="form-check form-check-inline" id="checkSameAdd"
										${setlimit.value eq '1' ? 'checked' : 'checked'}
										name="checkSameAdd" onclick="checkAdd()"  /></td>

									<%-- 	<td class="text-center"> ${setlimit.value}
												</td>
												
												
												<td class="check" style="text-align: center;">
													</td> --%>
								</tr>


							</tbody>
						</table>
						val=${setlimit.value}
						<input type="hidden" name="setId"  id="setId" value="${setlimit.settingId}">
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
<script>
		function checkAdd() {
			//alert("hii..");
			var temp;
			if (document.getElementById("checkSameAdd").checked == true) {
 				temp=1;
 			} else {
 				temp=0;
			}
			//alert("hii.."+temp);
			var setId = document.getElementById("setId").value;
			var valid=true;
			
			 
				$
						.getJSON(
								'${updateLeaveLimit}',
								{
									temp : temp,
									setId : setId,
									ajax : 'true',

								},
								function(data) {

									 

								});
				 
			 

		}
	</script>

</body>
</html>