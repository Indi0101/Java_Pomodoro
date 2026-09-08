import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.Menu;
import java.awt.MenuItem;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Clase_javafx extends Application{
    TextField c_hora,c_min,c_seg,d_hora,d_min,d_seg,c_hora_m,c_min_m,c_seg_m,d_hora_m,d_min_m,d_seg_m;
    int tiempoTrabajo;
    int tiempoDescanso;
    int tiempoActual;
    boolean enDescanso=false;
    Label t_1,t_2;
    Timeline timeline;
    Media media;
    MediaPlayer mediaPlayer;
    Button btn_play = new Button();
    Button btn_stop = new Button();
    Button btn_reset = new Button();
    Button btn_change = new Button();
    Button btn_save = new Button();
    Button btn_view = new Button();
    ObservableList<registroConcentracion> registros = FXCollections.observableArrayList();

    String style_empty_tf_input="-fx-padding: 0 2 0 2;"+
                                "-fx-border-color: transparent;"+
                                "-fx-text-align: center;" +
                                "-fx-background-color: rgb(247, 178, 103);"+
                                "-fx-text-fill: rgb(242, 140, 140);";
    String style_initial_tf_input= "-fx-background-color: transparent;"+
                                "-fx-text-align: center;" +
                                "-fx-padding : 0 2 0 2;"+
                                "-fx-background-insets: 0;\n" +
                                "-fx-background-radius: 0;\n" +
                                "-fx-border-color: transparent;" +
                                "-fx-text-fill: rgb(242, 140, 140);";
    String style_initial_tf_show="-fx-background-color: transparent;"+
                                    "-fx-text-align: center;" +
                                    "-fx-padding : 0 5 0 5;"+
                                    "-fx-background-insets: 0;\n" +
                                    "-fx-background-radius: 0;\n" +
                                    "-fx-border-color: transparent;" +
                                    "-fx-text-fill: rgb(242, 140, 140);";

    @Override
    public void start(Stage primaryStage){
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(
                new Image(getClass().getResourceAsStream("/Iconos/apple-128x128.png")));

        Font.loadFont(getClass().getResourceAsStream("/contenido/Poppins-Bold/poppins/Poppins-Bold.ttf"), 14);
        Image backgroundImage = new Image("/resources/Fondos/fondo_gif_1.gif");
        BackgroundImage bg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,false,false, true,false)
        );
