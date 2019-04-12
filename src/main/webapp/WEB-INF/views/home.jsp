<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Limitless - Responsive Web Application Kit by Eugene Kopyov</title>

	<!-- Global stylesheets -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/global_assets/css/icons/icomoon/styles.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap_limitless.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/layout.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/components.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/colors.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/customecss.css" rel="stylesheet" type="text/css">
	
	<!-- /global stylesheets -->

	<!-- Core JS files -->
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/main/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/main/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/loaders/blockui.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/ui/ripple.min.js"></script>
	<!-- /core JS files -->

	<!-- Theme JS files -->
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/visualization/d3/d3.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/visualization/d3/d3_tooltip.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/forms/styling/switchery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/ui/moment/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/pickers/daterangepicker.js"></script>

	<script src="${pageContext.request.contextPath}/resources/assets/js/app.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/demo_pages/dashboard.js"></script>
	<!-- /theme JS files -->

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
			 

				<div class="breadcrumb-line breadcrumb-line-light header-elements-md-inline">
					<div class="d-flex">
						<div class="breadcrumb">
							<a href="index.html" class="breadcrumb-item"><i class="icon-home2 mr-2"></i> Home</a>
							<span class="breadcrumb-item active">Dashboard</span>
						</div>

						<a href="#" class="header-elements-toggle text-default d-md-none"><i class="icon-more"></i></a>
					</div>

				 
				</div>
			</div>
			<!-- /page header -->


			<!-- Content area -->
			<div class="content">

				<!-- Form validation -->
				<div class="card">
					<div class="card-header header-elements-inline">
						<h5 class="card-title">Form validation</h5>
						<div class="header-elements">
							<div class="list-icons">
		                		<a class="list-icons-item" data-action="collapse"></a>
		                		<a class="list-icons-item" data-action="reload"></a>
		                		<a class="list-icons-item" data-action="remove"></a>
		                	</div>
	                	</div>
					</div>

					<div class="card-body">
						<p class="mb-4">Validate.js makes simple clientside form validation easy, whilst still offering plenty of customization options. The plugin comes bundled with a useful set of validation methods, including URL and email validation, while providing an API to write your own methods. All bundled methods come with default error messages in english and translations into 37 other languages. <strong>Note:</strong> <code>success</code> callback is configured for demo purposes only and can be removed in validation setup.</p>

						<form class="form-validate-jquery" action="#" novalidate="novalidate">
							<fieldset class="mb-3">
								<legend class="text-uppercase font-size-sm font-weight-bold">Basic inputs</legend>

								<!-- Basic text input -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Basic text input <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="basic" class="form-control" required="" placeholder="Text input validation" aria-invalid="false"><label id="basic-error" class="validation-invalid-label validation-valid-label" for="basic">Success.</label>
									</div>
								</div>
								<!-- /basic text input -->


								<!-- Input with icons -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Input with icon <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-group-feedback form-group-feedback-right">
											<input type="text" name="with_icon" class="form-control" required="" placeholder="Text input with icon validation" aria-invalid="false">
											<div class="form-control-feedback">
												<i class="icon-droplets"></i>
											</div>
										<label id="with_icon-error" class="validation-invalid-label validation-valid-label" for="with_icon">Success.</label></div>
									</div>
								</div>
								<!-- /input with icons -->


								<!-- Input group -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Input group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="icon-mention"></i></span>
											</div>
											<input type="text" name="input_group" class="form-control" required="" placeholder="Input group validation">
										</div>
									<label id="input_group-error" class="validation-invalid-label" for="input_group">This field is required.</label></div>
								</div>
								<!-- /input group -->


								<!-- Password field -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Password field <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="password" name="password" id="password" class="form-control" required="" placeholder="Minimum 5 characters allowed"><label id="password-error" class="validation-invalid-label" for="password">This field is required.</label>
									</div>
								</div>
								<!-- /password field -->


								<!-- Repeat password -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Repeat password <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="password" name="repeat_password" class="form-control" required="" placeholder="Try different password"><label id="repeat_password-error" class="validation-invalid-label" for="repeat_password">This field is required.</label>
									</div>
								</div>
								<!-- /repeat password -->


								<!-- Email field -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Email field <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="email" name="email" class="form-control validate-equalTo-blur" id="email" required="" placeholder="Enter a valid email address"><label id="email-error" class="validation-invalid-label" for="email">This field is required.</label>
									</div>
								</div>
								<!-- /email field -->


								<!-- Repeat email -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Repeat email <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="email" name="repeat_email" class="form-control" required="" placeholder="Enter a valid email address" aria-invalid="true"><label id="repeat_email-error" class="validation-invalid-label" for="repeat_email">Please enter the same value again.</label>
									</div>
								</div>
								<!-- /repeat email -->


								<!-- Minimum characters -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Minimum characters <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="minimum_characters" class="form-control" required="" placeholder="Enter at least 10 characters"><label id="minimum_characters-error" class="validation-invalid-label" for="minimum_characters">This field is required.</label>
									</div>
								</div>
								<!-- /minimum characters -->


								<!-- Maximum characters -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Maximum characters <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="maximum_characters" class="form-control" required="" placeholder="Enter 10 characters maximum"><label id="maximum_characters-error" class="validation-invalid-label" for="maximum_characters">This field is required.</label>
									</div>
								</div>
								<!-- /maximum characters -->


								<!-- Minimum number -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Minimum number <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="minimum_number" class="form-control" required="" placeholder="Enter a value greater than or equal to 10"><label id="minimum_number-error" class="validation-invalid-label" for="minimum_number">This field is required.</label>
									</div>
								</div>
								<!-- /minimum number -->


								<!-- Maximum number -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Maximum number <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="maximum_number" class="form-control" required="" placeholder="Please enter a value less than or equal to 10"><label id="maximum_number-error" class="validation-invalid-label" for="maximum_number">This field is required.</label>
									</div>
								</div>
								<!-- /maximum number -->


								<!-- Basic textarea -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Basic textarea <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<textarea rows="5" cols="5" name="textarea" class="form-control" required="" placeholder="Default textarea"></textarea><label id="textarea-error" class="validation-invalid-label" for="textarea">This field is required.</label>
									</div>
								</div>
								<!-- /basic textarea -->

							</fieldset>

							<fieldset class="mb-3">
								<legend class="text-uppercase font-size-sm font-weight-bold">Advanced inputs</legend>

								<!-- Number range -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Number range <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="number_range" class="form-control" required="" placeholder="Enter a value between 10 and 20" aria-invalid="true"><label id="number_range-error" class="validation-invalid-label" for="number_range">This field is required.</label>
									</div>
								</div>
								<!-- /number range -->


								<!-- Touchspin spinners -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Touchspin spinner <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="input-group bootstrap-touchspin"><span class="input-group-prepend"><button class="btn btn-light bootstrap-touchspin-down legitRipple" type="button">–</button></span><span class="input-group-prepend bootstrap-touchspin-prefix d-none"><span class="input-group-text"></span></span><input type="text" name="touchspin" value="" required="" class="form-control touchspin-postfix" placeholder="Not valid if empty" style="display: block;"><span class="input-group-append bootstrap-touchspin-postfix"><span class="input-group-text">%</span></span><span class="input-group-append"><button class="btn btn-light bootstrap-touchspin-up legitRipple" type="button">+</button></span></div>
									<label id="touchspin-error" class="validation-invalid-label" for="touchspin">This field is required.</label></div>
								</div>
								<!-- /touchspin spinners -->


								<!-- Custom message -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Custom message <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="custom" class="form-control" required="" placeholder="Custom error message"><label id="custom-error" class="validation-invalid-label" for="custom">This is a custom error message</label>
									</div>
								</div>
								<!-- /custom message -->


								<!-- URL validation -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">URL validation <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="url" class="form-control" required="" placeholder="Enter a valid URL address"><label id="url-error" class="validation-invalid-label" for="url">This field is required.</label>
									</div>
								</div>
								<!-- /url validation -->


								<!-- Date validation -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Date validation <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="date" class="form-control" required="" placeholder="April, 2014 or any other date format"><label id="date-error" class="validation-invalid-label" for="date">This field is required.</label>
									</div>
								</div>
								<!-- /date validation -->


								<!-- ISO date validation -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">ISO date validation <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="date_iso" class="form-control" required="" placeholder="YYYY/MM/DD or any other ISO date format"><label id="date_iso-error" class="validation-invalid-label" for="date_iso">This field is required.</label>
									</div>
								</div>
								<!-- /iso date validation -->


								<!-- Numbers only -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Numbers only <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="numbers" class="form-control" required="" placeholder="Enter decimal number only"><label id="numbers-error" class="validation-invalid-label" for="numbers">This field is required.</label>
									</div>
								</div>
								<!-- /numbers only -->


								<!-- Digits only -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Digits only <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="digits" class="form-control" required="" placeholder="Enter digits only"><label id="digits-error" class="validation-invalid-label" for="digits">This field is required.</label>
									</div>
								</div>
								<!-- /digits only -->


								<!-- Credit card validation -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Credit card validation <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="text" name="card" class="form-control" required="" placeholder="Enter credit card number. Try 446-667-651"><label id="card-error" class="validation-invalid-label" for="card">This field is required.</label>
									</div>
								</div>
								<!-- /credit card validation -->


								<!-- Basic file uploader -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Basic file uploader <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<input type="file" name="unstyled_file" class="form-control" required=""><label id="unstyled_file-error" class="validation-invalid-label" for="unstyled_file">This field is required.</label>
									</div>
								</div>
								<!-- /basic file uploader -->


								<!-- Styled file uploader -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Styled file uploader <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="uniform-uploader"><input type="file" name="styled_file" class="form-input-styled" required="" data-fouc=""><span class="filename" style="user-select: none;">No file selected</span><span class="action btn bg-blue legitRipple" style="user-select: none;">Choose File</span></div>
									<label id="styled_file-error" class="validation-invalid-label" for="styled_file">This field is required.</label></div>
								</div>
								<!-- /styled file uploader -->


								<!-- Basic select -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Basic select <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<select name="default_select" class="form-control" required="">
											<option value="">Choose an option</option> 
											<optgroup label="Alaskan/Hawaiian Time Zone">
												<option value="AK">Alaska</option>
												<option value="HI">Hawaii</option>
												<option value="CA">California</option>
											</optgroup>
											<optgroup label="Mountain Time Zone">
												<option value="AZ">Arizona</option>
												<option value="CO">Colorado</option>
												<option value="WY">Wyoming</option>
											</optgroup>
											<optgroup label="Central Time Zone">
												<option value="AL">Alabama</option>
												<option value="AR">Arkansas</option>
												<option value="KY">Kentucky</option>
											</optgroup>
										</select><label id="default_select-error" class="validation-invalid-label" for="default_select">This field is required.</label>
									</div>
								</div>
								<!-- /basic select -->


								<!-- Styled select -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Styled select <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="uniform-select fixedWidth"><span style="user-select: none;">Choose an option</span><select name="styled_select" class="form-control form-input-styled" required="" data-fouc="">
											<option value="">Choose an option</option> 
											<optgroup label="Alaskan/Hawaiian Time Zone">
												<option value="AK">Alaska</option>
												<option value="HI">Hawaii</option>
												<option value="CA">California</option>
											</optgroup>
											<optgroup label="Mountain Time Zone">
												<option value="AZ">Arizona</option>
												<option value="CO">Colorado</option>
												<option value="WY">Wyoming</option>
											</optgroup>
											<optgroup label="Central Time Zone">
												<option value="AL">Alabama</option>
												<option value="AR">Arkansas</option>
												<option value="KY">Kentucky</option>
											</optgroup>
										</select></div>
									<label id="styled_select-error" class="validation-invalid-label" for="styled_select">This field is required.</label></div>
								</div>
								<!-- /styled asic select -->


								<!-- Select2 select -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Select2 select <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<select name="select2" data-placeholder="Select a State..." class="form-control form-control-select2 select2-hidden-accessible" required="" data-fouc="" tabindex="-1" aria-hidden="true">
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
										</select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-labelledby="select2-select2-fy-container"><span class="select2-selection__rendered" id="select2-select2-fy-container"><span class="select2-selection__placeholder">Select a State...</span></span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
									<label id="select2-error" class="validation-invalid-label" for="select2">This field is required.</label></div>
								</div>
								<!-- /select2 select -->


								<!-- Multiple select -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Multiple select <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<select name="default_multiple_select" class="form-control" multiple="" required="">
											<optgroup label="Alaskan/Hawaiian Time Zone">
												<option value="AK">Alaska</option>
												<option value="HI">Hawaii</option>
												<option value="CA">California</option>
												<option value="NV">Nevada</option>
												<option value="WA">Washington</option>
											</optgroup>
											<optgroup label="Mountain Time Zone">
												<option value="AZ">Arizona</option>
												<option value="CO">Colorado</option>
												<option value="ID">Idaho</option>
												<option value="WY">Wyoming</option>
											</optgroup>
											<optgroup label="Central Time Zone">
												<option value="AL">Alabama</option>
												<option value="AR">Arkansas</option>
												<option value="IL">Illinois</option>
												<option value="KS">Kansas</option>
												<option value="KY">Kentucky</option>
											</optgroup>
										</select><label id="default_multiple_select-error" class="validation-invalid-label" for="default_multiple_select">This field is required.</label>
									</div>
								</div>
								<!-- /multiple select -->

							</fieldset>

							<fieldset class="mb-3">
								<legend class="text-uppercase font-size-sm font-weight-bold">Checkboxes and radios</legend>

								<!-- Basic single checkbox -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Basic single checkbox <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check">
											<label class="form-check-label">
												<input type="checkbox" class="form-check-input" name="single_basic_checkbox" required="">
												Accept our terms
											</label>
										</div>
									<label id="single_basic_checkbox-error" class="validation-invalid-label" for="single_basic_checkbox">This field is required.</label></div>
								</div>
								<!-- /basic singlecheckbox -->


								<!-- Basic checkbox group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Basic checkbox group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check">
											<label class="form-check-label">
												<input type="checkbox" class="form-check-input" name="basic_checkbox" required="">
												Duis eget nibh purus
											</label>
										</div>

										<div class="form-check">
											<label class="form-check-label">
												<input type="checkbox" class="form-check-input" name="basic_checkbox">
												Maecenas non nulla ac nunc
											</label>
										</div>

										<div class="form-check">
											<label class="form-check-label">
												<input type="checkbox" class="form-check-input" name="basic_checkbox">
												Maecenas egestas tristique
											</label>
										</div>
									<label id="basic_checkbox-error" class="validation-invalid-label" for="basic_checkbox">This field is required.</label></div>
								</div>
								<!-- /basic checkbox group -->


								<!-- Inline checkbox group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Basic inline checkbox group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-inline">
											<label class="form-check-label">
												<input type="checkbox" class="form-check-input" name="basic_inline_checkbox" required="">
												Duis eget nibh purus
											</label>
										</div>

										<div class="form-check form-check-inline">
											<label class="form-check-label">
												<input type="checkbox" class="form-check-input" name="basic_inline_checkbox">
												Maecenas non nulla ac nunc
											</label>
										</div>
									<label id="basic_inline_checkbox-error" class="validation-invalid-label" for="basic_inline_checkbox">This field is required.</label></div>
								</div>
								<!-- /inline checkbox group -->


								<!-- Basic radio group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Basic radio group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check">
											<label class="form-check-label">
												<input type="radio" class="form-check-input" name="basic_radio" required="">
												Cras leo turpis malesuada eget
											</label>
										</div>

										<div class="form-check">
											<label class="form-check-label">
												<input type="radio" class="form-check-input" name="basic_radio">
												Maecenas congue justo vel ipsum
											</label>
										</div>
									<label id="basic_radio-error" class="validation-invalid-label" for="basic_radio">This field is required.</label></div>
								</div>
								<!-- /basic radio group -->


								<!-- Basic inline radio group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Basic inline radio group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-inline">
											<label class="form-check-label">
												<input type="radio" class="form-check-input" name="basic_radio_group" required="">
												Cras leo turpis malesuada eget
											</label>
										</div>

										<div class="form-check form-check-inline">
											<label class="form-check-label">
												<input type="radio" class="form-check-input" name="basic_radio_group">
												Maecenas congue justo vel ipsum
											</label>
										</div>
									<label id="basic_radio_group-error" class="validation-invalid-label" for="basic_radio_group">This field is required.</label></div>
								</div>
								<!-- /basic inline radio group -->


								<hr class="mb-4">


								<!-- Single styled checkbox -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Single styled checkbox <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check">
											<label class="form-check-label">
												<div class="uniform-checker"><span><input type="checkbox" name="single_styled_checkbox" class="form-input-styled" required="" data-fouc=""></span></div>
												Accept our terms
											</label>
										</div>
									<label id="single_styled_checkbox-error" class="validation-invalid-label" for="single_styled_checkbox">This field is required.</label></div>
								</div>
								<!-- /single styled checkbox -->


								<!-- Styled checkbox group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Styled checkbox group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check">
											<label class="form-check-label">
												<div class="uniform-checker"><span><input type="checkbox" name="styled_checkbox" class="form-input-styled" required="" data-fouc=""></span></div>
												Duis eget nibh purus
											</label>
										</div>

										<div class="form-check">
											<label class="form-check-label">
												<div class="uniform-checker"><span><input type="checkbox" name="styled_checkbox" class="form-input-styled" data-fouc=""></span></div>
												Maecenas non nulla ac nunc
											</label>
										</div>

										<div class="form-check">
											<label class="form-check-label">
												<div class="uniform-checker"><span><input type="checkbox" name="styled_checkbox" class="form-input-styled" data-fouc=""></span></div>
												Maecenas egestas tristique
											</label>
										</div>
									<label id="styled_checkbox-error" class="validation-invalid-label" for="styled_checkbox">This field is required.</label></div>
								</div>
								<!-- /styled checkbox group -->


								<!-- Inline checkbox group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Inline checkbox group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-inline">
											<label class="form-check-label">
												<div class="uniform-checker"><span><input type="checkbox" name="styled_inline_checkbox" class="form-input-styled" required="" data-fouc=""></span></div>
												Duis eget nibh purus
											</label>
										</div>
						
										<div class="form-check form-check-inline">
											<label class="form-check-label">
												<div class="uniform-checker"><span><input type="checkbox" name="styled_inline_checkbox" class="form-input-styled" data-fouc=""></span></div>
												Maecenas non nulla ac nunc
											</label>
										</div>
									<label id="styled_inline_checkbox-error" class="validation-invalid-label" for="styled_inline_checkbox">This field is required.</label></div>
								</div>
								<!-- /inline checkbox group -->


								<!-- Styled radio group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Styled radio group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check">
											<label class="form-check-label">
												<div class="uniform-choice"><span><input type="radio" name="styled_radio" class="form-input-styled" required="" data-fouc=""></span></div>
												Cras leo turpis malesuada eget
											</label>
										</div>

										<div class="form-check">
											<label class="form-check-label">
												<div class="uniform-choice"><span><input type="radio" name="styled_radio" class="form-input-styled" data-fouc=""></span></div>
												Maecenas congue justo vel ipsum
											</label>
										</div>
									<label id="styled_radio-error" class="validation-invalid-label" for="styled_radio">This field is required.</label></div>
								</div>
								<!-- /styled radio group -->


								<!-- Styled inline radio group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Styled inline radio group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-inline">
											<label class="form-check-label">
												<div class="uniform-choice"><span><input type="radio" name="styled_inline_radio" class="form-input-styled" required="" data-fouc=""></span></div>
												Cras leo turpis malesuada eget
											</label>
										</div>

										<div class="form-check form-check-inline">
											<label class="form-check-label">
												<div class="uniform-choice"><span><input type="radio" name="styled_inline_radio" class="form-input-styled" data-fouc=""></span></div>
												Maecenas congue justo vel ipsum
											</label>
										</div>
									<label id="styled_inline_radio-error" class="validation-invalid-label" for="styled_inline_radio">This field is required.</label></div>
								</div>
								<!-- /styled inline radio group -->

							</fieldset>

							<fieldset>
								<legend class="text-uppercase font-size-sm font-weight-bold">Switcher components</legend>

								<!-- Switchery single -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Swichery single <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-switchery switchery-sm">
											<label class="form-check-label">
												<input type="checkbox" name="switchery_single" class="form-input-switchery" required="" data-fouc="" data-switchery="true" style="display: none;"><span class="switchery switchery-default" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s;"><small style="left: 0px; transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
												Accept our terms
											</label>
										</div>
									<label id="switchery_single-error" class="validation-invalid-label" for="switchery_single">This field is required.</label></div>
								</div>
								<!-- /switchery single -->


								<!-- Switchery group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Swichery group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-switchery switchery-sm">
											<label class="form-check-label">
												<input type="checkbox" name="switchery_group" class="form-input-switchery" required="" data-fouc="" data-switchery="true" style="display: none;"><span class="switchery switchery-default" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s;"><small style="left: 0px; transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
												Duis eget nibh purus
											</label>
										</div>

										<div class="form-check form-check-switchery switchery-sm">
											<label class="form-check-label">
												<input type="checkbox" name="switchery_group" class="form-input-switchery" data-fouc="" data-switchery="true" style="display: none;"><span class="switchery switchery-default" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s;"><small style="left: 0px; transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
												Maecenas non nulla ac nunc
											</label>
										</div>

										<div class="form-check form-check-switchery switchery-sm">
											<label class="form-check-label">
												<input type="checkbox" name="switchery_group" class="form-input-switchery" data-fouc="" data-switchery="true" style="display: none;"><span class="switchery switchery-default" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s;"><small style="left: 0px; transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
												Maecenas egestas tristique
											</label>
										</div>
									<label id="switchery_group-error" class="validation-invalid-label" for="switchery_group">This field is required.</label></div>
								</div>
								<!-- /switchery group -->


								<!-- Inline switchery group -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Inline swichery group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-inline form-check-switchery switchery-sm">
											<label class="form-check-label">
												<input type="checkbox" name="inline_switchery_group" class="form-input-switchery" required="" data-fouc="" data-switchery="true" style="display: none;"><span class="switchery switchery-default" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s;"><small style="left: 0px; transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
												Duis eget nibh purus
											</label>
										</div>

										<div class="form-check form-check-inline form-check-switchery switchery-sm">
											<label class="form-check-label">
												<input type="checkbox" name="inline_switchery_group" class="form-input-switchery" data-fouc="" data-switchery="true" style="display: none;"><span class="switchery switchery-default" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s;"><small style="left: 0px; transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
												Maecenas egestas tristique
											</label>
										</div>
									<label id="inline_switchery_group-error" class="validation-invalid-label" for="inline_switchery_group">This field is required.</label></div>
								</div>
								<!-- /inline switchery group -->


								<hr>


								<!-- Switch single -->
								<div class="form-group row">
									<label class="col-lg-3 col-form-label">Switch single <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-switch form-check-switch-left">
											<label class="form-check-label d-flex align-items-center">
												<div class="bootstrap-switch-off bootstrap-switch bootstrap-switch-wrapper bootstrap-switch-animate" style="width: 102px;"><div class="bootstrap-switch-container" style="width: 150px; margin-left: -50px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 50px;">Yes</span><span class="bootstrap-switch-label" style="width: 50px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 50px;">No</span><input type="checkbox" name="switch_single" data-on-text="Yes" data-off-text="No" class="form-input-switch" required=""></div></div>
												Accept our terms
											</label>
										</div>
									<label id="switch_single-error" class="validation-invalid-label" for="switch_single">This field is required.</label></div>
								</div>
								<!-- /switch single -->


								<!-- Switch group -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Switch group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-switch form-check-switch-left">
											<label class="form-check-label d-flex align-items-center">
												<div class="bootstrap-switch bootstrap-switch-wrapper bootstrap-switch-animate bootstrap-switch-on" style="width: 112px;"><div class="bootstrap-switch-container" style="width: 165px; margin-left: 0px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 55px;">ON</span><span class="bootstrap-switch-label" style="width: 55px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 55px;">OFF</span><input type="checkbox" name="switch_group" class="form-input-switch" required="" aria-invalid="true"></div></div>
												Duis eget nibh purus
											</label>
										</div>

										<div class="form-check form-check-switch form-check-switch-left">
											<label class="form-check-label d-flex align-items-center">
												<div class="bootstrap-switch bootstrap-switch-wrapper bootstrap-switch-animate bootstrap-switch-on" style="width: 112px;"><div class="bootstrap-switch-container" style="width: 165px; margin-left: 0px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 55px;">ON</span><span class="bootstrap-switch-label" style="width: 55px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 55px;">OFF</span><input type="checkbox" name="switch_group" class="form-input-switch" aria-invalid="false"></div></div>
												Maecenas non nulla ac nunc
											</label>
										</div>

										<div class="form-check form-check-switch form-check-switch-left">
											<label class="form-check-label d-flex align-items-center">
												<div class="bootstrap-switch bootstrap-switch-wrapper bootstrap-switch-animate bootstrap-switch-on" style="width: 112px;"><div class="bootstrap-switch-container" style="width: 165px; margin-left: 0px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 55px;">ON</span><span class="bootstrap-switch-label" style="width: 55px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 55px;">OFF</span><input type="checkbox" name="switch_group" class="form-input-switch" aria-invalid="false"></div></div>
												Maecenas egestas tristique
											</label>
										</div>
									<label id="switch_group-error" class="validation-invalid-label validation-valid-label" for="switch_group">Success.</label></div>
								</div>
								<!-- /switch group -->


								<!-- Inline switch group -->
								<div class="form-group row">
									<label class="col-form-label col-lg-3">Inline switch group <span class="text-danger">*</span></label>
									<div class="col-lg-9">
										<div class="form-check form-check-inline form-check-switch form-check-switch-left">
											<label class="form-check-label align-items-center">
												<div class="bootstrap-switch-off bootstrap-switch bootstrap-switch-wrapper bootstrap-switch-animate" style="width: 112px;"><div class="bootstrap-switch-container" style="width: 165px; margin-left: -55px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 55px;">ON</span><span class="bootstrap-switch-label" style="width: 55px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 55px;">OFF</span><input type="checkbox" name="inline_switch_group" class="form-input-switch" required=""></div></div>
												Duis eget nibh purus
											</label>
										</div>

										<div class="form-check form-check-inline form-check-switch form-check-switch-left">
											<label class="form-check-label align-items-center">
												<div class="bootstrap-switch-off bootstrap-switch bootstrap-switch-wrapper bootstrap-switch-animate" style="width: 112px;"><div class="bootstrap-switch-container" style="width: 165px; margin-left: -55px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 55px;">ON</span><span class="bootstrap-switch-label" style="width: 55px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 55px;">OFF</span><input type="checkbox" name="inline_switch_group" class="form-input-switch"></div></div>
												Maecenas non nulla ac nunc
											</label>
										</div>
									<label id="inline_switch_group-error" class="validation-invalid-label" for="inline_switch_group">This field is required.</label></div>
								</div>
								<!-- /inline switch group -->

							</fieldset>

							<div class="d-flex justify-content-end align-items-center">
								<button type="reset" class="btn btn-light legitRipple" id="reset">Reset <i class="icon-reload-alt ml-2"></i></button>
								<button type="submit" class="btn btn-primary ml-3 legitRipple">Submit <i class="icon-paperplane ml-2"></i></button>
							</div>
						</form>
					</div>
				</div>
				<!-- /form validation -->

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