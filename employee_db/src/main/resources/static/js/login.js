document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Retrieve user input
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
        // Send a POST request to the server for authentication
        const response = await fetch('/api/authenticate', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password }),
        });

        if (!response.ok) {
            throw new Error('Authentication failed');
        }

        // Redirect to the dashboard upon successful authentication
        window.location.href = 'dashboard.html';
    } catch (error) {
        // Display an error message to the user
        alert('Invalid credentials! Please try again.');
    }
});
