"use strict";
/*

Intro:

    Project grew and we ended up in a situation with
    some users starting to have more influence.
    Therefore, we decided to create a new person type
    called PowerUser which is supposed to combine
    everything User and Admin have.

Exercise:

    Define type PowerUser which should have all fields
    from both User and Admin (except for type),
    and also have type 'powerUser' without duplicating
    all the fields in the code.

*/
Object.defineProperty(exports, "__esModule", { value: true });
exports.logPerson = exports.persons = void 0;
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
    {
        type: "powerUser",
        name: "Nikki Stone",
        age: 45,
        role: "Moderator",
        occupation: "Cat groomer",
    },
];
function isAdmin(person) {
    return person.type === "admin";
}
function isUser(person) {
    return person.type === "user";
}
function isPowerUser(person) {
    return person.type === "powerUser";
}
function logPerson(person) {
    var additionalInformation = "";
    if (isAdmin(person)) {
        additionalInformation = person.role;
    }
    if (isUser(person)) {
        additionalInformation = person.occupation;
    }
    if (isPowerUser(person)) {
        additionalInformation = "".concat(person.role, ", ").concat(person.occupation);
    }
    console.log("".concat(person.name, ", ").concat(person.age, ", ").concat(additionalInformation));
}
exports.logPerson = logPerson;
console.log("Admins:");
exports.persons.filter(isAdmin).forEach(logPerson);
console.log();
console.log("Users:");
exports.persons.filter(isUser).forEach(logPerson);
console.log();
console.log("Power users:");
exports.persons.filter(isPowerUser).forEach(logPerson);
// In case you are stuck:
// https://www.typescriptlang.org/docs/handbook/utility-types.html
// https://www.typescriptlang.org/docs/handbook/2/objects.html#intersection-types
