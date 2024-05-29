"use strict";
/*

Intro:

    Time to filter the data! In order to be flexible
    we filter users using a number of criteria and
    return only those matching all of the criteria.
    We don't need Admins yet, we only filter Users.

Exercise:

    Without duplicating type structures, modify
    filterUsers function definition so that we can
    pass only those criteria which are needed,
    and not the whole User information as it is
    required now according to typing.

Higher difficulty bonus exercise:

    Exclude "type" from filter criteria.

*/
Object.defineProperty(exports, "__esModule", { value: true });
exports.filterUsers = exports.logPerson = exports.isUser = exports.isAdmin = exports.persons = void 0;
exports.persons = [
    {
        type: "user",
        name: "Max Mustermann",
        age: 25,
        occupation: "Chimney sweep",
    },
    {
        type: "admin",
        name: "Jane Doe",
        age: 32,
        role: "Administrator",
    },
    {
        type: "user",
        name: "Kate Müller",
        age: 23,
        occupation: "Astronaut",
    },
    {
        type: "admin",
        name: "Bruce Willis",
        age: 64,
        role: "World saver",
    },
    {
        type: "user",
        name: "Wilson",
        age: 23,
        occupation: "Ball",
    },
    {
        type: "admin",
        name: "Agent Smith",
        age: 23,
        role: "Administrator",
    },
];
var isAdmin = function (person) {
    return person.type === "admin";
};
exports.isAdmin = isAdmin;
var isUser = function (person) {
    return person.type === "user";
};
exports.isUser = isUser;
function logPerson(person) {
    var additionalInformation = "";
    if ((0, exports.isAdmin)(person)) {
        additionalInformation = person.role;
    }
    if ((0, exports.isUser)(person)) {
        additionalInformation = person.occupation;
    }
    console.log(" - ".concat(person.name, ", ").concat(person.age, ", ").concat(additionalInformation));
}
exports.logPerson = logPerson;
function filterUsers(persons, criteria) {
    return persons.filter(exports.isUser).filter(function (user) {
        var criteriaKeys = Object.keys(criteria);
        return criteriaKeys.every(function (fieldName) {
            return user[fieldName] === criteria[fieldName];
        });
    });
}
exports.filterUsers = filterUsers;
console.log("Users of age 23:");
filterUsers(exports.persons, {
    age: 23,
}).forEach(logPerson);
// In case you are stuck:
// https://www.typescriptlang.org/docs/handbook/utility-types.html#partialtype
// https://www.typescriptlang.org/docs/handbook/release-notes/typescript-2-8.html#predefined-conditional-types
