import java.util.*;
import java.util.prefs.*;
import javafx.scene.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.beans.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.beans.binding.*;
import javafx.beans.value.*;
import javafx.beans.property.*;
import javafx.fxml.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.scene.canvas.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;	
import javafx.scene.control.Alert.AlertType.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.cell.*;	
import javafx.scene.effect.*;	
import javafx.scene.image.*;	
import javafx.scene.input.*;	
import javafx.scene.layout.*;	
import javafx.scene.media.*;	
import javafx.scene.media.*;	
import javafx.scene.paint.*;	
import javafx.scene.shape.*;	
import javafx.scene.text.*;	
import javafx.scene.transform.*;	
import javafx.scene.web.*;
import javafx.scene.control.ScrollPane.*;
import java.lang.Math;


public class MazeTest extends Application {
	private Preferences prefs;
	public static void main (String[] args){
		launch(args);
	}
	@Override
	public void start(Stage stage){
		prefs = Preferences.userRoot().node(this.getClass().getName());
		Maze test=getpreferences(prefs);
		List<String> difficultyLevel=new ArrayList<String>();
		for (int i=1; i<=20; i++){
			String a=Integer.toString(i);
			difficultyLevel.add(a);
		}
		List<String> timeLevel=new ArrayList<String>();
		for (int i=1; i<=20; i++){
			String a=Integer.toString(i);
			timeLevel.add(a);
		}
		IntegerProperty wins=new SimpleIntegerProperty(0);
		IntegerProperty losses=new SimpleIntegerProperty(0);
		Text congratulations=new Text("\nCongrats!\n You Win!");
		Text failure=new Text("\nFail!\n You Lose!");
		congratulations.setFont(new Font("Arial",50));
		congratulations.setFill(Color.GREEN);
		failure.setFont(new Font("Arial",50));
		failure.setFill(Color.RED);
		GridPane congratsPane=new GridPane();
		GridPane failPane=new GridPane();
		congratsPane.add(congratulations,0,0);
		failPane.add(failure,0,0);
		congratsPane.setVisible(false);
		failPane.setVisible(false);
		HBox hbox = new HBox();
        hbox.setLayoutX(100);
        hbox.setLayoutY(200);		
		Circle circle=new Circle(1, test.getCircleColor());
		GridPane gridpane=new GridPane();
		gridpane.setPadding(new Insets(test.insetup(),test.insetright(),test.insetdown(),test.insetleft()));
		Group root=new Group();
		gridpane.add(circle,1,1);
		circle.setVisible(false);
		DoubleProperty starttime=new SimpleDoubleProperty(test.gettime());
		DoubleProperty timeSeconds=new SimpleDoubleProperty(test.starttime());
		Label timerLabel=new Label();
		Label percentageLabel=new Label();
		ProgressBar percentageBar=new ProgressBar(0.0);		
		percentageLabel.setText("0.00%");
		percentageLabel.setFont(new Font("Arial",20));
		percentageLabel.setTextFill(test.getTimerColor());
		HBox percentagebox=new HBox(8);
		percentagebox.setMaxWidth(gridpane.getMaxWidth());
		percentageBar.setMinWidth(gridpane.getMaxWidth()); 
		percentagebox.getChildren().addAll(percentageLabel,percentageBar);
		timerLabel.setText(String.format("%.1f",Double.valueOf(timeSeconds.get())));
		timerLabel.setTextFill(test.getTimerColor());	
		Font titleFont = Font.loadFont(getClass().getResourceAsStream("digital-7.ttf"), 50);
		timerLabel.setFont(titleFont);
		timerLabel.setVisible(false);
		Timeline timeline=new Timeline();
		Timeline continuousTime=new Timeline();
		continuousTime.setCycleCount(Timeline.INDEFINITE);
		MenuBar menubar=new MenuBar();
		Menu fileMenu=new Menu("File");
		Menu settingMenu=new Menu("Settings");
		MenuItem newItem=new MenuItem("New");
		MenuItem exitItem=new MenuItem("Exit");
		exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
		newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));	timeline.setCycleCount(Timeline.INDEFINITE);
		Button startButton=new Button("Start");
		startButton.setVisible(false);
		startButton.setFont(new Font("Arial",20));
		Button firstStartButton=new Button("Press Play");
		firstStartButton.setFont(new Font("Arial",40));
		BorderPane.setAlignment(firstStartButton,Pos.BOTTOM_CENTER);
		Text titlescreen=new Text(0,100,"AP Computer Science Simple Maze Game");
		titlescreen.setVisible(true);
		titlescreen.setFont(new Font("Arial",40));
		BorderPane justforFirstStartButton=new BorderPane(titlescreen);
		justforFirstStartButton.setBottom(firstStartButton);
		MenuItem pauseItem=new MenuItem("Pause");
		GridPane pausePane=new GridPane();
		pausePane.setPadding(new Insets(test.insetup(),test.insetright(),test.insetdown(),test.insetleft()));
		pauseItem.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
		Text paused=new Text("\n   Paused\n");
		paused.setFont(new Font("Arial",80));
		Text pauseds=new Text("Press Ctrl+P to \n continue");
		pauseds.setFont(new Font("Arial",60));
		pausePane.add(paused,1,1,2,1);
		pausePane.add(pauseds,1,2,1,2);
		pausePane.setVisible(false);
		Menu statisticMenu=new Menu("Statistics");
		MenuItem winLossItem=new MenuItem("Wins/Losses");
		MenuItem miscellaneousItem=new MenuItem("Miscellaneous");	
		MenuItem difficultyItem=new MenuItem("Set Maze Size Difficulty");
		MenuItem timeDifficultyItem=new MenuItem("Set Time Difficulty");
		MenuItem coloringItem=new MenuItem("Set Up Colors");
		MenuItem gameplayItem=new MenuItem("Set Up Gameplay/Difficulty");
		Menu helpMenu=new Menu("Help");		
		MenuItem mazegeneratorItem=new MenuItem("See Maze Generator");
		MenuItem endItem=new MenuItem("End Current Game");
		endItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		MenuItem instructionItem=new MenuItem("Instructions");
		helpMenu.getItems().addAll(instructionItem,mazegeneratorItem);
		fileMenu.getItems().addAll(newItem,pauseItem,endItem,exitItem);
		settingMenu.getItems().addAll(difficultyItem,timeDifficultyItem,coloringItem,gameplayItem);
		statisticMenu.getItems().addAll(winLossItem,miscellaneousItem);
		menubar.getMenus().addAll(fileMenu,settingMenu,statisticMenu,helpMenu);
		BorderPane borderPane=new BorderPane(gridpane);
		VBox vbox=new VBox(8);
		vbox.getChildren().addAll(menubar);
		if (test.getshowpercentage()){
			vbox.getChildren().addAll(percentagebox);
		}
		borderPane.setTop(vbox);
		borderPane.setBottom(hbox);
		justforFirstStartButton.setTop(vbox);
		root.getChildren().addAll(borderPane,pausePane);				
		stage.setResizable(true);
		stage.setTitle("APCS Maze Game");
		Scene scene=new Scene(root,Color.WHITE);
		hbox.getChildren().addAll(startButton,timerLabel);			
		borderPane.toFront();
