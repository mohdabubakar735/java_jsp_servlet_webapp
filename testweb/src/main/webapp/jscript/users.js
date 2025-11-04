
function clearUserForm(event) {
    if (event) event.preventDefault(); 
    document.getElementById('username').value = '';
    document.getElementById('password').value = '';
    document.getElementById('email').value = '';
    document.getElementById('role').value = '';
    document.getElementById('isactive').value = 'true';
    document.getElementById('createdby').value = '';
    document.getElementById('created_date').value = '';
}



function deleteUser(userId) {
    // alert('delete: ' + userId);
    const xhr = new XMLHttpRequest();
    xhr.open("OPTIONS", "/testweb/users?userid=" + userId, true);
    xhr.setRequestHeader("Content-Type", "text/html");

    xhr.onload = function() {
        if (xhr.status === 200) {
            console.log("User deleted...");
            window.location.reload();
        } else {
            console.error("HTTP request failed with status: " + xhr.status);
        }
    };

    xhr.onerror = function() {
        console.error("Network error occurred.");
    };

    xhr.send();
}

function updateUser(userId) {
    const xhr = new XMLHttpRequest();
    xhr.open("HEAD", "/testweb/users?userid=" + userId, true);
    xhr.setRequestHeader("Content-Type", "text/html");
    console.log(xhr);

    xhr.onload = function() {
        if (xhr.status === 200) {
            console.log("Redirecting to users edit page...");
            window.location.href = "/testweb/usersedit.jsp";
        } else {
            console.error("HTTP request failed with status: " + xhr.status);
        }
    };

    xhr.onerror = function() {
        console.error("Network error occurred.");
    };

    xhr.send();
}

function validateUser(){
	
	var user= document.usersFrm.username.value;
	var pass=document.usersFrm.password.value;
	var email=document.usersFrm.email.value;
	
	if((user === "") || ( pass === "") || ( email === "") ) {
		alert('Enter mandatory values');
		return false;
	}
	return true;
}
