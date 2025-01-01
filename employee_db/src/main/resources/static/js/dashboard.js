// Wait for the DOM to fully load
document.addEventListener('DOMContentLoaded', function () {
    // Get the canvas elements and ensure they are available
    const taskChartCanvas = document.getElementById('taskChart')?.getContext('2d');
    const performanceChartCanvas = document.getElementById('performanceChart')?.getContext('2d');

    // Ensure the canvases are available before proceeding
    if (taskChartCanvas && performanceChartCanvas) {
        // Data for the Monthly Tasks Overview chart
        const taskData = {
            labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
            datasets: [{
                label: 'Tasks Completed',
                data: [10, 15, 20, 25],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        };

        // Data for the Performance Summary chart
        const performanceData = {
            labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
            datasets: [{
                label: 'Performance',
                data: [80, 85, 90, 95],
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 1
            }]
        };

        // Configuration for the Monthly Tasks Overview chart
        const taskConfig = {
            type: 'bar',
            data: taskData,
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 5, // Customize step size for better readability
                        }
                    }
                }
            }
        };

        // Configuration for the Performance Summary chart
        const performanceConfig = {
            type: 'line',
            data: performanceData,
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 10, // Customize step size for better readability
                        }
                    }
                }
            }
        };

        // Create the Monthly Tasks Overview chart
        new Chart(taskChartCanvas, taskConfig);

        // Create the Performance Summary chart
        new Chart(performanceChartCanvas, performanceConfig);
    } else {
        console.error('Chart canvas elements not found.');
    }
});
