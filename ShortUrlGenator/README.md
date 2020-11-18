# URL Shortener Service
This project contains a simple URL Shortener service which exposes its functionality via a Web Service API.

## Assignment
The implementation of this service is not quite complete. Implement the following features:

- A user can add a website URL for which a short URL is created and stored in the database. The short URL must have a unique ID
  consisting of alphanumeric characters.
- The service exposes a Web Service API for providing appropriate CRUD operations to consumers.
- *Optional*: The Web Service API accepts and provides data in multiple formats, e.g. JSON, XML, etc.
- When opening a short URL in a Web Browser the website of the original URL shows up.
- Whenever someone opens a short URL a set of statistics is stored (number of calls, date and time, user agent, referrer, etc.).
- These statistics can be retrieved via the Web Service API.

These features are sorted by priority and therefore should be implemented in that order. It's not necessary to implement
all of these features. However, you should spend at least 3 - 4 hours on them. Please submit the assignment 3 days after
it has been send to you by pushing the code to GitHub.

Thank you for taking your time.

**Happy coding!**

## Work with the Project

### Build
This project uses [Maven](https://maven.apache.org) as its build tool. In order to build, test and package the application
run the following command:

```bash
mvn clean package
```

## Run
Once the application has been build and packaged successfully, you'll find a JAR file in the `target` folder. The file
is called `url-shortener.jar`. Use the following command to run the application:

```bash
java -jar target/url-shortener.jar
```

After a few seconds the application should have started successfully. You can access the Web Service endpoints via
[http://localhost:8080](http://localhost:8080) now.

## API Document

Allowed HTTPs requests:
- POST    : To create resource
- GET     : Get a resource or list of resources
- DELETE  : To delete resource

Description Of Usual Server Responses:
- 404 Not Found - resource was not found.
- 409 Conflict - resource was already exist, duplicate!

Description of API:

To add an URL into the database,

```bash
POST Request: http://localhost:8080/addurl
Provided Request body,
{
	"url":"https://www.youtube.com",
	"user":"khaled"
}

Get JSON Response,
{
    "url": "https://www.youtube.com",
    "id": "4028329675d4084a0175d40850320001",
    "message": "Successfully genereated short url. You can use this short url by this id now by http://localhost:8080/id"
}
```
Get URL redirected by generated URL Id,

```bash
GET Request: http://localhost:8080/4028329675d4084a0175d40850320001
Youtube page supposed to be opened in the browser. 
```

Get all URLs of an user,

```bash
GET Request: http://localhost:8080/geturls/khaled
Get JSON Response,
{
    "message": "List of URL and corresponding Id for your given user. You can use them by these ids now by following way, http://localhost:8080/id",
    "urlAndIds": [
        {
            "url": "https://www.youtube.com",
            "id": "4028329675d4084a0175d40c20d10003"
        },
        {
            "url": "https://www.facebook.com",
            "id": "4028329675d4084a0175d40c516b0004"
        }
    ]
}
```

Delete URL of an user,

```bash
DELETE Request: http://localhost:8080/deleteurl
Provided Request body,
{
	"url":"https://www.youtube.com",
	"user":"khaled"
}

Get JSON Response,
{
    "user": "khaled",
    "urlId": "4028329675d4084a0175d40850320001",
    "message": "Successfully deleted your given URL."
}
```

Get Statistics of an URL Id,

```bash
GET Request: http://localhost:8080/geturlstatistic/4028329675d4084a0175d40850320001
Get JSON Response,
{
    "urlId": "4028329675d4084a0175d40c516b0004",
    "numberOfCalls": 2,
    "accessAt": "2020-11-17T02:42:27.261+00:00",
    "referrer": null,
    "userAgent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
}
```

Possible to select JSON or XML response,

```bash
Set request header,
Accept: application/json will provide json format
Example failure response in JSON,
{
    "status": "404",
    "error": "Your given User or URL Id is/are not found in our system."
}

Accept: application/xml will provide xml format
Example failure response in XML,
<URLErrorResponse>
    <status>404</status>
    <error>Your given User or URL Id is/are not found in our system.</error>
</URLErrorResponse>
```








