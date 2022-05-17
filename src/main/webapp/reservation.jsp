<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Registration</title>
    <%@ include file="/Bootstrap/bootstrap-css.html" %>
</head>
<body>
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h3 class="text-center">Reservation Form</h3>
        <hr>

        <div class="card text-white bg-dark mx-auto" style="max-width: 600px;">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h4 class="card-title mt-3 text-center">Create reservation ^_^</h4>
                <br>
                <form id="reservation-form" method="post" action="Reservation">
                    <label for="layout">Please select the layout:</label>
                    <select name="layout" id="layout" class="form-control w-auto">
                        <option value="STANDART">Standard</option>
                        <option value="DELUXE">Deluxe</option>
                        <option value="JOINT">Joint</option>
                        <option value="SUITE">Suite</option>
                    </select>
                    <label for="occupancy">
                        Please select the occupancy:
                    </label>
                    <select name="occupancy" id="occupancy" class="form-control w-auto">
                        <option value="1">One person</option>
                        <option value="2">Two persons</option>
                        <option value="3">Three persons</option>
                        <option value="4">Four persons</option>
                        <option value="5">Five persons</option>
                        <option value="6">Six persons</option>
                    </select>

                    <div class="form-group">
                        <label for="Check-in">Check-in date:</label>
                        <input
                                type="date"
                                name="Check-in"
                                id="Check-in"
                                placeholder="Check-in"
                                required class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="Check-out">Check-out date:</label>
                        <input
                                type="date"
                                name="Check-out"
                                id="Check-out"
                                placeholder="Check-in"
                                required class="form-control col">
                    </div>
                    <div class="form-group">
                        <button type="submit" id="reservation-submit" class="btn btn-warning col"> Make Reservation</button>
                    </div>
                </form>
            </article>
        </div>
    </div>

    <div class="modal fade" id="failureModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger">Reservation Failure</h5>
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