<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
<c:url var="getFreeEmployeeList" value="/getFreeEmployeeList" />
<c:url var="moveEmp" value="/moveEmp" />
<c:url var="deleteEmp" value="/deleteEmp" />
<c:url var="getEmployeeAllocatedHistory"
	value="/getEmployeeAllocatedHistory" />
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
						<a href="${pageContext.request.contextPath}/showProjectHeaderList"
							class="breadcrumb-elements-item"> Project List</a>

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
								<h6 class="card-title">Project Allotment</h6>
								<!-- 	<div class="header-elements">
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
									action="${pageContext.request.contextPath}/submitProjectAllotment"
									id="submitInsertEmpType" method="post">
									<div class="form-group row">
										<label class="col-form-label col-lg-2"> Project Title
											: </label> <label class="col-form-label col-lg-3">
											${projectInfo.projectTitle}</label> <label
											class="col-form-label col-lg-2"> Project Type : </label> <label
											class="col-form-label col-lg-3">
											${projectInfo.projectTypeTitle} <input id="projectId"
											name="projectId" value="${projectInfo.projectId}"
											type="hidden">
										</label>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2"> Customer : </label> <label
											class="col-form-label col-lg-3">
											${projectInfo.custName}</label> <label
											class="col-form-label col-lg-2"> Project Management
											Location : </label> <label class="col-form-label col-lg-3">
											${projectInfo.locName}</label>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="catId">
											Employee Category <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="catId" data-placeholder="Select Category"
												id="catId"
												class="form-control form-control-select2 select2-hidden-accessible"
												data-fouc="" aria-hidden="true">

												<option value="">Select Category</option>

												<c:forEach items="${empCatList}" var="empCatList">
													<option value="${empCatList.empCatId}">${empCatList.empCatName}</option>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_catId"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2">Date Range:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control daterange-basic_new "
												name="leaveDateRange" data-placeholder="Select Date"
												id="leaveDateRange" autocomplete="off"> <span
												class="validation-invalid-label" id="error_Range"
												style="display: none;">This field is required.</span>

										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="fullHalfwork">Select
											<span style="color: red">* </span>:
										</label>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												class="form-check-input" name="fullHalfwork"
												id="fullHalfwork" checked value="2"> Full Time
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												class="form-check-input" name="fullHalfwork"
												id="fullHalfwork" value="1"> Partial Time
											</label>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="locationId">
											Select Location :</label>
										<div class="col-lg-10">
											<select name="locationId" data-placeholder="Select Category"
												id="locationId"
												class="form-control form-control-select2 select2-hidden-accessible"
												data-fouc="" aria-hidden="true" multiple="multiple">

												<option value="">Select Category</option>

												<c:forEach items="${locationIds}" var="locationId">
													<c:forEach items="${locationList}" var="locationList">
														<c:if test="${locationList.locId==locationId}">
															<option value="${locationList.locId}">${locationList.locName}</option>
														</c:if>

													</c:forEach>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_catId"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group" style="text-align: center;">


										<button type="button" class="btn bg-blue ml-3 legitRipple"
											id="searchbtn" onclick="getFreeEmployeeList()">Search</button>

									</div>
									<br>

									<div class="row">
										<div class="col-md-6">
											<table
												class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic1  datatable-button-print-columns1"
												id="printtable1">
												<thead>
													<tr class="bg-blue">
														<th width="10%">Sr. No.</th>
														<th>Employee Name</th>
														<th width="10%" style="text-align: center;">Action</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
										<div class="col-md-6">
											<table
												class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic1  datatable-button-print-columns1"
												id="printtable2">
												<thead>
													<tr class="bg-blue">
														<th width="10%">Sr. No.</th>
														<th>Employee Name</th>
														<th width="20%">From Date</th>
														<th width="20%">To Date</th>
														<th width="10%" style="text-align: center;">Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${bsyList}" var="bsyList"
														varStatus="count">

														<tr>
															<td>${count.index+1}</td>


															<td>${bsyList.empFname}&nbsp;${bsyList.empSname}</td>
															<td>${bsyList.pallotFromdt}</td>
															<td>${bsyList.pallotTodt}</td>
															<td>
																<%-- <a onclick="deleteEmp(${count.index})"><i
																	class="icon-trash"></i></a> --%>
															</td>
														</tr>

													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>
									<br>
									<div class="form-group" style="text-align: center;">
										<button type="submit" class="btn bg-blue ml-3 legitRipple"
											id="submtbtn">
											Submit <i class="icon-paperplane ml-2"></i>
										</button>
										<a
											href="${pageContext.request.contextPath}/showProjectHeaderList"><button
												type="button" class="btn bg-blue ml-3 legitRipple"
												id="searchbtn">Cancel</button></a>
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

		$(document).ready(function($) {

			$("#submitInsertEmpType").submit(function(e) {
				var isError = false;
				var errMsg = "";
				/* $("#error_empTypeName").hide();
				
				if (!$("#empTypeName").val()) {

					isError = true;

					$("#error_empTypeName").show();
					//return false;
				}   */

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

		function getFreeEmployeeList() {

			var catId = document.getElementById("catId").value;
			var daterange = document.getElementById("leaveDateRange").value;
			var res = daterange.split(" to ");
			var locationId = document.getElementById("locationId").value;
			var worktime = document
					.querySelector('input[name="fullHalfwork"]:checked').value;

			if (catId == "") {
				$("#error_catId").show();
			} else {

				$("#error_catId").hide();

				var selectedValues = ",";
				$("#locationId :selected").each(
						function() {
							selectedValues = selectedValues
									+ parseInt($(this).val()) + ",";
						});

				if (locationId == "") {
					selectedValues = 0;
				}

				//alert(selectedValues);
				$
						.getJSON(
								'${getFreeEmployeeList}',
								{

									fromDate : res[0],
									toDate : res[1],
									catId : catId,
									locationId : selectedValues,
									worktime : worktime,
									ajax : 'true',

								},
								function(data) {

									//alert(data);
									$("#printtable1 tbody").empty();

									for (var i = 0; i < data.length; i++) {

										var tr_data = '<tr>'
												+ '<td  >'
												+ (i + 1)
												+ '</td>'
												+ '<td  >'
												+ data[i].empFname
												+ ' '
												+ data[i].empSname
												+ '</td>'
												+ '<td  >  <a  onclick="fillEmpInfo('
												+ i
												+ ','
												+ data[i].empId
												+ ')"><i class="icon-drag-right "></i></a></td>'
												+ '</tr>';
										$('#printtable1' + ' tbody').append(
												tr_data);
									}

								});

			}

		}
		function moveEmp() {

			var empId = document.getElementById("tempEmpId").value;
			var selectWorkType = document
					.querySelector('input[name="selectWorkType"]:checked').value;
			var worktime = document
					.querySelector('input[name="fullHalfwork"]:checked').value;
			var hours = parseFloat(document.getElementById("hours").value);
			var flag = 0;
			$("#error_fullTimeError").hide();
			$("#error_hours").hide();

			if (selectWorkType == 1) {

				if (isNaN(hours)) {

					flag = 1;
					$("#error_hours").show();

				}

			} else {
				hours = 9;
			}

			if (selectWorkType == 2 && worktime == 1) {
				flag = 1;
				$("#error_fullTimeError").show();

			}

			if (flag == 0) {

				$
						.getJSON(
								'${moveEmp}',
								{

									empId : empId,
									selectWorkType : selectWorkType,
									hours : hours,
									ajax : 'true',

								},
								function(data) {

									$("#printtable1 tbody").empty();

									for (var i = 0; i < data.freeList.length; i++) {

										var tr_data = '<tr>'
												+ '<td  >'
												+ (i + 1)
												+ '</td>'
												+ '<td  >'
												+ data.freeList[i].empFname
												+ ' '
												+ data.freeList[i].empSname
												+ '</td>'
												+ '<td  >  <a  onclick="fillEmpInfo('
												+ i
												+ ','
												+ data.freeList[i].empId
												+ ')"><i class="icon-drag-right "></i></a></td>'
												+ '</tr>';
										$('#printtable1' + ' tbody').append(
												tr_data);
									}

									$("#printtable2 tbody").empty();

									for (var i = 0; i < data.bsyList.length; i++) {

										var atn;
										if (data.bsyList[i].pallotId == 0) {
											atn = '<td  >  <a  onclick="deleteEmp('
													+ i
													+ ')"><i class="icon-trash"></i></a></td>';
										} else {
											atn = '<td  >  </td>';
										}
										var tr_data = '<tr>' + '<td  >'
												+ (i + 1) + '</td>' + '<td  >'
												+ data.bsyList[i].empFname
												+ ' '
												+ data.bsyList[i].empSname
												+ '</td>' + '<td  >'
												+ data.bsyList[i].pallotFromdt
												+ '</td>' + '<td  >'
												+ data.bsyList[i].pallotTodt
												+ '</td>' + atn + '</tr>';
										$('#printtable2' + ' tbody').append(
												tr_data);
									}

									$('#modal_full').modal('hide');

								});
			}

		}

		function fillEmpInfo(index, empId) {

			document.getElementById("fulltimeWorkType").checked = true;
			document.getElementById("hours").value = "";
			$('#hoursDiv').hide();
			$("#error_hours").hide();
			$("#historyTableDiv").hide();
			$("#error_fullTimeError").hide();
			document.getElementById("tempEmpId").value = index;
			document.getElementById("tempEmployeeId").value = empId;
			$('#modal_full').modal('show');

		}

		function getEmpHistory() {

			$("#historyTableDiv").show();
			var empId = document.getElementById("tempEmployeeId").value;

			$.getJSON('${getEmployeeAllocatedHistory}', {

				empId : empId,
				ajax : 'true',

			}, function(data) {

				//alert(data);
				$("#historyTable tbody").empty();

				for (var i = 0; i < data.length; i++) {

					var halfFull;

					if (data[i].exInt1 == 1) {
						halfFull = "Partial";
					} else {
						halfFull = "Full";
					}
					var tr_data = '<tr>' + '<td  >' + (i + 1) + '</td>'
							+ '<td  >' + data[i].projectTitle + '</td>' 
							+'<td  >' + halfFull + '</td>' + '<td  >'
							+ data[i].pallotFromdt + '</td>' + '<td  >'
							+ data[i].pallotTodt + '</td>' 
							+ '<td  >' + data[i].pallotDailyHrs + '</td>' + '</tr>';
					$('#historyTable' + ' tbody').append(tr_data);
				}

			});

		}

		function opneCloseHoursDiv(value) {

			if (value == 1) {
				$('#hoursDiv').show();
			} else {
				$('#hoursDiv').hide();
			}

		}
		function deleteEmp(empId) {

			$.getJSON('${deleteEmp}', {

				empId : empId,
				ajax : 'true',

			}, function(data) {

				$("#printtable1 tbody").empty();

				for (var i = 0; i < data.freeList.length; i++) {

					var tr_data = '<tr>' + '<td  >' + (i + 1) + '</td>'
							+ '<td  >' + data.freeList[i].empFname + ' '
							+ data.freeList[i].empSname + '</td>'
							+ '<td  >  <a  onclick="fillEmpInfo(' + i + ','
							+ data.freeList[i].empId
							+ ')"><i class="icon-drag-right "></i></a></td>'
							+ '</tr>';
					$('#printtable1' + ' tbody').append(tr_data);
				}

				$("#printtable2 tbody").empty();

				for (var i = 0; i < data.bsyList.length; i++) {

					var atn;
					if (data.bsyList[i].pallotId == 0) {
						atn = '<td  >  <a  onclick="deleteEmp(' + i
								+ ')"><i class="icon-trash"></i></a></td>';
					} else {
						atn = '<td  >  </td>';
					}
					var tr_data = '<tr>' + '<td  >' + (i + 1) + '</td>'
							+ '<td  >' + data.bsyList[i].empFname + ' '
							+ data.bsyList[i].empSname + '</td>' + '<td  >'
							+ data.bsyList[i].pallotFromdt + '</td>' + '<td  >'
							+ data.bsyList[i].pallotTodt + '</td>' + atn
							+ '</tr>';
					$('#printtable2' + ' tbody').append(tr_data);
				}

			});

		}
	</script>

	<!-- Scrollable modal -->
	<!-- <div id="modal_scrollable" class="modal fade" data-backdrop="false"
		tabindex="-1">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header pb-3">

					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body py-0">
					<h5 class="modal-title">Fill Info</h5>
					<br>
					<div class="col-md-10">

						<div class="form-check form-check-inline">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="selectWorkType"
								id="fulltimeWorkType" checked value="2"
								onclick="opneCloseHoursDiv(2)"> Full Time
							</label>
						</div>
						<div class="form-check form-check-inline">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="selectWorkType"
								id="parttimeWorkType" value="1" onclick="opneCloseHoursDiv(1)">
								Partial Time
							</label>
						</div>
						<span class="validation-invalid-label" id="error_fullTimeError"
							style="display: none;">You Can't Select Full Time</span>
					</div>

					<div class="col-lg-10" style="display: none;" id="hoursDiv">
						<label class="col-form-label col-lg-5" for="hours"> Enter
							Hours :<span style="color: red">* </span>
						</label>
						<div class="col-md-10">
							<input type="text" class="form-control" name="hours"
								data-placeholder="Enter Hours" id="hours" autocomplete="off">
							<span class="validation-invalid-label" id="error_hours"
								style="display: none;">Enter Valid Hours </span>
						</div>

					</div>
				</div>

				<div class="modal-footer pt-3">
					<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn bg-primary" onclick="moveEmp()">Add</button>
					<input id="tempEmpId" name="tempEmpId" type="hidden">
				</div>
			</div>
		</div>
	</div> -->

	<!-- Full width modal -->
	<div id="modal_full" class="modal fade" data-backdrop="false"
		tabindex="-1">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Fill Info</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">

					<div class="form-group row">
						<label class="col-form-label col-lg-2" for="fulltimeWorkType">
							Select : <span style="color: red">* </span>
						</label>
						<div class="col-lg-10">
							<div class="form-check form-check-inline">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="selectWorkType"
									id="fulltimeWorkType" checked value="2"
									onclick="opneCloseHoursDiv(2)"> Full Time
								</label>
							</div>
							<div class="form-check form-check-inline">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="selectWorkType"
									id="parttimeWorkType" value="1" onclick="opneCloseHoursDiv(1)">
									Partial Time
								</label>
							</div>
							<span class="validation-invalid-label" id="error_fullTimeError"
								style="display: none;">You Can't Select Full Time</span>
						</div>
					</div>

					<div class="form-group row" style="display: none;" id="hoursDiv">
						<label class="col-form-label col-lg-2" for="hours">Enter
							Hours :<span style="color: red">* </span>
						</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="hours"
								data-placeholder="Enter Hours" id="hours" autocomplete="off">
							<span class="validation-invalid-label" id="error_hours"
								style="display: none;">Enter Valid Hours </span>
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn bg-primary" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn bg-primary"
							onclick="getEmpHistory()">History</button>
						<button type="button" class="btn bg-primary" onclick="moveEmp()">Add</button>
						<input id="tempEmpId" name="tempEmpId" type="hidden"> <input
							id="tempEmployeeId" name="tempEmployeeId" type="hidden">
					</div>

					<div class="row" id="historyTableDiv" style="display: none;">

						<table
							class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic1  datatable-button-print-columns1"
							id="historyTable">
							<thead>
								<tr class="bg-blue">
									<th width="10%">Sr. No.</th>
									<th>Project Name</th>
									<th width="10%">Half/Full</th>
									<th width="10%">From Date</th>
									<th width="10%">To Date</th>
									<th width="10%">Hours</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>
				</div>


			</div>
		</div>
	</div>
	<!-- /full width modal -->
	<!-- /scrollable modal -->

</body>
</html>