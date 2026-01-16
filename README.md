TODO LIST
-------
there is cursor-based pagination also
BookResponseDTO etc  are EntityModel

add unified exception handling for invalid enums

PAGINATION HATEOAS
For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.

 

global error handling status codes and Service-level validation (fallback) with new throw exception message VALIDATION

API response general

ENVIRONMENT VARIABLES AND FOLDER .env.local  

RESPONSE ENTITY

why to use records instead of class Records automatically generate everything you need
Java automatically generates:
    constructor
    getters    
    equals()    
    hashCode()    
    toString()


price Double - BigDecimal


CACHEABLE FREQUENT QUERIES - HOW

CORS FOR REACT
```
GET http://localhost:8080/api/books?size=2

This is the response we get:
{
  "content": [                    → the actual books returned
    { "id": 1, "title": "Book
    { "id": 2, "title": "Boo
  "pageable": {                   → the request from frontend
    "pageNumber": 0,              → which page you’re on
    "pageSize": 2,                → how many items per page
    "so
      "empty": 
      "sorted":
      "unsorted":

    "offse
    "paged":
    "unpaged":
  },                              → the response we take (Top‑level pagination metadata = This describes the result of the query)
  "last": false,                  first / last → booleans
  "totalElements": 10,            → total number of books in database
  "totalPages": 5,                → how many pages exist 
  "size": 2,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}    

In the response as we can see, there are duplicates that we can be removed making
the response cleaner for frontend. We upgrade the code creating a generic DTO.
The solution is to  make custom JSON pagination wrapper PageResponseDTO<T> HATEOAS-like 
The next step is to extend response with navigation links self, prev, next
For getBooks the api is of Level 2.5 (resources, HTTP verbs, pagination, sorting, filtering, navigation links (self,prev,next), metadata for UI)

HATEOAS = Hypermedia As The Engine Of Application State
It means your API responses include machine‑navigable links that describe what the client can do next.
Spring HATEOAS uses a special format called HAL.
A HATEOAS paginated response looks like this: 
{
  "_embedded": {
    "books": [ ... ]
  },
  "_links": {
    "self": { "href": "/api/books?page=0&size=10" },
    "next": { "href": "/api/books?page=1&size=10" },
    "prev": null
  },
  "page": {
    "size": 10,
    "totalElements": 42,
    "totalPages": 5,
    "number": 0
  }
}
Notice:
_embedded instead of content
_links instead of your simple links
page object instead of your flat metadata
This is the official REST Level 3 hypermedia format. (REST maturities levels 0-3)





```