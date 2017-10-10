package info.tahmidchoyon.graphyity;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import info.tahmidchoyon.graphyity.processor.MultiGraphCreator;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.regex.Pattern;

public class Controller {

    @FXML
    public JFXListView<String> listView;

    @FXML
    public JFXTextArea textArea;

    private static MultiGraphCreator multiGraph;

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            if (multiGraph == null) {
                multiGraph = new MultiGraphCreator();
            }

            String response = execCommand(textArea.getText().split("\n")[textArea.getText().split("\n").length - 1]);

            if (!response.equals("ERROR! Invalid Command")) {
                System.out.println(response);
                listView.getItems().add(response);
            } else {
                System.out.println(response);
            }
        }
    }

    public String execCommand(String command) {
        if (Pattern.matches("^add_node[(][\\w{1}][)]", command)) {
            return multiGraph.addNode("" + command.charAt(command.length() - 2));
        } else if (Pattern.matches("^del_node[(][\\w{1}][)]", command)) {
            return multiGraph.removeNode("" + command.charAt(command.length() - 2));
        } else if (Pattern.matches("^create_edge[(][\\w{1}]-{1}[\\w{1}][)]", command)) {
            String nodes[] = processStringNodes(command).split("-");
            return multiGraph.createEdge(nodes[0], nodes[1]);
        }
        return "ERROR! Invalid Command";
    }

    private String processStringNodes(String command) {
        return command.replace("create_edge", "").replaceAll("\\(", "").replaceAll("\\)", "");
    }
}