//ICONOS
        SVGPath icon_play = new SVGPath();
        icon_play.setContent("m11.596 8.697-6.363 3.692c-.54.313-1.233-.066-1.233-.697V4.308c0-.63.692-1.01 1.233-.696l6.363 3.692a.802.802 0 0 1 0 1.393");
        icon_play.setFill(Color.rgb(217,123,108));
        icon_play.setScaleX(3.1);
        icon_play.setScaleY(3.1);

        SVGPath icon_stop = new SVGPath();
        icon_stop.setContent("M5.5 3.5A1.5 1.5 0 0 1 7 5v6a1.5 1.5 0 0 1-3 0V5a1.5 1.5 0 0 1 1.5-1.5m5 0A1.5 1.5 0 0 1 12 5v6a1.5 1.5 0 0 1-3 0V5a1.5 1.5 0 0 1 1.5-1.5");
        icon_stop.setFill(Color.rgb(217,123,108));
        icon_stop.setScaleX(3.1);
        icon_stop.setScaleY(3.1);

        SVGPath icon_reset_1 = new SVGPath();
        icon_reset_1.setContent("M11.534 7h3.932a.25.25 0 0 1 .192.41l-1.966 2.36a.25.25 0 0 1-.384 0l-1.966-2.36a.25.25 0 0 1 .192-.41m-11 2h3.932a.25.25 0 0 0 .192-.41L2.692 6.23a.25.25 0 0 0-.384 0L.342 8.59A.25.25 0 0 0 .534 9");
        icon_reset_1.setFill(Color.rgb(217,123,108));
        icon_reset_1.setScaleX(2.5);
        icon_reset_1.setScaleY(2.5);
        SVGPath icon_reset_2 = new SVGPath();
        icon_reset_2.setContent("M8 3c-1.552 0-2.94.707-3.857 1.818a.5.5 0 1 1-.771-.636A6.002 6.002 0 0 1 13.917 7H12.9A5 5 0 0 0 8 3M3.1 9a5.002 5.002 0 0 0 8.757 2.182.5.5 0 1 1 .771.636A6.002 6.002 0 0 1 2.083 9z");
        icon_reset_2.setFill(Color.rgb(217,123,108));
        icon_reset_2.setScaleX(2.5);
        icon_reset_2.setScaleY(2.5);
        Group icon_reset = new Group(icon_reset_1,icon_reset_2);

        SVGPath icon_change = new SVGPath();
        icon_change.setContent("M16 8A8 8 0 1 0 0 8a8 8 0 0 0 16 0m-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707z");
        icon_change.setFill(Color.rgb(217,123,108));
        icon_change.setScaleX(2.1);
        icon_change.setScaleY(2.1);

        SVGPath icon_save_1 = new SVGPath();
        icon_save_1.setContent("M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5");
        icon_save_1.setFill(Color.rgb(217,123,108));
        icon_save_1.setScaleX(2.5);
        icon_save_1.setScaleY(2.5);
        SVGPath icon_save_2 = new SVGPath();
        icon_save_2.setContent("M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708z");
        icon_save_2.setFill(Color.rgb(217,123,108));
        icon_save_2.setScaleX(2.5);
        icon_save_2.setScaleY(2.5);
        Group icon_save = new Group(icon_save_1,icon_save_2);

        SVGPath icon_view_1 = new SVGPath();
        icon_view_1.setContent("M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8M1.173 8a13 13 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5s3.879 1.168 5.168 2.457A13 13 0 0 1 14.828 8q-.086.13-.195.288c-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5s-3.879-1.168-5.168-2.457A13 13 0 0 1 1.172 8z");
        icon_view_1.setFill(Color.rgb(217,123,108));
        icon_view_1.setScaleX(2.5);
        icon_view_1.setScaleY(2.5);
        SVGPath icon_view_2 = new SVGPath();
        icon_view_2.setContent("M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0");
        icon_view_2.setFill(Color.rgb(217,123,108));
        icon_view_2.setScaleX(2.5);
        icon_view_2.setScaleY(2.5);
        Group icon_view = new Group(icon_view_1,icon_view_2);

//BOTONES

        btn_play.setGraphic(icon_play);
        btn_stop.setGraphic(icon_stop);
        btn_reset.setGraphic(icon_reset);
        btn_change.setGraphic(icon_change);
        btn_save.setGraphic(icon_save);
        btn_view.setGraphic(icon_view);
        btn_play.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        btn_stop.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        btn_reset.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        btn_change.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        btn_save.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        btn_view.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        btn_play.setCursor(Cursor.HAND);
        btn_stop.setCursor(Cursor.HAND);
        btn_reset.setCursor(Cursor.HAND);
        btn_change.setCursor(Cursor.HAND);
        btn_save.setCursor(Cursor.HAND);
        btn_view.setCursor(Cursor.HAND);
//MENSAJERS BOTONE
        btn_play.setTooltip(craerToolTip("Iniciar Pomodoro"));
        btn_stop.setTooltip(craerToolTip("Detener Pomodoro"));
        btn_reset.setTooltip(craerToolTip("Reset Pomodoro"));
        btn_change.setTooltip(craerToolTip("Cambiar Pomodoro"));
        btn_view.setTooltip(craerToolTip("Ver reporte"));
