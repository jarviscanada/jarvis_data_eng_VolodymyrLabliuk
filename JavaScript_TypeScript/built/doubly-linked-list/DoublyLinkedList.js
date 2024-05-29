"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Node = /** @class */ (function () {
    function Node(data) {
        this.data = null;
        this.prev = null;
        this.next = null;
        this.data = data;
    }
    return Node;
}());
var DoublyLinkedList = /** @class */ (function () {
    function DoublyLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    DoublyLinkedList.prototype.push = function (data) {
        var node = new Node(data);
        if (this.size == 0) {
            this.first = node;
            this.last = this.first;
        }
        else {
            this.last.next = node;
            node.prev = this.last;
            this.last = node;
        }
        this.size++;
    };
    DoublyLinkedList.prototype.pop = function () {
        if (this.size == 0) {
            return;
        }
        var node = this.last;
        if (this.first != this.last) {
            this.last.prev.next = null;
            this.last = this.last.prev;
        }
        else {
            this.first = null;
            this.last = null;
        }
        this.size--;
        return node;
    };
    DoublyLinkedList.prototype.shift = function () {
        if (this.size == 0) {
            return;
        }
        var node = this.first;
        if (this.first != this.last) {
            this.first.next.prev = null;
            this.first = this.first.next;
        }
        else {
            this.first = null;
            this.last = null;
        }
        this.size--;
        return node;
    };
    DoublyLinkedList.prototype.unshift = function (data) {
        var node = new Node(data);
        if (this.size == 0) {
            this.first = node;
            this.last = this.first;
        }
        else {
            this.first.prev = node;
            node.next = this.first;
            this.first = node;
        }
        this.size++;
    };
    return DoublyLinkedList;
}());
var dll = new DoublyLinkedList();
dll.push(19);
dll.push(5);
dll.push(10);
console.log(dll.pop());
console.log(dll.pop());
console.log(dll.pop());
console.log(dll.pop());
dll.unshift(19);
dll.unshift(5);
dll.unshift(10);
console.log(dll.shift());
console.log(dll.shift());
console.log(dll.shift());
console.log(dll.shift());
