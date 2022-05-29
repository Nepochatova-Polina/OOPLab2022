<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Confirmation</title>
    <%@ include file="/Bootstrap/bootstrap-css.html" %>
</head>
<body>
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h3 class="text-center">Reservation</h3>
        <hr>

        <div class="card text-white bg-dark mx-auto" style="max-width: 600px;">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h4 class="card-title mt-3 text-center">Create reservation ^_^</h4>
                <br>
                <form id="reservation-form" method="get" action="AdminServlet">
                    <h4 style="color: orange">Apartment number</h4><h5>${apartment_number}</h5>
                    <h4 style="color: orange">layout:</h4><h5>${layout}</h5>
                    <h4 style="color: orange">occupancy:</h4><h5>${occupancy}</h5>
                    <h4 style="color: orange">Check in:</h4><h5>${check_in}</h5>
                    <h4 style="color: orange">Check out:</h4><h5>${check_out}</h5>
                    <h4 style="color: orange">Bill:</h4><h5>${bill}</h5>
                    <div class="form-group">
                        <button type="submit" id="reservation-submit" class="btn btn-warning col"> Confirm Reservation</button>
                        <p class="text-center">I want to go back <a href="Logout" style="color: yellow">Log out</a></p>
                    </div>
                </form>
            </article>
        </div>
    </div>

    <div class="modal fade" id="failureModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger">Confirmation Failure</h5>
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
<%@ include file="Bootstrap/bootstrap-js.html" %>
</html>