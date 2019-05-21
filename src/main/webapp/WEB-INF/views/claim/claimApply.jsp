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

<body onload="chkAssign()">

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
							href="${pageContext.request.contextPath}/showApplyForClaim"
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
								<h6 class="card-title">Add Claim</h6>
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
								<span
												class="validation-invalid-label" id="error_assign"
												style="display: none;">Sorry You Can Not Apply for Claim as Claim Authorities Are not Assigned !!</span>
								
								
<div class="form-group row">
										<label class="col-form-label col-lg-2" for="lvsName">
											Employee Code <span style="color:red">* </span>:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Leave Structure Name" id="empCode"   value="${editEmp.empCode}" 
												name="lvsName" autocomplete="off" onchange="trim(this)" readonly>
											
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="lvsName">
											Employee Name <span style="color:red">* </span>:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Enter Leave Structure Name" id="empName"  value="${editEmp.empFname} ${editEmp.empMname} ${editEmp.empSname}   "
												name="lvsName" autocomplete="off" onchange="trim(this)" readonly>
											
										</div>
									</div>
									<hr>
									
								<form
									action="${pageContext.request.contextPath}/showClaimProof"
									id="submitInsertLeave" method="post">
									
									
									
										
										<div class="form-group row">
										<label class="col-form-label col-lg-2" for="claimTypeId">Select
											Claim Type <span style="color:red">* </span>:</label>
										<div class="col-lg-4">
											<select name="claimTypeId"
												data-placeholder="Select Claim Type" id="claimTypeId"
												class="form-control form-control-select2 select2-hidden-accessible"
												 data-fouc="" aria-hidden="true">
												<option></option>
												<c:forEach items="${claimTypeList}" var="claimTypeList">
													
															<option value="${claimTypeList.claimTypeId}"  data-clstrname="${claimTypeList.claimTypeTitle}">${claimTypeList.claimTypeTitle}</option>
														
													
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_claimTypeId"
												style="display: none;">This field is required.</span>
										</div>
										</div>
										
										<div class="form-group row">
										<label class="col-form-label col-lg-2" for="projectTypeId">Select
											Project <span style="color:red">* </span>:</label>
										<div class="col-lg-4">
											<select name="projectTypeId"
												data-placeholder="Select Project" id="projectTypeId"
												class="form-control form-control-select2 select2-hidden-accessible"
												 data-fouc=""  aria-hidden="true">
												<option></option>
												<c:forEach items="${projectTypeList}" var="proTypeList">
															<option value="${proTypeList.projectId}" data-prostrname="${proTypeList.projectTitle}">${proTypeList.projectTitle}</option>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_projectTypeId"
												style="display: none;">This field is required.</span>
										</div>
										
											
										
										
										</div>
											
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="joiningDate">Claim
											Date <span style="color:red">* </span>: </label>
										<div class="col-lg-10">
											<input type="text" class="form-control datepickerclass "
												name="claimDate" id="claimDate"
												placeholder="Joining Date"> <span
												class="validation-invalid-label" id="error_claimDate"
												style="display: none;">This field is required.</span>
										</div>
									</div>
										
									
									
	<div class="form-group row">
										<label class="col-form-label col-lg-2" for="claimAmt">
										Claim Amount <span style="color:red">* </span>: </label>
										<div class="col-lg-4">
											<input type="text" class="form-control numbersOnly" 
												placeholder="Amount of Claim in Rs. " id="claimAmt"
												name="claimAmt" autocomplete="off" >
											<span class="validation-invalid-label" id="error_claimAmt"
												style="display: none;">This field is required.</span>
										</div>
										</div>
									

						<div class="form-group row">
										<label class="col-form-label col-lg-2" for="lvngReson">Remark :  </label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Remark" onchange="trim(this)"
												id="claimRemark" name="claimRemark"> </textarea>
												</div>
									</div>
										<input type="hidden" class="form-control numbersOnly" 
												value="${editEmp.empId}" id="empId"
												name="empId" autocomplete="off" readonly>
										<input type="hidden"class="form-control numbersOnly" id="auth" value="${authorityInformation.claimInitialAuth}"
										name="auth">
									
									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">

											<button type="submit" class="btn bg-blue ml-3 legitRipple"
												id="submtbtn">
												Submit <i class="icon-paperplane ml-2"></i>
											</button>
											
											<a href="${pageContext.request.contextPath}/showApplyForClaim"><button
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

												if (!$("#claimTypeId").val()) {

													isError = true;

													$("#error_claimTypeId").show()
													//return false;
												} else {
													$("#error_claimTypeId").hide()
												}

												if (!$("#projectTypeId").val()) {

													isError = true;

													$("#error_projectTypeId")
															.show()

												} else {
													$("#error_projectTypeId")
															.hide()
												}

												if (!$("#claimDate").val()) {

													isError = true;

													$("#error_claimDate").show()

												} else {
													$("#error_claimDate").hide()
												}

												if (!$("#claimAmt").val()) {

													isError = true;

													$("#error_claimAmt").show()

												} else {
													$("#error_claimAmt").hide()
												}

												
												if (!isError) {
													var option = $(
													"#claimTypeId option:selected")
													.attr(
															"data-clstrname");
 											$('#clType').html(option);
											
											var option1 = $(
											"#projectTypeId option:selected")
											.attr(
													"data-prostrname");

									$('#proName').html(option1)
											
											$('#claimAmt1')
													.html(
															document
																	.getElementById("claimAmt").value);
											$('#empCode1')
													.html(
															document
																	.getElementById("empCode").value);
											$('#empName1')
													.html(
															document
																	.getElementById("empName").value);
											
											$('#claimDate1')
											.html(
													document
															.getElementById("claimDate").value);
											$('#remark1')
											.html(
													document
															.getElementById("claimRemark").value);
									
											$('#modal_scrollable')
													.modal('show');
 													
													//end ajax send this to php page
												}
												return false;
											});
						});
		
	</script>



