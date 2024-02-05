-- set cd as 1st search schema

SET search_path TO cd;

-- insert

INSERT INTO facilities
VALUES
    (9, 'Spa', 20, 30, 100000, 800);

-- select in insert

INSERT INTO facilities
VALUES
    (
        (
            SELECT
                max(facid)
            FROM
                cd.facilities
        ) + 1,
        'Spa',
        20,
        30,
        100000,
        800
    );

-- update

UPDATE
    facilities
SET
    initialoutlay = 10000
WHERE
    name = 'Tennis Court 2';

-- update with calculation

UPDATE
    facilities
SET
    membercost = (
        (
            SELECT
                membercost
            FROM
                cd.facilities
            WHERE
                name = 'Tennis Court 1'
        ) * 1.1
        ),
    guestcost = (
        (
            SELECT
                guestcost
            FROM
                cd.facilities
            WHERE
                name = 'Tennis Court 1'
        ) * 1.1
        )
WHERE
    name = 'Tennis Court 2';

-- delete all

TRUNCATE TABLE facilities CASCADE;

-- delete condition
DELETE FROM
    cd.members
WHERE
    memid = 37;

-- select with condition

SELECT
    f.facid,
    f.name,
    f.membercost,
    f.monthlymaintenance
FROM
    facilities f
WHERE
    membercost > 0
  AND membercost < (
    (
        SELECT
            monthlymaintenance
        FROM
            facilities
        WHERE
            facid = f.facid
    ) * 0.02
    )

-- Select with contains

SELECT
    *
FROM
    facilities
WHERE
    name LIKE '%Tennis%';

-- Select two without OR

SELECT
    *
FROM
    facilities
WHERE
    facid = 1
UNION
SELECT
    *
FROM
    facilities
WHERE
    facid = 5;

-- Compare timestamp

SELECT
    memid,
    surname,
    firstname,
    joindate
FROM
    members
WHERE
    DATE(joindate) >= '2012-09-01';


-- Union members surnames and facilities names

SELECT
    surname
FROM
    members
UNION
SELECT
    name
FROM
    facilities;

-- Select with join and condition

SELECT
    starttime
FROM
    members m
        JOIN bookings b ON m.memid = b.memid
WHERE
    surname = 'Farrell'
  AND firstname = 'David';

-- Join with conditions

SELECT
    b.starttime AS start,
    f.name
FROM
    facilities f
        JOIN bookings b ON f.facid = b.facid
WHERE
    DATE(b.starttime) = '2012-09-21'
  AND name LIKE 'Tennis%'
ORDER BY
    b.starttime;

-- Select with same table FK

SELECT
    m.firstname AS memfname,
    m.surname AS memsname,
    rm.firstname AS recfname,
    rm.surname AS recsname
FROM
    members m
        LEFT OUTER JOIN members rm ON rm.memid = m.recommendedby
ORDER BY
    (m.surname, m.firstname);

-- Select with recommendedby

SELECT
    DISTINCT rm.firstname AS firstname,
             rm.surname AS surname
FROM
    cd.members m
        JOIN cd.members rm ON rm.memid = m.recommendedby
ORDER BY
    surname,
    firstname;

-- select without join

SELECT DISTINCT m.firstname || ' ' ||
                m.surname AS member, (SELECT rm.firstname || ' ' ||
                                             rm.surname AS recommender FROM cd.members rm WHERE rm.memid = m.recommendedby )
FROM
    cd.members m
ORDER BY member;

-- Group by count

SELECT
    recommendedby,
    COUNT(memid) AS count
FROM
    members
WHERE
    recommendedby IS NOT NULL
GROUP BY
    recommendedby
ORDER BY
    recommendedby;

-- Sum of slots

SELECT
    facid,
    SUM(slots) AS "Total Slots"
FROM
    bookings
GROUP BY
    facid
ORDER BY
    facid;

-- Sum with date condition

SELECT
    facid,
    SUM(slots) AS "Total Slots"
FROM
    bookings
WHERE
    DATE(starttime) BETWEEN '2012-09-01'
  AND '2012-09-30'
GROUP BY
    facid
ORDER BY
    "Total Slots";
