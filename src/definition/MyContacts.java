package definition;

import adt.MyContactsADT;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        MyList<String> contactNumbers = getContactNumbers();

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

    /*
     * This is a helper method for addContact() method
     * it helps in comparing the firstName of a contact and return a single index
     * of the contact that is found Lexicographically  equal or greater
     *
     *  FirstName the name to be compared
     * return index of the contact equal or greater Lexicographically
     */
    private int compareFirstName(String FirstName) {
        int index = 0;
        /*
        If there is no contact do nothing and return the first index as no sorting is required
         */
        if (myContacts.size == 0) {
            return index;
        }
        /*
            Traversing myContactBook to compare everyName
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
                FirstName = FirstName.toLowerCase();
                /*
                If passed name is smaller increase the value of index by 1
                 */
                if (name.compareTo(FirstName) < 0) {
                    index++;
                }
                /*
                if the name found equal or greater break the loop and return the index
                 */
                else if (name.compareTo(FirstName) == 0) {
                    break;
                } else {
                    break;
                }

            }

        }
        return index;
    }


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


    /* A helper method of addContact() method
     * use to input contactNumbers of the Contact
     * Ensures that there must be AtLeast 1 contact for every individual contact

     */

    private MyList<String> getContactNumbers() {
        /*
        Declaration of a new list to store ContactNumbers of the new Contact
         */
        MyList<String> contactNumbers = new MyList<>();
        /*
        A String Variable to store an individual Contact of the new Contact
         */
        String contactNo;
        while (true) {
            System.out.print("ContactNumber: ");
            contactNo = scanner.next();
             /*
             If entered number is valid add contactNo to the list
              */
            if (Pattern.matches("[0-9]+", contactNo)) {
                contactNumbers.add(contactNo);
                break;
            }
            /*
            if entered number is not valid i.e, contains characters except digits
             */
            else {
                System.out.println("Invalid PhoneNumber");
            }
        }
        while (true) {
            /*
            Asking again for a contactNumber if user  want to add
             */
            System.out.print("Do You Want to add a new ContactNumber? (y/n) :");
            String a = scanner.next();
            a = a.toLowerCase();
            /*
            Checking if the entered input is a single character or not
            if not printing Invalid input message
             */
            char at = a.charAt(0);
            if (a.length() > 1) {
                System.out.println("Please Enter a Valid Input i.e., y for Yes or n for No");
                continue;
            }
            /*
            if entered input means yes allow to add a new contact and add that contact also to the list of ContactNumbers
             */

            if (at == 'y') {
                System.out.print("ContactNumber: ");
                contactNo = scanner.next();
                /*
                checking a valid contact number
                 */
                if (Pattern.matches("[0-9]+", contactNo)) {

                    contactNumbers.add(contactNo);
                } else {
                    System.out.println("Invalid PhoneNumber");
                }
            }
            /*
            if the entered input means no end the loop and return the contactNumbers list
             */
            else if (at == 'n') {
                break;
            }
            /*
            If there is some invalid input other than 'y' or 'n' then print Invalid input message
             */
            else {
                System.out.println("Please Enter a Valid Input i.e., y for Yes or n for No");
            }
        }

        return contactNumbers;


    }

    /*
     * A helper method of addContact() method
     * use to input email of the Contact
     * gives the choice if user want to add a email or not
     * there can only be a single email for a individual contact
     * if user dont want to add a email return null
     *
     * return email of the new contact
     */


    private String getEmail() {
        String Email = null;
        System.out.print("Do You Want to add an email ? (y/n) :");
        while (true) {
            String a = scanner.next();
            a = a.toLowerCase();
            /*
            Checking if the entered input is a single character or not
            if not printing Invalid input message
             */
            char at = a.charAt(0);
            if (a.length() > 1) {
                System.out.println("Please Enter a Valid Input i.e., y for Yes or n No");
                continue;
            }
            /*
            if entered input means yes allow to add a new email
             */
            if (at == 'y') {
                System.out.print("Email Address: ");
                Email = scanner.next();
                break;
            }
             /*
            if the entered input means no end the loop
             */
            else if (at == 'n') {
                break;
            }
             /*
            If there is some invalid input other than 'y' or 'n' then print Invalid input message
             */
            else {
                System.out.println("Please Enter a Valid Input i.e., y for Yes or n for No");
            }
        }
        return Email;
    }
}



