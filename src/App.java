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


import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage arg0) throws Exception {

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(5.5);
        gp.setVgap(25.5);

        gp.add(new Label("First Name: "), 0, 0);
        TextField fn = new TextField();
        gp.add(fn, 1, 0, 5, 1);

        gp.add(new Label("Last Name: "), 0, 1);
        TextField ln = new TextField();
        gp.add(ln, 1, 1, 5, 1);

        gp.add(new Label("City: "), 0, 2);
        TextField city = new TextField();
        city.setPrefWidth(80);
        gp.add(city, 1, 2, 1, 1);

        gp.add(new Label("Province: "), 2, 2);
        final ComboBox<String> cbProv = new ComboBox<>();
        cbProv.getItems().addAll("Alberta", "British Columbia", "Manitoba", "New Brunswick",
                "Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario",
                "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon");
        cbProv.setPrefWidth(80);
        gp.add(cbProv, 3, 2, 1, 1);

        gp.add(new Label("Postal Code: "), 4, 2);
        TextField postal = new TextField();
        postal.setPrefWidth(80);
        gp.add(postal, 5, 2, 1, 1);

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(80);
        Button btnFirst = new Button("First");
        btnFirst.setMinWidth(80);
        Button btnNext = new Button("Next");
        btnNext.setMinWidth(80);
        Button btnPrevious = new Button("Previous");
        btnPrevious.setMinWidth(80);
        Button btnLast = new Button("Last");
        btnLast.setMinWidth(80);
        Button btnUpdate = new Button("Update");
        btnUpdate.setMinWidth(80);

        gp.add(btnAdd, 0, 3);
        gp.add(btnFirst, 1, 3);
        gp.add(btnNext, 2, 3);
        gp.add(btnPrevious, 3, 3);
        gp.add(btnLast, 4, 3);
        gp.add(btnUpdate, 5, 3);

        Scene scene = new Scene(gp, 600, 300);
        arg0.setScene(scene);
        arg0.setTitle("Address Book");
        arg0.show();

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                                
                StringHelper s = new StringHelper();
                
                String fnS = s.fillString(fn.getText(), 20);
                String lnS = s.fillString(ln.getText(), 30);
                String cityS = s.fillString(city.getText(), 15);
                String provS = s.fillString((String) cbProv.getValue(), 25);
                String postalS = s.fillString(postal.getText(), 7);

                RandomAccessFileHelper f = new RandomAccessFileHelper("AddressBook.txt");
                try {
                    f.appendFile(fnS + "\n" + lnS + "\n" + cityS + "\n" + provS + "\n" + postalS + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnFirst.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                
                RandomAccessFileHelper f = new RandomAccessFileHelper("AddressBook.txt");
                try {
                    fn.setText(f.readFile(0, 20));
                    ln.setText(f.readFile(21, 30));
                    city.setText(f.readFile(52, 15));
                    cbProv.setValue(f.readFile(68, 25));
                    postal.setText(f.readFile(94, 7));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }     
        });

        btnNext.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {

                StringHelper s = new StringHelper();
                String fnS = s.fillString(fn.getText(), 20);
                RandomAccessFileHelper f = new RandomAccessFileHelper("AddressBook.txt");
                try {
                    int nxt = f.getFilePointer(fnS);
                    fn.setText(f.readFile(nxt, 20));
                    ln.setText(f.readFile(nxt + 21, 30));
                    city.setText(f.readFile(nxt + 52, 15));
                    cbProv.setValue(f.readFile(nxt + 68, 25));
                    postal.setText(f.readFile(nxt + 94, 7));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPrevious.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {

                StringHelper s = new StringHelper();
                String fnS = s.fillString(fn.getText(), 20);
                RandomAccessFileHelper f = new RandomAccessFileHelper("AddressBook.txt");
                try {
                    int nxt = f.getFilePointer(fnS) - 204;
                    fn.setText(f.readFile(nxt, 20));
                    ln.setText(f.readFile(nxt + 21, 30));
                    city.setText(f.readFile(nxt + 52, 15));
                    cbProv.setValue(f.readFile(nxt + 68, 25));
                    postal.setText(f.readFile(nxt + 94, 7));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnLast.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                
                RandomAccessFileHelper f = new RandomAccessFileHelper("AddressBook.txt");
                try {
                    int lst = f.getLength() - 102;
                    fn.setText(f.readFile(lst, 20));
                    ln.setText(f.readFile(lst + 21, 30));
                    city.setText(f.readFile(lst + 52, 15));
                    cbProv.setValue(f.readFile(lst + 68, 25));
                    postal.setText(f.readFile(lst + 94, 7));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                                
                StringHelper s = new StringHelper();
                
                String fnS = s.fillString(fn.getText(), 20);
                String lnS = s.fillString(ln.getText(), 30);
                String cityS = s.fillString(city.getText(), 15);
                String provS = s.fillString((String) cbProv.getValue(), 25);
                String postalS = s.fillString(postal.getText(), 7);

                RandomAccessFileHelper f = new RandomAccessFileHelper("AddressBook.txt");
                try {
                    int upd = f.getFilePointer(fnS) - 102;
                    f.writeFile(fnS + "\n" + lnS + "\n" + cityS + "\n" + provS + "\n" + postalS + "\n", upd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static void main(String[] args) {
        launch();
    }

}