//CAMBIAR COLOR CON MOUSE BOTONE
        btn_play.setOnMouseEntered(e -> {
            icon_play.setFill(Color.rgb(242,140,140));
        });
        btn_play.setOnMouseExited(e->{
            icon_play.setFill(Color.rgb(217,123,108));
        });
        btn_stop.setOnMouseEntered(e -> {
            icon_stop.setFill(Color.rgb(242,140,140));
        });
        btn_stop.setOnMouseExited(e->{
            icon_stop.setFill(Color.rgb(217,123,108));
        });
        btn_reset.setOnMouseEntered(e -> {
            icon_reset_1.setFill(Color.rgb(242,140,140));
            icon_reset_2.setFill(Color.rgb(242,140,140));

        });
        btn_reset.setOnMouseExited(e->{
            icon_reset_1.setFill(Color.rgb(217,123,108));
            icon_reset_2.setFill(Color.rgb(217,123,108));
        });
        btn_change.setOnMouseEntered(e -> {
            icon_change.setFill(Color.rgb(242,140,140));
        });
        btn_change.setOnMouseExited(e -> {
            icon_change.setFill(Color.rgb(217,123,108));
        });
        btn_save.setOnMouseEntered(e->{
            icon_save_1.setFill(Color.rgb(242,140,140));
            icon_save_2.setFill(Color.rgb(242,140,140));
        });
        btn_save.setOnMouseExited(e->{
            icon_save_1.setFill(Color.rgb(217,123,108));
            icon_save_2.setFill(Color.rgb(217,123,108));
        });
        btn_view.setOnMouseEntered(e->{
            icon_view_1.setFill(Color.rgb(217,123,108));
            icon_view_2.setFill(Color.rgb(217,123,108));
        });
        btn_view.setOnMouseExited(e->{
            icon_view_1.setFill(Color.rgb(217,123,108));
            icon_view_2.setFill(Color.rgb(217,123,108));
        });
//POSICION BOTONES
        btn_play.setLayoutX(265);
        btn_play.setLayoutY(348);
        btn_stop.setLayoutX(btn_play.getLayoutX()+btn_play.getWidth()+60);
        btn_stop.setLayoutY(btn_play.getLayoutY());
        btn_reset.setLayoutX(btn_stop.getLayoutX()+btn_stop.getWidth()+40);
        btn_reset.setLayoutY(btn_play.getLayoutY()-5);
        btn_change.setLayoutX(btn_reset.getLayoutX()+btn_reset.getWidth()+65);
        btn_change.setLayoutY(btn_play.getLayoutY()-1);
        btn_view.setLayoutX(btn_change.getLayoutX()+btn_change.getWidth()+45);
        btn_view.setLayoutY(btn_play.getLayoutY()-5);

//ACCION DE BOTONES
        btn_play.setOnAction(e ->{
            iniciar_pomodoro();
        });
        btn_stop.setOnAction(e ->{
            stop_timer();
        });
        btn_reset.setOnAction(e ->{
            date_reset();
        });
        btn_change.setOnAction(e->{
            date_change();
        });
        btn_view.setOnAction( e->{
            mostrarRegistro();
        });

//TITULOS
        t_1=new Label("Concentracion");
        t_1.setLayoutX(85);
        t_1.setLayoutY(75);
        t_1.setFont(Font.font("Poppins", FontWeight.BOLD, 30));
        t_1.setStyle(style_initial_tf_input);
        t_2=new Label("Descanso");
        t_2.setLayoutX(t_1.getLayoutX()+t_1.getWidth()+420);
        t_2.setLayoutY(t_1.getLayoutY());
        t_2.setFont(Font.font("Poppins", FontWeight.BOLD, 30));
        t_2.setStyle(style_initial_tf_input);

