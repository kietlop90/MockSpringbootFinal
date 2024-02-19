$(document).ready(function () {
    // Initialize editor with custom theme and modules
    var fullEditor = new Quill('#full-editor', {
        theme: 'snow'
    });
    // Khởi tạo trình soạn thảo Quill
    var quill = new Quill('#full-editor1', {
        theme: 'snow'
    });

    // Nội dung HTML bạn muốn đặt sẵn, bao gồm các icon từ Font Awesome
    var editorContent = `
    <img src="/icon/indicator/verified_user.png" alt="">    
      <b>Training</b>
        <ul>
        <li>Trainee who actively complete online learning according to MOOC links provided</li>
        <li>At the end of the day, students complete Daily Quiz for 30 minutes</li>
        <li>Trainer/Mentor supports answering questions, guiding exercises 1.5-2.0h/day</li>
        <li>Trainer conduct the workshops</li>
        <li>Trainees complete Assignments and Labs</li>
        <li>Trainees have 1 final test in 4 hours (1 hour theory + 3 hours of practice)</li>
</ul>

 <img src="/icon/indicator/verified_user.png" alt="">    
      <b>Re-test</b>
      <ul>
      <li>Only allow each student to retake the test up to 2 times</li>
      <li>Re-exam the same structure as the Final Test</li>
</ul>

     <img src="/icon/indicator/verified_user.png" alt="">    
      <b>Marking</b>
      <ul>
      <li>Mentor review students on 2 Assignments</li>
      <li>Mentor marks the 3 Quizzes and Final Exam Theory</li>
      <li>Trainer marks the Final Exam Practice</li>
      <li>
        <ul>If the trainees have to retake test, the score will be calculated:
            <li>The score >=6, the score will be 6</li>
            <li>The score <6, the score will be that score</li>
        </ul>
      </li>
</ul>

<img src="/icon/indicator/verified_user.png" alt="">    
      <b>Waiver Criteria</b>
      <ul>
      <li>Students pass the quick test</li>
      <li>Trainer Audit: rank B</li>
</ul>


<img src="/icon/indicator/verified_user.png" alt="">    
      <b>Others</b>
      <ul>
      <li>Trainers can allow students to complete homework and submit the next day</li>
</ul>
    `;


    quill.clipboard.dangerouslyPasteHTML(editorContent);

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

    const ctx1 = document.getElementById('myChart1');

    new Chart(ctx1, config);


})