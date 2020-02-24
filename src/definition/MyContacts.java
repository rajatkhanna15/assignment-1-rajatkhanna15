package definition;

import adt.MyContactsADT;

import java.util.Scanner;

/*
This class is the definition class where the various methods are implemented from the MyListAdt.
 */
public class MyContacts implements MyContactsADT {
    //Creating object of scanner class.
    Scanner scanner = new Scanner(System.in);
    /*
    A list of person type to store the contacts.
     */

    MyList<Person> myContacts = new MyList<>();

    /*
    A list of string type to store contact number of an individual to add in the list.
     */
    MyList<String> myContactNumbers = new MyList<>;


    /*
    This method add a new Contact to MyContactBook
     * It itself creates a node of person type and store all the information of the contact passed by user in the Contact List
     * It compare the FirstName of the contact and stores accordingly arranged in Lexographical Order.
     */

    @Override
    public void addContact() {

        System.out.println("You have chosen to add a new contact:");
        /*
        call the getFirstName() method to store the first name of the contact.
         */
        String firstName = getFirstName();

        /*
        call the getLastName() method to store the last name of the contact.
         */
        String lastName = getLastName();

        /*
        call the getContactNumbers() method to store the contact numbers of the contact.
         */
        contactNumbers = getContactNumbers();

        /*
        call the getEmail() method to store the email of the contact.
         */
        String Email = getEmail();

        /*
        Creating a new object of Person Class with the above values.
         */
        Person newContact = new Person(firstName, lastName, Email, contactNumbers);


        /*
        calling the compareFirstName() method to compare the firstName and return the index.
         */
        int index = compareFirstName(firstName);

        /*
        Adding a new contact at the index location found after comparing the first name.
         */
        myContacts.add(newContact, index);

        /*
        printing confirmation message
        */
        System.out.println("CONTACT ADDED SUCCESSFULLY");


    }

    /*
    this method will print all the contacts in the user's contact list.
     */

    @Override
    public void viewContact() {
        System.out.println("---Here are all your contacts---\n" +
                "-------- * -------- * -------- * --------");

        /*
        printing all the contacts one by one with the help of traversing.
         */
        for (int i = 0; i < myContacts.size; i++) {
            Person response = myContacts.getData(i);
            System.out.println(response);

        }

    }

    @Override
    public void deleteContact() {

    }

    @Override
    public void searchContact() {

    }


}
