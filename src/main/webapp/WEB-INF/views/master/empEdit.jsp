<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="/WEB-INF/views/include/metacssjs.jsp"></jsp:include>
<c:url var="getSubmoduleList" value="/getSubmoduleList" />
</head>

<body onload="showIsReg()">

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
			<div class="page-header page-header-light"></div>
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
								<table width="100%">
									<tr width="100%">
										<td width="60%"><h5 class="card-title">Edit Employee</h5></td>
										<td width="40%" align="right">
											<%-- <a
									href="${pageContext.request.contextPath}/showAddKra?empId=${editKra.exVar3}&finYrId=${editKra.exVar2}"
									class="breadcrumb-elements-item">
										<button type="button" class="btn btn-primary">KRA List </button>
								</a>  --%>
										</td>
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

								<form action="${pageContext.request.contextPath}/SubmitEditEmp"
									id="SubmitEditEmp" method="post" enctype="multipart/form-data">
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="empCode">
											Employee Code <span style="color: red">* </span> :
										</label>
										<div class="col-lg-2">
											<input type="text" class="form-control"
												placeholder="Employee Code" id="empCode" name="empCode"
												value="${editEmp.empCode}" autocomplete="off"
												onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_empCode"
												style="display: none;">This field is required.</span>
										</div>
									</div>


									<input type="hidden" id="url" value='${weighImageUrl}' /> <input
										type="hidden" name="imageName" id="imageName">


									<%-- <div class="form-group row">
										<label class="col-form-label col-lg-2" for="profilePic">Profile
											Pic :</label>
										<div class="col-lg-6">
											<input type="file" class="form-control" id="profilePic"
												value="${editEmp.empPhoto}" name="profilePic"
												accept=".jpg,.png,.gif"> <span class="filename"
												style="user-select: none;"> </span>
										</div>

										<div class="col-lg-2">
											<img id="image1" name="image1" alt="l" height="50px;"
												width="50px;">
										</div>
									</div> --%>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="profilePic">
											Profile Pic :</label>
										<div class="col-lg-10">
											<div class="input-group-btn  ">

												<c:if test="${not empty editEmp.empPhoto}">
													<img src="${imageUrl}${editEmp.empPhoto}"
														style="width: 200px; height: auto;">
												</c:if>

												<span class="filename" style="user-select: none1;"><img
													id="temppreviewimageki1" name="image1"
													class="temppreviewimageki1" alt="l"
													style="width: 200px; height: auto; display: none"> </span>
												<!-- image-preview-clear button -->
												<button type="button" title="Clear selected files"
													class="btn btn-default btn-secondary fileinput-remove fileinput-remove-button legitRipple image-preview-clear image-preview-clear1"
													id="1" style="display: none;">
													<i class="icon-cross2 font-size-base mr-2"></i> Clear
												</button>

												<div class="btn btn-primary btn-file legitRipple">
													<i class="icon-file-plus"></i> <span class="hidden-xs">Browse</span><input
														type="file" class="file-input browseimage browseimage1"
														data-fouc="" id="1" name="profilePic"
														accept=".jpg,.png,.gif">
												</div>
											</div>


										</div>

									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="fname">
											Employee Name <span style="color: red">* </span>:
										</label>
										<div class="col-lg-3">
											<input type="text" class="form-control  "
												placeholder="First Name" id="fname" name="fname"
												value="${editEmp.empFname}" autocomplete="off"
												onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_fname"
												style="display: none;">This field is required.</span>
										</div>



										<div class="col-lg-3">
											<input type="text" class="form-control  "
												placeholder="Middle Name" id="mname" name="mname"
												value="${editEmp.empMname}" autocomplete="off"
												onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_mname"
												style="display: none;">This field is required.</span>
										</div>

										<div class="col-lg-3">
											<input type="text" class="form-control  "
												placeholder="Last Name" id="sname" name="sname"
												value="${editEmp.empSname}" autocomplete="off"
												onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_sname"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="locId">
											Location <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="locId" data-placeholder="Select Location"
												id="locId"
												class="form-control form-control-select2 select2-hidden-accessible"
												data-fouc="" aria-hidden="true">

												<option value="">Select Location</option>


												<c:forEach items="${locationList}" var="locationList">
													<c:choose>
														<c:when test="${locationList.locId == editEmp.locId}">
															<option selected value="${locationList.locId}">${locationList.locName}</option>
														</c:when>
														<c:otherwise>
															<option value="${locationList.locId}">${locationList.locName}</option>
														</c:otherwise>

													</c:choose>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_locId"
												style="display: none;">This field is required.</span>
										</div>
									</div>





									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="catId">
											Category <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="catId" data-placeholder="Select Category"
												id="catId"
												class="form-control form-control-select2 select2-hidden-accessible"
												data-fouc="" t aria-hidden="true">

												<option value="">Select Category</option>

												<c:forEach items="${catList}" var="catList">
													<c:choose>
														<c:when test="${catList.empCatId == editEmp.empCatId}">
															<option selected value="${catList.empCatId}">${catList.empCatName}</option>
														</c:when>
														<c:otherwise>
															<option value="${catList.empCatId}">${catList.empCatName}</option>
														</c:otherwise>

													</c:choose>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_catId"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="typeId">
											Type <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="typeId" data-placeholder="Select Type"
												id="typeId"
												class="form-control form-control-select2 select2-hidden-accessible"
												data-fouc="" aria-hidden="true">

												<option value="">Select Type</option>

												<c:forEach items="${empTypelist}" var="empTypelist">
													<c:choose>
														<c:when
															test="${empTypelist.empTypeId == editEmp.empTypeId}">
															<option selected value="${empTypelist.empTypeId}">${empTypelist.empTypeName}</option>
														</c:when>
														<c:otherwise>
															<option value="${empTypelist.empTypeId}">${empTypelist.empTypeName}</option>
														</c:otherwise>

													</c:choose>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_typeId"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="deptId">
											Department <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="deptId" data-placeholder="Select Department"
												id="deptId"
												class="form-control form-control-select2 select2-hidden-accessible"
												data-fouc="" aria-hidden="true">

												<option value="">Select Department</option>

												<c:forEach items="${deptList}" var="deptList">
													<c:choose>
														<c:when test="${deptList.empDeptId == editEmp.empDeptId}">
															<option selected value="${deptList.empDeptId}">${deptList.empDeptName}</option>
														</c:when>
														<c:otherwise>
															<option value="${deptList.empDeptId}">${deptList.empDeptName}</option>
														</c:otherwise>

													</c:choose>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_deptId"
												style="display: none;">This field is required.</span>
										</div>
									</div>




									<%-- <div class="form-group row">

										<label class="col-form-label col-lg-2" for="gender">Select 
											<span style="color: red">* </span>:
										</label>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <c:choose>
													<c:when test="${editEmp.exInt3==1}">
														<input type="radio" class="form-check-input"
															name="salesType" id="salesType" checked value="1">  Pre-Sales
													</c:when>
													<c:otherwise>
														<input type="radio" class="form-check-input"
															name="salesType" id="salesType" value="1">  Pre-Sales
													
													</c:otherwise>
												</c:choose>
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <c:choose>
													<c:when test="${editEmp.exInt3==2}">
														<input type="radio" class="form-check-input"
															name="salesType" id="salesType"  value="2">
											 Sales Claim
													</c:when>
													<c:otherwise>
														<input type="radio" class="form-check-input"
															name="salesType" checked id="salesType" value="2">
											 Sales Claim
													
													</c:otherwise>
												</c:choose>
											</label>
										</div>

									</div> --%>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="salesType">
											Select <span style="color: red">* </span>:
										</label>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												${editEmp.exInt3==1 ? 'checked' : ''}
												class="form-check-input" name="salesType" id="salesType"
												checked value="1"> Pre-Sales
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												${editEmp.exInt3==2 ? 'checked' : ''}
												class="form-check-input" name="salesType" id="salesType"
												value="2"> Sales Claim
											</label>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="tempAdd">Temporary
											Address <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Temporary Address" onchange="trim(this)"
												id="tempAdd" name="tempAdd">${editEmp.empAddressTemp}</textarea>
											<span class="validation-invalid-label" id="error_tempAdd"
												style="display: none;">This field is required.</span>

										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="checkSameAdd">
											Same As Permanent Address : </label>
										<div class="form-check form-check-inline">
											<input type="checkbox" id="checkSameAdd"
												${editEmp.empAddressTemp == editEmp.empAddressPerm ? 'checked' : 'unchecked'}
												name="checkSameAdd" onclick="checkAdd()">

										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="permntAdd">Permanent
											Address <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Permanent Address" onchange="trim(this)"
												id="permntAdd" name="permntAdd">${editEmp.empAddressPerm}</textarea>
											<span class="validation-invalid-label" id="error_permntAdd"
												style="display: none;">This field is required.</span>

										</div>
									</div>

									<%-- <div class="form-group row">
										<label class="col-form-label col-lg-2" for="bloodGrp">Blood
											Group *: </label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Blood Group" id="bloodGrp" name="bloodGrp"
												value="${editEmp.empBloodgrp}" autocomplete="off"
												onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_bloodGrp"
												style="display: none;">This field is required.</span>
										</div>
									</div> --%>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Blood Group <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<select name="bloodGrp" data-placeholder="Please Select"
												id="bloodGrp"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="-1" aria-hidden="true">
												<option value="">Please Select</option>

												<option ${editEmp.empBloodgrp == '0'  ? 'Selected': '' }
													value="0">A+</option>
												<option ${editEmp.empBloodgrp == '1' ? 'Selected': '' }
													value="1">O+</option>
												<option ${editEmp.empBloodgrp == '2'  ? 'Selected': '' }
													value="2">B+</option>
												<option ${editEmp.empBloodgrp == '3' ? 'Selected': '' }
													value="3">AB+</option>
												<option ${editEmp.empBloodgrp == '4'  ? 'Selected': '' }
													value="4">A-</option>
												<option ${editEmp.empBloodgrp == '5'  ? 'Selected': '' }
													value="5">O-</option>
												<option ${editEmp.empBloodgrp == '6'  ? 'Selected': '' }
													value="6">B-</option>
												<option ${editEmp.empBloodgrp == '7'  ? 'Selected': '' }
													value="7">AB-</option>


											</select> <span class="validation-invalid-label" id="error_bloodGrp"
												style="display: none;">This field is required.</span>
										</div>
									</div>


									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="mobile1">Contact
											No <span style="color: red">* </span>:
										</label>
										<div class="col-lg-4">
											<input type="text" class="form-control"
												placeholder="Contact No." id="mobile1" name="mobile1"
												value="${editEmp.empMobile1}" autocomplete="off"
												onchange="trim(this)" maxlength="10"> <span
												class="validation-invalid-label" id="error_mobile1"
												style="display: none;">This field is required.</span>
										</div>

										<label class="col-form-label col-lg-2" for="mobile2">Alternate
											Contact No: </label>
										<div class="col-lg-4">
											<input type="text" class="form-control"
												placeholder="Alternate Contact No." id="mobile2"
												value="${editEmp.empMobile2}" name="mobile2"
												autocomplete="off" onchange="trim(this)" maxlength="10">
											<span class="validation-invalid-label"
												id="error_emgContNo2_alt" style="display: none;">This
												field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="email">Email
											<span style="color: red">* </span> :
										</label>
										<div class="col-lg-4">
											<input type="text" class="form-control" placeholder="Email"
												id="email" name="email" value="${editEmp.empEmail}"
												autocomplete="off" readonly onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_email"
												style="display: none;">This field is required.</span>
										</div>

										<label class="col-form-label col-lg-2" for="gender">Gender
											<span style="color: red">* </span>:
										</label>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <c:choose>
													<c:when test="${editEmp.exInt2==0}">
														<input type="radio" class="form-check-input" name="gender"
															id="gender" checked value="0"> Male
													</c:when>
													<c:otherwise>
														<input type="radio" class="form-check-input" name="gender"
															id="gender" value="0"> Male
													
													</c:otherwise>
												</c:choose>
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <c:choose>
													<c:when test="${editEmp.exInt2==0}">
														<input type="radio" class="form-check-input" name="gender"
															id="gender" value="1">
												Female
													</c:when>
													<c:otherwise>
														<input type="radio" class="form-check-input" name="gender"
															checked id="gender" value="1">
												Female
													
													</c:otherwise>
												</c:choose>
											</label>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="emgContPrsn1">Emergency
											Contact Person 1 <span style="color: red">* </span>:
										</label>
										<div class="col-lg-4">
											<input type="text" class="form-control"
												placeholder="Emergency Contact Person" id="emgContPrsn1"
												value="${editEmp.empEmergencyPerson1}" name="emgContPrsn1"
												autocomplete="off" onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_emgContPrsn1"
												style="display: none;">This field is required.</span>
										</div>


										<label class="col-form-label col-lg-2" for="emgContNo1">Emergency
											Contact No 1 <span style="color: red">* </span>:
										</label>
										<div class="col-lg-4">
											<input type="text" class="form-control"
												placeholder="Emergency Contact No." id="emgContNo1"
												value="${editEmp.empEmergencyNo1}" name="emgContNo1"
												autocomplete="off" onchange="trim(this)" maxlength="10">
											<span class="validation-invalid-label" id="error_emgContNo1"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="emgContPrsn2">Emergency
											Contact Person 2 : </label>
										<div class="col-lg-4">
											<input type="text" class="form-control"
												placeholder="Emergency Alternate Contact Person"
												value="${editEmp.empEmergencyPerson2}" id="emgContPrsn2"
												name="emgContPrsn2" autocomplete="off" onchange="trim(this)">
											<span class="validation-invalid-label"
												id="error_emgContPrsn2" style="display: none;">This
												field is required.</span>
										</div>

										<label class="col-form-label col-lg-2" for="emgContNo2">Emergency
											Contact No 2 : </label>
										<div class="col-lg-4">
											<input type="text" class="form-control"
												placeholder="Emergency Alternate Contact No."
												id="emgContNo2" name="emgContNo2"
												value="${editEmp.empEmergencyNo2}" autocomplete="off"
												onchange="trim(this)" maxlength="10"> <span
												class="validation-invalid-label" id="error_emgContNo2"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="ratePerHr">
											Employee Rate Per Hour <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control numbersOnly"
												placeholder="Employee Rate Per Hour" id="ratePerHr"
												value="${editEmp.empRatePerhr}" name="ratePerHr"
												autocomplete="off" onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_ratePerHr"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="joiningDate">Joining
											Date <span style="color: red">* </span>:
										</label>
										<div class="col-lg-10">
											<input type="text" class="form-control datepickerclass "
												name="joiningDate" id="joiningDate"
												value="${editEmp.empJoiningDate}" placeholder="Joining Date">
											<span class="validation-invalid-label" id="error_joiningDate"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="prevsExpYr">
											Previous Experience in Year <span style="color: red">*
										</span>:
										</label>
										<div class="col-lg-4">
											<input type="text" class="form-control numbersOnly"
												value="${editEmp.empPrevExpYrs}"
												placeholder="Previous Experience in Year" id="prevsExpYr"
												name="prevsExpYr" autocomplete="off" onchange="trim(this)">
											<span class="validation-invalid-label" id="error_prevsExpYr"
												style="display: none;">This field is required.</span>
										</div>

										<label class="col-form-label col-lg-2" for="prevsExpMn">
											Previous Experience in Month <span style="color: red">*
										</span> :
										</label>
										<div class="col-lg-4">
											<input type="text" class="form-control numbersOnly"
												placeholder="Previous Experience in Month" id="prevsExpMn"
												value="${editEmp.empPrevExpMonths}" name="prevsExpMn"
												autocomplete="off" onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_prevsExpMn"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="comoffallowed">Is
											Working Today <span style="color: red">* </span>:
										</label>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												${editEmp.exInt1==1 ? 'checked' : ''}
												class="form-check-input" name="isWorking" id="isStructured"
												onclick="setDate(this.value)" checked value="1"> Yes
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input type="radio"
												${editEmp.exInt1==0 ? 'checked' : ''}
												class="form-check-input" onclick="setDate(this.value)"
												name="isWorking" id="isStructured" value="0"> No
											</label>
										</div>
									</div>
									<div id="abc" style="display: none;">
										<div class="form-group row">
											<label class="col-form-label col-lg-2" for="leavingDate">Leaving
												Date : </label>
											<div class="col-lg-10">
												<input type="text" class="form-control datepickerclass "
													name="leavingDate" id="leavingDate"
													value="${editEmp.empLeavingDate}"
													placeholder="Leaving Date">
											</div>
										</div>
									</div>
									<div id="xyz" style="display: none;">

										<div class="form-group row">
											<label class="col-form-label col-lg-2" for="lvngReson">
												Leaving Reason : </label>
											<div class="col-lg-10">
												<textarea rows="3" cols="3" class="form-control"
													placeholder="Leaving Reason" onchange="trim(this)"
													id="lvngReson" name="lvngReson"> ${editEmp.empLeavingReason}</textarea>


											</div>
										</div>
									</div>

									<div class="card-header header-elements-inline">
										<h6 class="card-title">Edit User</h6>

									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="uname">
											User Name *:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control  "
												placeholder="User Name" id="uname" name="uname"
												value="${editUser.userName}" autocomplete="off" readonly
												onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_uname"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="upass">
											User Password <span style="color: red">* </span> :
										</label>
										<div class="col-lg-10">
											<input type="password" class="form-control  "
												placeholder="User Password " id="upass" name="upass"
												value="${editUser.userPwd}" autocomplete="off" readonly
												onchange="trim(this)"> <span
												class="validation-invalid-label" id="error_upass"
												style="display: none;">This field is required.</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="locId2">
											Location <span style="color: red">* </span> :
										</label>
										<div class="col-lg-10">

											<select data-placeholder="Select Location" name="locId2"
												id="locId2" class="form-control form-control-sm select"
												multiple="multiple">
												<option value="">Select Location</option>
												<c:forEach items="${locationList}" var="location">
													<c:set var="flag" value="0"></c:set>
													<c:forEach items="${locIdList}" var="selLocation"
														varStatus="count2">
														<c:choose>
															<c:when test="${selLocation==location.locId}">
																<option selected value="${location.locId}"><c:out
																		value="${location.locName}" /></option>
																<c:set var="flag" value="1"></c:set>
															</c:when>
															<c:otherwise>

															</c:otherwise>
														</c:choose>
													</c:forEach>
													<c:choose>
														<c:when test="${flag==0}">
															<option value="${location.locId}"><c:out
																	value="${location.locName}" /></option>
														</c:when>
													</c:choose>
												</c:forEach>

											</select> <span class="validation-invalid-label" id="error_locId2"
												style="display: none;">This field is required.</span>
										</div>
									</div>



									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">
											<!-- <button type="reset" class="btn btn-light legitRipple">Reset</button> -->
											<button type="submit" class="btn bg-blue ml-3 legitRipple"
												id="submtbtn">
												Submit <i class="icon-paperplane ml-2"></i>
											</button>
											<a href="${pageContext.request.contextPath}/showEmpList"><button
													type="button" class="btn btn-primary">
													<i class="${sessionScope.cancelIcon}" aria-hidden="true"></i>&nbsp;&nbsp;
													Cancel
												</button></a>

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
		function setDate(value) {
			//alert("Value " +value)
			if (value == 1) {
				//alert(value)
				//document.getElementById("relDate").removeAttribute("required");
				document.getElementById("abc").style.display = "none";
				document.getElementById("xyz").style.display = "none";

				//alert(value)
			} else {
				//alert(value)
				//document.getElementById("relDate").setAttribute("required","true");
				document.getElementById("abc").style.display = "block";
				document.getElementById("xyz").style.display = "block";
				//alert(value)

			}

		}
	</script>
	<script type="text/javascript">
		function showIsReg() {
			//alert("Hi");
			var x = $
			{
				editEmp.exInt1
			}
			;

			if (x == 1) {

				document.getElementById("abc").style.display = "none";
				document.getElementById("xyz").style.display = "none";

			} else {
				//alert("Hi es")
				document.getElementById("abc").style.display = "block";
				document.getElementById("xyz").style.display = "block";

			}

		}
	</script>
	<script type="text/javascript">
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#image1').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("#profilePic").change(function() {

			readURL(this);
		});
	</script>
	<script>
		function checkAdd() {

			if (document.getElementById("checkSameAdd").checked == true) {

				document.getElementById("permntAdd").value = document
						.getElementById("tempAdd").value;

			} else {

				document.getElementById("permntAdd").value = "";
			}

		}
	</script>

	<script>
		function trim(el) {
			el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
			replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
			replace(/\n +/, "\n"); // Removes spaces after newlines
			return;
		}

		function validateEmail(email) {

			var eml = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

			if (eml.test($.trim(email)) == false) {

				return false;

			}

			return true;

		}
		function validateMobile(mobile) {
			var mob = /^[1-9]{1}[0-9]{9}$/;

			if (mob.test($.trim(mobile)) == false) {

				//alert("Please enter a valid email address .");
				return false;

			}
			return true;

		}

		$(document)
				.ready(
						function($) {

							$("#SubmitEditEmp")
									.submit(
											function(e) {
												var isError = false;
												var errMsg = "";

												if (!$("#empCode").val()) {

													isError = true;

													$("#error_empCode").show()
													//return false;
												} else {
													$("#error_empCode").hide()
												}

												if (!$("#fname").val()) {

													isError = true;

													$("#error_fname").show()

												} else {
													$("#error_fname").hide()
												}

												if (!$("#mname").val()) {

													isError = true;

													$("#error_mname").show()

												} else {
													$("#error_mname").hide()
												}

												if (!$("#sname").val()) {

													isError = true;

													$("#error_sname").show()

												} else {
													$("#error_sname").hide()
												}

												if (!$("#locId").val()) {

													isError = true;

													$("#error_locId").show()

												} else {
													$("#error_locId").hide()
												}

												if (!$("#catId").val()) {

													isError = true;

													$("#error_catId").show()

												} else {
													$("#error_catId").hide()
												}
												if (!$("#typeId").val()) {

													isError = true;

													$("#error_typeId").show()

												} else {
													$("#error_typeId").hide()
												}
												if (!$("#deptId").val()) {

													isError = true;

													$("#error_deptId").show()

												} else {
													$("#error_deptId").hide()
												}
												if (!$("#permntAdd").val()) {

													isError = true;

													$("#error_permntAdd")
															.show()

												} else {
													$("#error_permntAdd")
															.hide()
												}
												if (!$("#tempAdd").val()) {

													isError = true;

													$("#error_tempAdd").show()

												} else {
													$("#error_tempAdd").hide()
												}

												if (!$("#emgContPrsn1").val()) {

													isError = true;

													$("#error_emgContPrsn1")
															.show()

												} else {
													$("#error_emgContPrsn1")
															.hide()
												}

												/* if (!$("#emgContPrsn2").val()) {

													isError = true;

													$("#error_emgContPrsn2")
															.show()

												} else {
													$("#error_emgContPrsn2")
															.hide()
												} */

												if (!$("#ratePerHr").val()) {

													isError = true;

													$("#error_ratePerHr")
															.show()

												} else {
													$("#error_ratePerHr")
															.hide()
												}

												if (!$("#prevsExpYr").val()) {

													isError = true;

													$("#error_prevsExpYr")
															.show()

												} else {
													$("#error_prevsExpYr")
															.hide()
												}

												if (!$("#prevsExpMn").val()) {

													isError = true;

													$("#error_prevsExpMn")
															.show()

												} else {
													$("#error_prevsExpMn")
															.hide()
												}
												if (!$("#locId2").val()) {

													isError = true;

													$("#error_locId2").show()

												} else {
													$("#error_locId2").hide()
												}
												if (!$("#uname").val()) {

													isError = true;

													$("#error_uname").show()

												} else {
													$("#error_uname").hide()
												}
												if (!$("#upass").val()) {

													isError = true;

													$("#error_upass").show()

												} else {
													$("#error_upass").hide()
												}

												if (!$("#mobile1").val()
														|| !validateMobile($(
																"#mobile1")
																.val())) {

													isError = true;

													if (!$("#mobile1").val()) {
														document
																.getElementById("error_mobile1").innerHTML = "This field is required.";
													} else {
														document
																.getElementById("error_mobile1").innerHTML = "Enter valid Mobile No.";
													}

													$("#error_mobile1").show()

												} else {
													$("#error_mobile1").hide()
												}

												if (!$("#emgContNo1").val()
														|| !validateMobile($(
																"#emgContNo1")
																.val())) {

													isError = true;

													if (!$("#emgContNo1").val()) {
														document
																.getElementById("error_emgContNo1").innerHTML = "This field is required.";
													} else {
														document
																.getElementById("error_emgContNo1").innerHTML = "Enter valid Mobile No.";
													}

													$("#error_emgContNo1")
															.show()

												} else {
													$("#error_emgContNo1")
															.hide()
												}

												if ($("#emgContNo2").val() != ""
														&& !validateMobile($(
																"#emgContNo2")
																.val())) {
													isError = true;
													document
															.getElementById("error_emgContNo2").innerHTML = "Enter valid Mobile No.";
													$("#error_emgContNo2")
															.show()
												} else {
													$("#error_emgContNo2")
															.hide()
												}

												if ($("#mobile2").val() != ""
														&& !validateMobile($(
																"#mobile2")
																.val())) {
													isError = true;
													document
															.getElementById("error_emgContNo2_alt").innerHTML = "Enter valid Mobile No.";
													$("#error_emgContNo2_alt")
															.show()
												} else {
													$("#error_emgContNo2_alt")
															.hide()
												}

												/* if (!$("#emgContNo2").val()
														|| !validateMobile($(
																"#emgContNo2")
																.val())) {

													isError = true;

													if (!$("#emgContNo2").val()) {
														document
																.getElementById("error_emgContNo2").innerHTML = "This field is required.";
													} else {
														document
																.getElementById("error_emgContNo2").innerHTML = "Enter valid Mobile No.";
													}

													$("#error_emgContNo2")
															.show()

												} else {
													$("#error_emgContNo2")
															.hide()
												} */

												if (!$("#email").val()
														|| !validateEmail($(
																"#email").val())) {

													isError = true;

													if (!$("#email").val()) {
														document
																.getElementById("error_email").innerHTML = "This field is required.";
													} else {
														document
																.getElementById("error_email").innerHTML = "Enter valid email.";
													}

													$("#error_email").show()

												} else {
													$("#error_email").hide()
												}
												if (!isError) {

													var x = true;
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
	<script type="text/javascript">
		function readURL(input) {
			/* 
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#image1').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			} */
		}

		$("#profilePic").change(function() {

			//readURL(this);
		});

		$(function() {

			//image 1
			// Create the close button

			// Clear event
			$('.image-preview-clear').click(function() {
				var imgid = $(this).attr('id');

				$('.browseimage' + imgid).val("");
				$('.image-preview-clear' + imgid).hide();

				//$('.image-preview-input-title'+imgid).text("Browse"); 
				$('.temppreviewimageki' + imgid).attr("src", '');
				$('.temppreviewimageki' + imgid).hide();
			});
			// Create the preview image
			$(".browseimage").change(
					function() {
						var img = $('<img/>', {
							id : 'dynamic',
							width : 250,
							height : 200,
						});
						var imgid = $(this).attr('id');
						var file = this.files[0];
						var reader = new FileReader();
						// Set preview image into the popover data-content
						reader.onload = function(e) {

							//	$('.image-preview-input-title'+imgid).text("Change");
							$('.image-preview-clear' + imgid).show();
							//	$('.image-preview-filename'+imgid).val(file.name);   
							img.attr('src', e.target.result);

							$(".temppreviewimageki" + imgid).attr("src",
									$(img)[0].src);
							$(".temppreviewimageki" + imgid).show();

						}
						reader.readAsDataURL(file);
					});
			//end  
		});
	</script>

</body>
</html>

