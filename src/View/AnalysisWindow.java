package View;

import Data.Enums.SetupAlgorithms;
import Data.Enums.VicAlgorithms;
import Data.RunConfig;
import Logic.AI;
import Logic.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AnalysisWindow {

    private AI ai;
    private Game game;
    private DisplayFx display;
    private DisplayUtility utility;

    private Label analyisLabel = new Label("Analysis run: ");
    private Label averageLabel =new Label("Average:" + '\n');
    private ArrayList<RunStat> runStats = new ArrayList<>();

    public AnalysisWindow(Game game, AI ai, DisplayFx display){
        this.game=game;
        this.ai=ai;
        this.display=display;
        this.utility=new DisplayUtility();
    }

    public void createWindow(){

        Stage analysisWindow = new Stage();

        HBox hBoxAlgorithms = drawAlgorithmSelecter();
        HBox hBoxSetupAlgorithms = drawSetupAlgorithmSelecter();
        HBox hBoxLimits = drawLimitsSelecter();
        ScrollPane analysisTextScrollPane = drawAnalysisScrollPane();

        HBox hBoxRunIterations = drawIterations();
        Label average = drawAverage();

        VBox vBoxColumns = new VBox(hBoxAlgorithms,hBoxSetupAlgorithms , hBoxLimits, analysisTextScrollPane , average , hBoxRunIterations);
        vBoxColumns.setPadding(new Insets(10));
        vBoxColumns.setSpacing(10);

        Scene secondScene = new Scene(vBoxColumns, 1300,800);
        analysisWindow.setTitle("Analysis");
        analysisWindow.setScene(secondScene);
        analysisWindow.setX(0);
        analysisWindow.setY(40);
        analysisWindow.show();
    }


    public HBox drawSetupAlgorithmSelecter(){

        RadioButton s1 = new RadioButton("Depth First Search");
        RadioButton s2 = new RadioButton("Random First Search");
        RadioButton s3 = new RadioButton("Breadth First Search");
        RadioButton s4 = new RadioButton("Position Score");
        RadioButton s5 = new RadioButton("Cross Block Score");

        // create a toggle group
        ToggleGroup sg = new ToggleGroup();
        s1.setToggleGroup(sg);
        s2.setToggleGroup(sg);
        s3.setToggleGroup(sg);
        s4.setToggleGroup(sg);
        s5.setToggleGroup(sg);
        s1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedSetupHeuristic(0);
            }
        });
        s2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedSetupHeuristic(1);
            }
        });
        s3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedSetupHeuristic(2);
            }
        });
        s4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedSetupHeuristic(3);
            }
        });
        s5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedSetupHeuristic(4);
            }
        });

        s1.setSelected(true);
        HBox setupAlgorithms = new HBox(s1,s2,s3,s4,s5);
        setupAlgorithms.setSpacing(10);
        return setupAlgorithms;
    }


    public HBox drawAlgorithmSelecter(){
        //Algorithm=0
        //Uses Depth limited Depth first search
        //Algorithm=1
        //Uses Depth limited random first search
        //Algorithm=2
        //Uses Depth limited breadth first search
        RadioButton v1 = new RadioButton("Depth First Search");
        RadioButton v2 = new RadioButton("Random First Search");
        RadioButton v3 = new RadioButton("Breadth First Search");
        RadioButton v4 = new RadioButton("Air First Search");
        RadioButton v5 = new RadioButton("Breadth First Search preloaded Setups");
        RadioButton v6 = new RadioButton("Generic BSearch");
        RadioButton v7 = new RadioButton("Generic DSearch");

        // create a toggle group
        ToggleGroup vg = new ToggleGroup();
        v1.setToggleGroup(vg);
        v2.setToggleGroup(vg);
        v3.setToggleGroup(vg);
        v4.setToggleGroup(vg);
        v5.setToggleGroup(vg);
        v6.setToggleGroup(vg);
        v7.setToggleGroup(vg);


        v1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedVicAlgorithm(0);
            }
        });
        //Algorithm=0
        //Uses Depth limited Depth first search
        v2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedVicAlgorithm(1);
            }
        });
        //Algorithm=1
        //Uses Depth limited random first search
        v3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedVicAlgorithm(2);
            }
        });
        //Algorithm=2
        //Uses Depth limited breadth first search
        v4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedVicAlgorithm(3);
            }
        });
        v5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedVicAlgorithm(4);
            }
        });
        v6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedVicAlgorithm(5);
            }
        });
        v7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setSelectedVicAlgorithm(6);
            }
        });
        v1.setSelected(true);
        HBox vicAlgorithms = new HBox(v1,v2,v3,v4,v5,v6,v7);
        vicAlgorithms.setSpacing(10);


        return vicAlgorithms;
    }


    public HBox drawLimitsSelecter(){

        final TextField depthLimitTextField = new TextField();
        depthLimitTextField.setPromptText("Enter depthLimit (11)");
        depthLimitTextField.setPrefColumnCount(10);
        depthLimitTextField.setText("12");

        final TextField setupLimitTextField = new TextField();
        setupLimitTextField.setPromptText("Enter setupLimit (4)");
        setupLimitTextField.setPrefColumnCount(10);
        setupLimitTextField.setText("5");

        Button applyButton = new Button("Apply");
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ai.setDepthLimit(Integer.parseInt(depthLimitTextField.getText()));
                ai.setSetupLimit(Integer.parseInt(setupLimitTextField.getText()));
                System.out.println("depthLimit: " + ai.getDepthLimit() + "         setupLimit: " + ai.getSetupLimit());
            }
        });

        HBox hBoxLimits = new HBox(depthLimitTextField , setupLimitTextField , applyButton);
        hBoxLimits.setSpacing(10);

        return hBoxLimits;
    }

    public ScrollPane drawAnalysisScrollPane(){
        ScrollPane scrollPane = new ScrollPane(analyisLabel);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefViewportHeight(400);
        return scrollPane;
    }


    public HBox drawIterations(){
        final TextField iterationsTextField = new TextField();
        iterationsTextField.setPromptText("Enter Iteration Cicles (1)");
        iterationsTextField.setPrefColumnCount(20);
        iterationsTextField.setText("1");

        Button applyButton = new Button("Run x Times");
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int iterations = Integer.parseInt(iterationsTextField.getText());
                System.out.println("Iterations set to: " + iterations);
                for (int i=0; i<iterations; i++){
                    System.out.println("remaining iterations: " +(iterations-i));
                    display.executeNextSequence();
                }

            }
        });

        HBox hBoxRunIterations = new HBox(iterationsTextField , applyButton);
        hBoxRunIterations.setSpacing(10);
        return hBoxRunIterations;
    }


    public Label drawAverage(){
        String averageLabelString ="";
        for(RunConfig stat :utility.getStatList()){
            averageLabelString = averageLabelString+stat.toString();
        }
        averageLabel.setText(averageLabelString);
        return averageLabel;
    }

    public void updateAverage(){
        String averageLabelString ="";
        for(RunConfig stat :utility.getStatList()){
            averageLabelString = averageLabelString+stat.toString();
        }
        averageLabel.setText(averageLabelString);
    }

    public void updateAnalysisLabel(float timeUsed , int movesUsed, int timesFailed,int amntTimeout){
        analyisLabel.setText(analyisLabel.getText() + '\n'
                + "Algorithm: " + utility.intToVicAlgo(ai.getSelectedVicAlgorithm()) + "\t"
                + "SetupAlgorithm: "+ utility.intToSetupHeuristic(ai.getSelectedSetupHeuristic()) + "\t"
                + "Depth Limit: " + ai.getDepthLimit()+ "\t"
                + "Setup Limit: " + ai.getSetupLimit() + "\t"
                + "Time needed: " + timeUsed + "\t"
                + "Moves used: " + movesUsed + "\t"
                + "Times failed " + timesFailed + "\t"
                + "Timeouts " + amntTimeout);


        VicAlgorithms algorithm;
        SetupAlgorithms setup;
        switch (ai.getSelectedVicAlgorithm()) {
            case 0:  algorithm = VicAlgorithms.DFS;
                break;
            case 1:  algorithm = VicAlgorithms.RFS;
                break;
            case 2:  algorithm = VicAlgorithms.BFS;
                break;
            case 3:  algorithm = VicAlgorithms.AIR_FS;
                break;
            case 4:  algorithm = VicAlgorithms.BFS_PRELOAD;
                break;
            case 5:  algorithm = VicAlgorithms.BGENERIC;
                break;
            case 6:  algorithm = VicAlgorithms.DGENERIC;
                break;
            default: algorithm= VicAlgorithms.UNDEFINED;
        }
        switch (ai.getSelectedSetupHeuristic()) {
            case 0:  setup = SetupAlgorithms.DFS;
                break;
            case 1:  setup = SetupAlgorithms.RFS;
                break;
            case 2:  setup = SetupAlgorithms.BFS;
                break;
            case 3:  setup = SetupAlgorithms.POS_SCORE;
                break;
            case 4:  setup = SetupAlgorithms.CROSS_SCORE;
                break;
            default: setup= SetupAlgorithms.UNDEFINED;
        }



        RunConfig config = new RunConfig(algorithm,setup, ai.getDepthLimit(), ai.getSetupLimit());

        RunStat runStat = new RunStat(config, timeUsed , movesUsed,timesFailed, amntTimeout);
        runStats.add(runStat);
        utility.updateStats(runStat);
        updateAverage();

    }
}
