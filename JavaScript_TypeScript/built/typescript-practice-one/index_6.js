"use strict";
/*

Intro:

    Filtering requirements have grown. We need to be
    able to filter any kind of Persons.

Exercise:

    Fix typing for the filterPersons so that it can filter users
    and return User[] when personType='user' and return Admin[]
    when personType='admin'. Also filterPersons should accept
    partial User/Admin type according to the personType.
    `criteria` argument should behave according to the
    `personType` argument value. `type` field is not allowed in
    the `criteria` field.

Higher difficulty bonus exercise:

    Implement a function `getObjectKeys()` which returns more
    convenient result for any argument given, so that you don't
    need to cast it.

    let criteriaKeys = Object.keys(criteria) as (keyof User)[];
    -->
    let criteriaKeys = getObjectKeys(criteria);

*/
Object.defineProperty(exports, "__esModule", { value: true });
exports.adminsOfAge23 = exports.usersOfAge23 = exports.filterPersons = exports.logPerson = exports.persons = void 0;
exports.persons = [
    {
        type: "user",
        name: "Max Mustermann",
        age: 25,
        occupation: "Chimney sweep",
    },
    { type: "admin", name: "Jane Doe", age: 32, role: "Administrator" },
    { type: "user", name: "Kate Müller", age: 23, occupation: "Astronaut" },
    { type: "admin", name: "Bruce Willis", age: 64, role: "World saver" },
    { type: "user", name: "Wilson", age: 23, occupation: "Ball" },
    { type: "admin", name: "Agent Smith", age: 23, role: "Anti-virus engineer" },
];
function logPerson(person) {
    console.log(" - ".concat(person.name, ", ").concat(person.age, ", ").concat(person.type === "admin" ? person.role : person.occupation));
}
exports.logPerson = logPerson;
function filterPersons(persons, personType, criteria) {
    return persons
        .filter(function (person) { return person.type === personType; })
        .filter(function (person) {
        var criteriaKeys = Object.keys(criteria);
        return criteriaKeys.every(function (fieldName) {
            return person[fieldName] === criteria[fieldName];
        });
    });
}
exports.filterPersons = filterPersons;
exports.usersOfAge23 = filterPersons(exports.persons, "user", { age: 23 });
exports.adminsOfAge23 = filterPersons(exports.persons, "admin", { age: 23 });
console.log("Users of age 23:");
exports.usersOfAge23.forEach(logPerson);
console.log();
console.log("Admins of age 23:");
exports.adminsOfAge23.forEach(logPerson);
// In case you are stuck:
// https://www.typescriptlang.org/docs/handbook/2/functions.html#function-overloads
