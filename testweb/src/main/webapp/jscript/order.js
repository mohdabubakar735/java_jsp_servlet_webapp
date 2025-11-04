  
function defaultVaue(){
	 document.getElementById('orderDate').value = '2025-10-15';
}

function clearForm() {
    document.getElementById('orderDate').value = '';
    document.getElementById('totalAmount').value = '';
    document.getElementById('description').value = '';
}
	
function saveOrder() {
    // Dummy save (append to table)
    const date = document.getElementById('orderDate').value;
    const amount = document.getElementById('totalAmount').value;
    const desc = document.getElementById('description').value;

    if (date && amount && desc) {
        const table = document.getElementById('orderTable').getElementsByTagName('tbody')[0];
        const newRow = table.insertRow();
        newRow.innerHTML = `
            <td>Auto</td>
            <td>${date}</td>
            <td>${amount}</td>
            <td>${desc}</td>
        `;
        clearForm();
    } else {
        alert("Please fill in all fields.");
    }
    // this.forms[0].submit();
}

function refreshTable(event) {
	    if (event) event.preventDefault();
    alert("This is just a demo refresh.");
}

function deleteOrder(id)
{
	//alert('delete:'+id);
    const xhr = new XMLHttpRequest();
    xhr.open("OPTIONS", "/testweb/order?orderId="+id, true);
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
    
function updateOrder(id)
{
	
	const xhr = new XMLHttpRequest();
    xhr.open("HEAD", "/testweb/order?orderId="+id, true);
    xhr.setRequestHeader("Content-Type", "text/html");
    console.log(xhr);
	xhr.onload = function() {
	    if (xhr.status === 200) {
		    console.log('open update page redirected...');
		    window.location.href = '/testweb/orderedit.jsp';
		    
	    } else {
	        console.error("HTTP request failed with status: " + xhr.status);
	    }
    };

    xhr.onerror = function() {
        console.error("Network error occurred.");
    };

    xhr.send();
    
}