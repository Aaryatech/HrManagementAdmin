<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

				<!-- Form validation -->
				<div class="row">
					<div class="col-md-12">
						<!-- Title -->
						<div class="mb-3">
							<h6 class="mb-0 font-weight-semibold">Hidden labels</h6>
							<span class="text-muted d-block">Inputs with empty values</span>
						</div>
						<!-- /title -->


						<div class="card">
							<div class="card-header header-elements-inline">
								<h6 class="card-title">Right buttons</h6>
								<div class="header-elements">
									<div class="list-icons">
										<a class="list-icons-item" data-action="collapse"></a> <a
											class="list-icons-item" data-action="reload"></a> <a
											class="list-icons-item" data-action="remove"></a>
									</div>
								</div>
							</div>

							<div class="card-body">
								<form action="#">
									<div class="form-group row">
										<label class="col-form-label col-lg-2">Your name:</label>
										<div class="col-lg-10">
											<input type="text" class="form-control"
												placeholder="Eugene Kopyov">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2">Your password:</label>
										<div class="col-lg-10">
											<input type="password" class="form-control"
												placeholder="Your strong password">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2">Your message:</label>
										<div class="col-lg-10">
											<textarea rows="3" cols="3" class="form-control"
												placeholder="Enter your message here"></textarea>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Your message:</label>
										<div class="col-lg-10">
											<select name="select2" data-placeholder="Select a State..." id="select2"
												class="form-control form-control-select2 select2-hidden-accessible"
												required="" data-fouc="" tabindex="-1" aria-hidden="true">
												<option></option>
												<optgroup label="Alaskan/Hawaiian Time Zone">
													<option value="AK">Alaska</option>
													<option value="HI">Hawaii</option>
												</optgroup>
												<optgroup label="Pacific Time Zone">
													<option value="CA">California</option>
													<option value="NV">Nevada</option>
													<option value="OR">Oregon</option>
													<option value="WA">Washington</option>
												</optgroup>
												<optgroup label="Mountain Time Zone">
													<option value="AZ">Arizona</option>
													<option value="CO">Colorado</option>
													<option value="ID">Idaho</option>
													<option value="WY">Wyoming</option>
												</optgroup>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-lg-2" for="styled_file">Styled file
											uploader <span class="text-danger">*</span>
										</label>
										<div class="col-lg-9">
											<div class="uniform-uploader">
												<input type="file" name="styled_file"
													class="form-input-styled" required="" data-fouc="" id="styled_file"><span
													class="filename" style="user-select: none;">No file
													selected</span><span class="action btn bg-blue legitRipple"
													style="user-select: none;">Choose File</span>
											</div>
											<label id="styled_file-error"
												class="validation-invalid-label" for="styled_file">This
												field is required.</label>
										</div>
									</div>



									<div class="form-group row mb-0">
										<div class="col-lg-10 ml-lg-auto">
											<button type="submit" class="btn btn-light legitRipple">
												Cancel
												<div class="legitRipple-ripple"
													style="left: 62.0495%; top: 68.4211%; transform: translate3d(-50%, -50%, 0px); transition-duration: 0.15s, 0.5s; width: 219.85%;"></div>
											</button>
											<button type="submit" class="btn bg-blue ml-3 legitRipple">
												Submit <i class="icon-paperplane ml-2"></i>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>


					</div>
				</div>
				<!-- /form validation -->
				<!-- table listing  area -->
				<!-- Highlighting rows and columns -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Highlighting rows and columns</h5>
						<div class="header-elements">
							<div class="list-icons">
								<a class="list-icons-item" data-action="collapse"></a> <a
									class="list-icons-item" data-action="reload"></a> <a
									class="list-icons-item" data-action="remove"></a>
							</div>
						</div>
					</div>

					<div class="card-body">
						Highlighting rows and columns have be quite useful for drawing
						attention to where the user's cursor is in a table, particularly
						if you have a lot of narrow columns. Of course the highlighting of
						a row is easy enough using CSS, but for column highlighting, you
						need to use a little bit of Javascript. This example shows that in
						action on DataTable by making use of the
						<code>cell().index()</code>
						,
						<code>cells().nodes()</code>
						and
						<code>column().nodes()</code>
						methods.
					</div>
					<table
						class="table table-bordered table-hover datatable-highlight1 datatable-button-html5-basic  datatable-button-print-columns1"
						id="printtable1">
						<thead>
							<tr class="bg-blue">
								<th>First Name</th>
								<th>Last Name</th>
								<th>Job Title</th>
								<th>DOB</th>
								<th>Status</th>
								<th class="text-center">Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Marth</td>
								<td><a href="#">Enright</a></td>
								<td>Traffic Court Referee</td>
								<td>22 Jun 1972</td>
								<td><span class="badge badge-success">Active</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Jackelyn</td>
								<td>Weible</td>
								<td><a href="#">Airline Transport Pilot</a></td>
								<td>3 Oct 1981</td>
								<td><span class="badge badge-secondary">Inactive</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Aura</td>
								<td>Hard</td>
								<td>Business Services Sales Representative</td>
								<td>19 Apr 1969</td>
								<td><span class="badge badge-danger">Suspended</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Nathalie</td>
								<td><a href="#">Pretty</a></td>
								<td>Drywall Stripper</td>
								<td>13 Dec 1977</td>
								<td><span class="badge badge-info">Pending</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Sharan</td>
								<td>Leland</td>
								<td>Aviation Tactical Readiness Officer</td>
								<td>30 Dec 1991</td>
								<td><span class="badge badge-secondary">Inactive</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Maxine</td>
								<td><a href="#">Woldt</a></td>
								<td><a href="#">Business Services Sales Representative</a></td>
								<td>17 Oct 1987</td>
								<td><span class="badge badge-info">Pending</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Sylvia</td>
								<td><a href="#">Mcgaughy</a></td>
								<td>Hemodialysis Technician</td>
								<td>11 Nov 1983</td>
								<td><span class="badge badge-danger">Suspended</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Lizzee</td>
								<td><a href="#">Goodlow</a></td>
								<td>Technical Services Librarian</td>
								<td>1 Nov 1961</td>
								<td><span class="badge badge-danger">Suspended</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Kennedy</td>
								<td>Haley</td>
								<td>Senior Marketing Designer</td>
								<td>18 Dec 1960</td>
								<td><span class="badge badge-success">Active</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Chantal</td>
								<td><a href="#">Nailor</a></td>
								<td>Technical Services Librarian</td>
								<td>10 Jan 1980</td>
								<td><span class="badge badge-secondary">Inactive</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Delma</td>
								<td>Bonds</td>
								<td>Lead Brand Manager</td>
								<td>21 Dec 1968</td>
								<td><span class="badge badge-info">Pending</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Roland</td>
								<td>Salmos</td>
								<td><a href="#">Senior Program Developer</a></td>
								<td>5 Jun 1986</td>
								<td><span class="badge badge-secondary">Inactive</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Coy</td>
								<td>Wollard</td>
								<td>Customer Service Operator</td>
								<td>12 Oct 1982</td>
								<td><span class="badge badge-success">Active</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Maxwell</td>
								<td>Maben</td>
								<td>Regional Representative</td>
								<td>25 Feb 1988</td>
								<td><span class="badge badge-danger">Suspended</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>Cicely</td>
								<td>Sigler</td>
								<td><a href="#">Senior Research Officer</a></td>
								<td>15 Mar 1960</td>
								<td><span class="badge badge-info">Pending</span></td>
								<td class="text-center">
									<div class="list-icons">
										<div class="dropdown">
											<a href="#" class="list-icons-item" data-toggle="dropdown">
												<i class="icon-menu9"></i>
											</a>

											<div class="dropdown-menu dropdown-menu-right">
												<a href="#" class="dropdown-item"><i
													class="icon-file-pdf"></i> Export to .pdf</a> <a href="#"
													class="dropdown-item"><i class="icon-file-excel"></i>
													Export to .csv</a> <a href="#" class="dropdown-item"><i
													class="icon-file-word"></i> Export to .doc</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /highlighting rows and columns -->
				<!-- table listing  area -->
				<!-- Left fixed column -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Left fixed column</h5>
						<div class="header-elements">
							<div class="list-icons">
								<a class="list-icons-item" data-action="collapse"></a> <a
									class="list-icons-item" data-action="reload"></a> <a
									class="list-icons-item" data-action="remove"></a>
							</div>
						</div>
					</div>

					<div class="card-body">
						When displaying a table which scrolls along the
						<code>x-axis</code>
						, it can sometimes be useful to the end user for the left most
						column to be
						<code>fixed</code>
						in place, if it shows grouping, index or similar information. This
						is basically the same idea as
						<code>'freeze columns'</code>
						in Excel. This can be achieved with the
						<code>FixedColumns</code>
						plug-in for DataTables, as shown below.
					</div>

					<table
						class="table table-bordered table-hover   datatable-fixed-left">
						<thead>
							<tr class="bg-blue">
								<th>First name</th>
								<th>Last name</th>
								<th>Position</th>
								<th>Office</th>
								<th>Age</th>
								<th>Start date</th>
								<th>Salary</th>
								<th>Extn.</th>
								<th>E-mail</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Tiger</td>
								<td>Nixon</td>
								<td>System Architect</td>
								<td>Edinburgh</td>
								<td>61</td>
								<td>2011/04/25</td>
								<td><span class="badge badge-info">$320,800</span></td>
								<td>5421</td>
								<td><a href="#">t.nixon@datatables.net</a></td>
							</tr>
							<tr>
								<td>Garrett</td>
								<td>Winters</td>
								<td>Accountant</td>
								<td>Tokyo</td>
								<td>63</td>
								<td>2011/07/25</td>
								<td><span class="badge badge-danger">$170,750</span></td>
								<td>8422</td>
								<td><a href="#">g.winters@datatables.net</a></td>
							</tr>
							<tr>
								<td>Ashton</td>
								<td>Cox</td>
								<td>Junior Technical Author</td>
								<td>San Francisco</td>
								<td>66</td>
								<td>2009/01/12</td>
								<td><span class="badge badge-secondary">$86,000</span></td>
								<td>1562</td>
								<td><a href="#">a.cox@datatables.net</a></td>
							</tr>
							<tr>
								<td>Cedric</td>
								<td>Kelly</td>
								<td>Senior Javascript Developer</td>
								<td>Edinburgh</td>
								<td>22</td>
								<td>2012/03/29</td>
								<td><span class="badge badge-success">$433,060</span></td>
								<td>6224</td>
								<td><a href="#">c.kelly@datatables.net</a></td>
							</tr>
							<tr>
								<td>Airi</td>
								<td>Satou</td>
								<td>Accountant</td>
								<td>Tokyo</td>
								<td>33</td>
								<td>2008/11/28</td>
								<td><span class="badge badge-danger">$162,700</span></td>
								<td>5407</td>
								<td><a href="#">a.satou@datatables.net</a></td>
							</tr>
							<tr>
								<td>Brielle</td>
								<td>Williamson</td>
								<td>Integration Specialist</td>
								<td>New York</td>
								<td>61</td>
								<td>2012/12/02</td>
								<td><span class="badge badge-info">$372,000</span></td>
								<td>4804</td>
								<td><a href="#">b.williamson@datatables.net</a></td>
							</tr>
							<tr>
								<td>Herrod</td>
								<td>Chandler</td>
								<td>Sales Assistant</td>
								<td>San Francisco</td>
								<td>59</td>
								<td>2012/08/06</td>
								<td><span class="badge badge-danger">$137,500</span></td>
								<td>9608</td>
								<td><a href="#">h.chandler@datatables.net</a></td>
							</tr>
							<tr>
								<td>Rhona</td>
								<td>Davidson</td>
								<td>Integration Specialist</td>
								<td>Tokyo</td>
								<td>55</td>
								<td>2010/10/14</td>
								<td><span class="badge badge-secondary">$97,900</span></td>
								<td>6200</td>
								<td><a href="#">r.davidson@datatables.net</a></td>
							</tr>
							<tr>
								<td>Colleen</td>
								<td>Hurst</td>
								<td>Javascript Developer</td>
								<td>San Francisco</td>
								<td>39</td>
								<td>2009/09/15</td>
								<td><span class="badge badge-success">$405,500</span></td>
								<td>2360</td>
								<td><a href="#">c.hurst@datatables.net</a></td>
							</tr>
							<tr>
								<td>Sonya</td>
								<td>Frost</td>
								<td>Software Engineer</td>
								<td>Edinburgh</td>
								<td>23</td>
								<td>2008/12/13</td>
								<td><span class="badge badge-danger">$103,600</span></td>
								<td>1667</td>
								<td><a href="#">s.frost@datatables.net</a></td>
							</tr>
							<tr>
								<td>Jena</td>
								<td>Gaines</td>
								<td>Office Manager</td>
								<td>London</td>
								<td>30</td>
								<td>2008/12/19</td>
								<td><span class="badge badge-secondary">$90,560</span></td>
								<td>3814</td>
								<td><a href="#">j.gaines@datatables.net</a></td>
							</tr>
							<tr>
								<td>Quinn</td>
								<td>Flynn</td>
								<td>Support Lead</td>
								<td>Edinburgh</td>
								<td>22</td>
								<td>2013/03/03</td>
								<td><span class="badge badge-info">$342,000</span></td>
								<td>9497</td>
								<td><a href="#">q.flynn@datatables.net</a></td>
							</tr>
							<tr>
								<td>Charde</td>
								<td>Marshall</td>
								<td>Regional Director</td>
								<td>San Francisco</td>
								<td>36</td>
								<td>2008/10/16</td>
								<td><span class="badge badge-success">$470,600</span></td>
								<td>6741</td>
								<td><a href="#">c.marshall@datatables.net</a></td>
							</tr>
							<tr>
								<td>Haley</td>
								<td>Kennedy</td>
								<td>Senior Marketing Designer</td>
								<td>London</td>
								<td>43</td>
								<td>2012/12/18</td>
								<td><span class="badge badge-danger">$113,500</span></td>
								<td>3597</td>
								<td><a href="#">h.kennedy@datatables.net</a></td>
							</tr>
							<tr>
								<td>Tatyana</td>
								<td>Fitzpatrick</td>
								<td>Regional Director</td>
								<td>London</td>
								<td>19</td>
								<td>2010/03/17</td>
								<td><span class="badge badge-info">$385,750</span></td>
								<td>1965</td>
								<td><a href="#">t.fitzpatrick@datatables.net</a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /left fixed column -->
				<!-- /table listing  area -->
				<!-- /table listing  area -->
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