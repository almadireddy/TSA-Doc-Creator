package DocCreator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller
  implements Initializable {

  @FXML //  fx:id="myButton"
  private Button submitButton; // Value injected by FXMLLoader
  @FXML
  private TextField name;
  @FXML
  private TextField classification;
  @FXML
  private TextField problem;
  @FXML
  private TextArea  procedure;
  @FXML
  private TextField result;


  @Override // This method is called by the FXMLLoader when initialization is complete
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        DocCreator creator = new DocCreator();
        String projectName = name.getText();
        String classNum = classification.getText();
        String problemText = problem.getText();
        String procedureText = procedure.getText();
        String resultText = result.getText();

        creator.createHeader(projectName, classNum);
        creator.createProblem(problemText);
        creator.createProcedure(procedureText);
        creator.createResult(resultText);
        creator.saveDoc();

        System.out.println("That was easy, wasn't it?");
      }
    });

  }

}
