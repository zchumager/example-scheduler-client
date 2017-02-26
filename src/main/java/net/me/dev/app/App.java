package net.me.dev.app;

import java.awt.Point;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.me.dev.utils.Task;
import net.me.dev.utils.TaskScheduler;

public class App extends Application {
	private boolean status = false;
	
	private String titleStr;
	private String runMessage;
	private String stopMessage;
	private GridPane gridPane;
	private Pane root;
	private String statusStr;
	private Point statusCoords;
	private Label statusLbl;
	private String runSchedulerStr;
	private Point runSchedulerCoords;
	private Button runSchedulerBtn;
	private Point stopSchedulerCoords;
	private String stopSchedulerStr;
	private Button stopSchedulerBtn;
	
	
	private Task task = null;
	private TaskScheduler ts = null;
	
	public App() {
		this.status = false;
		this.titleStr = "Example of JavaFX";
		this.runMessage = "RUN SCHEDULER";
		this.stopMessage = "STOP SCHEDULER";
		this.gridPane = new GridPane();
		this.root = new Pane();
		this.statusStr = "Inactived";
		this.statusCoords = new Point(10,10);
		this.statusLbl = new Label(
				this.statusStr);
		this.runSchedulerStr = "Run Scheduler";
		this.runSchedulerCoords = new Point(10, 5);
		this.runSchedulerBtn = new Button(
				this.runSchedulerStr);
		this.stopSchedulerStr = "Stop Scheduler";
		this.stopSchedulerCoords  = new Point(30, 5);
		this.stopSchedulerBtn = new Button(
				this.stopSchedulerStr);
		
		this.task = new Task();
		this.ts = new TaskScheduler(this.task);
	}
	
	public Pane appWidgets() {
		this.gridPane.add(
				this.runSchedulerBtn
				, (int)this.runSchedulerCoords.getX()
				, (int)this.runSchedulerCoords.getY());
		
		this.gridPane.add(this.stopSchedulerBtn
				, (int)this.stopSchedulerCoords.getX()
				, (int)this.stopSchedulerCoords.getY());
		
		this.gridPane.add(this.statusLbl
				, (int)this.statusCoords.getX()
				, (int)this.statusCoords.getY());
		
		this.root.getChildren().addAll(this.gridPane);
		
		this.appEvents();
		
		return this.root;
	}
	
	public void changeStatusLbl(boolean status) {
		if(status)
			this.statusLbl.setText(
					this.statusStr = "Actived");
		else
			this.statusLbl.setText(
					this.statusStr = "Inactived");
	}
	
	public void appEvents() {
		this.runSchedulerBtn.setOnAction(
				new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(App.this.runMessage);
				App.this.ts.turnOn();
				App.this.changeStatusLbl(App.this.status = true);
			}
		});
		
		this.stopSchedulerBtn.setOnAction(
				new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(App.this.stopMessage);
				App.this.ts.shutDown();
				App.this.changeStatusLbl(App.this.status = false);
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(this.titleStr);
		primaryStage.setScene(new Scene(this.appWidgets()));
		primaryStage.show();
	}

}
