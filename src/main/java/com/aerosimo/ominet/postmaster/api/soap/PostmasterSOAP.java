/******************************************************************************
 * This piece of work is to enhance postmaster project functionality.         *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      PostmasterSOAP.java                                             *
 * Created:   20/09/2025, 23:48                                               *
 * Modified:  20/09/2025, 23:48                                               *
 *                                                                            *
 * Copyright (c)  2025.  Aerosimo Ltd                                         *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,            *
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES            *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND                   *
 * NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT                 *
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,               *
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING               *
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE                 *
 * OR OTHER DEALINGS IN THE SOFTWARE.                                         *
 *                                                                            *
 ******************************************************************************/

package com.aerosimo.ominet.postmaster.api.soap;

import com.aerosimo.ominet.postmaster.dao.models.Postbox;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebService(
        name = "PostmasterService",
        serviceName = "PostmasterService",
        portName = "PostmasterPort",
        targetNamespace = "https://aerosimo.com/ws")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class PostmasterSOAP {

    private static final Logger log;

    static {
        log = LogManager.getLogger(PostmasterSOAP.class.getName());
    }
    static String Response;

    @WebMethod(operationName = "sendEmail")
    @WebResult(name = "Status", partName = "sendEmailResponse")
    public String sendMail(@XmlElement(required = true) @WebParam(name = "emailAddress", partName = "sendEmailRequest") String emailAddress,
                           @XmlElement(required = true) @WebParam(name = "emailSubject", partName = "sendEmailRequest") String emailSubject,
                           @XmlElement(required = true) @WebParam(name = "emailMessage", partName = "sendEmailRequest") String emailMessage,
                           @WebParam(name = "emailFiles", partName = "sendEmailRequest") String emailFiles) {
        if (emailAddress.isEmpty()) {
            Response = "Schema Validation failed because emailAddress is a required field or not valid";
            log.info("Response when email is empty: {}", Response);
        } else if (emailSubject.isEmpty()) {
            Response = "Schema Validation failed because emailSubject is a required field";
            log.info("Response when subject is empty: {}", Response);
        } else if (emailMessage.isEmpty()) {
            Response = "Schema Validation failed because emailMessage is a required field";
            log.info("Response when message is empty: {}", Response);
        } else {
            Response = Postbox.sendEmail(emailAddress, emailSubject, emailMessage, emailFiles, "Aerosimo Support<support@aerosimo.com>");
            log.info("Response from Postbox: {}", Response);
        }
        return Response;
    }
}