![MIT License](/src/main/webapp/assets/img/MIT.png "MIT")

<details>
  <summary>License</summary>

**MIT License © 2025 Aerosimo**

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER  
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  
SOFTWARE.

The characters, names, events, articles, templates, or information provided by  
Aerosimo Ltd are fictional and for reference only. While we strive to keep the  
information up to date and correct, we make no representations or warranties of  
any kind, express or implied, about the completeness, accuracy, reliability,  
suitability, or availability with respect to the information, articles, templates,  
or related graphics contained in this document or any part of the project.  
Any reliance you place on such information is therefore strictly at your own risk.
</details>

---

![Project Cover](/src/main/webapp/assets/img/cover.jpg "Postmaster")
# Postmaster
> *Postmaster is a versatile and robust email service designed to simplify and enhance your email communication.*

---

## Project Structure

This `README.md` gives an overview of the project structure and instructions on how to build and deploy the application.

## Features

**SOAP & REST Web Service**: Exposes a `sendMail(String emailAddress,String emailSubject,String emailMessage,String emailFiles)` method to post mail with or without attachment(s).

## Getting Started

![Project Codes & Tasks](/src/main/webapp/assets/img/code.jpg "Project Codes and Task")

---

### Prerequisites

>- **Apache TomEE 10**: is the application server used during development, but any Jakarta EE 10-compliant server should work.
>- **Java 23**: Ensure you have Java 23 installed as TomEE 10 targets Jakarta EE 10.
>- **Maven**: The project uses Maven for dependency management with any IDE of choice.

### Dependencies

The required dependencies are defined in `pom.xml`. Below are the key dependencies:

- **Jakarta EE 10 API**: Provides JAX-WS support.
- **JAX-WS**: For SOAP web service implementation.

### Building and Running the Application

## Quickstart

1. **Clone the repository**:

    ```bash
    git clone https://github.com/aerosimo/postmaster.git
    cd spectre
    ```

2. **Build the project using Maven**:

    ```bash
    mvn clean install
    ```

3. **Deploy the WAR file**:

   After building the project, deploy the generated `WAR` file from the `target/` directory into the `webapps/` folder of your preferred Jakarta EE 10-compatible server.

4. **Start your preferred Jakarta EE 10-compatible server**:

   Start server and access the application:

    - SOAP Service: WSDL at `http://localhost:8081/postmaster/ws/sendemail?wsdl`
    - REST Service: http://localhost:8081/postmaster/api/sendemail
    - Web Interface: `http://localhost:8081/postmaster/index.jsp`

## Detailed Explanation of Components

### 1. **SOAP Web Service** (JAX-WS)

The SOAP web service is implemented in `com.aerosimo.ominet.postmaster.api.soap.PostmasterSOAP.java`.

Example sendMail SOAP Request:
```xml
<?xml version='1.0' encoding='UTF-8'?>
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:mail="https://aerosimo.com/api/ws">
    <soap:Header/>
    <soap:Body>
        <mail:sendEmail>
            <emailAddress>someone@somebody.com</emailAddress>
            <emailSubject>Test Email from PostMan</emailSubject>
            <emailMessage>Hello I am a test email message from Postman Application</emailMessage>
            <emailFiles />
        </mail:sendEmail>
    </soap:Body>
</soap:Envelope>
```
Example sendMail SOAP Response:
```xml
<?xml version='1.0' encoding='UTF-8'?>
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
    <soap:Body>
        <mail:sendEmailResponse xmlns:mail="https://aerosimo.com/api/ws">
            <Status>Message sent successfully</Status>
        </mail:sendEmailResponse>
    </soap:Body>
</soap:Envelope>
```

### 2. **REST Web Service** (JAX-RS)

The REST web service is implemented in `com.aerosimo.ominet.postmaster.api.rest.PostmasterREST.java`.

Example sendMail REST Request:
```json
    {
      "emailAddress": "someone@somebody.com",
      "emailSubject": "Test Email from PostMan",
      "emailMessage": "Hello I am a test email message from Postman Application",
      "emailFiles": ""
    }

```
Example sendMail REST Response:
```json
    {
      "Status": "Message sent successfully"
    }
```
## Contributing

We welcome feedback and contributions. Please open an issue or submit a pull request.

---

![Aerosimo Logo.!](/src/main/webapp/assets/img/logo.png "Aerosimo")