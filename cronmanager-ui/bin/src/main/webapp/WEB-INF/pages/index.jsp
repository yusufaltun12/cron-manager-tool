<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script type="text/javascript">
 		
		$(document).ready(function(){ 
		$.urlParam = function(name){
		    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		    if (results==null){
		       return null;
		    }
		    else{
		       return results[1] || 0;
		    }
		}
		
		var jobId=$.urlParam('id');
		$.ajax({
			type : "GET",
			url : "/job/all",
			crossDomain : true,
			processData : true,
			success : function(jobs) 
			{
				var trHTML = '';
		        $.each(jobs, function (i, item) {
		            trHTML += '<tr><td>' + jobs[i].jobName + '</td><td>' + jobs[i].period + '</td><td>' + jobs[i].executableFilePath + '</td><td> </td><td><a href="/job-detail/?id='+jobs[i].id+'" >Go</a></td></tr>';
		        });
		        $('#jobs-table').append(trHTML);
			},
			error : function(ex, ajaxOptions, thrownError) 
			{
				 
			}
		});
	});
	</script>
</head>
<body>
	<div>
		<div style="width: 100%; border: 1px silver solid; height: 40px;">QUALIST Cron Job Manager Tool</div>
		<div style="width: 100%; border: 1px silver solid; height: 500px;">
			<div style="float: left; width: 20%; border: 1px silver solid; height: 100%"></div>
			<div style="float: left; width: 70%;  height: 100%">
				<table style="width: 100%" id="jobs-table">
					<thead>
						<th>Job Adı</th>
						<th>Çalışma Periyodu</th>
						<th>Çalıştırılabilir Dosya</th>
						<th>Son Çalışma Zamanı</th>
						<th>Git</th>
					</thead>
				</table>
			</div>
		</div>

	</div>
</body>
</html>