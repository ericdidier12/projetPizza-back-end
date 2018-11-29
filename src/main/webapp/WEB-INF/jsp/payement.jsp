<%@ taglib prefix="button" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/importTags.jsp"%>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap2-css">

<body>
<div class="container">
    <div class="row">
        <br>
        <div class="col-md-12">
            <div class="col-md-4 col-sm-6 col-xs-12 col-md-push-6 col-sm-push-6">
                <!--REVIEW ORDER-->
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h4><spring:message code="paiement"/></h4>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-12">
                            <div id="paypal-button-container" class="container"></div>
                    </div>
                    </div>
                </div>
                <!--REVIEW ORDER END-->
            </div>
            <div class="col-md-8 col-sm-6 col-xs-12 col-md-pull-6 col-sm-pull-6">
                <!--SHIPPING METHOD-->
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h4><spring:message code="summaryOrder"/></h4>
                        <table class="table borderless">
                            <tbody>
                            <tr>
                                <td class="col-md-6">
                                    <div class="media-body">
                                        <h5 class="media-heading"><spring:message code="nameCustomer"/> :</h5>
                                    </div>
                                </td>
                                <td class="text-left"><div>${nameCustomer}</div></td>
                            </tr>
                            <tr>
                                <td class="col-md-6">
                                    <div class="media-body">
                                        <h5 class="media-heading"><spring:message code="orderNumber"/> :</h5>
                                    </div>
                                </td>
                                <td class="text-left"><div>${IdOrder}</div></td>
                            </tr>
                            <tr>
                                <td class="col-md-6">
                                    <div class="media-body">
                                        <h5 class="media-heading"><spring:message code="messagePromo"/></h5>
                                    </div>
                                </td>
                                <td class="text-left"><div>${promoOrder} %</div></td>
                            </tr>
                            <tr>
                                <td class="col-md-6">
                                    <div class="media-body">
                                        <h5 class="media-heading"><spring:message code="TotalPrice"/></h5>
                                    </div>
                                </td>
                                <td class="text-left"><div>${total} €</div></td>
                            </tr>
                            <tr>
                                <td class="col-md-6">
                                    <div class="media-body">
                                        <h5 class="media-heading"><spring:message code="address"/>:</h5>
                                    </div>
                                </td>
                                <td class="text-left"><div>${addressCustomer}</div></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--SHIPPING METHOD END-->
            </div>
        </div>
    </div>
</div>

</body>

<script src="https://www.paypalobjects.com/api/checkout.js"></script>
<script>
    // Render the PayPal button
    paypal.Button.render({
// Set your environment
        env: 'sandbox', // sandbox | production

// Specify the style of the button
        style: {
            layout: 'vertical',  // horizontal | vertical
            size:   'medium',    // medium | large | responsive
            shape:  'rect',      // pill | rect
            color:  'gold'       // gold | blue | silver | white | black
        },

// Specify allowed and disallowed funding sources
//
// Options:
// - paypal.FUNDING.CARD
// - paypal.FUNDING.CREDIT
// - paypal.FUNDING.ELV
        funding: {
            allowed: [
                paypal.FUNDING.CARD,
                paypal.FUNDING.CREDIT
            ],
            disallowed: []
        },

// PayPal Client IDs - replace with your own
// Create a PayPal app: https://developer.paypal.com/developer/applications/create
        client: {
            sandbox: 'AaQx0-E6l-2nlB9kQC12vB37A0nBDCBOtf_KgaATfVHk4PqRS6LIMS9s5AFCs-gKspvpFSqu52gfZ75i',
            production: '<insert production client id>'
        },

        payment: function (data, actions) {
            return actions.payment.create({
                payment: {
                    transactions: [
                        {
                            amount: {
                                total: "${total}",
                                currency: 'EUR'
                            }
                        }
                    ]
                }
            });
        },

        onAuthorize: function (data, actions) {
            return actions.payment.execute()
                .then(function () {

                    window.alert('Payment Complete!');
                    window.location.replace("http://localhost:8080/payement/setorderpaid")

                });
        }
    }, '#paypal-button-container');
</script>

