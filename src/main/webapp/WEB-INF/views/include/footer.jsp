<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
 <div class="navbar navbar-expand-lg navbar-light">
				<div class="text-center d-lg-none w-100">
					<button type="button" class="navbar-toggler dropdown-toggle" data-toggle="collapse" data-target="#navbar-footer">
						<i class="icon-unfold mr-2"></i>
						Footer
					</button>
				</div>

				<div class="navbar-collapse collapse" id="navbar-footer">
					<span class="navbar-text">
						&copy; 2015 - 2018. <a href="#">Limitless Web App Kit</a> by <a href="http://themeforest.net/user/Kopyov" target="_blank">Eugene Kopyov</a>
					</span>

					<ul class="navbar-nav ml-lg-auto">
						<li class="nav-item"><a href="https://kopyov.ticksy.com/" class="navbar-nav-link" target="_blank"><i class="icon-lifebuoy mr-2"></i> Support</a></li>
						<li class="nav-item"><a href="http://demo.interface.club/limitless/docs/" class="navbar-nav-link" target="_blank"><i class="icon-file-text2 mr-2"></i> Docs</a></li>
						<li class="nav-item"><a href="https://themeforest.net/item/limitless-responsive-web-application-kit/13080328?ref=kopyov" class="navbar-nav-link font-weight-semibold"><span class="text-pink-400"><i class="icon-cart2 mr-2"></i> Purchase</span></a></li>
					</ul>
				</div>
			</div>
			
	<script>
			jQuery('.numbersOnly').keyup(function () { 
	            this.value = this.value.replace(/[^0-9\.]/g,'');
	        });
	        jQuery('.alphaonly').keyup(function () { 
	            this.value = this.value.replace(/[^a-zA-Z\s]+$/,'');
	        });
	        jQuery('.alhanumeric').keyup(function () { 
	            this.value = this.value.replace(/[^a-zA-Z0-9\-\s]+$/,'');
	        });
	        jQuery('.dob').keyup(function () { 
	            this.value = this.value.replace(/[^a-zA-Z0-9\-\s]+$/,'');
	        });
	</script>		