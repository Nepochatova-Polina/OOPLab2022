<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Registration</title>
    <%@ include file="/Bootstrap/bootstrap-css.html" %>
<%--    <script src="registration/scripts/registration.js"></script>--%>
</head>
<body>
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h3 class="text-center">Registration Form</h3>
        <hr>

        <div class="card text-white bg-dark mx-auto" style="max-width: 600px;" >
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h4 class="card-title mt-3 text-center">Create new account ^_^</h4>
                <br>
                <form id="registration-form" method="post" action="Registration">
                    <div class="form-group">
                        <label for="username">User Name:</label>
                        <input
                                type="text"
                                name="username"
                                id="username"
                                placeholder="User name"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="role">
                            Please select the role:
                        </label>
                        <select name="role" id="role" class="form-control w-auto">
                            <option value="client">Client</option>
                            <option value="administrator">Administrator</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input
                                type="password"
                                name="password"
                                id="password"
                                placeholder="Password"
                                required
                                class="form-control col">
                    </div>

                    <div class="form-group">
                        <button type="submit" id="registration-submit" class="btn btn-warning col"> Register </button>
                    </div>

                    <p class="text-center">Already registered? <a href="login.jsp" style="color: yellow">Click here!</a> </p>
                </form>
            </article>
        </div>
    </div>

    <div class="modal fade" id="failureModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger">Registration Failure</h5>
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