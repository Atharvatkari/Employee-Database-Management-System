// Handle file input change to preview the selected photo
document.getElementById('photoUpload').addEventListener('change', function (event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            // Update the profile photo image source to show the selected image
            document.getElementById('profilePhoto').src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
});

// Handle form submission with validation and proper error handling
document.getElementById('profileForm').addEventListener('submit', function (event) {
    event.preventDefault();
    
    const name = document.getElementById('name').value.trim();
    const address = document.getElementById('address').value.trim();
    const phone = document.getElementById('phone').value.trim();
    
    // Basic validation to ensure required fields are not empty
    if (!name || !address || !phone) {
        alert('Please fill out all required fields.');
        return;
    }

    const formData = new FormData();
    formData.append('name', name);
    formData.append('address', address);
    formData.append('phone', phone);
    
    const photoFile = document.getElementById('photoUpload').files[0];
    if (photoFile) {
        formData.append('photo', photoFile);
    }

    // Sending the form data to the server (use your actual API endpoint)
    fetch('/update-profile', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Profile updated successfully');
            window.location.href = "profile-success.html"; // Redirect to a success page
        } else {
            alert('Failed to update profile. Please try again.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('There was an error updating the profile. Please try again.');
    });
});