<script type="text/javascript">
function chkAssign(){
		
		var auth=document.getElementById("auth").value;
		
		//alert("hii"+auth);
		if(auth==0){
			document.getElementById("submtbtn").disabled = true;
			
			$("#error_assign").show()
		}
		else{
			document.getElementById("submtbtn").disabled =false ;
		}
	}
	</script>
<script>
		function submitForm() {
			$('#modal_scrollable').modal('hide');
			document.getElementById("submtbtn").disabled = true;
			document.getElementById("submitInsertLeave").submit();

		}
	</script>
	<!-- Scrollable modal -->
	<div id="modal_scrollable" class="modal fade" data-backdrop="false"
		tabindex="-1">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header pb-3">

					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body py-0">
					<h5 class="modal-title">Claim Details</h5><br>

					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="empCode1">
							Employee Code : </label> <label class="col-form-label col-lg-2"
							id="empCode1" for="empCode1"> </label>

					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="empName1">
							Employee Name : </label> <label class="col-form-label col-lg-6"
							id="empName1" for="empName1"> </label>

					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="clType"> Claim
							Type : </label> <label class="col-form-label col-lg-2" id="clType"
							for="clType"> </label>

					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="proName"> Project 
							Name : </label> <label class="col-form-label col-lg-6" id="proName"
							for="proName"> </label>

					</div>

				
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="noOfDays"> Claim Amount
							 : </label> <label class="col-form-label col-lg-3" id="claimAmt1"
							for="claimAmt1"> </label>

					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="claimDate1"> Claim Date
							 : </label> <label class="col-form-label col-lg-3" id="claimDate1"
							for="claimDate1"> </label>

					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-3" for="remark1"> Claim Remark
							 : </label> <label class="col-form-label col-lg-3" id="remark1"
							for="remark1"> </label>

					</div>
				</div>

				<div class="modal-footer pt-3">
					<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn bg-primary" onclick="submitForm()">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /scrollable modal -->



</body>
</html>