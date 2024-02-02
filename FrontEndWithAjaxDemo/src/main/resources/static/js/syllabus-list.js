$(document).ready(function () {
    // Initialize editor with custom theme and modules
    var fullEditor = new Quill('#full-editor', {
        theme: 'snow'
    });

    // chart
    let arrayNumber = [10, 30, 40, 60, 40];
    const ctx = document.getElementById('myChart');

    const data = {
        datasets: [
            {
                label: 'Dataset 1',
                data: arrayNumber,
                backgroundColor: ['Red', 'Orange', 'Yellow', 'Green', 'Blue'],
            }
        ]
    };

    const config = {
        type: 'pie',
        data: data,
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false,
                },
                title: {
                    display: false,
                }
            }
        },
    };

    new Chart(ctx, config);
})