package application;

import com.ifsc.claudio.renaldo.elana.diovane.controller.ContatoLista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Parent scene;

	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle(" lista de contatos ");
			primaryStage.resizableProperty().setValue(Boolean.FALSE);

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/com/ifsc/claudio/renaldo/elana/diovane/view/ContatoLista.fxml"));

			Parent ContatoListaXML = loader.load();

			Scene Contatolista = new Scene(ContatoListaXML);
			primaryStage.setScene(Contatolista);

			loader.getController();

			primaryStage.setOnCloseRequest(e -> {
				if (ContatoLista.onCloseQuery()) {
					System.exit(0);
				} else {
					e.consume();
				}

			});

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
