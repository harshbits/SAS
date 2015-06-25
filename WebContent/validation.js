/**
 * 
 */

function validate() {
	
	try {
		
		var n = document.forms["myForm"]["name"].value;
		var e = document.forms["myForm"]["userid"].value;
		var p = document.forms["myForm"]["password"].value;
		var c = document.forms["myForm"]["category"].value;
		var d = document.forms["myForm"]["department"].value;
		var cp = document.forms["myForm"]["cpass"].value;
		var atpos = e.indexOf("@");
		var dotpos = e.lastIndexOf(".");

	    if (n == null || n == "" || e == null || e == "" || p == null || p == "" || cp == null || cp == "" || c == null || c == "" || d == null || d == "") {
	    	
	        alert("All Fields are mandatory");
	        return false;
	    }
		else if(p!==cp)
		{
			
			alert("passwords should match");
		    return false;
		}
	    else if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=e.length) {
	    	
	        alert("Not a valid e-mail address");
	        return false;
	    }
		else {
			
			return true;	
		}
	} catch (err) {
		
		alert(err);
	}
	
	return true;
}

function validatePostDiscussion() {
	
	try {

		var title = document.forms["myForm"]["title"].value;
		var description = document.forms["myForm"]["description"].value;

		if (title == null || title.trim() == '') {
			
			alert("Title can not be Empty!");
			return false;
		} else if (description == null || description.trim() == '') {
			
			alert("Description can not be Empty!");
			return false;
		}
		
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
}

function validateDiscussionForumAddComments() {
	
	try {

		var comment = document.forms["myForm"]["comment"].value;

		if (comment == null || comment.trim() == '') {
			
			alert("Comment can not be Empty!");
			return false;
		}
		
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
}

function validatePostQuestion() {
	
	try {

		var title = document.forms["myForm"]["title"].value;
		var question = document.forms["myForm"]["question"].value;

		if (title == null || title.trim() == '') {
			
			alert("Title can not be Empty!");
			return false;
		} else if (question == null || question.trim() == '') {
			
			alert("Question can not be Empty!");
			return false;
		}
		
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
}

function validateQAThread() {
	
	try {
		
		var reply = document.forms["myForm"]["reply"].value;

		if (reply == null || reply.trim() == '') {
			
			alert("Reply can not be Empty!");
			return false;
		} 

		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	
	return false;
}

function validateNewsUpload() {
	
	try {

		var title = document.forms["myForm"]["title"].value;
		var category = document.forms["myForm"]["category"].value;
		var newscontent = document.forms["myForm"]["newscontent"].value;
		var newslink = document.forms["myForm"]["newslink"].value;
		
		if (title == null || title.trim() == '') {
			
			alert("Title can not be Empty!");
			return false;
		} else if (category == null || category.trim() == '') {
			
			alert("Category can not be Empty!");
			return false;
		} else if ((newscontent == null || newscontent.trim() == '') && (newslink == null || newslink.trim() == '')) {
			
			alert("Both New Link and Description can not be Empty!");
			return false;
		}
		
		
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
}


function validateBookSearch() {
	
	try {
		var searchText = document.forms["mySearch"]["searchBook"].value;
		
		if (searchText == null || searchText.trim() == '') {
			
			alert("Search Text can not be Empty!");
			return false;
		} 

		
		return true;
	} catch (err) {
		
		alert(err);
	}
	
	return false;
}

function validateBookUpload() {
	try {

		var title = document.forms["myForm"]["title"].value;
		var category = document.forms["myForm"]["category"].value;
		var author = document.forms["myForm"]["author"].value;
		var edition = document.forms["myForm"]["edition"].value;
		
		if (title == null || title.trim() == '') {
			
			alert("Title can not be Empty!");
			return false;
		} else if (category == null || category.trim() == '') {
			
			alert("Category can not be Empty!");
			return false;
		} else if (author == null || author.trim() == '')  {
			
			alert("Author can not be Empty!");
			return false;
		} else if (edition == null || edition.trim() == '')  {
		
			alert("Edition can not be Empty!");
			return false;
		}		
		
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
	
}


function validateJobSearch() {
	
	try {
		var searchText = document.forms["mySearch"]["searchjob"].value;
		
		if (searchText == null || searchText.trim() == '') {
			
			alert("Search Text can not be Empty!");
			return false;
		} 
		
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
}

function validateJobUpload() {
	try {

		var title = document.forms["myForm"]["job_title"].value;
		var skillset = document.forms["myForm"]["skill_set"].value;
		var jobfunction = document.forms["myForm"]["job_function"].value;
		var experience = document.forms["myForm"]["job_experience"].value;
		var type = document.forms["myForm"]["job_employement_type"].value;
		
		if (title == null || title.trim() == '') {
			
			alert("Job Title can not be Empty!");
			return false;
		} else if (skillset == null || skillset.trim() == '') {
			
			alert("Skill Set can not be Empty!");
			return false;
		} else if (jobfunction == null || jobfunction.trim() == '')  {
			
			alert("Job Function can not be Empty!");
			return false;
		} else if (experience == null || experience.trim() == '')  {
		
			alert("Experience can not be Empty!");
			return false;	
		} else if (type == null || type.trim() == '')  {
			
			alert("Employement Type can not be Empty!");
			return false;
		}				
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
	
}

function validateVideoSearch() {
	
	try {
		var searchText = document.forms["mySearch"]["searchVideo"].value;
		
		if (searchText == null || searchText.trim() == '') {
			
			alert("Search Text can not be Empty!");
			return false;
		} 
		
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
}

function validateVideoUpload() {
	try {

		var title = document.forms["myForm"]["title"].value;
		var category = document.forms["myForm"]["category"].value;
		var courseno = document.forms["myForm"]["courseno"].value;

		if (title == null || title.trim() == '') {
			
			alert("Title can not be Empty!");
			return false;
		} else if (category == null || category.trim() == '') {
			
			alert("Category can not be Empty!");
			return false;
		} else if (courseno == null || courseno.trim() == '')  {
			
			alert("Course no. can not be Empty!");
			return false;
		} 	
		
		return true;
		
	} catch (err) {
		
		alert(err);
	}
	
	return false;
	
	
}