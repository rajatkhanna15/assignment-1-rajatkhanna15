package definition;

import adt.MyContactsADT;

import java.util.InputMismatchException;
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

    /*
    this method will first print the name of all the contacts with a specified number(starting from 1)
    and then delete the specified contact by the specified nummber.
     */

    @Override
    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);

        /*
        calling the printNames() method to print all the contact names.
         */
        printNames();

        /*
        number to delete a contact to be entered.
         */

        System.out.println("Enter the number against the contact to delete it:");
        try {
            int index = scanner.nextInt();

            /*
            if the index is 0 or greater than the size than print invalid message.
             */
            if (index > myContacts.size || index == 0)
                System.out.println("INVALID MESSAGE");
            else {
                /*
                get the data of the contact and storing in person type variable
                 */
                Person p1 = myContacts.getData(index - 1);

                /*
                Getting the first and last name of the contact.
                 */
                String name = p1.getFirstName() + " " + p1.getLastName();

                /*
                Deleting that contact
                 */
                myContacts.remove(index - 1);

                /*
                printing message
                 */
                System.out.println(name + "contact deleted from list!");

            }

        } catch (InputMismatchException E) {
            System.out.println("Integer input to be entered");
        }

    }

    /*
     * This Method allows you to search contact by their FirstName
     */
    @Override
    public void searchContact() {
        /*
        Taking input of user
         */
        int size;
        Scanner sc = new Scanner(System.in);
        System.out.println("You could search for a contact from their first names:");
        String name = sc.next();
        /*
        removing space from the input given by the user if present
         */
        name = name.trim();
        /*
        calling the matchFirst() method and storing the list of returned indexes in a indexesList field
         */
        MyList<Integer> indexesList = matchFirst(name);
        /*
        initialization of a boolean variable for ternary operation
         */
        boolean a = false;
        /*
        get the size of indexesList field in the size variable
         */
        size = indexesList.size;
        if (size > 1) {
            /*;
            if only 1 match found i.e, the size of the list is 1 then make a =true
             */
            a = true;
        }
        /*
        Check if No result found
         */
        if (size == 0) {
            System.out.println("NO RESULTS FOUND");
        } else
        /*
        Print the number of matches Found
         */

            System.out.println(a ? size + " Matches found!" : size + " Match found!");
        /*
        Print every matched contact by the indexes in the indexesList
         */
        for (int i = 0; i < size; i++) {
            int index = indexesList.getData(i);
            System.out.println(myContacts.getData(index));
        }
    }

    /*
     helper method for deleteContact() method and will print the names with the index number (starting from 1) in front of it
     */
    private void printNames() {
        /*
        getting the first and last name of the contact by traversing(one by one)
         */
        System.out.println("Here are your all contacts:");
        for (int i = 0; i < myContacts.size; i++) {
            Person temp = myContacts.getData(i);
            /*
            Printing the names
             */
            System.out.println((i + 1) + "." + temp.getFirstName() + " " + temp.getLastName());
        }
    }

    /* helper method for searchContact() method it match the firstName passed with every contact present in the list
       and return a list of such indexes where the name is matched
     */
    private MyList<Integer> matchFirst(String firstName) {
        /*
        Creating a new List to store the  matched indexes
         */
        MyList<Integer> indexes = new MyList<>();
        /*
        if myContacts is empty do nothing
         */
        if (myContacts.size == 0) {
            System.out.println("There are No contacts saved please add some");
        }
        /*
        Traversing myContacts to match with the firstName
         */
        else {
            for (int i = 0; i < myContacts.size; i++) {
                /*
                First get the contact in a Person type variable
                 */
                Person temp = myContacts.getData(i);
                /*
                Then get the FirstName of that contact
                 */
                String name = temp.getFirstName();
                /*
                Converting both the passed name and ContactName to lowercase to achieve case Insensitivity
                 */
                name = name.toLowerCase();
                firstName = firstName.toLowerCase();
                /*
                if both the names found same add the index to the list
                 */

                if (name.compareTo(firstName) == 0) {
                    indexes.add(i);
                }

            }
        }
        /*
        return the list
         */
        return indexes;
    }

    /*A helper method of addContact() method
      use to input firstName of the Contact

     */
    private String getFirstName() {
        System.out.println("Please Enter the name of the Person");
        System.out.print("FirstName: ");
        return scanner.next();

    }

    /*
      A helper method of addContact() method
     * use to input LastName of the Contact

     */

    private String getLastName() {
        System.out.print("LastName: ");
        return scanner.next();

    }


}
