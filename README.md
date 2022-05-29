# SpringbootRestApi
Creating a RestAPI for the list of countries using SpringBoot

## GET REQUESTS:

### RETRIVE ALL COUNTRIES

-- http://localhost:8080/countries/all

### RETRIVE BY PARTIAL NAME
Search by country name. It can be the native name or partial name

-- http://localhost:8080/countries/name/{name}

**Examples:** 

http://localhost:8080/countries/name/ind

http://localhost:8080/countries/name/united

### RETRIVE BY FULL NAME
Search by country full name

-- http://localhost:8080/countries/name/{name}?fullText=true

**Example:**

http://localhost:8080/countries/name/india?fullText=true

## POST REQUESTS:

### Add a country

-- http://localhost:8080/countries/

**Example:**
Add the json to the body of request in the following format. "id" parameter is optional (program assign a random "id" if not provided). "countryName" and "population" is mandatory fields.  
1. {
"id": 1169051518,
"countryName": "India",
"population": 653215462
}
2. {
   "countryName": "India",
   "population": 653215462
   }

Errors are handled on following: 

1. If "**id**" or "**countryName**" is already existed
2. If "**countryName**" or "**population**" is not provided
3. If id < 0

## DELETE REQUESTS:

Delete a country

-- http://localhost:8080/countries/{countryName}

**Example:**

http://localhost:8080/countries/brazil

Errors are handled if the "**countryName**" is not found.