// Add the Menus
		Button nextButton=new Button("Next");
		Button showMaze=new Button("Show Maze");
		Button showMazeSolution=new Button("Show Optimal Maze Path");
		nextButton.setFont(new Font("Arial",20));
		nextButton.setVisible(false);
		hbox.getChildren().addAll(nextButton);
		ChoiceDialog<String> difficultDialog=new ChoiceDialog<>(Integer.toString(test.getdifficulty()),difficultyLevel);
		difficultDialog.setTitle("Size Difficulty Level");
		difficultDialog.setHeaderText("The size difficulty level of the maze relates to the size of the maze."+
		" \nThe higher the difficulty, the larger the maze."+
		" \nTotal score will be size difficulty multiplied by time difficulty. The new settings will be implemented in the next new game.");
		difficultDialog.setContentText("Choose your difficulty level (1-20):");
		ChoiceDialog<String> timeDialog=new ChoiceDialog<>(Integer.toString(test.gettimedifficulty()),timeLevel);
		timeDialog.setTitle("Time Difficulty Level");
		timeDialog.setHeaderText("The time difficulty level of the maze relates to the time left on the clock."+
		" \nThe higher the difficulty, the lower the time left on clock."+
		" \nTotal score will be size difficuty multplied by time difficulty. The new settings will be implemented in the next new game.");
		timeDialog.setContentText("Choose your difficulty level (1-20):");
		boolean centerScreen=true;
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){	
				if (test.finished()){
					timeline.pause();
					congratsPane.setVisible(true);
					gridpane.setVisible(false);
				}	
				else if (test.isFail() || test.isDone()){
					// do nothing
				}
				else{
					Rectangle r=new Rectangle(test.getSpaceSize(),test.getSpaceSize());
					r.setFill(test.getVisitColor());
					int col17=gridpane.getColumnIndex(circle);
					int row17=gridpane.getRowIndex(circle);
					if (test.isCorrectPath(test.x(),test.y()) && !test.hasvisited(test.x(),test.y())){
						test.incrementpercentage();
						if (test.getpercentdone()>0.1){
							percentageLabel.setText(String.format("%.1f",test.getpercentdone()*100)+"%");
							percentageBar.setProgress(test.getpercentdone());
						}
						else{
							percentageLabel.setText(String.format("%.2f",test.getpercentdone()*100)+"%");
							percentageBar.setProgress(test.getpercentdone());
						}
					}
					test.setvisited(test.x(),test.y());
					if ((event.getCode()==KeyCode.UP || event.getCode()==KeyCode.W) && test.canup()){	
						if (test.getpathfill()){
							Rectangle s=new Rectangle(test.getSpaceSize(),test.getwall());
							s.setFill(test.getVisitColor());
							gridpane.add(s,col17,row17-1);
						}
						gridpane.setRowIndex(circle,row17-2);
						test.up();
					}
					else if ((event.getCode()==KeyCode.RIGHT || event.getCode()==KeyCode.D) && test.canright()){
						if (test.getpathfill()){
							Rectangle s=new Rectangle(test.getwall(),test.getSpaceSize());
							s.setFill(test.getVisitColor());
							gridpane.add(s,col17+1,row17);
						}
						gridpane.setColumnIndex(circle,col17+2);
						test.right();
					}
					else if ((event.getCode()==KeyCode.DOWN || event.getCode()==KeyCode.S) && test.candown()){
						if (test.getpathfill()){
							Rectangle s=new Rectangle(test.getSpaceSize(),test.getwall());
							s.setFill(test.getVisitColor());
							gridpane.add(s,col17,row17+1);
						}
						gridpane.setRowIndex(circle,row17+2);
						test.down();
					}
					else if ((event.getCode()==KeyCode.LEFT || event.getCode()==KeyCode.A) && test.canleft()){
						if (test.getpathfill()){
							Rectangle s=new Rectangle(test.getwall(),test.getSpaceSize());
							s.setFill(test.getVisitColor());
							gridpane.add(s,col17-1,row17);
						}
						gridpane.setColumnIndex(circle,col17-2);
						test.left();
					}		
					if (!test.hasvisited(col17,row17) && test.getpathfill()){
						gridpane.add(r,col17,row17);
					}
					else{
						gridpane.getChildren().removeAll(r);
					}
					circle.toFront();
					if (test.finished()){
						test.addwin();
						percentageLabel.setText("100%");
						timeline.pause();
						if (!hbox.getChildren().contains(nextButton)) hbox.getChildren().addAll(startButton,nextButton);
						test.setdone();
						congratsPane.setVisible(true);
						borderPane.setRight(showMaze);
						gridpane.setVisible(false);
						nextButton.setVisible(true);
						wins.set(wins.get()+1);
					}						
				}
			}
		});
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (test.getshowpercentage()){
					borderPane.setTop(vbox);
				}
				percentageLabel.setText("0.00%");
				percentageBar.setProgress(0.00);
				if (borderPane.getChildren().contains(showMaze)){
					borderPane.getChildren().removeAll(showMaze);
				}
				else if (borderPane.getChildren().contains(showMazeSolution)){
					borderPane.getChildren().removeAll(showMazeSolution);
				}
				hbox.getChildren().removeAll(nextButton,startButton);
				pausePane.setVisible(false);
				gridpane.setVisible(true);
				failPane.setVisible(false);
				congratsPane.setVisible(false);
				titlescreen.setVisible(false);
				timerLabel.setVisible(true);
				circle.setVisible(true);
				startButton.setDisable(true);
				startButton.setVisible(false);
				gridpane.getChildren().clear();
				createNewMaze(gridpane,test);
				circle.setRadius(test.getSpaceSize()/2);
				stage.centerOnScreen();
				timeSeconds.set(test.starttime());
				gridpane.add(circle,(int)test.getstarting().getX()+1,(int)test.getstarting().getY()+1,1,1);
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>(){
					public void handle(ActionEvent event){
						timeSeconds.set(timeSeconds.get()-0.1);
						timerLabel.setText(String.format("%.1f",Double.valueOf(timeSeconds.get())));
						if (timeSeconds.get()<=0.1){	
							test.setfail();
							test.addlose();
							timeline.stop();							
							circle.setVisible(false);
							timeSeconds.set(0.0);
							borderPane.setRight(showMaze);
							timerLabel.setText(String.format("%.1f",Double.valueOf(timeSeconds.get())));
							startButton.setVisible(true);
							startButton.setDisable(false);
							failPane.setVisible(true);
							gridpane.setVisible(false);
							nextButton.setVisible(true);
							if (!hbox.getChildren().contains(nextButton)) hbox.getChildren().addAll(startButton,nextButton);
						}
					}
				}));
				if (timeline.getKeyFrames().size()>1){
					timeline.getKeyFrames().remove(1);
				}
				timeline.playFromStart();
			}		
		});
		showMaze.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				failPane.setVisible(false);
				congratsPane.setVisible(false);
				gridpane.setVisible(true);
				borderPane.setRight(showMazeSolution);		
			}
		});
		showMazeSolution.setOnAction(new EventHandler<ActionEvent>(){		
			public void handle(ActionEvent event){
				failPane.setVisible(false);
				congratsPane.setVisible(false);
				gridpane.setVisible(true);
				for (int i=0; i<test.width(); i++){
					for (int j=0; j<test.height(); j++){
						if (test.isCorrectPath(i,j)){
							Rectangle r=new Rectangle(test.getSpaceSize(),test.getSpaceSize());
							gridpane.add(r,1+i*2,1+j*2);
						}
					}
				}
			}
		});
		continuousTime.getKeyFrames().add(new KeyFrame(Duration.seconds(0.2), new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				stage.sizeToScene();
				borderPane.toFront();
				if (centerScreen){
					stage.centerOnScreen();
				}
			}
		}));
		continuousTime.playFromStart();
		//.getChildren().addAll(continuousTime);
		newItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				justforFirstStartButton.setVisible(false);
				root.getChildren().remove(justforFirstStartButton);
				if (timeSeconds.get()<test.starttime() && timeSeconds.get()>0.1 && !test.isDone()){
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("New Game Confirmation");
					alert.setHeaderText("Attempting to start a new game");
					alert.setContentText("Are you sure you want to end the current game and start a new game? This will count as a loss in the statistics.");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
						test.addlose();
						startButton.setDisable(false);		
						startButton.fire();
					} 
				}
				else{	
					startButton.setDisable(false);		
					startButton.fire();
				}	
			}
		});
		endItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if (timeSeconds.get()==0){
					
				}
				else if (timeSeconds.get()<test.starttime() && timeSeconds.get()>0.1 && !test.isDone()){
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("End Game Confirmation");
					alert.setHeaderText("Attempting to end the current game");
					alert.setContentText("Are you sure you want to end the current game? This will count as a loss in the statistics.");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){				
						if (pauseItem.isVisible() && !gridpane.isVisible()){
							pauseItem.fire();
						}
						timeSeconds.set(0.0);
						nextButton.setVisible(true);
					} 
					else {			
					}
				}
				else{
					if (pauseItem.isVisible() && !gridpane.isVisible()){
						pauseItem.fire();
					}
					else{
						if (!hbox.getChildren().contains(nextButton)) hbox.getChildren().addAll(nextButton);
						timeSeconds.set(0.0);
						nextButton.setVisible(true);
					}
				}
			}
		});
		exitItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Exit Confirmation");
				alert.setHeaderText("Attempting to Exit the Game");
				alert.setContentText("Are you sure you want to quit the game?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					setpreferences(prefs,test);
					System.exit(0);
				} 
			}
		});
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
				exitItem.fire();
			}
		});
		firstStartButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				startButton.fire();
				firstStartButton.setVisible(false);
				borderPane.setTop(vbox);
				justforFirstStartButton.setTop(null);
				root.getChildren().remove(justforFirstStartButton);
				justforFirstStartButton.setVisible(false);
			}
		});
		pauseItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if (test.finished() || justforFirstStartButton.isVisible()){
					
				}
				else if (gridpane.isVisible() && timeSeconds.get()>0.0 && timeSeconds.get()<test.starttime()){
					timerLabel.setVisible(false);
					pausePane.setVisible(true);
					gridpane.setVisible(false);
					timeline.pause();
				}
				else{
					timerLabel.setVisible(true);
					pausePane.setVisible(false);
					gridpane.setVisible(true);
					timeline.play();
				}		
				stage.centerOnScreen();
			}
		});
		nextButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				newItem.fire();
			}
		});
		difficultyItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Optional<String> result = difficultDialog.showAndWait();
				if (result.isPresent()){					
					test.setdifficulty(Integer.parseInt(result.get()));
				}
			}
		});
		timeDifficultyItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Optional<String> result = timeDialog.showAndWait();
				if (result.isPresent()){					
					test.settimedifficulty(Integer.parseInt(result.get()));
				}
			}
		});
		coloringItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Group roo=new Group();
				Stage okay=new Stage();
				Scene wins=new Scene(roo,400,300);
				okay.setScene(wins);
				GridPane grid=setupcoloringitem(test);
				okay.setTitle("Set Up Colors");
				roo.getChildren().addAll(grid);
				okay.showAndWait();
			}
		});
		gameplayItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				boolean fill=test.getpathfill();
				Group roo=new Group();
				Stage okay=new Stage();
				Scene wins=new Scene(roo,400,500);
				okay.setScene(wins);
				GridPane grid=setupgameplayitem(test,difficultDialog,timeDialog);
				okay.setTitle("Set Up Other Gameplay");
				roo.getChildren().addAll(grid);
				okay.showAndWait();
				if (!test.getshowpercentage()){
					vbox.getChildren().removeAll(percentagebox);
				}
				else{
					vbox.getChildren().removeAll(percentagebox);
					vbox.getChildren().addAll(percentagebox);
				}
				if (!test.getpathfill() && fill){
					redrawMaze(gridpane,test);
					int zz=gridpane.getRowIndex(circle);
					int za=gridpane.getColumnIndex(circle);
					gridpane.getChildren().removeAll(circle);
					gridpane.add(circle,za,zz);
				}
			}
		});
		winLossItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Group roo=new Group();
				Stage okay=new Stage();
				Scene wins=new Scene(roo,400,300);
				okay.setScene(wins);
				GridPane grid=setupwinlossitem(test);
				okay.setTitle("Win/Loss");
				roo.getChildren().addAll(grid);
				okay.showAndWait();
			}
		});
		miscellaneousItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Group roo=new Group();
				Stage okay=new Stage();
				Scene wins=new Scene(roo,500,300);
				okay.setScene(wins);
				GridPane grid=setupmiscallaneousitem(test);
				okay.setTitle("Miscellaneous");
				roo.getChildren().addAll(grid);
				okay.showAndWait();
			}
		});
		instructionItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Group roo=new Group();
				Stage okay=new Stage();
				Scene wins=new Scene(roo,500,300);
				okay.setScene(wins);
				Label area=setupinstructionitem(test);
				okay.setTitle("Instructions");						
				ScrollPane sp=new ScrollPane();
				sp.setHbarPolicy(ScrollBarPolicy.NEVER);
				sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
				sp.setContent(area);
				roo.getChildren().addAll(sp);
				okay.showAndWait();
			}
		});
		mazegeneratorItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				ArrayList<GridPane> grid=setupmazegenerator(test);
				IntegerProperty maxed=new SimpleIntegerProperty(grid.size());
				IntegerProperty currentproperty=new SimpleIntegerProperty(0);
				Timeline gridline=new Timeline();
				gridline.setCycleCount(grid.size());
				gridline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.2), new EventHandler<ActionEvent>(){
					public void handle(ActionEvent event){
						Group roo=new Group();
						Stage okay=new Stage();
						Scene wins=new Scene(roo,600,500);
						okay.setScene(wins);
						okay.setTitle("Maze Generator "+currentproperty.get());
						roo.getChildren().addAll(grid.get(currentproperty.get()));
						currentproperty.set(currentproperty.get()+1);
						okay.show();
					}
				}));
				gridline.play();
			}
		});
		borderPane.toFront();
		root.getChildren().addAll(justforFirstStartButton,congratsPane,failPane);
        stage.setScene(scene);
        stage.show();
		borderPane.toFront();
	}	
	
	
	public void createNewMaze(GridPane gridpane, Maze test){
		test.setMazeAgain();
		redrawMaze(gridpane,test);
		test.setpoint((int)test.getstarting().getX(),(int)test.getstarting().getY());
	}
	public void redrawMaze(GridPane gridpane, Maze test){
		int wallthickness=test.getwall();
		int mazewidth=test.width();
		int mazeheight=test.height();
		int spacesize=test.getSpaceSize();
		Point2D starting=test.getstarting();
		Point2D destination=test.getdestination();		
		int insetup=40;
		int insetright=40;
		int insetdown=40;
		int insetleft=40;	
		Color background=test.getBackgroundColor();
		Color space=test.getSpaceColor();
		Color wallcol=test.getWallColor();
		Color open=test.getOpenColor();
		Color currentspace=test.getCurrentColor();
		Color visitcolor=test.getVisitColor();
		for (int i=0; i<mazewidth; i++){
			for (int j=0; j<mazeheight; j++){				
				Rectangle r=new Rectangle(spacesize,spacesize);
				if (i==starting.getX() && j==starting.getY()){
					r.setFill(Color.GRAY);
				}
				else if (i==destination.getX() && j==destination.getY()){
					r.setFill(Color.GRAY);
				}
				else{
					r.setFill(space);
				}
				Rectangle wall=new Rectangle(wallthickness,spacesize);
				if (test.west(i,j)){
					wall.setFill(wallcol);
				}
				else{
					wall.setFill(open);
				}
				if (i<mazewidth && j<mazeheight) gridpane.add(r,1+i*2,1+j*2);
				if (j<mazeheight) gridpane.add(wall,i*2,1+j*2);
			}
			
			for (int j=0; j<mazeheight; j++){
				Rectangle wall=new Rectangle(spacesize,wallthickness);						
				Rectangle w2=new Rectangle(wallthickness,wallthickness);
				if (test.north(i,j)){
					wall.setFill(wallcol);
				}
				else{
					wall.setFill(open);
				}
				w2.setFill(wallcol);
				if (i<mazewidth) gridpane.add(wall,1+i*2,j*2);
				gridpane.add(w2,i*2,j*2);
			}
			Rectangle well=new Rectangle(spacesize,wallthickness);
			Rectangle well2=new Rectangle(wallthickness,wallthickness);
			well.setFill(wallcol);
			well2.setFill(wallcol);
			gridpane.add(well,1+i*2,mazeheight*2);
			gridpane.add(well2,i*2,mazeheight*2);
		}
		for (int i=0; i<mazeheight; i++){
			Rectangle well=new Rectangle(wallthickness,spacesize);
			Rectangle well2=new Rectangle(wallthickness,wallthickness);
			well.setFill(wallcol);
			well2.setFill(wallcol);
			gridpane.add(well,mazewidth*2,1+i*2);
			gridpane.add(well2,mazewidth*2,i*2);
		}
		int preferredX=mazewidth*(wallthickness+spacesize);
		int preferredY=mazeheight*(wallthickness+spacesize);
		gridpane.setPrefSize(preferredX,preferredY);
		//gridpane.resize(mazewidth*(wallthickness+spacesize),mazeheight*(wallthickness+spacesize));
	}
	public GridPane setupwinlossitem(Maze test){
		GridPane grid=new GridPane();
		Text test11=new Text("Total Games Played ");
		Text test21=new Text("Wins");
		Text test31=new Text("Losses");
		Text test41=new Text("Win Percentage");
		Text test51=new Text("Average Size Level of Wins: ");
		Text test61=new Text("Average Time Level of Wins: ");
		Text test71=new Text("Average Size Level of Losses: ");
		Text test81=new Text("Average Time Level of Losses: ");
		Text test12=new Text(Integer.toString(test.gettotalgames()));
		Text test22=new Text(Integer.toString(test.getwincount()));
		Text test32=new Text(Integer.toString(test.getlosecount()));
		Text test42=new Text(String.format("%.1f",test.getwinpercentage())+"%");
		Text test52=new Text(String.format("%.2f",test.getnumberwinssize()));
		Text test62=new Text(String.format("%.2f",test.getnumberwinstime()));
		Text test72=new Text(String.format("%.2f",test.getnumberlosesize()));
		Text test82=new Text(String.format("%.2f",test.getnumberlosetime()));
		test11.setStyle("-fx-font: 20 arial;");
		test21.setStyle("-fx-font: 20 arial;");
		test31.setStyle("-fx-font: 20 arial;");
		test41.setStyle("-fx-font: 20 arial;");
		test51.setStyle("-fx-font: 20 arial;");
		test61.setStyle("-fx-font: 20 arial;");
		test71.setStyle("-fx-font: 20 arial;");
		test81.setStyle("-fx-font: 20 arial;");
		test12.setStyle("-fx-font: 20 arial;");
		test22.setStyle("-fx-font: 20 arial;");
		test32.setStyle("-fx-font: 20 arial;");
		test42.setStyle("-fx-font: 20 arial;");
		test52.setStyle("-fx-font: 20 arial;");
		test62.setStyle("-fx-font: 20 arial;");
		test72.setStyle("-fx-font: 20 arial;");
		test82.setStyle("-fx-font: 20 arial;");
		grid.add(test11,0,0);
		grid.add(test21,0,1);
		grid.add(test31,0,2);
		grid.add(test41,0,3);
		grid.add(test51,0,4);
		grid.add(test61,0,5);
		grid.add(test71,0,6);
		grid.add(test81,0,7);
		grid.add(test12,1,0);
		grid.add(test22,1,1);
		grid.add(test32,1,2);
		grid.add(test42,1,3);
		grid.add(test52,1,4);
		grid.add(test62,1,5);
		grid.add(test72,1,6);
		grid.add(test82,1,7);
		return grid;
	}
	public GridPane setupmiscallaneousitem(Maze test){
		GridPane grid=new GridPane();
		Text test11=new Text("Spaces tried to move: ");
		Text test21=new Text("Spaces actually moved: ");
		Text test31=new Text("Spaces tried to move without movement: ");
		Text test41=new Text("Spaces revisited or incorrect spaces: ");
		Text test51=new Text("Optimal number of spaces to move: ");
		Text test61=new Text("Percent optimal spaces of spaces tried to move: ");
		Text test12=new Text(Integer.toString(test.getspacestriedtomove()));
		Text test22=new Text(Integer.toString(test.gettotalspacesmoved()));
		Text test32=new Text(Integer.toString(test.getspacestriedtomove()-test.gettotalspacesmoved()));
		Text test42=new Text(Integer.toString(test.gettotalspacesmoved()-test.getoptimalspacesmoved()));
		Text test52=new Text(Integer.toString(test.getoptimalspacesmoved()));
		Text test62=new Text(String.format("%.1f",100.00*test.getoptimalspacesmoved()/test.getspacestriedtomove())+"%");
		test11.setStyle("-fx-font: 18 arial;");
		test21.setStyle("-fx-font: 18 arial;");
		test31.setStyle("-fx-font: 18 arial;");
		test41.setStyle("-fx-font: 18 arial;");
		test51.setStyle("-fx-font: 18 arial;");
		test61.setStyle("-fx-font: 18 arial;");
		test12.setStyle("-fx-font: 18 arial;");
		test22.setStyle("-fx-font: 18 arial;");
		test32.setStyle("-fx-font: 18 arial;");
		test42.setStyle("-fx-font: 18 arial;");
		test52.setStyle("-fx-font: 18 arial;");
		test62.setStyle("-fx-font: 18 arial;");
		grid.add(test11,0,0);
		grid.add(test21,0,1);
		grid.add(test31,0,2);
		grid.add(test41,0,3);
		grid.add(test51,0,4);
		grid.add(test61,0,5);
		grid.add(test12,1,0);
		grid.add(test22,1,1);
		grid.add(test32,1,2);
		grid.add(test42,1,3);
		grid.add(test52,1,4);
		grid.add(test62,1,5);
		return grid;
	}
	public GridPane setupcoloringitem(Maze test){
		GridPane grid=new GridPane();
		return grid;
	}
	public GridPane setupgameplayitem(Maze test, ChoiceDialog<String> difficultDialog, ChoiceDialog<String> timeDialog){
		GridPane grid=new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(4);
		Slider slider = new Slider(1,20,test.getdifficulty());
		Text test00=new Text("Size Level");
		slider.setMaxWidth(200);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(20);
		slider.setMinorTickCount(20);
		slider.setBlockIncrement(1);
		grid.add(test00,0,0);
		grid.add(slider,1,0,6,1);
		Label sliderValue=new Label(String.format("%.0f",slider.getValue()));
		grid.add(sliderValue,7,0);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    sliderValue.setText(String.format("%.0f", new_val));
					difficultDialog.setSelectedItem(String.valueOf(new_val.intValue()));
					test.setdifficulty(new_val.intValue());
            }
        });
		Slider slider2=new Slider(1,20,test.gettimedifficulty());
		Text test01=new Text("Time Level");
		GridPane.setHgrow(slider2,Priority.ALWAYS);
		slider2.setMaxWidth(200);
		slider2.setShowTickLabels(true);
		slider2.setShowTickMarks(true);
		slider2.setMajorTickUnit(20);
		slider2.setMinorTickCount(20);
		slider2.setBlockIncrement(1);
		grid.add(test01,0,2);
		grid.add(slider2,1,2,6,1);
		Label slider2Value=new Label(String.format("%.0f",slider2.getValue()));
		grid.add(slider2Value,7,2);
		slider2.valueProperty().addListener(new ChangeListener<Number>()  {
			public void changed(ObservableValue<? extends Number> ov, 
				Number old_val, Number new_val){
					slider2Value.setText(String.format("%.0f",new_val));
					timeDialog.setSelectedItem(String.valueOf(new_val.intValue()));
					test.settimedifficulty(new_val.intValue());
				}
		});
		CheckBox cb1=new CheckBox("Always End at Bottom Right Corner");
		CheckBox cb2=new CheckBox("Adjust Time for Length of Maze (recommended)");
		CheckBox cb3=new CheckBox("Include Horizontal Laser Beams (not recommended)");
		CheckBox cb4=new CheckBox("Include Vertical Laser Beams (not recommended)");
		CheckBox cb5=new CheckBox("Turn On Path Fill");
		CheckBox cb6=new CheckBox("Save Settings for Next Time");
		CheckBox cb7=new CheckBox("Save Statistics for Next Time \n(WARNING: UNSELECTING WILL DELETE ALL DATA)");
		CheckBox cb8=new CheckBox("Show Complete Percentage");
		cb3.setIndeterminate(true);
		cb4.setIndeterminate(true);
		cb3.setDisable(true);
		cb4.setDisable(true);
		cb1.setSelected(test.getcorner());
		cb2.setSelected(test.gettimeadjust());
		cb3.setSelected(test.gethlazer());
		cb4.setSelected(test.getvlazer());
		cb5.setSelected(test.getpathfill());
		cb6.setSelected(test.getsavesettings());
		cb7.setSelected(test.getsavestats());
		cb8.setSelected(test.getshowpercentage());
		cb1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                test.setcorner(new_val);
			}
		});
		cb2.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                test.settimeadjust(new_val);
			}
		});
		cb3.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                test.sethlazer(new_val);
			}
		});
		cb4.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                test.setvlazer(new_val);
			}
		});
		cb5.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                test.setpathfill(new_val);
			}
		});
		cb6.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                test.setsavesettings(new_val);
			}
		});
		cb7.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                test.setsavestats(new_val);
			}
		});
		cb8.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                test.setshowpercentage(new_val);
			}
		});
		grid.add(cb1,0,5,4,1);
		grid.add(cb2,0,6,4,1);
		grid.add(cb3,0,7,4,1);
		grid.add(cb4,0,8,4,1);
		grid.add(cb5,0,9,4,1);
		grid.add(cb6,0,10,4,1);
		grid.add(cb7,0,11,4,1);
		grid.add(cb8,0,12,4,1);
		final ToggleGroup group = new ToggleGroup();
		Text text99=new Text("\n");
		grid.add(text99,0,13);
		Text text01=new Text("Space Thickness");
		RadioButton rb1=new RadioButton("Thin");
		RadioButton rb2=new RadioButton("Normal");
		RadioButton rb3=new RadioButton("Thick");
		RadioButton rb4=new RadioButton("Very Thin");	
		RadioButton rb5=new RadioButton("Thin");
		RadioButton rb6=new RadioButton("Normal");
		RadioButton rb7=new RadioButton("Thick");
		RadioButton rb8=new RadioButton("Very Thick");
		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);
		rb3.setToggleGroup(group);
		rb4.setToggleGroup(group);
		int spacethickness=test.getSpaceSize();
		//12 space //3 wall
		switch (spacethickness){
			case 10: rb1.setSelected(true); break;
			case 12: rb2.setSelected(true); break;
			case 14: rb3.setSelected(true); break;
			case 8: rb4.setSelected(true); break;
			default: rb2.setSelected(true); break;
		}
		rb1.requestFocus();
		rb2.requestFocus();
		rb3.requestFocus();
		rb4.requestFocus();
		rb5.requestFocus();
		rb6.requestFocus();
		rb7.requestFocus();
		rb8.requestFocus();
		rb1.setUserData("Thin"); // Space Thin
		rb2.setUserData("Normal"); // Space Normal
		rb3.setUserData("Thick"); // Space Thick
		rb4.setUserData("Square"); // Space Square
		rb5.setUserData("Thin"); // Wall Thin
		rb6.setUserData("Normal"); // Wall Normal
		rb7.setUserData("Thick"); // Wall Thick
		rb8.setUserData("Square"); // Wall Square
		grid.add(text01,0,14);
		grid.add(rb1,2,14);
		grid.add(rb2,3,14);
		grid.add(rb3,4,14);
		grid.add(rb4,1,14);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
			Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle()!=null){
					String k=group.getSelectedToggle().getUserData().toString();
					switch (k){
						case "Thin":   test.setSpaceSize(10); break;
						case "Normal": test.setSpaceSize(12); break;
						case "Thick":  test.setSpaceSize(14); break;
						case "Square": test.setSpaceSize(8); break;
					}
				}
			}
		});
		final ToggleGroup group2=new ToggleGroup();
		Text text02=new Text("Wall Thickness");
	
		rb5.setToggleGroup(group2);
		rb6.setToggleGroup(group2);
		rb7.setToggleGroup(group2);
		rb8.setToggleGroup(group2);
		int wallthickness=test.getwall();
		switch (wallthickness){
			case 2: rb5.setSelected(true); break;
			case 3: rb6.setSelected(true); break;
			case 4: rb7.setSelected(true); break;
			case 8: rb8.setSelected(true); break;
			default: rb6.setSelected(true); break;
		}
		grid.add(text02,0,15);
		grid.add(rb5,1,15);
		grid.add(rb6,2,15);
		grid.add(rb7,3,15);
		grid.add(rb8,4,15);
		group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
			Toggle old_toggle, Toggle new_toggle) {
				if (group2.getSelectedToggle()!=null){
					String k=group2.getSelectedToggle().getUserData().toString();
					switch (k){
						case "Thin": test.setwall(2); break;
						case "Normal": test.setwall(3); break;
						case "Thick": test.setwall(4); break;
						case "Square": test.setwall(8); break;
					}
				}
			}
		});
		return grid;
	}
	public Label setupinstructionitem(Maze test){
		Label textarea=new Label();
		return textarea;
	}
	public ArrayList<GridPane> setupmazegenerator(Maze test){
		ArrayList<GridPane> grid=test.specialmazegenerator();
		return grid;
	}
	public Maze getpreferences(Preferences pref){
		//Settings
		int size=prefs.getInt("Size",1);	//	Size (int 1-20)
		int time=prefs.getInt("Time",1);	//	Time (int 1-20)
		boolean corner=prefs.getBoolean("Corner",true);//	Corner (boolean)
		boolean timeadjust=prefs.getBoolean("Timeadjust",true);//		Timeadjust (boolean)
		boolean hbeam=prefs.getBoolean("HBeam",false); //		Hbeam (boolean)
		boolean vbeam=prefs.getBoolean("VBeam",false);		//Vbeam (boolean)
		boolean pathfill=prefs.getBoolean("Pathfill",true);	//	Pathfill (boolean)
		boolean savesettings=prefs.getBoolean("Savesettings",false);//		Savesettings (boolean)
		boolean savestats=prefs.getBoolean("Savestats",true);//		Savestats (boolean)
		boolean percentage=prefs.getBoolean("Percentage",true);	//	Percentage (boolean)
		int spacethick=prefs.getInt("Spacethick",12);	//	Spacethick (int)
		int wallthick=prefs.getInt("Wallthick",3);//	Wallthick (int)
		//Statistics:
		int totalgames=prefs.getInt("Totalgames",0);//		Totalgames (int)
		int wins=prefs.getInt("Wins",0);	//	Wins (int)
		int losses=prefs.getInt("Losses",0);	//	Losses (int)
		int sizewins=prefs.getInt("Sizewins",0);	//	Sizewins (int)
		int timewins=prefs.getInt("Timewins",0);//		Timewins (int)
		int sizeloss=prefs.getInt("Sizeloss",0);//		Sizeloss (int)
		int timeloss=prefs.getInt("Timeloss",0);	//	Timeloss (int)
		int spacesactuallymoved=prefs.getInt("Spacesactuallymoved",0);//		Spacesactuallymoved (int)
		int spacestriedmoved=prefs.getInt("Spacestriedmoved",0);//		Spacestriedmoved (int)
		int optimalspaces=prefs.getInt("Optimalspaces",0);		//Optimalspaces (int)
		// Colors
		Maze test=new Maze(size,time,corner,timeadjust,hbeam,vbeam,pathfill,savesettings,savestats,percentage,spacethick,wallthick,totalgames,
		wins,losses,sizewins,timewins,sizeloss,timeloss,spacesactuallymoved,spacestriedmoved,optimalspaces);
		return test;
	}
	public void setpreferences(Preferences pref, Maze test){
		if (test.getsavesettings()){
			prefs.putInt("Size",test.getdifficulty()); // Size (int 1-20)
			prefs.putInt("Time",test.gettimedifficulty()); // Time (int 1-20)
			prefs.putBoolean("Corner",test.getcorner()); // Corner (boolean)
			prefs.putBoolean("Timeadjust",test.gettimeadjust()); // Timeadjust (Boolean)
			prefs.putBoolean("HBeam",test.gethlazer()); // Hbeam (boolean)
			prefs.putBoolean("VBeam",test.getvlazer()); // Vbeam (boolean)
			prefs.putBoolean("Pathfill",test.getpathfill()); // Pathfill (boolean)
			prefs.putBoolean("Savesettings",test.getsavesettings()); // Savesettings (Boolean)
			prefs.putBoolean("Savestats",test.getsavestats()); // Savestats (Boolean)
			prefs.putBoolean("Percentage",test.getshowpercentage()); // Percentage (boolean)
			prefs.putInt("Spacethick",test.getSpaceSize()); // Spacethick (int)
			prefs.putInt("Wallthick",test.getwall()); // Wallthick (int)
		}
		else{
			prefs.remove("Size");
			prefs.remove("Time");
			prefs.remove("Corner");
			prefs.remove("Timeadjust");
			prefs.remove("VBeam");
			prefs.remove("HBeam");
			prefs.remove("Pathfill");
			prefs.remove("Savesettings");
			prefs.remove("Savestats");
			prefs.remove("Percentage");
			prefs.remove("Spacethick");
			prefs.remove("Wallthick");
		}
		if (test.getsavestats()){
			prefs.putInt("Totalgames",test.gettotalgames()); // Totalagaames (int)
			prefs.putInt("Wins", test.getwincount()); // Wins (int)
			prefs.putInt("Losses",test.getlosecount()); // Losses (int)
			prefs.putInt("Sizewins",test.getwinsize()); // Sizewins (int)
			prefs.putInt("Timewins",test.getwintime()); // Timewins (int)
			prefs.putInt("Sizeloss",test.getlosesize()); // Sizeloss (int)
			prefs.putInt("Timeloss",test.getlosetime()); // Timeloss (int)
			prefs.putInt("Spacesactuallymoved",test.gettotalspacesmoved()); //Spacesactuallymoved (int)
			prefs.putInt("Spacestriedmoved",test.getspacestriedtomove()); // Spacestriedmoved (int) 
			prefs.putInt("Optimalspaces",test.getoptimalspacesmoved()); // Optimalspaces (int)
		}
		else{
			prefs.remove("Totalgames");
			prefs.remove("Wins");
			prefs.remove("Losses");
			prefs.remove("Sizewins");
			prefs.remove("Timewins");
			prefs.remove("Sizeloss");
			prefs.remove("Timeloss");
			prefs.remove("Spacesactuallymoved");
			prefs.remove("Spacestriedmoved");
			prefs.remove("Optimalspaces");
		}
	}
}
