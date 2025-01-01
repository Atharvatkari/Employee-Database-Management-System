const apiUrl = '/api/employees';
const employeeTable = document.getElementById('employeeTable');
const employeeForm = document.getElementById('employeeForm');

async function fetchEmployees() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const employees = await response.json();
        employeeTable.innerHTML = employees.map(employee => `
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.email}</td>
                <td>${employee.designation}</td>
                <td>${employee.phone}</td>
                <td>
                    <button onclick="deleteEmployee(${employee.id})" class="btn btn-danger btn-sm">Delete</button>
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.error('Error fetching employees:', error);
        alert('Failed to load employee data.');
    }
}

employeeForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const employee = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        designation: document.getElementById('designation').value,
        phone: document.getElementById('phone').value,
    };
    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(employee),
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        window.location.href = "success.html"; // Redirect to success page
    } catch (error) {
        console.error('Error adding employee:', error);
        alert('Failed to add employee.');
    }
});

async function deleteEmployee(id) {
    if (confirm('Are you sure you want to delete this employee?')) {
        try {
            const response = await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            fetchEmployees();
        } catch (error) {
            console.error('Error deleting employee:', error);
            alert('Failed to delete employee.');
        }
    }
}

fetchEmployees();
