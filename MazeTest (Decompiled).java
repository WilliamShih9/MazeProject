import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Priority;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Slider;
import javafx.geometry.Point2D;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.stage.WindowEvent;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import java.util.Collection;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;
import javafx.stage.Stage;
import java.util.prefs.Preferences;
import javafx.application.Application;

public class MazeTest extends Application
{
    private Preferences prefs;
    
    public static void main(final String[] args) {
        launch(args);
    }
    
    public void start(final Stage stage) {
        this.prefs = Preferences.userRoot().node(this.getClass().getName());
        final Maze test = this.getpreferences(this.prefs);
        final List<String> difficultyLevel = new ArrayList<String>();
        for (int i = 1; i <= 20; ++i) {
            final String a = Integer.toString(i);
            difficultyLevel.add(a);
        }
        final List<String> timeLevel = new ArrayList<String>();
        for (int j = 1; j <= 20; ++j) {
            final String a2 = Integer.toString(j);
            timeLevel.add(a2);
        }
        final IntegerProperty wins = (IntegerProperty)new SimpleIntegerProperty(0);
        final IntegerProperty losses = (IntegerProperty)new SimpleIntegerProperty(0);
        final Text congratulations = new Text("\nCongrats!\n You Win!");
        final Text failure = new Text("\nFail!\n You Lose!");
        congratulations.setFont(new Font("Arial", 50.0));
        congratulations.setFill((Paint)Color.GREEN);
        failure.setFont(new Font("Arial", 50.0));
        failure.setFill((Paint)Color.RED);
        final GridPane congratsPane = new GridPane();
        final GridPane failPane = new GridPane();
        congratsPane.add((Node)congratulations, 0, 0);
        failPane.add((Node)failure, 0, 0);
        congratsPane.setVisible(false);
        failPane.setVisible(false);
        final HBox hbox = new HBox();
        hbox.setLayoutX(100.0);
        hbox.setLayoutY(200.0);
        final Circle circle = new Circle(1.0, (Paint)test.getCircleColor());
        final GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets((double)test.insetup(), (double)test.insetright(), (double)test.insetdown(), (double)test.insetleft()));
        final Group root = new Group();
        gridpane.add((Node)circle, 1, 1);
        circle.setVisible(false);
        final DoubleProperty starttime = (DoubleProperty)new SimpleDoubleProperty(test.gettime());
        final DoubleProperty timeSeconds = (DoubleProperty)new SimpleDoubleProperty(test.starttime());
        final Label timerLabel = new Label();
        final Label percentageLabel = new Label();
        final ProgressBar percentageBar = new ProgressBar(0.0);
        percentageLabel.setText("0.00%");
        percentageLabel.setFont(new Font("Arial", 20.0));
        percentageLabel.setTextFill((Paint)test.getTimerColor());
        final HBox percentagebox = new HBox(8.0);
        percentagebox.setMaxWidth(gridpane.getMaxWidth());
        percentageBar.setMinWidth(gridpane.getMaxWidth());
        percentagebox.getChildren().addAll((Object[])new Node[] { (Node)percentageLabel, (Node)percentageBar });
        timerLabel.setText(String.format("%.1f", timeSeconds.get()));
        timerLabel.setTextFill((Paint)test.getTimerColor());
        final Font titleFont = Font.loadFont(this.getClass().getResourceAsStream("digital-7.ttf"), 50.0);
        timerLabel.setFont(titleFont);
        timerLabel.setVisible(false);
        final Timeline timeline = new Timeline();
        final Timeline continuousTime = new Timeline();
        continuousTime.setCycleCount(-1);
        final MenuBar menubar = new MenuBar();
        final Menu fileMenu = new Menu("File");
        final Menu settingMenu = new Menu("Settings");
        final MenuItem newItem = new MenuItem("New");
        final MenuItem exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        timeline.setCycleCount(-1);
        final Button startButton = new Button("Start");
        startButton.setVisible(false);
        startButton.setFont(new Font("Arial", 20.0));
        final Button firstStartButton = new Button("Press Play");
        firstStartButton.setFont(new Font("Arial", 40.0));
        BorderPane.setAlignment((Node)firstStartButton, Pos.BOTTOM_CENTER);
        final Text titlescreen = new Text(0.0, 100.0, "AP Computer Science Simple Maze Game");
        titlescreen.setVisible(true);
        titlescreen.setFont(new Font("Arial", 40.0));
        final BorderPane justforFirstStartButton = new BorderPane((Node)titlescreen);
        justforFirstStartButton.setBottom((Node)firstStartButton);
        final MenuItem pauseItem = new MenuItem("Pause");
        final GridPane pausePane = new GridPane();
        pausePane.setPadding(new Insets((double)test.insetup(), (double)test.insetright(), (double)test.insetdown(), (double)test.insetleft()));
        pauseItem.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        final Text paused = new Text("\n   Paused\n");
        paused.setFont(new Font("Arial", 80.0));
        final Text pauseds = new Text("Press Ctrl+P to \n continue");
        pauseds.setFont(new Font("Arial", 60.0));
        pausePane.add((Node)paused, 1, 1, 2, 1);
        pausePane.add((Node)pauseds, 1, 2, 1, 2);
        pausePane.setVisible(false);
        final Menu statisticMenu = new Menu("Statistics");
        final MenuItem winLossItem = new MenuItem("Wins/Losses");
        final MenuItem miscellaneousItem = new MenuItem("Miscellaneous");
        final MenuItem difficultyItem = new MenuItem("Set Maze Size Difficulty");
        final MenuItem timeDifficultyItem = new MenuItem("Set Time Difficulty");
        final MenuItem gameplayItem = new MenuItem("Set Up Gameplay/Difficulty");
        final Menu helpMenu = new Menu("Help");
        final MenuItem mazegeneratorItem = new MenuItem("See Maze Generator");
        final MenuItem endItem = new MenuItem("End Current Game");
        endItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        final MenuItem instructionItem = new MenuItem("Instructions");
        helpMenu.getItems().addAll((Object[])new MenuItem[] { instructionItem, mazegeneratorItem });
        fileMenu.getItems().addAll((Object[])new MenuItem[] { newItem, pauseItem, endItem, exitItem });
        settingMenu.getItems().addAll((Object[])new MenuItem[] { difficultyItem, timeDifficultyItem, gameplayItem });
        statisticMenu.getItems().addAll((Object[])new MenuItem[] { winLossItem, miscellaneousItem });
        menubar.getMenus().addAll((Object[])new Menu[] { fileMenu, settingMenu, statisticMenu, helpMenu });
        final BorderPane borderPane = new BorderPane((Node)gridpane);
        final VBox vbox = new VBox(8.0);
        vbox.getChildren().addAll((Object[])new Node[] { (Node)menubar });
        if (test.getshowpercentage()) {
            vbox.getChildren().addAll((Object[])new Node[] { (Node)percentagebox });
        }
        borderPane.setTop((Node)vbox);
        borderPane.setBottom((Node)hbox);
        justforFirstStartButton.setTop((Node)vbox);
        root.getChildren().addAll((Object[])new Node[] { (Node)borderPane, (Node)pausePane });
        stage.setResizable(true);
        stage.setTitle("APCS Maze Game");
        final Scene scene = new Scene((Parent)root, (Paint)Color.WHITE);
        hbox.getChildren().addAll((Object[])new Node[] { (Node)startButton, (Node)timerLabel });
        borderPane.toFront();
        final Button nextButton = new Button("Next");
        final Button showMaze = new Button("Show Maze");
        final Button showMazeSolution = new Button("Show Optimal Maze Path");
        nextButton.setFont(new Font("Arial", 20.0));
        nextButton.setVisible(false);
        hbox.getChildren().addAll((Object[])new Node[] { (Node)nextButton });
        final ChoiceDialog<String> difficultDialog = (ChoiceDialog<String>)new ChoiceDialog((Object)Integer.toString(test.getdifficulty()), (Collection)difficultyLevel);
        difficultDialog.setTitle("Size Difficulty Level");
        difficultDialog.setHeaderText("The size difficulty level of the maze relates to the size of the maze. \nThe higher the difficulty, the larger the maze. \nTotal score will be size difficulty multiplied by time difficulty. The new settings will be implemented in the next new game.");
        difficultDialog.setContentText("Choose your difficulty level (1-20):");
        final ChoiceDialog<String> timeDialog = (ChoiceDialog<String>)new ChoiceDialog((Object)Integer.toString(test.gettimedifficulty()), (Collection)timeLevel);
        timeDialog.setTitle("Time Difficulty Level");
        timeDialog.setHeaderText("The time difficulty level of the maze relates to the time left on the clock. \nThe higher the difficulty, the lower the time left on clock. \nTotal score will be size difficuty multplied by time difficulty. The new settings will be implemented in the next new game.");
        timeDialog.setContentText("Choose your difficulty level (1-20):");
        final boolean centerScreen = true;
        scene.setOnKeyPressed((EventHandler)new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent event) {
                if (test.finished()) {
                    timeline.pause();
                    congratsPane.setVisible(true);
                    gridpane.setVisible(false);
                }
                else if (!test.isFail()) {
                    if (!test.isDone()) {
                        final Rectangle r = new Rectangle((double)test.getSpaceSize(), (double)test.getSpaceSize());
                        r.setFill((Paint)test.getVisitColor());
                        final GridPane val$gridpane = gridpane;
                        final int col17 = GridPane.getColumnIndex((Node)circle);
                        final GridPane val$gridpane2 = gridpane;
                        final int row17 = GridPane.getRowIndex((Node)circle);
                        if (test.isCorrectPath(test.x(), test.y()) && !test.hasvisited(test.x(), test.y())) {
                            test.incrementpercentage();
                            if (test.getpercentdone() > 0.1) {
                                percentageLabel.setText(String.format("%.1f", test.getpercentdone() * 100.0) + "%");
                                percentageBar.setProgress(test.getpercentdone());
                            }
                            else {
                                percentageLabel.setText(String.format("%.2f", test.getpercentdone() * 100.0) + "%");
                                percentageBar.setProgress(test.getpercentdone());
                            }
                        }
                        test.setvisited(test.x(), test.y());
                        if ((event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) && test.canup()) {
                            if (test.getpathfill()) {
                                final Rectangle s = new Rectangle((double)test.getSpaceSize(), (double)test.getwall());
                                s.setFill((Paint)test.getVisitColor());
                                gridpane.add((Node)s, col17, row17 - 1);
                            }
                            final GridPane val$gridpane3 = gridpane;
                            GridPane.setRowIndex((Node)circle, Integer.valueOf(row17 - 2));
                            test.up();
                        }
                        else if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) && test.canright()) {
                            if (test.getpathfill()) {
                                final Rectangle s = new Rectangle((double)test.getwall(), (double)test.getSpaceSize());
                                s.setFill((Paint)test.getVisitColor());
                                gridpane.add((Node)s, col17 + 1, row17);
                            }
                            final GridPane val$gridpane4 = gridpane;
                            GridPane.setColumnIndex((Node)circle, Integer.valueOf(col17 + 2));
                            test.right();
                        }
                        else if ((event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) && test.candown()) {
                            if (test.getpathfill()) {
                                final Rectangle s = new Rectangle((double)test.getSpaceSize(), (double)test.getwall());
                                s.setFill((Paint)test.getVisitColor());
                                gridpane.add((Node)s, col17, row17 + 1);
                            }
                            final GridPane val$gridpane5 = gridpane;
                            GridPane.setRowIndex((Node)circle, Integer.valueOf(row17 + 2));
                            test.down();
                        }
                        else if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) && test.canleft()) {
                            if (test.getpathfill()) {
                                final Rectangle s = new Rectangle((double)test.getwall(), (double)test.getSpaceSize());
                                s.setFill((Paint)test.getVisitColor());
                                gridpane.add((Node)s, col17 - 1, row17);
                            }
                            final GridPane val$gridpane6 = gridpane;
                            GridPane.setColumnIndex((Node)circle, Integer.valueOf(col17 - 2));
                            test.left();
                        }
                        if (!test.hasvisited(col17, row17) && test.getpathfill()) {
                            gridpane.add((Node)r, col17, row17);
                        }
                        else {
                            gridpane.getChildren().removeAll((Object[])new Node[] { (Node)r });
                        }
                        circle.toFront();
                        if (test.finished()) {
                            test.addwin();
                            percentageLabel.setText("100%");
                            timeline.pause();
                            if (!hbox.getChildren().contains((Object)nextButton)) {
                                hbox.getChildren().addAll((Object[])new Node[] { (Node)startButton, (Node)nextButton });
                            }
                            test.setdone();
                            congratsPane.setVisible(true);
                            borderPane.setRight((Node)showMaze);
                            gridpane.setVisible(false);
                            nextButton.setVisible(true);
                            wins.set(wins.get() + 1);
                        }
                    }
                }
            }
        });
        startButton.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent e) {
                if (test.getshowpercentage()) {
                    borderPane.setTop((Node)vbox);
                }
                percentageLabel.setText("0.00%");
                percentageBar.setProgress(0.0);
                if (borderPane.getChildren().contains((Object)showMaze)) {
                    borderPane.getChildren().removeAll((Object[])new Node[] { (Node)showMaze });
                }
                else if (borderPane.getChildren().contains((Object)showMazeSolution)) {
                    borderPane.getChildren().removeAll((Object[])new Node[] { (Node)showMazeSolution });
                }
                hbox.getChildren().removeAll((Object[])new Node[] { (Node)nextButton, (Node)startButton });
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
                MazeTest.this.createNewMaze(gridpane, test);
                circle.setRadius((double)(test.getSpaceSize() / 2));
                stage.centerOnScreen();
                timeSeconds.set(test.starttime());
                gridpane.add((Node)circle, (int)test.getstarting().getX() + 1, (int)test.getstarting().getY() + 1, 1, 1);
                timeline.getKeyFrames().add((Object)new KeyFrame(Duration.seconds(0.1), (EventHandler)new EventHandler<ActionEvent>() {
                    public void handle(final ActionEvent event) {
                        timeSeconds.set(timeSeconds.get() - 0.1);
                        timerLabel.setText(String.format("%.1f", timeSeconds.get()));
                        if (timeSeconds.get() <= 0.1) {
                            test.setfail();
                            test.addlose();
                            timeline.stop();
                            circle.setVisible(false);
                            timeSeconds.set(0.0);
                            borderPane.setRight((Node)showMaze);
                            timerLabel.setText(String.format("%.1f", timeSeconds.get()));
                            startButton.setVisible(true);
                            startButton.setDisable(false);
                            failPane.setVisible(true);
                            gridpane.setVisible(false);
                            nextButton.setVisible(true);
                            if (!hbox.getChildren().contains((Object)nextButton)) {
                                hbox.getChildren().addAll((Object[])new Node[] { (Node)startButton, (Node)nextButton });
                            }
                        }
                    }
                }, new KeyValue[0]));
                if (timeline.getKeyFrames().size() > 1) {
                    timeline.getKeyFrames().remove(1);
                }
                timeline.playFromStart();
            }
        });
        showMaze.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                failPane.setVisible(false);
                congratsPane.setVisible(false);
                gridpane.setVisible(true);
                borderPane.setRight((Node)showMazeSolution);
            }
        });
        showMazeSolution.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                failPane.setVisible(false);
                congratsPane.setVisible(false);
                gridpane.setVisible(true);
                for (int i = 0; i < test.width(); ++i) {
                    for (int j = 0; j < test.height(); ++j) {
                        if (test.isCorrectPath(i, j)) {
                            final Rectangle r = new Rectangle((double)test.getSpaceSize(), (double)test.getSpaceSize());
                            gridpane.add((Node)r, 1 + i * 2, 1 + j * 2);
                        }
                    }
                }
            }
        });
        continuousTime.getKeyFrames().add((Object)new KeyFrame(Duration.seconds(0.2), (EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                stage.sizeToScene();
                borderPane.toFront();
                if (centerScreen) {
                    stage.centerOnScreen();
                }
            }
        }, new KeyValue[0]));
        continuousTime.playFromStart();
        newItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                justforFirstStartButton.setVisible(false);
                root.getChildren().remove((Object)justforFirstStartButton);
                if (timeSeconds.get() < test.starttime() && timeSeconds.get() > 0.1 && !test.isDone()) {
                    final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("New Game Confirmation");
                    alert.setHeaderText("Attempting to start a new game");
                    alert.setContentText("Are you sure you want to end the current game and start a new game? This will count as a loss in the statistics.");
                    final Optional<ButtonType> result = (Optional<ButtonType>)alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        test.addlose();
                        startButton.setDisable(false);
                        startButton.fire();
                    }
                }
                else {
                    startButton.setDisable(false);
                    startButton.fire();
                }
            }
        });
        endItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                if (timeSeconds.get() != 0.0) {
                    if (timeSeconds.get() < test.starttime() && timeSeconds.get() > 0.1 && !test.isDone()) {
                        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("End Game Confirmation");
                        alert.setHeaderText("Attempting to end the current game");
                        alert.setContentText("Are you sure you want to end the current game? This will count as a loss in the statistics.");
                        final Optional<ButtonType> result = (Optional<ButtonType>)alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            if (pauseItem.isVisible() && !gridpane.isVisible()) {
                                pauseItem.fire();
                            }
                            timeSeconds.set(0.0);
                            nextButton.setVisible(true);
                        }
                    }
                    else if (pauseItem.isVisible() && !gridpane.isVisible()) {
                        pauseItem.fire();
                    }
                    else {
                        if (!hbox.getChildren().contains((Object)nextButton)) {
                            hbox.getChildren().addAll((Object[])new Node[] { (Node)nextButton });
                        }
                        timeSeconds.set(0.0);
                        nextButton.setVisible(true);
                    }
                }
            }
        });
        exitItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit Confirmation");
                alert.setHeaderText("Attempting to Exit the Game");
                alert.setContentText("Are you sure you want to quit the game? If a game is in play, the game will automatically count as a loss in the statistics.");
                final Optional<ButtonType> result = (Optional<ButtonType>)alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (timeSeconds.get() < test.starttime() && timeSeconds.get() > 0.1 && !test.isDone()) {
                        test.addlose();
                    }
                    MazeTest.this.setpreferences(MazeTest.this.prefs, test);
                    System.exit(0);
                }
            }
        });
        stage.setOnCloseRequest((EventHandler)new EventHandler<WindowEvent>() {
            public void handle(final WindowEvent event) {
                event.consume();
                exitItem.fire();
            }
        });
        firstStartButton.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent e) {
                startButton.fire();
                firstStartButton.setVisible(false);
                borderPane.setTop((Node)vbox);
                justforFirstStartButton.setTop((Node)null);
                root.getChildren().remove((Object)justforFirstStartButton);
                justforFirstStartButton.setVisible(false);
            }
        });
        pauseItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                if (!test.finished()) {
                    if (!justforFirstStartButton.isVisible()) {
                        if (gridpane.isVisible() && timeSeconds.get() > 0.0 && timeSeconds.get() < test.starttime()) {
                            timerLabel.setVisible(false);
                            pausePane.setVisible(true);
                            gridpane.setVisible(false);
                            timeline.pause();
                        }
                        else {
                            timerLabel.setVisible(true);
                            pausePane.setVisible(false);
                            gridpane.setVisible(true);
                            timeline.play();
                        }
                    }
                }
                stage.centerOnScreen();
            }
        });
        nextButton.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                newItem.fire();
            }
        });
        difficultyItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final Optional<String> result = (Optional<String>)difficultDialog.showAndWait();
                if (result.isPresent()) {
                    test.setdifficulty(Integer.parseInt(result.get()));
                }
            }
        });
        timeDifficultyItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final Optional<String> result = (Optional<String>)timeDialog.showAndWait();
                if (result.isPresent()) {
                    test.settimedifficulty(Integer.parseInt(result.get()));
                }
            }
        });
        gameplayItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final boolean fill = test.getpathfill();
                final Group roo = new Group();
                final Stage okay = new Stage();
                final Scene wins = new Scene((Parent)roo, 400.0, 500.0);
                okay.setScene(wins);
                final GridPane grid = MazeTest.this.setupgameplayitem(test, difficultDialog, timeDialog);
                okay.setTitle("Set Up Other Gameplay");
                roo.getChildren().addAll((Object[])new Node[] { (Node)grid });
                okay.showAndWait();
                if (!test.getshowpercentage()) {
                    vbox.getChildren().removeAll((Object[])new Node[] { (Node)percentagebox });
                }
                else {
                    vbox.getChildren().removeAll((Object[])new Node[] { (Node)percentagebox });
                    vbox.getChildren().addAll((Object[])new Node[] { (Node)percentagebox });
                }
                if (!test.getpathfill() && fill) {
                    MazeTest.this.redrawMaze(gridpane, test);
                    final GridPane val$gridpane = gridpane;
                    final int zz = GridPane.getRowIndex((Node)circle);
                    final GridPane val$gridpane2 = gridpane;
                    final int za = GridPane.getColumnIndex((Node)circle);
                    gridpane.getChildren().removeAll((Object[])new Node[] { (Node)circle });
                    gridpane.add((Node)circle, za, zz);
                }
            }
        });
        winLossItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final Group roo = new Group();
                final Stage okay = new Stage();
                final Scene wins = new Scene((Parent)roo, 400.0, 300.0);
                okay.setScene(wins);
                final GridPane grid = MazeTest.this.setupwinlossitem(test);
                okay.setTitle("Win/Loss");
                roo.getChildren().addAll((Object[])new Node[] { (Node)grid });
                okay.showAndWait();
            }
        });
        miscellaneousItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final Group roo = new Group();
                final Stage okay = new Stage();
                final Scene wins = new Scene((Parent)roo, 500.0, 300.0);
                okay.setScene(wins);
                final GridPane grid = MazeTest.this.setupmiscallaneousitem(test);
                okay.setTitle("Miscellaneous");
                roo.getChildren().addAll((Object[])new Node[] { (Node)grid });
                okay.showAndWait();
            }
        });
        instructionItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final Group roo = new Group();
                final Stage okay = new Stage();
                final Scene wins = new Scene((Parent)roo, 900.0, 300.0);
                okay.setScene(wins);
                final Label area = MazeTest.this.setupinstructionitem(test);
                okay.setTitle("Instructions");
                final ScrollPane sp = new ScrollPane();
                sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                sp.setContent((Node)area);
                sp.setPrefSize(899.0, 250.0);
                final HBox vb = new HBox();
                vb.getChildren().addAll((Object[])new Node[] { (Node)sp });
                roo.getChildren().addAll((Object[])new Node[] { (Node)vb });
                okay.showAndWait();
            }
        });
        mazegeneratorItem.setOnAction((EventHandler)new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final ArrayList<GridPane> grid = MazeTest.this.setupmazegenerator(test);
                final IntegerProperty maxed = (IntegerProperty)new SimpleIntegerProperty(grid.size());
                final IntegerProperty currentproperty = (IntegerProperty)new SimpleIntegerProperty(0);
                final Timeline gridline = new Timeline();
                gridline.setCycleCount(grid.size());
                gridline.getKeyFrames().add((Object)new KeyFrame(Duration.seconds(0.2), (EventHandler)new EventHandler<ActionEvent>() {
                    public void handle(final ActionEvent event) {
                        final Group roo = new Group();
                        final Stage okay = new Stage();
                        final Scene wins = new Scene((Parent)roo, 800.0, 800.0);
                        okay.setScene(wins);
                        okay.setTitle("Maze Generator " + currentproperty.get());
                        roo.getChildren().addAll((Object[])new Node[] { grid.get(currentproperty.get()) });
                        currentproperty.set(currentproperty.get() + 1);
                        okay.show();
                    }
                }, new KeyValue[0]));
                gridline.play();
            }
        });
        borderPane.toFront();
        root.getChildren().addAll((Object[])new Node[] { (Node)justforFirstStartButton, (Node)congratsPane, (Node)failPane });
        stage.setScene(scene);
        stage.show();
        borderPane.toFront();
    }
    
    public void createNewMaze(final GridPane gridpane, final Maze test) {
        test.setMazeAgain();
        this.redrawMaze(gridpane, test);
        test.setpoint((int)test.getstarting().getX(), (int)test.getstarting().getY());
    }
    
    public void redrawMaze(final GridPane gridpane, final Maze test) {
        final int wallthickness = test.getwall();
        final int mazewidth = test.width();
        final int mazeheight = test.height();
        final int spacesize = test.getSpaceSize();
        final Point2D starting = test.getstarting();
        final Point2D destination = test.getdestination();
        final int insetup = 40;
        final int insetright = 40;
        final int insetdown = 40;
        final int insetleft = 40;
        final Color background = test.getBackgroundColor();
        final Color space = test.getSpaceColor();
        final Color wallcol = test.getWallColor();
        final Color open = test.getOpenColor();
        final Color currentspace = test.getCurrentColor();
        final Color visitcolor = test.getVisitColor();
        for (int i = 0; i < mazewidth; ++i) {
            for (int j = 0; j < mazeheight; ++j) {
                final Rectangle r = new Rectangle((double)spacesize, (double)spacesize);
                if (i == starting.getX() && j == starting.getY()) {
                    r.setFill((Paint)Color.GRAY);
                }
                else if (i == destination.getX() && j == destination.getY()) {
                    r.setFill((Paint)Color.GRAY);
                }
                else {
                    r.setFill((Paint)space);
                }
                final Rectangle wall = new Rectangle((double)wallthickness, (double)spacesize);
                if (test.west(i, j)) {
                    wall.setFill((Paint)wallcol);
                }
                else {
                    wall.setFill((Paint)open);
                }
                if (i < mazewidth && j < mazeheight) {
                    gridpane.add((Node)r, 1 + i * 2, 1 + j * 2);
                }
                if (j < mazeheight) {
                    gridpane.add((Node)wall, i * 2, 1 + j * 2);
                }
            }
            for (int j = 0; j < mazeheight; ++j) {
                final Rectangle wall2 = new Rectangle((double)spacesize, (double)wallthickness);
                final Rectangle w2 = new Rectangle((double)wallthickness, (double)wallthickness);
                if (test.north(i, j)) {
                    wall2.setFill((Paint)wallcol);
                }
                else {
                    wall2.setFill((Paint)open);
                }
                w2.setFill((Paint)wallcol);
                if (i < mazewidth) {
                    gridpane.add((Node)wall2, 1 + i * 2, j * 2);
                }
                gridpane.add((Node)w2, i * 2, j * 2);
            }
            final Rectangle well = new Rectangle((double)spacesize, (double)wallthickness);
            final Rectangle well2 = new Rectangle((double)wallthickness, (double)wallthickness);
            well.setFill((Paint)wallcol);
            well2.setFill((Paint)wallcol);
            gridpane.add((Node)well, 1 + i * 2, mazeheight * 2);
            gridpane.add((Node)well2, i * 2, mazeheight * 2);
        }
        for (int i = 0; i < mazeheight; ++i) {
            final Rectangle well = new Rectangle((double)wallthickness, (double)spacesize);
            final Rectangle well2 = new Rectangle((double)wallthickness, (double)wallthickness);
            well.setFill((Paint)wallcol);
            well2.setFill((Paint)wallcol);
            gridpane.add((Node)well, mazewidth * 2, 1 + i * 2);
            gridpane.add((Node)well2, mazewidth * 2, i * 2);
        }
        final int preferredX = mazewidth * (wallthickness + spacesize);
        final int preferredY = mazeheight * (wallthickness + spacesize);
        gridpane.setPrefSize((double)preferredX, (double)preferredY);
    }
    
    public GridPane setupwinlossitem(final Maze test) {
        final GridPane grid = new GridPane();
        final Text test2 = new Text("Total Games Played ");
        final Text test3 = new Text("Wins");
        final Text test4 = new Text("Losses");
        final Text test5 = new Text("Win Percentage");
        final Text test6 = new Text("Average Size Level of Wins: ");
        final Text test7 = new Text("Average Time Level of Wins: ");
        final Text test8 = new Text("Average Size Level of Losses: ");
        final Text test9 = new Text("Average Time Level of Losses: ");
        final Text test10 = new Text(Integer.toString(test.gettotalgames()));
        final Text test11 = new Text(Integer.toString(test.getwincount()));
        final Text test12 = new Text(Integer.toString(test.getlosecount()));
        final Text test13 = new Text(String.format("%.1f", test.getwinpercentage()) + "%");
        final Text test14 = new Text(String.format("%.2f", test.getnumberwinssize()));
        final Text test15 = new Text(String.format("%.2f", test.getnumberwinstime()));
        final Text test16 = new Text(String.format("%.2f", test.getnumberlosesize()));
        final Text test17 = new Text(String.format("%.2f", test.getnumberlosetime()));
        test2.setStyle("-fx-font: 20 arial;");
        test3.setStyle("-fx-font: 20 arial;");
        test4.setStyle("-fx-font: 20 arial;");
        test5.setStyle("-fx-font: 20 arial;");
        test6.setStyle("-fx-font: 20 arial;");
        test7.setStyle("-fx-font: 20 arial;");
        test8.setStyle("-fx-font: 20 arial;");
        test9.setStyle("-fx-font: 20 arial;");
        test10.setStyle("-fx-font: 20 arial;");
        test11.setStyle("-fx-font: 20 arial;");
        test12.setStyle("-fx-font: 20 arial;");
        test13.setStyle("-fx-font: 20 arial;");
        test14.setStyle("-fx-font: 20 arial;");
        test15.setStyle("-fx-font: 20 arial;");
        test16.setStyle("-fx-font: 20 arial;");
        test17.setStyle("-fx-font: 20 arial;");
        grid.add((Node)test2, 0, 0);
        grid.add((Node)test3, 0, 1);
        grid.add((Node)test4, 0, 2);
        grid.add((Node)test5, 0, 3);
        grid.add((Node)test6, 0, 4);
        grid.add((Node)test7, 0, 5);
        grid.add((Node)test8, 0, 6);
        grid.add((Node)test9, 0, 7);
        grid.add((Node)test10, 1, 0);
        grid.add((Node)test11, 1, 1);
        grid.add((Node)test12, 1, 2);
        grid.add((Node)test13, 1, 3);
        grid.add((Node)test14, 1, 4);
        grid.add((Node)test15, 1, 5);
        grid.add((Node)test16, 1, 6);
        grid.add((Node)test17, 1, 7);
        return grid;
    }
    
    public GridPane setupmiscallaneousitem(final Maze test) {
        final GridPane grid = new GridPane();
        final Text test2 = new Text("Spaces tried to move: ");
        final Text test3 = new Text("Spaces actually moved: ");
        final Text test4 = new Text("Spaces tried to move without movement: ");
        final Text test5 = new Text("Spaces revisited or incorrect spaces: ");
        final Text test6 = new Text("Optimal number of spaces to move: ");
        final Text test7 = new Text("Percent optimal spaces of spaces tried to move: ");
        final Text test8 = new Text(Integer.toString(test.getspacestriedtomove()));
        final Text test9 = new Text(Integer.toString(test.gettotalspacesmoved()));
        final Text test10 = new Text(Integer.toString(test.getspacestriedtomove() - test.gettotalspacesmoved()));
        final Text test11 = new Text(Integer.toString(test.gettotalspacesmoved() - test.getoptimalspacesmoved()));
        final Text test12 = new Text(Integer.toString(test.getoptimalspacesmoved()));
        final Text test13 = new Text(String.format("%.1f", 100.0 * test.getoptimalspacesmoved() / test.getspacestriedtomove()) + "%");
        test2.setStyle("-fx-font: 18 arial;");
        test3.setStyle("-fx-font: 18 arial;");
        test4.setStyle("-fx-font: 18 arial;");
        test5.setStyle("-fx-font: 18 arial;");
        test6.setStyle("-fx-font: 18 arial;");
        test7.setStyle("-fx-font: 18 arial;");
        test8.setStyle("-fx-font: 18 arial;");
        test9.setStyle("-fx-font: 18 arial;");
        test10.setStyle("-fx-font: 18 arial;");
        test11.setStyle("-fx-font: 18 arial;");
        test12.setStyle("-fx-font: 18 arial;");
        test13.setStyle("-fx-font: 18 arial;");
        grid.add((Node)test2, 0, 0);
        grid.add((Node)test3, 0, 1);
        grid.add((Node)test4, 0, 2);
        grid.add((Node)test5, 0, 3);
        grid.add((Node)test6, 0, 4);
        grid.add((Node)test7, 0, 5);
        grid.add((Node)test8, 1, 0);
        grid.add((Node)test9, 1, 1);
        grid.add((Node)test10, 1, 2);
        grid.add((Node)test11, 1, 3);
        grid.add((Node)test12, 1, 4);
        grid.add((Node)test13, 1, 5);
        return grid;
    }
    
    public GridPane setupgameplayitem(final Maze test, final ChoiceDialog<String> difficultDialog, final ChoiceDialog<String> timeDialog) {
        final GridPane grid = new GridPane();
        grid.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        grid.setVgap(4.0);
        final Slider slider = new Slider(1.0, 20.0, (double)test.getdifficulty());
        final Text test2 = new Text("Size Level");
        slider.setMaxWidth(200.0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(20.0);
        slider.setMinorTickCount(20);
        slider.setBlockIncrement(1.0);
        grid.add((Node)test2, 0, 0);
        grid.add((Node)slider, 1, 0, 6, 1);
        final Label sliderValue = new Label(String.format("%.0f", slider.getValue()));
        grid.add((Node)sliderValue, 7, 0);
        slider.valueProperty().addListener((ChangeListener)new ChangeListener<Number>() {
            public void changed(final ObservableValue<? extends Number> ov, final Number old_val, final Number new_val) {
                sliderValue.setText(String.format("%.0f", new_val));
                difficultDialog.setSelectedItem((Object)String.valueOf(new_val.intValue()));
                test.setdifficulty(new_val.intValue());
            }
        });
        final Slider slider2 = new Slider(1.0, 20.0, (double)test.gettimedifficulty());
        final Text test3 = new Text("Time Level");
        GridPane.setHgrow((Node)slider2, Priority.ALWAYS);
        slider2.setMaxWidth(200.0);
        slider2.setShowTickLabels(true);
        slider2.setShowTickMarks(true);
        slider2.setMajorTickUnit(20.0);
        slider2.setMinorTickCount(20);
        slider2.setBlockIncrement(1.0);
        grid.add((Node)test3, 0, 2);
        grid.add((Node)slider2, 1, 2, 6, 1);
        final Label slider2Value = new Label(String.format("%.0f", slider2.getValue()));
        grid.add((Node)slider2Value, 7, 2);
        slider2.valueProperty().addListener((ChangeListener)new ChangeListener<Number>() {
            public void changed(final ObservableValue<? extends Number> ov, final Number old_val, final Number new_val) {
                slider2Value.setText(String.format("%.0f", new_val));
                timeDialog.setSelectedItem((Object)String.valueOf(new_val.intValue()));
                test.settimedifficulty(new_val.intValue());
            }
        });
        final CheckBox cb1 = new CheckBox("Always End at Bottom Right Corner");
        final CheckBox cb2 = new CheckBox("Adjust Time for Length of Maze (recommended)");
        final CheckBox cb3 = new CheckBox("Include Horizontal Laser Beams (not recommended)");
        final CheckBox cb4 = new CheckBox("Include Vertical Laser Beams (not recommended)");
        final CheckBox cb5 = new CheckBox("Turn On Path Fill");
        final CheckBox cb6 = new CheckBox("Save Settings for Next Time");
        final CheckBox cb7 = new CheckBox("Save Statistics for Next Time \n(WARNING: UNSELECTING WILL DELETE ALL DATA)");
        final CheckBox cb8 = new CheckBox("Show Complete Percentage");
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
        cb1.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> ov, final Boolean old_val, final Boolean new_val) {
                test.setcorner(new_val);
            }
        });
        cb2.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> ov, final Boolean old_val, final Boolean new_val) {
                test.settimeadjust(new_val);
            }
        });
        cb3.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> ov, final Boolean old_val, final Boolean new_val) {
                test.sethlazer(new_val);
            }
        });
        cb4.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> ov, final Boolean old_val, final Boolean new_val) {
                test.setvlazer(new_val);
            }
        });
        cb5.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> ov, final Boolean old_val, final Boolean new_val) {
                test.setpathfill(new_val);
            }
        });
        cb6.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> ov, final Boolean old_val, final Boolean new_val) {
                test.setsavesettings(new_val);
            }
        });
        cb7.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> ov, final Boolean old_val, final Boolean new_val) {
                test.setsavestats(new_val);
            }
        });
        cb8.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> ov, final Boolean old_val, final Boolean new_val) {
                test.setshowpercentage(new_val);
            }
        });
        grid.add((Node)cb1, 0, 5, 4, 1);
        grid.add((Node)cb2, 0, 6, 4, 1);
        grid.add((Node)cb3, 0, 7, 4, 1);
        grid.add((Node)cb4, 0, 8, 4, 1);
        grid.add((Node)cb5, 0, 9, 4, 1);
        grid.add((Node)cb6, 0, 10, 4, 1);
        grid.add((Node)cb7, 0, 11, 4, 1);
        grid.add((Node)cb8, 0, 12, 4, 1);
        final ToggleGroup group = new ToggleGroup();
        final Text text99 = new Text("\n");
        grid.add((Node)text99, 0, 13);
        final Text text100 = new Text("Space Thickness");
        final RadioButton rb1 = new RadioButton("Thin");
        final RadioButton rb2 = new RadioButton("Normal");
        final RadioButton rb3 = new RadioButton("Thick");
        final RadioButton rb4 = new RadioButton("Very Thin");
        final RadioButton rb5 = new RadioButton("Thin");
        final RadioButton rb6 = new RadioButton("Normal");
        final RadioButton rb7 = new RadioButton("Thick");
        final RadioButton rb8 = new RadioButton("Very Thick");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        rb4.setToggleGroup(group);
        final int spacethickness = test.getSpaceSize();
        switch (spacethickness) {
            case 10: {
                rb1.setSelected(true);
                break;
            }
            case 12: {
                rb2.setSelected(true);
                break;
            }
            case 14: {
                rb3.setSelected(true);
                break;
            }
            case 8: {
                rb4.setSelected(true);
                break;
            }
            default: {
                rb2.setSelected(true);
                break;
            }
        }
        rb1.requestFocus();
        rb2.requestFocus();
        rb3.requestFocus();
        rb4.requestFocus();
        rb5.requestFocus();
        rb6.requestFocus();
        rb7.requestFocus();
        rb8.requestFocus();
        rb1.setUserData((Object)"Thin");
        rb2.setUserData((Object)"Normal");
        rb3.setUserData((Object)"Thick");
        rb4.setUserData((Object)"Square");
        rb5.setUserData((Object)"Thin");
        rb6.setUserData((Object)"Normal");
        rb7.setUserData((Object)"Thick");
        rb8.setUserData((Object)"Square");
        grid.add((Node)text100, 0, 14);
        grid.add((Node)rb1, 2, 14);
        grid.add((Node)rb2, 3, 14);
        grid.add((Node)rb3, 4, 14);
        grid.add((Node)rb4, 1, 14);
        group.selectedToggleProperty().addListener((ChangeListener)new ChangeListener<Toggle>() {
            public void changed(final ObservableValue<? extends Toggle> ov, final Toggle old_toggle, final Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    final String string;
                    final String k = string = group.getSelectedToggle().getUserData().toString();
                    switch (string) {
                        case "Thin": {
                            test.setSpaceSize(10);
                            break;
                        }
                        case "Normal": {
                            test.setSpaceSize(12);
                            break;
                        }
                        case "Thick": {
                            test.setSpaceSize(14);
                            break;
                        }
                        case "Square": {
                            test.setSpaceSize(8);
                            break;
                        }
                    }
                }
            }
        });
        final ToggleGroup group2 = new ToggleGroup();
        final Text text101 = new Text("Wall Thickness");
        rb5.setToggleGroup(group2);
        rb6.setToggleGroup(group2);
        rb7.setToggleGroup(group2);
        rb8.setToggleGroup(group2);
        final int wallthickness = test.getwall();
        switch (wallthickness) {
            case 2: {
                rb5.setSelected(true);
                break;
            }
            case 3: {
                rb6.setSelected(true);
                break;
            }
            case 4: {
                rb7.setSelected(true);
                break;
            }
            case 8: {
                rb8.setSelected(true);
                break;
            }
            default: {
                rb6.setSelected(true);
                break;
            }
        }
        grid.add((Node)text101, 0, 15);
        grid.add((Node)rb5, 1, 15);
        grid.add((Node)rb6, 2, 15);
        grid.add((Node)rb7, 3, 15);
        grid.add((Node)rb8, 4, 15);
        group2.selectedToggleProperty().addListener((ChangeListener)new ChangeListener<Toggle>() {
            public void changed(final ObservableValue<? extends Toggle> ov, final Toggle old_toggle, final Toggle new_toggle) {
                if (group2.getSelectedToggle() != null) {
                    final String string;
                    final String k = string = group2.getSelectedToggle().getUserData().toString();
                    switch (string) {
                        case "Thin": {
                            test.setwall(2);
                            break;
                        }
                        case "Normal": {
                            test.setwall(3);
                            break;
                        }
                        case "Thick": {
                            test.setwall(4);
                            break;
                        }
                        case "Square": {
                            test.setwall(8);
                            break;
                        }
                    }
                }
            }
        });
        return grid;
    }
    
    public Label setupinstructionitem(final Maze test) {
        final Label textarea = new Label("Instructions:\n This is a simple maze game that has a timer ticking down. It is not\nso much a maze game but about rapdily pressing buttons\nquickly and efficiently to get through the maze. This game only consists of single games and there are no series involved.\nA circle is used as the item/object navigating through the maze and a colored square indicates the start and destination.\nTo move up: Press the up arrow key and/or the W key.\nTo move right: Press the right arrow key and/or the D key.\nTo move down: Press the down arrow key and/or the S key.\nTo move left: Press the left arrow key and/or the A key.\nButton remapping is not supported. All games start at the upper left corner and generally end at the lower right corner.\nThe maze was generated using a depth-first search algorithm, so the paths mainly consists of long winded passageways. For\nthis reason, taking the wrong path is not necessarily common, and the game is primarily about speed. \n Settings Definition\nSize Level: The size level determines the width and the height of the maze, which are generally the same. Therefore,\nthe maze is shaped like a square. The width and height is equal to maze difficulty minus 10\nTime Level: The time level determines the amount of time left when starting a game. The time level depends on the\ndifficulty/size of the maze if adjust time for length of maze (recommended) is turned off. If adjust time for length of maze\n(recommended) is turned on then, the length of the maze instead of the size of the maze determines the amount of time left\nHere are the relevant values for determining how much time is left beginning the maze (in seconds).\nLevel 1: length*0.28\nLevel 2: length*0.27\nLevel 3: length*0.26\nLevel 4: length*0.25\nLevel 5: length*0.24\nLevel 6: length*0.23\nLevel 7: length*0.22\nLevel 8: length*0.21\nLevel 9: length*0.20\nLevel 10: length*0.19\nLevel 11: length*0.18\nLevel 12: length*0.175\nLevel 13: length*0.17nLevel 14: length*0.165\nLevel 15: length*0.16\nLevel 16: length*0.156\nLevel 17: length*0.152\nLevel 18: length*0.148\nLevel 19: length*0.144\nLevel 20: length*0.14\nOne second is also added to the resulting value from the above equation. When adjust time for length of maze\nis turned off, the same values are used, but the average length of the maze is used as the length.\nThe resulting value is multiplied by 1.1. If adjust for length of maze is turned off, some mazes are very easy\nand some mazes may be impossible. Note that there are multiple places to change the difficulty settings.\nBoth change the same thing, but the \"Set up gameplay/difficulty\" is the easiest way to do so.\n \n Laser Beams: Laser beams were originally to be added to the game, but they have been disabled\nPath fill: If path fill is turned on, every space that the circle has been to will be colored, which makes the maze\neasier as you can see which places have already been completed. There is a glitch where some spaces won't be colored\nwhen passing through it however.\nSave settings for next time: The entire menu will be saved if this is turned on. If this is turned off, the default\nsettings will be restored, which can't be altered from the start menu.\nSave statistics for next time: Statistics are normally saved every time the game is turned on and off, however,\nthey can be deleted by unmarking the check box and exiting the game\nShow complete percentage: A percentage counter indicating what percentage of the maze is completed, along with\na corresponding progress bar is included. This can be turned on and off by marking and unmarking the check box.\nThe percentage counter will not be incremented if circle goes to any wrong spaces or goes backwards in the maze.\n\nThe space size and the wall size can also be changed. If the space size is set to very thin and the wall size\nis set to very thick, then the walls and spaces have the same width. However, it is difficulty to navigate the maze\nthis way, so it is not recommended. The default settings have the spaces and walls set at normal\n\nDefining the statistics menu: The first of the statistics menu is labeled Wins/Losses and shows the typical wins and\nlosses. Win percentage is defined as wins divided by total games. Losses are incurred when creating a new game or exiting\nthe game while the timer is ticking down or when the timer hits 0.0. The average size and time difficulty when winning the\ngame and the average size and time difficulty in games that are lost are also included.\n\nIn the miscellaneous section, a few other limited number of statistics are available. The total number of directional button\ninputs is defined as spaces tried to move. The number of spaces that the circle actually moved is the spaces actually moved. \nTherefore, the spaces tried to move subtracting the spaces actually moved defines the number of directional inputs tht did not\nresult in any movement, which is spaces tried to move without movement. The optimal number of spaces is defined as the minimum\nnumber of directional inputs to reach the end of the maze and win the game. The actual number of spaces subtracting the optimal\nmovement is therefore extra movement that is not needed. This can be revisiting spaces that were already visited or going the\nincorrect way, which is the statistic shown as spaces revisited or incorrect spaces.\nFinally, the percentage optimal spaces of spaces tried to move is simply optimal number of spaces divided by spaces tried to move.\nThis can be taken as the efficiency ratio of the player.\n\nOther things: Be sure to pause the game through pressing Ctrl+P, which is the quickest method, to change any settings during the game, because\nthe clock will be ticking down otherwise. The window on the screen is also centered, and cannot be moved from the center\nIn the help menu, a maze generator is included, which produces a 19*19 size maze, equivalent to a level 9 size difficulty maze.\nThis produces a gridpane of every single step that updates the maze when generating the maze through the depth-first search algorithm.\nThis produces 441 images and the best way to remove the images is just to quit the game. Make sure a game is not ongoing when pressing\nthe see maze generator item in the Help menu.\n");
        textarea.setFont(new Font("Times New Roman", 13.0));
        return textarea;
    }
    
    public ArrayList<GridPane> setupmazegenerator(final Maze test) {
        final ArrayList<GridPane> grid = test.specialmazegenerator();
        return grid;
    }
    
    public Maze getpreferences(final Preferences pref) {
        final int size = this.prefs.getInt("Size", 1);
        final int time = this.prefs.getInt("Time", 1);
        final boolean corner = this.prefs.getBoolean("Corner", true);
        final boolean timeadjust = this.prefs.getBoolean("Timeadjust", true);
        final boolean hbeam = this.prefs.getBoolean("HBeam", false);
        final boolean vbeam = this.prefs.getBoolean("VBeam", false);
        final boolean pathfill = this.prefs.getBoolean("Pathfill", true);
        final boolean savesettings = this.prefs.getBoolean("Savesettings", false);
        final boolean savestats = this.prefs.getBoolean("Savestats", true);
        final boolean percentage = this.prefs.getBoolean("Percentage", true);
        final int spacethick = this.prefs.getInt("Spacethick", 12);
        final int wallthick = this.prefs.getInt("Wallthick", 3);
        final int totalgames = this.prefs.getInt("Totalgames", 0);
        final int wins = this.prefs.getInt("Wins", 0);
        final int losses = this.prefs.getInt("Losses", 0);
        final int sizewins = this.prefs.getInt("Sizewins", 0);
        final int timewins = this.prefs.getInt("Timewins", 0);
        final int sizeloss = this.prefs.getInt("Sizeloss", 0);
        final int timeloss = this.prefs.getInt("Timeloss", 0);
        final int spacesactuallymoved = this.prefs.getInt("Spacesactuallymoved", 0);
        final int spacestriedmoved = this.prefs.getInt("Spacestriedmoved", 0);
        final int optimalspaces = this.prefs.getInt("Optimalspaces", 0);
        final Maze test = new Maze(size, time, corner, timeadjust, hbeam, vbeam, pathfill, savesettings, savestats, percentage, spacethick, wallthick, totalgames, wins, losses, sizewins, timewins, sizeloss, timeloss, spacesactuallymoved, spacestriedmoved, optimalspaces);
        return test;
    }
    
    public void setpreferences(final Preferences pref, final Maze test) {
        if (test.getsavesettings()) {
            this.prefs.putInt("Size", test.getdifficulty());
            this.prefs.putInt("Time", test.gettimedifficulty());
            this.prefs.putBoolean("Corner", test.getcorner());
            this.prefs.putBoolean("Timeadjust", test.gettimeadjust());
            this.prefs.putBoolean("HBeam", test.gethlazer());
            this.prefs.putBoolean("VBeam", test.getvlazer());
            this.prefs.putBoolean("Pathfill", test.getpathfill());
            this.prefs.putBoolean("Savesettings", test.getsavesettings());
            this.prefs.putBoolean("Savestats", test.getsavestats());
            this.prefs.putBoolean("Percentage", test.getshowpercentage());
            this.prefs.putInt("Spacethick", test.getSpaceSize());
            this.prefs.putInt("Wallthick", test.getwall());
        }
        else {
            this.prefs.remove("Size");
            this.prefs.remove("Time");
            this.prefs.remove("Corner");
            this.prefs.remove("Timeadjust");
            this.prefs.remove("VBeam");
            this.prefs.remove("HBeam");
            this.prefs.remove("Pathfill");
            this.prefs.remove("Savesettings");
            this.prefs.remove("Savestats");
            this.prefs.remove("Percentage");
            this.prefs.remove("Spacethick");
            this.prefs.remove("Wallthick");
        }
        if (test.getsavestats()) {
            this.prefs.putInt("Totalgames", test.gettotalgames());
            this.prefs.putInt("Wins", test.getwincount());
            this.prefs.putInt("Losses", test.getlosecount());
            this.prefs.putInt("Sizewins", test.getwinsize());
            this.prefs.putInt("Timewins", test.getwintime());
            this.prefs.putInt("Sizeloss", test.getlosesize());
            this.prefs.putInt("Timeloss", test.getlosetime());
            this.prefs.putInt("Spacesactuallymoved", test.gettotalspacesmoved());
            this.prefs.putInt("Spacestriedmoved", test.getspacestriedtomove());
            this.prefs.putInt("Optimalspaces", test.getoptimalspacesmoved());
        }
        else {
            this.prefs.remove("Totalgames");
            this.prefs.remove("Wins");
            this.prefs.remove("Losses");
            this.prefs.remove("Sizewins");
            this.prefs.remove("Timewins");
            this.prefs.remove("Sizeloss");
            this.prefs.remove("Timeloss");
            this.prefs.remove("Spacesactuallymoved");
            this.prefs.remove("Spacestriedmoved");
            this.prefs.remove("Optimalspaces");
        }
    }
}
