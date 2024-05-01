#include <iostream>
#include <string.h>
#include <math.h>
#include <iomanip>
using namespace std;

struct Node {
    int data;
    Node* prev;
    Node* next;
};
Node* createList(int length) {
    Node* head = nullptr, * tail = nullptr;
    for (int i = 0; i < length; i++) {
        Node* newNode = new Node;
        newNode->data = i;
        newNode->prev = nullptr;
        newNode->next = nullptr;
        if (head == nullptr) {
            head = newNode;
            tail = head;
        }
        else {
            tail->next = newNode;
            newNode->prev = tail;
            tail = newNode;
        }
    }
    return head;
}
void deleteList(Node*& head) {
    Node* curr = head;
    Node* next;
    while (curr != nullptr) {
        next = curr->next;
        delete curr;
        curr = next;
    }
    head = nullptr;
}

void insertAnyPos(Node*& head, int value, int pos) {
    Node* newNode = new Node();
    newNode->data = value;
    if (pos < 0) {
        cout << "Err";
        return;
    }
    if (head == nullptr) {
        head = newNode;
    }
    else if (pos == 0) {
        newNode->next = head;
        head->prev = newNode;
        head = newNode;
    }
    else
    {
        Node* curr = head;
        int count = 0;
        while (curr != nullptr && count < pos - 1) {
            curr = curr->next;
            count++;
        }
        newNode->prev = curr;
        newNode->next = curr->next;
        if (curr->next != nullptr) {
            curr->next->prev = newNode;
        }
        curr->next = newNode;
    }
}
void deleteAnyPos(Node*& head, int pos) {
    if (pos == 0) {
        Node* temp = head;
        head = head->next;
        
        delete temp;
    }
    else {
        Node* curr = head;
        int count = 0;
        while (curr != nullptr && count < pos) {
            curr = curr->next;
            count++;
        }
        curr->prev->next = curr->next;
        if (curr->next != nullptr) {
            curr->next->prev = curr->prev;
        }
        delete curr;
    }
}
void printList(Node* head) {
    Node* curr = head;
    if (head == nullptr) {
        cout << "list is empty.";
    }
    while (curr != nullptr) {
        cout << curr->data << " ";
        curr = curr->next;
    }
    cout << endl;
}
int main() {
    Node* list = createList(10);
    insertAnyPos(list, 100, 5);
    printList(list);
    deleteAnyPos(list, 0);
    printList(list);
    deleteList(list);
    if (list == nullptr) {
        cout << "deleted" << endl;
    }
    printList(list);
    return 0;
}
