package adt;

/*
This is an interface for the required contact application in which the
user is allowed to perform the following operations:
1. add a new contact.
2. view all the contacts.
3. search a contact by his/her first name.
4. delete a contact.
 */

public interface MyContactsADT {
    /*
    this method will add a new contact in the contact list.
    Also,it will store the newly added contact in lexographical order.
     */
    void addContact();


    /*
    this method will make the user to view all
    the contacts in his/her list.
    */
    void viewContact();


    /*
    this method will first print all the contacts and
    then user can delete a particular specified contact.
     */
    void deleteContact();


    /*
    this method will allow the user to search a contact by his/her first name.
     */
    void searchContact();
}
