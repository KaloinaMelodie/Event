
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Error
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">

          <div class="error-page">
            <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"></i> Oops! </h3>
              <p>
                  <%= request.getAttribute("error") %>
              </p>
            </div><!-- /.error-content -->
          </div><!-- /.error-page -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->