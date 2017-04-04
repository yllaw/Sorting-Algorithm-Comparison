package gui;


import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import sorting.IntegerLists;
import sorting.SortStats;
import sorting.SortUtils;

public class SortCompareGUI extends Application {
	Stage window;
	TableView<SortStats> table;
	List<Integer> sortedList;

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Sorting Algorithm Comparison");

		// sortName column;
		TableColumn<SortStats, String> sortNameColumn = new TableColumn<>("Sort Algorithm");
		sortNameColumn.setMinWidth(200);
		sortNameColumn.setCellValueFactory(new PropertyValueFactory<>("sortName"));

		// numComparison column;
		TableColumn<SortStats, Long> numComparisonColumn = new TableColumn<>("Comparison #");
		numComparisonColumn.setMinWidth(100);
		numComparisonColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfComparison"));

		// numSwap column;
		TableColumn<SortStats, Long> numSwapColumn = new TableColumn<>("Swap #");
		numSwapColumn.setMinWidth(100);
		numSwapColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfSwap"));

		// executionTimeMillis column;
		TableColumn<SortStats, Long> executionTimeColumn = new TableColumn<>("Time (ms)");
		executionTimeColumn.setMinWidth(100);
		executionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("executionTimeMillis"));

		// executionTimeMillis column;
		TableColumn<SortStats, String> runtimeColumn = new TableColumn<>("Big(Θ) Runtime");
		runtimeColumn.setMinWidth(150);
		runtimeColumn.setCellValueFactory(new PropertyValueFactory<>("runtime"));

		runtimeColumn.setCellFactory(new Callback<TableColumn<SortStats, String>, TableCell<SortStats, String>>() {
			public TableCell call(TableColumn param) {
				return new TableCell<SortStats, String>() {

					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							
							
							if (item.contentEquals("Θ(n log(n))")) {
								this.setTextFill(Color.DARKKHAKI);
							} else if (item.contentEquals("Θ(n^2)")) {
								this.setTextFill(Color.RED);
							} else if (item.contains("Θ(n(log(n))^2)")) {
								this.setTextFill(Color.ORANGE);
							}  else if (item.contains("Θ(n+k") || item.contains("Θ(nk")) {
								this.setTextFill(Color.GREEN);
							} else if (item.contains("Θ(n+k") || item.contains("Θ(nk")) {
								this.setTextFill(Color.GREEN);
							} else if (item.contains("Θ(n log(n)) - Θ (n^2)")){
								this.setTextFill(Color.DARKORANGE);
							}
							setText(item);
							this.setStyle("-fx-font-weight: bold");
							
						}
					}
				};
			}
		});

		Label sort = new Label("Choose # of elements to sort");
		sort.setPadding(new Insets(5, 5, 5, 5));

		Button ten = new Button("10");
		ten.setPadding(new Insets(5, 10, 5, 10));
		ten.setOnAction(e -> tenClicked());

		Button hundred = new Button("100");
		hundred.setPadding(new Insets(5, 10, 5, 10));
		hundred.setOnAction(e -> hundredClicked());

		Button thousand = new Button("1000");
		thousand.setPadding(new Insets(5, 10, 5, 10));
		thousand.setOnAction(e -> thousandClicked());

		Button tenThousand = new Button("10000");
		tenThousand.setPadding(new Insets(5, 10, 5, 10));
		tenThousand.setOnAction(e -> tenThousandClicked());

		Button hundredThousand = new Button("100000");

		hundredThousand.setPadding(new Insets(5, 10, 5, 10));
		hundredThousand.setOnAction(e -> hundredThousandClicked());

		Button sortedList = new Button("Sorted List");

		sortedList.setPadding(new Insets(5, 12, 5, 10));
		sortedList.setOnAction(e -> sortedListClicked());

		// Button note = new Button("?");
		//
		// note.setPadding(new Insets(5, 10, 5, 10));
		// note.setOnAction(e -> noteClicked());

		table = new TableView<>();
		table.getColumns().addAll(sortNameColumn, numComparisonColumn, numSwapColumn, executionTimeColumn,
				runtimeColumn);

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 10, 5, 10));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(sort, ten, hundred, thousand, tenThousand, hundredThousand, sortedList);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(table, hbox);

		Scene scene = new Scene(vbox);
		window.setResizable(false);
		window.setScene(scene);
		window.show();

	}

	public void tenClicked() {
		table.getItems().clear();
		table.setItems(getSortStats10());
	}

	public void hundredClicked() {
		table.getItems().clear();
		table.setItems(getSortStats100());
		table.refresh();

	}

	public void thousandClicked() {
		table.getItems().clear();
		table.setItems(getSortStats1000());
	}

	public void tenThousandClicked() {
		table.getItems().clear();
		table.setItems(getSortStats10000());
	}

	public void hundredThousandClicked() {
		table.getItems().clear();
		table.setItems(getSortStats100000());
	}

	public void sortedListClicked() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sorted List");
		alert.setHeaderText("Sorted List of " + sortedList.size() + " elements");
		alert.setContentText("Sorted List:");

		TextArea textArea = new TextArea();

		for (Integer i : sortedList) {
			textArea.appendText(i.toString() + "\n");
		}
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);

		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}

	public ObservableList<SortStats> getSortStats10() {
		ObservableList<SortStats> stList = FXCollections.observableArrayList();
		sortedList = IntegerLists.create10RandomIntList();

		stList.add(SortUtils.bubbleSort(IntegerLists.create10RandomIntList()));
		stList.add(SortUtils.cocktailSort(IntegerLists.create10RandomIntList()));
		stList.add(SortUtils.combSort(IntegerLists.create10RandomIntList()));
		stList.add(SortUtils.countingSort(IntegerLists.create10RandomIntList(50000)));
		stList.add(SortUtils.insertionSort(IntegerLists.create10RandomIntList()));
		stList.add(SortUtils.mergeSort(IntegerLists.create10RandomIntList()));
		stList.add(SortUtils.quickSort(sortedList));
		stList.add(SortUtils.radixSort(IntegerLists.create10RandomIntList()));
		stList.add(SortUtils.selectionSort(IntegerLists.create10RandomIntList()));
		stList.add(SortUtils.shellSort(IntegerLists.create10RandomIntList()));

		return stList;
	}

	public ObservableList<SortStats> getSortStats100() {
		ObservableList<SortStats> stList = FXCollections.observableArrayList();
		sortedList = IntegerLists.create100RandomIntList();

		stList.add(SortUtils.bubbleSort(IntegerLists.create100RandomIntList()));
		stList.add(SortUtils.cocktailSort(IntegerLists.create100RandomIntList()));
		stList.add(SortUtils.combSort(IntegerLists.create100RandomIntList()));
		stList.add(SortUtils.countingSort(IntegerLists.create100RandomIntList(50000)));
		stList.add(SortUtils.insertionSort(IntegerLists.create100RandomIntList()));
		stList.add(SortUtils.mergeSort(IntegerLists.create100RandomIntList()));
		stList.add(SortUtils.quickSort(sortedList));
		stList.add(SortUtils.radixSort(IntegerLists.create100RandomIntList()));
		stList.add(SortUtils.selectionSort(IntegerLists.create100RandomIntList()));
		stList.add(SortUtils.shellSort(IntegerLists.create100RandomIntList()));

		return stList;
	}

	public ObservableList<SortStats> getSortStats1000() {
		ObservableList<SortStats> stList = FXCollections.observableArrayList();
		sortedList = IntegerLists.create1000RandomIntList();

		stList.add(SortUtils.bubbleSort(IntegerLists.create1000RandomIntList()));
		stList.add(SortUtils.cocktailSort(IntegerLists.create1000RandomIntList()));
		stList.add(SortUtils.combSort(IntegerLists.create1000RandomIntList()));
		stList.add(SortUtils.countingSort(IntegerLists.create1000RandomIntList(50000)));
		stList.add(SortUtils.insertionSort(IntegerLists.create1000RandomIntList()));
		stList.add(SortUtils.mergeSort(IntegerLists.create1000RandomIntList()));
		stList.add(SortUtils.quickSort(sortedList));
		stList.add(SortUtils.radixSort(IntegerLists.create1000RandomIntList()));
		stList.add(SortUtils.selectionSort(IntegerLists.create1000RandomIntList()));
		stList.add(SortUtils.shellSort(IntegerLists.create1000RandomIntList()));

		return stList;
	}

	public ObservableList<SortStats> getSortStats10000() {
		ObservableList<SortStats> stList = FXCollections.observableArrayList();
		sortedList = IntegerLists.create10000RandomIntList();

		stList.add(SortUtils.bubbleSort(IntegerLists.create10000RandomIntList()));
		stList.add(SortUtils.cocktailSort(IntegerLists.create10000RandomIntList()));
		stList.add(SortUtils.combSort(IntegerLists.create10000RandomIntList()));
		stList.add(SortUtils.countingSort(IntegerLists.create10000RandomIntList(50000)));
		stList.add(SortUtils.insertionSort(IntegerLists.create10000RandomIntList()));
		stList.add(SortUtils.mergeSort(IntegerLists.create10000RandomIntList()));
		stList.add(SortUtils.quickSort(sortedList));
		stList.add(SortUtils.radixSort(IntegerLists.create10000RandomIntList()));
		stList.add(SortUtils.selectionSort(IntegerLists.create10000RandomIntList()));
		stList.add(SortUtils.shellSort(IntegerLists.create10000RandomIntList()));

		return stList;
	}

	public ObservableList<SortStats> getSortStats100000() {
		ObservableList<SortStats> stList = FXCollections.observableArrayList();
		sortedList = IntegerLists.create100000RandomIntList();

		stList.add(SortUtils.bubbleSort(IntegerLists.create100000RandomIntList()));
		stList.add(SortUtils.cocktailSort(IntegerLists.create100000RandomIntList()));
		stList.add(SortUtils.combSort(IntegerLists.create100000RandomIntList()));
		stList.add(SortUtils.countingSort(IntegerLists.create100000RandomIntList(50000)));
		stList.add(SortUtils.insertionSort(IntegerLists.create100000RandomIntList()));
		stList.add(SortUtils.mergeSort(IntegerLists.create100000RandomIntList()));
		stList.add(SortUtils.quickSort(sortedList));
		stList.add(SortUtils.radixSort(IntegerLists.create100000RandomIntList()));
		stList.add(SortUtils.selectionSort(IntegerLists.create100000RandomIntList()));
		stList.add(SortUtils.shellSort(IntegerLists.create100000RandomIntList()));

		return stList;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
