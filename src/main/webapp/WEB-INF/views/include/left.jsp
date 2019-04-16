<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
 	<div class="sidebar sidebar-light sidebar-main sidebar-expand-md">

			<!-- Sidebar mobile toggler -->
			<div class="sidebar-mobile-toggler text-center">
				<a href="#" class="sidebar-mobile-main-toggle">
					<i class="icon-arrow-left8"></i>
				</a>
				<span class="font-weight-semibold">Navigation</span>
				<a href="#" class="sidebar-mobile-expand">
					<i class="icon-screen-full"></i>
					<i class="icon-screen-normal"></i>
				</a>
			</div>
			<!-- /sidebar mobile toggler -->


			<!-- Sidebar content -->
			<div class="sidebar-content">

				<!-- User menu -->
				<div class="sidebar-user-material">
					<div class="sidebar-user-material-body">
						<div class="card-body text-center">
							<a href="#">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/placeholders/placeholder.jpg" class="img-fluid rounded-circle shadow-1 mb-3" width="80" height="80" alt="">
							</a>
							<h6 class="mb-0 text-white text-shadow-dark">Victoria Baker</h6>
							<span class="font-size-sm text-white text-shadow-dark">Santa Ana, CA</span>
						</div>
					</div>
				</div>
				<!-- /user menu -->


				<!-- Main navigation -->
				<div class="card card-sidebar-mobile">
					<ul class="nav nav-sidebar" data-nav-type="accordion">

						<!-- Main -->
						<!-- <li class="nav-item-header"><div class="text-uppercase font-size-xs line-height-xs">Main</div> <i class="icon-menu" title="Main"></i></li> -->
						<li class="nav-item">
							<a href="index.html" class="nav-link active">
								<i class="icon-home4"></i>
								<span>
									Dashboard
								</span>
							</a>
						</li>
						<li class="nav-item nav-item-submenu">
							<a href="#" class="nav-link"><i class="icon-list-unordered"></i><span>Master</span></a>

							<ul class="nav nav-group-sub" data-submenu-title="Layouts">
								 
								<li class="nav-item"><a href="${pageContext.request.contextPath}/showCompanyList" class="nav-link">Company List</a></li> 
								<li class="nav-item"><a href="${pageContext.request.contextPath}/showLocationList" class="nav-link">Location List</a></li>
								<li class="nav-item"><a href="${pageContext.request.contextPath}/showEmpTypeList" class="nav-link">Employee Type List</a></li> 
								 
							</ul>
						</li>
						<li class="nav-item nav-item-submenu">
							<a href="#" class="nav-link"><i class="icon-pencil3"></i> <span>Layouts</span></a>

							<ul class="nav nav-group-sub" data-submenu-title="Layouts">
								<li class="nav-item"><a href="index.html" class="nav-link active">Default layout</a></li>
								<li class="nav-item"><a href="layout_2/LTR/material/full/index.html" class="nav-link">Layout 2</a></li>
								<li class="nav-item"><a href="layout_3/LTR/material/full/index.html" class="nav-link">Layout 3</a></li>
								<li class="nav-item"><a href="layout_4/LTR/material/full/index.html" class="nav-link">Layout 4</a></li>
								<li class="nav-item"><a href="layout_5/LTR/material/full/index.html" class="nav-link">Layout 5</a></li>
								<li class="nav-item"><a href="layout_6/LTR/material/full/index.html" class="nav-link disabled">Layout 6 <span class="badge bg-transparent align-self-center ml-auto">Coming soon</span></a></li>
							</ul>
						</li>
						<li class="nav-item">
							<a href="changelog.html" class="nav-link">
								<i class="icon-list-unordered"></i>
								<span>Changelog</span>
								<span class="badge bg-blue-400 align-self-center ml-auto">2.2</span>
							</a>
						</li>
						<li class="nav-item"><a href="../../../RTL/material/full/index.html" class="nav-link"><i class="icon-width"></i> <span>RTL version</span></a></li>
						<!-- /main -->
 

					</ul>
				</div>
				<!-- /main navigation -->

			</div>
			<!-- /sidebar content -->
			
		</div>