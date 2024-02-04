# Introduction

# SQL Quries

###### Table Setup (DDL)

```sql
CREATE TABLE Members (
  memid integer NOT NULL, 
  surname character varying(200) NOT NULL, 
  firsname character varying(200) NOT NULL, 
  address character varying(300) NOT NULL, 
  zipcode integer NOT NULL, 
  telephone character varying(20) NOT NULL, 
  recommendedby integer NOT NULL, 
  joindate timestamp NOT NULL, 
  CONSTRAINT member_pk PRIMARY KEY (memid) CONSTRAINT members_fk FOREIGN KEY (recommendedby) REFERENCES Members(memid) ON DELETE 
  SET 
    NULL
);
CREATE TABLE Bookings (
  facid integer NOT NULL, 
  memid integer NOT NULL, 
  starttime timestamp NOT NULL, 
  slots integer NOT NULL, 
  CONSTRAINT bookings_pk PRIMARY KEY (facid), 
  CONSTRAINT bookings_member_fk FOREIGN KEY (memid) REFERENCES Members(memid), 
  CONSTRAINT bookings_facilities_fk FOREIGN KEY (facid) REFERENCES Facilities(facid)
  );
CREATE TABLE Facilities (
  facid integer NOT NULL, 
  name character varying(20) NOT NULL, 
  membercost numeric NOT NULL, 
  guestcost numeric NOT NULL, 
  initialoutlay numeric NOT NULL, 
  monthlymaintenance numeric NOT NULL, 
  CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

```

###### Question 1: Show all members

```sql
SELECT *
FROM cd.members
```

###### Questions 2: Lorem ipsum...

```sql
SELECT blah blah 
```

EOF