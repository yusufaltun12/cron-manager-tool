<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		var results;
		$.ajax({
			type : "GET",
			url : "/job/result/"+jobId,
			crossDomain : true,
			processData : true,
			success : function(resultsResponse) 
			{
				results = resultsResponse;
				var trHTML = '';
		        $.each(resultsResponse, function (i, item) {
		             trHTML += '<a href="#'+resultsResponse[i].id+'" class="dateLink">' +resultsResponse[i].date+ '</a></br>'; 
		        });
		        $('#results-table').append(trHTML);
		        $( ".dateLink" ).click(function() {
		  		    var resultId = $(this).attr('href').split('#')[1];
		  		  
		  		  
			  		$.each(results, function (i, item) {
			            if(results[i].id == resultId)
			            {
			            	$('#description').html(results[i].description)
			            }
			        });
		  		});
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
			<div style="float: left; width: 20%; border: 1px silver solid; height: 100%" id="results-table">
			</div>
			<div style="float: left; width: 70%;  height: 100%">
				 <div id="description"></div>
			</div>
		</div>

	</div>
</body>
</html>