//TextField
        c_hora = new TextField("00");
        c_hora.setLayoutX(66);
        c_hora.setLayoutY(205);
        c_hora.setPrefWidth(34);
        c_hora.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
        c_hora.setStyle(style_initial_tf_input);
        c_hora.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,2}")) {
                return change;
            }
            return null;
            }));
        c_hora.setOnAction(e -> formatear(c_hora));
        c_hora.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) formatear(c_hora);
        });
       c_hora.setTooltip(craerToolTip("Ingresa Hora"));

        c_min = new TextField("00");
        c_min.setLayoutX(c_hora.getLayoutX()+c_hora.getWidth()+120);
        c_min.setLayoutY(c_hora.getLayoutY());
        c_min.setPrefWidth(34);
        c_min.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
        c_min.setStyle(style_initial_tf_input);
        c_min.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,2}")) {
                return change;
            }
            return null;
            }));
        c_min.setOnAction(e -> formatear(c_min));
        c_min.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) formatear(c_min);
        });
        c_min.setTooltip(craerToolTip("Ingresar Minutos"));

        c_seg=new TextField("00");
        c_seg.setLayoutX(c_min.getLayoutX()+c_min.getWidth()+102);
        c_seg.setLayoutY(c_min.getLayoutY());
        c_seg.setPrefWidth(34);
        c_seg.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
        c_seg.setStyle(style_initial_tf_input);
        c_seg.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,2}")) {
                return change;
            }
            return null;
        }));
        c_seg.setOnAction(e -> formatear(c_seg));
        c_seg.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) formatear(c_min);
        });
        c_seg.setTooltip(craerToolTip("Ingresar Segundos"));

        d_hora = new TextField("00");
        d_hora.setLayoutX(c_seg.getLayoutX()+c_seg.getWidth()+165);
        d_hora.setLayoutY(c_seg.getLayoutY());
        d_hora.setPrefWidth(34);
        d_hora.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
        d_hora.setStyle(style_initial_tf_input);
        d_hora.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,2}")) {
                return change;
            }
            return null;
            }));
        d_hora.setTooltip(craerToolTip("Ingresar Hora"));

        d_min = new TextField("00");
        d_min.setLayoutX(d_hora.getLayoutX()+d_hora.getWidth()+105);
        d_min.setLayoutY(d_hora.getLayoutY());
        d_min.setPrefWidth(34);
        d_min.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
        d_min.setStyle(style_initial_tf_input);
        d_min.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,2}")) {
                return change;
            }
            return null;
        }));
        d_min.setTooltip(craerToolTip("Ingresar Minutos"));

        d_seg=new TextField("00");
        d_seg.setLayoutX(d_min.getLayoutX()+d_min.getWidth()+105);
        d_seg.setLayoutY(d_min.getLayoutY());
        d_seg.setPrefWidth(34);
        d_seg.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
        d_seg.setStyle(style_initial_tf_input);
        d_seg.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,2}")) {
                return change;
            }
            return null;
        }));
        d_seg.setTooltip(craerToolTip("Ingresar Segundos"));

//TEXTFIELD PARA MOSTRAR HORA
        c_hora_m = new TextField("00");
        c_hora_m.setLayoutX(50);
        c_hora_m.setLayoutY(110);
        c_hora_m.setPrefWidth(100);
        c_hora_m.setEditable(false);
        c_hora_m.setFont(Font.font("Poppins", FontWeight.BOLD, 70));
        c_hora_m.setStyle(style_initial_tf_show);
        c_hora_m.setTooltip(craerToolTip("Hora"));

        c_min_m = new TextField("25");
        c_min_m.setLayoutX(c_hora_m.getLayoutX()+c_hora_m.getPrefWidth()+5);
        c_min_m.setLayoutY(c_hora_m.getLayoutY());
        c_min_m.setPrefWidth(100);
        c_min_m.setEditable(false);
        c_min_m.setFont(Font.font("Poppins", FontWeight.BOLD, 70));
        c_min_m.setStyle(style_initial_tf_show);
        c_min_m.setTooltip(craerToolTip("Minutos"));

        c_seg_m = new TextField("00");
        c_seg_m.setLayoutX(c_min_m.getLayoutX()+c_min_m.getPrefWidth()+5);
        c_seg_m.setLayoutY(c_min_m.getLayoutY());
        c_seg_m.setPrefWidth(100);
        c_seg_m.setEditable(false);
        c_seg_m.setFont(Font.font("Poppins", FontWeight.BOLD, 70));
        c_seg_m.setStyle(style_initial_tf_show);
        c_seg_m.setTooltip(craerToolTip("Segundos"));

        d_hora_m = new TextField("00");
        d_hora_m.setLayoutX(c_min_m.getLayoutX()+c_min_m.getPrefWidth()+170);
        d_hora_m.setLayoutY(c_min_m.getLayoutY());
        d_hora_m.setPrefWidth(100);
        d_hora_m.setEditable(false);
        d_hora_m.setFont(Font.font("Poppins", FontWeight.BOLD, 70));
        d_hora_m.setStyle(style_initial_tf_show);
        d_hora_m.setTooltip(craerToolTip("Hora"));

        d_min_m = new TextField("05");
        d_min_m.setLayoutX(d_hora_m.getLayoutX()+d_hora_m.getPrefWidth()+5);
        d_min_m.setLayoutY(d_hora_m.getLayoutY());
        d_min_m.setPrefWidth(100);
        d_min_m.setEditable(false);
        d_min_m.setFont(Font.font("Poppins", FontWeight.BOLD, 70));
        d_min_m.setStyle(style_initial_tf_show);
        d_min_m.setTooltip(craerToolTip("Minutos"));

        d_seg_m= new TextField("00");
        d_seg_m.setLayoutX(d_min_m.getLayoutX()+d_min_m.getPrefWidth()+5);
        d_seg_m.setLayoutY(d_min_m.getLayoutY());
        d_seg_m.setPrefWidth(100);
        d_seg_m.setEditable(false);
        d_seg_m.setFont(Font.font("Poppins", FontWeight.BOLD, 70));
        d_seg_m.setStyle(style_initial_tf_show);
        d_seg_m.setTooltip(craerToolTip("Segundos"));
