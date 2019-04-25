<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<c:url var="addStrDetail" value="/addStrDetail" />

<c:url var="getLeaveStructureForEdit" value="/getLeaveStructureForEdit" />

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
</head>

<body>
	<c:url value="/chkNum" var="chkNum"></c:url>

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
						<a
							href="${pageContext.request.contextPath}/showLeaveStructureList"
							class="breadcrumb-elements-item"> Leave Structure List</a>

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
								<h6 class="card-title">Add Leave Structure</h6>
								<div class="header-elements">
									<div class="list-icons">
										<a class="list-icons-item" data-action="collapse"></a>
									</div>
								</div>
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
<div class="form-group row">
										<label class="col-form-label col-lg-2" for="lvsName">
											Employee Code : *</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Leave Structure Name" id="lvsName"   value="${editEmp.empCode}" 
												name="lvsName" autocomplete="off" onchange="trim(this)" readonly>
											
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="lvsName">
											Employee Name : *</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Leave Structure Name" id="lvsName"  value="${editEmp.empFname} ${editEmp.empMname} ${editEmp.empSname}   "
												name="lvsName" autocomplete="off" onchange="trim(this)" readonly>
											
										</div>
									</div>
									<hr>
									
								<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
							id="printtable1">
						
						
							<thead>
								<tr class="bg-blue">
								
									<th></th>
									<th>OB</th>
									<th>Earned</th>
									<th>Sanction</th>
									<th>Balanced</th>
									<th>Applied</th>
									
									 
									
								</tr>
							</thead>
							<tbody>
	<c:forEach items="${leaveHistoryList}" var="leaveHistoryList" >
									<tr>
										<td>${leaveHistoryList.lvTitleShort}</td>
										<td>${leaveHistoryList.balLeave}</td>
										<td>${leaveHistoryList.balLeave+leaveHistoryList.lvsAllotedLeaves}</td>
										<td>${leaveHistoryList.sactionLeave}</td>
										<td>${leaveHistoryList.lvsAllotedLeaves-leaveHistoryList.sactionLeave}</td>
										<td>${leaveHistoryList.aplliedLeaeve}</td>
										
										
									</tr>
								</c:forEach>
									
							


							</tbody>
						</table>
								<form
									action="${pageContext.request.contextPath}/insertLeave"
									id="submitInsertLeave" method="post">
									
									
									
										<div class="form-group row">
										<label class="col-form-label col-lg-2" for="leaveTypeId">Select
											Leave Type :</label>
										<div class="col-lg-4">
											<select name="leaveTypeId"
												data-placeholder="Select Leave Type" id="leaveTypeId"
												class="form-control form-control-select2 select2-hidden-accessible"
												 data-fouc="" tabindex="-1" aria-hidden="true">
												<option></option>
												<c:forEach items="${leaveHistoryList}" var="leaveHistoryList">
													<c:choose>
														<c:when test="${leaveHistoryList.lvTypeId == editLeave.lvTypeId}">
															<option value="${leaveHistoryList.lvTypeId}" selected="selected">${leaveHistoryList.lvTitle}</option>
														</c:when>
														<c:otherwise>
															<option value="${leaveHistoryList.lvTypeId}">${leaveHistoryList.lvTitle}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_leaveTypeId"
												style="display: none;">This field is required.</span>
										</div>
										
											
										
										<div class="col-lg-2">
											<select  data-placeholder="Select a Day Type" id="dayTypeName" name="dayTypeName"
												class="form-control form-control-select2 select2-hidden-accessible"
												 data-fouc="" tabindex="-1" aria-hidden="true">
												<option></option>		
											<option value="1">Full Day</option>
											<option value="2">Half Day</option>

											
											</select><span class="validation-invalid-label" id="error_dayType"
												style="display: none;">This field is required.</span>
										</div>
										</div>
											<div class="form-group row">
										<label class="col-form-label col-lg-2">Date Range:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control daterange-basic_new "
												name="leaveDateRange" data-placeholder="Select Date"
												id="leaveDateRange" onchange="calculateDiff()"> <span
												class="validation-invalid-label" id="error_Range"
												style="display: none;">This field is required.</span>

										</div>
									</div>
									
										
									
									
	<div class="form-group row">
										<label class="col-form-label col-lg-2" for="noOfDays">
										No. of Days : *</label>
										<div class="col-lg-4">
											<input type="text" class="form-control numbersOnly" 
												placeholder="No. of Days " id="noOfDays"
												name="noOfDays" autocomplete="off" readonly>
											<span class="validation-invalid-label" id="error_noOfDays"
												style="display: none;">This field is required.</span>
										</div>
										</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="noOfDaysExclude">
											Excluding Weekly Off *</label>
										<div class="col-lg-4">
											<input type="text" class="form-control numbersOnly"
												placeholder="Excluding Weekly Off: " id="noOfDaysExclude"  
												name="noOfDaysExclude" autocomplete="off" onchange="trim(this)" readonly>
											<span class="validation-invalid-label" id="error_noOfDaysExclude"
												style="display: none;">This field is required.</span>
										</div>
									</div>

						<div class="form-group row">
										<label class="col-form-label col-lg-2" for="lvngReson">Remark :  </label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Remark" onchange="trim(this)"
												id="leaveRemark" name="leaveRemark"> </textarea>
												</div>
									</div>
										<input type="hidden" class="form-control numbersOnly"
												id="empId"  value="${empId}"
												name="empId" >
												
													<input type="hidden" class="form-control numbersOnly"
												id="tempNoDays" 
												name="tempNoDays" >
									
									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">

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
	<script type="text/javascript">
	function calculateDiff() {
		 
		var daterange = document.getElementById("leaveDateRange").value;
		var res = daterange.split(" to ");
		 
		var date1res = res[0].split("-");
		var date2res = res[1].split("-");
		
		var date1 = new Date(date1res[2],date1res[1]-1,date1res[0])//converts string to date object
        
        var date2 = new Date(date2res[2],date2res[1]-1,date2res[0])
         
        const diffTime = Math.abs(date2.getTime() - date1.getTime());
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
        document.getElementById("noOfDays").value =  diffDays+1 ;
        
        document.getElementById("noOfDaysExclude").value =  diffDays+1 ;
	}
	
	
	
		function checkUnique() {
			var inputValue = document.getElementById("leaveTypeId").value;
			var valid = false;
			
			if(inputValue!=null){
				valid == true;
			}
			if (valid == true)
				$
						.getJSON(
								'${chkNum}',
								{

									inputValue : inputValue,
									ajax : 'true',

								},
								function(data) {

									//alert("Data  " +JSON.stringify(data));
									if (data.error == true) {
										
									}
								});
		}

		
	
	
	</script>
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

	<script>
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		
		$(document)
				.ready(
						function($) {

							$("#submitInsertLeave")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#leaveTypeId").val()) {

													isError = true;

													$("#error_leaveTypeId").show()
													//return false;
												} else {
													$("#error_leaveTypeId").hide()
												}

												if (!$("#dayTypeName").val()) {

													isError = true;

													$("#error_dayType")
															.show()

												} else {
													$("#error_dayType")
															.hide()
												}

												if (!$("#leaveDateRange").val()) {

													isError = true;

													$("#error_Range").show()

												} else {
													$("#error_Range").hide()
												}

												if (!$("#noOfDays").val()) {

													isError = true;

													$("#error_noOfDays").show()

												} else {
													$("#error_noOfDays").hide()
												}

												if (!$("#noOfDaysExclude").val()) {

													isError = true;

													$("#error_noOfDaysExclude").show()

												} else {
													$("#error_noOfDaysExclude").hide()
												}

												if (!isError) {

													var x = confirm("Do you really want to submit the form?");
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