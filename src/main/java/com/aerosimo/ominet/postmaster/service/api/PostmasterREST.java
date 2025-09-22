/******************************************************************************
 * This piece of work is to enhance postmaster project functionality.         *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      PostmasterREST.java                                             *
 * Created:   21/09/2025, 00:13                                               *
 * Modified:  21/09/2025, 00:13                                               *
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

package com.aerosimo.ominet.postmaster.service.api;

import com.aerosimo.ominet.postmaster.dao.impl.SendEmailRequestDTO;
import com.aerosimo.ominet.postmaster.models.Postbox;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/postmaster")   // <--- resource directly under /api/postmaster
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostmasterREST {

    private static final Logger log;

    static {
        log = LogManager.getLogger(PostmasterREST.class.getName());
    }

    @POST
    public Response sendMail(SendEmailRequestDTO request) {
        if (request == null ||
                request.getEmailAddress() == null ||
                request.getEmailSubject() == null ||
                request.getEmailMessage() == null) {
            log.error("Bad request - {}", PostmasterREST.class.getName(), Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Status\":\"Missing required fields\"}")
                    .build());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Status\":\"Missing required fields\"}")
                    .build();
        }

        String ref = Postbox.sendEmail(
                request.getEmailAddress(),
                request.getEmailSubject(),
                request.getEmailMessage(),
                request.getEmailFiles(),
                "Aerosimo Support<support@aerosimo.com>"
        );
        log.info("Successfully send email to {}", request.getEmailAddress());
                Response.ok("{\"Status\":\"" + ref + "\"}").build();
        return Response.ok("{\"Status\":\"" + ref + "\"}").build();
    }
}