<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<!-- Meta -->
<meta charset="utf-8">

<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PayKick. One bet - one hit!</title>

<!-- Favicon -->
<link rel="shortcut icon"
	href="static/img/favicon.ico">

<!-- Fonts -->
<link
	href='http://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700'
	rel='stylesheet' type='text/css'>

<!-- Styles -->
<link
	href="static/css/bootstrap.min.css"
	rel="stylesheet">
<link href="static/css/style.css"
	rel="stylesheet">
	
<!-- LayerSlider stylesheet -->
<link rel="stylesheet"
	href="static/css/layerslider.css" type="text/css">
</head>

<body>
	
	<div class="main-wrapper">
		<div id="home">
		
			<!-- SLider -->
			<div id="bg-slider-home">
				<div id="slider-wrapper">
					<div id="full-slider-wrapper">
						<div id="layerslider"
							style="width: 100%; height: 780px; max-width: 100%;">
							<div class="ls-slide" data-ls="slidedelay:8000;transition2d:4;">
								<img src="static/img/slider/slide-b-bg.jpg" class="ls-bg"
									alt="Slide background" /> <img class="ls-l"
									style="top: 180px; left: 790px; white-space: nowrap;"
									data-ls="offsetxin:50;durationin:2000;delayin:800;offsetxout:50;durationout:1000;parallaxlevel:1;"
									src="static/img/slider/gerrard.png" alt=""> <img
									class="ls-l"
									style="top: 120px; left: 500px; white-space: nowrap;"
									data-ls="offsetxin:100;durationin:2000;delayin:1200;offsetxout:100;durationout:1000;parallaxlevel:3;"
									src="static/img/slider/beckham.png" alt="">
								<h6 class="ls-l"
									style="top: 350px; left: 236px; height: 40px; white-space: nowrap; color: #FFF; font-size: .9em; font-weight: bold;"
									data-ls="
							durationin:2000;
							delayin:2000;
							rotatein:20;
							rotatexin:30;
							scalexin:1.5;
							scaleyin:1.5;
							transformoriginin:
							left 50% 0;
							durationout:750;
							rotateout:20;
							rotatexout:-30;
							scalexout:0;
							scaleyout:0;
							transformoriginout:left 50% 0;">
									Choose match - Make bet - Earn money</h6>
								<h2 class="ls-l"
									style="top: 370px; left: 110px; text-transform: uppercase; font-size: 3em; color: #FFDD00; margin-bottom: 20px; font-weight: bold; white-space: nowrap;"
									data-ls="
							offsetxin:0;
							durationin:2000;
							delayin:2300;
							rotateyin:90;
							skewxin:60;
							transformoriginin:25% 50% 0;
							offsetxout:100;
							durationout:750;
							skewxout:60;">
									Extremely Simple Rules</h2>
								<p class="ls-l"
									style="top: 435.68px; left: 43.359px; font-size: 13.8141px; padding: 0px 29.6016px; color: #FFF; line-height: 24.668px; width: 600px; height: auto; margin-left: 0px; margin-top: 0px; opacity: 1; visibility: visible; text-align: center;"
									data-ls="
							durationin:2000;
							delayin:2800;
							rotatein:20;
							rotatexin:30;
							scalexin:1.5;
							scaleyin:1.5;
							transformoriginin:left 50% 0;
							durationout:750;
							rotateout:20;
							rotatexout:-30;
							scalexout:0;
							scaleyout:0;
							transformoriginout:left 50% 0;">
									The high-quality statistics system will help you to make<br>
									right choice and to earn your first win!
								</p>
								<h6 class="ls-l"
									style="top: 490.148px; left: 274.359px; color: #FFF; font-size: .9em; font-weight: bold;"
									data-ls="
							offsetxin:0;
							durationin:2000;
							delayin:3200;
							rotateyin:90;
							skewxin:60;
							transformoriginin:25% 50% 0;
							offsetxout:100;
							durationout:750;
							skewxout:60;">
									Bets from <span
										style="font-family: 'Open Sans'; font-size: 2em; letter-spacing: 0px; color: #FF0000;">$1</span>
									per bet
								</h6>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!--  Top Menu -->
			<div class="container bs-main">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-lg-10 col-lg-offset-1">
						<!-- Top Header and Navigation -->
						<div class="top-header hidden-xs">
							<div class="top-navigation">
								<ul class="top-nav list-unstyled list-inline">
									<li><a href="">Games</a></li>
									<li><a href="">Top Users</a></li>
									<li><a href=""><img
											src="static/img/logo.png" alt="PayKick Logo" /></a></li>
									<li><a href="">Contacts</a></li>
									<li><a href="" class="red">Log In</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>

	<!-- Scripts -->
	<script
		src="static/js/jquery-1.9.1.min.js"></script>
	<script
		src="static/js/layerslider/greensock.js"
		type="text/javascript"></script>
	<script
		src="static/js/layerslider/layerslider.transitions.js"
		type="text/javascript"></script>
	<script
		src="static/js/layerslider/layerslider.kreaturamedia.jquery.js"
		type="text/javascript"></script>
	
	<script>
		jQuery("#layerslider").layerSlider({
			responsive : false,
			responsiveUnder : 1280,
			layersContainer : 1280,
		});
	</script>

</body>
</html>