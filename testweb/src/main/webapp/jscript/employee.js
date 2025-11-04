 let employeeId = 2;

function clearEmployeeForm() {
  document.getElementById('empFullName').value = '';
  document.getElementById('empBalance').value = '';
  document.querySelectorAll('input[name="employeeType"]').forEach(el => el.checked = false);
  document.querySelectorAll('input[name="sex"]').forEach(el => el.checked = false);
  document.getElementById('empCity').value = '';
  document.getElementById('empJoiningDate').value = '';
}

function refreshEmployeeTable() {
  alert("This is just a demo refresh.");
}

function deleteEmp(id)
{
	//alert('delete:'+id);
    const xhr = new XMLHttpRequest();
    xhr.open("OPTIONS", "/testweb/employee?empId="+id, true);
    xhr.setRequestHeader("Content-Type", "text/html");
    
	xhr.onload = function() {
	    if (xhr.status === 200) {
			console.log("redord delete...");
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
    
function updateEmp(id)
{
	
	const xhr = new XMLHttpRequest();
    xhr.open("HEAD", "/testweb/employee?empId="+id, true);
    xhr.setRequestHeader("Content-Type", "text/html");
    console.log(xhr);
	xhr.onload = function() {
	    if (xhr.status === 200) {
		    console.log('open update page redirected...');
		    window.location.href = '/testweb/employeeedit.jsp';
		    
	    } else {
	        console.error("HTTP request failed with status: " + xhr.status);
	    }
    };

    xhr.onerror = function() {
        console.error("Network error occurred.");
    };

    xhr.send();
    
}