//AGREGAR FONDO
        Background background = new Background(bg);
//CARGAR REGISTROS ARCHIVO CVS
        cargarRegistrosArchivo();

        Pane root= new Pane();
        root.getChildren().addAll(btn_play,btn_stop,btn_reset,btn_change,btn_view,c_hora,c_min,c_seg,d_hora,d_min,d_seg,c_hora_m,c_min_m,c_seg_m,d_hora_m,d_min_m,d_seg_m,t_1,t_2);
        root.setBackground(background);
        Scene scene = new Scene(root, 800, 450);
        primaryStage.setTitle("Pomodoro");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void iniciar_pomodoro()
    {
        int c_hora_mos = Integer.parseInt(c_hora_m.getText().trim());
        int c_min_mos=Integer.parseInt(c_min_m.getText().trim());
        int c_seg_mos=Integer.parseInt(c_seg_m.getText().trim());
        int d_hora_mos=Integer.parseInt(d_hora_m.getText().trim());
        int d_min_mos=Integer.parseInt(d_min_m.getText().trim());
        int d_seg_mos=Integer.parseInt(d_seg_m.getText().trim());

        tiempoTrabajo=(c_hora_mos*3600)+(c_min_mos*60)+c_seg_mos;
        tiempoDescanso=(d_hora_mos*3600)+(d_min_mos*60+d_seg_mos);
        tiempoActual=tiempoTrabajo;
        System.out.println("TIEMPO ACTUAL iniciar_pomodoro : "+tiempoActual);
        guardaRegistro(d_hora_mos+":"+d_min_mos+":"+d_seg_mos,c_hora_mos+":"+c_min_mos+":"+c_seg_mos);
        iniciar_timer();
    }
    private void formatear(TextField txt) {
        String texto = txt.getText().trim();
        if (texto.matches("\\d")) {
            txt.setText("0" + texto);
        }
    }
    public void iniciar_timer()
    {
        if(timeline != null) timeline.stop();

        timeline=new Timeline(new KeyFrame(Duration.seconds(1), e->{
            tiempoActual--;
          //  c_hora_m.setText(formatearTiempo(tiempoActual));
            mostrarTiempo(tiempoActual);
            if(tiempoActual<=0)
            {
                if(!enDescanso){
                    //cambia a descanso
                    StarAudio("/Audios/Alertas/"+"6"+".mp3");
                    enDescanso=true;
                    tiempoActual=tiempoDescanso;
                    mostrarNotificacion("POMODORO","Descanso iniciado.");
                    System.out.println("TIEMPO DE DESCANSO INICIADO: "+tiempoDescanso);
                }else{
                    //termina ciclo
                    System.out.println("Ciclo completado");
                    StarAudio("/Audios/Alertas/"+"1"+".mp3");
                    mostrarNotificacion("POMODORO","Concentración completada \uD83C\uDF45.");
                    timeline.stop();

                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        StopAudio();
    }
    public void mostrarTiempo(int tiempoActual){
        System.out.println("\u001B[34m FORMATEAR TIEMPO : DESCANSO = \u001B[0m"+enDescanso);
        if(enDescanso)
        {
            d_hora_m.setText(String.format("%02d",tiempoActual/3600));
            d_min_m.setText(String.format("%02d",(tiempoActual%3600)/60));
            d_seg_m.setText(String.format("%02d",tiempoActual%60));
            System.out.printf("DESCANSO: "+"%02d:%02d:%02d%n", tiempoActual/3600,(tiempoActual%3600)/60, tiempoActual % 60);
            System.out.println("\u001B[34m DESCANSO: DESCANSO = \u001B[0m"+enDescanso);
        }else {
            c_hora_m.setText(String.format("%02d",tiempoActual/3600));
            c_min_m.setText(String.format("%02d",(tiempoActual%3600)/60));
            c_seg_m.setText(String.format("%02d",tiempoActual%60));
            System.out.printf("CONCENTRATION: "+"%02d:%02d:%02d%n", tiempoActual/3600, tiempoActual/3600, tiempoActual % 60);
            System.out.println("\u001B[34m CONCENTRATION : DESCANSO = \u001B[0m"+enDescanso);
        }
    }
    public void stop_timer()
    {

        timeline.stop();
    }
    public void date_change()
    {
       if(timeline!=null) {
           stop_timer();
       }
        if(c_hora.getText().isEmpty()||c_min.getText().isEmpty()||d_hora.getText().isEmpty()||d_min.getText().isEmpty()||d_seg.getText().isEmpty()||c_seg.getText().isEmpty())
        {
            if (c_hora.getText().isEmpty())
            {c_hora.setStyle(style_empty_tf_input);}
            if (c_min.getText().isEmpty())
            {c_min.setStyle(style_empty_tf_input);}
            if (c_seg.getText().isEmpty())
            {d_min.setStyle(style_empty_tf_input);}
            if (d_hora.getText().isEmpty())
            {d_hora.setStyle(style_empty_tf_input);}
            if (d_min.getText().isEmpty())
            {d_min.setStyle(style_empty_tf_input);}
            if (d_seg.getText().isEmpty())
            {d_min.setStyle(style_empty_tf_input);}

        }
        else {
            c_hora.setStyle(style_initial_tf_input);
            c_min.setStyle(style_initial_tf_input);
            c_seg.setStyle(style_initial_tf_input);
            d_hora.setStyle(style_initial_tf_input);
            d_min.setStyle(style_initial_tf_input);
            d_seg.setStyle(style_initial_tf_input);
            try{
                c_hora_m.setText(c_hora.getText().trim());
                formatear(c_hora_m);
                c_hora.setText("00");
                d_hora_m.setText(d_hora.getText().trim());
                formatear(d_hora_m);
                d_hora.setText("00");
                c_min_m.setText(c_min.getText().trim());
                d_min_m.setText(d_min.getText().trim());
                formatear(c_min_m);
                formatear(d_min_m);
                c_min.setText("00");
                d_min.setText("00");
                c_seg_m.setText(c_seg.getText());
                formatear(c_seg_m);
                c_seg.setText("00");
                d_seg_m.setText(d_seg.getText());
                formatear(d_seg_m);
                d_seg.setText("00");

            }catch (NumberFormatException e)
            {
                System.out.println(""+e);
            }
        }
    }
    public void date_reset()
    {
        if(timeline!=null)
        {
            stop_timer();
        }
        c_hora_m.setText("00");
        c_min_m.setText("25");
        c_seg_m.setText("00");
        d_hora_m.setText("00");
        d_min_m.setText("05");
        d_seg_m.setText("00");
        enDescanso=false;

    }
    public void StarAudio(String ruta_audio){
        //ruta_audio = "/Audios/Alertas/"+"1"+".mp3";
        media = new Media(Objects.requireNonNull(getClass().getResource(ruta_audio)).toExternalForm());
        mediaPlayer = new MediaPlayer(media);

        if (mediaPlayer!=null) {
            mediaPlayer.play();
            System.out.println("\u001B[34m INICIA AUDIO \u001B[0m");
        }
    }
    public void StopAudio(){
        if (mediaPlayer!=null) {
            mediaPlayer.stop();
            System.out.println("\u001B[34m DETENER AUDIO \u001B[0m");
        }
    }
    public Tooltip craerToolTip(String msj)
    {
        Tooltip  tip=new Tooltip(msj);
        tip.setShowDelay(Duration.millis(200));
        tip.setPrefSize(120,30);
        tip.setMinSize(120,30);
        tip.setMaxSize(120,30);
        tip.setWrapText(true);
        tip.setStyle("""
                        -fx-background-color: #F28C8C;
                        -fx-text-fill: white;
                        -fx-pref-width: 120px;
                        -fx-pref-height: 40px;
                        -fx-wrap-text: true;
                        -fx-alignment: center;
                        -fx-font-size: 11px;
                    """);
        return tip;
    }
    public void guardaRegistro(String descanso, String concentracion){
        String fecha=  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        registroConcentracion registro = new registroConcentracion(fecha,descanso,concentracion);
        registros.add(registro);
        guardar_registro_archivo(registro);
    }
    public void mostrarRegistro(){
        Stage ventanaNueva = new Stage();
        ventanaNueva.setTitle("Registro");
        TableView<registroConcentracion> tabla = new TableView<>();
        //FECHA
        TableColumn<registroConcentracion, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        //CONCENTRACION
        TableColumn<registroConcentracion, String> colConcentracion = new TableColumn<>("Concentración");
        colConcentracion.setCellValueFactory(new PropertyValueFactory<>("concentracion"));
        //DESCANSO
        TableColumn<registroConcentracion, String> colDescanso = new TableColumn<>("Descanso");
        colDescanso.setCellValueFactory(new PropertyValueFactory<>("descanso"));

        tabla.setStyle("""
            -fx-table-cell-border-color: transparent;
            -fx-border-color: transparent;
            -fx-selection-bar: #F28C8C;
            -fx-selection-bar-non-focused: #FAD0D0;
            -fx-table-cell-border-color: transparent;
            -fx-border-color: transparent;
            -fx-background-insets: 0;
        """);

        tabla.getColumns().addAll(colFecha,colConcentracion,colDescanso);
        tabla.setItems(registros);
        VBox root = new VBox(tabla);
        root.setPadding(new Insets(15));
        Scene scene = new Scene(root, 330, 350);
        ventanaNueva.setScene(scene);
        ventanaNueva.setResizable(false);
        ventanaNueva.show();
    }
    public void guardar_registro_archivo(registroConcentracion registroConcentracion){
        try (FileWriter writer = new FileWriter("registros_pomodoro.csv", true)){
            writer.write(registroConcentracion.getFecha()+","+
                            registroConcentracion.getConcentracion()+","+
                            registroConcentracion.getDescanso()+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cargarRegistrosArchivo(){
        File archivo = new File("registros_pomodoro.csv");
        if (!archivo.exists())
        {return;}

        try(BufferedReader render = new BufferedReader(new FileReader(archivo))){
           String linea;
           while((linea= render.readLine()) !=null){
               String[] datos=linea.split(",");
               if (datos.length==3){
                   registros.add(new registroConcentracion(
                           datos[0],
                           datos[2],
                           datos[1]
                   ));
               }
           }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void mostrarNotificacion(String titulo, String mensaje)
    {
        if(!SystemTray.isSupported()){
            System.out.println("SystemTray no soportado");
            return;
        }
        try{
            SystemTray tray = SystemTray.getSystemTray();
            java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icono.png");

            PopupMenu menu = new PopupMenu();
            MenuItem abrir = new MenuItem("Abrir");
            MenuItem salir = new MenuItem("Salir");

            menu.add(abrir);
            menu.addSeparator();
            menu.add(salir);

            TrayIcon trayIcon = new TrayIcon(image, "POMODORO",menu);
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Pomodoro activo");
            tray.add(trayIcon);
            trayIcon.displayMessage(titulo,mensaje,TrayIcon.MessageType.INFO);

            salir.addActionListener(e -> {
                System.exit(0);
            });



            tray.remove(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
