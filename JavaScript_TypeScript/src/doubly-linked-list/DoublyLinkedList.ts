export interface NodeProps<T> {
  data: T | null;
  prev: Node<T> | null;
  next: Node<T> | null;
}

class Node<T> implements NodeProps<T> {
  data: T | null = null;
  prev: Node<T> | null = null;
  next: Node<T> | null = null;
  constructor(data: T) {
    this.data = data;
  }
}

export interface LinkedListProps<T> {
  size: number | 0;
  first: Node<T> | null;
  last: Node<T> | null;
  push(data: T): void;
  pop(): Node<T> | undefined;
  shift(): Node<T> | undefined;
  unshift(data: T): void;
}

class DoublyLinkedList<T> implements LinkedListProps<T> {
  first: Node<T> | null = null;
  last: Node<T> | null = null;
  size: number | 0 = 0;

  push(data: T) {
    const node = new Node<T>(data);
    if (this.size == 0) {
      this.first = node;
      this.last = this.first;
    } else {
      this.last.next = node;
      node.prev = this.last;
      this.last = node;
    }
    this.size++;
  }
  pop() {
    if (this.size == 0) {
      return;
    }

    const node = this.last;
    if (this.first != this.last) {
      this.last.prev.next = null;
      this.last = this.last.prev;
    } else {
      this.first = null;
      this.last = null;
    }

    this.size--;
    return node;
  }
  shift() {
    if (this.size == 0) {
      return;
    }

    const node = this.first;
    if (this.first != this.last) {
      this.first.next.prev = null;
      this.first = this.first.next;
    } else {
      this.first = null;
      this.last = null;
    }

    this.size--;
    return node;
  }
  unshift(data: T) {
    const node = new Node<T>(data);
    if (this.size == 0) {
      this.first = node;
      this.last = this.first;
    } else {
      this.first.prev = node;
      node.next = this.first;
      this.first = node;
    }
    this.size++;
  }
}

const dll = new DoublyLinkedList();
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
