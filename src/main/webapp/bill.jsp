<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <%@ include file="/Bootstrap/bootstrap-css.html" %>
</head>
<body>
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h3 class="text-center">Bill Form</h3>
        <hr>

        <div class="card text-white bg-dark mx-auto" style="max-width: 600px;">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h3 class=" text-center">Your Bill:</h3>
                <br>
                <form id="bill-form" >
                    <h3 style="color: yellow">Your apartment number:</h3><h4>${Apartment_number}</h4>
                    <h3 style="color: yellow">Payment:</h3><h4>${bill}</h4>
                </form>
            </article>
        </div>
    </div>

    <div class="modal fade" id="failureModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger">Login Failure</h5>
                </div>
                <div class="modal-body">
                    <p id="failureModalText"></p>
                </div>
                <div class="modal-footer" id="failureModalFooter">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="/Bootstrap/bootstrap-js.html" %>
</html>
