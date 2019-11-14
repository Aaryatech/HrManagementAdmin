<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"
	import="  org.springframework.util.MultiValueMap"
	import="  com.ats.hradmin.model.LoginResponse"
	import="  org.springframework.util.LinkedMultiValueMap"
	import="  com.ats.hradmin.model.ActivityRevenueReport"
	import="  com.ats.hradmin.common.Constants" import=" java.text.DecimalFormat"%>
<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
<c:url var="empInfoCountList" value="/empInfoCountList" />
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
								<td width="60%">
									<h5 class="card-title">Project Activity Wise Revenue Report</h5>
								</td>
								<td width="40%" align="right"></td>
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

						<form action="${pageContext.request.contextPath}/onbenchReport"
							id="onbenchReport" method="GET">

							<%-- 	<div class="form-group row">
							<div class="col-md-2"></div>
								<label class="col-form-label col-lg-1" for="fromDate">From
									Date :*</label>
								<div class="col-md-2">
									<input type="text" class="form-control datepickerclass "
										name="fromDate" id="fromDate" value="${fromDate}" autocomplete="off"> <span
										class="validation-invalid-label" id="error_fromDate"
										style="display: none;">This field is required.</span>
								</div>

								<div class="col-md-1"></div>
								<label class="col-form-label col-lg-1" for="toDate">To
									Date :*</label>
								<div class="col-md-2">
									<input type="text" class="form-control datepickerclass "
										name="toDate" id="toDate" autocomplete="off" value="${toDate}"> <span
										class="validation-invalid-label" id="error_toDate"
										style="display: none;">This field is required.</span>
								</div>

								 
							</div>
							
							<div class="co-md-12" style="text-align: center;">
							<input type="submit" class="btn bg-blue ml-3 legitRipple"
									id="submtbtn" value="Submit">
							</div> --%>
						</form>
						<div id='loader' style='display: none;'>
							<img
								src='${pageContext.request.contextPath}/resources/assets/images/giphy.gif'
								width="150px" height="150px"
								style="display: block; margin-left: auto; margin-right: auto">
						</div>
						<%
						DecimalFormat df = new DecimalFormat("0.00");
							LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
							MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
							map.add("compId", userObj.getCompanyId());

							ActivityRevenueReport[] activityRevenueReport = Constants.getRestTemplate().postForObject(
									Constants.url + "/revenueReportProjectCategoryWise", map, ActivityRevenueReport[].class);
							List<ActivityRevenueReport> list = new ArrayList<>(Arrays.asList(activityRevenueReport));
						%>
						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="bootstrap-data-table">
							<thead>
								<tr class="bg-blue" style="text-align: center;">
									<th width="10%">Sr.no</th>
									<th>Activity</th>
									<th>Revenue</th>
									<th>Resource Cost</th>
									<th>Profit</th>
								</tr>
							</thead>
							<tbody>


								<%
									for (int i = 0; i < list.size(); i++) {
								%>
								<tr>
									<td>
										<%
											out.println(i + 1);
										%>
									</td>
									<td>
										<%
											out.println(list.get(i).getProjectTypeTitle());
										%>
									</td>
									<td align="right">
										<%
											out.println(df.format(list.get(i).getRevenue()));
										%>
									</td>
									<td align="right">
										<%
											out.println(df.format(list.get(i).getResourceCost()));
										%>
									</td>
									<td align="right">
										<%
											out.println(df.format(list.get(i).getRevenue() - list.get(i).getResourceCost()));
										%>
									</td>

								</tr>
								<%
									}
								%>

								<%-- <c:forEach items="${list}" var="list" varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${list.projectTypeTitle}</td>
										<td align="right">${list.revenue}</td>
										<td align="right">${list.resourceCost}</td>
										<td align="right">${list.revenue-list.resourceCost}</td>

									</tr>
								</c:forEach> --%>

							</tbody>
						</table>
					</div>

				</div>
				<!-- /highlighting rows and columns -->


				<!-- /content area -->
			</div>

			<!-- Footer -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
			<!-- /footer -->

		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->
	<script type="text/javascript">
		// Single picker
		$('.datepickerclass').daterangepicker({
			singleDatePicker : true,
			selectMonths : true,
			selectYears : true,
			locale : {
				format : 'DD-MM-YYYY'
			}
		});

		//daterange-basic_new
		// Basic initialization
		$('.daterange-basic_new').daterangepicker({
			applyClass : 'bg-slate-600',

			cancelClass : 'btn-light',
			locale : {
				format : 'DD-MM-YYYY',
				separator : ' to '
			}
		});
	</script>

</body>
</html>