<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.3/jspdf.umd.min.js"></script>
<button id="generate-pdf-button">Generate PDF</button>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function() {
    $('#generate-pdf-button').click(function() {
      // Make an AJAX request to fetch the JSP content
      $.ajax({
        url: 'affichage.jsp',
        success: function(data) {
          // Generate the PDF
          var pdf = new jsPDF();
          pdf.fromHTML(data, 15, 15, {
            width: 170
          });
          pdf.save('generated.pdf');
        }
      });
    });
  });
</script>
