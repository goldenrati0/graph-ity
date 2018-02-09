package info.tahmidchoyon.graphyity;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import info.tahmidchoyon.graphyity.processor.MultiGraphCreator;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.regex.Pattern;

public class Controller {

    @FXML
    public JFXListView<String> listView;

    @FXML
    public JFXTextField textField;

    @FXML
    public JFXListView<String> commandHistoryListView;

    private static MultiGraphCreator multiGraph;

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            if (Controller.multiGraph == null) {
                new Runnable() {
                    @Override
                    public void run() {
                        Controller.multiGraph = new MultiGraphCreator();
                    }
                }.run();
            }

            String command = textField.getText();
            String response = execCommand(command);
            textField.clear();
            commandHistoryListView.getItems().add(command);

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
        } else if (Pattern.matches("^starterpack", command)) {
            multiGraph.starterPack();
            return "10 Nodes added named from A to J and created edges among them";
        } else if (Pattern.matches("^random$", command)) {
            multiGraph.randomStarterPack();
            return "Random addition of nodes and edges";
        } else if (Pattern.matches("^randomsix$", command)) {
            multiGraph.randomSix();
            return "Nodes: a-f, edges towards \"a\" from others";
        } else if (Pattern.matches("^help", command)) {
            return "Help window (coming soon)";
        } else if (Pattern.matches("^del_all_nodes", command)) {
            return multiGraph.removeAllNodes();
        } else if (Pattern.matches("^del_edge[(][\\w{1}]-{1}[\\w{1}][)]", command)) {
            String nodes[] = processStringNodes(command).split("-");
            return multiGraph.deleteEdge(nodes[0].concat(nodes[1]));
        }
        return "ERROR! Invalid Command";
    }

    private String processStringNodes(String command) {
        return command.replace("create_edge", "").replaceAll("del_edge", "").replaceAll("\\(", "").replaceAll("\\)", "");
    }
}
