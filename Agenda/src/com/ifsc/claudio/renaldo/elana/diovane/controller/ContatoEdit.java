package com.ifsc.claudio.renaldo.elana.diovane.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ifsc.claudio.renaldo.elana.diovane.entity.Contato;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ContatoEdit implements Initializable {

	@FXML
	private AnchorPane pnlPrincipal;

	@FXML
	private ColumnConstraints pnlDados;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblTelefone;

	@FXML
	private TextField lblValornome;

	@FXML
	private TextField lblvalortelefone;

	@FXML
	private TextField lblvaloremail;

	@FXML
	private Label lblEmail;

	@FXML
	private HBox pnlbotoes;

	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancela;

	private Stage janelaContatoEdit;

	private Contato contato;

	private boolean okClick = true;

	@FXML
	void onClicbtnCancela(ActionEvent event) {

		this.getJanelaContatoEdit().close();

	}

	@FXML
	void onClicbtnOk(ActionEvent event) {

		if (validarCampos()) {
			this.contato.setNome(this.lblValornome.getText());
			this.contato.setTelefone(this.lblvalortelefone.getText());
			this.contato.setEmail(this.lblvaloremail.getText());

			this.okClick = true;
			this.getJanelaContatoEdit().close();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public Stage getJanelaContatoEdit() {
		return janelaContatoEdit;
	}

	public void setJanelaContatoEdit(Stage janelaContatoEdit) {
		this.janelaContatoEdit = janelaContatoEdit;
	}

	public void populaTela(Contato contato) {

		this.contato = contato;

		this.lblValornome(contato.getNome());
		// this.lblvaloremail(contato.getEmail());
		this.lblvalortelefone(contato.getTelefone());
		this.lblvaloremail(contato.getEmail());
	}

	private void lblvaloremail(String email) {
		// TODO Auto-generated method stub
		
	}

	private void lblvalortelefone(String telefone) {
		// TODO Auto-generated method stub
		
	}

	private void lblValornome(String nome) {
		// TODO Auto-generated method stub
		
	}

	public boolean isOkClick() {
		return okClick;
	}

	private boolean validarCampos() {

		String mensagemErros = new String();

		if (this.lblValornome.getText() == null || (this.lblValornome.getText()).trim().length() == 0) {
			mensagemErros += "Informe o nome !\n";
		}

		if (this.lblvalortelefone.getText() == null || (this.lblvalortelefone.getText()).trim().length() == 0) {
			mensagemErros += "Informe o telefone !\n";
		}
		if (this.lblvaloremail.getText() == null || (this.lblvaloremail.getText()).trim().length() == 0) {
			mensagemErros += "informe o email ! \n ";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaContatoEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}

	}

}
