			
	window.fbAsyncInit = function() {
	  FB.init({
	    appId      : '1425845537653977',
	    status     : true, // check login status
	    cookie     : true, // enable cookies to allow the server to access the session
	    xfbml      : true  // parse XFBML
	  });
	  FB.Event.subscribe('auth.authResponseChange', function(response) {
		    // Here we specify what we do with the response anytime this event occurs. 
		    if (response.status === 'connected') {
		      
		    } else if (response.status === 'not_authorized') {
		      
		    } else {
		      
		    }
	    FbButton(FB.getLoginStatus( function (response){
			 FbButton(response); 
		  }));
	  });
	  
		
	};
	// Load the SDK asynchronously
	  (function(d){
	   var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	   if (d.getElementById(id)) {return;}
	   js = d.createElement('script'); js.id = id; js.async = true;
	   js.src = "//connect.facebook.net/en_US/all.js";
	   ref.parentNode.insertBefore(js, ref);
	  }(document));

	FbLogin = function(){
		//var uid="";
		//var accessToken="";
		FB.getLoginStatus(function(response) {
			alert(response.status);
			if (response.status == 'connected') {
			    //uid = response.authResponse.userID;
			    //accessToken = response.authResponse.accessToken;
		  	} 
			else {
			    FB.login(function(response) {
				    if (response.authResponse) {
				        //uid = response.authResponse.userID;
			    		//accessToken = response.authResponse.accessToken;
				    } else {
				    	alert("Login Dialog Cancelled");
				    }
				});
		  }
		});
	};
		
	FbButton = function(response){
		if (response.status == 'connected') {
		    document.getElementById("btn-fb").className="btn btn-success btn-large";
	  	} 
		else {
			document.getElementById("btn-fb").className="btn btn-primary btn-large";
		}
		
	};
		
	
	
	
