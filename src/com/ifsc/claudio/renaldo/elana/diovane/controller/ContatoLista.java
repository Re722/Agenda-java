package com.ifsc.claudio.renaldo.elana.diovane.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.claudio.renaldo.elana.diovane.dao.ContatoDAO;
import com.ifsc.claudio.renaldo.elana.diovane.entity.Contato;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ContatoLista implements Initializable {

	@FXML
	private AnchorPane pnlPrincipal;

	@FXML
	private SplitPane pnlDivisao;

	@FXML
	private AnchorPane pnlEsquerda;

	@FXML
	private TableView<Contato> tbcContato;

	@FXML
	private TableColumn<Contato, Long> tbcCodigo;

	@FXML
	private TableColumn<Contato, String> tbcNome;

	@FXML
	private AnchorPane pnlDireita;

	@FXML
	private Label lblDetalhes;

	@FXML
	private GridPane pnlDetalhes;

	@FXML
	private Label tblNome;

	@FXML
	private Label tblPhone;

	@FXML
	private Label tblVolorNome;

	@FXML
	private Label tblValorPhone;

	@FXML
	private ButtonBar barBotoes;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	private List<Contato> listaContato;
	private ObservableList<Contato> observableListaContato = FXCollections.observableArrayList();
	private ContatoDAO contatoDAO;

	public static final String Contato_EDITAR = " - Editar";
	public static final String Contato_INCLUIR = " - Incluir";

	@FXML
	void onClicbtnEditar(ActionEvent event) throws IOException {

		Contato contato = this.tbcContato.getSelectionModel().getSelectedItem();

		if (contato != null) {
			boolean btnConfirmarClic = this.onShowtelaContatoEdit(contato, ContatoLista.Contato_EDITAR);

			if (btnConfirmarClic) {
				this.getContatoDAO().update(contato, null);
				this.carregarTableViewContato();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma contato na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClicbtnExcluir(ActionEvent event) throws IOException {

		Contato contato = this.tbcContato.getSelectionModel().getSelectedItem();

		if (contato != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("confirma a exclusao docontato?\n " + contato.getId());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getContatoDAO().delete(contato);
				this.carregarTableViewContato();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma contato na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClicbtnIncluir(ActionEvent event) throws IOException {

		Contato contato = new Contato();

		boolean btnConfirmarClic = this.onShowtelaContatoEdit(contato, Contato_INCLUIR);
		try {
			if (btnConfirmarClic) {
				this.getContatoDAO().save(contato);
				this.carregarTableViewContato();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			this.setContatoDAO(new ContatoDAO());

			
			this.carregarTableViewContato();
			this.selecionarItemTableViewContato(null);

			this.tbcContato.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> selecionarItemTableViewContato(newValue));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public ContatoDAO getContatoDAO() {
		return contatoDAO;
	}

	public List<Contato> getListaContato() {
		return listaContato;
	}

	public ObservableList<Contato> getObservableListaContato() {
		return observableListaContato;
	}

	public void setListaContato(List<Contato> listaContato) {
		this.listaContato = listaContato;
	}

	public void setObservableListaContato(ObservableList<Contato> observableListaContato) {
		this.observableListaContato = observableListaContato;
	}

	public void carregarTableViewContato() throws IOException {

		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<Contato, Long>("id"));
		this.tbcNome.setCellValueFactory(new PropertyValueFactory<Contato, String>("nome"));

		this.setListaContato(this.getContatoDAO().getAll());

		this.setObservableListaContato(FXCollections.observableArrayList(this.getListaContato()));
		this.tbcContato.setItems(this.getObservableListaContato());

	}

	public void selecionarItemTableViewContato(Contato contato) {
		if (contato != null) {
			this.tblNome.setText(contato.getNome());
			this.tblValorPhone.setText(contato.getTelefone());
		}

		else {
			this.tblVolorNome.setText("");
			this.tblValorPhone.setText("");
		}

	}

	public static boolean onCloseQuery() {

		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("PERGUNTA");
		alerta.setHeaderText("deseja sair do sistema?");
		ButtonType botaoNao = ButtonType.NO;
		ButtonType botaoSim = ButtonType.YES;
		alerta.getButtonTypes().setAll(botaoSim, botaoNao);
		Optional<ButtonType> opcaoClicada = alerta.showAndWait();

		return opcaoClicada.get() == botaoSim ? true : false;

	}

	public boolean onShowtelaContatoEdit(Contato contato, String operacao) {
		try {

			FXMLLoader loader = new FXMLLoader(
					getClass().getClass().getResource("/com/ifsc/claudio/renaldo/elana/diovane/view/ContatoEdit.fxml"));
			Parent ContatoEditXML = loader.load();

			Stage janelaContatoEdit = new Stage();
			janelaContatoEdit.setTitle("Cadastro de contato" + operacao);
			janelaContatoEdit.initModality(Modality.APPLICATION_MODAL);
			janelaContatoEdit.resizableProperty().set(false);

			Scene ContatoEditLayout = new Scene(ContatoEditXML);
			janelaContatoEdit.setScene(ContatoEditLayout);

			ContatoEdit contatoEditController = loader.getController();
			contatoEditController.setJanelaContatoEdit(janelaContatoEdit);
			contatoEditController.populaTela(contato);

			janelaContatoEdit.showAndWait();

			return contatoEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return false;

	}

	public List<Contato> retornaListagemContato() {
		if (this.getContatoDAO() == null) {
			this.setContatoDAO(contatoDAO);

		}
		return this.getContatoDAO().getAll();
	}

	private void setContatoDAO(ContatoDAO contato) {
		// TODO Auto-generated method stub
		this.contatoDAO=contato;

	}

}