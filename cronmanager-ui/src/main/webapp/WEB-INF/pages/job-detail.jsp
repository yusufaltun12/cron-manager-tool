<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html lang="en">
<head>
<meta charset="UTF-8">
<title>Example of Bootstrap 3 List Group Contextual Classes</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">
    .bs-example{
    	margin: 20px;
    }
</style>
<script type="text/javascript">
	$(document).ready(function(){ 
		
		var jobId=${jobId}
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
		        	var statusCss = "";
		        	if(item.resultStatus == 'ERROR')
	        		{
	        			statusCss = 'list-group-item-warning';
	        		}
		        	else if(item.resultStatus == 'SUCCESS')
		        	{
		        		statusCss = 'list-group-item-success';
		        	}
		        	else if(item.resultStatus == 'SYSTEM_ERROR')
		        	{
		        		statusCss = 'list-group-item-danger';
		        	}
		            trHTML += '<li class="list-group-item '+ statusCss +'"><a href="#'+resultsResponse[i].id+'" class="date-link">' +resultsResponse[i].date+ '</a></li>'; 
		        });
		        $('#results-list').append(trHTML);
		        $( ".date-link" ).click(function() {
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
<div class="row" style="height: 100px; border: 1px silver solid; margin: 20px">
</div>
<div class="col-sm-3">
	<ul class="nav nav-pills nav-stacked bs-example" id="results-list">
        
	</ul>
</div>
<div class="col-sm-9">
	<div id="description">
	</div>
</div>
</body>
</html>