/**********************************************
Workshop 5
Course:     JAC444 - Fall2020
Last Name:  Xie
First Name: Yushi
ID:         142358167
Section:    NCC
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:       28-Oct-2020
**********************************************/

public class StringHelper {
    public String fillString (String str, int length) {
        return String.format("%1$-"+length+ "s", str);
    }
}
